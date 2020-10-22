package com.vandson.marvel.compartilhado.domain;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
@Getter
public class DataContainer {
    private Integer offset;
    private Integer limit;
    private final Long total;
    private Integer count = 0;
    private final List<?> items;


    public DataContainer(Integer offset,
                         Integer limit,
                         Long total,
                         List<?> collect) {

        this.offset = offset;
        if(offset == null) {
            this.offset = PageableImpl.OFFSET;
        }
        this.limit = limit;
        if(limit == null)
            this.limit = PageableImpl.LIMIT;

        this.total = total;

        if(Objects.nonNull(collect))
            this.count = collect.size();

        this.items = collect;
    }
}
