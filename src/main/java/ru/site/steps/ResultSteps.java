package ru.site.steps;

import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.site.pages.BasePage;
import ru.site.pages.ResultPage;

import java.util.List;

import static ru.site.steps.BaseSteps.basePage;

public class ResultSteps {
    ResultPage resultPage = new ResultPage();

    @When("страница \"(.*)\" загружена")
    public void pageLoaded(String name) throws Exception {
        Class example = Class.forName("ru.site.pages." + name);
        basePage = (BasePage)example.newInstance();
    }

    @When("\"(.*)\" составляет \"(.*)\"")
    public void installPrice(String name, String price) throws Exception {
        basePage.maxPrice(name, price);
    }

    @When("выполнено нажатие на \"(.*)\"")
    public void click(String name) throws Exception {
        resultPage.click(name);
    }

    @When("нажимаем \"(.*)\" в поле \"(.*)\" выбираем \"(.*)\"")
    public void inputBrand(String viewAll, String name, String text) throws Exception {
        basePage.waitElementClickable(viewAll);
        basePage.click(viewAll);
        basePage.inputText(name, text);
        resultPage.selectBrand(text);
    }
    @When("в поле \"(.*)\" выбираем \"(.*)\"")
    public void inputSecondBrand(String name, String text) throws Exception {
        basePage.waitElementClickable(name);
        basePage.inputText(name, text);
        resultPage.selectBrand(text);
    }

    @When("убираем первый товар в корзину")
    public void addBasket(){
        basePage.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resultPage.productsXpath)));
        resultPage.createList();
        for (int i = 0; i < 8; i++){
            int x = 0;
            if (i % 2 == 0){
                resultPage.products.get(i).findElement(By.xpath(".//div[contains(text(),'В корзину')]")).click();
                x++;
            }
        resultPage.waitElementRefreshing(resultPage.cart, Integer.toString(x));
        }

    }


}
