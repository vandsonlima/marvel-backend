package com.vandson.marvel.stories.domain;

import com.vandson.marvel.compartilhado.api.SummaryListFactory;
import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.compartilhado.domain.ObjectSummary;
import com.vandson.marvel.compartilhado.domain.SummaryList;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
@Getter
public class StorieResponse {

    private final Long id;
    private final String resourceURI;
    private final String title;
    private final String description;
    private final StoryType storyType;
    private final LocalDate modified;
    private final Image thumbnail;
    private final SummaryList comics;
    private final SummaryList events;
    private final SummaryList characters;
    private final SummaryList series;

    private ObjectSummary originalIssue;

    public StorieResponse(Storie storie) {
        this.id = storie.getId();
        this.resourceURI = storie.getResourceURI();
        this.title = storie.getTitle();
        this.description = storie.getDescription();
        this.storyType = storie.getStoryType();
        this.modified = storie.getModified();
        this.thumbnail = storie.getThumbnail();
        this.characters = new SummaryListFactory().getCharacters(storie.getCharacters());
        this.events = new SummaryListFactory().getEvents(storie.getEvents());
        this.comics = new SummaryListFactory().getComics(storie.getComics());
        this.series = new SummaryListFactory().getSeries(storie.getSeries());
        if(Objects.nonNull(storie.getOriginalIssue()))
            this.originalIssue = new ObjectSummary(storie.getOriginalIssue().getResourceURI(), storie.getOriginalIssue().getTitle());

    }
}
