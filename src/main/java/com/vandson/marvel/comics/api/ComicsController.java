package com.vandson.marvel.comics.api;

import com.vandson.marvel.character.domain.MarvelCharacterRepository;
import com.vandson.marvel.comics.domain.ComicRepository;
import com.vandson.marvel.compartilhado.api.MarvelController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 12/08/2020
 **/
@RestController
@RequestMapping("/v1/public")
public class ComicsController extends MarvelController {

    private final ComicRepository comicsRepository;
    private final MarvelCharacterRepository marvelCharacterRepository;

    public ComicsController(ComicRepository comicsRepository, MarvelCharacterRepository marvelCharacterRepository) {
        this.comicsRepository = comicsRepository;
        this.marvelCharacterRepository = marvelCharacterRepository;
    }

    @GetMapping("/characters/{characterId}/comics")
    public ResponseEntity getComics(@PathVariable Long characterId){
        var optionalMarvelCharacter = marvelCharacterRepository.findById(characterId);
           if(optionalMarvelCharacter.isEmpty())
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body("character not found.");

        List<ComicResponse> comicResponses = comicsRepository.findAllByCharactersIs(optionalMarvelCharacter.get())
                .stream()
                .map(comic -> new ComicResponse().fromModel(comic))
                .collect(Collectors.toList());

        Integer limit = 0;
        Integer offset = 0;
        long count = 0;
        return getResponseEntityDataWrapper(limit, offset, comicResponses, count);
    }

    @GetMapping("/comics/{comicId}")
    public ResponseEntity getOne(@PathVariable Long comicId) {
       var optionalComics = comicsRepository.findById(comicId);
       if(optionalComics.isEmpty())
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comics not found.");

       var comicResponse = new ComicResponse().fromModel(optionalComics.get());
       return  getResponseEntityDataWrappperObject(comicResponse);
    }
}
