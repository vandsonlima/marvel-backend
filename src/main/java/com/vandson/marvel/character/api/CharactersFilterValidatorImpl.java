package com.vandson.marvel.character.api;

import com.vandson.marvel.compartilhado.domain.FilterValidator;
import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CharactersFilterValidatorImpl extends FilterValidator implements CharactersFilterValidator  {

    public static final List<String> ACCEPTED_LIST_ORDERING = Arrays.asList("modified", "-modified", "name", "-name");

    @Override
    public List<MarvelErrorMessage> validateParameters(Integer limit, String sortField) {
        var errors = new ArrayList<MarvelErrorMessage>();
        errors.addAll(validateLimit(limit));
        errors.addAll(validateOrderBy(sortField, ACCEPTED_LIST_ORDERING));
        return errors;
    }




}