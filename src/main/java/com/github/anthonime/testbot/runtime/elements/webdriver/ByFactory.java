package com.github.anthonime.testbot.runtime.elements.webdriver;

import com.github.anthonime.testbot.definitions.elements.ElementIdentifier;
import com.github.anthonime.testbot.definitions.elements.SelectorDefinition;
import com.github.anthonime.testbot.w3c.html.ButtonType;
import com.github.anthonime.testbot.w3c.html.InputType;
import com.github.anthonime.testbot.w3c.html.Tag;
import com.github.anthonime.testbot.w3c.xpath.XPathBuilder;
import com.google.common.base.Joiner;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by schio on 9/11/2017.
 * We could also detect the framework to detect more components...
 */
public class ByFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ByFactory.class);

    /**
     * Return a By that find elements a, button and input type reset,button,submit,
     * and any elements with role attribute to button,link,menuitem,tab
     * FIXME: checkbox, radio... ?
     *
     * @return a by instance to find any clickable element
     */
    public By clickable() {
        List<String> selectors = Arrays.asList(
                "a",
                "button",
                "input[type='button']",
                "input[type='reset']",
                "input[type='submit']",
                "[role='button']",
                "[role='link']",
                "[role='menuitem']",
                "[role='tab']");
        return By.cssSelector(Joiner.on(",").join(selectors));
    }

    public By fromSelector(SelectorDefinition definition) {
        if (definition.isId()) {
            return By.id(definition.toString());
        } else if (definition.isCss()) {
            return By.cssSelector(definition.toString());
        } else if (definition.isXPath()) {
            return By.xpath(definition.toString());
        }
        throw new IllegalArgumentException("cannot transform definition " + definition + " into a By instance");
    }

    /**
     * Return a By.xpath to find all clickable elements corresponding to the given identifier, i.e:
     * <ul>
     * <li>button elements with text or any attribute containing identifier</li>
     * <li>a (links) elements with text or any attribute containing identifier</li>
     * <li>clickable input elements (button, reset, submit)  with any attribute containing identifier</li>
     * <li>any element with role attribute (button, link, menuitem, tab) with text or any attribute containing identifier </li>
     * </ul>
     * <p></p>
     * The returned 'by' instance can be combined with another parent 'by' in a ByChained instance
     * in order to narrow the search scope to a container element.
     * <p></p>
     *
     * @param identifier
     * @return
     */
    public By clickable(ElementIdentifier identifier) {
        Objects.requireNonNull(identifier, "identifier cannot be null");
        String elementIdentifier = identifier.toLowerCase();
        XPathBuilder builder = XPathBuilder.union(
                buttons(elementIdentifier, XPathBuilder.fromRelative().descendant()),
                links(elementIdentifier, XPathBuilder.fromRelative().descendant()),
                clickableInputs(elementIdentifier, XPathBuilder.fromRelative().descendant()),
                anyElementWithclickableRoles(elementIdentifier, XPathBuilder.fromRelative().descendant())
        );


        //LOGGER.debug(builder.build());
        return By.xpath(builder.build());
    }

    public By text(ElementIdentifier identifier) {
        Objects.requireNonNull(identifier, "identifier cannot be null");
        String elementIdentifier = identifier.toLowerCase();
        By containsItself = By.xpath(XPathBuilder.aRelativeDescendant().any().predicate().hasTextContaining(elementIdentifier).end().build());
//        By exactTextMatch = By.xpath(XPathBuilder.aRelativeDescendant().any().predicate().hasTextContaining(elementIdentifier).end().build());
        //first search exact text match in itself
        //then search contains text match
        //do the same in child
        //do the same in descendants
        //LOGGER.debug(containsItself.toString());
        //return By.xpath(builder.build());
        return containsItself;
    }


    public By textInput(ElementIdentifier identifier) {
        Objects.requireNonNull(identifier, "identifier cannot be null");
        String elementIdentifier = identifier.toLowerCase();
        XPathBuilder builder = XPathBuilder.union(
                simpleTextInputs(elementIdentifier, XPathBuilder.fromRelative().descendant())
                //textareas
                //button, checkbox,color, date, file, range,radio reset, submit,select???
                //hybrid (but depends on nav): date, datetime-local,number,time, select (if findable)
                //ARIA roles: https://www.w3.org/TR/wai-aria/roles#role_definitions
                //checkbox, combobox, listbox, menu
                //button, link => clickbox
                //listbox => option
                //menuitemcheckbox, menuitemradio
                //radio
                //slider, spinbutton
                //textbox
                //

                //ignore those!
                //datalist
                //keygen
                //output
        );

        //LOGGER.debug(builder.build());
        return By.xpath(builder.build());
    }

    private XPathBuilder simpleTextInputs(String identifier, XPathBuilder builder) {
        //input with no attribute type
        //or with type(date,email,month,number,password,search, tel,text,time,url,week)
        //or with attribute list
        XPathBuilder.XPathPredicateBuilder predicate = builder.tag(Tag.input).predicate()
                .hasNoAttribute("type")
                .or().hasAttributeEqualsTo("type", InputType.date.name())
                .or().hasAttributeEqualsTo("type", InputType.email.name())
                .or().hasAttributeEqualsTo("type", InputType.month.name())
                .or().hasAttributeEqualsTo("type", InputType.number.name())
                .or().hasAttributeEqualsTo("type", InputType.password.name())
                .or().hasAttributeEqualsTo("type", InputType.search.name())
                .or().hasAttributeEqualsTo("type", InputType.tel.name())
                .or().hasAttributeEqualsTo("type", InputType.text.name())
                .or().hasAttributeEqualsTo("type", InputType.time.name())
                .or().hasAttributeEqualsTo("type", InputType.url.name())
                .or().hasAttributeEqualsTo("type", InputType.week.name())
                .or().hasAttribute("list")
                .end()
                .predicate()
                .hasAttributeNameContaining(identifier)
                .or().hasAnyAttributeContaining(identifier);

        if (ButtonType.isButtonType(identifier)) {
            predicate = predicate.or().hasAttributeEqualsTo("type", identifier);
        }

        return predicate.end();
    }

    private XPathBuilder anyElementWithclickableRoles(String identifier, XPathBuilder builder) {
        //any element with roles(button,link,menuitme,tab) with id,name,text,value
        //not button,a,input
        return identifierInTextAndAttributes(identifier, builder.any()).end()
                .predicate()
                .hasAttributeEqualsTo("role", "button")
                .or().hasAttributeEqualsTo("role", "link")
                .or().hasAttributeEqualsTo("role", "menuitem")
                .or().hasAttributeEqualsTo("role", "tab")
                .end();
    }


    private XPathBuilder.XPathPredicateBuilder anyElementWithTextContent(String identifier, XPathBuilder builder) {
        return builder.any().predicate()
                .anyDescendant().predicate().hasTextContaining(identifier).endUp()
                .or().hasTextContaining(identifier);
    }


    private XPathBuilder clickableInputs(String identifier, XPathBuilder builder) {
        //input (button,restet,submit), id(contains), name(contains), value(contains)
        XPathBuilder.XPathPredicateBuilder predicate = builder.tag(Tag.input).predicate()
                .hasAttributeEqualsTo("type", InputType.button.name())
                .or().hasAttributeEqualsTo("type", InputType.reset.name())
                .or().hasAttributeEqualsTo("type", InputType.submit.name())
                .or().hasAttributeEqualsTo("type", InputType.checkbox.name())
                .end()
                .predicate()
                .hasAttributeNameContaining(identifier)
                .or().hasAnyAttributeContaining(identifier);

        if (ButtonType.isButtonType(identifier)) {
            predicate = predicate.or().hasAttributeEqualsTo("type", identifier);
        }

        return predicate.end();
    }

    private XPathBuilder links(String identifier, XPathBuilder builder) {

        XPathBuilder.XPathPredicateBuilder predicate = identifierInTextAndAttributes(identifier, builder.tag(Tag.a));

        return predicate.end();
    }

    private XPathBuilder buttons(String identifier, XPathBuilder builder) {
        //button id, name, text or children containing text
        //NB: if text is placed after children declaration, it does not work!
        XPathBuilder.XPathPredicateBuilder predicate = identifierInTextAndAttributes(identifier, builder.tag(Tag.button));

        if (ButtonType.isButtonType(identifier)) {
            predicate = predicate.or().hasAttributeEqualsTo("type", identifier);
        }

        return predicate.end();
    }

    private XPathBuilder.XPathPredicateBuilder identifierInTextAndAttributes(String identifier, XPathBuilder builder) {
        return builder.predicate()
                .anyDescendant().predicate().hasTextContaining(identifier).endUp()
                .or().hasTextContaining(identifier)
                .or().hasAttributeNameContaining(identifier)
                .or().hasAnyAttributeContaining(identifier);
    }


}
