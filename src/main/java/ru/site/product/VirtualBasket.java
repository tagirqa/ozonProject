package ru.site.product;

import java.util.ArrayList;
import java.util.List;

public class VirtualBasket {
    private static List<Product> basket = new ArrayList<>();

    public static void setBasket(List<Product> basket) {
        VirtualBasket.basket = basket;
    }

    public static List<Product> getBasket() {
        return basket;
    }
}
