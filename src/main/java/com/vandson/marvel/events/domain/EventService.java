package com.vandson.marvel.events.domain;

import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.compartilhado.errors.MarvelException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> getAllByFilters(Character character, String name, String nameStartsWith, LocalDateTime modifiedSince, String comics, Integer limit, Integer offset, String sortField) throws MarvelException;

    Long countByFilters(Character character, String name, String nameStartsWith, LocalDateTime modifiedSince, String comics) throws MarvelException;

    Optional<Event> getById(Long eventId);
}
