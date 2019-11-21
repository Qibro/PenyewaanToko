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
public class Transaksi {
    String idTransaksi;
    String idPegawai;
    String jenisTransaksi;

    public Transaksi(String idTransaksi, String idPegawai, String jenisTransaksi) {
        this.idTransaksi = idTransaksi;
        this.idPegawai = idPegawai;
        this.jenisTransaksi = jenisTransaksi;
    }
    
    public void prosesTransaksi(){
        
    }
    
    public void dendaSewa(){
        
    }
    
    public void cetakBuktiTransaksi(){
        
    }
}
