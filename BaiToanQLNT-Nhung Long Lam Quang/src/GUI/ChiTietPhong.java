/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import DTO.PhongTro;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author quang nguyen
 */
public class ChiTietPhong extends javax.swing.JDialog {

    /**
     * Creates new form ChiTietPhong
     */
    public ChiTietPhong(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ImageIcon icon = new ImageIcon("src/images/blue-home-icon.png");
        setIconImage(icon.getImage());
        ArrayList<DTO.PhongTro> list = BLL.BLLPhongTro.GetAll();
        txtMaPhong.setText(TrangChu.text);
        for (PhongTro phongTro : list) {
            if(phongTro.getTenPhong().equals(FormChiTiet.TenPhong.getText())){
                txtMaPhong.setText(phongTro.getMaPhong());
                txtTenPhong.setText(phongTro.getTenPhong());
                txtMaLoaiPhong.setText(phongTro.getMaLoaiPhong());
                txtMaKhuTro.setText(phongTro.getMaKhuTro());
                txtViTri.setText(phongTro.getViTri());
                txtDienTich.setText(String.valueOf(phongTro.getDienTich()));
                txtGiuong.setText(String.valueOf(phongTro.getGiuong()));
                txtBan.setText(String.valueOf(phongTro.getBan()));
                txtBongDen.setText(String.valueOf(phongTro.getBongDen()));
                txtCuaKinh.setText(String.valueOf(phongTro.getKinhCua()));
                txtTrangThai.setText(phongTro.isTrangThai()?"Đang sử dụng":"Không sử dụng");
            }
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMaPhong = new javax.swing.JLabel();
        txtTenPhong = new javax.swing.JLabel();
        txtMaLoaiPhong = new javax.swing.JLabel();
        txtMaKhuTro = new javax.swing.JLabel();
        txtViTri = new javax.swing.JLabel();
        txtDienTich = new javax.swing.JLabel();
        txtGiuong = new javax.swing.JLabel();
        txtBan = new javax.swing.JLabel();
        txtBongDen = new javax.swing.JLabel();
        txtCuaKinh = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CHI TIẾT PHÒNG");

        jLabel2.setText("Mã Phòng");

        jLabel3.setText("Giường");

        jLabel4.setText("Tên Phòng");

        jLabel5.setText("Mã Loại phòng");

        jLabel6.setText("Mã khu trọ");

        jLabel7.setText("Vị Trí");

        jLabel8.setText("Diện tích");

        jLabel9.setText("Bàn");

        jLabel10.setText("Bóng đèn");

        jLabel11.setText("Cửa kính");

        jLabel13.setText("Trạng thái");

        txtMaPhong.setText("txtMaPhong");

        txtTenPhong.setText("txtTenPhong");

        txtMaLoaiPhong.setText("txtMaLoaiPhong");

        txtMaKhuTro.setText("txtMaKhuTro");

        txtViTri.setText("txtViTri");

        txtDienTich.setText("txtDienTich");

        txtGiuong.setText("txtGiuong");

        txtBan.setText("txtBan");

        txtBongDen.setText("txtBongDen");

        txtCuaKinh.setText("txtCuaKinh");

        txtTrangThai.setText("txtTrangThai");

        jButton1.setText("Sửa Phòng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTrangThai)
                            .addComponent(txtCuaKinh)
                            .addComponent(txtBongDen)
                            .addComponent(txtBan)
                            .addComponent(txtGiuong)
                            .addComponent(txtDienTich)
                            .addComponent(txtViTri)
                            .addComponent(txtMaKhuTro)
                            .addComponent(txtMaLoaiPhong)
                            .addComponent(txtTenPhong)
                            .addComponent(txtMaPhong)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaPhong))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenPhong))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaLoaiPhong))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMaKhuTro))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtViTri))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDienTich))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtGiuong))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtBan))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtBongDen))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCuaKinh))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTrangThai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(26, 26, 26))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new ChinhSuaPhong(null, true).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ChiTietPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChiTietPhong dialog = new ChiTietPhong(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel txtBan;
    private javax.swing.JLabel txtBongDen;
    private javax.swing.JLabel txtCuaKinh;
    private javax.swing.JLabel txtDienTich;
    private javax.swing.JLabel txtGiuong;
    private javax.swing.JLabel txtMaKhuTro;
    private javax.swing.JLabel txtMaLoaiPhong;
    public static javax.swing.JLabel txtMaPhong;
    private javax.swing.JLabel txtTenPhong;
    private javax.swing.JLabel txtTrangThai;
    private javax.swing.JLabel txtViTri;
    // End of variables declaration//GEN-END:variables
}
