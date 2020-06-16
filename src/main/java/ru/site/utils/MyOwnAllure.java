package ru.site.utils;

import cucumber.api.Result;
import cucumber.api.event.EventPublisher;
import cucumber.api.event.TestStepFinished;
import io.qameta.allure.cucumber3jvm.AllureCucumber3Jvm;

public class MyOwnAllure extends AllureCucumber3Jvm {
    @Override
    public void setEventPublisher(final EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, testStepFinished -> {
            if (testStepFinished.result.is(Result.Type.FAILED)) {
                AllureUtils.addScreenshot();
            }
        });
        super.setEventPublisher(publisher);
    }
}
