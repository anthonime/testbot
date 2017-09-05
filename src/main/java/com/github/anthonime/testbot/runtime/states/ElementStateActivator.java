package com.github.anthonime.testbot.runtime.states;

import com.github.anthonime.testbot.definitions.data.ObjectInstance;
import com.github.anthonime.testbot.runtime.elements.Element;

/**
 * Created by schio on 9/16/2017.
 */
public interface ElementStateActivator {

    boolean isActive(Element e, ObjectInstance data);
}
