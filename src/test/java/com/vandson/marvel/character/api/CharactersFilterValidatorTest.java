package com.vandson.marvel.character.api;

import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class CharactersFilterValidatorTest {

    private static final Integer MAX_VALUE = 100;
    private static final int MIN_VALUE = 1;
    private CharactersFilterValidator charactersFilterValidator;

    @BeforeEach
    void setUp() {
        charactersFilterValidator = new CharactersFilterValidatorImpl();
    }

    @Test
    @DisplayName("Valores nulos não devem gerar resultado")
    public void validateParametersWithNullValue() {
        List<MarvelErrorMessage> results = charactersFilterValidator.validateParameters(null, null);
        Assertions.assertEquals(List.of(), results);
    }

    @Test
    @DisplayName("Limite maior que MAX_VALUE deve gerar item na lista de erro")
    public void validateParametersWithLimitGreaterThanMaxValue() {
        List<MarvelErrorMessage> results = charactersFilterValidator.validateParameters(MAX_VALUE+1, null);
        Assertions.assertEquals(1, results.size());
    }

    @Test
    @DisplayName("Limite menor que MIN_VALUE deve gerar um item na lista de erro")
    public void validateParametersWithLimitLessThanMinValue() {
        List<MarvelErrorMessage> results = charactersFilterValidator.validateParameters(MIN_VALUE-1, null);
        Assertions.assertEquals(1, results.size());
    }

    @Test
    @DisplayName("valor do order by que não estiver  dentro da lista de palavras aceitáveis gera erro")
    public void validateOrderByWithAnyValue(){
        List<MarvelErrorMessage> results = charactersFilterValidator.validateParameters(null, "");
        Assertions.assertEquals(1, results.size());
    }

    @Test
    @DisplayName("valor do order by deve estar dentro da lista de palavras aceitáveis")
    public void validateOrderByWithAceptedValue(){
        List<MarvelErrorMessage> results = charactersFilterValidator.validateParameters(null, "name" );
        Assertions.assertEquals(0, results.size());
    }
}