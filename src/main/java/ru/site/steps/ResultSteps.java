package ru.site.steps;

import cucumber.api.java.en.When;
import org.openqa.selenium.Keys;
import ru.site.pages.ResultPage;

public class ResultSteps {
    ResultPage resultPage = new ResultPage();

    @When("Максимальная цена до {string}")
    public void maxPrice(String price){
        resultPage.waitElementClickable(resultPage.priceField);
        for (int i = 6; i > 0; i--){
            resultPage.priceField.sendKeys(Keys.BACK_SPACE);
            if (i == 1) resultPage.priceField.sendKeys(price);
        }

    }

    @When("Отметить чекбокс – Высокий рейтинг")
    public void clickButtonHighRating(){

        resultPage.click(resultPage.buttonHighRating);
    }

    @When("Отметить чекбокс – NFC")
    public void clickNFC(){

        resultPage.click(resultPage.checkboxNFC);
    }
}
