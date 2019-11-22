/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wecount.Model;

import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class Penyewa extends Autentikasi{
    String idPenyewa;
    String username;
    String password;
    String namaPenyewa;
    String alamat;
    String noTelp;
    boolean tipePembayaran;
    int masaSewa;
    ArrayList<Toko> listToko = new ArrayList<>();

    public Penyewa(String username,String password,String idPenyewa, String namaPenyewa, String alamat, String noTelp, boolean tipePembayaran, int masaSewa) {
        super(username,password);
        this.idPenyewa = idPenyewa;
        this.namaPenyewa = namaPenyewa;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.tipePembayaran = tipePembayaran;
        this.masaSewa = masaSewa;
    }

    public String getIdPenyewa() {
        return idPenyewa;
    }

    public String getUsername() {
        return username;
    }

    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public boolean isTipePembayaran() {
        return tipePembayaran;
    }

    public int getMasaSewa() {
        return masaSewa;
    }

    public ArrayList<Toko> getListToko() {
        return listToko;
    }    
    
    public void tambahSewaToko(){
        
    }
    
    public void bayarSewa(){
        
    }
    
    public void perpanjangSewa(){
        
    }
}
