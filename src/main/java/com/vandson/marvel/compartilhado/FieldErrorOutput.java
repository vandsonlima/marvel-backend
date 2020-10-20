package com.vandson.marvel.compartilhado;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 19/10/2020
 **/
public class FieldErrorOutput {
    private final String field;
    private final String message;

    public FieldErrorOutput(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
