package com.vandson.marvel.compartilhado.errors;

import lombok.Getter;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 21/10/2020
 */
@Getter
public class MarvelErrorMessage {
    private final int code;
    private final String message;

    public MarvelErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
