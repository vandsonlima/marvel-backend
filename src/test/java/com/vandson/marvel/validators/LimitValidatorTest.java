package com.vandson.marvel.validators;

import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LimitValidatorTest {

    private static final Integer MAX_VALUE = 100;
    private static final int MIN_VALUE = 1;

    @Test
    @DisplayName("Limite maior que MAX_VALUE deve gerar item na lista de erro")
    public void validateParametersWithLimitGreaterThanMaxValue() {
        List<MarvelErrorMessage> results = new LimitValidator().validate(MAX_VALUE+1);
        assertEquals(1, results.size());
    }

    @Test
    @DisplayName("Limite menor que MIN_VALUE deve gerar um item na lista de erro")
    public void validateParametersWithLimitLessThanMinValue() {
        List<MarvelErrorMessage> results = new LimitValidator().validate(MIN_VALUE-1);
        assertEquals(1, results.size());
    }

}