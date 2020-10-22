package com.vandson.marvel.validators;

import com.vandson.marvel.comics.domain.FormatType;
import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
public class ComicFormatTypeValidator {

    public List<MarvelErrorMessage> validate(String formatType) {
        List<MarvelErrorMessage> errors = new ArrayList<>();
        if (StringUtils.hasLength(formatType)) {
            try{
                FormatType extract = FormatType.valueOf(formatType);
            }catch (IllegalArgumentException e){
                errors.add(new MarvelErrorMessage(HttpStatus.CONFLICT.value(), "Invalid or unrecognized parameter."));
            }
        }
        return errors;
    }
}
