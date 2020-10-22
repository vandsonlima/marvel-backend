package com.vandson.marvel.events.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Urls {
    @JsonProperty("url")
    private String url;
    @JsonProperty("type")
    private String type;
}
