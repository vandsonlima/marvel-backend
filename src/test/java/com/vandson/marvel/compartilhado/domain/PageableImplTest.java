package com.vandson.marvel.compartilhado.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PageableImplTest {

    @Test
    @DisplayName("a paginação default deve ser retornada quando os valores dos parametros forem nulos")
    void validateBuildObjectWithNullValues() {
        Sort defaultSort = Sort.by(Sort.DEFAULT_DIRECTION, "id");
        PageableImpl pageable = new PageableImpl(null, null, null);

        assertNotNull(pageable);
        assertEquals(PageableImpl.LIMIT, pageable.getLimit());
        assertEquals(PageableImpl.OFFSET, pageable.getOffset());
        assertEquals(defaultSort, pageable.getSort());
    }

    @Test
    @DisplayName("a paginação deve retornar o limit e offset informados com sort default")
    void validateBuildObjectWithOnlyOffset() {
        Sort defaultSort = Sort.by(Sort.DEFAULT_DIRECTION, "id");
        PageableImpl pageable = new PageableImpl(10, 10, null);

        assertNotNull(pageable);
        assertEquals(10, pageable.getLimit());
        assertEquals(10, pageable.getOffset());
        assertEquals(defaultSort, pageable.getSort());
    }

    @Test
    @DisplayName("a paginação deve retornar o sort com o campo passado no parametro")
    void validateBuildObjectWithSortField() {
        Sort defaultSort = Sort.by(Sort.DEFAULT_DIRECTION, "name");
        PageableImpl pageable = new PageableImpl(10, 10, "name");
        assertEquals(defaultSort, pageable.getSort());
    }

    @Test
    @DisplayName("a paginação deve retornar o sort descendente")
    void validateBuildObjectWithSortFieldDesc() {
        Sort defaultSort = Sort.by(Sort.Direction.DESC, "name");
        PageableImpl pageable = new PageableImpl(10, 10, "-name");
        assertEquals(defaultSort, pageable.getSort());
    }

}