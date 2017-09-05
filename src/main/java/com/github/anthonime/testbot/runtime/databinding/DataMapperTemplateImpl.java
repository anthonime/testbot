package com.github.anthonime.testbot.runtime.databinding;

import com.github.anthonime.testbot.definitions.data.ObjectInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by schio on 9/13/2017.
 */
public class DataMapperTemplateImpl implements DataMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataMapperTemplateImpl.class);
    private final groovy.text.Template groovyTemplate;
    private final Pattern parsingPattern;
    private final List<String> placeholderNames;

    public DataMapperTemplateImpl(groovy.text.Template groovyTemplate, Pattern parsingPattern, List<String> placeholderNames) {
        this.groovyTemplate = groovyTemplate;
        this.parsingPattern = parsingPattern;
        this.placeholderNames = placeholderNames;
    }

    @Override
    public String format(ObjectInstance instance) {
        Map<String, Object> binding = new HashMap<>(instance.getProperties());
        return groovyTemplate.make(binding).toString();
    }

    @Override
    public void parse(String text, ObjectInstance instance) {
        //create a matcher from the regexp & the input text
        Matcher matcher = parsingPattern.matcher(text);
        //what if nothing match ? => not normal!
        if (matcher.matches()) {
            for (int index = 0; index < matcher.groupCount(); index++) {
                //for each match, get the corresponding placeholder name
                String matchedText = matcher.group(index + 1);
                String propertyName = placeholderNames.get(index);
                //set the Dynabean using the matched text value (type coercion may be necessary).
                instance.setProperty(propertyName, matchedText);
            }
        }
        //print warnings ?
    }

}
