package com.vandson.marvel.character.api;

import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import com.vandson.marvel.validators.LimitValidator;
import com.vandson.marvel.validators.OrderByValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CharactersFilterValidator  {

    public static final List<String> ACCEPTED_LIST_ORDERING = Arrays.asList("modified", "-modified", "name", "-name");

    public List<MarvelErrorMessage> validateParameters(Integer limit, String sortField) {
        var errors = new ArrayList<MarvelErrorMessage>();
        errors.addAll(new LimitValidator().validate(limit));
        errors.addAll(new OrderByValidator(ACCEPTED_LIST_ORDERING).validate(sortField));
        return errors;
    }
}