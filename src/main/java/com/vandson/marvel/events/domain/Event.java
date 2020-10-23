package com.vandson.marvel.events.domain;

import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.comics.domain.Comic;
import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.compartilhado.domain.MarvelObjects;
import com.vandson.marvel.compartilhado.domain.Url;
import com.vandson.marvel.events.api.EventController;
import com.vandson.marvel.series.domain.Series;
import com.vandson.marvel.stories.domain.Storie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
@Getter
@AllArgsConstructor
@Builder
@Entity
public class Event implements MarvelObjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<Url> urls;
    private String description;
    private String title;
    private LocalDate end;
    private LocalDate start;
    private LocalDate modified;
    private Image thumbnail;
    @ManyToMany
    private List<Comic> comics;
    @ManyToMany
    private List<Character> characters;
    @ManyToMany
    private List<Series> series;
    @ManyToMany
    private List<Storie> stories;


    @Deprecated
    public Event() {
    }

    public String getResourceURI(){
        return linkTo(methodOn(EventController.class).getOne(this.id)).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(urls, event.urls) &&
                Objects.equals(description, event.description) &&
                title.equals(event.title) &&
                Objects.equals(end, event.end) &&
                start.equals(event.start) &&
                Objects.equals(modified, event.modified) &&
                Objects.equals(thumbnail, event.thumbnail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, title, end, start, modified, thumbnail);
    }

    public void addSerie(Series newSeries) {
        if(Objects.isNull(this.series))
            this.series = new ArrayList<>();
        this.series.add(newSeries);
    }
}
