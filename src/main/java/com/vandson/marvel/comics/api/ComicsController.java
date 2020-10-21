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
import java.util.stream.Collectors;

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
    public List<ComicResponse> getComics(@PathVariable Long characterId){
        var marvelCharacterSelected = marvelCharacterRepository.findById(characterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found"));
       return comicsRepository.findAllByCharactersIs(marvelCharacterSelected)
               .stream()
               .map(comic -> new ComicResponse().fromModel(comic))
               .collect(Collectors.toList());
    }

    @GetMapping("/comics/{comicId}")
    public ComicResponse getOne(@PathVariable Long comicId) {
        Comic comic = comicsRepository.findById(comicId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comic Not Found"));
        return new ComicResponse().fromModel(comic);
    }
}
