package com.vandson.marvel.compartilhado.domain;

/**
 * @author Vandson (vandson.vslima@gmail.com)
 * @since 20/10/2020
 */
public class ObjectSummary {
    private String resourceURI;
    private String name;

    public ObjectSummary(String resourceURI, String name) {
        this.resourceURI = resourceURI;
        this.name = name;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public String getName() {
        return name;
    }
}
