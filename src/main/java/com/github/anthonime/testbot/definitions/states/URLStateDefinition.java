package com.github.anthonime.testbot.definitions.states;

/**
 * Created by schio on 9/16/2017.
 */
public class URLStateDefinition {

    private String url;

    public URLStateDefinition(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        URLStateDefinition that = (URLStateDefinition) o;

        return url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }
}
