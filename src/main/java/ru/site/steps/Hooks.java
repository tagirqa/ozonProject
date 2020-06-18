package ru.site.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import ru.site.product.VirtualBasket;
import ru.site.utils.ReportHelper;

public class Hooks {
    @Before
    public void setUp(){
        BaseSteps.setUp();
    }

    @After
    public void afterEach(){
        VirtualBasket.getBasket().forEach(x-> ReportHelper.addTextAttach(x.getName() + " цена:" + x.getPrice()));
    }

    public void tearDown(){

    }
}
