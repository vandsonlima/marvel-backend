package com.vandson.marvel.comics.api;

import com.vandson.marvel.compartilhado.domain.FilterValidator;
import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 21/10/2020
 */
@Service
public class ComicFilterValidator extends FilterValidator {

    private static final List<String> ACCEPTED_LIST_ORDERING = Arrays.asList("title", "-title");

    public List<MarvelErrorMessage> validateParameters(Integer limit, String sortField) {
        var errors = new ArrayList<MarvelErrorMessage>();
        errors.addAll(validateLimit(limit));
        errors.addAll(validateOrderBy(sortField, ACCEPTED_LIST_ORDERING));
        return errors;
    }
}
