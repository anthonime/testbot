package com.github.anthonime.testbot.runtime.elements;

/**
 * Created by schio on 9/10/2017.
 */
public interface Focusable<T> {

    boolean hasFocus();

    T focus();
}
