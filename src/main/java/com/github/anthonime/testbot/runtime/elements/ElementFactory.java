package com.github.anthonime.testbot.runtime.elements;

import com.github.anthonime.testbot.definitions.elements.ElementDefinition;

/**
 * Created by schio on 9/12/2017.
 */
public interface ElementFactory {
    Element create(ElementDefinition definition);
}
