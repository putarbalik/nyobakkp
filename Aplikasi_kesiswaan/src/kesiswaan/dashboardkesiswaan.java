/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kesiswaan;
import java.awt.Image;
import java.beans.PropertyVetoException;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import koneksi.koneksi;
import kesiswaan.menu_PelanggaranSiswa;
/**
 *
 * @author User
 */
public class dashboardkesiswaan extends javax.swing.JFrame {

    /**
     * Creates new form dashboardguru
     */
    public dashboardkesiswaan() {
        initComponents();
        // Session dihilangkan sementara
        tampilkanHomeAwal();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        
        // Teks diketik manual (hardcode)
        lab_login.setText("SELAMAT DATANG DI DASHBOARD KESISWAAN"); 
        
        sethome(home,"/resource/home.png");
        setleave(logout,"/resource/keluar.png");
        setPel(bpel,"/resource/logoPelanggaran.png");
    }
    
    public void tampilkanHomeAwal() {
    try {
        desktop.removeAll();
        
        homedashboardkesiswaan h = new homedashboardkesiswaan();
        
        h.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI)h.getUI()).setNorthPane(null);
        
        desktop.add(h);
        
        h.setSize(desktop.getWidth(), desktop.getHeight());
        h.setVisible(true);
        
        h.tampilkanAngkaRingkasan();
        
