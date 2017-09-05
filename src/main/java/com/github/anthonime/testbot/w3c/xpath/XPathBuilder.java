package com.github.anthonime.testbot.w3c.xpath;

import com.github.anthonime.testbot.w3c.html.Tag;
import com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.ListIterator;

/**
 * Created by schio on 9/11/2017.
 * <p>
 * By the help of http://ricostacruz.com/cheatsheets/xpath
 */
public class XPathBuilder {

    public static XPathBuilder empty() {
        return new XPathBuilder("");
    }

    /**
     * Create a new XPath expression with "/"
     *
     * @return
     */
    public static XPathBuilder fromRoot() {
        return new XPathBuilder("/");
    }

    /**
     * Create a new XPath expression with "//"
     *
     * @return
     */

    public static XPathBuilder fromAnywhere() {
        return new XPathBuilder("//");
    }

    /**
     * Create a new XPath expression with //tag
     *
     * @param
     * @return
     */
    public static XPathBuilder fromAnywhere(Tag tag) {
        return fromAnywhere().tag(tag);
    }


    /**
     * Create a new XPath expression with "."
     *
     * @return
     */
    public static XPathBuilder fromRelative() {
        return new XPathBuilder(".");
    }

    public static XPathBuilder aRelativeDescendant() {
        return fromRelative().descendant();
    }


    public static XPathBuilder union(XPathBuilder... builders) {
        XPathBuilder result = empty();
        ListIterator<XPathBuilder> iterator = Arrays.asList(builders).listIterator();
        if (iterator.hasNext()) {
            result = result.append(iterator.next().build());
        }
        while (iterator.hasNext()) {
            result = result.union().append(iterator.next().toString());
        }
        return result;
    }

    /**
     * @return
     */
    protected static String anyAttribute() {
        return attribute(anyToken());
    }


    /**
     * Return '*'
     *
     * @return
     */
    protected static String anyToken() {
        return "*";
    }

    /**
     * Return the string "@attributeName"
     *
     * @param attributeName
     * @return
     */
    protected static String attribute(String attributeName) {
        return "@" + attributeName;
    }

    /**
     * The quote character
     *
     * @param string
     * @return
     */
    protected static String quote(String string) {
        return "'" + string + "'";
    }

    /**
     * The text function text()
     *
     * @return
     */
    protected static String textFunction() {
        return lowerCaseFunction(function("text"));
    }

    protected static String lowerCaseFunction(String expression) {
        return function("translate", expression, quote("ABCDEFGHIJKLMNOPQRSTUVWXYZ"), quote("abcdefghijklmnopqrstuvwxyz"));
    }

    /**
     * Append a function call with arguments: 'name(arg1,arg2)'
     *
     * @param name
     * @param args
     * @return
     */
    protected static String function(String name, String... args) {
        return name + "(" + Joiner.on(",").join(args) + ")";
    }

    private StringBuilder sb;

    private XPathBuilder(String s) {
        sb = new StringBuilder(s);
    }

    public XPathBuilder append(String s) {
        sb = sb.append(s);
        return this;
    }

    /**
     * Return the xpath expression as a string
     *
     * @return
     */
    public String build() {
        return sb.toString();
    }

    /**
     * Alias for #build()
     *
     * @return
     */
    public String toString() {
        return build();
    }

    /**
     * Append the union symbol : '|'
     *
     * @return
     */
    public XPathBuilder union() {
        return append(" | ");
    }


    /**
     * Append a tag
     *
     * @param tag
     * @return
     */
    public XPathBuilder tag(Tag tag) {
        return append(tag.name());
    }

    /**
     * Append any element (star character) i.e '*'
     *
     * @return
     */
    public XPathBuilder any() {
        return append(anyToken());
    }

    /**
     * Append the descendant axis : //
     *
     * @return
     */
    public XPathBuilder descendant() {
        return append("//");
    }


    /**
     * Append the any elemtent as descendant : //*
     *
     * @return
     */
    public XPathBuilder anyDescendant() {
        return descendant().any();
    }

    /**
     * Append a descendant tag : //tag
     *
     * @return
     */
    public XPathBuilder descendant(Tag tag) {
        return descendant().tag(tag);
    }


    /**
     * Append the child axis : /
     *
     * @return
     */
    public XPathBuilder child() {
        return append("/");
    }

    /**
     * Append the any element as child : /*
     *
     * @return
     */
    public XPathBuilder anyChild() {
        return child().any();
    }

    /**
     * Append a predicate to check there is any element as child
     *
     * @return
     */
    public XPathBuilder hasAnyChild() {
        return new XPathPredicateBuilder(this).hasChildren();
    }

    /**
     * Append a predicate to check there is an element as child
     *
     * @return
     */
    public XPathBuilder hasTagChild(Tag tag) {
        return new XPathPredicateBuilder(this).hasChildren(tag.name());
    }


    /**
     * Append a child tag : /tag
     *
     * @return
     */
    public XPathBuilder child(Tag tag) {
        return child().tag(tag);
    }

    //order selectors

    /**
     * Append the first element selector : //ul/li[1]
     *
     * @return
     */
    public XPathBuilder first() {
        return new XPathPredicateBuilder(this).first();
    }

    /**
     * Append the nth element selector : //ul/li[2]
     *
     * @param nth
     * @return
     */
    public XPathBuilder nth(int nth) {
        return new XPathPredicateBuilder(this).nth(nth);
    }

    /**
     * Append the last element selector : //ul/li[last()]
     *
     * @return
     */
    public XPathBuilder last() {
        return new XPathPredicateBuilder(this).last();
    }

    //siblings?

