package ru.site.product;

import java.util.ArrayList;
import java.util.List;

public class VirtualBasket {
    private static List<Product> basket = new ArrayList<>();

    public static List<Product> getBasket() {
        return basket;
    }
}
