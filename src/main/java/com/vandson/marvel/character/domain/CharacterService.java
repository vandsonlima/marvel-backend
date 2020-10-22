package com.vandson.marvel.character.domain;

import java.time.LocalDateTime;
import java.util.List;


public interface CharacterService {

    List<Character> getAllByFilter(String name, String nameStartsWith, LocalDateTime modifiedSince, Integer offset, Integer limit, String sortField, String comics);

    long countByFilter(String name, String nameStartsWith, LocalDateTime modifiedSince, String comics);
}
