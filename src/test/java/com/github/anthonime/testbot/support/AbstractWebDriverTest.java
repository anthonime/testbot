package com.github.anthonime.testbot.support;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by schio on 9/12/2017.
 */
public class AbstractWebDriverTest {

    protected static WebDriver driver;
    protected final boolean singletonDriver;

    @BeforeClass
    public static void createChromeDriverManagerBeforeAllTests() {
        ChromeDriverManager.getInstance().setup();
    }

    public AbstractWebDriverTest() {
        this(true);
    }

    public AbstractWebDriverTest(boolean singletonDriver) {
        this.singletonDriver = singletonDriver;
    }

    @Before
    public void createDriverIfNecessaryBeforeTest() {
        if (driver == null)
            driver = new ChromeDriver();
    }


    @After
    public void quitDriverIfNecessaryAfterTest() {
        if (!singletonDriver && driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @AfterClass
    public static void quitDriverIfNecessaryAfterAllTests() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
