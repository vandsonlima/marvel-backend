package com.vandson.marvel.validators;

import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
public class LimitValidator {

    public List<? extends MarvelErrorMessage> validate(Integer limit) {
        List<MarvelErrorMessage> errors = new ArrayList<>();
        if (Objects.nonNull(limit)) {
            if (limit <= 0)
                errors.add(new MarvelErrorMessage(HttpStatus.CONFLICT.value(), "Limit invalid or below 1."));
            else if (limit > 100)
                errors.add(new MarvelErrorMessage(HttpStatus.CONFLICT.value(), "Limit greater than 100."));
        }
        return errors;
    }


}
