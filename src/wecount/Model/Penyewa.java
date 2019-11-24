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
    int idPenyewa;
    String namaPenyewa;
    String alamat;
    String noTelp;
    ArrayList<Toko> listToko = new ArrayList<>();
    public Penyewa(int idPenyewa,String namaPenyewa, String username, String password, String alamat, String noTelp) {
        super(namaPenyewa,username,password,alamat,noTelp);
        this.idPenyewa = idPenyewa;
        this.idPenyewa = idPenyewa;
        this.namaPenyewa = namaPenyewa;
        this.alamat = alamat;
        this.noTelp = noTelp;
    }

    public int getIdPenyewa() {
        return idPenyewa;
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
