/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wecount.Controller;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import wecount.Model.Penyewa;
import wecount.Model.Toko;
import wecount.Model.Transaksi;
import wecount.View.Admin;

/**
 *
 * @author ACER
 */
public class TableControl {

    
    DefaultTableModel modelPenyewa,modelTransaksi,modelLapak;
    ArrayList<Penyewa> lPenyewa;
    ArrayList<Toko> lToko;
    ArrayList<Transaksi> lTransaksi;
    Connection conn;
    int countLapak;
    public TableControl() {
        modelPenyewa = new DefaultTableModel();
        modelTransaksi = new DefaultTableModel();
        modelLapak = new DefaultTableModel();
        conn = Koneksi.koneksiDatabase();
        this.countLapak = 0;
    }

    public int getCountLapak() {
        return countLapak;
    }
    
    public DefaultTableModel getModelPenyewa() {
        return modelPenyewa;
    }
    
    public DefaultTableModel getModelTransaksi() {
        return modelTransaksi;
    }

    public DefaultTableModel getModelLapak() {
        return modelLapak;
    }
    
    public void loadKolomPenyewa(){
        modelPenyewa.addColumn("Id Penyewa");
        modelPenyewa.addColumn("Username");
        modelPenyewa.addColumn("Nama Penyewa");
        modelPenyewa.addColumn("Alamat");
        modelPenyewa.addColumn("No Telpon");
        modelPenyewa.addColumn("Status");
    }
    
    public void loadKolomLapak(){
        modelLapak.addColumn("Lokasi");
        modelLapak.addColumn("Ukuran");
        modelLapak.addColumn("Harga Sewa");
        modelLapak.addColumn("Status");
    }
    
    public void showLapak(){
        modelPenyewa.setRowCount(0);
        for(Toko t : lToko){
            modelLapak.addRow(new Object[]{t.getBlokToko(),t.getUkuranToko(),t.getHargaSewa(),t.getStatus_ketersediaan()});
        }
        countLapak++;
    }
    
    public void showPenyewa(){
        modelPenyewa.setRowCount(0);
        for(Penyewa p : lPenyewa){
            modelPenyewa.addRow(new Object[]{p.getIdPenyewa(),p.getUsername(),p.getNama(),p.getAlamat(),p.getNoTelp()});
        }
    }
    
    public void loadPenyewa(){
        if(conn !=  null){
            try{
                String query = "SELECT * FROM tb_penyewa";
                lPenyewa = new ArrayList<>();
                PreparedStatement ps =  conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id_penyewa");
                    String nama = rs.getString("nama");
                    String alamat = rs.getString("alamat");
                    String noTelp = rs.getString("no_telp");
                    int status = rs.getInt("status");
                    Penyewa penyewa = new Penyewa(id,nama,username,password,alamat,noTelp);
                    lPenyewa.add(penyewa);
                }
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE,null,e);     
            }
        }
    }
    
    public void loadLapak(){
        if(conn !=  null){
            try{
                String query = "SELECT * FROM tb_toko";
                lToko = new ArrayList<>();
                PreparedStatement ps =  conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int idToko = rs.getInt("id_toko");
                    String ukuran = rs.getString("ukuran_toko");
                    String lokasi = rs.getString("blok_toko");
                    int hargaSewa = rs.getInt("harga_sewa");
                    int status = rs.getInt("status_sedia");
                    Toko toko = new Toko(idToko,"",lokasi,ukuran,hargaSewa,status);
                    lToko.add(toko);
                }
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE,null,e);     
            }
        }
    }
    
    
}
