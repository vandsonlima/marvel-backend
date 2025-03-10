package com.vandson.marvel.comics.domain;

import java.util.List;

public interface ComicsService {

    List<Comic> getAllByFilter(FilterComics filterComics, Integer offset, Integer limit, String sortField);
    long countByFilter(FilterComics filterComics);
}
