package ru.site.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.site.annotation.FieldName;
import ru.site.utils.DriverManager;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class BasePage {
    public WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 10);
    public Actions actions = new Actions(DriverManager.getDriver());

    public BasePage(){
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void waitElementVision(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitElementClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    public void scrollElement(WebElement element){
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("return arguments[0].scrollIntoView(false);", element);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public WebElement getField(String name, String className) throws Exception {
        Class example = Class.forName(className);
        List<Field> fields = Arrays.asList(example.getFields());
        for (Field field : fields){
            if (field.getAnnotation(FieldName.class).name().equals(name)){
                return DriverManager.getDriver().findElement(By.xpath(field.getAnnotation(FindBy.class).xpath()));
            }
        }
        Assert.fail("Не объявлен элемент с наименованием " + name);
        return null;
    }

    public abstract WebElement getField(String name) throws Exception;
}
