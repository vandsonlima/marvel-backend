package com.vandson.marvel.comics.api;

import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import com.vandson.marvel.validators.ComicFormatTypeValidator;
import com.vandson.marvel.validators.ComicFormatValidator;
import com.vandson.marvel.validators.LimitValidator;
import com.vandson.marvel.validators.OrderByValidator;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 21/10/2020
 */
@Service
public class ComicFilterValidator {

    private static final List<String> ACCEPTED_LIST_ORDERING = Arrays.asList("title", "-title");

    public List<MarvelErrorMessage> validateParameters(Integer limit, String sortField, String formatComic, String formatType) {
        var errors = new ArrayList<MarvelErrorMessage>();
        errors.addAll(new LimitValidator().validate(limit));
        errors.addAll(new OrderByValidator(ACCEPTED_LIST_ORDERING).validate(sortField));
        errors.addAll(new ComicFormatValidator().validate(formatComic));
        errors.addAll(new ComicFormatTypeValidator().validate(formatType));
        return errors;
    }





}
