/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wecount.Model;

/**
 *
 * @author ACER
 */
public class Toko {
    String idToko;
    String namaToko;
    String blokToko;
    String deskripsiToko;
    boolean status_ketersediaan;
    double hargaSewa;

    public Toko(String idToko, String namaToko, String blokToko, String deskripsiToko, boolean status_ketersediaan, double hargaSewa) {
        this.idToko = idToko;
        this.namaToko = namaToko;
        this.blokToko = blokToko;
        this.deskripsiToko = deskripsiToko;
        this.status_ketersediaan = status_ketersediaan;
        this.hargaSewa = hargaSewa;
    }
}
