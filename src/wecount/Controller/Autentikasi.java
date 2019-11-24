/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wecount.Controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import wecount.Controller.Koneksi;
import wecount.Model.Penyewa;
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

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }
    
    public String getMD5(String input){
         try { 
            MessageDigest md = MessageDigest.getInstance("MD5");   
            byte[] messageDigest = md.digest(input.getBytes());   
            BigInteger no = new BigInteger(1, messageDigest);  
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
    
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
    
    public void changePassword(String newPass){
        if(conn != null){
            try{
                Autentikasi penyewa = current();
                System.out.println(penyewa.getUsername());
                String query = "UPDATE tb_akun SET password = ? where username = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, newPass);
                ps.setString(2, penyewa.getUsername());
                int hasil = ps.executeUpdate();
            }catch(SQLException e){
                Logger.getLogger(JLogin.class.getName()).log(Level.SEVERE,null,e);
            }
        }
    }
    
    public Penyewa current(){
        Penyewa penyewa = null;
        if(conn != null){
            try{
                String query = "SELECT * FROM tb_akun WHERE username = ? AND password = ?";           
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2 , password);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    penyewa = new Penyewa(rs.getInt("id_akun"),rs.getString("nama"),rs.getString("username"),rs.getString("password"),rs.getString("alamat"),rs.getString("no_telp"));                    
                }
            }catch(SQLException e){
                Logger.getLogger(JLogin.class.getName()).log(Level.SEVERE,null,e);
            }
        }
        return penyewa;
    }
    
    public boolean checkPassword(){
        boolean cekTrue = false;
        if(conn != null){
            try{
                String query = "SELECT password FROM tb_akun WHERE username = ?";           
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                if(rs.getString("password") == password){
                    cekTrue = true;
                }
            }catch(SQLException e){
                Logger.getLogger(JLogin.class.getName()).log(Level.SEVERE,null,e);
            }
        }
        return cekTrue;
    }

}
