package com.vandson.marvel.character.domain;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
public class CharactersSpecification {
    public static Specification<MarvelCharacter> filter(FilterCharacter filter){

        return new Specification<MarvelCharacter>() {
            @Override
            public Predicate toPredicate(Root<MarvelCharacter> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if(StringUtils.hasText(filter.getName())) {
                    predicates.add(criteriaBuilder.equal(root.get("name"), filter.getName()));
                }
                if(StringUtils.hasText(filter.getNameStartsWith())) {
                    predicates.add(criteriaBuilder.like(root.get("name"), filter.getNameStartsWith()+"%"));
                }
                if(Objects.nonNull(filter.getModifiedSince()))
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("modified"), filter.getModifiedSince()));

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };

    }

}
