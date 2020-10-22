package com.vandson.marvel.comics.api;

import com.vandson.marvel.character.domain.MarvelCharacterRepository;
import com.vandson.marvel.comics.domain.ComicsService;
import com.vandson.marvel.comics.domain.FilterComics;
import com.vandson.marvel.compartilhado.api.MarvelController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 21/10/2020
 */
@RestController
@RequestMapping("/v1/public")
public class ComicFinderController extends MarvelController {

    private final MarvelCharacterRepository marvelCharacterRepository;
    private final ComicsService comicsService;
    private final ComicFilterValidator comicFilterValidator;

    public ComicFinderController(MarvelCharacterRepository marvelCharacterRepository, ComicsService comicsService, ComicFilterValidator comicFilterValidator) {
        this.marvelCharacterRepository = marvelCharacterRepository;
        this.comicsService = comicsService;
        this.comicFilterValidator = comicFilterValidator;
    }


    @GetMapping("/characters/{characterId}/comics")
    public ResponseEntity getComics(@PathVariable Long characterId,
                                    @RequestParam(value = "limit", required = false) Integer limit,
                                    @RequestParam(value = "offset", required = false) Integer offset,
                                    @RequestParam(value = "orderBy", required = false) String sortField,
                                    @RequestParam(value = "name", required = false) String title,
                                    @RequestParam(value = "nameStartsWith", required = false) String titleStartsWith,
                                    @RequestParam(value = "formatComic" , required = false) String formatComic,
                                    @RequestParam(value = "formatType" , required = false) String formatType){

        var optionalMarvelCharacter = marvelCharacterRepository.findById(characterId);
        if(optionalMarvelCharacter.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("character not found.");

        var marvelErrorMessages = comicFilterValidator.validateParameters(limit, sortField, formatComic, formatType);
        if(!marvelErrorMessages.isEmpty())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(marvelErrorMessages);

        FilterComics filterComics = FilterComics.FilterComicsBuilder.aFilterComics()
                .withCharacter(optionalMarvelCharacter.get())
                .withTitle(title)
                .withTitleStartsWith(titleStartsWith)
                .withFormatComic(formatComic)
                .withFormatType(formatType)
                .build();

        var comicResponses = comicsService.getAllByFilter(filterComics, offset, limit, sortField)
                .stream()
                .map(comic -> new ComicResponse().fromModel(comic))
                .collect(Collectors.toList());

        return getResponseEntityDataWrapper(limit, offset, comicResponses, comicsService.countByFilter(filterComics));
    }
}
