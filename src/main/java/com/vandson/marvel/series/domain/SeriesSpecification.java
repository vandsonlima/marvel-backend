package com.vandson.marvel.series.domain;

import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.comics.domain.Comic;
import com.vandson.marvel.events.domain.Event;
import com.vandson.marvel.stories.domain.Storie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
public class SeriesSpecification {

    public static Specification<Series> filter(FilterSeries filter){
        return (Specification<Series>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(Objects.nonNull(filter.getCharacter())) {
                Join<Series, Character> characterJoin = root.join("characters");
                predicates.add(criteriaBuilder.equal(characterJoin.get("id"), filter.getCharacter().getId()));
            }

            if(StringUtils.hasText(filter.getTitle()))
                predicates.add(criteriaBuilder.equal(root.get("title"), filter.getTitle()));

            if(StringUtils.hasText(filter.getTitleStartsWith()))
                predicates.add(criteriaBuilder.like(root.get("title"), filter.getTitleStartsWith()+"%"));

            if(Objects.nonNull(filter.getModifiedSince()))
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("modified"), filter.getModifiedSince()));

            if(Objects.nonNull(filter.getStartYear()))
                predicates.add(criteriaBuilder.equal(root.get("startYear"), filter.getStartYear()));

            if(!CollectionUtils.isEmpty(filter.getComics())){
                Join<Comic, Series> comics = root.join("comics");
                Expression<Long> parentExpression = comics.get("id");
                predicates.add(parentExpression.in(filter.getComics()));
            }

            if(!CollectionUtils.isEmpty(filter.getEvents())){
                Join<Event, Series> comics = root.join("events");
                Expression<Long> parentExpression = comics.get("id");
                predicates.add(parentExpression.in(filter.getComics()));
            }

            if(!CollectionUtils.isEmpty(filter.getStories())){
                Join<Storie, Character> comics = root.join("stories");
                Expression<Long> parentExpression = comics.get("id");
                predicates.add(parentExpression.in(filter.getComics()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
