package com.vandson.marvel.validators;

import com.vandson.marvel.comics.domain.FormatComic;
import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
public class ComicFormatValidator {

    public List<? extends MarvelErrorMessage> validate(String formatComic) {
        List<MarvelErrorMessage> errors = new ArrayList<>();
        if (StringUtils.hasLength(formatComic) && (Objects.isNull(FormatComic.getByName(formatComic))))
            errors.add(new MarvelErrorMessage(HttpStatus.CONFLICT.value(), "Invalid or unrecognized parameter."));
        return errors;
    }
}
