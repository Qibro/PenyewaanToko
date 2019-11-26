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
    int idToko;
    String namaToko;
    String lokasi;
    String ukuranToko;
    int status_ketersediaan;
    String hargaSewa;

    public Toko(int idToko,int idPenyewa,String namaToko, String lokasi,String ukuranToko, String hargaSewa) {
        super();
        this.idToko = idToko;
        this.namaToko = namaToko;
        this.ukuranToko = ukuranToko;
        this.lokasi = lokasi;
        this.status_ketersediaan = status_ketersediaan;
        this.hargaSewa = hargaSewa;
    }
    
     public Toko(int idToko , String lokasi,String ukuranToko, String hargaSewa) {
        this.idToko = idToko;
        this.ukuranToko = ukuranToko;
        this.lokasi = lokasi;
        this.hargaSewa = hargaSewa;
    }
    
    public Toko(){
        
    }

    public int getIdToko() {
        return idToko;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getUkuranToko() {
        return ukuranToko;
    }

    public int getStatus_ketersediaan() {
        return status_ketersediaan;
    }

    public String getHargaSewa() {
        return hargaSewa;
    }
    
}