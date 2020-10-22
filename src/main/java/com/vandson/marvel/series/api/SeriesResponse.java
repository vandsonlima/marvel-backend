package com.vandson.marvel.series.api;

import com.vandson.marvel.character.api.CharacterFinderController;
import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.comics.api.ComicFinderController;
import com.vandson.marvel.comics.domain.Comic;
import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.compartilhado.domain.ObjectSummary;
import com.vandson.marvel.compartilhado.domain.SummaryList;
import com.vandson.marvel.compartilhado.domain.Url;
import com.vandson.marvel.events.api.EventController;
import com.vandson.marvel.events.domain.Event;
import com.vandson.marvel.series.domain.Series;
import com.vandson.marvel.stories.api.StoriesController;
import com.vandson.marvel.stories.domain.Stories;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
@Getter
public class SeriesResponse {

    private final Integer id;
    private final String description;
    private final String title;
    private final String resourceURI;
    private final List<Url> urls;
    private final Integer startYear;
    private final Integer endYear;
    private final String rating;
    private final LocalDate modified;
    private final Image thumbnail;
    private SummaryList comics;
    private SummaryList stories;
    private SummaryList events;
    private SummaryList characters;

    public SeriesResponse(@NotNull Series series) {
        Assert.notNull(series, "series must be not null");
        this.description = series.getDescription();
        this.endYear = series.getEndYear();
        this.id= Math.toIntExact(series.getId());
        this.modified = series.getModified();
        this.resourceURI = series.getResourceURI();
        this.startYear = series.getStartYear();
        this.thumbnail = series.getThumbnail();
        this.rating = series.getRating();
        this.urls = series.getUrls();
        this.title = series.getTitle();

        fillComics(series.getComics());
        fillStories(series.getStories());
        fillEvents(series.getEvents());
        fillCharacters(series.getCharacters());
    }

    private void fillCharacters(List<Character> characters) {
        this.characters = new SummaryList(
                characters.stream().map(character -> new ObjectSummary(character.getResourceURI(), character.getName())).collect(Collectors.toList()),
                linkTo(methodOn(CharacterFinderController.class).getAll(null, null, null, null, null, null, null)).toString()
        );
    }

    private void fillEvents(List<Event> events) {
        this.events = new SummaryList(
                events.stream().map(event -> new ObjectSummary(event.getResourceURI(), event.getTitle())).collect(Collectors.toList()),
                linkTo(methodOn(EventController.class).getAll(null, null, null, null, null, null, null, null)).toString()
        );
    }

    private void fillStories(List<Stories> stories) {
        this.stories = new SummaryList(
                stories.stream().map(storie -> new ObjectSummary(storie.getResourceURI(), storie.getTitle())).collect(Collectors.toList()),
                linkTo(methodOn(StoriesController.class).getAll(null)).toString()
        );
    }

    private void fillComics(List<Comic> comics) {
        this.comics = new SummaryList(
                comics.stream().map(comic -> new ObjectSummary(comic.getResourceURI(), comic.getTitle())).collect(Collectors.toList()),
                linkTo(methodOn(ComicFinderController.class).getComics(null, null, null, null, null, null, null, null)).toString()
        );
    }
}
