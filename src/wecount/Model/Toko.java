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
    int idPenyewa;
    String namaToko;
    String lokasi;
    String ukuranToko;
    int durasiSewa;//tambah
    String sisa_pembayaran;
    int status_bayar;
    int status_ketersediaan;
    String hargaSewa;
    
    //ubah
    public Toko(int idToko,int idPenyewa,String namaToko, String lokasi,String ukuranToko, String hargaSewa, int durasiSewa,String sisa_pembayaran,int status_bayar) {
        super();
        this.idToko = idToko;
        this.idPenyewa = idPenyewa;
        this.namaToko = namaToko;
        this.ukuranToko = ukuranToko;
        this.lokasi = lokasi;
        this.status_ketersediaan = status_ketersediaan;
        this.hargaSewa = hargaSewa;
        this.durasiSewa = durasiSewa;//tambah
        this.sisa_pembayaran = sisa_pembayaran;
        this.status_bayar = status_bayar;
    }
    //ubah
    public Toko(int idToko , String lokasi,String ukuranToko, String hargaSewa) {
        this.idToko = idToko;
        this.ukuranToko = ukuranToko;
        this.lokasi = lokasi;
        this.hargaSewa = hargaSewa;
        this.durasiSewa =1;//tambah
    }
    
    public Toko(){
        
    }
    
    
    public String getSisa_pembayaran() {
        return sisa_pembayaran;
    }

    public int getStatus_bayar() {
        return status_bayar;
    }

    public int getIdToko() {
        return idToko;
    }
    
    public int getIdPenyewa(){
        return idPenyewa;
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
    
    //tambahan
    public int getDurasiSewa() {
        return durasiSewa;
    }
    
}