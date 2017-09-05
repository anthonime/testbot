package com.github.anthonime.testbot.runtime.elements.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Find elements using each by in sequence, until at least one element is found.
 * Hence, it is like a OR operation, where the second operand is not evaluated if the first one succeed.
 */
public class ByOr extends By {

    private By[] bys;

    public ByOr(By... bys) {
        this.bys = bys;
    }

    @Override
    public List<WebElement> findElements(SearchContext context) {
        if (bys.length == 0) {
            return new ArrayList<>();
        }
        for (By by : bys) {
            List<WebElement> newElems = by.findElements(context);
            if (newElems.size() > 0) {
                return newElems;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public WebElement findElement(SearchContext context) {
        return super.findElement(context);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("By.or(");
        stringBuilder.append("{");

        boolean first = true;
        for (By by : bys) {
            stringBuilder.append((first ? "" : ",")).append(by);
            first = false;
        }
        stringBuilder.append("})");
        return stringBuilder.toString();
    }
}
