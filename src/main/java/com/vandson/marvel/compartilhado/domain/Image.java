package com.vandson.marvel.compartilhado.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 19/10/2020
 */
@Embeddable
public class Image {

    private  String path;
    private  String extension;

    public Image(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    @Deprecated
    public Image() {

    }

    public String getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }
}
