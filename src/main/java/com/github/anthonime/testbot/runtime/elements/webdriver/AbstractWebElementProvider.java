package com.github.anthonime.testbot.runtime.elements.webdriver;

import com.github.anthonime.testbot.runtime.elements.WebPageContext;
import com.github.anthonime.testbot.runtime.elements.exceptions.MultipleElementsException;
import com.github.anthonime.testbot.runtime.elements.exceptions.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by schio on 9/10/2017.
 */
public abstract class AbstractWebElementProvider implements WebElementProvider {
    private WebPageContextImpl webDriverProvider;

    public AbstractWebElementProvider(WebPageContextImpl webDriverProvider) {
        this.webDriverProvider = webDriverProvider;
    }

    /**
     * Return the WebElement if it is immediately present and if it is single.
     *
     * @return the webelement, if present
     * @throws NoSuchElementException    if the element is not present
     * @throws MultipleElementsException it several elements are found
     */
    public WebElement get() throws NoSuchElementException, MultipleElementsException {
        List<WebElement> webElements = getAll();
        if (webElements.size() == 1) {
            return webElements.get(0);
        } else if (webElements.isEmpty()) {
            throw new NoSuchElementException("no element matching '" + getDescription() + "'");
        } else {
            throw new MultipleElementsException(webElements.size() + " elements matching '" + getDescription() + ": " + debug(webElements), webElements);
        }
    }

    protected String debug(List<WebElement> webElements) {
        return IntStream.range(0, webElements.size())
                .limit(8)
                .mapToObj(i -> {
                    WebElement w = webElements.get(i);
                    return "\nelement " + i + " : " + w.getTagName() + " " + w.getText();
                }).reduce("", (s1, s2) -> s1 + "\n" + s2);
    }


    public WebDriverProvider getWebDriverProvider() {
        return webDriverProvider;
    }

    @Override
    public WebPageContext getContext() {
        return webDriverProvider;
    }
}
