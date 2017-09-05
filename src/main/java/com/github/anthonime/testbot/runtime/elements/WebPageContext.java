package com.github.anthonime.testbot.runtime.elements;

import java.net.URI;

/**
 * Created by schio on 9/10/2017.
 */
public interface WebPageContext {

    /**
     * Return true if the given element has focus, false otherwise.
     *
     * @param element
     * @return
     */
    boolean hasFocus(Element element);

    /**
     * Return the current URL or null if the current URL is invalid or empty.
     *
     * @return
     */
    URI getCurrentURI();

    /**
     * Set the current URL
     *
     * @return
     */
    void setCurrentURI(URI uri);
}
