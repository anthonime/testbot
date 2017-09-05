package com.github.anthonime.testbot.runtime.elements.webdriver;

import com.github.anthonime.testbot.runtime.elements.HasWebPageContext;
import com.github.anthonime.testbot.runtime.elements.exceptions.MultipleElementsException;
import com.github.anthonime.testbot.runtime.elements.exceptions.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * Created by schio on 9/16/2017.
 */
public interface WebElementSupplier extends HasWebPageContext {

    /**
     * The description of the way to find the elements
     *
     * @return
     */
    String getDescription();

    /**
     * Return the WebElement if it is immediately present and if it is single.
     *
     * @return the webelement, if present
     * @throws NoSuchElementException    if the element is not present
     * @throws MultipleElementsException it several elements are found
     */
    WebElement get() throws NoSuchElementException, MultipleElementsException;

}
