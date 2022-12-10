/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import DTO.KhachThue;
import DTO.PhongTro;
import static GUI.FormDaiDien.cbbDaiDien;
import static GUI.HopDong.cbbMaKhachHang;
import static GUI.HopDong.cbbPhong;
import java.util.ArrayList;

/**
 *
 * @author quang nguyen
 */
public class FormChiTiet extends javax.swing.JDialog {

    /**
     * Creates new form FormChiTiet
     */
    public FormChiTiet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
        jButton1 = new javax.swing.JButton();
        btnThemKhach = new javax.swing.JButton();
        btnHopDong = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        TenPhong = new javax.swing.JLabel();
        btnDichVu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnDaiDien = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setText("Thông tin phòng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnThemKhach.setText("Thêm Khách");
        btnThemKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhachActionPerformed(evt);
            }
        });

        btnHopDong.setText("Hợp Đồng");
        btnHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHopDongActionPerformed(evt);
            }
        });

        btnHoaDon.setText("Hóa Đơn");
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });

        TenPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TenPhong.setText("jLabel2");

        btnDichVu.setText("Dịch Vụ");
        btnDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDichVuActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smart-home-128px.png"))); // NOI18N

        btnDaiDien.setText(" Đại Diện");
        btnDaiDien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaiDienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(TenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDichVu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHopDong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemKhach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(100, 100, 100))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(TenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(16, 16, 16)
                        .addComponent(btnThemKhach)
                        .addGap(18, 18, 18)
                        .addComponent(btnHopDong)
                        .addGap(18, 18, 18)
                        .addComponent(btnHoaDon)
                        .addGap(20, 20, 20)
                        .addComponent(btnDichVu))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDaiDien)))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ChiTietPhong ctp = new ChiTietPhong(null, true);
        ctp.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        // TODO add your handling code here:
        new HoaDon(null, true).setVisible(true);
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHopDongActionPerformed
        // TODO add your handling code here:
        HopDong hopDong = new HopDong(null, true);
        ArrayList<DTO.PhongTro> arrPT =  BLL.BLLPhongTro.GetAll();
        
        jdlKhachThue khachThue = new jdlKhachThue(null, true);
        for (PhongTro phongTro : arrPT) {
            if(phongTro.getTenPhong().equals(TenPhong.getText())){
                hopDong.JLTenPhong.setText(phongTro.getTenPhong());
                ArrayList<KhachThue> arrKT = BLL.BLLKhachThue.FindByMangMaOrTen(phongTro.getMaPhong());  
                  BLL.BLLKhachThue.doComboBox(arrKT, cbbMaKhachHang);
                    ArrayList<PhongTro> arrOnlyPT = BLL.BLLPhongTro.FindOnlyMaPhong(TenPhong.getText());
                    BLL.BLLPhongTro.doComboBox(arrOnlyPT, cbbPhong);
               
            }
        }
        hopDong.setVisible(true);
    }//GEN-LAST:event_btnHopDongActionPerformed

    private void btnThemKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhachActionPerformed
        // TODO add your handling code here:
        
        ArrayList<DTO.PhongTro> arrPT =  BLL.BLLPhongTro.GetAll();
        
        jdlKhachThue khachThue = new jdlKhachThue(null, true);
        for (PhongTro phongTro : arrPT) {
            if(phongTro.getTenPhong().equals(TenPhong.getText())){
                khachThue.txtMaKH.setText(phongTro.getMaPhong());
                khachThue.JLTenPhong.setText(phongTro.getTenPhong());
                
            }
        }
       
       
       khachThue.setVisible(true);
    }//GEN-LAST:event_btnThemKhachActionPerformed

    private void btnDaiDienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaiDienActionPerformed
        // TODO add your handling code here:
        ArrayList<DTO.PhongTro> arrPT =  BLL.BLLPhongTro.GetAll();
        FormDaiDien daidien = new FormDaiDien(null, true);
        for (PhongTro phongTro : arrPT) {
            if(phongTro.getTenPhong().equals(TenPhong.getText())){
                daidien.JLabelDaiDien.setText(phongTro.getMaPhong());
                ArrayList<KhachThue> arrKT = BLL.BLLKhachThue.FindByMangMaOrTen(phongTro.getMaPhong());
        BLL.BLLKhachThue.doComboBox(arrKT, cbbDaiDien);
            }
        }
        
        daidien.setVisible(true);
    }//GEN-LAST:event_btnDaiDienActionPerformed

    private void btnDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDichVuActionPerformed
        // TODO add your handling code here:
        jdlThemDichVuPhong ctp = new jdlThemDichVuPhong(null, true);
        ctp.setVisible(true);
    }//GEN-LAST:event_btnDichVuActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormChiTiet dialog = new FormChiTiet(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel TenPhong;
    private javax.swing.JButton btnDaiDien;
    private javax.swing.JButton btnDichVu;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnHopDong;
    private javax.swing.JButton btnThemKhach;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
