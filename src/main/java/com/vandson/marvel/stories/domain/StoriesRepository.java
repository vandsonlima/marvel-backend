package com.vandson.marvel.stories.domain;

import com.vandson.marvel.character.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoriesRepository extends JpaRepository<Storie, Long> {


    List<Storie> findAllByCharactersIs(Character character);
}
