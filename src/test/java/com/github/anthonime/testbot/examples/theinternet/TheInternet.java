package com.github.anthonime.testbot.examples.theinternet;

import com.github.anthonime.testbot.definitions.actions.Actions;
import com.github.anthonime.testbot.definitions.applications.ApplicationDefinition;
import com.github.anthonime.testbot.definitions.applications.ApplicationDefinitionBuilder;
import com.github.anthonime.testbot.definitions.data.ObjectDefinitionRepository;
import com.github.anthonime.testbot.definitions.elements.ButtonDefinition;
import com.github.anthonime.testbot.definitions.elements.ContainerElementDefinition;
import com.github.anthonime.testbot.definitions.elements.SelectorDefinition;
import com.github.anthonime.testbot.definitions.states.StateDefinition;
import com.github.anthonime.testbot.definitions.transitions.TransitionDefinition;

import static com.github.anthonime.testbot.definitions.Definitions.application;
import static com.github.anthonime.testbot.definitions.elements.builders.Elements.*;
import static com.github.anthonime.testbot.definitions.states.builders.StateDefinitionBuilder.aState;
import static com.github.anthonime.testbot.definitions.states.builders.States.elementInvisible;
import static com.github.anthonime.testbot.definitions.states.builders.States.elementVisible;
import static com.github.anthonime.testbot.definitions.transitions.builders.TransitionDefinitionBuilder.aTransition;

/**
 * Created by schio on 9/16/2017.
 */
public class TheInternet {

    public static final TheInternet THE_INTERNET = new TheInternet();


    //DATA DEFS
    private ObjectDefinitionRepository objectDefinitions = new ObjectDefinitionRepository(
    );

    //ELEMENTS DEFS
    public ButtonDefinition dynamicControlsMenuItem;
    public ContainerElementDefinition mainMenu = body("mainMenu")
            .withChildren(
                    text("Welcome to the"),
                    dynamicControlsMenuItem = button("Dynamic Controls")
            )
            .build();

    public ButtonDefinition dynamicControlsRemoveButton = button("remove");
    public ButtonDefinition dynamicControlsAddButton = button("add");
    public ButtonDefinition dynamicControlsCheckbox = button("checkbox");
    public ContainerElementDefinition dynamicControls = container("dynamicControls")
            .withSelector(SelectorDefinition.css(".example"))
            .withChildren(
                    text("dynamicControlsTitle", "Dynamic Controls"),
                    dynamicControlsRemoveButton,
                    dynamicControlsAddButton,
                    dynamicControlsCheckbox
            )
            .build();


    //STATE DEFS
    public StateDefinition homePage = aState("home").atRoot().withAllElementsVisible(mainMenu).build();
    public StateDefinition dynamicControlsPage = aState("dynamic controls").at("/dynamic_controls").with(
            elementVisible(dynamicControls.getChild(0))
    ).build();
    public StateDefinition dynamicControlsWithCheckbox = aState("dynamic controls with checkbox").at("/dynamic_controls").with(
            elementVisible(dynamicControlsCheckbox),
            elementVisible(dynamicControlsRemoveButton),
            elementInvisible(dynamicControlsAddButton)
    ).build();
    public StateDefinition dynamicControlsWithoutCheckbox = aState("dynamic controls without checkbox").at("/dynamic_controls").with(
            elementInvisible(dynamicControlsCheckbox),
            elementInvisible(dynamicControlsRemoveButton),
            elementVisible(dynamicControlsAddButton)

    ).build();

    //TRANSITIONS
    public TransitionDefinition toDynamicControlsPage = aTransition("toDynamicControls")
            .from(homePage)
            .to(dynamicControlsPage)
            .by(Actions.click(dynamicControlsMenuItem))
            .build();
//
//    public TransitionDefinitionBuilder toDynamicControlsWithoutCheckboxBuilder = aTransition("toDynamicControlsWithoutCheckbox");
//    public TransitionDefinition toDynamicControlsWithCheckbox = aTransition("toDynamicControlsWithCheckbox")
//            .from("toDynamicControlsWithoutCheckbox")


    //APPLICATION DEFS
    private ApplicationDefinitionBuilder appBuilder = application("the-internet", "http://the-internet.herokuapp.com/")
            .withElements(mainMenu, dynamicControls)
            .withStates(homePage, dynamicControlsPage, dynamicControlsWithCheckbox, dynamicControlsWithoutCheckbox)
            .withObjectDefinitions(objectDefinitions)
            .withTransitions(toDynamicControlsPage);
    public ApplicationDefinition applicationDefinition = appBuilder.build();


    public StateDefinition rootState = applicationDefinition.getRootStateDefinition();
}
