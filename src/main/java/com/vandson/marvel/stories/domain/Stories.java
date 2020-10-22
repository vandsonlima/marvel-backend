package com.vandson.marvel.stories.domain;

import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.comics.domain.Comic;
import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.compartilhado.domain.MarvelObjects;
import com.vandson.marvel.events.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Stories implements MarvelObjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private StoryType storyType;
    private LocalDate modified;
    private Image thumbnail;
    @ManyToMany
    private List<Comic> comics;
    @ManyToMany
    private List<Event> events;
    @ManyToMany
    private List<Character> characters;
    @ManyToOne
    private Comic originalIssue;

    @Override
    public String getResourceURI() {
        return null;
    }

}
