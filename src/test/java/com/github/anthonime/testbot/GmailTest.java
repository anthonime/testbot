package com.github.anthonime.testbot;

import com.github.anthonime.testbot.definitions.data.*;
import com.github.anthonime.testbot.definitions.databinding.DataMapperDefinition;
import com.github.anthonime.testbot.definitions.databinding.DataMapperType;
import com.github.anthonime.testbot.definitions.databinding.ElementTitleExtractorDefinition;
import com.github.anthonime.testbot.definitions.elements.*;
import com.github.anthonime.testbot.definitions.states.*;
import com.github.anthonime.testbot.runtime.data.DataContext;
import com.github.anthonime.testbot.runtime.databinding.DataMapperFactory;
import com.github.anthonime.testbot.runtime.databinding.DataMapperProvider;
import com.github.anthonime.testbot.runtime.databinding.DataMapperProviderImpl;
import com.github.anthonime.testbot.runtime.databinding.DataMapperRepository;
import com.github.anthonime.testbot.runtime.elements.*;
import com.github.anthonime.testbot.runtime.elements.impl.ElementFactoryImpl;
import com.github.anthonime.testbot.runtime.elements.webdriver.WebElementProviderFactoryImpl;
import com.github.anthonime.testbot.runtime.elements.webdriver.WebPageContextImpl;
import com.github.anthonime.testbot.runtime.states.ElementStateActivatorProvider;
import com.github.anthonime.testbot.runtime.states.ElementValueExtractorProvider;
import com.github.anthonime.testbot.runtime.states.StateActivator;
import com.github.anthonime.testbot.runtime.states.StateActivatorImpl;
import com.github.anthonime.testbot.support.AbstractWebDriverTest;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by schio on 9/12/2017.
 */
public class GmailTest extends AbstractWebDriverTest {


    public GmailTest() {
        super(false);
    }

    private static class User {
        String email;
        String password;
        String firstName;
        String lastName;

