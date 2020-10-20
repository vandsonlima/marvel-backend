package com.vandson.marvel.comics.api;

import com.vandson.marvel.character.api.CharacterResponse;
import com.vandson.marvel.character.api.ImageResponse;
import com.vandson.marvel.character.api.UrlResponse;
import com.vandson.marvel.character.domain.Image;
import com.vandson.marvel.character.domain.MarvelCharacter;
import com.vandson.marvel.comics.domain.MarvelComic;
import com.vandson.marvel.compartilhado.SummaryObject;
import org.springframework.util.CollectionUtils;

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
    private List<UrlResponse> urls;
    private String resourceURI;
//    private List<SeriesResponse> series;
//    @ManyToMany
//    private List<ComicResponse> variants;
//    @ManyToMany
//    private List<ComicResponse> collections;

//    private List<ComicResponse> collectedIssues;
//    private List<ComicDateResponse> dates;
//    private List<ComicPriceResponse> prices;
    private Image thumbnail;
    private List<ImageResponse> images;

    private SummaryObject characters;
//    private List<StoriesResponse> stories;
//    private List<EventsResponse> events;

    public ComicResponse() {

    }

    public ComicResponse fromModel(MarvelComic marvelComic){
        this.id = marvelComic.getId().intValue();
        this.digitalId = marvelComic.getDigitalId();
        this.title = marvelComic.getTitle();
        this.issueNumber = marvelComic.getIssueNumber();
        this.variantDescription = marvelComic.getVariantDescription();
        this.description = marvelComic.getDescription();
        this.modified = marvelComic.getModified();
        this.isbn = marvelComic.getIsbn();
        this.upc = marvelComic.getUpc();
        this.diamondCode = marvelComic.getDiamondCode();
        this.ean = marvelComic.getEan();
        this.issn = marvelComic.getIssn();
        this.format = marvelComic.getFormat();
        this.pageCount = marvelComic.getPageCount();
        this.textObjects = marvelComic.getTextObjects();
        this.urls = marvelComic.getUrls().stream().map(url -> new UrlResponse(url.getType(), url.getUrl())).collect(Collectors.toList());
        this.thumbnail = marvelComic.getThumbnail();
        this.images = marvelComic.getImages().stream().map(image -> new ImageResponse(image.getPath(), image.getExtension())).collect(Collectors.toList());
        this.resourceURI = linkTo(methodOn(ComicsController.class).getOne((long) this.id)).toString();
        addSummaryCharacters(marvelComic.getCharacters());
        return this;
    }

    private void addSummaryCharacters(@NotNull List<MarvelCharacter> marvelCharacters) {
        this.characters = null;
        List<CharacterResponse> characterResponses = marvelCharacters.stream().map(CharacterResponse::new).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(marvelCharacters)){
            this.characters = new SummaryObject(characterResponses, resourceURI);
        }


    }

    public int getId() {
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

    public List<UrlResponse> getUrls() {
        return urls;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public List<ImageResponse> getImages() {
        return images;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public SummaryObject getCharacters() {
        return characters;
    }
}
