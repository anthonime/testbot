package com.github.anthonime.testbot.definitions;

import com.github.anthonime.testbot.definitions.applications.ApplicationDefinitionBuilder;
import com.github.anthonime.testbot.definitions.applications.EnvironmentDefinition;

/**
 * Created by schio on 9/12/2017.
 */
public class Definitions {

    /**
     * Return a new application builder for the given application identifier
     *
     * @param identifier
     * @return
     */
    public static ApplicationDefinitionBuilder application(String identifier) {
        return ApplicationDefinitionBuilder.anApplication(identifier);
    }

    /**
     * Return a new application builder for the given application identifier
     * and the given URI, with one single root environment
     *
     * @param identifier
     * @param uri
     * @return
     */
    public static ApplicationDefinitionBuilder application(String identifier, String uri) {
        return ApplicationDefinitionBuilder.anApplication(identifier)
                .withEnvironments(new EnvironmentDefinition("root",uri));
    }


}
