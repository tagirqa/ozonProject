package ru.site.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultPage extends BasePage {

    @FindBy(xpath = "//input[@qa-id='range-to'][1]")
    public WebElement priceField;

    @FindBy(xpath = "//span[contains(text(), 'NFC')]/../preceding-sibling::div")
    public WebElement checkboxNFC;

    @FindBy(xpath = "//div[@value='Высокий рейтинг']")
    public WebElement buttonHighRating;

    @Override
    public WebElement getField(String name) throws Exception {
        return null;
    }
}
