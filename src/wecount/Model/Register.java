/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wecount.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import wecount.View.Koneksi;
import wecount.View.JLogin;

/**
 *
 * @author ACER
 */
public class Register {
    Connection conn;
    private String nama;
    private String username;
    private String password;
    private String alamat;
    private String noTelp;
    
    public Register(String nama, String username, String password, String alamat, String noTelp) {
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.noTelp = noTelp;
        conn = Koneksi.koneksiDatabase();
    }
    
    public void validateRegister(){
        if(conn != null){
        try{
            String query = "INSERT INTO tb_akun(username,password,nama,alamat,no_telp,statusAktif,status) VALUES(?,?,?,?,?,1,1)";
            PreparedStatement ps = conn.prepareStatement(query);      
            ps.setString(1, nama);
            ps.setString(2, username);
            ps.setString(3, alamat);
            ps.setString(4, alamat);
            ps.setString(5, noTelp);
            int hasil = ps.executeUpdate();
        }catch(SQLException e){
            Logger.getLogger(JLogin.class.getName()).log(Level.SEVERE,null,e);
            }
        }
    }
}
