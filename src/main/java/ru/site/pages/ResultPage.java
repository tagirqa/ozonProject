package ru.site.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.site.annotation.FieldName;
import ru.site.utils.DriverManager;

import java.util.ArrayList;
import java.util.List;

public class ResultPage extends BasePage {



    @FindBy(xpath = "//input[@qa-id='range-to'][1]")
    @FieldName(name = "максимальная цена")
    public WebElement priceMaxField;

    @FindBy(xpath = "//span[contains(text(), 'NFC')]/../preceding-sibling::div")
    @FieldName(name = "NFC")
    public WebElement checkboxNFC;

    @FindBy(xpath = "//div[@value='Высокий рейтинг']")
    @FieldName(name = "высокий рейтинг")
    public WebElement buttonHighRating;

    @FindBy(xpath = "//span[contains(text(), 'Посмотреть все')][1]")
    @FieldName(name = "посмотреть все бренды")
    public WebElement viewAll;

    @FindBy(xpath = "//span[@class='show']/../div/input")
    @FieldName(name = "найти бренд")
    public WebElement findBrand;

    @FindBy(xpath = "//a[@href='/cart']//span[1]")
    public WebElement cart;

    public List<WebElement> products = new ArrayList<>();

    public String productsXpath = "//div[contains(@style, 'grid-template')]//div[@style='grid-column-start: span 12;']";

    String waitBrand = "//span[contains(text(), 'Бренды: %s')]";

    String xpathBrand = "(//div[contains(text(), 'Бренды')]//..//div/following::input[@type='checkbox'])[1]/..";

    public void createList(){
        products = DriverManager.getDriver().findElements(By.xpath(productsXpath));
    }


    public void selectBrand(String text){
        WebElement button = DriverManager.getDriver().findElement(By.xpath(xpathBrand));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(xpathBrand, text))));
        button.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(waitBrand, text))));
    }

    public void waitElementRefreshing(WebElement element, String count) {

        wait.until(webDriver -> {

            return !count.equals(element.getText());
        });
    }


    @Override
    public WebElement getFieldName(String name) throws Exception {
        return getField(name, "ru.site.pages.ResultPage");
    }
}
