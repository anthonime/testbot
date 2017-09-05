package com.github.anthonime.testbot.runtime.elements;

/**
 * Created by schio on 9/10/2017.
 */
public interface ClickableElement<T> extends Element {

    /**
     * Perform a mouse click.
     *
     * @return
     */
    T click();

}
