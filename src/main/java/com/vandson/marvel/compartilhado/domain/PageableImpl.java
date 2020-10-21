package com.vandson.marvel.compartilhado.domain;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
@Getter
public class PageableImpl {

    public static final int LIMIT = 20;
    public static final int OFFSET = 0;

    private final Integer offset;
    private final Integer limit;
    private final Sort sort;

    public PageableImpl(Integer offset, Integer limit, String sort) {
        if(limit == null)
            limit = LIMIT;
        Assert.isTrue(limit > 0 && limit <= 100, "limit must be between 1 and 100");

        if(offset == null)
            offset = OFFSET;
        Assert.isTrue(offset >= 0, "offset must be > 0");

        this.offset = offset;
        this.limit = limit;
        this.sort = orderBy(sort);
    }

    private Sort orderBy(String sortField) {
        if (!StringUtils.hasText(sortField))
            return Sort.by(Sort.DEFAULT_DIRECTION, "id");

        if (sortField.startsWith("-"))
            return Sort.by(Sort.Direction.DESC, sortField.substring(1));

        return Sort.by(Sort.Direction.ASC, sortField);
    }

    public Pageable getPageable() {
        return  PageRequest.of(offset/limit, limit, sort);
    }
}
