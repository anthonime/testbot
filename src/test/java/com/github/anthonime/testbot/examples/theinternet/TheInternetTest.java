package com.github.anthonime.testbot.examples.theinternet;

import com.github.anthonime.testbot.definitions.actions.ActionDefinitionList;
import com.github.anthonime.testbot.definitions.actions.Actions;
import com.github.anthonime.testbot.definitions.applications.ApplicationDefinition;
import com.github.anthonime.testbot.runtime.actions.ActionResultType;
import com.github.anthonime.testbot.runtime.applications.exceptions.UnknownEnvironmentException;
import com.github.anthonime.testbot.support.AbstractApplicationTest;
import org.junit.Test;

import java.time.Duration;

import static com.github.anthonime.testbot.examples.theinternet.TheInternet.THE_INTERNET;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by schio on 9/16/2017.
 */
public class TheInternetTest extends AbstractApplicationTest {

    @Override
    protected ApplicationDefinition getApplicationDefinition() {
        return THE_INTERNET.applicationDefinition;
    }

    @Test
    public void openAndActiveEnvironment() throws Exception {
        //open the app
        assertThat(application.isOpen()).isFalse();
        assertThat(application.isOpen("root")).isFalse();
        assertThatThrownBy(() -> application.isOpen("unknown")).isInstanceOf(UnknownEnvironmentException.class)
                .hasMessageContaining("unknown");
        assertThat(application.getActiveEnvironment()).isNull();
        application.open();
        //assert active states
        assertThat(application.isOpen()).isTrue();
        assertThat(application.isOpen("root")).isTrue();
        assertThatThrownBy(() -> application.isOpen("unknown")).isInstanceOf(UnknownEnvironmentException.class)
                .hasMessageContaining("unknown");

        assertThat(application.getActiveEnvironment().getIdentifier()).isEqualTo("root");
        //
    }

    @Test
    public void dynamicControls() throws Exception {
        application.open();

        assertThat(application.getActiveStates(null)).extracting(s -> s.getDefinition())
                .containsOnly(THE_INTERNET.rootState, THE_INTERNET.homePage);

        application.operateTransition(THE_INTERNET.toDynamicControlsPage);

        assertThat(application.getActiveStates(null)).extracting(s -> s.getDefinition())
                .containsOnly(THE_INTERNET.rootState, THE_INTERNET.dynamicControlsPage, THE_INTERNET.dynamicControlsWithCheckbox);

        //perform a click on the remove button
        assertThat(application.performActions(
                new ActionDefinitionList(
                        Actions.click(THE_INTERNET.dynamicControlsRemoveButton),
                        Actions.pause(Duration.ofSeconds(3))
                )
        ).getResults()).extracting(r -> r.getType()).containsExactly(ActionResultType.SUCCESS, ActionResultType.SUCCESS);

        assertThat(application.getActiveStates(null)).extracting(s -> s.getDefinition())
                .containsOnly(THE_INTERNET.rootState, THE_INTERNET.dynamicControlsPage, THE_INTERNET.dynamicControlsWithoutCheckbox);

    }

}
