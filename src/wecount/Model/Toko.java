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
public class Toko{
    int idToko;
    String namaToko;
    String blokToko;
    String ukuranToko;
    int status_ketersediaan;
    int hargaSewa;

    public Toko(int idToko, String namaToko, String blokToko,String ukuranToko, int hargaSewa,int status_ketersediaan) {
        this.idToko = idToko;
        this.namaToko = namaToko;
        this.ukuranToko = ukuranToko;
        this.blokToko = blokToko;
        this.status_ketersediaan = status_ketersediaan;
        this.hargaSewa = hargaSewa;
    }

    public int getIdToko() {
        return idToko;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public String getBlokToko() {
        return blokToko;
    }

    public String getUkuranToko() {
        return ukuranToko;
    }

    public int getStatus_ketersediaan() {
        return status_ketersediaan;
    }

    public double getHargaSewa() {
        return hargaSewa;
    }
    
}