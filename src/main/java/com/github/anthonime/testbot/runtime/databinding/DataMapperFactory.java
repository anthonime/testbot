package com.github.anthonime.testbot.runtime.databinding;

import com.github.anthonime.testbot.definitions.data.ObjectDefinition;
import com.github.anthonime.testbot.definitions.data.ObjectDefinitionIdentifier;
import com.github.anthonime.testbot.definitions.data.ObjectDefinitionRepository;
import com.github.anthonime.testbot.definitions.databinding.DataMapperDefinition;
import com.github.anthonime.testbot.definitions.databinding.DataMapperType;
import com.google.common.base.Verify;
import groovy.lang.GString;
import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;
import groovy.text.TemplateEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by schio on 9/13/2017.
 */
public class DataMapperFactory implements DataMapperProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataMapperFactory.class);
    private static final String GSTRING_EXPRESSION = "\\$\\{([^\\}]*)\\}";
    private static final Pattern GSTRING_EXPRESSION_PATTERN = Pattern.compile(GSTRING_EXPRESSION);

    private static final String RELUCTANT_GRAB_TOKEN = "(.*?)";
    private static final String GREEDY_GRAB_TOKEN = "(.*)";

    private final TemplateEngine engine;
    private final ObjectDefinitionRepository repository;

    public DataMapperFactory(ObjectDefinitionRepository repository) {
        this.repository = repository;
        this.engine = new SimpleTemplateEngine();
    }

    @Override
    public DataMapper get(DataMapperDefinition dataMapperDefinition) {
        //extract placeholders from template in a List?DynaClass?
        //transform the template into a regexp

        DataMapperType type = dataMapperDefinition.getMapperType();
        switch (type) {
            case PROPERTY:
                return new DataMapperPropertyImpl(dataMapperDefinition.getMapping());
            case TEMPLATE:
                return createDataBinderTemplateImpl(dataMapperDefinition);
            default:
                throw new IllegalStateException("Cannot create databinder for type " + type);
        }
    }

    private DataMapperTemplateImpl createDataBinderTemplateImpl(DataMapperDefinition dataMapperDefinition) {
        final String stringTemplate = dataMapperDefinition.getMapping();
        List<String> expressions = findExpressions(stringTemplate);
        Verify.verify(expressions.size() > 0, "No expression detected in the template!");
        List<String> propertyNames = findPropertyNames(expressions);
        //check that all properties are defined by the

        Verify.verify(templatePropertiesExist(propertyNames, dataMapperDefinition.getDataType()), "Unknwon properties found (see error logs) in the template:" + stringTemplate);
        Template groovyTemplate = createGroovyTemplate(stringTemplate);
        //or is this a warning?
        Pattern parsingRegexp = generateParsingRegexp(expressions, stringTemplate);

        return new DataMapperTemplateImpl(groovyTemplate, parsingRegexp, propertyNames);
    }

    private boolean templatePropertiesExist(List<String> propertyNames, ObjectDefinitionIdentifier definitionIdentifier) {
        ObjectDefinition definition = repository.getDefinition(definitionIdentifier);
        return propertyNames.stream().filter(propertyName -> !definition.hasProperty(propertyName))
                .peek(p -> LOGGER.error("Unknown property {} in definition {}", p, definition.getIdentifier()))
                .count() == 0;
    }

    private List<String> findPropertyNames(List<String> expressions) {
        return expressions;
    }

    private Pattern generateParsingRegexp(List<String> expressions, String stringTemplate) {
        //replace each pattern with a reluctant token
        String result = stringTemplate.replaceAll(GSTRING_EXPRESSION, RELUCTANT_GRAB_TOKEN);
        //replace the last pattern with a greedy token
        result = replaceLast(result, Pattern.quote(RELUCTANT_GRAB_TOKEN), GREEDY_GRAB_TOKEN);
//        if(result.endsWith(GREEDY_GRAB_TOKEN)){
        //  result = result + "$";
//        }
        return Pattern.compile(result);
    }

    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }

    private GString evaluateTemplate(List<String> expressions, String stringTemplate) {
        return null;
    }

    private String replaceToken(String e) {
        return "${" + e + "}";
    }

    private List<String> findExpressions(String stringTemplate) {

        Matcher matcher = GSTRING_EXPRESSION_PATTERN.matcher(stringTemplate);
        List<String> expressions = new ArrayList<>();
        while (matcher.find()) {
            String expression = matcher.group(1);
            expressions.add(expression);
        }
        return expressions;
    }

    public groovy.text.Template createGroovyTemplate(String stringTemplate) {
        try {
            return engine.createTemplate(stringTemplate);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot create template from '" + stringTemplate + "'", e);
        }
    }

    public void evalu(String stringTemplate) {
//
//        Haha haha = new Haha();
//        haha.firstName = "yues!";
//        GString me = (GString) Eval.me("o", haha, "\"hello ${o.firstName}\"");
//        int valueCount = me.getValueCount();
//        for (int i = 0; i < valueCount; i++) {
//            Object val = me.getValue(i);
//            String s = me.getStrings()[i];
//            System.out.println(val + " " + s);
//        }
//        System.out.println(me);
    }
}
