package ru.site.steps;

import cucumber.api.java.en.When;
import org.junit.Assert;
import ru.site.pages.CartPage;

public class CartSteps {
    CartPage cartPage = new CartPage();

    @When("проверяем товар в корзине")
    public void checkCart(){
        cartPage.loadProductsCart();
        cartPage.sortCart();
        cartPage.equalsCartAndVirtualBasket();
    }

    @When("очищаем корзину")
    public void clearingCart(){
        cartPage.delCart();
    }

    @When("проверка очищена ли корзина")
    public void ClearCart(){
        Assert.assertTrue("не очищена", cartPage.checkOutProductCart());
    }

    @When("проверка надписи Ваша корзина \"(.*)\"")
    public void checkCountProducts(String text){
        Assert.assertEquals("надпись не соответствует!", cartPage.checkCountCartProducts(), text);
        cartPage.checkCountCartProducts();
    }

}
