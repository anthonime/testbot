package com.github.anthonime.testbot.runtime.elements.webdriver;

import com.github.anthonime.testbot.runtime.elements.Element;
import com.github.anthonime.testbot.runtime.elements.WebPageContext;
import com.github.anthonime.testbot.runtime.elements.exceptions.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by schio on 9/10/2017.
 */
public class WebDriverElement implements Element {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverElement.class);

    private WebElementProvider provider;

    public WebDriverElement(WebElementProvider provider) {
        this.provider = provider;
    }

    /**
     * Return the webelement if it is visible, or throws an exception.
     *
     * @return
     */
    protected WebElement getWebElementIfVisible() {
        WebElement webElement = getWebElementIfPresent();
        if (!webElement.isDisplayed()) {
            throw new IllegalStateException("WebDriver element is present but not visible (" + provider.getDescription() + ")");
        }
        return webElement;
    }

    public WebElement getWebElementIfPresent() {
        try {
            return provider.get();
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("WebDriver element is not present, when located as " + provider.getDescription());
        }
    }

    protected void usingWebElement(Consumer<WebElement> consumer) {
        consumer.accept(getWebElementIfVisible());
    }

    protected <T> T convertWebElement(Function<WebElement, T> function) {
        return function.apply(getWebElementIfVisible());
    }

    @Override
    public boolean isPresent() {
        return provider.isPresent();
    }

    @Override
    public boolean isAbsent() {
        return !provider.isPresent();
    }

    @Override
    public boolean isVisible() {
        try {
            LOGGER.debug("Checking is element described by {} is visible", provider.getDescription());
            WebElement webElement = provider.get();
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            LOGGER.debug("Element not present thus not visible");
            return false;
        }
    }

    @Override
    public boolean isInvisible() {
        try {
            WebElement webElement = provider.get();
            return !webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            LOGGER.debug("Element not present thus invisible");
            return true;
        }
    }


    @Override
    public WebPageContext getContext() {
        return this.provider.getContext();
    }

    @Override
    public String getTitleAttribute() {
        return getWebElementIfVisible().getAttribute("title");
    }

    @Override
    public String getAttributeOrProperty(String attributeOrPropertyName) {
        return getWebElementIfPresent().getAttribute(attributeOrPropertyName);
    }

    @Override
    public String toString() {
        return "Element located by " + this.provider.getDescription();
    }

    protected boolean isEnabled() {
        return getWebElementIfVisible().isEnabled();
    }

    @Override
    public String getTagName() {
        return getWebElementIfPresent().getTagName();
    }
}
