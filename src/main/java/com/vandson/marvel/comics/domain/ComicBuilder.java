package com.vandson.marvel.comics.domain;

import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.character.domain.MarvelCharacter;
import com.vandson.marvel.compartilhado.domain.Url;

import javax.persistence.Embedded;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
public final class ComicBuilder {
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
    private FormatComic format;
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
    private List<MarvelCharacter> characters;
    private FormatType formatType;
    @Embedded
    private List<ComicDate> dates;
    private List<ComicPrice> prices;

    private ComicBuilder() {
    }

    public static ComicBuilder aMarvelComic() {
        return new ComicBuilder();
    }

    public ComicBuilder withDigitalId(int digitalId) {
        this.digitalId = digitalId;
        return this;
    }

    public ComicBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ComicBuilder withIssueNumber(Double issueNumber) {
        this.issueNumber = issueNumber;
        return this;
    }

    public ComicBuilder withVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
        return this;
    }

    public ComicBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ComicBuilder withModified(LocalDate modified) {
        this.modified = modified;
        return this;
    }

    public ComicBuilder withIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public ComicBuilder withUpc(String upc) {
        this.upc = upc;
        return this;
    }

    public ComicBuilder withDiamondCode(String diamondCode) {
        this.diamondCode = diamondCode;
        return this;
    }

    public ComicBuilder withEan(String ean) {
        this.ean = ean;
        return this;
    }

    public ComicBuilder withIssn(String issn) {
        this.issn = issn;
        return this;
    }

    public ComicBuilder withFormat(FormatComic format) {
        this.format = format;
        return this;
    }

    public ComicBuilder withPageCount(int pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public ComicBuilder withTextObjects(List<String> textObjects) {
        this.textObjects = textObjects;
        return this;
    }

    public ComicBuilder withUrls(List<Url> urls) {
        this.urls = urls;
        return this;
    }

    public ComicBuilder withThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public ComicBuilder withImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public ComicBuilder withCharacters(List<MarvelCharacter> characters){
        this.characters = characters;
        return this;
    }

    public ComicBuilder withFormatType(FormatType formatType){
        this.formatType = formatType;
        return this;
    }

    public ComicBuilder withDates(List<ComicDate> dates){
        this.dates = dates;
        return this;
    }

    public ComicBuilder withPrices(List<ComicPrice> prices){
        this.prices = prices;
        return this;
    }

    public Comic build() {

        return new Comic(digitalId, title, issueNumber, variantDescription, description, modified, isbn, upc, diamondCode, ean, issn, format, pageCount, textObjects, urls, formatType, dates, prices, thumbnail, images, characters);
    }
}
