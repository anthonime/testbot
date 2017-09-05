package com.github.anthonime.testbot.runtime.elements.webdriver;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

/**
 * Created by schio on 9/16/2017.
 */
public interface WebElementExceptionCatcher {

    /**
     * Return true if the exception should propagated to called,
     * false otherwise.
     *
     * @param element
     * @param e
     * @return
     */
    boolean onException(WebElement element, StaleElementReferenceException e);
}
