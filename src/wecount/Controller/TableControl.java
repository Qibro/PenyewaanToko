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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import wecount.Model.Admin;
import wecount.Model.Penyewa;
import wecount.Model.Toko;
import wecount.Model.Transaksi;
import wecount.View.ViewAdmin;

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
        conn = KoneksiControl.koneksiDatabase();
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
        modelPenyewa.addColumn("Nama Penyewa");
        modelPenyewa.addColumn("Alamat");
        modelPenyewa.addColumn("No Telpon");
    }
    
    public void loadKolomLapak(){
        modelLapak.addColumn("ID Lapak");
        modelLapak.addColumn("Lokasi");
        modelLapak.addColumn("Ukuran");
        modelLapak.addColumn("Harga Sewa");
    }
    
    public void loadKolomTransaksi() {
        modelTransaksi.addColumn("ID Transaksi");
        modelTransaksi.addColumn("ID Toko");
        modelTransaksi.addColumn("ID Penyewa");
        modelTransaksi.addColumn("Jumlah Transaksi");
        modelTransaksi.addColumn("Tanggal Transaksi");
    }
    
    public void showTransaksi(){
        modelTransaksi.setRowCount(0);
        for(Transaksi t : lTransaksi){
            modelTransaksi.addRow(new Object[]{t.getIdTransaksi(), t.getIdToko(), t.getIdPenyewa(), t.getJumlahTransaksi(), t.getTanggalTranskasi()});
        }
    }
    
    public void showLapak(){
        modelLapak.setRowCount(0);
        for(Toko t : lToko){
            modelLapak.addRow(new Object[]{t.getIdToko(),t.getLokasi(),t.getUkuranToko(),t.getHargaSewa()});
        }
    }
    
    public void showPenyewa(){
        modelPenyewa.setRowCount(0);
        for(Penyewa p : lPenyewa){
            modelPenyewa.addRow(new Object[]{p.getIdPenyewa(),p.getNamaPenyewa(),p.getAlamat(),p.getNoTelp()});
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
                    String nama = rs.getString("nama_lengkap");
                    String alamat = rs.getString("alamat");
                    String noTelp = rs.getString("no_telepon");
                    int status = rs.getInt("status");              
                    Penyewa penyewa = new Penyewa(id,nama,alamat,noTelp);
                    lPenyewa.add(penyewa);
                }
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(ViewAdmin.class.getName()).log(Level.SEVERE,null,e);     
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
                    String ukuran = rs.getString("ukuran");
                    String lokasi = rs.getString("lokasi");
                    String hargaSewa = rs.getString("harga_sewa");
                    Toko lapak = new Toko(idToko,lokasi,ukuran,hargaSewa);
                    lToko.add(lapak);
                }
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(ViewAdmin.class.getName()).log(Level.SEVERE,null,e);     
            }
        }
    }
    
    public void loadTransaksi(){
        if(conn !=  null){
            try{
                String query = "SELECT * FROM tb_transaksi";
                lTransaksi = new ArrayList<>();
                PreparedStatement ps =  conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int idTransaksi = rs.getInt("id_transaksi");
                    int idToko = rs.getInt("id_toko");
                    int idPenyewa = rs.getInt("id_penyewa");
                    String jumlahTransaksi = rs.getString("jumlah_transaksi");
                    String tanggalTranskasi = rs.getString("tanggal_transaksi");
                    Transaksi data = new Transaksi(idTransaksi,idToko,idPenyewa,jumlahTransaksi, tanggalTranskasi);
                    lTransaksi.add(data);
                }
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE,null,e);     
            }
        }
    }
}
