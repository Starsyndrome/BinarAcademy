package org.binaracademy.repository;

import org.binaracademy.model.ProductMenu;

import java.util.Arrays;
import java.util.List;

public class ProductRepository {
    public static List<ProductMenu> menuList(){
        ProductMenu productMenu1 = new ProductMenu(1, "Es Kopi Susu", 15000);
        ProductMenu productMenu2 = new ProductMenu(2, "Cappuccino", 20000);
        ProductMenu productMenu3 = new ProductMenu(3, "Americano", 12000);
        ProductMenu productMenu4 = new ProductMenu(4, "Cookies\t", 10000);
        ProductMenu productMenu5 = new ProductMenu(5, "Croissant", 12000);

        return Arrays.asList(productMenu1, productMenu2, productMenu3, productMenu4, productMenu5);
    }
}
