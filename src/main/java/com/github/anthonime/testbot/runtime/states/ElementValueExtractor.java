package com.github.anthonime.testbot.runtime.states;

import com.github.anthonime.testbot.runtime.elements.Element;
import com.github.anthonime.testbot.runtime.elements.HasText;

/**
 * Created by schio on 9/14/2017.
 * Defines a way to extract a value from an element
 */
public interface ElementValueExtractor {

    Object extract(Element element);

    static ElementValueExtractor byElementText() {
        return e -> to(HasText.class, e).getText();
    }

    static ElementValueExtractor byAttributeTitle() {
        return e -> e.getTitleAttribute();
    }

    static ElementValueExtractor byAttributeOrProperty(String name) {
        return e -> e.getAttributeOrProperty(name);
    }

    static <T> T to(Class<T> clazz, Object o) {
        return (T) o;
    }
}
