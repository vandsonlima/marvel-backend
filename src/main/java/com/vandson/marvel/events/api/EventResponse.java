package com.vandson.marvel.events.api;

import com.vandson.marvel.compartilhado.api.SummaryListFactory;
import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.compartilhado.domain.SummaryList;
import com.vandson.marvel.compartilhado.domain.Url;
import com.vandson.marvel.events.domain.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

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
    private SummaryList series;
    private SummaryList stories;

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
        this.comics = new SummaryListFactory().getComics(event.getComics());
        this.characters = new SummaryListFactory().getCharacters(event.getCharacters());
        this.series = new SummaryListFactory().getSeries(event.getSeries());
        this.stories = new SummaryListFactory().getStories(event.getStories());
    }
}
