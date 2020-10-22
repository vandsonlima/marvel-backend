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
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<Character> getAllByFilter(String name, String nameStartsWith, LocalDateTime modifiedSince, Integer offset, Integer limit, String sortField, String comics){
        var filterCharacter = FilterCharacter.builder()
                .name(name)
                .nameStartsWith(nameStartsWith)
                .modifiedSince(modifiedSince)
                .comics(comics)
                .build();
        

        var characterSpecification = CharactersSpecification.filter(filterCharacter);
        return characterRepository.findAll(characterSpecification, new PageableImpl(offset, limit, sortField).getPageable()).getContent();
    }

    @Override
    public long countByFilter(String name, String nameStartsWith, LocalDateTime modifiedSince, String comics) {
        var filterCharacter = FilterCharacter.builder()
                .name(name)
                .nameStartsWith(nameStartsWith)
                .modifiedSince(modifiedSince)
                .build();
        return characterRepository.count(CharactersSpecification.filter(filterCharacter));
    }
}
