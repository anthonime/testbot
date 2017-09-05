package com.github.anthonime.testbot.runtime.elements.exceptions;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by schio on 9/12/2017.
 */
public class MultipleElementsException extends RuntimeException {
    public MultipleElementsException() {
        super();
    }

    private List<WebElement> elements;

    public MultipleElementsException(String message, List<WebElement> elements) {
        super(message);
        this.elements = elements;
    }

    public List<WebElement> getElements() {
        return elements;
    }
}
