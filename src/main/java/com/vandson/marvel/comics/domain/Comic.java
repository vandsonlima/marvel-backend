package com.vandson.marvel.comics.domain;

import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.comics.api.ComicsController;
import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.compartilhado.domain.MarvelObjects;
import com.vandson.marvel.compartilhado.domain.Url;
import com.vandson.marvel.series.domain.Series;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
@Getter
@Entity
public class Comic implements MarvelObjects {

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
    @Enumerated(EnumType.STRING)
    private FormatComic format;
    private int pageCount;
    @ElementCollection
    private List<String> textObjects;
    @ElementCollection
    private List<Url> urls;
    @Enumerated(EnumType.STRING)
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
    private List<Character> characters;

    @ManyToMany(mappedBy = "comics")
    private List<Series> series;

    @Deprecated
    public Comic() {

    }

    public Comic(int digitalId, String title, Double issueNumber, String variantDescription, String description, LocalDate modified, String isbn, String upc,
                 String diamondCode, String ean, String issn, FormatComic format, int pageCount, List<String> textObjects, List<Url> urls, FormatType formatType,
                 List<ComicDate> dates, List<ComicPrice> prices, Image thumbnail, List<Image> images, List<Character> characters) {

        Assert.notNull(formatType, "format type invalid");

        this.formatType = formatType;
        this.digitalId = digitalId;
        if(!FormatComic.Digital_comic.equals(format))
            this.digitalId = 0;

        this.title = title;
        this.issueNumber = issueNumber;
        if(FormatType.collection.equals(formatType))
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

    public String getResourceURI(){
        return linkTo(methodOn(ComicsController.class).getOne(this.id)).toString();
    }

}
