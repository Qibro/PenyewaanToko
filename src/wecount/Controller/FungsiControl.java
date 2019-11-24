/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wecount.Controller;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import wecount.View.Admin;
import wecount.View.JLogin;

/**
 *
 * @author ACER
 */
public class FungsiControl {
    Connection conn;

    public FungsiControl() {
        conn = Koneksi.koneksiDatabase();
    }
    
    public void tambahLapak(String ukuran,String lokasi,String harga_sewa){
        if(conn != null){
            try{
            String query = "INSERT INTO tb_toko(nama_toko,ukuran_toko,blok_toko,harga_sewa,status_sedia) VALUES(?,?,?,?,1)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "");
            ps.setString(2, ukuran);
            ps.setString(3, lokasi);
            ps.setString(4, harga_sewa);
            int hasil = ps.executeUpdate();
            }catch(SQLException e){
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE,null,e);
        }
      }
    }
}
