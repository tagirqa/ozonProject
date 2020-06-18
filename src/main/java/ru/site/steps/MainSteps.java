package ru.site.steps;

import cucumber.api.java.en.When;
import org.openqa.selenium.Keys;
import ru.site.pages.BasePage;
import ru.site.pages.MainPage;

public class MainSteps {

    MainPage mainPage = new MainPage();

    @When("вводим в поисковую строку \"(.*)\"")
    public void inputSearch(String string) {
        mainPage.searchField.sendKeys(string + Keys.ENTER);
    }


}
