package com.github.anthonime.testbot.runtime.elements.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by schio on 9/10/2017.
 */
public class WebElementProviderByImpl extends AbstractWebElementProvider {
    private final By by;

    public WebElementProviderByImpl(WebPageContextImpl webDriverProvider, By by) {
        super(webDriverProvider);
        this.by = by;
    }

    @Override
    public List<WebElement> getAll() {
        return ((WebPageContextImpl) getContext()).getWebDriver().findElements(by);
    }

    @Override
    public String getDescription() {
        return by.toString();
    }
}
