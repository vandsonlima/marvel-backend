package com.vandson.marvel.character.api;

import com.sun.istack.NotNull;
import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.comics.api.ComicFinderController;
import com.vandson.marvel.comics.domain.Comic;
import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.compartilhado.domain.ObjectSummary;
import com.vandson.marvel.compartilhado.domain.SummaryList;
import com.vandson.marvel.compartilhado.domain.Url;
import com.vandson.marvel.events.api.EventController;
import com.vandson.marvel.events.domain.Event;
import lombok.Getter;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 19/10/2020
 */
@Getter
public class CharacterResponse {

    private final int id;
    private final String name;
    private final String description;
    private final LocalDateTime modified;
    private final List<Url> urls;
    private final Image thumbnail;
    private final String resourceURI;
    private SummaryList comics;
    private SummaryList events;
    //private SummaryObject series;
    //private SummaryObject stories;

    public CharacterResponse(@NotNull @Valid Character character) {
        this.name = character.getName();
        this.id =  character.getId().intValue();
        this.description = character.getDescription();
        this.modified = character.getModified();
        this.urls = character.getUrls();
        this.thumbnail = character.getThumbnail();
        this.resourceURI = character.getResourceURI();
        fillComics(character.getComics());
        fillEvents(character.getEvents());
    }

    private void fillComics(List<Comic> comics) {
        this.comics = new SummaryList( comics
                .stream()
                .map(comic -> new ObjectSummary(comic.getResourceURI(), comic.getTitle()))
                .collect(Collectors.toList()), linkTo(methodOn(ComicFinderController.class).getComics((long) this.id, null, null, null, null, null, null, null)).toString());
    }

    private void fillEvents(List<Event> events) {
        this.events = new SummaryList( events
                .stream()
                .map(event -> new ObjectSummary(event.getResourceURI(), event.getTitle()))
                .collect(Collectors.toList()), linkTo(methodOn(EventController.class).getAll((long) this.id,null,null,null,null,null,null,null)).toString()
        );
    }

}