        desktop.repaint();
        desktop.revalidate();
    } catch (Exception e) {
        System.err.println("Gagal memuat home awal: " + e.getMessage());
    }
}

    
   private void setFixedIcon(javax.swing.JButton btn, String path) {
    try {
        ImageIcon iconAwal = new ImageIcon(getClass().getResource(path));
        Image imgBaru = iconAwal.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        btn.setIcon(new ImageIcon(imgBaru));

        btn.setForeground(java.awt.Color.WHITE);
        btn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        btn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn.setMargin(new java.awt.Insets(10, 20, 10, 10));
        btn.setIconTextGap(20);

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setContentAreaFilled(true);
                btn.setBackground(new java.awt.Color(255, 255, 0)); 
                btn.setForeground(java.awt.Color.BLACK); 
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setContentAreaFilled(false);
                btn.setForeground(java.awt.Color.WHITE);
            }
        });

    } catch (Exception e) {
        System.err.println("Gagal set hover: " + e.getMessage());
    }
}
   private void sethome(javax.swing.JButton btn, String path) {
    try {
        ImageIcon iconAwal = new ImageIcon(getClass().getResource(path));
        Image imgBaru = iconAwal.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        btn.setIcon(new ImageIcon(imgBaru));

        btn.setForeground(java.awt.Color.WHITE);
        btn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        btn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn.setIconTextGap(10);
        btn.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(java.awt.Graphics g, javax.swing.JComponent c) {
                if (btn.getModel().isRollover()) { 
                    java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                    g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new java.awt.Color(255, 255, 255)); // Warna putih untuk home
                    g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 25, 25);
                    g2.dispose();
                }
                super.paint(g, c); 
            }
        });

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setForeground(java.awt.Color.BLACK); // Teks otomatis hitam saat hover putih
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setForeground(java.awt.Color.WHITE); // Teks kembali putih
            }
        });

    } catch (Exception e) {
        System.err.println("Gagal set hover: " + e.getMessage());
    }
}
   private void setleave(javax.swing.JButton btn, String path) {
    try {
        ImageIcon iconAwal = new ImageIcon(getClass().getResource(path));
        Image imgBaru = iconAwal.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        btn.setIcon(new ImageIcon(imgBaru));

        btn.setForeground(java.awt.Color.WHITE);
        
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn.setIconTextGap(20);
        btn.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 10));

        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(java.awt.Graphics g, javax.swing.JComponent c) {
                if (btn.getModel().isRollover()) { 
                    java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                    g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new java.awt.Color(255, 0, 0)); // Warna merah untuk log out
                    g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 25, 25);
                    g2.dispose();
                }
                super.paint(g, c); 
            }
        });

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setForeground(java.awt.Color.WHITE); 
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setForeground(java.awt.Color.WHITE);
            }
        });

    } catch (Exception e) {
        System.err.println("Gagal set hover: " + e.getMessage());
    }
}
public void scaleImage(javax.swing.JComponent komponenTarget, String pathGambar) {
    try {
        // 1. Mengambil file gambar (sekarang mendukung format .jpg seperti logoSekolah.jpg)
        java.net.URL imgURL = getClass().getResource(pathGambar);
        
        if (imgURL == null) {
            System.err.println("Gambar TIDAK ditemukan di path: " + pathGambar);
            return;
        }
        
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(imgURL);
        java.awt.Image img = icon.getImage();
        
        // 2. Mengambil ukuran komponen. Jika ukuran komponen masih 0 (karena form belum selesai loading),
        // otomatis dipaksa menggunakan ukuran default 500x500 agar tidak terlalu kecil.
        int width = komponenTarget.getWidth() > 0 ? (komponenTarget.getWidth() - 6) : 100;
int height = komponenTarget.getHeight() > 0 ? (komponenTarget.getHeight() - 6) : 100;
        
        // 3. Meresize gambar
        java.awt.Image imgScale = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        javax.swing.ImageIcon scaledIcon = new javax.swing.ImageIcon(imgScale);
        
        // 4. Memasang gambar ke dalam komponen JLabel atau JButton
        if (komponenTarget instanceof javax.swing.JLabel) {
            ((javax.swing.JLabel) komponenTarget).setIcon(scaledIcon);
        } else if (komponenTarget instanceof javax.swing.JButton) {
            ((javax.swing.JButton) komponenTarget).setIcon(scaledIcon);
        }
        
    } catch (Exception e) {
        System.out.println("Error scale image: " + e.getMessage());
    }
}
private void setPel(javax.swing.JButton btn, String path) {
    try {
        ImageIcon iconAwal = new ImageIcon(getClass().getResource(path));
        Image imgBaru = iconAwal.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        btn.setIcon(new ImageIcon(imgBaru));

        btn.setForeground(java.awt.Color.WHITE);
        
        btn.setContentAreaFilled(false); 
        btn.setBorderPainted(false); // Matikan border
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn.setIconTextGap(20);
        btn.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 10));

        // Trik Jitu: Mengubah urutan menggambar tombol
        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(java.awt.Graphics g, javax.swing.JComponent c) {
                if (btn.getModel().isRollover()) { // Jika kursor menyentuh tombol
                    java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                    g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new java.awt.Color(255, 0, 0)); // Warna merah untuk hover
                    g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 25, 25);
                    g2.dispose();
                }
                super.paint(g, c); // Menggambar teks dan ikon DI ATAS background merah
            }
        });

        // MouseListener sekarang HANYA untuk mengubah warna teks (jika perlu)
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setForeground(java.awt.Color.WHITE); 
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setForeground(java.awt.Color.WHITE);
            }
        });

    } catch (Exception e) {
        System.err.println("Gagal set hover: " + e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        bpel = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        bpel1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lab_login = new javax.swing.JLabel();
        home = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        desktop = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 8, 97));

        jPanel1.setBackground(new java.awt.Color(0, 8, 97));

        bpel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bpel.setText("Input Kasus");
        bpel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bpel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpelActionPerformed(evt);
            }
        });

        logout.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        logout.setForeground(new java.awt.Color(255, 0, 0));
        logout.setText("Log out");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        bpel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bpel1.setText("Pelanggaran");
        bpel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bpel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bpel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bpel1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(bpel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bpel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 464, Short.MAX_VALUE)
                .addComponent(logout)
                .addGap(25, 25, 25))
        );

        jPanel2.setBackground(new java.awt.Color(0, 8, 97));

        lab_login.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lab_login.setForeground(new java.awt.Color(255, 255, 255));
        lab_login.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        home.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        home.setText("home");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/logoSekolah.png"))); // NOI18N
        logo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                logoComponentResized(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SMP 259 JAKARTA");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("KESISWAAN");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("_________________________");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(889, 889, 889))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(41, 41, 41)
                        .addComponent(lab_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel2))
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_login, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        desktop.setBackground(new java.awt.Color(245, 245, 245));
        desktop.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                desktopComponentResized(evt);
            }
        });

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(desktop))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(desktop)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bpelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpelActionPerformed
    desktop.removeAll();
    menu_PelanggaranSiswa as = new menu_PelanggaranSiswa();

    desktop.add(as);
    as.setVisible(true);
    try {
        as.setMaximum(true);
        } catch (java.beans.PropertyVetoException e) {
            System.err.println("Gagal memaksimalkan Internal Frame: " + e.getMessage());
        }   
    }//GEN-LAST:event_bpelActionPerformed

    private void desktopComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_desktopComponentResized
for (JInternalFrame frame : desktop.getAllFrames()) {
        try {
            frame.setMaximum(true);
        } catch (PropertyVetoException e) {
            System.err.println("Gagal resize internal frame: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_desktopComponentResized

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
                                      
        int pilih = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin keluar?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        
        if (pilih == JOptionPane.YES_OPTION) {
            // Karena tidak ada session, langsung tutup aplikasi
            this.dispose(); 
            System.exit(0); 
        }
    
    }//GEN-LAST:event_logoutActionPerformed

    private void logoComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_logoComponentResized
        scaleImage(logo, "/resource/logoSekolah.png");
    }//GEN-LAST:event_logoComponentResized

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        tampilkanHomeAwal();
    }//GEN-LAST:event_homeActionPerformed

    private void bpel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bpel1ActionPerformed

  public static void main(String args[]) {
    try {
    } catch (Exception ex) {
        System.err.println("Gagal memuat tema FlatLaf");
    }

    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new dashboardkesiswaan().setVisible(true);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bpel;
    private javax.swing.JButton bpel1;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JButton home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lab_login;
    private javax.swing.JLabel logo;
    private javax.swing.JButton logout;
    // End of variables declaration//GEN-END:variables
}
