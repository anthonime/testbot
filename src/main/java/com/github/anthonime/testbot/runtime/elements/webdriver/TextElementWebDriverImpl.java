package com.github.anthonime.testbot.runtime.elements.webdriver;

import com.github.anthonime.testbot.runtime.elements.HasText;

/**
 * Created by schio on 9/12/2017.
 */
public class TextElementWebDriverImpl extends WebDriverElement implements HasText {

    public TextElementWebDriverImpl(WebElementProvider provider) {
        super(provider);
    }

    @Override
    public String getText() {
        return getWebElementIfVisible().getText();
    }
}
