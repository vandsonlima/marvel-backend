package com.vandson.marvel.compartilhado.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
@Builder
@Getter
public class DataWrapper {
    private final int code;
    private final String status;
    private final String etag;
    private final String copyright;
    private final String attributionText;
    private final String attributionHTML;
    private final DataContainer data;

}
