/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kesiswaan;
import kesiswaan.*;
import java.sql.Connection;
import koneksi.koneksi;

/**
 *
 * @author User
 */
public class homedashboardkesiswaan extends javax.swing.JInternalFrame {
    // Beri tanda komentar (//) di bawah ini agar tidak memanggil koneksi
    // private Connection conn = new koneksi().getConnection();

    public homedashboardkesiswaan() {
        initComponents();
        tkelas.setText("REKAP DATA: SEMUA KELAS"); 
        tampilkanAngkaRingkasan();
    }
    
    
    public void tampilkanAngkaRingkasan() {
        // Langsung tembak teksnya secara manual (Data Dummy)
        lbl_angkaSiswa.setText("1.250 Siswa");
        lbl_angkaKasus.setText("5 Kasus");
        lbl_angkaAgenda.setText("3 Pertemuan");
        lbl_angkaPelanggaran.setText("12 Kasus");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        tkelas = new javax.swing.JLabel();
        panelDiagram = new javax.swing.JPanel();
        lbl_angkaSiswa = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelDiagram1 = new javax.swing.JPanel();
        lbl_angkaKasus = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelDiagram2 = new javax.swing.JPanel();
        lbl_angkaAgenda = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panelDiagram3 = new javax.swing.JPanel();
        lbl_angkaPelanggaran = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 3, 12)); // NOI18N
        jLabel3.setText("SISWA TERDATA");

        tkelas.setFont(new java.awt.Font("Segoe UI Symbol", 3, 12)); // NOI18N

        panelDiagram.setPreferredSize(new java.awt.Dimension(400, 300));
        panelDiagram.setLayout(new java.awt.BorderLayout());

        lbl_angkaSiswa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_angkaSiswa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_angkaSiswa.setText("0 Siswa");
        panelDiagram.add(lbl_angkaSiswa, java.awt.BorderLayout.CENTER);

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 3, 12)); // NOI18N
        jLabel4.setText("KASUS AKTIF");

        panelDiagram1.setPreferredSize(new java.awt.Dimension(400, 300));
        panelDiagram1.setLayout(new java.awt.BorderLayout());

        lbl_angkaKasus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_angkaKasus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_angkaKasus.setText("0 Siswa");
        panelDiagram1.add(lbl_angkaKasus, java.awt.BorderLayout.CENTER);

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 3, 12)); // NOI18N
        jLabel5.setText("AGENDA KONSELING");

        panelDiagram2.setPreferredSize(new java.awt.Dimension(400, 300));
        panelDiagram2.setLayout(new java.awt.BorderLayout());

        lbl_angkaAgenda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_angkaAgenda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_angkaAgenda.setText("0 Siswa");
        panelDiagram2.add(lbl_angkaAgenda, java.awt.BorderLayout.CENTER);

        jLabel6.setFont(new java.awt.Font("Segoe UI Symbol", 3, 12)); // NOI18N
        jLabel6.setText("PELANGGARAN BULAN INI");

        panelDiagram3.setPreferredSize(new java.awt.Dimension(400, 300));
        panelDiagram3.setLayout(new java.awt.BorderLayout());

        lbl_angkaPelanggaran.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_angkaPelanggaran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_angkaPelanggaran.setText("0 Siswa");
        panelDiagram3.add(lbl_angkaPelanggaran, java.awt.BorderLayout.CENTER);

        jLabel7.setFont(new java.awt.Font("Segoe UI Symbol", 3, 18)); // NOI18N
        jLabel7.setText("RINGKASAN HARI INI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelDiagram, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelDiagram1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelDiagram2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelDiagram3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tkelas, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(624, 624, 624))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelDiagram3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelDiagram2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelDiagram1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelDiagram, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tkelas)
                .addContainerGap(294, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbl_angkaAgenda;
    private javax.swing.JLabel lbl_angkaKasus;
    private javax.swing.JLabel lbl_angkaPelanggaran;
    private javax.swing.JLabel lbl_angkaSiswa;
    private javax.swing.JPanel panelDiagram;
    private javax.swing.JPanel panelDiagram1;
    private javax.swing.JPanel panelDiagram2;
    private javax.swing.JPanel panelDiagram3;
    private javax.swing.JLabel tkelas;
    // End of variables declaration//GEN-END:variables
}
