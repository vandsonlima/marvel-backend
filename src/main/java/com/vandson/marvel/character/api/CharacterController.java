package com.vandson.marvel.character.api;

import com.vandson.marvel.character.domain.CharactersSpecification;
import com.vandson.marvel.character.domain.FilterCharacter;
import com.vandson.marvel.character.domain.MarvelCharacter;
import com.vandson.marvel.character.domain.MarvelCharacterRepository;
import com.vandson.marvel.compartilhado.domain.DataContainer;
import com.vandson.marvel.compartilhado.domain.DataWraper;
import com.vandson.marvel.compartilhado.domain.PageableImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.vandson.marvel.compartilhado.domain.PageableImpl.*;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 12/08/2020
 **/
//2
@RestController
@RequestMapping("v1/public")
public class CharacterController {

    private final MarvelCharacterRepository marvelCharacterRepository;

    public CharacterController(MarvelCharacterRepository marvelCharacterRepository) {
        this.marvelCharacterRepository = marvelCharacterRepository;
    }

    @GetMapping("/characters/{characterId}")
    public DataWraper get(@PathVariable("characterId") Long id) {

        MarvelCharacter marvelCharacter = marvelCharacterRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "character not found"));

        return getDataWraper(LIMIT, OFFSET, Collections.singletonList(new CharacterResponse(marvelCharacter)), 1);
    }

    @GetMapping("/characters")
    public DataWraper getAll(@RequestParam(value = "limit", required = false) Integer limit,
                                          @RequestParam(value = "offset", required = false) Integer offset,
                                          @RequestParam(value = "orderBy", required = false) String sortField,
                                          @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "nameStartsWith", required = false) String nameStartsWith,
                                          @RequestParam(value = "modifiedSince", required = false)
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime modifiedSince) {

        Specification<MarvelCharacter> characterSpecification = CharactersSpecification
                .filter(FilterCharacter.builder()
                        .name(name)
                        .nameStartsWith(nameStartsWith)
                        .modifiedSince(modifiedSince)
                        .build());

        List<CharacterResponse> characterResponses = marvelCharacterRepository.findAll(characterSpecification, PageableImpl.of(offset, limit, sortField))
                .getContent()
                .stream()
                .map(CharacterResponse::new)
                .collect(Collectors.toList());

        return getDataWraper(limit, offset, characterResponses, marvelCharacterRepository.count(characterSpecification));

    }

    private DataWraper getDataWraper(Integer limit, Integer offset, List<CharacterResponse> items, long count) {
        var dataContainer = new DataContainer<CharacterResponse>(offset, limit, count, items);
        return DataWraper.builder().code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(dataContainer)
                .build();
    }
}
