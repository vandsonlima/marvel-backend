package com.vandson.marvel.series.domain;

import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.compartilhado.errors.MarvelException;
import com.vandson.marvel.series.api.SeriesResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SerieService {
    List<Series> findAllByFilters(Character character, String title, String titleStartsWith, Integer startYear, LocalDate modifiedSince, String comics, String events, String stories, Integer limit, Integer offset, String sortField) throws MarvelException;

    Long countByFilters(Character character, String title, String titleStartsWith, Integer startYear, LocalDate modifiedSince, String comics, String events, String stories);

    Optional<Series> getOne(Long seriesId);
}
