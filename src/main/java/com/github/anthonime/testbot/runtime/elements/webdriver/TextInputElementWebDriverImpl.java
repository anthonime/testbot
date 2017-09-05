package com.github.anthonime.testbot.runtime.elements.webdriver;

import com.github.anthonime.testbot.runtime.elements.TextInputElement;
import com.github.anthonime.testbot.w3c.ArrowKey;
import org.openqa.selenium.Keys;

/**
 * Created by schio on 9/10/2017.
 */
public class TextInputElementWebDriverImpl extends WebDriverElement implements TextInputElement {


    public TextInputElementWebDriverImpl(WebElementProvider provider) {
        super(provider);
    }

    @Override
    public TextInputElement click() {
        usingWebElement(e -> e.click());
        return this;
    }

    @Override
    public String getText() {
        return convertWebElement(e -> e.getAttribute("value"));
    }

    @Override
    public TextInputElement typeTab() {
        usingWebElement(e -> e.sendKeys(Keys.TAB));
        return this;
    }

    @Override
    public TextInputElement type(char c) {
        usingWebElement(e -> e.sendKeys(Character.toString(c)));
        return this;
    }

    @Override
    public TextInputElement type(CharSequence charSequence) {
        return null;
    }

    @Override
    public TextInputElement type(String string) {
        usingWebElement(e -> e.sendKeys(string));
        return this;
    }

    @Override
    public TextInputElement type(ArrowKey arrow) {
        usingWebElement(e -> e.sendKeys(arrow.toWebDriverKeys()));
        return this;
    }

    @Override
    public TextInputElement typeDel() {
        usingWebElement(e -> e.sendKeys(Keys.DELETE));
        return this;
    }

    @Override
    public TextInputElement typeEnter() {
        usingWebElement(e -> e.sendKeys(Keys.ENTER));
        return this;
    }

    @Override
    public TextInputElement typeEscape() {
        usingWebElement(e -> e.sendKeys(Keys.ESCAPE));
        return this;
    }

    @Override
    public TextInputElement focus() {
        return click();
    }

    @Override
    public boolean hasFocus() {
        return getContext().hasFocus(this);
    }

    @Override
    public TextInputElement deleteAllText() {
        usingWebElement(e -> {
            e.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        });
        return this;
    }

    @Override
    public TextInputElement selectAll() {
        usingWebElement(e -> e.sendKeys(Keys.chord(Keys.CONTROL, "a")));
        return this;
    }


}
