package ru.site.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import ru.site.product.VirtualBasket;
import ru.site.utils.ReportHelper;

import java.util.ArrayList;

public class Hooks {
    @Before
    public void setUp(){
        BaseSteps.setUp();
    }

    @After
    public void Products(){
        VirtualBasket.getBasket().forEach(x-> ReportHelper.addTextAttach(x.getName() + " цена:" + x.getPrice()));
        VirtualBasket.setBasket(new ArrayList<>());
    }
    @After
    public void tearDown(){
        BaseSteps.tearDown();
    }
}
