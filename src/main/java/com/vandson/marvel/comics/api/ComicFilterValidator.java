package com.vandson.marvel.comics.api;

import com.vandson.marvel.comics.domain.FormatComic;
import com.vandson.marvel.comics.domain.FormatType;
import com.vandson.marvel.compartilhado.domain.FilterValidator;
import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import org.springframework.hateoas.mediatype.alps.Format;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 21/10/2020
 */
@Service
public class ComicFilterValidator extends FilterValidator {

    private static final List<String> ACCEPTED_LIST_ORDERING = Arrays.asList("title", "-title");

    public List<MarvelErrorMessage> validateParameters(Integer limit, String sortField, String formatComic, String formatType) {
        var errors = new ArrayList<MarvelErrorMessage>();
        errors.addAll(validateLimit(limit));
        errors.addAll(validateOrderBy(sortField, ACCEPTED_LIST_ORDERING));
        errors.addAll(validateFormatComic(formatComic));
        errors.addAll(validateFormatType(formatType));
        return errors;
    }

    private List<? extends MarvelErrorMessage> validateFormatType(String formatType) {
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

    private List<? extends MarvelErrorMessage> validateFormatComic(String formatComic) {
        List<MarvelErrorMessage> errors = new ArrayList<>();
        if (StringUtils.hasLength(formatComic) && (Objects.isNull(FormatComic.getByName(formatComic))))
                errors.add(new MarvelErrorMessage(HttpStatus.CONFLICT.value(), "Invalid or unrecognized parameter."));
        return errors;
    }

}
