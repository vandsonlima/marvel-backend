package com.vandson.marvel.character.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 21/10/2020
 */
@Service
public class MarvelCharacterServiceImpl implements MarvelCharacterService {

    private final MarvelCharacterRepository marvelCharacterRepository;

    public MarvelCharacterServiceImpl(MarvelCharacterRepository marvelCharacterRepository) {
        this.marvelCharacterRepository = marvelCharacterRepository;
    }

    public List<MarvelCharacter> getAllByFilter(FilterCharacter filterCharacter, Pageable pageable){
        var characterSpecification = CharactersSpecification.filter(filterCharacter);
        return marvelCharacterRepository.findAll(characterSpecification, pageable).getContent();
    }

    @Override
    public long countByFilter(FilterCharacter filterCharacter) {
        return marvelCharacterRepository.count(CharactersSpecification.filter(filterCharacter));
    }
}
