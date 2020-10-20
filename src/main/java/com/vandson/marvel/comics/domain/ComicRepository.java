package com.vandson.marvel.comics.domain;

import com.vandson.marvel.character.domain.MarvelCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long> {

    public List<Comic> findAllByCharactersIs(MarvelCharacter marvelCharacter);
}
