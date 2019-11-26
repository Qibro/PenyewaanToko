/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wecount.Model;

import wecount.Controller.AutentikasiControl;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class Penyewa extends AutentikasiControl{
    int idPenyewa;
    String namaPenyewa;
    String username;
    String alamat;
    String noTelp;
    ArrayList<Toko> listToko = new ArrayList<>();
    
    public Penyewa(int idPenyewa,String namaPenyewa, String username, String password, String alamat, String noTelp) {
        super(namaPenyewa,username,password,alamat,noTelp);
        this.idPenyewa = idPenyewa;
        this.username = username;
        this.namaPenyewa = namaPenyewa;
        this.alamat = alamat;
        this.noTelp = noTelp;
    }

    public Penyewa(int idPenyewa, String namaPenyewa, String alamat, String noTelp) {
        this.idPenyewa = idPenyewa;
        this.namaPenyewa = namaPenyewa;
        this.alamat = alamat;
        this.noTelp = noTelp;
    }

    public Penyewa() {
    }
    
    
    
    
    public String getUsername() {
        return username;
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
}
