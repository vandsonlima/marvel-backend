package com.vandson.marvel.character.domain;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface MarvelCharacterService {

    List<MarvelCharacter> getAllByFilter(String name, String nameStartsWith, LocalDateTime modifiedSince, Integer offset, Integer limit, String sortField);

    long countByFilter(String name, String nameStartsWith, LocalDateTime modifiedSince);
}
