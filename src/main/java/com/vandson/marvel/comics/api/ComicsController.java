package com.vandson.marvel.comics.api;

import com.vandson.marvel.character.domain.MarvelCharacter;
import com.vandson.marvel.character.domain.MarvelCharacterRepository;
import com.vandson.marvel.comics.domain.Comic;
import com.vandson.marvel.comics.domain.ComicRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 12/08/2020
 **/
@RestController
@RequestMapping("/v1/public")
public class ComicsController {

    private final ComicRepository comicsRepository;
    private final MarvelCharacterRepository marvelCharacterRepository;

    public ComicsController(ComicRepository comicsRepository, MarvelCharacterRepository marvelCharacterRepository) {
        this.comicsRepository = comicsRepository;
        this.marvelCharacterRepository = marvelCharacterRepository;
    }

    @GetMapping("/characters/{characterId}/comics")
    public List<Comic> getComics(@PathVariable Long characterId){
        MarvelCharacter marvelCharacterSelected = marvelCharacterRepository.findById(characterId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found"));
        return comicsRepository.findAllByCharactersIs(marvelCharacterSelected);
    }

    @GetMapping("/comics/{comicId}")
    public Comic getOne(@PathVariable Long comicId) {
        return comicsRepository.findById(comicId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comics Not Found"));
    }
}
