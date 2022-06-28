package org.agzw.brick.configuration;

import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class ConfigurationWebDriverTest {

    private ConfigurationWebDriver driver;

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() {
        driver = new ConfigurationWebDriver();
    }

    @Test
    public void whenPrepareTwoTabsThenShouldReturnTwoItems() throws Exception {
        // act
        List<String> tabs = driver.prepareTwoTabs();

        // assert
        collector.checkThat(tabs.size(), is(2));
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
