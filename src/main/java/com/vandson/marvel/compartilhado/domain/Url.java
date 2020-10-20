package com.vandson.marvel.compartilhado.domain;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 19/10/2020
 */
@Embeddable
public class Url {

    @NotBlank
    private String type;
    @NotBlank
    private String url;

    @Deprecated
    public Url() {
    }

    public Url(@NotBlank String type, @NotBlank String url) {
        this.type = type;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
