package com.vandson.marvel.initialize;

import com.vandson.marvel.character.domain.Image;
import com.vandson.marvel.character.domain.MarvelCharacter;
import com.vandson.marvel.character.domain.MarvelCharacterRepository;
import com.vandson.marvel.comics.domain.ComicRepository;
import com.vandson.marvel.comics.domain.MarvelComic;
import com.vandson.marvel.comics.domain.MarvelComicBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 19/10/2020
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final MarvelCharacterRepository marvelCharacterRepository;
    private final ComicRepository marvelComicsRepository;

    public ApplicationStartup(MarvelCharacterRepository marvelCharacterRepository, ComicRepository marvelComicsRepository) {
        this.marvelCharacterRepository = marvelCharacterRepository;
        this.marvelComicsRepository = marvelComicsRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        insertCharacters(marvelCharacterRepository);
        insertComics(marvelComicsRepository);
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

    private void insertComics(ComicRepository marvelComicsRepository) {

        MarvelCharacter ironMan = marvelCharacterRepository.getOne(1L);
        MarvelCharacter captain = marvelCharacterRepository.getOne(2L);
        List<MarvelComic> comics = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MarvelComic marvelComic = MarvelComicBuilder.aMarvelComic()
                    .withTitle("the iron man ed"+ i)
                    .withDescription("incredible")
                    .withModified(LocalDate.now())
                    .withDiamondCode("DIAMND-001"+i)
                    .withFormat("comic")
                    .withIssueNumber(200d)
                    .withCharacters(Arrays.asList(ironMan, captain))
                    .withThumbnail(new Image("type", "jpeg"))
                    .withTextObjects(Arrays.asList("Ironman", "captain", "civil", "war"))
                    .build();
            comics.add(marvelComic);
        }
        marvelComicsRepository.saveAll(comics);
    }
}
