/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wecount.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import wecount.Controller.Koneksi;
import wecount.View.JLogin;

/**
 *
 * @author ACER
 */
public class Autentikasi {
    Connection conn;
    private String nama;
    private String username;
    private String password;
    private String alamat;
    private String noTelp;
    
    public Autentikasi(String nama, String username, String password, String alamat, String noTelp){
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.noTelp = noTelp;
        conn = Koneksi.koneksiDatabase();
    }
    public Autentikasi(String username, String password) {
        this.username = username;
        this.password = password;
        conn = Koneksi.koneksiDatabase();
    }
    public Autentikasi() {
        conn = Koneksi.koneksiDatabase();
    }
    
    public void validateRegister(){
        if(conn != null){
        try{
            String query = "INSERT INTO tb_akun(username,password,nama,alamat,no_telp,statusAktif,status) VALUES(?,?,?,?,?,1,1)";
            PreparedStatement ps = conn.prepareStatement(query);      
            ps.setString(1, username);
            ps.setString(2, password );
            ps.setString(3, nama);
            ps.setString(4, alamat);
            ps.setString(5, noTelp);
            int hasil = ps.executeUpdate();
        }catch(SQLException e){
            Logger.getLogger(JLogin.class.getName()).log(Level.SEVERE,null,e);
            }
        }
    }
    
    public boolean validateLogin(){
        boolean loginConfirm = false;
        if(conn != null){
            try{
                String query = "SELECT * FROM tb_akun WHERE username = ? AND password = ?";           
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2 , password);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    loginConfirm = true;
                }
            }catch(SQLException e){
            Logger.getLogger(JLogin.class.getName()).log(Level.SEVERE,null,e);
            }
        }
        return loginConfirm;
    }
}