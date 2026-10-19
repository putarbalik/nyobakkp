/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengaturandatabase;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import koneksi.koneksi;
/**
 *
 * @author User
 */
public class menu_siswa extends javax.swing.JInternalFrame {
    private Connection con = koneksi.getConnection();
    private DefaultTableModel tabmode;

    private void pasangIkon(javax.swing.JButton tombol, String pathGbr) {
        try {
            java.net.URL lokasi = getClass().getResource(pathGbr);
            if (lokasi != null) {
                java.awt.Image img = new javax.swing.ImageIcon(lokasi).getImage();
                java.awt.Image ukuranBaru = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
                tombol.setIcon(new javax.swing.ImageIcon(ukuranBaru));
            } else {
                System.err.println("GAMBAR HILANG: " + pathGbr);
            }
        } catch (Exception e) {
            System.err.println("GAGAL RENDER GAMBAR: " + e.getMessage());
        }
    }
    public menu_siswa() {
        initComponents();
        loadKelasOtomatis();
        javax.swing.plaf.basic.BasicInternalFrameUI ui = (javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        try {
            com.formdev.flatlaf.FlatLightLaf.setup();
        } catch (Exception e) {
        }

        pasangIkon(bsimpan, "/resource/save.png");
        pasangIkon(bubah, "/resource/edit.png");
        pasangIkon(bhapus, "/resource/delete.png");
        pasangIkon(bbatal, "/resource/reset.png");
        pasangIkon(bcari, "/resource/search.png");

        bsimpan.setBackground(new java.awt.Color(34, 197, 94));
        bsimpan.setForeground(java.awt.Color.WHITE);

        bubah.setBackground(new java.awt.Color(59, 130, 246));
        bubah.setForeground(java.awt.Color.WHITE);

        bhapus.setBackground(new java.awt.Color(239, 68, 68));
        bhapus.setForeground(java.awt.Color.WHITE);

        bbatal.setBackground(new java.awt.Color(107, 114, 128));
        bbatal.setForeground(java.awt.Color.WHITE);

        bcari.setBackground(new java.awt.Color(59, 130, 246));
        bcari.setForeground(java.awt.Color.WHITE);

        bsimpan.setFocusPainted(false);
        bubah.setFocusPainted(false);
        bhapus.setFocusPainted(false);
        bbatal.setFocusPainted(false);

        bsimpan.setIconTextGap(8);
        bubah.setIconTextGap(8);
        bhapus.setIconTextGap(8);
        bbatal.setIconTextGap(8);
        
        bsimpan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bubah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bhapus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bbatal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        kosong();
        aktif();
        datatable();
    }
    private void loadKelasOtomatis() {
    cbkls.removeAllItems();
    cbkls.addItem("-- Pilih Kelas --");
    
    cbklsfilter.removeAllItems();
    cbklsfilter.addItem("-- Semua Kelas --");
    
    String[] tingkat = {"VII", "VIII", "IX"};
    String[] ruang = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    
    for (String t : tingkat) {
        for (String r : ruang) {
            String namaKelas = t + "." + r;
            cbkls.addItem(namaKelas);
            cbklsfilter.addItem(namaKelas);
        }
    }
}

    protected void aktif() {
        txtnis.requestFocus();
    }

    protected void kosong() {
        txtnis.setText("");
        txtnm.setText("");
        cbjk.setSelectedIndex(0);
        txtcari.setText("");
        
        if (cbkls.getItemCount() > 0) {
            cbkls.setSelectedIndex(0);
        }
    }

    protected void datatable() {
    Object[] Baris = {"NIS", "Nama Siswa", "Jenis Kelamin", "Kelas"};
    tabmode = new DefaultTableModel(null, Baris);
    tbldatasiswa.setModel(tabmode);

    String cariitem = txtcari.getText();
    String kelasTerpilih = "";
    
    // Cek isi Combobox Filter lu, bukan combobox form input!
    if (cbklsfilter.getSelectedItem() != null && cbklsfilter.getSelectedIndex() != 0) {
        kelasTerpilih = cbklsfilter.getSelectedItem().toString();
    }

    try {
        String sql;
        PreparedStatement stat;

        if (kelasTerpilih.equals("")) {
            // Kalau combobox filter isinya "-- Semua Kelas --"
            sql = "SELECT * FROM tbl_siswa WHERE nis LIKE ? OR nama_siswa LIKE ? ORDER BY nis ASC";
            stat = con.prepareStatement(sql);
            stat.setString(1, "%" + cariitem + "%");
            stat.setString(2, "%" + cariitem + "%");
        } else {
            // Kalau combobox filter milih kelas tertentu
            sql = "SELECT * FROM tbl_siswa WHERE kelas = ? AND (nis LIKE ? OR nama_siswa LIKE ?) ORDER BY nis ASC";
            stat = con.prepareStatement(sql);
            stat.setString(1, kelasTerpilih);
            stat.setString(2, "%" + cariitem + "%");
            stat.setString(3, "%" + cariitem + "%");
        }

        ResultSet hasil = stat.executeQuery();

        while (hasil.next()) {
            tabmode.addRow(new Object[]{
                hasil.getString("nis"),
                hasil.getString("nama_siswa"),
                hasil.getString("jkel").equals("L") ? "Laki - Laki" : "Perempuan",
                hasil.getString("kelas")
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Data gagal dipanggil: " + e);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtnis = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnm = new javax.swing.JTextField();
        cbjk = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cbkls = new javax.swing.JComboBox<>();
        bsimpan = new javax.swing.JButton();
        bubah = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bbatal = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtcari = new javax.swing.JTextField();
        bcari = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldatasiswa = new javax.swing.JTable();
        cbklsfilter = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("NIS");

        txtnis.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("Nama Siswa");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setText("Jenis Kelamin");

        txtnm.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtnm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnmActionPerformed(evt);
            }
        });

        cbjk.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cbjk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki - Laki", "Perempuan" }));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setText("Kelas");

        cbkls.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        bsimpan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bsimpan.setText("Simpan");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });

        bubah.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bubah.setText("Ubah");
        bubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bubahActionPerformed(evt);
            }
        });

        bhapus.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bhapus.setText("Hapus");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });

        bbatal.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bbatal.setText("Batal");
        bbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addComponent(jLabel6))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbjk, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnm, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnis, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbkls, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bubah, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnis, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbjk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(cbkls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 419, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(bsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bubah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bhapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtcari.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
        });
        jPanel2.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 44, 300, -1));

        bcari.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bcari.setMargin(new java.awt.Insets(14, 14, 14, 14));
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });
        jPanel2.add(bcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 43, -1, 30));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setText(" Data Siswa");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 2, 122, -1));

        tbldatasiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbldatasiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldatasiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbldatasiswa);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 81, 1210, 570));

        cbklsfilter.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cbklsfilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbklsfilterActionPerformed(evt);
            }
        });
        jPanel2.add(cbklsfilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 40, 205, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORM DATA SISWA");
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 48))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1699, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariKeyPressed

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        // TODO add your handling code here:
        datatable();
    }//GEN-LAST:event_txtcariKeyReleased

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        // TODO add your handling code here:
        datatable();
    }//GEN-LAST:event_bcariActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
      if (txtnis.getText().equals("") || txtnm.getText().equals("") || cbkls.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Data belum lengkap!");
            return;
        }
        
        if (cbjk.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Pilih jenis kelamin!");
            return;
        }
        
        try {
            String sql = "INSERT INTO tbl_siswa (nis, nama_siswa, jkel, kelas) VALUES (?,?,?,?)";
            PreparedStatement stat = con.prepareStatement(sql);

            stat.setString(1, txtnis.getText());
            stat.setString(2, txtnm.getText());
            
            // Konversi Laki - Laki / Perempuan jadi L atau P sesuai tipe data enum di sql
            String jk = cbjk.getSelectedItem().toString();
            if (jk.equals("Laki - Laki")) {
                stat.setString(3, "L");
            } else {
                stat.setString(3, "P");
            }
            
            stat.setString(4, cbkls.getSelectedItem().toString());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");

            kosong();
            datatable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal simpan: " + e);
        }
        txtnis.requestFocus();
    }//GEN-LAST:event_bsimpanActionPerformed

    private void bubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubahActionPerformed
        try {
            if (txtnis.getText().equals("") || txtnm.getText().equals("") || cbkls.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Data tidak lengkap untuk diubah!");
                return;
            }

            String sql = "UPDATE tbl_siswa SET nama_siswa=?, jkel=?, kelas=? WHERE nis=?";
            PreparedStatement stat = con.prepareStatement(sql);

            stat.setString(1, txtnm.getText());
            
            String jk = cbjk.getSelectedItem().toString();
            if (jk.equals("Laki - Laki")) {
                stat.setString(2, "L");
            } else {
                stat.setString(2, "P");
            }
            
            stat.setString(3, cbkls.getSelectedItem().toString());
            stat.setString(4, txtnis.getText());
            
            stat.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil diubah");

            kosong();
            datatable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal ubah: " + e);
        }
    }//GEN-LAST:event_bubahActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM tbl_siswa WHERE nis=?";
                PreparedStatement stat = con.prepareStatement(sql);
                stat.setString(1, txtnis.getText());
                stat.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosong();
                datatable();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal hapus: " + e);
            }
        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void bbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatalActionPerformed
        // TODO add your handling code here
        kosong();
        datatable();
    }//GEN-LAST:event_bbatalActionPerformed

    private void tbldatasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldatasiswaMouseClicked
  int bar = tbldatasiswa.getSelectedRow();
        if (bar == -1) return;

        String nis = tabmode.getValueAt(bar, 0).toString();
        String nama = tabmode.getValueAt(bar, 1).toString();
        String jk = tabmode.getValueAt(bar, 2).toString();
        String kelas = tabmode.getValueAt(bar, 3).toString();

        txtnis.setText(nis);
        txtnm.setText(nama);
        cbjk.setSelectedItem(jk); // Karena di datatable udah gua set string "Laki - Laki" atau "Perempuan"
        cbkls.setSelectedItem(kelas);
    }//GEN-LAST:event_tbldatasiswaMouseClicked

    private void txtnmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnmActionPerformed

    private void cbklsfilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbklsfilterActionPerformed
        datatable();
    }//GEN-LAST:event_cbklsfilterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbatal;
    private javax.swing.JButton bcari;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton bubah;
    private javax.swing.JComboBox<String> cbjk;
    private javax.swing.JComboBox<String> cbkls;
    private javax.swing.JComboBox<String> cbklsfilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbldatasiswa;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtnis;
    private javax.swing.JTextField txtnm;
    // End of variables declaration//GEN-END:variables
}
