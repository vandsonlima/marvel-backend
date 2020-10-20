package com.vandson.marvel.character.api;

import com.vandson.marvel.character.domain.MarvelCharacterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<CharacterResponse> get(@PathVariable("characterId") Long id) {
        return marvelCharacterRepository.findById(id)
                .map(CharacterResponse::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/characters")
    public List<CharacterResponse> getAll(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "nameStartsWith", required = false) String nameStartsWith, @RequestParam(value = "modifiedSince", required = false) Date modifiedSince ){
        return marvelCharacterRepository.findAll()
                .stream().map(CharacterResponse::new)
                .collect(Collectors.toList());
    }
}
