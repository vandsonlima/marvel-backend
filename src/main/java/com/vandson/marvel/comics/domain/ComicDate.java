package com.vandson.marvel.comics.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
@Embeddable
public class ComicDate {

    @NotBlank
    private String type;
    @NotNull
    private LocalDate date;

    public ComicDate(String type, LocalDate date) {
        this.type = type;
        this.date = date;
    }

    @Deprecated
    public ComicDate() {

    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }
}
