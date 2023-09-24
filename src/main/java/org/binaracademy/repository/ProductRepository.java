package org.binaracademy.repository;

import org.binaracademy.model.ProductMenu;

import java.util.Arrays;
import java.util.List;

public class ProductRepository {
    public static List<ProductMenu> menuList() {
        ProductMenu produkMenu1 = new ProductMenu(1, "Es Kopi Susu", 15000);
        ProductMenu produkMenu2 = new ProductMenu(2, "Cappuccino", 20000);
        ProductMenu produkMenu3 = new ProductMenu(3, "Americano", 12000);
        ProductMenu produkMenu4 = new ProductMenu(4, "Cookies\t", 10000);
        ProductMenu produkMenu5 = new ProductMenu(5, "Croissant", 12000);

        return Arrays.asList(produkMenu1, produkMenu2, produkMenu3, produkMenu4, produkMenu5);
    }
}
