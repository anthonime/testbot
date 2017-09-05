package com.github.anthonime.testbot.runtime.elements.impl;

import com.github.anthonime.testbot.definitions.elements.ContainerElementDefinition;
import com.github.anthonime.testbot.definitions.elements.ElementDefinition;
import com.github.anthonime.testbot.definitions.elements.Type;
import com.github.anthonime.testbot.runtime.elements.Element;
import com.github.anthonime.testbot.runtime.elements.ElementFactory;
import com.github.anthonime.testbot.runtime.elements.webdriver.ClickableElementWebDriverImpl;
import com.github.anthonime.testbot.runtime.elements.webdriver.TextElementWebDriverImpl;
import com.github.anthonime.testbot.runtime.elements.webdriver.TextInputElementWebDriverImpl;
import com.github.anthonime.testbot.runtime.elements.webdriver.WebElementProviderFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by schio on 9/12/2017.
 */
public class ElementFactoryImpl implements ElementFactory {

    private WebElementProviderFactory factory;

    public ElementFactoryImpl(WebElementProviderFactory factory) {
        this.factory = factory;
    }

    @Override
    public Element create(ElementDefinition definition) {
        //checkbox, radiobuttons, file
        //select
        //list (multiple list)
        //tables (and row selection?)
        //texts
        //all text inputs
        Type type = definition.getType();
        switch (type) {
            case CONTAINER:
                List<Element> children = ((ContainerElementDefinition) definition).getChildrenElementDefinitions()
                        .stream().map(c -> create(c)).collect(Collectors.toList());
                return new ContainerElementImpl(factory.create(definition), children);
            case BUTTON:
                return new ClickableElementWebDriverImpl(factory.create(definition));
            case INPUT:
                return new TextInputElementWebDriverImpl(factory.create(definition));
            case TEXT:
                return new TextElementWebDriverImpl(factory.create(definition));
            default:
                throw new IllegalStateException("not handled type definition " + type);
        }
    }
}
