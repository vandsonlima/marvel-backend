package com.vandson.marvel.comics.domain;

import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.character.domain.MarvelCharacter;
import com.vandson.marvel.compartilhado.domain.Url;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
@Getter
@Entity
public class Comic {

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
    @Enumerated
    private FormatComic format;
    private int pageCount;
    @ElementCollection
    private List<String> textObjects;
    @ElementCollection
    private List<Url> urls;
    @Enumerated
    private FormatType formatType;

    @ElementCollection
    private List<ComicDate> dates;
    @ElementCollection
    private List<ComicPrice> prices;
    @Embedded
    private Image thumbnail;
    @ElementCollection
    private List<Image> images;
    @ManyToMany
    private List<MarvelCharacter> characters;

    @Deprecated
    public Comic() {

    }

    public Comic(int digitalId, String title, Double issueNumber, String variantDescription, String description, LocalDate modified, String isbn, String upc,
                 String diamondCode, String ean, String issn, FormatComic format, int pageCount, List<String> textObjects, List<Url> urls, FormatType formatType,
                 List<ComicDate> dates, List<ComicPrice> prices, Image thumbnail, List<Image> images, List<MarvelCharacter> characters) {

        Assert.notNull(formatType, "format type invalid");

        this.formatType = formatType;
        this.digitalId = digitalId;
        if(!FormatComic.Digital_comic.equals(format))
            this.digitalId = 0;

        this.title = title;
        this.issueNumber = issueNumber;
        if(FormatType.Collection.equals(formatType))
            this.issueNumber = 0D;
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
        this.dates = dates;
        this.prices = prices;
    }

}
