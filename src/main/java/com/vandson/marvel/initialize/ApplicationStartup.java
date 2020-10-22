package com.vandson.marvel.initialize;

import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.character.domain.CharacterRepository;
import com.vandson.marvel.comics.domain.*;
import com.vandson.marvel.compartilhado.domain.Image;
import com.vandson.marvel.compartilhado.domain.Url;
import com.vandson.marvel.events.domain.Event;
import com.vandson.marvel.events.domain.EventRepository;
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

    private final CharacterRepository characterRepository;
    private final ComicRepository marvelComicsRepository;
    private final EventRepository eventRepository;

    public ApplicationStartup(CharacterRepository characterRepository, ComicRepository marvelComicsRepository, EventRepository eventRepository) {
        this.characterRepository = characterRepository;
        this.marvelComicsRepository = marvelComicsRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        insertCharacters(characterRepository);
        insertComics(marvelComicsRepository);
        insertEvents(eventRepository);
    }

    private void insertCharacters(CharacterRepository characterRepository) {
        Character ironMan = new Character("Iron Man", "the strongest", LocalDateTime.now());
        ironMan.addUrl("index", "http://ironman.com");
        ironMan.addUrl("contact", "http://ironman.com/contact");

        Character captain = new Character("Captain America", "the leader", LocalDateTime.now());
        captain.addUrl("index", "http://captain.com");

        Character hulk = new Character("Hulk", "Hulk smash", LocalDateTime.now());
        hulk.addThumbnail("http:images.io/hjulk", "jpeg");

        Character blackWidow = new Character("Black widow", "the smallest", LocalDateTime.now());
        blackWidow.addThumbnail("imageBucket.io/blackwidow", "gif");

        characterRepository.saveAll(Arrays.asList(ironMan, captain, hulk, blackWidow));
    }

    private void insertComics(ComicRepository marvelComicsRepository) {

        Character ironMan = characterRepository.getOne(1L);
        Character captain = characterRepository.getOne(2L);
        Character blackWidow = characterRepository.getOne(4L);
        List<Comic> comics = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Comic comic = ComicBuilder.aMarvelComic()
                    .withTitle("the iron man ed"+ i)
                    .withDescription("incredible")
                    .withModified(LocalDate.now())
                    .withDiamondCode("DIAMND-001"+i)
                    .withFormat(FormatComic.Comic)
                    .withIssueNumber(200d)
                    .withFormatType(FormatType.comic)
                    .withCharacters(Arrays.asList(ironMan, captain))
                    .withThumbnail(new Image("type", "jpeg"))
                    .withTextObjects(Arrays.asList("Ironman", "captain", "civil", "war"))
                    .build();
            comics.add(comic);
        }

        for (int i = 0; i < 5; i++) {
            Comic comic = ComicBuilder.aMarvelComic()
                    .withTitle("the iron man ed")
                    .withDescription("incredible")
                    .withModified(LocalDate.now())
                    .withDiamondCode("DIAMND-00")
                    .withFormat(FormatComic.Digital_comic)
                    .withIssueNumber(200d)
                    .withCharacters(Arrays.asList(ironMan, captain))
                    .withThumbnail(new Image("type", "jpeg"))
                    .withTextObjects(Arrays.asList("Ironman", "captain", "civil", "war"))
                    .withFormatType(FormatType.comic)
                    .build();

            comics.add(comic);
        }


        Comic comic2 = ComicBuilder.aMarvelComic()
                .withTitle("the iron man ed")
                .withDescription("incredible")
                .withModified(LocalDate.now())
                .withDiamondCode("DIAMND-00")
                .withFormat(FormatComic.Comic)
                .withIssueNumber(200d)
                .withCharacters(Arrays.asList(ironMan, captain))
                .withThumbnail(new Image("type", "jpeg"))
                .withTextObjects(Arrays.asList("Ironman", "captain", "civil", "war"))
                .withFormatType(FormatType.collection)
                .withDates(Arrays.asList(new ComicDate("type", LocalDate.of(1980,2, 12)),
                        new ComicDate("type", LocalDate.of(1990,2, 20)),
                        new ComicDate("type",LocalDate.now())))
                .build();

        comics.add(comic2);


        Comic comic3 = ComicBuilder.aMarvelComic()
                .withTitle("black widow one")
                .withDescription("incrediblesss")
                .withModified(LocalDate.now())
                .withDiamondCode("DIAMND-10")
                .withFormat(FormatComic.Digital_comic)
                .withIssueNumber(200d)
                .withCharacters(Arrays.asList(blackWidow))
                .withThumbnail(new Image("type", "jpeg"))
                .withTextObjects(Arrays.asList("Ironman", "captain", "civil", "war"))
                .withFormatType(FormatType.comic)
                .withDates(Arrays.asList(new ComicDate("type", LocalDate.of(1980,2, 12)),
                        new ComicDate("type", LocalDate.of(1990,2, 20)),
                        new ComicDate("type",LocalDate.now())))
                .build();

        comics.add(comic3);

        marvelComicsRepository.saveAll(comics);
    }

    private void insertEvents(EventRepository eventRepository) {
        Character ironMan = characterRepository.getOne(1L);
        Character captain = characterRepository.getOne(2L);
        Character blackWidow = characterRepository.getOne(4L);
        List<Event> events = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Comic randomComic = marvelComicsRepository.getOne((long) (2+i)); // random number between 12 and 1
            Comic randomComic2 = marvelComicsRepository.getOne((long) (3+i)); // random number between 12 and 1

            Event newEvent = Event.builder()
                    .characters(List.of(ironMan, captain, blackWidow))
                    .comics(List.of(randomComic, randomComic2))
                    .start(LocalDate.of(2020,2,15+ i))
                    .end(LocalDate.of(2020,2,17+ i))
                    .thumbnail(new Image("image.com/id", "jpg"))
                    .title("Marvel Comics 1"+i)
                    .urls(List.of(new Url("url.com", "url.com"), new Url("url.com", "url.com")))
                    .description("the greatest comicCon")
                    .build();

            events.add(newEvent);
        }
        eventRepository.saveAll(events);

    }
}
