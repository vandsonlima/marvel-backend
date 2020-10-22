package com.vandson.marvel.events.api;

import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.character.domain.FilterCharacter;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
@Getter
public class FilterEvent {

    private final Character character;
    private final String name;
    private final String nameStartsWith;
    private final LocalDateTime modifiedSince;
    private List<Long> comics;

    public FilterEvent(Character character, String name, String nameStartsWith, LocalDateTime modifiedSince, String comics) {
        this.character = character;
        this.name = name;
        this.nameStartsWith = nameStartsWith;
        this.modifiedSince = modifiedSince;
        if(StringUtils.hasLength(comics)){
            this.comics = Stream.of(comics.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }
    }

    public static FilterEventBuilder builder() {
        return FilterEventBuilder.aFilterEvent();
    }

    public static final class FilterEventBuilder {
        private Character character;
        private String name;
        private String nameStartsWith;
        private LocalDateTime modifiedSince;
        private String comics;

        private FilterEventBuilder() {
        }

        public static FilterEventBuilder aFilterEvent() {
            return new FilterEventBuilder();
        }

        public FilterEventBuilder character(Character character) {
            this.character = character;
            return this;
        }

        public FilterEventBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FilterEventBuilder nameStartsWith(String nameStartsWith) {
            this.nameStartsWith = nameStartsWith;
            return this;
        }

        public FilterEventBuilder modifiedSince(LocalDateTime modifiedSince) {
            this.modifiedSince = modifiedSince;
            return this;
        }

        public FilterEventBuilder comics(String comics) {
            this.comics = comics;
            return this;
        }

        public FilterEvent build() {
            return new FilterEvent(character, name, nameStartsWith, modifiedSince, comics);
        }
    }
}

