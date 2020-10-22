package com.vandson.marvel.comics.domain;

import java.util.Arrays;

public enum FormatComic {
    Comic("comic"),
    Magazine("magazine"),
    trade_paperback("trade Paperback"),
    hard_cover("hard cover"),
    Digest("digest"),
    Graphic_novel("graphic novel"),
    Digital_comic("digital comic"),
    Infinite_comic("infinite comic");

    private final String name;

    FormatComic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static FormatComic getByName(String name){
        return Arrays.stream(FormatComic.values())
                .filter(formatComic -> formatComic.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