        public User(String email, String password, String firstName, String lastName) {
            this.email = email;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    private static final User USER1 = new User(System.getProperty("gmail_account"), System.getProperty("gmail_password"), "Build", "Kronos");


    private ElementRepository elementRepository;

    private ButtonDefinition nextButton;
    private ContainerElementDefinition containerElementDefinition;
    private StateActivator stateActivator;
    private StateRepository stateRepository;
    private ButtonDefinition optionsButton;
    private ButtonDefinition forgotEmailButton;
    private ButtonDefinition accountButton;
    private InputDefinition emailInput;
    private InputDefinition passwordInput;
    private ContainerElementDefinition googleBar;
    private DataContext dataContext;

    private ObjectDefinitionRepository definitionRepository;
    private ObjectDefinitionFactory definitionFactory;
    private ObjectInstanceIdentifierGenerator instanceIdentiferGenerator;
    private ObjectInstanceFactory instanceFactory;
    private ObjectInstanceRepository instanceRepository;
    private ElementStateActivatorProvider elementStateActivatorProvider;

    private ObjectInstance userInstance;
    @Before
    public void setUp() throws Exception {
        WebPageContextImpl context = new WebPageContextImpl(driver);

        definitionRepository = new ObjectDefinitionRepository();
        definitionFactory = new ObjectDefinitionFactory(definitionRepository);
        instanceRepository = new ObjectInstanceRepository();
        instanceIdentiferGenerator = new ObjectInstanceIdentifierGenerator();
        instanceFactory = new ObjectInstanceFactory(definitionFactory, instanceRepository, instanceIdentiferGenerator);
        userInstance = instanceFactory.fromObject(USER1);

        ElementFactory elementFactory = new ElementFactoryImpl(new WebElementProviderFactoryImpl(context));

        elementRepository = new ElementRepository(elementFactory, createElementDefinitions());
        stateRepository = new StateRepository(createStateDefinitions());
        dataContext = new DataContext(instanceRepository);
        DataMapperProvider dataMapperProvider = new DataMapperProviderImpl(new DataMapperRepository(), new DataMapperFactory(definitionRepository));
        elementStateActivatorProvider = new ElementStateActivatorProvider(dataMapperProvider, new ElementValueExtractorProvider());
        stateActivator = new StateActivatorImpl(context, elementRepository, stateRepository, elementStateActivatorProvider);


    }


    private List<StateDefinition> createStateDefinitions() {
        List<StateDefinition> definitions = new ArrayList<>();

        URLStateDefinition urlStateDefinition = null;
        definitions.add(new StateDefinition("notLoggedIn",
                urlStateDefinition, new VisibilityStateDefinition(containerElementDefinition.getIdentifier(), VisibilityState.VISIBLE)));

        definitions.add(new StateDefinition("enteringEmail", "notLoggedIn",
                urlStateDefinition, new VisibilityStateDefinition(containerElementDefinition.getIdentifier(), VisibilityState.VISIBLE),
                new VisibilityStateDefinition(nextButton.getIdentifier(), VisibilityState.VISIBLE),
                new VisibilityStateDefinition(forgotEmailButton.getIdentifier(), VisibilityState.VISIBLE),
                new VisibilityStateDefinition(emailInput.getIdentifier(), VisibilityState.VISIBLE),
                new VisibilityStateDefinition(optionsButton.getIdentifier(), VisibilityState.VISIBLE)));

        definitions.add(new StateDefinition("enteringPassword", "notLoggedIn",
                urlStateDefinition, new VisibilityStateDefinition(containerElementDefinition.getIdentifier(), VisibilityState.VISIBLE),
                new VisibilityStateDefinition(nextButton.getIdentifier(), VisibilityState.VISIBLE),
                new VisibilityStateDefinition(forgotEmailButton.getIdentifier(), VisibilityState.INVISIBLE),
                new VisibilityStateDefinition(passwordInput.getIdentifier(), VisibilityState.VISIBLE),
                new VisibilityStateDefinition(optionsButton.getIdentifier(), VisibilityState.INVISIBLE)));


        definitions.add(new StateDefinition("choosingAccountToLogWith", "notLoggedIn",
                urlStateDefinition, new VisibilityStateDefinition(containerElementDefinition.getIdentifier(), VisibilityState.VISIBLE),
                new VisibilityStateDefinition(nextButton.getIdentifier(), VisibilityState.INVISIBLE),
                new VisibilityStateDefinition(forgotEmailButton.getIdentifier(), VisibilityState.INVISIBLE),
                new VisibilityStateDefinition(optionsButton.getIdentifier(), VisibilityState.INVISIBLE)));

        //TODO : define it as opposed to notLoggedIn (exclusive)
        definitions.add(new StateDefinition("loggedIn",
                urlStateDefinition, new VisibilityStateDefinition(googleBar.getIdentifier(), VisibilityState.VISIBLE),
                new VisibilityStateDefinition(accountButton.getIdentifier(), VisibilityState.VISIBLE),
                new VisibilityStateDefinition(containerElementDefinition.getIdentifier(), VisibilityState.INVISIBLE)
        ));
        //define loggedInAs as substate with a model!
        //the model is a class ? the state definition can depends on an instance of this class
        definitions.add(new StateDefinition("loggedInAs", "loggedIn",
                urlStateDefinition, new VisibilityStateDefinition(accountButton.getIdentifier(), VisibilityState.VISIBLE),
                new DataBindedElementStateDefinition(accountButton.getIdentifier(),
                        ElementTitleExtractorDefinition.INSTANCE,
                        new DataMapperDefinition(userInstance.getDefinition().getIdentifier(), DataMapperType.TEMPLATE, "$firstName $lastName"))
        ));
        return definitions;
    }

    @NotNull
    private List<ElementDefinition> createElementDefinitions() {
        return Arrays.asList(
                nextButton = new ButtonDefinition("next"),
                optionsButton = new ButtonDefinition("options"),
                forgotEmailButton = new ButtonDefinition("Forgot email"),
                emailInput = new InputDefinition("email"),
                passwordInput = new InputDefinition("password"),
                containerElementDefinition = new ContainerElementDefinition("container", SelectorDefinition.id("view_container"),
                        nextButton, optionsButton, forgotEmailButton, emailInput, passwordInput),
                accountButton = new ButtonDefinition("Compte Google"),
                googleBar = new ContainerElementDefinition("googleBar", SelectorDefinition.id("gb"), accountButton)
        );
    }

    @Test
    public void notLoggedInState() {
        openGmail();
        assertThat(stateActivator.isActive(stateRepository.getStateDefinition(new StateIdentifier("notLoggedIn")), userInstance)).isTrue();
    }

    @Test
    public void loggedInState() {
        openGmail();
        login();
        assertThat(stateActivator.isActive(stateRepository.getStateDefinition("loggedIn"), userInstance)).isTrue();
    }

    @Test
    public void loggedInAsState() {
        openGmail();
        login();
        dataContext.setCurrent(userInstance);
        assertThat(stateActivator.isActive(stateRepository.getStateDefinition("loggedInAs"), userInstance)).isTrue();
    }

    private void login() {
        loginAs(USER1);
    }

    @Test
    public void enteringEmail() {
        openGmail();
        assertThat(stateActivator.isActive(stateRepository.getStateDefinition(new StateIdentifier("enteringEmail")), userInstance)).isTrue();
    }

    @Test
    public void choosingAccountToLogwith() {
        openGmail();
        assertThat(stateActivator.isActive(stateRepository.getStateDefinition(new StateIdentifier("choosingAccountToLogWith")), userInstance)).isFalse();
    }

    @Test
    public void googleAccountButton() {
        openGmail();
        login();
        ClickableElement element = elementRepository.get(accountButton.getIdentifier());
        assertThat(element.isVisible()).isTrue();
        assertThat(element.getTitleAttribute()).containsIgnoringCase("Build Kronos");
    }

    private void loginAs(User user) {
        elementRepository.<TextInputElement>get(emailInput.getIdentifier()).type(user.email);
        elementRepository.<ClickableElement>get(nextButton.getIdentifier()).click();
        sleepSec(1);
        elementRepository.<TextInputElement>get(passwordInput.getIdentifier()).type(user.password);
        elementRepository.<ClickableElement>get(nextButton.getIdentifier()).click();
        sleepSec(1);
    }

    private void sleepSec(int i) {
        try {
            sleep(1000 * i);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void nextButton() throws Exception {
        Element element = elementRepository.get(nextButton.getIdentifier());
        openGmail();
        assertThat(element).isNotNull()
                .isInstanceOf(ClickableElement.class)
                .matches(e -> e.isVisible())
                .matches(e -> e.isPresent());

        ((ClickableElement) element).click();

    }

    @Test
    public void forgetEmailButton() throws Exception {
        Element element = elementRepository.get(forgotEmailButton.getIdentifier());
        openGmail();
        assertThat(element).isNotNull()
                .isInstanceOf(ClickableElement.class)
                .matches(e -> e.isPresent(), "is present")
                .matches(e -> e.isVisible(), "is visible")
        ;

        ((ClickableElement) element).click();

    }

    @Test
    public void formContainer() throws Exception {

        Element element = elementRepository.get(containerElementDefinition.getIdentifier());
        openGmail();
        assertThat(element).isNotNull()
                .isInstanceOf(ContainerElement.class)
                .matches(e -> e.isVisible())
                .matches(e -> e.isPresent());


        assertThat(((ContainerElement) element).getDescendants())
                .hasSize(5)
                .allMatch(c -> c.isVisible());
    }

    private void openGmail() {
        driver.get("http://www.gmail.com");
        //driver.get("http://localhost:63342/testbot/testbot_test/Gmail.html?_ijt=on65b4e8692vo6krhq9qetdvbt");
    }


}
