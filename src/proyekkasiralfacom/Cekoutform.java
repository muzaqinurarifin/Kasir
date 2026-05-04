/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyekkasiralfacom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author HALIM
 */
public class Cekoutform extends javax.swing.JFrame {
private int hitungTotal() {
    int total = 0;

    for (int i = 0; i < TABEL.getRowCount(); i++) {
        total += Integer.parseInt(
            TABEL.getValueAt(i, 4).toString() // kolom Subtotal
        );
    }
    lblTotal.setText("Rp " + total);
    return total;
}
     private final DefaultTableModel modelCheckout;
     private final DefaultTableModel modelKeranjang;
     
     private String generateKodeReferensi() {
        return "" + System.currentTimeMillis();
    }
     private int hitungTotalProduk() {
        int totalProduk = 0;
        for (int i = 0; i < TABEL.getRowCount(); i++) {
            totalProduk += Integer.parseInt(TABEL.getValueAt(i, 3).toString());
        }
        return totalProduk;
    }

    /**
     * Creates new form NotaForm
     * @param modelKeranjang
     */
public Cekoutform(DefaultTableModel modelKeranjang) {
   initComponents();
        setLocationRelativeTo(null);
        this.modelKeranjang = modelKeranjang;
        modelCheckout = new DefaultTableModel(
            new Object[]{"Kode", "Nama", "Harga", "Jumlah", "Subtotal"}, 0
        );
        TABEL.setModel(modelCheckout);
        for (int i = 0; i < modelKeranjang.getRowCount(); i++) {
            modelCheckout.addRow(new Object[]{
                modelKeranjang.getValueAt(i, 0),
                modelKeranjang.getValueAt(i, 1),
                modelKeranjang.getValueAt(i, 2),
                modelKeranjang.getValueAt(i, 3),
                modelKeranjang.getValueAt(i, 4)
            });
        }
        hitungTotal();
        lblTanggal.setText(
            new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm")
                .format(new java.util.Date())
        );
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABEL = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnCekout = new javax.swing.JButton();
        lblTanggal = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtBayar = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        lblKembali = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(214, 217, 224));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("TOKO ALFACOM");

