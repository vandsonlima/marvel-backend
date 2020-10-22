package com.vandson.marvel.character.api;

import com.vandson.marvel.character.domain.CharacterService;
import com.vandson.marvel.compartilhado.api.MarvelController;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 21/10/2020
 */
@RestController
@RequestMapping("v1/public")
public class CharacterFinderController extends MarvelController {

    private final CharacterService characterService;
    private final CharactersFilterValidator charactersFilterValidator;

    public CharacterFinderController(CharacterService characterService, CharactersFilterValidator charactersFilterValidator) {
        this.characterService = characterService;
        this.charactersFilterValidator = charactersFilterValidator;
    }

    @GetMapping("/characters")
    public ResponseEntity getAll(@RequestParam(value = "limit", required = false) Integer limit,
                                 @RequestParam(value = "offset", required = false) Integer offset,
                                 @RequestParam(value = "orderBy", required = false) String sortField,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "nameStartsWith", required = false) String nameStartsWith,
                                 @RequestParam(value = "modifiedSince", required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime modifiedSince,
                                 @RequestParam(value = "comics", required = false) String comics) {

        var marvelErrorMessages = charactersFilterValidator.validateParameters(limit, sortField);
        if(!marvelErrorMessages.isEmpty())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(marvelErrorMessages);

        var characterResponses = characterService.getAllByFilter( name, nameStartsWith, modifiedSince, offset, limit, sortField, comics)
                .stream()
                .map(CharacterResponse::new)
                .collect(Collectors.toList());

        return getResponseEntityDataWrapper(limit, offset, characterResponses, characterService.countByFilter(name, nameStartsWith, modifiedSince, comics));
    }

}
