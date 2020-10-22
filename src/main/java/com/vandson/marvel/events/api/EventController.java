package com.vandson.marvel.events.api;

import com.vandson.marvel.character.domain.CharacterRepository;
import com.vandson.marvel.compartilhado.api.MarvelController;
import com.vandson.marvel.compartilhado.errors.MarvelException;
import com.vandson.marvel.events.domain.Event;
import com.vandson.marvel.events.domain.EventService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
@RestController
@RequestMapping("/v1/public")
public class EventController extends MarvelController {

    private final CharacterRepository characterRepository;
    private final EventService eventService;

    public EventController(CharacterRepository characterRepository, EventService eventService) {
        this.characterRepository = characterRepository;
        this.eventService = eventService;
    }

    @GetMapping("/characters/{characterId}/events")
    public ResponseEntity getAll(@PathVariable Long characterId,
                                 @RequestParam(value = "limit", required = false) Integer limit,
                                 @RequestParam(value = "offset", required = false) Integer offset,
                                 @RequestParam(value = "orderBy", required = false) String sortField,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "nameStartsWith", required = false) String nameStartsWith,
                                 @RequestParam(value = "modifiedSince", required = false)
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime modifiedSince,
                                 @RequestParam(value = "comics", required = false) String comics){

        var optionalMarvelCharacter = characterRepository.findById(characterId);
        if(optionalMarvelCharacter.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("character not found.");

        try {
            List<EventResponse> eventResponses = eventService.getAllByFilters(optionalMarvelCharacter.get(), name, nameStartsWith, modifiedSince, comics, limit, offset, sortField)
                    .stream()
                    .map(EventResponse::new)
                    .collect(Collectors.toList());
            return getResponseEntityDataWrapper(limit, offset, eventResponses, eventService.countByFilters(optionalMarvelCharacter.get(),name, nameStartsWith, modifiedSince, comics));
        } catch (MarvelException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessages());
        }
    }

    @GetMapping("/event/{eventId}")
    public EventResponse getOne(@PathVariable @NotNull Long eventId) {
        return eventService.getById(eventId)
                .map(EventResponse::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "event not found"));
    }
}
