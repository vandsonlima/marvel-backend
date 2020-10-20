package com.vandson.marvel.compartilhado;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
public class SummaryObject {
    public static final int MAX_SIZE_ITEMS = 20;
    private final int available;
    private final int returned;
    private final String collectionURI;
    private final List<EventSummary> items;

    public SummaryObject(List<EventSummary> items, String resourceURI) {
        this.available = items.size();
        this.returned = Math.min(items.size(), MAX_SIZE_ITEMS);
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

    public List<EventSummary> getItems() {
        return items;
    }
}
