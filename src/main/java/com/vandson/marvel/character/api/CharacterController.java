package com.vandson.marvel.character.api;

import com.vandson.marvel.character.domain.MarvelCharacter;
import com.vandson.marvel.character.domain.MarvelCharacterRepository;
import com.vandson.marvel.compartilhado.api.MarvelController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 12/08/2020
 **/
//2
@RestController
@RequestMapping("v1/public")
public class CharacterController extends MarvelController {

    private final MarvelCharacterRepository marvelCharacterRepository;


    public CharacterController(MarvelCharacterRepository marvelCharacterRepository) {
        this.marvelCharacterRepository = marvelCharacterRepository;
    }

    @GetMapping("/characters/{characterId}")
    public ResponseEntity get(@PathVariable("characterId") Long id) {
        var optionalMarvelCharacter = marvelCharacterRepository.findById(id);
        if(optionalMarvelCharacter.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Character not found.");

        var marvelCharacter = optionalMarvelCharacter.get();
        return getResponseEntityDataWrappperObject(new CharacterResponse(marvelCharacter));
    }
}
