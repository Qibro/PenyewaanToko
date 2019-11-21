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
public class Penyewa {
    String username;
    String password;
    boolean tipePembayaranl;
    int masaSewa;
    ArrayList<Toko> listToko = new ArrayList<>();

    public Penyewa(String username, String password, boolean tipePembayaranl, int masaSewa) {
        this.username = username;
        this.password = password;
        this.tipePembayaranl = tipePembayaranl;
        this.masaSewa = masaSewa;
    }
    
    public void tambahSewaToko(){
        
    }
    
    public void bayarSewa(){
        
    }
    
    public void perpanjangSewa(){
        
    }
}
