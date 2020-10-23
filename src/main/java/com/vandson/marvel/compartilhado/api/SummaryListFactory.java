package com.vandson.marvel.compartilhado.api;

import com.vandson.marvel.character.api.CharacterFinderController;
import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.comics.api.ComicFinderController;
import com.vandson.marvel.comics.domain.Comic;
import com.vandson.marvel.compartilhado.domain.ObjectSummary;
import com.vandson.marvel.compartilhado.domain.SummaryList;
import com.vandson.marvel.events.api.EventController;
import com.vandson.marvel.events.domain.Event;
import com.vandson.marvel.series.api.SeriesController;
import com.vandson.marvel.series.domain.Series;
import com.vandson.marvel.stories.api.StoriesController;
import com.vandson.marvel.stories.domain.Storie;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
public class SummaryListFactory {

    public SummaryList getCharacters(List<Character> characters){
        return new SummaryList(
                characters.stream().map(character -> new ObjectSummary(character.getResourceURI(), character.getName())).collect(Collectors.toList()),
                linkTo(methodOn(CharacterFinderController.class).getAll(null, null, null, null, null, null, null)).toString()
        );
    }

    public SummaryList getEvents(List<Event> events){
        return  new SummaryList(
                events.stream().map(event -> new ObjectSummary(event.getResourceURI(), event.getTitle())).collect(Collectors.toList()),
                linkTo(methodOn(EventController.class).getAll(null, null, null, null, null, null, null, null)).toString()
        );
    }

    public SummaryList getComics(List<Comic> comics){
        return new SummaryList(
                comics.stream().map(comic -> new ObjectSummary(comic.getResourceURI(), comic.getTitle())).collect(Collectors.toList()),
                linkTo(methodOn(ComicFinderController.class).getComics(null, null, null, null, null, null, null, null)).toString()
        );
    }

    public SummaryList getStories(List<Storie> stories) {
        return new SummaryList(
                stories.stream().map(storie -> new ObjectSummary(storie.getResourceURI(), storie.getTitle())).collect(Collectors.toList()),
                linkTo(methodOn(StoriesController.class).getAll(null)).toString()
        );
    }

    public SummaryList getSeries(List<Series> series){
        return new SummaryList(
                series.stream().map(serie -> new ObjectSummary(serie.getResourceURI(), serie.getTitle())).collect(Collectors.toList()),
                linkTo(methodOn(SeriesController.class).getAll (null, null, null, null, null, null, null, null, null, null, null)).toString()
        );
    }
}
