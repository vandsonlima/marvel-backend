package com.vandson.marvel.character.api;

import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharactersFilterValidatorImpl implements CharactersFilterValidator {

    public static final List<String> ACCEPTED_LIST_ORDERING = Arrays.asList("modified", "-modified", "name", "-name");

    @Override
    public List<MarvelErrorMessage> validateParameters(Integer limit, String sortField) {
        List<MarvelErrorMessage> errors = new ArrayList<MarvelErrorMessage>();
        validateLimit(limit, errors);
        validateOrderBy(sortField, errors);
        return errors;
    }

    private void validateOrderBy(String sortField, List<MarvelErrorMessage> errors) {
        if (Objects.nonNull(sortField)) {
            if (!ACCEPTED_LIST_ORDERING.contains(sortField))
                errors.add(new MarvelErrorMessage(HttpStatus.CONFLICT.value(), "Invalid or unrecognized ordering parameter."));
        }
    }

    private void validateLimit(Integer limit, List<MarvelErrorMessage> errors) {
        if (Objects.nonNull(limit)) {
            if (limit <= 0)
                errors.add(new MarvelErrorMessage(HttpStatus.CONFLICT.value(), "Limit invalid or below 1."));
            else if (limit > 100)
                errors.add(new MarvelErrorMessage(HttpStatus.CONFLICT.value(), "Limit greater than 100."));
        }
    }
}