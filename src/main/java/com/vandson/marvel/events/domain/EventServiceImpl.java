package com.vandson.marvel.events.domain;

import com.vandson.marvel.character.domain.Character;
import com.vandson.marvel.compartilhado.domain.PageableImpl;
import com.vandson.marvel.compartilhado.errors.MarvelException;
import com.vandson.marvel.events.api.FilterEvent;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 22/10/2020
 */
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllByFilters(Character character, String name, String nameStartsWith, LocalDateTime modifiedSince, String comics, Integer limit, Integer offset, String sortField) throws MarvelException {
        var marvelErrorMessages = new EventFilterValidator().validateParameters(limit, sortField);
        if(!CollectionUtils.isEmpty(marvelErrorMessages))
            throw new MarvelException(marvelErrorMessages);

        var filterEvent = FilterEvent.builder()
                .character(character)
                .name(name)
                .nameStartsWith(nameStartsWith)
                .modifiedSince(modifiedSince)
                .comics(comics)
                .build();

        return eventRepository.findAll(EventSpecification.filter(filterEvent), new PageableImpl(offset, limit, sortField).getPageable()).getContent();
    }

    @Override
    public Long countByFilters(Character character, String name, String nameStartsWith, LocalDateTime modifiedSince, String comics) throws MarvelException {
        var filterEvent = FilterEvent.builder()
                .character(character)
                .name(name)
                .nameStartsWith(nameStartsWith)
                .modifiedSince(modifiedSince)
                .comics(comics)
                .build();

        return eventRepository.count(EventSpecification.filter(filterEvent));
    }

    @Override
    public Optional<Event> getById(Long eventId) {
        return eventRepository.findById(eventId);
    }
}