        TABEL.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TABEL);

        jLabel2.setText("TOTAL  :  ");

        jLabel4.setText("BAYAR  : Rp ");

        jLabel5.setText("KEMBALI  : ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Terima Kasih Telah Belanja");

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setPreferredSize(new java.awt.Dimension(100, 3));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jLabel3.setText("Jl. Gelatik. Desa Pakulaut. Kec Margasari. Tegal ");

        btnCekout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCekout.setText("Cekout");
        btnCekout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekoutActionPerformed(evt);
            }
        });

        lblTanggal.setText("Tanggal ");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("CekOut");

        txtBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBayarActionPerformed(evt);
            }
        });

        lblTotal.setText("Rp");

        lblKembali.setText("Rp");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel6.setText("enter");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(143, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(139, 139, 139))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(lblTanggal)
                            .addComponent(jLabel3)
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblKembali))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblTotal))
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(btnCekout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTanggal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblKembali))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCekout)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBayarActionPerformed
try {
        int bayar = Integer.parseInt(txtBayar.getText());
        int total = hitungTotal();
        int kembali = bayar - total;
        lblKembali.setText("Rp " + kembali);}
        catch (NumberFormatException e) {
        lblKembali.setText("Rp 0");
    }
    }//GEN-LAST:event_txtBayarActionPerformed

    private void btnCekoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekoutActionPerformed
    if (TABEL.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Keranjang kosong!");
            return;
        }
        if (txtBayar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan nominal bayar!");
            return;
        }
        int total = hitungTotal();
        int bayar;
        try {
            bayar = Integer.parseInt(txtBayar.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nominal tidak valid!");
            return;
        }
        if (bayar < total) {
            JOptionPane.showMessageDialog(this, "Uang kurang!");
            return;
        }
        int kembalian = bayar - total;

        // ===============================
        // SESSION KASIR
        // ===============================
        int idKasir = SessionUser.getIdUser();
        String namaKasir = SessionUser.getNamaUser();

        if (idKasir == 0 || namaKasir == null) {
            JOptionPane.showMessageDialog(this, "Session kasir belum ada!");
            return;
        }
        String kodeReferensi = generateKodeReferensi();
        int totalProduk = hitungTotalProduk();
        Connection conn = null;

        try {
            conn = Koneksi.getKoneksi();
            conn.setAutoCommit(false);

            // ===============================
            // INSERT TRANSAKSI
            // ===============================
            String sqlTransaksi = """
                INSERT INTO transaksi
                (tanggal, kode_referensi, total_produk, total, status, id_kasir)
                VALUES (NOW(), ?, ?, ?, ?, ?)
            """;

            PreparedStatement psTrans = conn.prepareStatement(
                sqlTransaksi,
                java.sql.Statement.RETURN_GENERATED_KEYS
            );

            psTrans.setString(1, kodeReferensi);
            psTrans.setInt(2, totalProduk);
            psTrans.setInt(3, total);
            psTrans.setString(4, "BERHASIL");
            psTrans.setInt(5, idKasir);
            psTrans.executeUpdate();

            ResultSet rs = psTrans.getGeneratedKeys();
            rs.next();
            int idTransaksi = rs.getInt(1);

            // ===============================
            // INSERT DETAIL TRANSAKSI
            // ===============================
            for (int i = 0; i < modelKeranjang.getRowCount(); i++) {

                String kodeBarang = modelKeranjang.getValueAt(i, 0).toString();
                
                int qty = Integer.parseInt(modelKeranjang.getValueAt(i, 3).toString());
                

                String sqlProduk = """
                SELECT id, stok, harga_beli, harga_jual
                FROM products
                WHERE kode_barang = ?
                """;

                PreparedStatement psProduk = conn.prepareStatement(sqlProduk);
                psProduk.setString(1, kodeBarang);
                ResultSet rp = psProduk.executeQuery();
                rp.next();

                int idProduk = rp.getInt("id");
                int stok = rp.getInt("stok");
                //int hargaBeli = rp.getInt("harga_beli");
                int hargaJual = rp.getInt("harga_jual");


                if (stok < qty) {
                    throw new SQLException("Stok tidak cukup!");
                }
                String sqlDetail = """
                        INSERT INTO detail_transaksi
                        (id_transaksi, id_produk, qty, harga, subtotal, status)
                        VALUES (?, ?, ?, ?, ?, ?)
                        """;
                        PreparedStatement psDetail = conn.prepareStatement(sqlDetail);
                        psDetail.setInt(1, idTransaksi);
                        psDetail.setInt(2, idProduk);
                        psDetail.setInt(3, qty);
                        psDetail.setInt(4, hargaJual);         
                        psDetail.setInt(5, hargaJual * qty);
                        psDetail.setString(6, "berhasil");
                        psDetail.executeUpdate();



                String sqlStok = "UPDATE products SET stok = stok - ? WHERE id=?";
                PreparedStatement psStok = conn.prepareStatement(sqlStok);
                psStok.setInt(1, qty);
                psStok.setInt(2, idProduk);
                psStok.executeUpdate();
            }

            conn.commit();

            JOptionPane.showMessageDialog(this, "Transaksi berhasil!");

            // ===============================
            // TAMPILKAN NOTA
            // ===============================
            new NotaForm_1(
                modelKeranjang,
                total,
                bayar,
                kembalian,
                kodeReferensi,
                idTransaksi,
                totalProduk
            ).setVisible(true);

            modelKeranjang.setRowCount(0);
            dispose();

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {}
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnCekoutActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TABEL;
    private javax.swing.JButton btnCekout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblKembali;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtBayar;
    // End of variables declaration//GEN-END:variables
}
