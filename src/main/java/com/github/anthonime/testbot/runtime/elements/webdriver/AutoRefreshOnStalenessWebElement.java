package com.github.anthonime.testbot.runtime.elements.webdriver;

import com.github.anthonime.testbot.runtime.elements.exceptions.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by schio on 9/15/2017.
 */
public class AutoRefreshOnStalenessWebElement implements WebElement {

    private WebElement initialElement;
    private final WebElementSupplier provider;
    private final Duration timeout;
    private final Duration sleep;
    private final WebElementExceptionCatcher catcher;

    public AutoRefreshOnStalenessWebElement(WebElementSupplier provider) {
        this(provider, null, null);
    }

    public AutoRefreshOnStalenessWebElement(WebElementSupplier provider, WebElementExceptionCatcher catcher) {
        this(provider, null, catcher);
    }

    public AutoRefreshOnStalenessWebElement(WebElementSupplier provider, WebElement initialElement, WebElementExceptionCatcher catcher) {
        this.provider = provider;
        this.timeout = Duration.ofSeconds(10);
        this.sleep = Duration.ofMillis(200);
        this.initialElement = initialElement;
        this.catcher = catcher;
    }

    protected void retryConsumer(Consumer<WebElement> consumer, String actionType) {
        retryFunction(e -> {
            consumer.accept(e);
            return true;
        }, actionType);
    }

    protected <T> T retryFunction(Function<WebElement, T> function, String actiondescription) {
        return new WebDriverWait(((WebPageContextImpl) provider.getContext()).getWebDriver(), timeout.getSeconds(), sleep.toMillis())
                .withMessage(() -> "Impossible to do " + actiondescription + " on element " + provider.getDescription() + " because element is continuously state!")
                .until(ExpectedConditions.refreshed(d -> {
                    try {
                        return function.apply(getWebElement());
                    } catch (StaleElementReferenceException e) {
                        //intercept to remove the initial element
                        if (initialElement != null) {
                            initialElement = null;
                        }
                        throw e;
                    } catch (NotFoundException e) {
                        //by default ignored => wrap it to make the Waiter fail
                        //(we don't want to ignore it)
                        throw new NoSuchElementException(e);
                    }
                }));
    }

    protected WebElement getWebElement() {
        return initialElement != null ? initialElement : provider.get();
    }

    @Override
    public void click() {
        retryConsumer(e -> e.click(), "click");
    }

    @Override
    public void submit() {
        retryConsumer(e -> e.submit(), "submit");
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        retryConsumer(e -> e.sendKeys(keysToSend), "sendKeys " + keysToSend);
    }

    @Override
    public void clear() {
        retryConsumer(e -> e.clear(), "clear");
    }

    @Override
    public String getTagName() {
        return retryFunction(e -> e.getTagName(), "getTagName");
    }

    @Override
    public String getAttribute(String name) {
        return retryFunction(e -> e.getAttribute(name), "getAttribute " + name);
    }

    @Override
    public boolean isSelected() {
        return retryFunction(e -> e.isSelected(), "isSelected");
    }

    @Override
    public boolean isEnabled() {
        return retryFunction(e -> e.isEnabled(), "isEnabled");
    }

    @Override
    public String getText() {
        return retryFunction(e -> e.getText(), "getText");
    }

    @Override
    public List<WebElement> findElements(By by) {
        return retryFunction(e -> e.findElements(by), "findElements " + by);
    }

    @Override
    public WebElement findElement(By by) {
        return retryFunction(e -> e.findElement(by), "findElement " + by);
    }

    @Override
    public boolean isDisplayed() {
        return retryFunction(e -> e.isDisplayed(), "isDisplayed");
    }

    @Override
    public Point getLocation() {
        return retryFunction(e -> e.getLocation(), "getLocation");
    }

    @Override
    public Dimension getSize() {
        return retryFunction(e -> e.getSize(), "getSize");
    }

    @Override
    public Rectangle getRect() {
        return retryFunction(e -> e.getRect(), "getRect");
    }

    @Override
    public String getCssValue(String propertyName) {
        return retryFunction(e -> e.getCssValue(propertyName), "getCssValue " + propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return retryFunction(e -> e.getScreenshotAs(target), "getScreenshotAs " + target);
    }
}
