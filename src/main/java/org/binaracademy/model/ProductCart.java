package org.binaracademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCart {
    private ProductMenu productMenu;
    private int qty;
    private double totalHarga;

    public ProductCart(ProductMenu productMenu, int qty){
        this.productMenu = productMenu;
        this.qty = qty;
        this.setTotalHarga();
    }

    public void setTotalHarga() {
        this.totalHarga = productMenu.getHargaMenu() * qty;
    }

    public ProductMenu getProduct(){
       return productMenu;
    }
}