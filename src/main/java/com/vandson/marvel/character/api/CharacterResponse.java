package com.vandson.marvel.character.api;

import com.sun.istack.NotNull;
import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.compartilhado.api.SummaryListFactory;
import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.compartilhado.domain.SummaryList;
import com.vandson.marvel.compartilhado.domain.Url;
import lombok.Getter;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

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
    private final SummaryList comics;
    private final SummaryList events;
    private final SummaryList series;
    private final SummaryList stories;

    public CharacterResponse(@NotNull @Valid Character character) {
        this.name = character.getName();
        this.id =  character.getId().intValue();
        this.description = character.getDescription();
        this.modified = character.getModified();
        this.urls = character.getUrls();
        this.thumbnail = character.getThumbnail();
        this.resourceURI = character.getResourceURI();
        this.comics = new SummaryListFactory().getComics(character.getComics());
        this.events = new SummaryListFactory().getEvents(character.getEvents());
        this.series = new SummaryListFactory().getSeries(character.getSeries());
        this.stories = new SummaryListFactory().getStories(character.getStories());
    }
}
