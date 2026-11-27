/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kesiswaan;

import kesiswaan.*;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author ACER
 */
public class menu_Peraturan extends javax.swing.JInternalFrame {
private Connection con;
private DefaultTableModel tabmode;
PreparedStatement ps;
ResultSet rs;
private String nisTerpilih = "";

public static ArrayList<String> daftarKategori = new ArrayList<>(Arrays.asList(
    "Terlambat masuk sekolah",
    "Tidak memakai seragam sesuai jadwal",
    "Tidak memakai atribut nama murid",
    "Tidak memakai atribut bendera merah putih",
    "Tidak memakai topi SMPN 259",
    "Tidak memakai atribut nama SMPN 259",
    "Tidak memakai dasi biru",
    "Tidak memakai dasi pramuka",
    "Tidak memakai ikat pinggang logo 259",
    "Tidak memakai kaos kaki putih",
    "Tidak memakai sepatu NB hitam putih tinggi",
    "Memakai pakaian ketat",
    "Memakai perhiasan berlebihan",
    "Memakai make-up berlebihan",
    "Mencorat coret seragam/topi/atribut",
    "Rambut panjang (bagi laki-laki)",
    "Alis di kerok",
    "Bertato",
    "Masuk atau keluar tidak melewati pintu",
    "Membawa uang dalam jumlah besar atau berlebih",
    "Mewarnai atau mengecat rambut maupun kuku",
    "Membuat kegaduhan atau huru-hara",
    "Membawa atau mengonsumsi permen karet",
    "Melakukan perjudian di lingkungan sekolah",
    "Membawa buku/majalah/gambar tidak sopan",
    "Rokok/vape/ganja/miras",
    "Perkelahian atau tawuran",
    "Membawa senjata tajam/api/bahan peledak",
    "Mengajak orang lain menyelesaikan persoalan",
    "Meminta sesuatu dari temannya secara paksa",
    "Menghina atau merugikan nama baik teman",
    "Bermain bola saat istirahat",
    "Mengambil atau mencuri barang teman/orang lain",
    "Membawa motor ke dalam lingkungan sekolah",
    "Membawa gawai tanpa seizin wali kelas",
    "Bullying",
    "Penyalahgunaan di sosial media",
    "Pelecehan seksual dan kesusilaan",
    "Pergaulan bebas",
    "Vandalisme dan perusakan",
    "Narkotika"
));

private int indexTerpilih = -1;


    /**
     * Creates new form menu_pelanggaranSiswa
     */
    public menu_Peraturan() {
    initComponents();
    javax.swing.plaf.basic.BasicInternalFrameUI ui = (javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI();
    ui.setNorthPane(null);
    kosong();
    datatable();
}
    
    private void cetakSiswa(String nis) {
        try {
            HashMap<String, Object> param = new HashMap<>();
            param.put("nis", nis);   // String, sesuai parameter di report

            JasperReport jr = JasperCompileManager.compileReport(
                "src/report/reportPersiswa.jrxml");
            param.put(net.sf.jasperreports.engine.JRParameter.REPORT_LOCALE, new java.util.Locale("id", "ID"));
                InputStream logoStream = getClass().getResourceAsStream("/resource/logoSekolah.png"); 
            if (logoStream == null) {
                System.out.println("GAGAL: File gambar tidak ditemukan di resources!");
            }
            param.put("logo", logoStream);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, con);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal cetak: " + e.getMessage());
        }
    }  
    
    protected void aktif(){
        txtperaturan.requestFocus();
    }
    
