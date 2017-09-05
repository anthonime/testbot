package com.github.anthonime.testbot.runtime.elements.webdriver;

import com.github.anthonime.testbot.runtime.elements.Element;
import com.github.anthonime.testbot.runtime.elements.WebPageContext;
import org.openqa.selenium.WebDriver;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by schio on 9/10/2017.
 */
public class WebPageContextImpl implements WebPageContext, WebDriverProvider {

    private WebDriver webDriver;

    public WebPageContextImpl(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public boolean hasFocus(Element element) {
        if (element instanceof WebDriverElement)
            return ((WebDriverElement) element).getWebElementIfVisible().equals(webDriver.switchTo().activeElement());
        throw new IllegalStateException("Cannot check if the element " + element + " has focus!");
    }

    @Override
    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Override
    public URI getCurrentURI() {
        try {
            return new URI(webDriver.getCurrentUrl());
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @Override
    public void setCurrentURI(URI uri) {
        webDriver.get(uri.toString());
    }
}
