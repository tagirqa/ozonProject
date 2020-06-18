package ru.site.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.site.annotation.FieldName;

public class MainPage extends BasePage {

    @FindBy(xpath = "//input[@name='search']")
    public WebElement searchField;




    @Override
    public WebElement getFieldName(String name) throws Exception {
        return getField(name, "ru.site.pages.MainPage");
    }
}
