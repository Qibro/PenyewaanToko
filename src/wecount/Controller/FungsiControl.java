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
import javax.swing.JOptionPane;
import wecount.View.ViewAdmin;
import wecount.View.ViewLogin;

/**
 *
 * @author ACER
 */
public class FungsiControl {
    Connection conn;

    public FungsiControl() {
        conn = KoneksiControl.koneksiDatabase();
    }
    TableControl tc = new TableControl();
    
    public void ubahDataLapak(int id,String ukuran,String lokasi,String harga_sewa){
        if(conn != null){
            try{
                String query = "UPDATE tb_toko SET ukuran = ?, lokasi = ? , harga_sewa = ? Where id_toko = ?"; 
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, ukuran);
                ps.setString(2, lokasi);
                ps.setString(3, harga_sewa);
                ps.setInt(4, id);
                int hasil = ps.executeUpdate();
            }catch(SQLException e){
                Logger.getLogger(ViewAdmin.class.getName()).log(Level.SEVERE,null,e);
        }   
    }
}
    
    public void tambahLapak(String ukuran,String lokasi,String harga_sewa){
        if(conn != null){
            try{
                String query = "INSERT INTO tb_toko(lokasi,ukuran,harga_sewa) VALUES(?,?,?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, lokasi);
                ps.setString(2, ukuran);
                ps.setString(3, harga_sewa);
                int hasil = ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Input Berhasil");
                tc.loadLapak();
                tc.showLapak();
            }catch(SQLException e){
                Logger.getLogger(ViewAdmin.class.getName()).log(Level.SEVERE,null,e);
        }
      }
    }
}
