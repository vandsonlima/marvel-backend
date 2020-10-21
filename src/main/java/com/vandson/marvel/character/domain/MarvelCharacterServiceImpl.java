package com.vandson.marvel.character.domain;

import com.vandson.marvel.compartilhado.domain.PageableImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<MarvelCharacter> getAllByFilter(String name, String nameStartsWith, LocalDateTime modifiedSince, Integer offset, Integer limit, String sortField){
        var filterCharacter = FilterCharacter.builder()
                .name(name)
                .nameStartsWith(nameStartsWith)
                .modifiedSince(modifiedSince)
                .build();

        var characterSpecification = CharactersSpecification.filter(filterCharacter);
        return marvelCharacterRepository.findAll(characterSpecification, PageableImpl.of(offset, limit, sortField)).getContent();
    }

    @Override
    public long countByFilter(String name, String nameStartsWith, LocalDateTime modifiedSince) {
        var filterCharacter = FilterCharacter.builder()
                .name(name)
                .nameStartsWith(nameStartsWith)
                .modifiedSince(modifiedSince)
                .build();
        return marvelCharacterRepository.count(CharactersSpecification.filter(filterCharacter));
    }
}
