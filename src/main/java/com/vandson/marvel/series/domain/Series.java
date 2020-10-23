package com.vandson.marvel.series.domain;

import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.comics.domain.Comic;
import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.compartilhado.domain.MarvelObjects;
import com.vandson.marvel.compartilhado.domain.Url;
import com.vandson.marvel.events.domain.Event;
import com.vandson.marvel.stories.domain.Storie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
@Getter
@AllArgsConstructor
@Builder
@Entity
public class Series implements MarvelObjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String title;
    @ElementCollection
    private List<Url> urls;
    private Integer startYear;
    private Integer endYear;
    private String rating;
    private LocalDate modified;
    @Embedded
    private Image thumbnail;
    @ManyToMany
    private List<Comic> comics;
    @ManyToMany
    private List<Storie> stories;
    @ManyToMany
    private List<Event> events;
    @ManyToMany
    private List<Character> characters;

    @Deprecated
    public Series() {

    }

    @Override
    public String getResourceURI() {
        return null;
    }
}
