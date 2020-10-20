package com.vandson.marvel.comics.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
@Getter
@Embeddable
public class ComicPrice {

    private String type;
    private Float price;

    public ComicPrice(String type, Float price) {
        this.type = type;
        this.price = price;
    }

    @Deprecated
    public ComicPrice() {

    }


}
