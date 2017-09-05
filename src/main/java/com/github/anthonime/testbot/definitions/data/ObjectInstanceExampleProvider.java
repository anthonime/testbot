package com.github.anthonime.testbot.definitions.data;

import java.util.List;

/**
 * Created by schio on 9/14/2017.
 * Provide examples of a objects.
 */
public interface ObjectInstanceExampleProvider {

    List<ObjectInstance> getExamples(ObjectDefinitionIdentifier definitionIdentifier);
}
