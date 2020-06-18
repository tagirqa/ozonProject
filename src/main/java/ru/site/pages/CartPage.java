package ru.site.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.site.product.Product;
import ru.site.product.VirtualBasket;
import ru.site.utils.DriverManager;

import java.util.Comparator;
import java.util.List;


public class CartPage extends BasePage {
    VirtualBasket virtualBasket = new VirtualBasket();

    public String waitCartLoadXpath = "//span[contains(text(),'Общая стоимость')]";
    public String productsXpath = "//span[@style='color: rgb(0, 26, 52);']";
    public String delCartXpath = "//span[contains(text(),'Удалить выбранные')]";
    public String delAcc = "//div[contains(text(), 'Удалить')]";
    public String loadAfterDelCart = "//div[contains(text(), 'Начать покупки')]";
    public String countProductsCart = "//span[contains(text(), '8 товаров')]";

    private static List<WebElement> list;

    public String checkCountCartProducts() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(countProductsCart)));
        String[] array = DriverManager.getDriver().findElement(By.xpath(countProductsCart)).getText().split(" ");
        return array[0] + " " + array[1];
    }

    public void loadProductsCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(waitCartLoadXpath)));
        list = DriverManager.getDriver().findElements(By.xpath(productsXpath));
    }

    public void sortCart() {
        VirtualBasket.getBasket().sort(Comparator.comparing(Product::getName));
        list.sort(Comparator.comparing(WebElement::getText));
    }

    public void equalsCartAndVirtualBasket() {
        for (int i = 0; i < VirtualBasket.getBasket().size(); i++) {
            Assert.assertEquals("Товар не найден!", VirtualBasket.getBasket().get(i).getName(), list.get(i).getText());
        }
    }

    public boolean checkOutProductCart() {
        try {
            DriverManager.getDriver().findElement(By.xpath(productsXpath));
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public void delCart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(delCartXpath))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(delAcc))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loadAfterDelCart)));
    }

    @Override
    public WebElement getFieldName(String name) throws Exception {
        return getFieldName(name + "ru.site.pages.CartPage");
    }
}
