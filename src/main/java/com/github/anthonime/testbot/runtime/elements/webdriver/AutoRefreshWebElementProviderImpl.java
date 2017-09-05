package com.github.anthonime.testbot.runtime.elements.webdriver;

import com.github.anthonime.testbot.runtime.elements.WebPageContext;
import com.github.anthonime.testbot.runtime.elements.exceptions.MultipleElementsException;
import com.github.anthonime.testbot.runtime.elements.exceptions.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by schio on 9/15/2017.
 */
public class AutoRefreshWebElementProviderImpl implements WebElementProvider {
    private WebElementProvider delegate;

    public AutoRefreshWebElementProviderImpl(WebElementProvider delegate) {
        this.delegate = delegate;
    }


    @Override
    public boolean isPresent() {
        return delegate.isPresent();
    }

    @Override
    public boolean isSingle() {
        return delegate.isSingle();
    }

    @Override
    public List<WebElement> getAll() {
        //FIXME: we may additionnally detect there are more elements (or less)
        //in the list by recalling the getAll() method sometime
        //we could detect it after iterating
        //the list could be "refreshed" itself if there is a new
        List<WebElement> initialElements = delegate.getAll();
        return IntStream.range(0, initialElements.size())
                .mapToObj(i -> {
                    WebElementProviderInList providerInList = new WebElementProviderInList(delegate, i);
                    WebElement initialElement = initialElements.get(i);
                    return new AutoRefreshOnStalenessWebElement(providerInList, initialElement, null);
                })
                .collect(Collectors.toList());

    }

    @Override
    public String getDescription() {
        return "[AutoRefresh stale elements] " + delegate.getDescription();
    }

    @Override
    public WebElement get() throws NoSuchElementException, MultipleElementsException {
        return new AutoRefreshOnStalenessWebElement(delegate);
    }

    @Override
    public WebPageContext getContext() {
        return delegate.getContext();
    }
}
