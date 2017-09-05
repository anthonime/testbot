package com.github.anthonime.testbot.runtime.elements;

/**
 * Created by schio on 9/10/2017.
 */
public interface Element extends HasWebPageContext {

    /**
     * Return true if the element is present.
     *
     * @return
     */
    boolean isPresent();

    /**
     * Return true if the element is absent.
     *
     * @return
     */
    boolean isAbsent();

    /**
     * Return true if the element is visible
     *
     * @return
     */
    boolean isVisible();

    /**
     * Return true if the element is not visible.
     *
     * @return
     */
    boolean isInvisible();

    /**
     * Return the context of this element.
     *
     * @return
     */
    WebPageContext getContext();

    /**
     * Return the title attribute
     *
     * @return
     */
    String getTitleAttribute();


    /**
     * Return the attribute or property value
     *
     * @return
     */
    String getAttributeOrProperty(String attributeOrPropertyName);

    /**
     * Return the html tag name of the element.
     *
     * @return
     */
    String getTagName();


}
