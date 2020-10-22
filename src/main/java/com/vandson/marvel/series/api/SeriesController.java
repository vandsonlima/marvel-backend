package com.vandson.marvel.series.api;

import com.vandson.marvel.character.domain.CharacterRepository;
import com.vandson.marvel.compartilhado.api.MarvelController;
import com.vandson.marvel.compartilhado.errors.MarvelException;
import com.vandson.marvel.series.domain.SerieService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
@RestController
@RequestMapping("/v1/public")
public class SeriesController extends MarvelController {

    private final CharacterRepository characterRepository;
    private final SerieService seriesService;

    public SeriesController(CharacterRepository characterRepository, SerieService seriesService) {
        this.characterRepository = characterRepository;
        this.seriesService = seriesService;
    }

    @GetMapping("/characters/{characterId}/series")
    public ResponseEntity getAll(@PathVariable Long characterId,
                                 @RequestParam(value = "limit", required = false) Integer limit,
                                 @RequestParam(value = "offset", required = false) Integer offset,
                                 @RequestParam(value = "orderBy", required = false) String sortField,
                                 @RequestParam(value = "name", required = false) String title,
                                 @RequestParam(value = "nameStartsWith", required = false) String titleStartsWith,
                                 @RequestParam(value = "startYear", required = false) Integer startYear,
                                 @RequestParam(value = "modifiedSince", required = false)
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate modifiedSince,
                                 @RequestParam(value = "comics", required = false) String comics,
                                 @RequestParam(value = "events", required = false) String events,
                                 @RequestParam(value = "stories", required = false) String stories){

        var optionalMarvelCharacter = characterRepository.findById(characterId);
        if(optionalMarvelCharacter.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("character not found.");

        try {
            var series = seriesService.findAllByFilters(optionalMarvelCharacter.get(), title, titleStartsWith, startYear, modifiedSince, comics, events, stories, limit, offset, sortField);
            return getResponseEntityDataWrapper(limit, offset, series.stream().map(SeriesResponse::new ).collect(Collectors.toList()), seriesService.countByFilters(optionalMarvelCharacter.get(), title, titleStartsWith, startYear, modifiedSince, comics, events, stories));
        }catch (MarvelException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessages());
        }
    }

    @GetMapping("/series/{seriesId}")
    public SeriesResponse getOne(@PathVariable Long seriesId){
        return seriesService.getOne(seriesId)
                .map(SeriesResponse::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "serie not found"));
    }
}
