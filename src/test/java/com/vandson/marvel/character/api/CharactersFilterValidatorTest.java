package com.vandson.marvel.character.api;

import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class CharactersFilterValidatorTest {

    @Test
    @DisplayName("Valores nulos não devem gerar resultado com erros")
    public void validateParametersWithNullValue() {
        List<MarvelErrorMessage> results = new CharactersFilterValidator().validateParameters(null, null);
        Assertions.assertEquals(List.of(), results);
    }
}