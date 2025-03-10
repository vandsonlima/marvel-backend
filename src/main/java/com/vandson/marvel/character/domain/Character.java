package com.vandson.marvel.character.domain;

import com.sun.istack.NotNull;
import com.vandson.marvel.character.api.CharacterController;
import com.vandson.marvel.comics.domain.Comic;
import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.compartilhado.domain.MarvelObjects;
import com.vandson.marvel.compartilhado.domain.Url;
import com.vandson.marvel.events.domain.Event;
import com.vandson.marvel.series.domain.Series;
import com.vandson.marvel.stories.domain.Storie;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 19/10/2020
 */
@Getter
@Entity
public class Character implements MarvelObjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private LocalDateTime modified;
    @ElementCollection
    private final List<Url> urls = new ArrayList<>();
    @Embedded
    private Image thumbnail;

    @ManyToMany(mappedBy = "characters")
    private List<Comic> comics;

    @ManyToMany(mappedBy = "characters")
    private List<Event> events;

    @ManyToMany(mappedBy = "characters")
    private List<Series> series;

    @ManyToMany(mappedBy = "characters")
    private List<Storie> stories;

    @Deprecated
    public Character() {
    }

    public Character(@NotBlank String name, @NotBlank String description, @NotNull LocalDateTime modified) {
        this.name = name;
        this.description = description;
        this.modified = modified;
    }

    public void addUrl(@NotNull  String type, @NotNull String url){
        urls.add(new Url(type, url));
    }

    public void addThumbnail(String path, String extension){
        thumbnail = new Image(path, extension);
    }

    public String getResourceURI() {
        return  linkTo(methodOn(CharacterController.class).get(this.id)).toString();
    }

    public List<Storie> getStories() {
        return stories;
    }

    public void setStories(List<Storie> stories) {
        this.stories = stories;
    }
}
