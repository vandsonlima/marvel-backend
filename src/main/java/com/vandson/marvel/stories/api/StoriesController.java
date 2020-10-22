package com.vandson.marvel.stories.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
@RestController
@RequestMapping("/v1/public")
public class StoriesController {

    @GetMapping("/characters/{characterId}/stories")
    public ResponseEntity getAll(@PathVariable Long characterId) {
        return null;
    }
}
