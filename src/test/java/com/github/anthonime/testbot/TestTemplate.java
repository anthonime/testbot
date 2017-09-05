package com.github.anthonime.testbot;

import com.github.anthonime.testbot.definitions.data.*;
import com.github.anthonime.testbot.definitions.databinding.DataMapperDefinition;
import com.github.anthonime.testbot.definitions.databinding.DataMapperType;
import com.github.anthonime.testbot.runtime.databinding.DataMapper;
import com.github.anthonime.testbot.runtime.databinding.DataMapperFactory;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by schio on 9/13/2017.
 */
public class TestTemplate {


    private static class Uzer {
        public String firstName;
        public String lastName;

        public Uzer(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    private ObjectDefinitionRepository definitionRepository;
    private ObjectDefinitionIdentifierFactory definitionIdentifierFactory;
    private ObjectDefinitionIdentifier uzerDefinitionId;
    private ObjectDefinitionFactory definitionFactory;
    private ObjectInstanceIdentifierGenerator instanceIdentiferGenerator;
    private ObjectInstanceFactory instanceFactory;
    private ObjectInstanceRepository instanceRepository;
    private ObjectInstance user1;

    @Before
    public void setUp() throws Exception {
        definitionIdentifierFactory = new ObjectDefinitionIdentifierFactory();
        definitionRepository = new ObjectDefinitionRepository();
        definitionFactory = new ObjectDefinitionFactory(definitionRepository);
        instanceRepository = new ObjectInstanceRepository();
        instanceIdentiferGenerator = new ObjectInstanceIdentifierGenerator();
        instanceFactory = new ObjectInstanceFactory(definitionFactory, instanceRepository, instanceIdentiferGenerator);
        uzerDefinitionId = definitionFactory.fromClass(Uzer.class).getIdentifier();
        user1 = instanceFactory.fromObject(new Uzer("Bob", "Dylon"));
    }

    @Test
    public void test_format() throws Exception {
        DataMapperDefinition definition = new DataMapperDefinition(user1.getDefinition().getIdentifier(), DataMapperType.TEMPLATE, "${firstName} ${lastName}");
        DataMapper template = createTemplate(definition);

        String formatted = template.format(user1);
        assertThat(formatted).isEqualTo("Bob Dylon");
    }

    @Test
    public void test_parse() throws Exception {
        DataMapperDefinition definition = new DataMapperDefinition(user1.getDefinition().getIdentifier(), DataMapperType.TEMPLATE, "${firstName} ${lastName}");
        DataMapper template = createTemplate(definition);

        template.parse("Bart Simpsung", user1);

        assertThat(user1).isEqualToIgnoringGivenFields(instanceFactory.fromObject(new Uzer("Bart", "Simpsung")), "identifier");
    }

    private DataMapper createTemplate(DataMapperDefinition definition) {
        DataMapperFactory templateProvider = createTemplateProvider();
        return templateProvider.get(definition);
    }

    private DataMapperFactory createTemplateProvider() {
        return new DataMapperFactory(definitionRepository);
    }
}
