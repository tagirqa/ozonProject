package ru.site.steps;

import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.site.pages.BasePage;
import ru.site.pages.ResultPage;
import ru.site.product.Product;
import ru.site.product.VirtualBasket;

import static ru.site.steps.BaseSteps.basePage;

public class ResultSteps {
    ResultPage resultPage = new ResultPage();

    @When("страница \"(.*)\" загружена")
    public void pageLoaded(String name) throws Exception {
        Class example = Class.forName("ru.site.pages." + name);
        resultPage.wait.until(ExpectedConditions.elementToBeClickable(resultPage.priceMaxField));
        basePage = (BasePage) example.newInstance();
    }

    @When("\"(.*)\" составляет \"(.*)\"")
    public void installPrice(String name, String price) throws Exception {
        basePage.maxPrice(name, price);
        resultPage.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resultPage.waitMaxPriceXpath)));
    }

    @When("выполнено нажатие на \"(.*)\"")
    public void click(String name) throws Exception {
        basePage.click(name);
        resultPage.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(resultPage.ratingWaitXpath)));
    }

    @When("выполнено нажатие на кнопку корзина")
    public void clickButton() {
        resultPage.cartButton.click();

    }

    @When("включаем высокий рейтинг")
    public void highRatingOn() {
        resultPage.wait.until(ExpectedConditions.elementToBeClickable(resultPage.buttonHighRating)).click();
        resultPage.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(resultPage.ratingWaitXpath)));
    }

    @When("включаем NFC")
    public void nfcOn() {
        resultPage.wait.until(ExpectedConditions.elementToBeClickable(resultPage.checkboxNFC)).click();
        resultPage.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(resultPage.nfcXpath)));
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

    @When("добавляем 8 нечетных товаров в корзину")
    public void addBasket() {
        basePage.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resultPage.productsXpath)));
        resultPage.createList();
        for (int i = 0; i < 16; i++) {
            int x = 0;
            if (i % 2 == 0) {
                try {
                    resultPage.products.get(i).findElement(By.xpath(".//div[contains(text(),'В корзину')]")).click();
                } catch (NoSuchElementException e) {
                    System.out.println("нечетный товар отсутствует, добавляю следующий четный");
                    resultPage.products.get(i + 1).findElement(By.xpath(".//div[contains(text(),'В корзину')]")).click();
                }
                waitRefresh(i, x);
                x++;
            }
            if (x == 8) break;
        }
    }

    public void waitRefresh(int i, int x) {
        resultPage.waitElementRefreshing(resultPage.cart, Integer.toString(x));
        WebElement element = resultPage.products.get(i);
        String name = element.findElement(By.xpath("." + resultPage.nameProductXpath)).getText();
        String price = element.findElement(By.xpath(resultPage.priceXpath)).getText();
        VirtualBasket.getBasket().add(new Product(name, price));
    }

    @When("добавляем все четные товары в корзину")
    public void addAllBasket() {
        basePage.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resultPage.productsXpath)));
        resultPage.createList();
        int x = 0;
        for (int i = 0; i < resultPage.products.size(); i++) {

            if (i % 2 != 0) {
                try {
                    resultPage.products.get(i).findElement(By.xpath(".//div[contains(text(),'В корзину')]")).click();
                } catch (NoSuchElementException e) {
                    System.out.println("товар недоступен");

                }
                waitRefresh(i, x);
                x++;
            }
        }
    }
}
