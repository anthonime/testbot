package com.github.anthonime.testbot.definitions.applications;

import com.google.common.base.Preconditions;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by schio on 9/16/2017.
 */
public class EnvironmentDefinition {

    private String identifier;
    private URI uri;

    public EnvironmentDefinition(String identifier, String host, int port) {
        this.identifier = identifier;
        try {
            this.uri = new URI("http", null, host, port, null, null, null);
        } catch (URISyntaxException e) {
            throw new IllegalStateException("cannot build environmenet for " + identifier + " " + host + " " + port, e);
        }
    }

    public EnvironmentDefinition(String identifier, URI uri) {
        this.identifier = identifier;
        this.uri = uri;
    }

    public EnvironmentDefinition(String identifier, String uri) {
        this.identifier = identifier;
        this.uri = URI.create(uri);
        Preconditions.checkArgument(this.uri.isAbsolute(), "environment URI must be absolute ! " + uri);
    }


    public String getIdentifier() {
        return identifier;
    }

    public URI getUri() {
        return uri;
    }
}
