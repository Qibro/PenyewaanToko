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
import wecount.Controller.KoneksiControl;
import wecount.Model.Penyewa;
import wecount.View.ViewLogin;

/**
 *
 * @author ACER
 */
public class AutentikasiControl {
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
    
    public static String getMD5(String input){
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
    
    public AutentikasiControl(String nama, String username, String password, String alamat, String noTelp){
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.noTelp = noTelp;
        conn = KoneksiControl.koneksiDatabase();
    }
    public AutentikasiControl(String username, String password) {
        this.username = username;
        this.password = password;
        conn = KoneksiControl.koneksiDatabase();
    }
    public AutentikasiControl() {
        conn = KoneksiControl.koneksiDatabase();
    }
    
    public void validateRegisterAkun(){
        if(conn != null){
        try{
            String queryAkun = "INSERT INTO tb_akun(username,password) VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(queryAkun);
            ps.setString(1, username);
            ps.setString(2, password );
            int hasil = ps.executeUpdate();
        }catch(SQLException e){
            Logger.getLogger(ViewLogin.class.getName()).log(Level.SEVERE,null,e);
            }
        }
    }
    
    public int getIdAkun(String username){
        int id = 0;
        if(conn != null){
            try{
                String query = "SELECT id_akun FROM tb_akun WHERE username = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    id = rs.getInt("id_akun");
                    System.out.println(id);
                }
            }catch(SQLException e){
                Logger.getLogger(ViewLogin.class.getName()).log(Level.SEVERE,null,e); 
            }
        }
        return id;
    }
    
    public void validateRegisterPenyewa(String username){
        if(conn != null){
            try{
                String query = "INSERT INTO tb_penyewa(id_akun,nama_lengkap,alamat,no_telepon) VALUES(?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, getIdAkun(username));
                ps.setString(2, nama);
                ps.setString(3, alamat);
                ps.setString(4, noTelp);
                int hasil = ps.executeUpdate();
            }catch(SQLException e){
                Logger.getLogger(ViewLogin.class.getName()).log(Level.SEVERE,null,e);
            }
        }
    }
    
    public boolean cekAdmin(){
        if(username.equals("admin") && password.equals(getMD5("admin")))return true;
        else return false;
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
            Logger.getLogger(ViewLogin.class.getName()).log(Level.SEVERE,null,e);
            }
        }
        return loginConfirm;
    }
    
    public void changePassword(String newPass){
        if(conn != null){
            try{
                AutentikasiControl penyewa = current();
                System.out.println(penyewa.getUsername());
                String query = "UPDATE tb_akun SET password = ? where username = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, newPass);
                ps.setString(2, penyewa.getUsername());
                int hasil = ps.executeUpdate();
            }catch(SQLException e){
                Logger.getLogger(ViewLogin.class.getName()).log(Level.SEVERE,null,e);
            }
        }
    }
    
    public Penyewa current(){
        Penyewa penyewa = null;
        if(conn != null){
            try{           
                String queryAkun = "SELECT * FROM tb_akun WHERE username = ? AND password = ?";
                String queryPenyewa = "SELECT * FROM tb_penyewa WHERE id_penyewa = ?";  
                PreparedStatement psPenyewa = conn.prepareStatement(queryPenyewa);
                PreparedStatement psAkun = conn.prepareStatement(queryAkun);
                psPenyewa.setString(1, username);
                psAkun.setInt(1, getIdAkun(username));
                psAkun.setString(2, password);
                ResultSet rsPenyewa = psPenyewa.executeQuery();
                ResultSet rsAkun = psAkun.executeQuery();
                if(rsPenyewa.next() && rsAkun.next()){
                    penyewa = new Penyewa(rsPenyewa.getInt("id_penyewa"),rsPenyewa.getString("nama_lengkap"),rsAkun.getString("username"),rsAkun.getString("password"),rsPenyewa.getString("alamat"),rsPenyewa.getString("no_telp"));                    
                }
            }catch(SQLException e){
                Logger.getLogger(ViewLogin.class.getName()).log(Level.SEVERE,null,e);
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
                Logger.getLogger(ViewLogin.class.getName()).log(Level.SEVERE,null,e);
            }
        }
        return cekTrue;
    }

}
