package com.github.anthonime.testbot.runtime.elements.webdriver;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by schio on 9/10/2017.
 */
public interface WebElementProvider extends WebElementSupplier {


    /**
     * Return all the element that match immediately
     *
     * @return
     */
    List<WebElement> getAll();

    /**
     * @return true if at least one element is found
     */
    default boolean isPresent() {
        return !getAll().isEmpty();
    }

    /**
     * Return true if only one element is found
     *
     * @return
     */
    default boolean isSingle() {
        return getAll().size() == 1;
    }


    static String debugElements(List<WebElement> webElements) {
        try {
            return webElements.stream().map(e -> e.getTagName() + e.getText() + e.toString())
                    .reduce("", (s1, s2) -> s1 + "\n" + s2);
        } catch (Exception e) {
            return " debug stream not available";
        }
    }
}
