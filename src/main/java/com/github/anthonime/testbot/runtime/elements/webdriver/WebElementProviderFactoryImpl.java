package com.github.anthonime.testbot.runtime.elements.webdriver;

import com.github.anthonime.testbot.definitions.elements.*;
import com.github.anthonime.testbot.runtime.elements.Element;
import com.github.anthonime.testbot.w3c.html.InputType;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by schio on 9/11/2017.
 */
public class WebElementProviderFactoryImpl implements WebElementProviderFactory {

    private WebPageContextImpl provider;

    private ByFactory byFactory = new ByFactory();

    public WebElementProviderFactoryImpl(WebPageContextImpl provider) {
        this.provider = provider;
    }

    @Override
    public WebElementProvider create(ElementDefinition definition) {
        switch (definition.getType()) {
            case CONTAINER:
                return create((ContainerElementDefinition) definition);
            case BUTTON:
                return create((ButtonDefinition) definition);
            case INPUT:
                return create((InputDefinition) definition);
            case TEXT:
                return create((TextDefinition) definition);
            default:
                throw new IllegalStateException("cannot handle " + definition.getType());
        }
    }

    private WebElementProvider create(TextDefinition definition) {
        return createWebElementProvider(definition, id -> byFactory.text(id));
    }


    private WebElementProvider create(ButtonDefinition definition) {
        return createWebElementProvider(definition, id -> byFactory.clickable(id));
    }


    public WebElementProvider create(InputDefinition definition) {
        return createWebElementProvider(definition, id -> byFactory.textInput(id));
    }

    private WebElementProvider create(ContainerElementDefinition definition) {
        List<By> bys = new ArrayList<>();
        populateWithContainerHierarchy(bys, definition);
        return new WebElementProviderByImpl(provider, new ByChained(bys.toArray(new By[bys.size()])));
    }


    @NotNull
    private WebElementProvider createWebElementProvider(ElementDefinition definition, Function<ElementIdentifier, By> bySupplier) {
        List<By> bys = new ArrayList<>();
        if (!definition.isRoot()) {
            populateWithContainerHierarchy(bys, definition.getParent());
        }
        SelectorDefinition selector = definition.getSelector();
        if (selector == null) {
            bys.add(bySupplier.apply(definition.getIdentifier()));
        } else {
            bys.add(byFactory.fromSelector(selector));
        }

        return new WebElementProviderByImpl(provider, new ByChained(bys.toArray(new By[bys.size()])));
    }

    private void populateWithContainerHierarchy(List<By> bys, ContainerElementDefinition container) {
        if (!container.isRoot()) {
            populateWithContainerHierarchy(bys, container.getParent());
        }
        bys.add(byFactory.fromSelector(container.getSelector()));

    }



    private Element inputElement(WebElement element, By by) {
        InputType type = InputType.fromInputTypeAttributeValue(element.getAttribute("type"));
        switch (type) {

            case button:
            case checkbox:
            case color:
            case date:
            case email:
            case file:
            case hidden:
            case image:
            case month:
            case number:
            case password:
            case radio:
            case range:
            case reset:
            case search:
            case submit:
            case tel:
            case text:
                //return new TextInputElementWebDriverImpl(new WebElementProviderByImpl(provider, by));
            case time:
            case url:
            case week:
            default:
                throw new IllegalStateException("type not handled " + type);
        }
    }
}
