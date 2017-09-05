package com.github.anthonime.testbot.runtime.elements.webdriver;

import com.github.anthonime.testbot.definitions.elements.ElementDefinition;

/**
 * Created by schio on 9/11/2017.
 */
public interface WebElementProviderFactory {

    WebElementProvider create(ElementDefinition definition);
}
