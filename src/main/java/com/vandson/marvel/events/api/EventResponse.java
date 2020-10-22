package com.vandson.marvel.events.api;

import com.vandson.marvel.character.api.CharacterFinderController;
import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.comics.api.ComicFinderController;
import com.vandson.marvel.comics.domain.Comic;
import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.compartilhado.domain.ObjectSummary;
import com.vandson.marvel.compartilhado.domain.SummaryList;
import com.vandson.marvel.compartilhado.domain.Url;
import com.vandson.marvel.events.domain.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class EventResponse {

    private Integer id;
    private List<Url> urls;
    private String resourceURI;
    private String description;
    private String title;
    private LocalDate end;
    private LocalDate start;
    private LocalDate modified;
    private Image thumbnail;
    private SummaryList comics;
    private SummaryList characters;

    public EventResponse(@NotNull Event event){
        Assert.notNull(event, "assert may be not null");
        this.id = event.getId().intValue();
        this.urls = event.getUrls();
        this.description = event.getDescription();
        this.title = event.getTitle();
        this.end = event.getEnd();
        this.modified = event.getModified();
        this.start = event.getStart();
        this.thumbnail = event.getThumbnail();
        this.resourceURI = event.getResourceURI();
        fillComics(event.getComics());
        fillCharacters(event.getCharacters());
    }

    private void fillCharacters(List<Character> characters) {
        var collect = characters.stream().map(character -> new ObjectSummary(character.getResourceURI(), character.getName())).collect(Collectors.toList());
        this.characters = new SummaryList(collect, linkTo(methodOn(CharacterFinderController.class).getAll(null, null, null, null, null, null, null)).toString());
    }

    private void fillComics(List<Comic> comics) {
        var collect = comics.stream().map(comic -> new ObjectSummary(comic.getResourceURI(), comic.getTitle())).collect(Collectors.toList());
        this.comics = new SummaryList(collect, linkTo(methodOn(ComicFinderController.class).getComics(null, null, null, null, null, null, null, null)).toString());
    }
}
