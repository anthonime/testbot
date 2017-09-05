package com.github.anthonime.testbot.runtime.states;

import com.github.anthonime.testbot.runtime.elements.Element;

import java.util.function.Function;

/**
 * Created by schio on 9/15/2017.
 */
public class ElementValueExtractorImpl implements ElementValueExtractor {

    private Function<Element, String> extractor;

    public ElementValueExtractorImpl(Function<Element, String> extractor) {
        this.extractor = extractor;
    }

    @Override
    public Object extract(Element element) {
        //FIXME: do
        return extractor.apply(element);
    }
}
