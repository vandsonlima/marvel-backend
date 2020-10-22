package com.vandson.marvel.series.domain;

import com.vandson.marvel.character.domain.Character;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
@Getter
public class FilterSeries {

    private final Character character;
    private final String title;
    private final String titleStartsWith;
    private final Integer startYear;
    private final LocalDate modifiedSince;
    private List<Long> comics;
    private List<Long> events;
    private List<Long> stories;

    public FilterSeries(Character character, String title, String titleStartsWith, Integer startYear, LocalDate modifiedSince, String comics, String events, String stories) {
        this.character = character;
        this.title = title;
        this.titleStartsWith = titleStartsWith;
        this.startYear = startYear;
        this.modifiedSince = modifiedSince;
        if(StringUtils.hasLength(comics)){
            this.comics = Stream.of(comics.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }

        if(StringUtils.hasLength(events)){
            this.events = Stream.of(events.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }

        if(StringUtils.hasLength(stories)){
            this.stories = Stream.of(stories.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }


    }

    public static FilterSeriesBuilder builder(){
        return FilterSeriesBuilder.aFilterSeries();
    }

    public static final class FilterSeriesBuilder {
        private Character character;
        private String title;
        private String titleStartsWith;
        private Integer startYear;
        private LocalDate modifiedSince;
        private String comics;
        private String events;
        private String stories;

        private FilterSeriesBuilder() {
        }

        public static FilterSeriesBuilder aFilterSeries() {
            return new FilterSeriesBuilder();
        }

        public FilterSeriesBuilder character(Character character) {
            this.character = character;
            return this;
        }

        public FilterSeriesBuilder title(String title) {
            this.title = title;
            return this;
        }

        public FilterSeriesBuilder titleStartsWith(String titleStartsWith) {
            this.titleStartsWith = titleStartsWith;
            return this;
        }

        public FilterSeriesBuilder startYear(Integer startYear) {
            this.startYear = startYear;
            return this;
        }

        public FilterSeriesBuilder modifiedSince(LocalDate modifiedSince) {
            this.modifiedSince = modifiedSince;
            return this;
        }

        public FilterSeriesBuilder comics(String comics) {
            this.comics = comics;
            return this;
        }

        public FilterSeriesBuilder events(String events) {
            this.events = events;
            return this;
        }

        public FilterSeriesBuilder stories(String stories) {
            this.stories = stories;
            return this;
        }

        public FilterSeries build() {
            return new FilterSeries(character, title, titleStartsWith, startYear, modifiedSince, comics, events,stories);
        }
    }
}


