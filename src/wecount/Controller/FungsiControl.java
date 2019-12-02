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
import wecount.View.ViewMainMenu;

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
    //ubah
    public void tambahLapak(String ukuran,String lokasi,String harga_sewa){
        if(conn != null){
            try{
                String query = "INSERT INTO tb_toko(lokasi,ukuran,harga_sewa,durasi_sewa) VALUES(?,?,?,1)";//ubah
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
    //ubsh
     public void sewaToko(int id_penyewa,int id_toko, String nama_toko, String durasi_sewa, String harga_sewa,String denda,int status_beli){
        if(conn != null){
            try{
                //ubah
                String query = "UPDATE tb_toko SET id_penyewa = ?,nama_toko = ?, durasi_sewa =?, harga_sewa = ?,sisa_pembayaran = ?, status_bayar = ?, status_beli = 1 WHERE id_toko = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, id_penyewa);
                ps.setString(2, nama_toko);
                ps.setString(3, durasi_sewa);//tambah
                ps.setString(4, harga_sewa);
                ps.setString(5, denda);
                ps.setInt(6, status_beli);
                ps.setInt(7, id_toko);

                int hasil = ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Sewa Berhasil");
            }catch(SQLException e){
                Logger.getLogger(ViewMainMenu.class.getName()).log(Level.SEVERE,null,e);
            }
        }
    }
     
    
    public void simpanTransaksi(int id_toko,int id_penyewa,String jumlah_transaksi,String date){
         if(conn != null){
             try{
                 String query = "INSERT INTO tb_transaksi(id_toko,id_penyewa,jumlah_transaksi,tanggal_transaksi) VALUES(?,?,?,?)";
                 PreparedStatement ps = conn.prepareStatement(query);
                 ps.setInt(1, id_toko);
                 ps.setInt(2, id_penyewa);
                 ps.setString(3, jumlah_transaksi);
                 ps.setString(4,date);
                 int hasil = ps.executeUpdate();
             }catch(SQLException e ){
                 Logger.getLogger(ViewMainMenu.class.getName()).log(Level.SEVERE,null,e);
             }
         }
     }
}
