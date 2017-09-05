package com.github.anthonime.testbot.support;

import com.github.anthonime.testbot.definitions.applications.ApplicationDefinition;
import com.github.anthonime.testbot.runtime.applications.Application;
import com.github.anthonime.testbot.runtime.applications.ApplicationFactory;
import com.github.anthonime.testbot.runtime.elements.WebPageContext;
import com.github.anthonime.testbot.runtime.elements.webdriver.WebPageContextImpl;
import org.junit.Before;

/**
 * Created by schio on 9/16/2017.
 */
public abstract class AbstractApplicationTest extends AbstractWebDriverTest {

    protected ApplicationFactory applicationFactory;
    protected Application application;

    public AbstractApplicationTest() {
    }

    public AbstractApplicationTest(boolean singletonDriver) {
        super(singletonDriver);
    }

    @Before
    public void createApplicationBeforeTest() throws Exception {
        if (applicationFactory == null) {
            this.applicationFactory = new ApplicationFactory(createContext());
        }

        if (applicationFactory != null) {
            this.application = applicationFactory.create(getApplicationDefinition());
        }
    }

    protected abstract ApplicationDefinition getApplicationDefinition();

    private WebPageContext createContext() {
        return new WebPageContextImpl(getDriver());
    }
}
