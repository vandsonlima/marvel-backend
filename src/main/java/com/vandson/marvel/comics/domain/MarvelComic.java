package com.vandson.marvel.comics.domain;

import com.vandson.marvel.character.domain.Image;
import com.vandson.marvel.character.domain.MarvelCharacter;
import com.vandson.marvel.character.domain.Url;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
@Entity
public class MarvelComic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @ElementCollection
    private List<String> textObjects;
    @ElementCollection
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
    @Embedded
    private Image thumbnail;
    @ElementCollection
    private List<Image> images;
    @ManyToMany
    private List<MarvelCharacter> characters;
//    private List<StoriesResponse> stories;
//    private List<EventsResponse> events;


    @Deprecated
    public MarvelComic() {

    }

    public MarvelComic(int digitalId, String title, Double issueNumber, String variantDescription, String description, LocalDate modified, String isbn, String upc, String diamondCode, String ean, String issn, String format, int pageCount, List<String> textObjects, List<Url> urls, Image thumbnail, List<Image> images, List<MarvelCharacter> characters) {
        this.digitalId = digitalId;
        this.title = title;
        this.issueNumber = issueNumber;
        this.variantDescription = variantDescription;
        this.description = description;
        this.modified = modified;
        this.isbn = isbn;
        this.upc = upc;
        this.diamondCode = diamondCode;
        this.ean = ean;
        this.issn = issn;
        this.format = format;
        this.pageCount = pageCount;
        this.textObjects = textObjects;
        this.urls = urls;
        this.thumbnail = thumbnail;
        this.images = images;
        this.characters = characters;
    }

    public Long getId() {
        return id;
    }

    public int getDigitalId() {
        return digitalId;
    }

    public String getTitle() {
        return title;
    }

    public Double getIssueNumber() {
        return issueNumber;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getModified() {
        return modified;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getUpc() {
        return upc;
    }

    public String getDiamondCode() {
        return diamondCode;
    }

    public String getEan() {
        return ean;
    }

    public String getIssn() {
        return issn;
    }

    public String getFormat() {
        return format;
    }

    public int getPageCount() {
        return pageCount;
    }

    public List<String> getTextObjects() {
        return textObjects;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<MarvelCharacter> getCharacters() {
        return characters;
    }
}
