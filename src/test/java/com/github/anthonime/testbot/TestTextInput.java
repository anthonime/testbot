package com.github.anthonime.testbot;

import com.github.anthonime.testbot.runtime.elements.TextInputElement;
import com.github.anthonime.testbot.support.AbstractWebDriverTest;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by schio on 9/10/2017.
 */
public class TestTextInput extends AbstractWebDriverTest {


    @Test
    @Ignore
    public void textInputCanSelectAll() throws Exception {
        //can focus
        //open the page
        //find the element
        //focus
        //type text
        //deleteAll
        //animations ?
        openGmail();

        TextInputElement input = findTextInput(driver, "email");
        assertThat(input).isNotNull();
        assertThat(input.isPresent()).isTrue();
        assertThat(input.isVisible()).isTrue();
        assertThat(input.isAbsent()).isFalse();
        assertThat(input.isInvisible()).isFalse();
    }

    private TextInputElement findTextInput(WebDriver driver, String email) {
        return null;
    }

    private WebElement getEmailInputElement(WebDriver driver) {
        return driver.findElement(By.cssSelector("input[type=email]"));
    }

    private void openGmail() {
        driver.get("file:///C:/Users/schio/Downloads/Gmail.html");
    }


}
