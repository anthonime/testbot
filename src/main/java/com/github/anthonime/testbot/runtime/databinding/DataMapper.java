package com.github.anthonime.testbot.runtime.databinding;

import com.github.anthonime.testbot.definitions.data.ObjectInstance;

/**
 * Map an ObjectInstance to a String, and vice versa.
 */
public interface DataMapper {

    /**
     * Format the given instance to a string
     *
     * @param instance
     * @return
     */
    String format(ObjectInstance instance);

    /**
     * Parse the given text to populate (partially) the given instance.
     *
     * @param text
     * @param instance
     */
    void parse(String text, ObjectInstance instance);
}
