package com.vandson.marvel.compartilhado.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */

public class PageableImpl {

    public static final int LIMIT = 20;
    public static final int OFFSET = 0;

    public static Pageable of(Integer limit, Integer offset,  String sort){
        if(limit == null)
            limit = LIMIT;
        Assert.isTrue(limit > 0 && limit <= 100, "limit must be between 1 and 100");

        if(offset == null)
            offset = OFFSET;
        Assert.isTrue(offset >= 0, "offset must be > 0");

        return PageRequest.of(offset/limit, limit, orderby(sort));
    }

    private static Sort orderby(String sortField) {
        if (!StringUtils.hasText(sortField))
            return Sort.by(Sort.DEFAULT_DIRECTION, "id");

        var list = Arrays.asList("modified", "-modified", "name", "-name");
        if (!list.contains(sortField))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Invalid or unrecognized ordering parameter");

        if (sortField.startsWith("-"))
            return Sort.by(Sort.Direction.DESC, sortField.substring(1));

        return Sort.by(Sort.Direction.ASC, sortField);
    }

}
