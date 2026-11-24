/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane; // Pastikan import ini ada

public class koneksi {
    public static Connection getKoneksi() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:dbsekolah.db";
conn = DriverManager.getConnection(url);
System.out.println("DB path: " + new java.io.File("dbsekolah.db").getAbsolutePath());
            
            // Tes apakah koneksi berhasil
            if (conn != null) {
                // Pesan ini bisa dihapus nanti kalau aplikasinya sudah normal
                System.out.println("Koneksi Berhasil!");
            }
        } catch (Exception e) {
            // Jika gagal, aplikasi akan memunculkan kotak dialog error di layar
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage());
        }
        return conn;
    }
}
    
