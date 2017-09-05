package com.github.anthonime.testbot.w3c.css;

import com.github.anthonime.testbot.w3c.html.Tag;

/**
 * Created by schio on 9/12/2017.
 */
public class CssSelectorBuilder {

    private StringBuilder sb;

    private CssSelectorBuilder() {
        sb = new StringBuilder();
    }

    protected CssSelectorBuilder append(String s) {
        sb = sb.append(s);
        return this;
    }

    public CssSelectorBuilder attribute(String attibuteName) {
        return append(attributeOperation(attibuteName, null, null));
    }

    public CssSelectorBuilder attributeEqualsTo(String attibuteName, String value) {
        return append(attributeOperation(attibuteName, "=", value));
    }

    public CssSelectorBuilder attributeContaining(String attibuteName, String value) {
        return append(attributeOperation(attibuteName, "*=", value));
    }

    public CssSelectorBuilder attributeContainingWord(String attibuteName, String word) {
        return append(attributeOperation(attibuteName, "~=", word));
    }

    public static String attributeOperation(String attributeName, String operation, String value) {
        return "[" + attributeName + (operation != null ? operation + value : "") + "]";
    }

    public CssSelectorBuilder tag(Tag tag) {
        return append(tag.name());
    }

    public String build() {
        return sb.toString();
    }

    public CssSelectorBuilder or() {
        return append(",");
    }

}
