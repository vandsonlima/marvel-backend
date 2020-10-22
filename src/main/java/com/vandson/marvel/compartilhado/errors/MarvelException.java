package com.vandson.marvel.compartilhado.errors;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
public class MarvelException extends Exception {

    private final List<MarvelErrorMessage> messages;

    public MarvelException(List<MarvelErrorMessage> messages) {
        this.messages = messages;
    }

    public List<MarvelErrorMessage> getMessages() {
        return messages;
    }
}
