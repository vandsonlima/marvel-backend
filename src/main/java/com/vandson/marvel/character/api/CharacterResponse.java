package com.vandson.marvel.character.api;

import com.sun.istack.NotNull;
import com.vandson.marvel.character.domain.MarvelCharacter;
import com.vandson.marvel.comics.api.ComicFinderController;
import com.vandson.marvel.comics.domain.Comic;
import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.compartilhado.domain.ObjectSummary;
import com.vandson.marvel.compartilhado.domain.SummaryList;
import com.vandson.marvel.compartilhado.domain.Url;
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
    //private SummaryObject series;
    //private SummaryObject stories;
    //private SummaryObject events;

    public CharacterResponse(@NotNull @Valid MarvelCharacter marvelCharacter) {
        this.name = marvelCharacter.getName();
        this.id =  marvelCharacter.getId().intValue();
        this.description = marvelCharacter.getDescription();
        this.modified = marvelCharacter.getModified();
        this.urls = marvelCharacter.getUrls();
        this.thumbnail = marvelCharacter.getThumbnail();
        this.resourceURI = marvelCharacter.getResourceURI();
        fillComics(marvelCharacter.getComics());
    }

    private void fillComics(List<Comic> comics) {
        this.comics = new SummaryList( comics
                .stream()
                .map(comic -> new ObjectSummary(comic.getResourceURI(), comic.getTitle()))
                .collect(Collectors.toList()), linkTo(methodOn(ComicFinderController.class).getComics((long) this.id, null, null, null, null, null, null, null)).toString());
    }

    public SummaryList getComics() {
        return comics;
    }
}
