package com.vandson.marvel.comics.api;

import com.vandson.marvel.comics.domain.ComicRepository;
import com.vandson.marvel.compartilhado.api.MarvelController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 12/08/2020
 **/
@RestController
@RequestMapping("/v1/public")
public class ComicsController extends MarvelController {

    private final ComicRepository comicsRepository;

    public ComicsController(ComicRepository comicsRepository) {
        this.comicsRepository = comicsRepository;
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
