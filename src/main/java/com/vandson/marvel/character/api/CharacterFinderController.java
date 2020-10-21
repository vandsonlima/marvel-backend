package com.vandson.marvel.character.api;

import com.vandson.marvel.character.domain.FilterCharacter;
import com.vandson.marvel.character.domain.MarvelCharacterService;
import com.vandson.marvel.compartilhado.api.MarvelController;
import com.vandson.marvel.compartilhado.domain.PageableImpl;
import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 21/10/2020
 */
@RestController
@RequestMapping("v1/public")
public class CharacterFinderController extends MarvelController {

    private final MarvelCharacterService marvelCharacterService;

    public CharacterFinderController(MarvelCharacterService marvelCharacterService) {
        this.marvelCharacterService = marvelCharacterService;
    }

    @GetMapping("/characters")
    public ResponseEntity getAll(@RequestParam(value = "limit", required = false) Integer limit,
                                 @RequestParam(value = "offset", required = false) Integer offset,
                                 @RequestParam(value = "orderBy", required = false) String sortField,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "nameStartsWith", required = false) String nameStartsWith,
                                 @RequestParam(value = "modifiedSince", required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime modifiedSince) {

        var optionalResponseEntity = validateParameters(limit, sortField);
        if(optionalResponseEntity.isPresent())
            return optionalResponseEntity.get();

        FilterCharacter filterCharacter = FilterCharacter.builder()
                .name(name)
                .nameStartsWith(nameStartsWith)
                .modifiedSince(modifiedSince)
                .build();

        List<CharacterResponse> characterResponses = marvelCharacterService.getAllByFilter(filterCharacter, PageableImpl.of(offset, limit, sortField))
                .stream()
                .map(CharacterResponse::new)
                .collect(Collectors.toList());

        return getResponseEntityDataWrapper(limit, offset, characterResponses, marvelCharacterService.countByFilter(filterCharacter));
    }

    private Optional<ResponseEntity<List<MarvelErrorMessage>>> validateParameters(Integer limit, String sortField) {
        List<MarvelErrorMessage> errors = new ArrayList<>();
        if(Objects.nonNull(limit)){
            if(limit <= 0)
                errors.add(new MarvelErrorMessage(HttpStatus.CONFLICT.value(), "Limit invalid or below 1."));
            else if(limit > 100)
                errors.add(new MarvelErrorMessage(HttpStatus.CONFLICT.value(), "Limit greater than 100."));
        }

        if(Objects.nonNull(sortField)) {
            var list = Arrays.asList("modified", "-modified", "name", "-name");
            if (!list.contains(sortField))
                errors.add(new MarvelErrorMessage(HttpStatus.CONFLICT.value(), "Invalid or unrecognized ordering parameter."));
        }
        if(!errors.isEmpty())
            return Optional.of(ResponseEntity.status(HttpStatus.CONFLICT).body(errors));

        return Optional.empty();
    }
}
