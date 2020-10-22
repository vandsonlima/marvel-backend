package com.vandson.marvel.comics.domain;

import com.vandson.marvel.compartilhado.domain.PageableImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 21/10/2020
 */
@Component
public class ComicsServiceImpl implements ComicsService {

    private final ComicRepository comicRepository;

    public ComicsServiceImpl(ComicRepository comicRepository) {
        this.comicRepository = comicRepository;
    }

    public List<Comic> getAllByFilter(FilterComics filterComics, Integer offset, Integer limit, String sortField){
        var comicsSpecification = ComicsSpecification.filter(filterComics);
        return comicRepository.findAll(comicsSpecification, new PageableImpl(offset, limit, sortField).getPageable()).getContent();
    }

    public long countByFilter(FilterComics filterComics){
        var comicsSpecification = ComicsSpecification.filter(filterComics);
        return comicRepository.count(comicsSpecification);
    }
}
