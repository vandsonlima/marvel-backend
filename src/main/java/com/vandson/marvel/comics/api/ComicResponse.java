package com.vandson.marvel.comics.api;

import com.vandson.marvel.character.api.CharacterFinderController;
import com.vandson.marvel.character.domain.MarvelCharacter;
import com.vandson.marvel.comics.domain.Comic;
import com.vandson.marvel.comics.domain.ComicDate;
import com.vandson.marvel.comics.domain.ComicPrice;
import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.compartilhado.domain.ObjectSummary;
import com.vandson.marvel.compartilhado.domain.SummaryList;
import com.vandson.marvel.compartilhado.domain.Url;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
@Getter
public class ComicResponse {

    private int id;
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
    private String resourceURI;

    private List<ObjectSummary> variants;
    private List<ObjectSummary> collections;
    private List<ObjectSummary> collectedIssues;

    private List<ComicDate> dates;
    private List<ComicPrice> prices;
    private Image thumbnail;
    private List<Image> images;
    private SummaryList characters;

//    private SummaryList stories;
//    private SummaryList events;
//    private SummaryList series;


    public ComicResponse() {

    }

    public ComicResponse fromModel(Comic comic){
        this.id = comic.getId().intValue();
        this.digitalId = comic.getDigitalId();
        this.title = comic.getTitle();
        this.issueNumber = comic.getIssueNumber();
        this.variantDescription = comic.getVariantDescription();
        this.description = comic.getDescription();
        this.modified = comic.getModified();
        this.isbn = comic.getIsbn();
        this.upc = comic.getUpc();
        this.diamondCode = comic.getDiamondCode();
        this.ean = comic.getEan();
        this.issn = comic.getIssn();
        this.format = comic.getFormat().getName();
        this.pageCount = comic.getPageCount();
        this.textObjects = comic.getTextObjects();
        this.urls = comic.getUrls();
        this.thumbnail = comic.getThumbnail();
        this.images = comic.getImages();
        this.dates = comic.getDates();
        this.prices = comic.getPrices();
        this.resourceURI = linkTo(methodOn(ComicsController.class).getOne((long) this.id)).toString();
        addSummaryCharacters(comic.getCharacters());
        return this;
    }

    private void addSummaryCharacters(@NotNull List<MarvelCharacter> marvelCharacters) {
        List<ObjectSummary> list = marvelCharacters.stream()
                .map(marvelCharacter -> new ObjectSummary(marvelCharacter.getResourceURI(), marvelCharacter.getName()))
                .collect(Collectors.toList());
        this.characters = new SummaryList(list, linkTo(methodOn(CharacterFinderController.class).getAll(null, null, null, null, null, null, null)).toString());
    }

}
