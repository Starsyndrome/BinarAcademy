package org.binaracademy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductMenu {
    private int nomorMenu;
    private String namaMenu;
    private double hargaMenu;

    public ProductMenu(int nomorMenu, String namaMenu, double hargaMenu){
        this.nomorMenu = nomorMenu;
        this.namaMenu = namaMenu;
        this.hargaMenu = hargaMenu;
    }
}
