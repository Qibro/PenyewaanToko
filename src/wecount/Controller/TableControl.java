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
import wecount.View.Admin;

/**
 *
 * @author ACER
 */
public class TableControl {

    public DefaultTableModel getModel() {
        return model;
    }
    DefaultTableModel model = new DefaultTableModel();
    ArrayList<Penyewa> lPenyewa;
    Connection conn = Koneksi.koneksiDatabase();;
    
    public TableControl() {
        conn = Koneksi.koneksiDatabase();
    }
    
    public void loadKolomPenyewa(){
        model.addColumn("Id Penyewa");
        model.addColumn("Username");
        model.addColumn("Nama Penyewa");
        model.addColumn("Alamat");
        model.addColumn("No Telpon");
        model.addColumn("Status");
    }
    
    public void showPenyewa(){
        model.setRowCount(0);
        for(Penyewa p : lPenyewa){
            model.addRow(new Object[]{p.getIdPenyewa(),p.getUsername(),p.getNama(),p.getAlamat(),p.getNoTelp()});
        }
    }
    
    public void loadPenyewa(){
        if(conn !=  null){
            try{
                String query = "SELECT * FROM tb_akun";
                lPenyewa = new ArrayList<>();
                PreparedStatement ps =  conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id_akun");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
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
}
