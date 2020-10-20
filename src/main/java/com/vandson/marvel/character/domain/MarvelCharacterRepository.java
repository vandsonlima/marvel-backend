package com.vandson.marvel.character.domain;

import com.vandson.marvel.character.domain.MarvelCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarvelCharacterRepository extends JpaRepository<MarvelCharacter, Long> {
}
