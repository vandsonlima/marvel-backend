package com.vandson.marvel.initialize;

import com.vandson.marvel.character.domain.MarvelCharacter;
import com.vandson.marvel.character.domain.MarvelCharacterRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 19/10/2020
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final MarvelCharacterRepository marvelCharacterRepository;

    public ApplicationStartup(MarvelCharacterRepository marvelCharacterRepository) {
        this.marvelCharacterRepository = marvelCharacterRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        insertCharacters(marvelCharacterRepository);
    }

    private void insertCharacters(MarvelCharacterRepository marvelCharacterRepository) {
        MarvelCharacter ironMan = new MarvelCharacter("Iron Man", "the strongest", LocalDateTime.now());
        ironMan.addUrl("index", "http://ironman.com");
        ironMan.addUrl("contact", "http://ironman.com/contact");

        MarvelCharacter captain = new MarvelCharacter("Captain America", "the leader", LocalDateTime.now());
        captain.addUrl("index", "http://captain.com");

        MarvelCharacter hulk = new MarvelCharacter("Hulk", "Hulk smash", LocalDateTime.now());
        hulk.addThumbnail("http:images.io/hjulk", "jpeg");

        MarvelCharacter blackWidow = new MarvelCharacter("black widow", "the smallest", LocalDateTime.now());
        blackWidow.addThumbnail("imageBucket.io/blackwidow", "gif");

        marvelCharacterRepository.saveAll(Arrays.asList(ironMan, captain, hulk, blackWidow));
    }
}
