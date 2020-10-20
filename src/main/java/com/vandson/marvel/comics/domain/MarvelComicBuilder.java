package com.vandson.marvel.comics.domain;

import com.vandson.marvel.character.domain.Image;
import com.vandson.marvel.character.domain.Url;

import java.time.LocalDate;
import java.util.List;

/**
* @author Vandson (vandson.vslima@gmail.com)
* @since 20/10/2020
*/
public final class MarvelComicBuilder {
    private int digitalId;
    private String title;
    private Double issueNumber;
    private String variantDescription;
    private String description;
    private LocalDate modified;
    private String isbn;
    private String upc;
    private String diamondCode;
    private String ean;
    private String issn;
    private String format;
    private int pageCount;
    private List<String> textObjects;
    private List<Url> urls;
    //    private List<SeriesResponse> series;
//    @ManyToMany
//    private List<ComicResponse> variants;
//    @ManyToMany
//    private List<ComicResponse> collections;
//    @ManyToMany
//    private List<ComicResponse> collectedIssues;
//    private List<ComicDateResponse> dates;
//    private List<ComicPriceResponse> prices;
    private Image thumbnail;
    private List<Image> images;

    private MarvelComicBuilder() {
    }

    public static MarvelComicBuilder aMarvelComic() {
        return new MarvelComicBuilder();
    }

    public MarvelComicBuilder withDigitalId(int digitalId) {
        this.digitalId = digitalId;
        return this;
    }

    public MarvelComicBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public MarvelComicBuilder withIssueNumber(Double issueNumber) {
        this.issueNumber = issueNumber;
        return this;
    }

    public MarvelComicBuilder withVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
        return this;
    }

    public MarvelComicBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public MarvelComicBuilder withModified(LocalDate modified) {
        this.modified = modified;
        return this;
    }

    public MarvelComicBuilder withIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public MarvelComicBuilder withUpc(String upc) {
        this.upc = upc;
        return this;
    }

    public MarvelComicBuilder withDiamondCode(String diamondCode) {
        this.diamondCode = diamondCode;
        return this;
    }

    public MarvelComicBuilder withEan(String ean) {
        this.ean = ean;
        return this;
    }

    public MarvelComicBuilder withIssn(String issn) {
        this.issn = issn;
        return this;
    }

    public MarvelComicBuilder withFormat(String format) {
        this.format = format;
        return this;
    }

    public MarvelComicBuilder withPageCount(int pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public MarvelComicBuilder withTextObjects(List<String> textObjects) {
        this.textObjects = textObjects;
        return this;
    }

    public MarvelComicBuilder withUrls(List<Url> urls) {
        this.urls = urls;
        return this;
    }

    public MarvelComicBuilder withThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public MarvelComicBuilder withImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public MarvelComic build() {
        return new MarvelComic(digitalId, title, issueNumber, variantDescription, description, modified, isbn, upc, diamondCode, ean, issn, format, pageCount, textObjects, urls, thumbnail, images);
    }
}
