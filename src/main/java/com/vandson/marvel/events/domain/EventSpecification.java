package com.vandson.marvel.events.domain;

import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.character.domain.FilterCharacter;
import com.vandson.marvel.comics.domain.Comic;
import com.vandson.marvel.events.api.FilterEvent;
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
public class EventSpecification {

    public static Specification<Event> filter(FilterEvent filter){

        return new Specification<Event>() {
            @Override
            public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(Objects.nonNull(filter.getCharacter())) {
                    Join<Event, Character> characterJoin = root.join("characters");
                    predicates.add(criteriaBuilder.equal(characterJoin.get("id"), filter.getCharacter().getId()));
                }

                if(StringUtils.hasText(filter.getName()))
                    predicates.add(criteriaBuilder.equal(root.get("name"), filter.getName()));
                if(StringUtils.hasText(filter.getNameStartsWith()))
                    predicates.add(criteriaBuilder.like(root.get("name"), filter.getNameStartsWith()+"%"));
                if(Objects.nonNull(filter.getModifiedSince()))
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("modified"), filter.getModifiedSince()));
                if(!CollectionUtils.isEmpty(filter.getComics())){
                    Join<Comic, Character> comics = root.join("comics");
                    Expression<Long> parentExpression = comics.get("id");
                    predicates.add(parentExpression.in(filter.getComics()));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };

    }
}
