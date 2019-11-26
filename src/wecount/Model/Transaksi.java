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
public class Transaksi extends Toko{
    int idTransaksi;
    int idToko;
    int idPenyewa;
    String jumlahTransaksi;
    String tanggalTranskasi;

    public Transaksi(int idTransaksi,int idToko, int idPenyewa, String jumlahTransaksi, String tanggalTranskasi) {
        this.idTransaksi = idTransaksi;
        this.idToko = idToko;
        this.idPenyewa = idPenyewa;
        this.jumlahTransaksi = jumlahTransaksi;
        this.tanggalTranskasi = tanggalTranskasi;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public int getIdToko() {
        return idToko;
    }

    public int getIdPenyewa() {
        return idPenyewa;
    }
    
    public String getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public String getTanggalTranskasi() {
        return tanggalTranskasi;
    }
}