    public XPathBuilder sibling() {
        return append("following-sibling::");
    }

    public XPathBuilder parent() {
        return append("..");
    }


    public XPathPredicateExpressionBuilder<XPathPredicateBuilder> predicate() {
        return new XPathPredicateBuilder(this).startExpression();
    }


    public static class XPathPredicateBuilder {

        private final XPathBuilder parent;
        private StringBuilder sb = new StringBuilder();

        public XPathPredicateBuilder(XPathBuilder parent) {
            this.parent = parent;
        }

        protected XPathPredicateBuilder append(String s) {
            sb = sb.append(s);
            return this;
        }

        public XPathPredicateExpressionBuilder and() {
            this.append(" and ");
            return new XPathPredicateExpressionBuilder(this);
        }

        public XPathPredicateExpressionBuilder or() {
            this.append(" or ");
            return new XPathPredicateExpressionBuilder(this);
        }

//        public XPathPredicateExpressionBuilder not() {
//            XPathPredicateExpressionBuilder notExpression = new XPathPredicateExpressionBuilder();
//            return append(function("not", notExpression));
//            return notExpression;
//        }


        public XPathBuilder end() {
            if (this instanceof XPathNestedPredicateBuilder) {
                endThis();
                ((XPathNestedPredicateBuilder) this).parent.endThis();
            } else {
                endThis();
            }
            return parent;
        }

        protected void endThis() {
            String predicateExpression = createPredicate(expression());
            parent.append(predicateExpression);
        }

        protected XPathBuilder first() {
            return append("1").end();
        }

        protected XPathBuilder nth(int nth) {
            return append(String.valueOf(nth)).end();
        }

        protected XPathBuilder last() {
            return append(function("last")).end();
        }

        protected XPathBuilder hasChildren() {
            return append(anyToken()).end();
        }

        protected XPathBuilder hasChildren(String expression) {
            return append(expression).end();
        }

        protected String expression() {
            return sb.toString();
        }


        public String createPredicate(String predicate) {
            return "[" + predicate + "]";
        }

        public XPathPredicateExpressionBuilder<XPathPredicateBuilder> startExpression() {
            return new XPathPredicateExpressionBuilder<>(this);
        }

        public XPathPredicateExpressionBuilder<XPathNestedPredicateBuilder> predicate() {
            return new XPathNestedPredicateBuilder(parent, this).startNestedExpression();
        }
    }

    public static class XPathNestedPredicateBuilder extends XPathPredicateBuilder {

        private final XPathPredicateBuilder parent;

        public XPathNestedPredicateBuilder(XPathBuilder parent, XPathPredicateBuilder parent1) {
            super(parent);
            this.parent = parent1;
        }

        public XPathPredicateExpressionBuilder<XPathNestedPredicateBuilder> startNestedExpression() {
            return new XPathPredicateExpressionBuilder<>(this);
        }

        /**
         * End this nested predicate and return the parent predicate
         *
         * @return
         */
        public XPathPredicateBuilder endUp() {
            endThis();
            return this.parent;
        }

        protected void endThis() {
            String predicateExpression = createPredicate(expression());
            parent.append(predicateExpression);
        }
    }

    public static class XPathPredicateExpressionBuilder<T extends XPathPredicateBuilder> {
        //  private StringBuilder sb = new StringBuilder();
        private final T parent;

        public XPathPredicateExpressionBuilder(T parent) {
            this.parent = parent;
        }

        public T append(String s) {
            return (T) parent.append(s);
        }

        public T tag(Tag tag) {
            return append(tag.name());
        }


        public T hasAttribute(String attribute) {
            return append(attribute(attribute));
        }

        public T hasAttributeNameContaining(String attribute) {
            append(anyAttribute())
                    .predicate().append(function("contains", lowerCaseFunction(function("name")), quote(attribute))).endUp();
            return parent;
        }


        public T hasAnyAttributeContaining(String attributeValue) {
            append(anyAttribute())
                    .predicate().append(function("contains", lowerCaseFunction("."), quote(attributeValue))).endUp();
            return parent;
        }

        public T hasAttributeEqualsTo(String attributeName, String value) {
            return append(attribute(attributeName) + "=" + quote(value));
        }

        public T hasAttributeStartingWith(String attributeName, String value) {
            return append(function("starts-with", attribute(attributeName), quote(value)));
        }

        public T hasAttributeEndingWith(String attributeName, String value) {
            return append(function("ends-with", attribute(attributeName), quote(value)));
        }


        public T hasAttributeContaining(String attributeName, String value) {
            return append(function("contains", attribute(attributeName), quote(value)));
        }

        public T hasTextEqualsTo(String value) {
            return append(textFunction() + "=" + quote(value));
        }

        public T hasTextContaining(String value) {
            return append(function("contains", textFunction(), quote(value)));
        }

        public T idEquals(String value) {
            return hasAttributeEqualsTo("id", value);
        }

        public T hasClass(String className) {
            String classToLookup = " " + className.trim() + " ";
            String normalizeClassAttributeValueFunction = function("normalize-space", attribute("class"));
            String quotedSpace = quote(" ");
            String concatFunction = function("concat", quotedSpace, normalizeClassAttributeValueFunction, quotedSpace);
            return append(function("contains", concatFunction, quote(classToLookup)));
        }

        public T hasNoAttribute(String attributeName) {
            return append(function("not", attribute(attributeName)));
        }

        public T descendant(Tag tag) {
            return append("//" + tag.name());
        }

        public T anyDescendant() {
            return append("./descendant-or-self::*");
        }

        public T anyDescendantExcludingSelf() {
            return append("./descendant::*");
        }


    }


}
