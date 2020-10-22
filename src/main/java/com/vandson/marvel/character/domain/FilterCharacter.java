package com.vandson.marvel.character.domain;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
@Getter
public class FilterCharacter {
    private final String name;
    private final String nameStartsWith;
    private final LocalDateTime modifiedSince;
    private List<Long> comics;

    public FilterCharacter(String name, String nameStartsWith, LocalDateTime modifiedSince, String comics) {
        this.name = name;
        this.nameStartsWith = nameStartsWith;
        this.modifiedSince = modifiedSince;
        if(StringUtils.hasLength(comics)){
            this.comics = Stream.of(comics.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }

    }

    public static FilterCharacterBuilder builder() {
        return FilterCharacterBuilder.aFilterCharacter();
    }


    public static final class FilterCharacterBuilder {
        private String name;
        private String nameStartsWith;
        private LocalDateTime modifiedSince;
        private String comics;

        private FilterCharacterBuilder() {
        }

        public static FilterCharacterBuilder aFilterCharacter() {
            return new FilterCharacterBuilder();
        }

        public FilterCharacterBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FilterCharacterBuilder nameStartsWith(String nameStartsWith) {
            this.nameStartsWith = nameStartsWith;
            return this;
        }

        public FilterCharacterBuilder modifiedSince(LocalDateTime modifiedSince) {
            this.modifiedSince = modifiedSince;
            return this;
        }

        public FilterCharacterBuilder comics(String comics){
            this.comics = comics;
            return this;
        }

        public FilterCharacter build() {
            return new FilterCharacter(name, nameStartsWith, modifiedSince, comics);
        }
    }
}
