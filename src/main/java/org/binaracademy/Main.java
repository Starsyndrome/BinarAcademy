package org.binaracademy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner MBA = new Scanner(System.in);
        double totalPesanan = 0;
        int pilihanCust, jumlahPesanan = 0;
        String[] namaMenu = {"Es Kopi Susu", "Cappuccino", "French Fries", "Croissant"};
        double[] hargaMenu = {15000, 20000, 10000, 12000};
        String[] menuYangDipesan = new String[100];
        double[] hargaYangDipesan = new double[100];
        int[] qty = new int[100];
        halamanUtama();

        do{
            System.out.println("Silakan pilih menu yang ingin dipesan:");
            /** Melakukan looping untuk mencetak tampilan menu dan harga */
            for (int i = 0; i < namaMenu.length; i++){
                System.out.println(i+1 + ". " + namaMenu[i] + "\t | " + hargaMenu[i]);
            }
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar Aplikasi");
            System.out.println();
            System.out.print("Inputkan nomor yang tersedia pada menu: ");
            pilihanCust = MBA.nextInt();

            /** Melakukan pengkondisian if di mana jika user menginputkan nomor yang tersedia di menu
             * yang di mana jika lebih dari sama dengan 1 dan kurang sama dengan namaMenu.length (4),
             * maka akan menghasilkan input yang menanyakan jumlah makanan/minuman yang di pesan */
            if (pilihanCust >= 1 && pilihanCust <= namaMenu.length){
                System.out.print("Inputkan jumlahnya: ");
                jumlahPesanan = MBA.nextInt();
            }
            System.out.println();
            /** Melakukan kondisi switch case untuk lanjutan proses dari inputan user pada saat
             * user memilih menu pesanan */
            switch (pilihanCust){
                case 0:
                    /** Jika user menginputkan 0 pada saat memilih menu makanan atau minuman
                     * maka user akan keluar dari program  */
                    System.exit(0);
                    break;
                    /** Melakukan perulangan for untuk mengecek di mana jika array string menuYangDipesan = null
                     * maka akan menjalankan proses di bawahnya, lalu akan memanggil array namaMenu,
                     * array hargaMenu, dan array qty(jumlah) agar muncul ketika proses pembayaran,
                     * berlaku untuk case 1 sampai dengan case 4 */
                case 1:
                    for (int i = 0; i < menuYangDipesan.length; i++){
                        if (menuYangDipesan[i] == null){
                            menuYangDipesan[i] = namaMenu[0];
                            hargaYangDipesan[i] = hargaMenu[0];
                            qty[i] = jumlahPesanan;
                            break;
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < menuYangDipesan.length; i++) {
                        if (menuYangDipesan[i] == null) {
                            menuYangDipesan[i] = namaMenu[1];
                            hargaYangDipesan[i] = hargaMenu[1];
                            qty[i] = jumlahPesanan;
                            break;
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < menuYangDipesan.length; i++) {
                        if (menuYangDipesan[i] == null) {
                            menuYangDipesan[i] = namaMenu[2];
                            hargaYangDipesan[i] = hargaMenu[2];
                            qty[i] = jumlahPesanan;
                            break;
                        }
                    }
                    break;
                case 4:
                    for (int i = 0; i < menuYangDipesan.length; i++) {
                        if (menuYangDipesan[i] == null) {
                            menuYangDipesan[i] = namaMenu[3];
                            hargaYangDipesan[i] = hargaMenu[3];
                            qty[i] = jumlahPesanan;
                            break;
                        }
                    }
                    break;
                case 99:
                    /** Jika user menginputkan angka 99 dan bertemu break; di mana proses pemesanan akan berhenti,
                     * lalu proses selanjutnya yaitu menuju ke pembayaran  */
                    break;
                default:
                    /** Jika angka yang diinputkan user tidak sesuai dengan yang ada di dalam switch case
                     * maka user akan diminta untuk memasukkan angka yang tersedia*/
                    System.out.println("Inputkan angka sesuai yang ada di menu!");
                    break;
            }
                    /** Perulang akan terus terjadi selama inputan user tidak sama dengan 99
                     * Karena 99 merupakan pilihan untuk ke menu pembayaran*/
        }while(pilihanCust != 99);

        System.out.println("====================================");
        System.out.println("\t\t    Pembayaran    \t\t");
        System.out.println("====================================");
        System.out.println("No\t" + "Yang Dipesan\t" + "Harga\t" + "  Jumlah\t");
        /** Melakukan perulangan untuk mencetak/memanggil apa saja yang sudah kita pesan ke dalam menu pembayaran */
        for (int i = 0; i < menuYangDipesan.length; i++){
            if (menuYangDipesan[i] == null){
                break;
            }
            System.out.println(i+1 + ". " + menuYangDipesan[i] + "\t | " + hargaYangDipesan[i] + "\t | \t" + qty[i]);
            totalPesanan += hargaYangDipesan[i] * qty[i];
        }
        System.out.println();
        System.out.println("Totalnya yaitu: " + totalPesanan);
        write("C:/BEJIntelliJEdu/Challenge1/src/main/resources/struk.txt", menuYangDipesan, hargaYangDipesan, qty);
    }


    /** Menampilkan halaman utama dari Stars Coffee */
    public static void halamanUtama () {
        System.out.println("*******************************");
        System.out.println("Selamat datang di Stars Coffee!");
        System.out.println("*******************************");
        System.out.println();
    }
    /** Codingan untuk mencetak struk menjadi .txt yang di mana kita membawa for looping untuk mencetak
     * hasil apa saja yang kita beli di menu pembayaran dan dimasukkan ke dalam file txt*/
    public static void write(String targetFile, String[] menuYangDipesan, double[] hargaYangDipesan, int[] qty){
        try{
            double totalPesanan = 0;
            File file = new File(targetFile);
            if (file.createNewFile()){
                System.out.println("File berhasil dibuat");
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\t     Struk Pembayaran\t\n");
            bw.newLine();
            bw.write("No\t" + "Yang Dipesan\t" + "Harga\t" + "  Jumlah\t\n");
            for (int i = 0; i < menuYangDipesan.length; i++){
                if (menuYangDipesan[i] == null){
                    break;
                }
                bw.write(i+1 + ". " + menuYangDipesan[i] + "\t | " + hargaYangDipesan[i] + "\t | \t" + qty[i] +"\n");
                totalPesanan += hargaYangDipesan[i] * qty[i];
            }
            bw.newLine();
            bw.write("Totalnya yaitu: " + totalPesanan);
            bw.newLine();
            bw.write("Terima kasih sudah datang ke Stars Coffee");
            bw.flush();
            bw.close();
            System.out.println();
            System.out.println("Terima kasih sudah datang ke Stars Coffee!");
        } catch (IOException e){
            System.out.println("FIle gagal dibuat");;
        }
    }
}