package com.vandson.marvel.comics.domain;

import com.vandson.marvel.character.domain.Character;
import lombok.Getter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 21/10/2020
 */
@Getter
public class FilterComics {
    private final Character character;
    private final String title;
    private final String titleStartsWith;
    private FormatComic formatComic;
    private FormatType formatType;

    private FilterComics(Character character, String title, String titleStartsWith, String formatComic, String formatType) {
        Assert.notNull(character, "Character is required");
        this.character = character;
        this.title = title;
        this.titleStartsWith = titleStartsWith;
        if(StringUtils.hasLength(formatComic))
            this.formatComic = FormatComic.getByName(formatComic);
        if(StringUtils.hasLength(formatType))
            this.formatType = FormatType.valueOf(formatType);
    }

    public static FilterComicsBuilder builder(){
        return FilterComicsBuilder.aFilterComics();
    }

    public static final class FilterComicsBuilder {
        private Character character;
        private String title;
        private String titleStartsWith;
        private String formatComic;
        private String formatType;

        private FilterComicsBuilder() {
        }

        private static FilterComicsBuilder aFilterComics() {
            return new FilterComicsBuilder();
        }

        public FilterComicsBuilder withCharacter(Character character) {
            this.character = character;
            return this;
        }

        public FilterComicsBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public FilterComicsBuilder withTitleStartsWith(String titleStartsWith) {
            this.titleStartsWith = titleStartsWith;
            return this;
        }

        public FilterComicsBuilder withFormatComic(String formatComic) {
            this.formatComic = formatComic;
            return this;
        }

        public FilterComicsBuilder withFormatType(String formatType) {
            this.formatType = formatType;
            return this;
        }

        public FilterComics build() {
            FilterComics filterComics = new FilterComics(character, title, titleStartsWith, formatComic, formatType);
            return filterComics;
        }
    }
}
