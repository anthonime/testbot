package com.github.anthonime.testbot;

import com.github.anthonime.testbot.definitions.elements.ElementIdentifier;
import com.github.anthonime.testbot.runtime.elements.webdriver.ByFactory;
import com.github.anthonime.testbot.support.AbstractWebDriverTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by schio on 9/12/2017.
 */
public class TestByFactory extends AbstractWebDriverTest {

    public static final ElementIdentifier ELEMENT_IDENTIFIER = new ElementIdentifier("identifier");
    public static final ElementIdentifier RESET_IDENTIFIER = new ElementIdentifier("reset");

    @Test
    public void clickable() throws Exception {
        ByFactory factory = new ByFactory();
        By by = factory.clickable(new ElementIdentifier("identifier"));
        driver.get("C:\\Users\\schio\\git\\testbot\\src\\test\\resources\\html_pages\\clickable.html");
        List<WebElement> elements = driver.findElements(by);
        assertThat(elements)
                .extracting(e -> e.getAttribute("testid"))
                .containsExactly(
                        //buttons
                        "buttonbytext", "buttonbytextignorecase",
                        "buttonbytextcontaining", "buttonbytextstarting", "buttonbytextending",
                        "buttonbytextwithchildrenafter", "buttonbytextinsidechild",
                        "buttonbyattributename", "buttonbyattributenamecontains", "buttonbyanyattributecontains",
                        "buttonbyid", "buttonbyidcontaining", "buttonbyname", "buttonbynamecontaining",
                        //inputs
                        "inputbuttonbyvalue", "inputbuttonbyid", "inputbuttonbyname", "inputbuttonbyattributename",
                        "inputresetbyvalue", "inputresetbyid", "inputresetbyname", "inputresetbyattributename",
                        "inputsubmitbyvalue", "inputsubmitbyid", "inputsubmitbyname", "inputsubmitbyattributename",
                        //links
                        "linkbytext", "linkbytextignorecase", "linkbytextcontaining", "linkbytextstarting", "linkbytextending",
                        "linkbytextwithchildrenafter", "linkbytextinsidechild",
                        "linkbyattributename", "linkbyattributenamecontains", "linkbyanyattributecontains",
                        //roles
                        "rolebutton", "rolelink", "rolemenuitem", "roletab",
                        //match two times
                        "role+button",
                        //inner
                        "innerbutton", "innerinput", "innerlink", "innerrole"
                );
    }

    @Test
    public void buttonByType() throws Exception {
        ByFactory factory = new ByFactory();
        By by = factory.clickable(RESET_IDENTIFIER);
        driver.get("C:\\Users\\schio\\git\\testbot\\src\\test\\resources\\html_pages\\clickable.html");
        List<WebElement> elements = driver.findElements(by);
        assertThat(elements)
                .extracting(e -> e.getAttribute("testid"))
                .containsExactly("buttonbytype",
                        "inputresetbyvalue",
                        "inputresetbyid",
                        "inputresetbyname",
                        "inputresetbyattributename");
    }


    @Test
    public void innerClickable() throws Exception {
        ByFactory factory = new ByFactory();
        By by = new ByChained(By.id("testcontainer"), factory.clickable(ELEMENT_IDENTIFIER));
        driver.get("C:\\Users\\schio\\git\\testbot\\src\\test\\resources\\html_pages\\clickable.html");
        List<WebElement> elements = driver.findElements(by);
        assertThat(elements)
                .extracting(e -> e.getAttribute("testid"))
                .containsExactly("innerbutton",
                        "innerinput",
                        "innerlink",
                        "innerrole");
    }

    @Test
    public void problematicCase() throws Exception {
        ByFactory factory = new ByFactory();
        By by = new ByChained(factory.clickable(new ElementIdentifier("Oubli")));
        driver.get("C:\\Users\\schio\\git\\testbot\\src\\test\\resources\\html_pages\\clickable.html");
        List<WebElement> elements = driver.findElements(by);
        assertThat(elements)
                .extracting(e -> e.getAttribute("testid"))
                .containsExactly("problembutton");
    }

    @Test
    public void textElements() {
        ByFactory factory = new ByFactory();
        By by = new ByChained(factory.text(new ElementIdentifier("my text")));
        driver.get("C:\\Users\\schio\\git\\testbot\\src\\test\\resources\\html_pages\\clickable.html");
        List<WebElement> elements = driver.findElements(by);
        assertThat(elements)
                .extracting(e -> e.getAttribute("testid"))
                .containsExactly("textexact",
                        "textcontains",
                        "textwithchild",
                        "textwithchildcontains",
                        "textinchild",
                        "textindescendant",
                        "textindescendantcontaining");
    }

}
