package com.github.anthonime.testbot.runtime.elements.webdriver;

import com.github.anthonime.testbot.runtime.elements.ClickableElement;

/**
 * Created by schio on 9/12/2017.
 */
public class ClickableElementWebDriverImpl extends WebDriverElement implements ClickableElement<ClickableElementWebDriverImpl> {

    public ClickableElementWebDriverImpl(WebElementProvider provider) {
        super(provider);
    }

    @Override
    public ClickableElementWebDriverImpl click() {
        getWebElementIfVisible().click();
        return this;
    }
}
