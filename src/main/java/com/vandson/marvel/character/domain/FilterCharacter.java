package com.vandson.marvel.character.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
@AllArgsConstructor
@Builder
@Getter
public class FilterCharacter {
    private String name;
    private String nameStartsWith;
    private LocalDateTime modifiedSince;



}
