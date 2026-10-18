/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kesiswaan;
import kesiswaan.*;
import java.sql.Connection;
import koneksi.koneksi;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author User
 */
public class homedashboardkesiswaan extends javax.swing.JInternalFrame {
    // Beri tanda komentar (//) di bawah ini agar tidak memanggil koneksi
    // private Connection conn = new koneksi().getConnection();

    public homedashboardkesiswaan() {
        initComponents();
        tampilkanAngkaRingkasan();
        tampilkanGrafik();
        tampilkanTabelLive();
    }
    
    public void tampilkanGrafik() {
        Connection conn = koneksi.getConnection();
        if (conn == null) {
            System.err.println("Koneksi gagal, grafik tidak dapat dimuat.");
            return;
        }

        // =========================================================================
        // 1. GRAFIK GARIS (KIRI) - Tren Pelanggaran Bulanan
        // =========================================================================
        org.jfree.data.category.DefaultCategoryDataset lineDataset = new org.jfree.data.category.DefaultCategoryDataset();
        String[] bulanBulan = {"Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Ags", "Sep", "Okt", "Nov", "Des"};
        int[] dataBulan = new int[12];
        int maxKasus = 0;

        String sqlLine = "SELECT MONTH(tgl_pelanggaran) as bulan, COUNT(*) as jumlah "
                       + "FROM tbl_pelanggaran WHERE YEAR(tgl_pelanggaran) = YEAR(CURDATE()) "
                       + "GROUP BY MONTH(tgl_pelanggaran)";

        try (PreparedStatement ps = conn.prepareStatement(sqlLine);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int blnIndex = rs.getInt("bulan") - 1; 
                int jml = rs.getInt("jumlah");
                dataBulan[blnIndex] = jml;
                if (jml > maxKasus) maxKasus = jml; 
            }
        } catch (java.sql.SQLException e) {
            System.err.println("Gagal memuat data line chart: " + e.getMessage());
        }

        for (int i = 0; i < 12; i++) {
            lineDataset.setValue(dataBulan[i], "Kasus", bulanBulan[i]);
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                null, null, null, lineDataset, 
                org.jfree.chart.plot.PlotOrientation.VERTICAL, 
                false, true, false);
        
