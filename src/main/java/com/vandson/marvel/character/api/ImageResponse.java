package com.vandson.marvel.character.api;

import com.vandson.marvel.character.domain.MarvelCharacter;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
public class ImageResponse {
    public String path;
    public String extension;

    public ImageResponse(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }
}
