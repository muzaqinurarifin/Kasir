/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyekkasiralfacom;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;


/**
 *
 * @author HALIM
 */
public class ManajemenProdukForm extends javax.swing.JFrame {
     DefaultTableModel model;
       private static final Logger logger = Logger.getLogger(ManajemenProdukForm.class.getName());

    public ManajemenProdukForm() {
        initComponents();
        setLocationRelativeTo(null);
        
        model = new DefaultTableModel();
        model.addColumn("ID"); // disembunyikan
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Deskripsi");
        model.addColumn("Kategori");
        model.addColumn("Harga Jual");
        model.addColumn("Stok");
        model.addColumn("Status");

        jTabel.setModel(model);
        loadData();

        // sembunyikan kolom ID
        jTabel.getColumnModel().getColumn(0).setMinWidth(0);
        jTabel.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    private void loadData() {
        model.setRowCount(0);

        try {
            Connection conn = Koneksi.getKoneksi();
            String sql = """
                SELECT 
                    p.id,
                    p.kode_barang,
                    p.nama_barang,
                    p.deskripsi,
                    k.nama_kategori,
                    p.harga_jual,
                    p.stok,
                    p.status
                FROM products p
                LEFT JOIN kategori k ON p.kategori_id = k.id
                ORDER BY p.id DESC
            """;

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("deskripsi"),
                    rs.getString("nama_kategori"),
                    rs.getInt("harga_jual"),
                    rs.getInt("stok"),
                    rs.getString("status")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        btnTambahProduk = new javax.swing.JButton();
        btnHapusProduk = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        JTable = new javax.swing.JScrollPane();
        jTabel = new javax.swing.JTable();
        btnBeranda = new javax.swing.JButton();
        btnEditProduk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("DATA PRODUK");

        jLabel2.setText("Cari Nama Produk");

        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnTambahProduk.setText("Tambah Produk");
        btnTambahProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahProdukActionPerformed(evt);
            }
        });

        btnHapusProduk.setText("Hapus Produk");
        btnHapusProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusProdukActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jTabel.setModel(new javax.swing.table.DefaultTableModel(
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
        JTable.setViewportView(jTabel);

        btnBeranda.setText("Beranda");
        btnBeranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBerandaActionPerformed(evt);
            }
        });

        btnEditProduk.setText("Edit Produk");
        btnEditProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProdukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JTable, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnTambahProduk)
                .addGap(6, 6, 6)
                .addComponent(btnHapusProduk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditProduk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBeranda)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari)
                    .addComponent(btnTambahProduk)
                    .addComponent(btnHapusProduk)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRefresh)
                        .addComponent(btnBeranda)
                        .addComponent(btnEditProduk)))
                .addGap(18, 18, 18)
                .addComponent(JTable, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed

    }//GEN-LAST:event_txtCariActionPerformed

    private void btnTambahProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahProdukActionPerformed
    new TambahProdukForm().setVisible(true);
    }//GEN-LAST:event_btnTambahProdukActionPerformed

    private void btnHapusProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusProdukActionPerformed
    int selectedRow = jTabel.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih Produk yang ingin dihapus!");
        return;
    }
    int idProduk = Integer.parseInt(
    jTabel.getValueAt(selectedRow, 0).toString()
    );

    try {
        Connection conn = Koneksi.getKoneksi();
        String sql = "DELETE FROM products WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, idProduk);

        pst.executeUpdate();
        JOptionPane.showMessageDialog(this, "Produk berhasil dihapus!");

        loadData(); // refresh tabel
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btnHapusProdukActionPerformed

    private void btnBerandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBerandaActionPerformed
    new BerandaAdmin().setVisible(true);
    dispose();
    }//GEN-LAST:event_btnBerandaActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
    loadData();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
 String keyword = txtCari.getText();
        model.setRowCount(0);

    try {
        String sql = """
            SELECT 
                p.id,
                p.kode_barang,
                p.nama_barang,
                p.deskripsi,
                k.nama_kategori,
                p.harga_jual,
                p.stok,
                p.status
            FROM products p
            LEFT JOIN kategori k ON p.kategori_id = k.id
            WHERE p.nama_barang LIKE ? OR p.deskripsi LIKE ?
            ORDER BY p.id DESC
        """;

        Connection conn = Koneksi.getKoneksi();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("kode_barang"),
                rs.getString("nama_barang"),
                rs.getString("deskripsi"),
                rs.getString("nama_kategori"),
                rs.getInt("harga_jual"),
                rs.getInt("stok"),
                rs.getString("status")
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnEditProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProdukActionPerformed
    int row = jTabel.getSelectedRow();

    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Pilih produk terlebih dahulu!");
        return;
    }

    int idProduk = Integer.parseInt(
        jTabel.getValueAt(row, 0).toString()
    );

    new EditProduk(idProduk).setVisible(true);
    dispose();
    }//GEN-LAST:event_btnEditProdukActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new ManajemenProdukForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JTable;
    private javax.swing.JButton btnBeranda;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnEditProduk;
    private javax.swing.JButton btnHapusProduk;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnTambahProduk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTable jTabel;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
