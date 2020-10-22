package com.vandson.marvel.series.domain;

import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.compartilhado.domain.PageableImpl;
import com.vandson.marvel.compartilhado.errors.MarvelException;
import com.vandson.marvel.series.api.SeriesFilterValidator;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
@Service
public class SeriesServiceImpl implements SerieService{

    private final SeriesRepository seriesRepository;

    public SeriesServiceImpl(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @Override
    public List<Series> findAllByFilters(Character character, String title, String titleStartsWith, Integer startYear, LocalDate modifiedSince, String comics, String events, String stories, Integer limit, Integer offset, String sortField) throws MarvelException {
        var marvelErrorMessages = new SeriesFilterValidator().validateParameters(limit, sortField);
        if(!CollectionUtils.isEmpty(marvelErrorMessages))
            throw new MarvelException(marvelErrorMessages);

        var filterSeries = FilterSeries.builder()
                .character(character)
                .title(title)
                .titleStartsWith(titleStartsWith)
                .comics(comics)
                .events(events)
                .modifiedSince(modifiedSince)
                .startYear(startYear)
                .stories(stories)
                .build();

        return seriesRepository.findAll(SeriesSpecification.filter(filterSeries), new PageableImpl(offset, limit, sortField).getPageable()).getContent();

    }

    @Override
    public Long countByFilters(Character character, String title, String titleStartsWith, Integer startYear, LocalDate modifiedSince, String comics, String events, String stories) {
        var filterSeries = FilterSeries.builder()
                .character(character)
                .title(title)
                .titleStartsWith(titleStartsWith)
                .comics(comics)
                .events(events)
                .modifiedSince(modifiedSince)
                .startYear(startYear)
                .stories(stories)
                .build();

        return seriesRepository.count(SeriesSpecification.filter(filterSeries));
    }

    @Override
    public Optional<Series> getOne(Long seriesId) {
        return seriesRepository.findById(seriesId);
    }
}
