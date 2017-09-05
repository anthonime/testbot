package com.github.anthonime.testbot.definitions.elements;

/**
 * Created by schio on 9/12/2017.
 */
public interface SelectorDefinition {
    boolean isXPath();

    boolean isCss();

    boolean isId();

    static SelectorDefinition xpath(String xpathExpression) {
        return new SelectorDefinitionImpl(true, false, false, xpathExpression);
    }

    static SelectorDefinition css(String selector) {
        return new SelectorDefinitionImpl(false, true, false, selector);
    }

    static SelectorDefinition id(String id) {
        return new SelectorDefinitionImpl(false, false, true, id);
    }

    class SelectorDefinitionImpl implements SelectorDefinition {
        private boolean xpath;
        private boolean css;
        private boolean id;
        private String value;

        private SelectorDefinitionImpl(boolean xpath, boolean css, boolean id, String value) {
            this.xpath = xpath;

            this.css = css;
            this.id = id;
            this.value = value;
        }


        @Override
        public boolean isXPath() {
            return xpath;
        }

        @Override
        public boolean isCss() {
            return css;
        }

        @Override
        public boolean isId() {
            return id;
        }

        @Override
        public String toString() {
            return value;
        }
    }

}
