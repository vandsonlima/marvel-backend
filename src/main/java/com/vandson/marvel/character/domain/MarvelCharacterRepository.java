package com.vandson.marvel.character.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MarvelCharacterRepository extends JpaRepository<MarvelCharacter, Long>, JpaSpecificationExecutor<MarvelCharacter> {





}
