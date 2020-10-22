package com.vandson.marvel.comics.domain;

import com.vandson.marvel.character.domain.MarvelCharacter;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 21/10/2020
 */
@Getter
@Builder
public class FilterComics {
    private final MarvelCharacter character;
    private final String title;
    private final String titleStartsWith;
    private final FormatComic formatComic;
    private final FormatType formatType;

    private FilterComics(MarvelCharacter character, String title, String titleStartsWith, FormatComic formatComic, FormatType formatType) {
        Assert.notNull(character, "Character is required");
        this.character = character;
        this.title = title;
        this.titleStartsWith = titleStartsWith;
        this.formatComic = formatComic;
        this.formatType = formatType;
    }
}
