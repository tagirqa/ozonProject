package ru.site.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
    @Before
    public void setUp(){
        BaseSteps.setUp();
    }

    @After
    public void tearDown(){
        BaseSteps.tearDown();
    }
}
