/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyekkasiralfacom;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.awt.BorderLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;



/**
 *
 * @author HALIM
 */
public class DasboardPemilik extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DasboardPemilik.class.getName());

    /**
     * Creates new form DasboardPemilik
     */
    public DasboardPemilik() {
        initComponents();
        setLocationRelativeTo(null);
        
        initTable();
        loadTahunterlaris();
        loadTahunTerjual();
        loadTahunPendapatan();
        loadTahunKasir();
        isiTahun();
        tampilkanGrafikPenjualan();
    }
    
     private void initTable() {
        tblProdukTerlaris.setModel(new DefaultTableModel(
            new Object[]{"Nama Produk", "Total Terjual"}, 0
        ));
        
         tblProdukTerjual.setModel(new DefaultTableModel(
            new Object[]{"Nama Produk", "Jumlah Transaksi"}, 0
        ));
     
       tblKInerjaKasir.setModel(new DefaultTableModel(
            new Object[]{"Nama Kasir", "Jumlah Transaksi"}, 0
    ));
    }
     
     private void loadTahunterlaris() {
    cmbterlaris.removeAllItems();
    int tahunSekarang = java.time.Year.now().getValue();
    for (int i = tahunSekarang - 3; i <= tahunSekarang + 2; i++) {
        cmbterlaris.addItem(String.valueOf(i));
    }
}
     private void loadTahunTerjual() {
    terjual.removeAllItems();
    int tahunSekarang = java.time.Year.now().getValue();
    for (int i = tahunSekarang - 3; i <= tahunSekarang + 2; i++) {
        terjual.addItem(String.valueOf(i));
    }
}
     private void loadTahunPendapatan() {
    pendapatan.removeAllItems();
    int tahunSekarang = java.time.Year.now().getValue();
    for (int i = tahunSekarang - 3; i <= tahunSekarang + 2; i++) {
        pendapatan.addItem(String.valueOf(i));
    }
}
     private void loadTahunKasir() {
    cmbKasir.removeAllItems();
    int tahunSekarang = java.time.Year.now().getValue();
    for (int i = tahunSekarang - 3; i <= tahunSekarang + 2; i++) {
        cmbKasir.addItem(String.valueOf(i));
    }
}
     private void isiTahun() {
    cbTahun.removeAllItems();
    int tahunSekarang = java.time.Year.now().getValue();

    for (int i = tahunSekarang; i >= tahunSekarang - 5; i--) {
        cbTahun.addItem(String.valueOf(i));
    }
    cbTahun.setSelectedIndex(0);
}

      private void loadProdukTerlaris(int tahun) {
    try {
        DefaultTableModel model =
            (DefaultTableModel) tblProdukTerlaris.getModel();
        model.setRowCount(0);

        Connection conn = Koneksi.getKoneksi();
        String sql =
            "SELECT p.nama_barang, SUM(dt.qty) AS total_terjual " +
            "FROM detail_transaksi dt " +
            "JOIN products p ON p.id = dt.id_produk " +
            "JOIN transaksi t ON dt.id_transaksi = t.id_transaksi " +
            "WHERE YEAR(t.tanggal) = ? " +
            "GROUP BY p.id " +
            "ORDER BY total_terjual DESC";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, tahun);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("nama_barang"),
                rs.getInt("total_terjual")
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
    
}
      private void loadProdukTerjual(int tahun) {
    try {
        DefaultTableModel model =
            (DefaultTableModel) tblProdukTerjual.getModel();
        model.setRowCount(0);

        Connection conn = Koneksi.getKoneksi();
        String sql =
            "SELECT p.nama_barang, COUNT(DISTINCT t.id_transaksi) AS jumlah_transaksi " +
            "FROM detail_transaksi dt " +
            "JOIN products p ON p.id = dt.id_produk " +
            "JOIN transaksi t ON t.id_transaksi = dt.id_transaksi " +
            "WHERE YEAR(t.tanggal) = ? " +
            "GROUP BY p.id " +
            "ORDER BY jumlah_transaksi DESC";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, tahun);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("nama_barang"),
                rs.getInt("jumlah_transaksi")
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}
      
      private void loadLaba(int tahun) {
    try {
        Connection conn = Koneksi.getKoneksi();
        String sql =
            "SELECT SUM(dt.qty * (p.harga_jual - p.harga_beli)) AS total_laba " +
            "FROM detail_transaksi dt " +
            "JOIN products p ON p.id = dt.id_produk " +
            "JOIN transaksi t ON t.id_transaksi = dt.id_transaksi " +
            "WHERE YEAR(t.tanggal) = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, tahun);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            long laba = rs.getLong("total_laba");
            jLabel4.setText("Rp. " + String.format("%,d", laba));
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}
      private void loadKinerjaKasir(int tahun) {
    try {
        DefaultTableModel model =
            (DefaultTableModel) tblKInerjaKasir.getModel();
        model.setRowCount(0);

        Connection conn = Koneksi.getKoneksi();
        String sql =
            "SELECT u.username AS nama_kasir, COUNT(t.id_transaksi) AS total_transaksi " +
            "FROM transaksi t " +
            "JOIN users u ON u.id = t.id_kasir " +
            "WHERE YEAR(t.tanggal) = ? AND u.role = 'kasir' " +
            "GROUP BY u.id " +
            "ORDER BY total_transaksi DESC";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, tahun);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("nama_kasir"),
                rs.getInt("total_transaksi")
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}
      private void tampilkanGrafikPenjualan() {

    if (cbTahun.getSelectedItem() == null) return;

    String tahun = cbTahun.getSelectedItem().toString();
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    String[] namaBulan = {
        "Jan","Feb","Mar","Apr","Mei","Jun",
        "Jul","Agu","Sep","Okt","Nov","Des"
    };

    int[] totalBulan = new int[12]; // default 0

    try {
        Connection conn = Koneksi.getKoneksi();

        String sql = """
            SELECT MONTH(tanggal) AS bulan, SUM(total) AS total
            FROM transaksi
            WHERE YEAR(tanggal) = ?
            GROUP BY MONTH(tanggal)
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tahun);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int bulan = rs.getInt("bulan"); // 1–12
            totalBulan[bulan - 1] = rs.getInt("total");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }

    // masukkan semua bulan (Jan–Des)
    for (int i = 0; i < 12; i++) {
        dataset.addValue(totalBulan[i], "Penjualan", namaBulan[i]);
    }

    JFreeChart chart = ChartFactory.createBarChart(
        "Grafik Penjualan Tahun " + tahun,
        "Bulan",
        "Total Penjualan",
        dataset
    );

    // === TAMPILKAN ANGKA DI BALOK ===
   CategoryPlot plot = chart.getCategoryPlot();
   BarRenderer renderer = (BarRenderer) plot.getRenderer();

    renderer.setDefaultItemLabelGenerator(
    new StandardCategoryItemLabelGenerator(
        "{2}",
        new java.text.DecimalFormat("#,###")
        )
    );
        renderer.setDefaultItemLabelsVisible(true);


    ChartPanel chartPanel = new ChartPanel(chart);

    panelgrafik.removeAll();
    panelgrafik.setLayout(new BorderLayout());
    panelgrafik.add(chartPanel, BorderLayout.CENTER);
    panelgrafik.validate();
}




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        terjual = new javax.swing.JComboBox<>();
        cmbterlaris = new javax.swing.JComboBox<>();
        j = new javax.swing.JScrollPane();
        tblProdukTerlaris = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProdukTerjual = new javax.swing.JTable();
        pendapatan = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKInerjaKasir = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        cmbKasir = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        panelgrafik = new javax.swing.JPanel();
        cbTahun = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(214, 217, 224));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setText("PRODUK TERLARIS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 18, 0, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel2.setText("PRODUK TERJUAL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 12, 0, 0);
        jPanel2.add(jLabel2, gridBagConstraints);

        terjual.setBackground(new java.awt.Color(153, 153, 153));
        terjual.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        terjual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terjualActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 25, 0, 0);
        jPanel2.add(terjual, gridBagConstraints);

        cmbterlaris.setBackground(new java.awt.Color(153, 153, 153));
        cmbterlaris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbterlarisActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 6, 0, 0);
        jPanel2.add(cmbterlaris, gridBagConstraints);

        tblProdukTerlaris.setModel(new javax.swing.table.DefaultTableModel(
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
        j.setViewportView(tblProdukTerlaris);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 183;
        gridBagConstraints.ipady = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 21, 0);
        jPanel2.add(j, gridBagConstraints);

        tblProdukTerjual.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblProdukTerjual);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.ipady = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 21, 0);
        jPanel2.add(jScrollPane2, gridBagConstraints);

        pendapatan.setBackground(new java.awt.Color(153, 153, 153));
        pendapatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pendapatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendapatanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 57, 0, 59);
        jPanel2.add(pendapatan, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel3.setText("PENDAPATAN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 18, 0, 0);
        jPanel2.add(jLabel3, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Rp. ");

        jLabel5.setText("Hasil dari penjualan atau laba");

        jLabel6.setText("qty × (harga_jual − harga_beli)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 41;
        gridBagConstraints.ipady = 31;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 18, 21, 59);
        jPanel2.add(jPanel3, gridBagConstraints);

        tblKInerjaKasir.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblKInerjaKasir);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 174;
        gridBagConstraints.ipady = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 21, 0);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel7.setText("KINERJA KASIR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 12, 0, 0);
        jPanel2.add(jLabel7, gridBagConstraints);

        cmbKasir.setBackground(new java.awt.Color(153, 153, 153));
        cmbKasir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbKasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKasirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 29, 0, 0);
        jPanel2.add(cmbKasir, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 78;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 6, 0, 0);
        jPanel1.add(jPanel2, gridBagConstraints);

        jButton1.setText("LOG OUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 767, 0, 0);
        jPanel1.add(jButton1, gridBagConstraints);

        javax.swing.GroupLayout panelgrafikLayout = new javax.swing.GroupLayout(panelgrafik);
        panelgrafik.setLayout(panelgrafikLayout);
        panelgrafikLayout.setHorizontalGroup(
            panelgrafikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelgrafikLayout.setVerticalGroup(
            panelgrafikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 893;
        gridBagConstraints.ipady = 370;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 19, 29, 0);
        jPanel1.add(panelgrafik, gridBagConstraints);

        cbTahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTahunActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(39, 10, 0, 0);
        jPanel1.add(cbTahun, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void terjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terjualActionPerformed
           if (terjual.getSelectedItem() != null) {
        int tahun = Integer.parseInt(terjual.getSelectedItem().toString());
        loadProdukTerjual(tahun);
    }
    }//GEN-LAST:event_terjualActionPerformed

    private void pendapatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendapatanActionPerformed
        if (pendapatan.getSelectedItem() != null) {
        int tahun = Integer.parseInt(pendapatan.getSelectedItem().toString());
        loadLaba(tahun);
    }      
    }//GEN-LAST:event_pendapatanActionPerformed

    private void cmbterlarisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbterlarisActionPerformed

    if (cmbterlaris.getSelectedItem() != null) {
        int tahun = Integer.parseInt(cmbterlaris.getSelectedItem().toString());
        loadProdukTerlaris(tahun);
    }
    }//GEN-LAST:event_cmbterlarisActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null,
                "Yakin ingin logout?", "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            new LoginForm().setVisible(true);
            dispose();
        }  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKasirActionPerformed
        if (cmbKasir.getSelectedItem() != null) {
        int tahun = Integer.parseInt(cmbKasir.getSelectedItem().toString());
        loadKinerjaKasir(tahun);
    }
    }//GEN-LAST:event_cmbKasirActionPerformed

    private void cbTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTahunActionPerformed
         if (cbTahun.getSelectedItem() != null) {
        tampilkanGrafikPenjualan();
    }
    }//GEN-LAST:event_cbTahunActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new DasboardPemilik().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbTahun;
    private javax.swing.JComboBox<String> cmbKasir;
    private javax.swing.JComboBox<String> cmbterlaris;
    private javax.swing.JScrollPane j;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelgrafik;
    private javax.swing.JComboBox<String> pendapatan;
    private javax.swing.JTable tblKInerjaKasir;
    private javax.swing.JTable tblProdukTerjual;
    private javax.swing.JTable tblProdukTerlaris;
    private javax.swing.JComboBox<String> terjual;
    // End of variables declaration//GEN-END:variables
}
