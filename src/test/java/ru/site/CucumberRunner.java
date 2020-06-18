package ru.site;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        glue = {"ru.site.steps"},
        features = {"src/test/resources"},
        tags = {"@all"},
        plugin = {"ru.site.utils.MyOwnAllure",
        "pretty"}
)

public class CucumberRunner {
}
