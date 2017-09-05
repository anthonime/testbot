package com.github.anthonime.testbot.runtime.elements.webdriver;

import com.github.anthonime.testbot.runtime.elements.WebPageContext;
import com.github.anthonime.testbot.runtime.elements.exceptions.MultipleElementsException;
import com.github.anthonime.testbot.runtime.elements.exceptions.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * Created by schio on 9/16/2017.
 */
public class WebElementProviderInList implements WebElementSupplier {


    private WebElementProvider provider;
    private int index;

    public WebElementProviderInList(WebElementProvider provider, int index) {
        this.provider = provider;
        this.index = index;
    }

    @Override
    public WebElement get() throws NoSuchElementException, MultipleElementsException {
        return provider.getAll().get(index);
    }

    @Override
    public String getDescription() {
        return provider.getDescription();
    }

    @Override
    public WebPageContext getContext() {
        return provider.getContext();
    }
}