        // Judul Kiri (Mencorong ke kanan sedikit agar tidak mepet)
        org.jfree.chart.title.TextTitle titleKiri = new org.jfree.chart.title.TextTitle("Tren Pelanggaran", new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        titleKiri.setHorizontalAlignment(org.jfree.ui.HorizontalAlignment.LEFT);
        titleKiri.setMargin(new org.jfree.ui.RectangleInsets(15, 20, 0, 5)); // Jarak 20px dari kiri
        lineChart.setTitle(titleKiri);
        
        // Judul Kanan (Tahun) dinaikkan dengan margin negatif agar sejajar
        int tahunSekarang = java.time.Year.now().getValue();
        org.jfree.chart.title.TextTitle titleKanan = new org.jfree.chart.title.TextTitle(String.valueOf(tahunSekarang), new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        titleKanan.setHorizontalAlignment(org.jfree.ui.HorizontalAlignment.RIGHT);
        titleKanan.setMargin(new org.jfree.ui.RectangleInsets(-25, 5, 0, 20)); // Margin atas -25px menariknya ke atas
        lineChart.addSubtitle(titleKanan);

        org.jfree.chart.plot.CategoryPlot linePlot = lineChart.getCategoryPlot();
        linePlot.setBackgroundPaint(java.awt.Color.WHITE);
        linePlot.setOutlineVisible(false);
        linePlot.setDomainGridlinesVisible(false);
        linePlot.setRangeGridlinePaint(new java.awt.Color(230, 230, 230));

        org.jfree.chart.renderer.category.LineAndShapeRenderer renderer = new org.jfree.chart.renderer.category.LineAndShapeRenderer();
        renderer.setSeriesPaint(0, new java.awt.Color(54, 162, 235)); 
        renderer.setSeriesStroke(0, new java.awt.BasicStroke(3.0f)); 
        linePlot.setRenderer(renderer);

        org.jfree.chart.axis.NumberAxis yAxis = (org.jfree.chart.axis.NumberAxis) linePlot.getRangeAxis();
        yAxis.setStandardTickUnits(org.jfree.chart.axis.NumberAxis.createIntegerTickUnits()); 
        yAxis.setRange(0, maxKasus + 10); 
        
        yAxis.setTickLabelFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        linePlot.getDomainAxis().setTickLabelFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        lineChart.setBackgroundPaint(java.awt.Color.WHITE);
        lineChart.setPadding(new org.jfree.ui.RectangleInsets(10, 10, 10, 10)); // Ruang agar grafik tidak tabrakan dengan border melengkung

        ChartPanel lineChartPanel = new ChartPanel(lineChart);
        lineChartPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200), 2, true)); // Border Sudut Tumpul
        jPanel7.removeAll(); 
        jPanel7.setLayout(new java.awt.BorderLayout());
        jPanel7.add(lineChartPanel, java.awt.BorderLayout.CENTER);
        jPanel7.validate();


        // =========================================================================
        // 2. DONUT CHART (KANAN) - Sebaran Kasus
        // =========================================================================
        org.jfree.data.general.DefaultPieDataset pieDataset = new org.jfree.data.general.DefaultPieDataset();
        String sqlPie = "SELECT kategori_pelanggaran, COUNT(id_pelanggaran) as jumlah "
                      + "FROM tbl_pelanggaran GROUP BY kategori_pelanggaran ORDER BY jumlah DESC LIMIT 5";

        try (PreparedStatement ps = conn.prepareStatement(sqlPie);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pieDataset.setValue(rs.getString("kategori_pelanggaran"), rs.getInt("jumlah"));
            }
        } catch (java.sql.SQLException e) {
            System.err.println("Gagal memuat data donut chart: " + e.getMessage());
        }

        JFreeChart donutChart = ChartFactory.createRingChart(null, pieDataset, true, true, false); 
        
        // Menambahkan Judul Sebaran Jenis Kasus
        org.jfree.chart.title.TextTitle titleDonut = new org.jfree.chart.title.TextTitle("Sebaran Jenis Kasus", new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        titleDonut.setHorizontalAlignment(org.jfree.ui.HorizontalAlignment.LEFT);
        titleDonut.setMargin(new org.jfree.ui.RectangleInsets(15, 20, 5, 5));
        donutChart.setTitle(titleDonut);
        
        org.jfree.chart.plot.RingPlot donutPlot = (org.jfree.chart.plot.RingPlot) donutChart.getPlot();
        donutPlot.setBackgroundPaint(java.awt.Color.WHITE);
        donutPlot.setOutlineVisible(false);
        donutPlot.setShadowXOffset(0);
        donutPlot.setShadowYOffset(0);
        
        // Mempertebal donat agar persentase 18px muat sempurna di dalamnya
        donutPlot.setSectionDepth(0.50); 
        donutPlot.setInteriorGap(0.04); 
        
        donutPlot.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator("{2}")); 
        donutPlot.setSimpleLabels(true); 
        donutPlot.setLabelFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14)); // Dikecilkan sedikit jadi 14 agar persentase kecil seperti 5% tidak luber
        donutPlot.setLabelBackgroundPaint(null);
        donutPlot.setLabelOutlinePaint(null);
        donutPlot.setLabelShadowPaint(null);
        
        donutChart.getLegend().setPosition(org.jfree.ui.RectangleEdge.RIGHT);
        donutChart.getLegend().setItemFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        donutChart.getLegend().setBorder(org.jfree.chart.block.BlockBorder.NONE); // Menghilangkan kotak pada tulisan legenda
        
        donutChart.setBackgroundPaint(java.awt.Color.WHITE);
        donutChart.setPadding(new org.jfree.ui.RectangleInsets(10, 10, 10, 10)); // Ruang border

        ChartPanel donutChartPanel = new ChartPanel(donutChart);
        donutChartPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200), 2, true)); // Border Sudut Tumpul
        jPanel1.removeAll();
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(donutChartPanel, java.awt.BorderLayout.CENTER);
        jPanel1.validate();
    }
    
    public void tampilkanTabelLive() {
        // Menambahkan kolom "Nama Siswa" ke dalam Header Tabel
        String[] kolom = {"NIS", "Nama Siswa", "Kategori Pelanggaran", "Tanggal & Jam Kejadian"};
        DefaultTableModel modelTabel = new DefaultTableModel(null, kolom) {
            @Override
            public boolean isCellEditable(int row, int column) { 
                return false; 
            }
        };
        tblpelanggaran.setModel(modelTabel);
        
        // Memperindah tampilan tabel
        tblpelanggaran.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        tblpelanggaran.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        tblpelanggaran.setRowHeight(30);

        Connection conn = koneksi.getConnection();
        if (conn == null) return;

        // Menggunakan perintah JOIN untuk menarik kolom 'nama' dari tbl_siswa
        String sql = "SELECT p.nis, s.nama_siswa, p.kategori_pelanggaran, p.created_at "
                   + "FROM tbl_pelanggaran p "
                   + "JOIN tbl_siswa s ON p.nis = s.nis "
                   + "ORDER BY p.created_at DESC LIMIT 10";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            // Format tanggal dan jam standar Indonesia
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            while (rs.next()) {
                String tglJamFormat = "-";
                if(rs.getTimestamp("created_at") != null) {
                    tglJamFormat = sdf.format(rs.getTimestamp("created_at"));
                }
                
                // Memasukkan data ke dalam baris secara berurutan sesuai Header
                modelTabel.addRow(new Object[]{
                    rs.getString("nis"),                  // Ubah dari "nisn" menjadi "nis"
                    rs.getString("nama_siswa"),           // Ubah dari "nama" menjadi "nama_siswa"
                    rs.getString("kategori_pelanggaran"),
                    tglJamFormat
                });
            }
        } catch (java.sql.SQLException e) {
            System.err.println("Gagal memuat tabel live: " + e.getMessage());
        }
    }
    
    
    public void tampilkanAngkaRingkasan() {
    Connection conn = koneksi.getConnection();

    if (conn == null) {
        lbl_angkaSiswa.setText("Koneksi gagal");
        lbl_angkaKasus.setText("Koneksi gagal");
        return;
    }
    String sqlSiswa = "SELECT COUNT(*) AS total FROM tbl_siswa";

    try (PreparedStatement ps = conn.prepareStatement(sqlSiswa);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            lbl_angkaSiswa.setText(rs.getInt("total") + " Siswa");
        }

    } catch (java.sql.SQLException e) {
        lbl_angkaSiswa.setText("Gagal memuat");
        System.err.println("Gagal menghitung siswa: " + e.getMessage());
    }
    String sqlPelanggaran =
            "SELECT COUNT(*) AS total "
          + "FROM tbl_pelanggaran "
          + "WHERE DATE(tgl_pelanggaran) = CURDATE()";

    try (PreparedStatement ps = conn.prepareStatement(sqlPelanggaran);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            lbl_angkaKasus.setText(rs.getInt("total") + " Kasus");
        }

    } catch (java.sql.SQLException e) {
        lbl_angkaKasus.setText("Gagal memuat");
        System.err.println("Gagal menghitung pelanggaran: " + e.getMessage());
    }
}
public void scaleImage(javax.swing.JComponent komponenTarget, String pathGambar) {
    try {
        java.net.URL imgURL = getClass().getResource(pathGambar);
        
        if (imgURL == null) {
            System.err.println("Gambar TIDAK ditemukan di path: " + pathGambar);
            return;
        }
        
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(imgURL);
        java.awt.Image img = icon.getImage();
        
        int width = komponenTarget.getWidth() > 0 ? (komponenTarget.getWidth() - 6) : 100;
        int height = komponenTarget.getHeight() > 0 ? (komponenTarget.getHeight() - 6) : 100;
        
        java.awt.Image imgScale = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        javax.swing.ImageIcon scaledIcon = new javax.swing.ImageIcon(imgScale);
        
        if (komponenTarget instanceof javax.swing.JLabel) {
            ((javax.swing.JLabel) komponenTarget).setIcon(scaledIcon);
        } else if (komponenTarget instanceof javax.swing.JButton) {
            ((javax.swing.JButton) komponenTarget).setIcon(scaledIcon);
        }
        
    } catch (Exception e) {
        System.out.println("Error scale image: " + e.getMessage());
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tkelas = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbl_angkaSiswa = new javax.swing.JLabel();
        lsiswa = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lpelanggaran = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_angkaKasus = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpelanggaran = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        jLabel5.setText("jLabel5");

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);

        tkelas.setFont(new java.awt.Font("Segoe UI Symbol", 3, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("RINGKASAN HARI INI");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Total Siswa Aktif");

        lbl_angkaSiswa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_angkaSiswa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_angkaSiswa.setText("0 Siswa");

        lsiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/siswa.png"))); // NOI18N
        lsiswa.setText("jLabel1");
        lsiswa.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                lsiswaComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lsiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_angkaSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lsiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(lbl_angkaSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        lpelanggaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/pelanggaran.png"))); // NOI18N
        lpelanggaran.setText("jLabel1");
        lpelanggaran.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                lpelanggaranComponentResized(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Pelanggaran Hari ini");

        lbl_angkaKasus.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_angkaKasus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_angkaKasus.setText("0 Siswa");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lpelanggaran, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_angkaKasus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lpelanggaran, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_angkaKasus, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 582, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI Symbol", 3, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("PELANGGARAN TERBARU (LIVE)");

        tblpelanggaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblpelanggaran);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(1104, Short.MAX_VALUE)
                .addComponent(tkelas, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                        .addGap(555, 555, 555))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tkelas)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lsiswaComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_lsiswaComponentResized
     scaleImage(lsiswa,"/resource/siswa.png");
    }//GEN-LAST:event_lsiswaComponentResized

    private void lpelanggaranComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_lpelanggaranComponentResized
        scaleImage(lpelanggaran,"/resource/pelanggaran.png");
    }//GEN-LAST:event_lpelanggaranComponentResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_angkaKasus;
    private javax.swing.JLabel lbl_angkaSiswa;
    private javax.swing.JLabel lpelanggaran;
    private javax.swing.JLabel lsiswa;
    private javax.swing.JTable tblpelanggaran;
    private javax.swing.JLabel tkelas;
    // End of variables declaration//GEN-END:variables
}
