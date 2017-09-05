package com.github.anthonime.testbot.runtime.elements.impl;

import com.github.anthonime.testbot.runtime.elements.ContainerElement;
import com.github.anthonime.testbot.runtime.elements.Element;
import com.github.anthonime.testbot.runtime.elements.webdriver.WebDriverElement;
import com.github.anthonime.testbot.runtime.elements.webdriver.WebElementProvider;

import java.util.List;

/**
 * Created by schio on 9/10/2017.
 */
public class ContainerElementImpl extends WebDriverElement implements ContainerElement {
    private List<Element> children;

    public ContainerElementImpl(WebElementProvider provider, List<Element> children) {
        super(provider);
        this.children = children;
    }

    @Override
    public List<Element> getDescendants() {
        return children;
    }
}