    private void cetakPerBulan(int bulan, int tahun) {
        try {
            HashMap<String, Object> param = new HashMap<>();
            param.put("bulan", bulan);
            param.put("tahun", tahun);

            JasperReport jr = JasperCompileManager.compileReport(
                "src/report/reportPerbulan.jrxml");   // pakai .jrxml, bukan .jasper
            param.put(net.sf.jasperreports.engine.JRParameter.REPORT_LOCALE, new java.util.Locale("id", "ID"));
            InputStream logoStream = getClass().getResourceAsStream("/resource/logoSekolah.png"); 
        if (logoStream == null) {
            System.out.println("GAGAL: File gambar tidak ditemukan di resources!");
        }
        param.put("logo", logoStream);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, con);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal cetak: " + e.getMessage());
        }
    }
    
    
    private void cetakKelas(String kelas) {
        try {
            HashMap<String, Object> param = new HashMap<>();
            param.put("kelas", kelas);   // String, sesuai parameter di report

            JasperReport jr = JasperCompileManager.compileReport(
                "src/report/reportPerkelas.jrxml");
             param.put(net.sf.jasperreports.engine.JRParameter.REPORT_LOCALE, new java.util.Locale("id", "ID"));
            InputStream logoStream = getClass().getResourceAsStream("/resource/logoSekolah.png"); 
        if (logoStream == null) {
            System.out.println("GAGAL: File gambar tidak ditemukan di resources!");
        }
        param.put("logo", logoStream);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, con);

            if (jp.getPages().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Tidak ada data pelanggaran untuk " + kelas);
                return;
            }

            JasperViewer.viewReport(jp, false);
            // Kalau mau langsung dialog printer tanpa preview:
            // JasperPrintManager.printReport(jp, true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal cetak: " + e.getMessage());
        }
    }
    protected void kosong() {
         txtperaturan.setText("");
         indexTerpilih = -1;
    }   
    
      protected void datatable() {
        Object[] baris = {"No", "Nama Peraturan"};
        tabmode = new DefaultTableModel(null, baris) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    tblperaturan.setModel(tabmode);

    for (int i = 0; i < daftarKategori.size(); i++) {
        tabmode.addRow(new Object[]{
            (i + 1),
            daftarKategori.get(i)
        });
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtperaturan = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblperaturan = new javax.swing.JTable();
        bbatal = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bsimpan = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(214647, 2147483647));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panel Input", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16), new java.awt.Color(2, 2, 2))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(1, 1, 1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setText("NAMA PELANGGARAN           :");
        jLabel12.setToolTipText("");

        txtperaturan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtperaturanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtperaturan, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtperaturan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORM INPUT PELANGGARAN SISWA");
        jLabel1.setToolTipText("");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panel Peraturan", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tblperaturan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblperaturan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblperaturanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblperaturan);
        if (tblperaturan.getColumnModel().getColumnCount() > 0) {
            tblperaturan.getColumnModel().getColumn(0).setResizable(false);
            tblperaturan.getColumnModel().getColumn(1).setResizable(false);
            tblperaturan.getColumnModel().getColumn(2).setResizable(false);
            tblperaturan.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bbatal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bbatal.setText("BATAL");
        bbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatalActionPerformed(evt);
            }
        });

        bhapus.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bhapus.setText("HAPUS");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });

        bsimpan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bsimpan.setText("SIMPAN");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(bsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bbatal, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(bhapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(28, 28, 28)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(bhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(604, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
    if (indexTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Pilih peraturan di tabel yang ingin dihapus terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String namaPeraturan = daftarKategori.get(indexTerpilih);
        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Yakin ingin menghapus peraturan:\n\"" + namaPeraturan + "\"?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            daftarKategori.remove(indexTerpilih);
            JOptionPane.showMessageDialog(this, "Peraturan berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            kosong();
            datatable();
        }
// TODO add your handling code here:
    }//GEN-LAST:event_bhapusActionPerformed

    private void bbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatalActionPerformed

    int konfirmasi = JOptionPane.showConfirmDialog(this, 
            "Batal melakukan input pelanggaran? Semua isian dan centang akan dikosongkan.", 
            "Konfirmasi Batal", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);
            
    if (konfirmasi == JOptionPane.YES_OPTION) {
        kosong();     
        datatable();  
        
        JOptionPane.showMessageDialog(this, "Form berhasil dibersihkan.", "Batal", JOptionPane.INFORMATION_MESSAGE);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_bbatalActionPerformed

    private void txtperaturanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtperaturanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtperaturanActionPerformed

    private void tblperaturanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblperaturanMouseClicked
    indexTerpilih = tblperaturan.getSelectedRow();
    if (indexTerpilih != -1) {
        txtperaturan.setText(daftarKategori.get(indexTerpilih));
    }
    }//GEN-LAST:event_tblperaturanMouseClicked

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        String nama = txtperaturan.getText().trim();
    if (nama.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Nama peraturan tidak boleh kosong!");
        return;
    }
    daftarKategori.add(nama);
    JOptionPane.showMessageDialog(this, "Peraturan berhasil ditambahkan!");
    kosong();
    datatable();        // TODO add your handling code here:
    }//GEN-LAST:event_bsimpanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbatal;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblperaturan;
    private javax.swing.JTextField txtperaturan;
    // End of variables declaration//GEN-END:variables
}
