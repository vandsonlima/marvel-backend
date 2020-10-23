package com.vandson.marvel.stories.api;

import com.vandson.marvel.character.domain.CharacterRepository;
import com.vandson.marvel.compartilhado.api.MarvelController;
import com.vandson.marvel.stories.domain.StorieResponse;
import com.vandson.marvel.stories.domain.StoriesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
@RestController
@RequestMapping("/v1/public")
public class StoriesController extends MarvelController {

    private final CharacterRepository characterRepository;
    private final StoriesRepository storiesRepository;

    public StoriesController(CharacterRepository characterRepository, StoriesRepository storiesRepository) {
        this.characterRepository = characterRepository;
        this.storiesRepository = storiesRepository;
    }

    @GetMapping("/characters/{characterId}/stories")
    public ResponseEntity getAll(@PathVariable Long characterId) {
        var optionalMarvelCharacter = characterRepository.findById(characterId);
        if(optionalMarvelCharacter.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("character not found.");

        var result = storiesRepository
                .findAllByCharactersIs(optionalMarvelCharacter.get())
                .stream()
                .map(StorieResponse::new)
                .collect(Collectors.toList());

        return getResponseEntityDataWrapper(null, null, result, result.size());
    }

    @GetMapping("/stories/{storyId}")
    public StorieResponse getOne(@PathVariable Long storyId) {
        return storiesRepository.findById(storyId)
                .map(StorieResponse::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "storie not found"));
    }
}
