package com.vandson.marvel.comics.domain;

public enum FormatComic {
    Comic("Comic"),
    Magazine("Magazine"),
    trade_paperback("Trade Paperback"),
    hard_cover("Hard Cover"),
    Digest("Digest"),
    Graphic_novel("Graphic Novel"),
    Digital_comic("Digital Comic"),
    Infinite_comic("Infinite Comic");

    private final String name;

    FormatComic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
