package com.vandson.marvel.character.domain;

import org.springframework.data.domain.Pageable;

import java.util.List;


public interface MarvelCharacterService {

    public List<MarvelCharacter> getAllByFilter(FilterCharacter filterCharacter, Pageable pageable);

    public long countByFilter(FilterCharacter filterCharacter);
}
