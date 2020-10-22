package com.vandson.marvel.comics.domain;

import com.vandson.marvel.character.domain.FilterCharacter;
import com.vandson.marvel.character.domain.MarvelCharacter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 21/10/2020
 */
public class ComicsSpecification {
    public static Specification<Comic> filter(FilterComics filter){

        return new Specification<Comic>() {
            @Override
            public Predicate toPredicate(Root<Comic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if(Objects.nonNull(filter.getCharacter())) {
                    Join<Comic, Character> characterJoin = root.join("characters");
                    predicates.add(criteriaBuilder.equal(characterJoin.get("id"), filter.getCharacter().getId()));
                }

                if(StringUtils.hasText(filter.getTitle()))
                    predicates.add(criteriaBuilder.equal(root.get("title"), filter.getTitle()));

                if(StringUtils.hasText(filter.getTitleStartsWith()))
                    predicates.add(criteriaBuilder.like(root.get("title"), filter.getTitleStartsWith()+"%"));
                if(Objects.nonNull(filter.getFormatComic()))
                    predicates.add(criteriaBuilder.equal(root.get("formatComic"), filter.getFormatComic()));
                if(Objects.nonNull(filter.getFormatType()))
                    predicates.add(criteriaBuilder.equal(root.get("formatType"), filter.getFormatType()));

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
