package com.vandson.marvel.validators;

import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class OrderByValidatorTest {

    private List<String> acceptedList;
    private OrderByValidator orderByValidator;

    @BeforeEach
    void setUp() {
        acceptedList = List.of("modified", "-modified", "name", "-name");
        orderByValidator = new OrderByValidator(acceptedList);
    }

    @Test
    @DisplayName("valor do order by nulo não gera erro")
    public void validateOrderByWithNullValue(){
        List<MarvelErrorMessage> results = new OrderByValidator(acceptedList).validate(null);
        Assertions.assertEquals(0, results.size());
    }

    @Test
    @DisplayName("valor do order by que não estiver  dentro da lista de palavras aceitáveis gera erro")
    public void validateOrderByWithAnyValue(){
        List<MarvelErrorMessage> results = new OrderByValidator(acceptedList).validate("any");
        Assertions.assertEquals(1, results.size());
    }

    @Test
    @DisplayName("valor do order by deve estar dentro da lista de palavras aceitáveis")
    public void validateOrderByWithAceptedValue(){
        List<MarvelErrorMessage> results = orderByValidator.validate("name" );
        Assertions.assertEquals(0, results.size());
    }

}