package com.vandson.marvel.validators;

import com.vandson.marvel.comics.domain.FormatComic;
import com.vandson.marvel.compartilhado.errors.MarvelErrorMessage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ComicFormatValidatorTest {

    @Test
    void validateWithNullValue() {
        List<MarvelErrorMessage> marvelErrorMessages = new ComicFormatValidator().validate(null);

        assertNotNull(marvelErrorMessages);
        assertEquals(0, marvelErrorMessages.size());
    }

    @Test
    void validateWithValidName() {
        List<MarvelErrorMessage> marvelErrorMessages = new ComicFormatValidator().validate(FormatComic.Comic.getName());

        assertNotNull(marvelErrorMessages);
        assertEquals(0, marvelErrorMessages.size());
    }


    @Test
    void validateWithInvalidName() {
        List<MarvelErrorMessage> marvelErrorMessages = new ComicFormatValidator().validate("anyValue");
        assertNotNull(marvelErrorMessages);
        assertEquals(1, marvelErrorMessages.size());
    }

}