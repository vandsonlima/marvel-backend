package com.vandson.marvel.character.api;

import com.sun.istack.NotNull;
import com.vandson.marvel.character.domain.MarvelCharacter;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 19/10/2020
 */
public class CharacterResponse extends RepresentationModel<CharacterResponse> {

    private final int id;
    private final String name;
    private final String description;
    private final LocalDateTime modified;
    private final List<UrlResponse> urls;
    private ImageResponse thumbnail;


    public CharacterResponse(@NotNull @Valid MarvelCharacter marvelCharacter) {
        this.name = marvelCharacter.getName();
        this.id =  marvelCharacter.getId().intValue();
        this.description = marvelCharacter.getDescription();
        this.modified = marvelCharacter.getModified();
        this.urls = marvelCharacter.getUrls().stream()
                .map(url -> new UrlResponse(url.getType(), url.getUrl()))
                .collect(Collectors.toList());
        if(Objects.nonNull(marvelCharacter.getThumbnail()))
            this.thumbnail = new ImageResponse(marvelCharacter.getThumbnail().getPath(), marvelCharacter.getThumbnail().getExtension());

        this.add(linkTo(methodOn(CharacterController.class).get((long) this.id)).withSelfRel());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public List<UrlResponse> getUrls() {
        return urls;
    }

    public ImageResponse getThumbnail() {
        return thumbnail;
    }
}
