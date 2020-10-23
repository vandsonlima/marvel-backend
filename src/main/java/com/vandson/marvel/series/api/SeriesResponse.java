package com.vandson.marvel.series.api;

import com.vandson.marvel.compartilhado.api.SummaryListFactory;
import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.compartilhado.domain.SummaryList;
import com.vandson.marvel.compartilhado.domain.Url;
import com.vandson.marvel.series.domain.Series;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

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
    private final SummaryList comics;
    private final SummaryList stories;
    private final SummaryList events;
    private final SummaryList characters;

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
        this.characters = new SummaryListFactory().getCharacters(series.getCharacters());
        this.events = new SummaryListFactory().getEvents(series.getEvents());
        this.comics = new SummaryListFactory().getComics(series.getComics());
        this.stories = new SummaryListFactory().getStories(series.getStories());
    }

}
