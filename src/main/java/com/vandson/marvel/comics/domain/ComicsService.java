package com.vandson.marvel.comics.domain;

import java.util.List;

public interface ComicsService {

    public List<Comic> getAllByFilter(FilterComics filterComics, Integer offset, Integer limit, String sortField);
    public long countByFilter(FilterComics filterComics);
}
