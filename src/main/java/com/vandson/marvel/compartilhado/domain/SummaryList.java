package com.vandson.marvel.compartilhado.domain;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
public class SummaryList {
    public static final int MAX_SIZE_ITEMS = 20;
    private final int available;
    private final int returned;
    private String collectionURI;
    private final List<ObjectSummary> items;

    public SummaryList(@NotNull List<ObjectSummary> items, @NotNull String resourceURI) {
        Assert.notNull(items, "Os itens não podem ser nulos");
        Assert.hasLength(resourceURI,"resourceURI não pode ser nula");

        this.available = items.size();
        this.returned = Math.min(items.size(), MAX_SIZE_ITEMS);
        if(!CollectionUtils.isEmpty(items))
            this.collectionURI = resourceURI;
        this.items = items.stream().limit(returned).collect(Collectors.toList());
    }

    public int getAvailable() {
        return available;
    }

    public int getReturned() {
        return returned;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public List<ObjectSummary> getItems() {
        return items;
    }
}
