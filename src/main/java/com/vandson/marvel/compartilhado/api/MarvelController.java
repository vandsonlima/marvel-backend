package com.vandson.marvel.compartilhado.api;

import com.vandson.marvel.compartilhado.domain.DataContainer;
import com.vandson.marvel.compartilhado.domain.DataWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 21/10/2020
 */
public class MarvelController {

    public ResponseEntity<DataWrapper> getResponseEntityDataWrapper(Integer limit, Integer offset, List<?> items, long count) {
        var dataContainer = new DataContainer(offset, limit, count, items);
        return ResponseEntity.ok(DataWrapper.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(dataContainer)
                .build());
    }

    public ResponseEntity<DataWrapper> getResponseEntityDataWrappperObject(Object object){
        var dataContainer = new DataContainer(null, null, 1L, Collections.singletonList(object));
        return ResponseEntity.ok(DataWrapper.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(dataContainer)
                .build());
    }
}
