package com.vandson.marvel.compartilhado.domain;

import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 21/10/2020
 */
public class FilterValidator {

    protected List<MarvelErrorMessage> validateLimit(Integer limit) {
        List<MarvelErrorMessage> errors = new ArrayList<>();
        if (Objects.nonNull(limit)) {
            if (limit <= 0)
                errors.add(new MarvelErrorMessage(HttpStatus.CONFLICT.value(), "Limit invalid or below 1."));
            else if (limit > 100)
                errors.add(new MarvelErrorMessage(HttpStatus.CONFLICT.value(), "Limit greater than 100."));
        }
        return errors;
    }

    protected List<MarvelErrorMessage> validateOrderBy(String sortField, @NotNull List<?> acceptedList) {
        List<MarvelErrorMessage> errors = new ArrayList<>();
        if (Objects.nonNull(sortField)) {
            if (!acceptedList.contains(sortField))
                errors.add(new MarvelErrorMessage(HttpStatus.CONFLICT.value(), "Invalid or unrecognized ordering parameter."));
        }
        return errors;
    }
}
