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
import wecount.View.ViewLogin;

/**
 *
 * @author ACER
 */
public class TableControl {

    
    DefaultTableModel modelPenyewa,modelTransaksi,modelLapak,modelToko,modelTransaksiSaya;
    ArrayList<Penyewa> lPenyewa;
    ArrayList<Toko> lLapak;
    ArrayList<Toko> lToko;
    ArrayList<Transaksi> lTransaksi;
    ArrayList<Transaksi> lTransaksiSaya;
    Connection conn;
    int countLapak;
    
    public TableControl() {
        modelPenyewa = new DefaultTableModel();
        modelTransaksi = new DefaultTableModel();
        modelLapak = new DefaultTableModel();
        modelToko = new DefaultTableModel();
        modelTransaksiSaya = new DefaultTableModel();
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
    
     public DefaultTableModel getModelTransaksiSaya() {
        return modelTransaksiSaya;
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
        modelLapak.addColumn("Ukuran(meter)");
        modelLapak.addColumn("Harga Sewa");
    }
    
    public void loadKolomTransaksi() {
        modelTransaksi.addColumn("ID Transaksi");
        modelTransaksi.addColumn("ID Toko");
        modelTransaksi.addColumn("ID Penyewa");
        modelTransaksi.addColumn("Jumlah Transaksi");
        modelTransaksi.addColumn("Tanggal Transaksi");
    }
    
    public void loadKolomTransaksiSaya() {
        modelTransaksiSaya.addColumn("ID Transaksi");
        modelTransaksiSaya.addColumn("ID Toko");
        modelTransaksiSaya.addColumn("Jumlah Transaksi(Rupiah)");
        modelTransaksiSaya.addColumn("Tanggal Transaksi");
    }
    
    public DefaultTableModel getModelToko() {
        return modelToko;
    }
    //ubah
    public void loadKolomToko(){
        modelToko.addColumn("ID Toko");
        modelToko.addColumn("ID Penyewa");
        modelToko.addColumn("Nama Toko");
        modelToko.addColumn("Lokasi");
        modelToko.addColumn("Ukuran(meter)");
        modelToko.addColumn("Durasi Sewa(tahun)");//tambah
        modelToko.addColumn("Harga Sewa");
        modelToko.addColumn("Sisa pembayaran");
        modelToko.addColumn("Status Bayar");
    }
    
    public void showTransaksi(){
        modelTransaksi.setRowCount(0);
        for(Transaksi t : lTransaksi){
            modelTransaksi.addRow(new Object[]{t.getIdTransaksi(), t.getIdToko(), t.getIdPenyewa(), t.getJumlahTransaksi(), t.getTanggalTranskasi()});
        }
    }
    
    public void showLapak(){
        modelLapak.setRowCount(0);
        for(Toko t : lLapak){
            modelLapak.addRow(new Object[]{t.getIdToko(),t.getLokasi(),t.getUkuranToko(),t.getHargaSewa()});
        }
    }
    
    public void showPenyewa(){
        modelPenyewa.setRowCount(0);
        for(Penyewa p : lPenyewa){
            modelPenyewa.addRow(new Object[]{p.getIdPenyewa(),p.getNamaPenyewa(),p.getAlamat(),p.getNoTelp()});
        }
    }
    //ubah
    public void showToko(){
        modelToko.setRowCount(0);
        String status_bayar = "Lunas";
        for(Toko t : lToko){
            if(t.getStatus_bayar() == 0) status_bayar = "Belum Lunas";
            modelToko.addRow(new Object[]{t.getIdToko(), t.getIdPenyewa(), t.getNamaToko(), t.getLokasi(), t.getUkuranToko(), t.getDurasiSewa(),t.getHargaSewa(),t.getSisa_pembayaran(),status_bayar});
        }
    }
    
    public void showTransaksiHaha(){
        modelTransaksiSaya.setRowCount(0);
        for(Transaksi ts : lTransaksiSaya){
            modelTransaksi.addRow(new Object[]{ts.getIdTransaksi(),ts.getIdToko(),ts.getJumlahTransaksi(), ts.getTanggalTranskasi()});//tambah
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
                String query = "SELECT * FROM tb_toko WHERE status_beli = 0";
                lLapak = new ArrayList<>();
                PreparedStatement ps =  conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int idToko = rs.getInt("id_toko");
                    String ukuran = rs.getString("ukuran");
                    String lokasi = rs.getString("lokasi");
                    String hargaSewa = rs.getString("harga_sewa");
                    Toko lapak = new Toko(idToko,lokasi,ukuran,hargaSewa);
                    lLapak.add(lapak);
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
    
    public void loadTransaksiHaha(int id){
        if(conn !=  null){
            try{
                String query = "SELECT * FROM tb_transaksi WHERE id_penyewa = ?";
                lTransaksi = new ArrayList<>();
                PreparedStatement ps =  conn.prepareStatement(query);
                ps.setInt(1, id);
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
    //ubah
    public void loadToko( ){
        if(conn !=  null){
            try{
                String query = "SELECT * FROM tb_toko WHERE status_beli = 1";
                lToko= new ArrayList<>();
                PreparedStatement ps =  conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int idToko = rs.getInt("id_toko");
                    int idPenyewa = rs.getInt("id_penyewa");
                    String namaToko = rs.getString("nama_toko");
                    String lokasi = rs.getString("lokasi");
                    String ukuran = rs.getString("ukuran");
                    String hargaSewa = rs.getString("harga_sewa");
                    int durasiSewa = rs.getInt("durasi_sewa");//tambah
                    String sisa_pembayaran = rs.getString("sisa_pembayaran");
                    int status_bayar = rs.getInt("status_bayar");
                    Toko data = new Toko(idToko, idPenyewa, namaToko, lokasi, ukuran, hargaSewa, durasiSewa,sisa_pembayaran,status_bayar);//tambah
                    lToko.add(data);
                }
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE,null,e);     
            }
        }
    }
    //ubah
    public void loadTokoSaya(int id){
    if(conn !=  null){
            try{
                String query = "SELECT * FROM tb_toko WHERE id_penyewa = ?";
                lToko= new ArrayList<>();
                PreparedStatement ps =  conn.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int idToko = rs.getInt("id_toko");
                    int idPenyewa = rs.getInt("id_penyewa");
                    String namaToko = rs.getString("nama_toko");
                    String lokasi = rs.getString("lokasi");
                    String ukuran = rs.getString("ukuran");
                    String hargaSewa = rs.getString("harga_sewa");
                    int durasiSewa = rs.getInt("durasi_sewa");//tambah
                    String sisa_pembayaran = rs.getString("sisa_pembayaran");
                    int status_bayar = rs.getInt("status_bayar");
                    Toko data = new Toko(idToko, idPenyewa, namaToko, lokasi, ukuran, hargaSewa, durasiSewa,sisa_pembayaran,status_bayar);//tambah
                    lToko.add(data);
                }
                rs.close();
                ps.close();
            }catch(SQLException e){
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE,null,e);     
            }
        }
    
//    public void loadTransaksiSaya(int id_penyewa){
//        if(conn !=  null){
//            try{
//                String query = "SELECT * FROM tb_transaksi WHERE id_penyewa = ?";
//                lTransaksiSaya = new ArrayList<>();
//                PreparedStatement ps =  conn.prepareStatement(query);
//                ps.setInt(1, id_penyewa);
//                System.out.println(id_penyewa);
//                ResultSet rs = ps.executeQuery();
//                while(rs.next()){
//                    int idTransaksi = rs.getInt("id_transaksi");
//                    int idToko = rs.getInt("id_toko");
//                    int idPenyewa = rs.getInt("id_penyewa");
//                    String jumlahTransaksi = rs.getString("jumlah_transaksi");
//                    String tanggalTranskasi = rs.getString("tanggal_transaksi");
//                    Transaksi data = new Transaksi(idTransaksi,idToko,idPenyewa,jumlahTransaksi, tanggalTranskasi);//gua tambahin constuctor di model Transaksinya
//                    lTransaksiSaya.add(data);
//                }
//                rs.close();
//                ps.close();
//            }catch(SQLException e){
//                Logger.getLogger(ViewLogin.class.getName()).log(Level.SEVERE,null,e);
//            }E
//        }
//    }
    }
}
 