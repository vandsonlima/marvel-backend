package com.vandson.marvel.events.domain;

import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import com.vandson.marvel.validators.LimitValidator;
import com.vandson.marvel.validators.OrderByValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
public class EventFilterValidator {

    public static final List<String> ACCEPTED_LIST_ORDERING = Arrays.asList("title", "start", "modified", "-modified", "-title", "-start");

    public List<MarvelErrorMessage> validateParameters(Integer limit, String sortField) {
        var errors = new ArrayList<MarvelErrorMessage>();
        errors.addAll(new LimitValidator().validate(limit));
        errors.addAll(new OrderByValidator(ACCEPTED_LIST_ORDERING).validate(sortField));
        return errors;
    }

}
