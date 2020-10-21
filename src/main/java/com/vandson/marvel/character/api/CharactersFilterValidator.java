package com.vandson.marvel.character.api;

import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;

import java.util.List;

public interface CharactersFilterValidator {
    List<MarvelErrorMessage> validateParameters(Integer limit, String sortField);
}
