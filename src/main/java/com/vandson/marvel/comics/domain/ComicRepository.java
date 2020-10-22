package com.vandson.marvel.comics.domain;

import com.vandson.marvel.character.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long>, JpaSpecificationExecutor<Comic> {

    public List<Comic> findAllByCharactersIs(Character character);
}
