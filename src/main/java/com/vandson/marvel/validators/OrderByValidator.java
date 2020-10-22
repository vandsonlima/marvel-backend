package com.vandson.marvel.validators;

import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
public class OrderByValidator {

    private final List<?> acceptedList;

    public OrderByValidator(@NotNull List<?> acceptedList) {
        Assert.notNull(acceptedList, "acceptedList must be not null");
        this.acceptedList = acceptedList;
    }

    public List<MarvelErrorMessage> validate(String sortField) {
        List<MarvelErrorMessage> errors = new ArrayList<>();
        if (Objects.nonNull(sortField)) {
            if (!acceptedList.contains(sortField))
                errors.add(new MarvelErrorMessage(HttpStatus.CONFLICT.value(), "Invalid or unrecognized ordering parameter."));
        }
        return errors;
    }
}
