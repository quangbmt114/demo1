/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import DTO.KhachThue;
import DTO.PhongTro;
import static GUI.HopDong.cbbMaKhachHang;
import static GUI.HopDong.cbbPhong;
import helper.ChuyenDoi;
import helper.ThongBao;
import java.util.ArrayList;
import helper.sqlHelper;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author quang nguyen
 */
public class jdlKhachThue extends javax.swing.JDialog {

    /**
     * Creates new form KhachThue2
     */
    public jdlKhachThue(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ImageIcon icon = new ImageIcon("src/images/blue-home-icon.png");
        setIconImage(icon.getImage());
//        ArrayList<KhachThue> arrKH = BLL.BLLKhachThue.GetAll();

//        jLabel90.setVisible(false);
//        rbNam.setVisible(false);
//        rbNu.setVisible(false);
    }

    String AnhCD;
    String AnhCMNDTrc;
    String AnhCMNDSau;

    public boolean validateform() {

        if (txtMaKH.getText().equals("") || txtTenKH.getText().equals("") || txtCMND.getText().equals("") || txtSDT.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ĐẦY ĐỦ THÔNG TIN!");
            return false;
        }
        return true;

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
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        panelKhachThue2 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        txtCMND = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        JLHinhAnh = new javax.swing.JLabel();
        btnHInhAnhCD = new javax.swing.JButton();
        btnThemKH = new javax.swing.JButton();
        btnResetKH = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        rbDangThue = new javax.swing.JRadioButton();
        rbDaTra = new javax.swing.JRadioButton();
        JDNgaySinh = new com.toedter.calendar.JDateChooser();
        JDNgayVao = new com.toedter.calendar.JDateChooser();
        JLHinhCMNDMatSau = new javax.swing.JLabel();
        btnHInhCMNDTrc = new javax.swing.JButton();
        JLHinhCMNDMatTrc = new javax.swing.JLabel();
        btnHInhCMNDSau = new javax.swing.JButton();
        jLabel89 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        rbNam = new javax.swing.JRadioButton();
        rbNu = new javax.swing.JRadioButton();
        TBEmail = new javax.swing.JLabel();
        JLTenPhong = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelKhachThue2.setBackground(new java.awt.Color(0, 204, 255));

        jLabel29.setBackground(new java.awt.Color(0, 204, 255));
        jLabel29.setFont(new java.awt.Font("UTM Times", 1, 36)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 0, 51));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("KHÁCH THUÊ");
        jLabel29.setOpaque(true);

        jPanel18.setBackground(new java.awt.Color(0, 204, 255));

        jLabel79.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel79.setText("Mã KH");

        jLabel80.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel80.setText("Tên KH");

        txtTenKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenKHKeyReleased(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel81.setText("Ngày sinh");

        jLabel82.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel82.setText("CCCD/CMND");

        txtCMND.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCMNDKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCMNDKeyTyped(evt);
            }
        });

        jLabel84.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel84.setText("SDT");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSDTKeyTyped(evt);
            }
        });

        jLabel85.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel85.setText("Địa chỉ");

        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        JLHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLHinhAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/man-128px.png"))); // NOI18N

        btnHInhAnhCD.setBackground(new java.awt.Color(255, 255, 255));
        btnHInhAnhCD.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnHInhAnhCD.setText("Ảnh CD");
        btnHInhAnhCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHInhAnhCDActionPerformed(evt);
            }
        });

        btnThemKH.setBackground(new java.awt.Color(255, 255, 255));
        btnThemKH.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnThemKH.setText("Thêm KH");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        btnResetKH.setBackground(new java.awt.Color(255, 255, 255));
        btnResetKH.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnResetKH.setText("Làm Mới");
        btnResetKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetKHActionPerformed(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel86.setText("Ngày vào:");

        jLabel87.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel87.setText("Tình trạng");

        rbDangThue.setBackground(new java.awt.Color(0, 204, 255));
        buttonGroup2.add(rbDangThue);
        rbDangThue.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        rbDangThue.setText("Đang thuê");

        rbDaTra.setBackground(new java.awt.Color(0, 204, 255));
        buttonGroup2.add(rbDaTra);
        rbDaTra.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        rbDaTra.setText("Đã trả");
        rbDaTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDaTraActionPerformed(evt);
            }
        });

        JDNgaySinh.setDateFormatString("dd/MM/yyyy");

        JDNgayVao.setDateFormatString("dd/MM/yyyy");

        JLHinhCMNDMatSau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLHinhCMNDMatSau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/man-128px.png"))); // NOI18N

        btnHInhCMNDTrc.setBackground(new java.awt.Color(255, 255, 255));
        btnHInhCMNDTrc.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnHInhCMNDTrc.setText("CMND trước");
        btnHInhCMNDTrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHInhCMNDTrcActionPerformed(evt);
            }
        });

        JLHinhCMNDMatTrc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLHinhCMNDMatTrc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/man-128px.png"))); // NOI18N

        btnHInhCMNDSau.setBackground(new java.awt.Color(255, 255, 255));
        btnHInhCMNDSau.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnHInhCMNDSau.setText("CMND Sau");
        btnHInhCMNDSau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHInhCMNDSauActionPerformed(evt);
            }
        });

        jLabel89.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel89.setText("Email");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        jLabel90.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel90.setText("Giới tính");

        rbNam.setBackground(new java.awt.Color(0, 204, 255));
        buttonGroup3.add(rbNam);
        rbNam.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        rbNam.setText("Nam");

        rbNu.setBackground(new java.awt.Color(0, 204, 255));
        buttonGroup3.add(rbNu);
        rbNu.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        rbNu.setText("Nữ");

        TBEmail.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel79)
                            .addComponent(jLabel80)
                            .addComponent(jLabel82)
                            .addComponent(jLabel84)
                            .addComponent(jLabel89)
                            .addComponent(jLabel85)
                            .addComponent(jLabel81)
                            .addComponent(jLabel86)
                            .addComponent(jLabel90)
                            .addComponent(jLabel87))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TBEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JDNgayVao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JDNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCMND, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbNam)
                                    .addComponent(rbDangThue))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbDaTra)
                                    .addComponent(rbNu))
                                .addGap(37, 37, 37)))))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(JLHinhCMNDMatTrc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JLHinhCMNDMatSau, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JLHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnHInhAnhCD, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(btnResetKH, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(btnHInhCMNDTrc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHInhCMNDSau, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel79)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel80)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel82)
                            .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel84)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(JLHinhCMNDMatSau)
                    .addComponent(JLHinhCMNDMatTrc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89)
                    .addComponent(btnHInhCMNDTrc)
                    .addComponent(btnHInhCMNDSau))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TBEmail)
                .addGap(4, 4, 4)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel85)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel81)
                            .addComponent(JDNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel86)
                            .addComponent(JDNgayVao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel90)
                            .addComponent(rbNam)
                            .addComponent(rbNu)))
                    .addComponent(JLHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(rbDangThue)
                    .addComponent(rbDaTra)
                    .addComponent(btnHInhAnhCD))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemKH)
                    .addComponent(btnResetKH))
                .addGap(314, 314, 314))
        );

        JLTenPhong.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        JLTenPhong.setForeground(new java.awt.Color(255, 51, 51));
        JLTenPhong.setText("jLabel1");

        javax.swing.GroupLayout panelKhachThue2Layout = new javax.swing.GroupLayout(panelKhachThue2);
        panelKhachThue2.setLayout(panelKhachThue2Layout);
        panelKhachThue2Layout.setHorizontalGroup(
            panelKhachThue2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelKhachThue2Layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelKhachThue2Layout.setVerticalGroup(
            panelKhachThue2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhachThue2Layout.createSequentialGroup()
                .addGroup(panelKhachThue2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLTenPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelKhachThue2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelKhachThue2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void btnHInhAnhCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHInhAnhCDActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser("./src/images");
        chooser.showOpenDialog(btnHInhAnhCD);

        File file = chooser.getSelectedFile();
        String st = file.getName();
        AnhCD = st;
        ImageIcon icon = new ImageIcon(new ImageIcon(file.getPath()).getImage().getScaledInstance(140, 200, Image.SCALE_SMOOTH));
        JLHinhAnh.setIcon(icon);
        System.out.println(st);
    }//GEN-LAST:event_btnHInhAnhCDActionPerformed
    public static boolean check = false;
    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        String MaNguoiThue;
        String TenNguoiThue;
        String CMND;
        String SDT;
        String DiaChi;
        String NgaySinh;
        String Email;
        String NgayTaoDT;
        boolean GioiTinh;
        boolean TrangThai;

        String TenPhong = String.valueOf(JLTenPhong.getText());

        //            String NgayTra;
        if (validateform()) {
            MaNguoiThue = txtMaKH.getText();
            TenNguoiThue = txtTenKH.getText();
            DiaChi = txtDiaChi.getText();
            SDT = txtSDT.getText();
            Email = txtEmail.getText();
            if (rbNu.isSelected()) {
                GioiTinh = false;
            } else {
                GioiTinh = true;
            }
            if (rbDaTra.isSelected()) {
                TrangThai = true;
            } else {
                TrangThai = false;
            }
            CMND = txtCMND.getText();
            NgaySinh = ChuyenDoi.LayNgayString(JDNgaySinh.getDate());
            NgayTaoDT = ChuyenDoi.LayNgayString(JDNgayVao.getDate());
            KhachThue kh = new KhachThue(MaNguoiThue, TenNguoiThue, CMND, SDT, Email,
                    DiaChi, NgaySinh, NgayTaoDT, GioiTinh, TrangThai, AnhCD, AnhCMNDTrc, AnhCMNDSau);
            // Thêm Khách Thuê
            BLL.BLLKhachThue.Add(kh);
            if (check == true) {
                // truyền dữ liêu sang form Hợp đồng
                // lọc cbb theo Phòng đinh sẵn
                ArrayList<PhongTro> arrPT = BLL.BLLPhongTro.GetAll();
                HopDong hopdong = new HopDong(null, true);
                for (PhongTro pt : arrPT) {

                    if (TenPhong.equals(pt.getTenPhong())) {
                        ArrayList<KhachThue> arrKT = BLL.BLLKhachThue.FindByMaKhachThue(pt.getMaPhong());
                        BLL.BLLKhachThue.doComboBox(arrKT, cbbMaKhachHang);
                        ArrayList<PhongTro> arrOnlyPT = BLL.BLLPhongTro.FindByMaPhong(pt.getMaPhong());
                        BLL.BLLPhongTro.doComboBox(arrOnlyPT, cbbPhong);
                        hopdong.JLTenPhong.setText(TenPhong);
                    }
                }
                hopdong.setVisible(true);
                setVisible(false);
            }

//            for (int i = 0; i < cbbMaKhachHang.getItemCount(); i++) {         
//                if (cbbMaKhachHang.getItemAt(i).equals(MaNguoiThue)) {
//                    cbbMaKhachHang.setSelectedIndex(i);
//                }
//            }
        }


    }//GEN-LAST:event_btnThemKHActionPerformed

    private void rbDaTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDaTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbDaTraActionPerformed

    private void btnHInhCMNDTrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHInhCMNDTrcActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser("./src/images");
        chooser.showOpenDialog(btnHInhCMNDTrc);

        File file = chooser.getSelectedFile();
        String st = file.getName();
        AnhCMNDTrc = st;
        ImageIcon icon = new ImageIcon(new ImageIcon(file.getPath()).getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH));
        JLHinhCMNDMatTrc.setIcon(icon);
        System.out.println(st);
    }//GEN-LAST:event_btnHInhCMNDTrcActionPerformed

    private void btnHInhCMNDSauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHInhCMNDSauActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser("./src/images");
        chooser.showOpenDialog(btnHInhCMNDSau);

        File file = chooser.getSelectedFile();
        String st = file.getName();
        AnhCMNDSau = st;
        ImageIcon icon = new ImageIcon(new ImageIcon(file.getPath()).getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH));
        JLHinhCMNDMatSau.setIcon(icon);
        System.out.println(st);
    }//GEN-LAST:event_btnHInhCMNDSauActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtCMNDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCMNDKeyTyped

        char a = evt.getKeyChar();
        if (!Character.isDigit(a)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCMNDKeyTyped

    private void txtCMNDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCMNDKeyReleased
        String CMND = txtCMND.getText();
        if (CMND.length() > 14) {
            JOptionPane.showMessageDialog(null, "Số CMND tối đa 14 số ");
        }
    }//GEN-LAST:event_txtCMNDKeyReleased

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        String SDT = txtSDT.getText();
        if (SDT.length() > 10) {
            JOptionPane.showMessageDialog(null, "Số điện thoại tối đa 10 số ");
        }
    }//GEN-LAST:event_txtSDTKeyReleased

    private void txtSDTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyTyped
        char a = evt.getKeyChar();
        if (!Character.isDigit(a)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSDTKeyTyped

    private void txtTenKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKHKeyReleased
        String Name = txtTenKH.getText();
        String CheckName = "\\w[^0-9]{0,20}";
        if (!Name.matches(CheckName)) {
            JOptionPane.showMessageDialog(null, "Tên phải là chữ");
        }


    }//GEN-LAST:event_txtTenKHKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        String Email = "^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,3}$";
        Pattern email = Pattern.compile(Email);
        Matcher match = email.matcher(txtEmail.getText());
        if (!match.matches()) {
            TBEmail.setText("Nhập chưa đúng định dạng Email!!");
        } else {
            TBEmail.setText(null);
        }
    }//GEN-LAST:event_txtEmailKeyReleased
    public void LamMoi() {
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtCMND.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        JDNgaySinh.setDate(null);
        JDNgayVao.setDate(null);
        String fileAnhcd = "./src/images/man-128px.png";
        ImageIcon iconAnhCD = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(140, 180, Image.SCALE_SMOOTH));
        ImageIcon iconAnhCMNDtrc = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
        ImageIcon iconAnhCMNDSau = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
        JLHinhAnh.setIcon(iconAnhCD);
        JLHinhCMNDMatTrc.setIcon(iconAnhCMNDtrc);
        JLHinhCMNDMatSau.setIcon(iconAnhCMNDSau);
    }
    private void btnResetKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetKHActionPerformed
        // TODO add your handling code here:
        LamMoi();

    }//GEN-LAST:event_btnResetKHActionPerformed
    private void formWindowOpened(java.awt.event.WindowEvent evt) {

    }

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
            java.util.logging.Logger.getLogger(KhachThue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachThue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachThue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachThue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdlKhachThue dialog = new jdlKhachThue(new javax.swing.JFrame(), true);
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
    private com.toedter.calendar.JDateChooser JDNgaySinh;
    private com.toedter.calendar.JDateChooser JDNgayVao;
    private javax.swing.JLabel JLHinhAnh;
    private javax.swing.JLabel JLHinhCMNDMatSau;
    private javax.swing.JLabel JLHinhCMNDMatTrc;
    public static javax.swing.JLabel JLTenPhong;
    private javax.swing.JLabel TBEmail;
    private javax.swing.JButton btnHInhAnhCD;
    private javax.swing.JButton btnHInhCMNDSau;
    private javax.swing.JButton btnHInhCMNDTrc;
    private javax.swing.JButton btnResetKH;
    private javax.swing.JButton btnThemKH;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel panelKhachThue2;
    private javax.swing.JRadioButton rbDaTra;
    private javax.swing.JRadioButton rbDangThue;
    private javax.swing.JRadioButton rbNam;
    private javax.swing.JRadioButton rbNu;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    public static javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
