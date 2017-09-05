package com.github.anthonime.testbot.runtime.elements;

import java.util.List;

/**
 * Created by schio on 9/10/2017.
 */
public interface ContainerElement extends Element {
    List<Element> getDescendants();
}
