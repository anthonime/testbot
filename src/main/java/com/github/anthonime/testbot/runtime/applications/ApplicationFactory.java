package com.github.anthonime.testbot.runtime.applications;

import com.github.anthonime.testbot.definitions.applications.ApplicationDefinition;
import com.github.anthonime.testbot.runtime.elements.WebPageContext;

/**
 * Created by schio on 9/16/2017.
 */
public class ApplicationFactory {

    private WebPageContext context;

    public ApplicationFactory(WebPageContext context) {
        this.context = context;
    }

    public Application create(ApplicationDefinition definition) {
        return new ApplicationImpl(context, definition);
    }


}
