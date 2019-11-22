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
public class Toko extends Penyewa{
    String idToko;
    String namaToko;
    String blokToko;
    String deskripsiToko;
    boolean status_ketersediaan;
    double hargaSewa;

    public Toko(String idToko, String namaToko, String blokToko, String deskripsiToko, boolean status_ketersediaan, double hargaSewa, String username, String password, String idPenyewa, String namaPenyewa, String alamat, String noTelp, boolean tipePembayaran, int masaSewa) {
        super(username, password, idPenyewa, namaPenyewa, alamat, noTelp, tipePembayaran, masaSewa);
        this.idToko = idToko;
        this.namaToko = namaToko;
        this.blokToko = blokToko;
        this.deskripsiToko = deskripsiToko;
        this.status_ketersediaan = status_ketersediaan;
        this.hargaSewa = hargaSewa;
    }

    public String getIdToko() {
        return idToko;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public String getBlokToko() {
        return blokToko;
    }

    public String getDeskripsiToko() {
        return deskripsiToko;
    }

    public boolean isStatus_ketersediaan() {
        return status_ketersediaan;
    }

    public double getHargaSewa() {
        return hargaSewa;
    }
    
}