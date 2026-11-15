/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class koneksi {
    public static Connection getKoneksi() {
        Connection conn = null;
        try {
            // Sesuaikan nama file databasemu (pakai .db atau tidak, tergantung aslinya)
            String url = "jdbc:sqlite:dbsekolah.db"; 
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute("PRAGMA journal_mode = WAL;"); // Mengizinkan baca & tulis bersamaan
            stmt.execute("PRAGMA busy_timeout = 5000;"); // Jika sibuk, tunggu 5 detik, jangan langsung error
            stmt.close();
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
    
