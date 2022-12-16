/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import DTO.*;
import static GUI.TrangChu.tblPhongTro;
import helper.ChuyenDoi;
import helper.ThongBao;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author quang nguyen
 */
public class HopDong extends javax.swing.JDialog {

    /**
     * Creates new form HopDong
     */
   
   ArrayList<KhachThue> arrKT =BLL.BLLKhachThue.GetAll();
    ArrayList<PhongTro> arrPT = BLL.BLLPhongTro.GetAll();
    ArrayList<HoatDongThuePhong> arrHD = BLL.BLLHoatDongThuePhong.GetAll();
    String maKhachthue;
    public HopDong(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
        ImageIcon icon = new ImageIcon("src/images/blue-home-icon.png");
        setIconImage(icon.getImage());
        opened();
        
    }

    public void opened() {
        //set kích thước table

        //dổ dữ liệu vào bảng
        BLL.BLLKhachThue.doComboBox(arrKT, cbbMaKhachHang);
            BLL.BLLPhongTro.doComboBox(arrPT, cbbPhong);
        //set default value
        dateNgayKy.setDate(new Date());
        rbDangThueAC.setSelected(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGTrangThai = new javax.swing.ButtonGroup();
        btnGGioiTinh = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btResetNgayTraHD = new javax.swing.JButton();
        dateNgayKy = new com.toedter.calendar.JDateChooser();
        dateNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel51 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel52 = new javax.swing.JLabel();
        rbDangThueAC = new javax.swing.JRadioButton();
        btnTao = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        btnLamMoi1 = new javax.swing.JButton();
        txtMaHopDong = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        cbbMaKhachHang = new javax.swing.JComboBox<>();
        txtSDT = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtCMND = new javax.swing.JLabel();
        dateNgaySinh = new com.toedter.calendar.JDateChooser();
        txtDiaChi = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        cbbPhong = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        txtDienTich = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtGiaPhong = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtTenNguoiThueHD = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        JLmaKhachThue = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        JLTenPhong = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btResetNgayTraHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh-16px.png"))); // NOI18N
        btResetNgayTraHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetNgayTraHDActionPerformed(evt);
            }
        });

        dateNgayKy.setDateFormatString("dd/MM/yyyy");

        dateNgayKetThuc.setDateFormatString("dd/MM/yyyy");

        jLabel51.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel51.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane4.setViewportView(txtGhiChu);

        jLabel52.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel52.setText("Trạng Thái");

        rbDangThueAC.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        rbDangThueAC.setText("Đang thuê");

        btnTao.setBackground(new java.awt.Color(255, 255, 255));
        btnTao.setFont(new java.awt.Font("UTM Times", 1, 12)); // NOI18N
        btnTao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/notebook_1.png"))); // NOI18N
        btnTao.setText(" Tạo HĐ");
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel40.setText("Mã HĐ");

        btnLamMoi1.setBackground(new java.awt.Color(255, 255, 255));
        btnLamMoi1.setFont(new java.awt.Font("UTM Times", 1, 12)); // NOI18N
        btnLamMoi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        btnLamMoi1.setText("Làm mới");
        btnLamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi1ActionPerformed(evt);
            }
        });

        txtMaHopDong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaHopDongKeyReleased(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel41.setText("Tên KH");

        cbbMaKhachHang.setFont(new java.awt.Font("UTM Times", 0, 12)); // NOI18N
        cbbMaKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMaKhachHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbMaKhachHangItemStateChanged(evt);
            }
        });

        txtSDT.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtSDT.setText("   ");

        jLabel42.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel42.setText("Ngày Sinh");

        txtCMND.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtCMND.setText("   ");

        dateNgaySinh.setDateFormatString("dd/MM/yyyy");

        txtDiaChi.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtDiaChi.setText("   ");

        jLabel43.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel43.setText("SĐT");

        cbbPhong.setFont(new java.awt.Font("UTM Times", 0, 12)); // NOI18N
        cbbPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbPhong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbPhongItemStateChanged(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel44.setText("Địa chỉ");

        txtDienTich.setFont(new java.awt.Font("UTM Times", 1, 12)); // NOI18N
        txtDienTich.setText("  ");

        jLabel45.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel45.setText("Phòng");

        txtGiaPhong.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtGiaPhong.setText("  ");

        jLabel46.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel46.setText("Giá phòng");

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh-16px.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel47.setText("CCCD");

        jLabel77.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel77.setText("Mã KH");

        jLabel48.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel48.setText("Diện tích");

        txtTenNguoiThueHD.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtTenNguoiThueHD.setText("  ");

        jLabel49.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel49.setText("Ngày Kí");

        jLabel50.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel50.setText("Ngày Hết Hạn");

        JLmaKhachThue.setText(" ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45)
                            .addComponent(jLabel46)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50)
                            .addComponent(jLabel51)
                            .addComponent(jLabel52)
                            .addComponent(jLabel77))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(cbbPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel48)
                                .addGap(5, 5, 5)
                                .addComponent(txtDienTich, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
                            .addComponent(txtGiaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel47)
                                .addGap(24, 24, 24)
                                .addComponent(txtCMND, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTenNguoiThueHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbMaKhachHang, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMaHopDong))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5)
                                .addGap(39, 39, 39))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rbDangThueAC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JLmaKhachThue))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnLamMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                        .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(dateNgayKy, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btResetNgayTraHD))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btnTao, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel40)
                        .addComponent(txtMaHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel77)
                    .addComponent(cbbMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel41)
                    .addComponent(txtTenNguoiThueHD))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel47)
                    .addComponent(txtSDT)
                    .addComponent(txtCMND))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtDiaChi))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel48)
                    .addComponent(cbbPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDienTich))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel46)
                    .addComponent(txtGiaPhong))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel49)
                    .addComponent(dateNgayKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel50)
                        .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btResetNgayTraHD))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel51)))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(rbDangThueAC)
                    .addComponent(JLmaKhachThue))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(btnLamMoi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText(" HỢP ĐỒNG THUÊ");

        JLTenPhong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        JLTenPhong.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JLTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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

    private void btResetNgayTraHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetNgayTraHDActionPerformed
        // TODO add your handling code here:
        rbDangThueAC.setSelected(false);
        dateNgayKetThuc.setDate(null);
    }//GEN-LAST:event_btResetNgayTraHDActionPerformed

    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed
        // TODO add your handling code here:
        if (cbbMaKhachHang.getSelectedIndex() > 0 && cbbPhong.getSelectedIndex() > 0) {
            HoatDongThuePhong hoatDong = LayDataForm();
            if (hoatDong != null) {
                Date ngaykt = dateNgayKetThuc.getDate();
                Date ngayky = dateNgayKy.getDate();
                if ((new Date().before(ngaykt) && ngaykt != null) || new Date().before(ngayky)) {
                    ThongBao.ThongBaoDonGian("Thông báo", "Ngày ký/ ngày kết thúc không được sau ngày hiện tại");
                    return;
                }
                if (dateNgayKetThuc.getDate() != null) {
                    if (ngayky.after(ngaykt)) {
                        ThongBao.ThongBaoDonGian("Thông báo", "Ngày ký không đước sau ngày kết thúc!");
                        return;
                    }
                }
                if (!BLL.BLLHoatDongThuePhong.CheckMaHopDong(hoatDong.getMaHopDong())) {
                    ThongBao.ThongBaoDonGian("Thông báo", "Mã hợp đồng không hợp lệ!   ");

                } else {
                    ThongBao.ThongBaoDonGian("Thông báo", "Mã hợp đồng hợp lệ, đã thêm!");
                    if (hoatDong.getNgayTra() != null) {
                        BLL.BLLHoatDongThuePhong.Add(hoatDong, 0);
                    } else {
                        BLL.BLLHoatDongThuePhong.Add(hoatDong, 1);
                    }
                LamMoi();
                }
                ArrayList<DTO.PhongTro> list = BLL.BLLPhongTro.GetAll();
                BLL.BLLPhongTro.DoVaoTable(list, tblPhongTro);
            } else {
                ThongBao.ThongBaoDonGian("Thông báo", "Vui lòng chọn người thuê và phòng thuê!");
            }
        }
    }//GEN-LAST:event_btnTaoActionPerformed

    private void btnLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi1ActionPerformed
        txtMaHopDong.setText("");
        LamMoi();
        JOptionPane.showMessageDialog(this, "Làm mới Thành Công !!");
    }//GEN-LAST:event_btnLamMoi1ActionPerformed

    private void cbbMaKhachHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbMaKhachHangItemStateChanged
        // TODO add your handling code here:
        if (cbbMaKhachHang.getItemCount() > 1 && cbbMaKhachHang.getSelectedIndex() > 0) {
            KhachThue khach = BLL.BLLKhachThue.FindByMaNguoiThue(cbbMaKhachHang.getSelectedItem().toString());
            TTkhackThue(khach);
        }
    }//GEN-LAST:event_cbbMaKhachHangItemStateChanged

    public void TTkhackThue(KhachThue khach) {
        txtTenNguoiThueHD.setText(khach.getTenNguoiThue());
        dateNgaySinh.setDate(ChuyenDoi.LayNgayDate(khach.getNgaySinh()));
        txtSDT.setText(khach.getSDT());
        txtCMND.setText(khach.getCMND());
        txtDiaChi.setText(khach.getDiaChi());
    }
    private void cbbPhongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbPhongItemStateChanged
        // TODO add your handling code here:
        System.out.println(cbbPhong.getItemCount());
        if (cbbPhong.getItemCount() > 1 && cbbPhong.getSelectedIndex() > 0) {
            PhongTro phongChon = BLL.BLLPhongTro.FindMaPhong(cbbPhong.getSelectedItem().toString());
            TTphong(phongChon);
            String SoHopDong = BLL.BLLHoatDongThuePhong.SoHoaDon(cbbPhong.getSelectedItem().toString());
            txtMaHopDong.setText(SoHopDong);
        }
    }//GEN-LAST:event_cbbPhongItemStateChanged

    public void TTphong(PhongTro phongChon) {
        txtDienTich.setText(ChuyenDoi.SoString(phongChon.getDienTich()));
        LoaiPhong loaiPhong = BLL.BLLLoaiPhong.FindMaLoaiPhong(phongChon.getMaLoaiPhong().toString());
        txtGiaPhong.setText(ChuyenDoi.SoString(loaiPhong.getGiaPhong()));
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        if (cbbPhong.getSelectedIndex() > 0) {
            String SoHopDong = BLL.BLLHoatDongThuePhong.SoHoaDon(cbbPhong.getSelectedItem().toString());
            txtMaHopDong.setText(SoHopDong);
        } else {
            ThongBao.ThongBaoDonGian("Thông báo", "Vui lòng chọn phòng trước!");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtMaHopDongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaHopDongKeyReleased
        // TODO add your handling code here:
        String maHopDong = txtMaHopDong.getText();
        if (maHopDong != null) {
            if (BLL.BLLHoatDongThuePhong.FindMaHopDong(maHopDong) != null) {
                HoatDongThuePhong hopDongTP = BLL.BLLHoatDongThuePhong.FindMaHopDong(maHopDong);
                BLL.BLLKhachThue.HienThiKhachThueCBB(cbbMaKhachHang, hopDongTP.getMaNguoiThue());
                KhachThue khach = BLL.BLLKhachThue.FindByMaNguoiThue(hopDongTP.getMaNguoiThue());
                TTkhackThue(khach);
                PhongTro phongChon = BLL.BLLPhongTro.FindMaPhong(hopDongTP.getMaPhong());
                TTphong(phongChon);
                dateNgayKy.setDate(hopDongTP.getNgayThue());
                dateNgayKetThuc.setDate(hopDongTP.getNgayTra());
                if (hopDongTP.isTinhTrang()) {
                    rbDangThueAC.setSelected(true);
                } else {
                    rbDangThueAC.setSelected(false);
                }
            } else {
                LamMoi();
            }
        } else {
            LamMoi();
        }
    }//GEN-LAST:event_txtMaHopDongKeyReleased

    public void phong(PhongTro phongChon) {
        txtDienTich.setText(ChuyenDoi.SoString(phongChon.getDienTich()));
        LoaiPhong loaiPhong = BLL.BLLLoaiPhong.FindMaLoaiPhong(phongChon.getMaLoaiPhong().toString());
        System.out.println(loaiPhong.getGiaPhong() + "   " + loaiPhong.getMaLoaiPhong());
        txtGiaPhong.setText(ChuyenDoi.SoString(loaiPhong.getGiaPhong()));
    }

    public void khackThue(KhachThue khach) {
        dateNgaySinh.setDate(ChuyenDoi.LayNgayDate(khach.getNgaySinh()));
        txtSDT.setText(khach.getSDT());
        txtCMND.setText(khach.getCMND());
        txtDiaChi.setText(khach.getDiaChi());
    }

    public void LamMoi() {
        // TODO add your handling code here:
//        txtMaHopDong.setText("");
        cbbMaKhachHang.setSelectedIndex(0);
        txtTenNguoiThueHD.setText("");
        txtSDT.setText("");
        txtCMND.setText("");
        txtDiaChi.setText("");
        cbbPhong.setSelectedIndex(0);
        dateNgaySinh.setDate(null);
        dateNgayKy.setDate(null);
        dateNgayKetThuc.setDate(null);
        txtSDT.setText("");
        txtCMND.setText("");
        txtDienTich.setText("");
        txtGiaPhong.setText("");
        txtGhiChu.setText("");
        txtDiaChi.setText("");
    }

    public HoatDongThuePhong LayDataForm() {
        String MaHD = txtMaHopDong.getText();
        if (MaHD.equals("")) {
            ThongBao.ThongBaoDonGian("Thông báo", "Chưa có mã hợp đồng    ");
            return null;
        }
        if (cbbMaKhachHang.getSelectedIndex() == 0 || dateNgayKy.getDate() == null) {
            ThongBao.ThongBaoDonGian("Thông báo", "Chưa đủ thông tin cần thiết");
            return null;
        }
        String MaPhong = cbbPhong.getSelectedItem().toString();
        String MaNguoiThue = cbbMaKhachHang.getSelectedItem().toString();
//        System.out.println("'"+MaNguoiThue+"'");
        Date NgayThue = dateNgayKy.getDate();
        String GhiChu = txtGhiChu.getText();
        boolean TinhTrang = true;
        Date NgayTra = dateNgayKetThuc.getDate();
        if (dateNgayKetThuc.getDate() != null) {
            rbDangThueAC.setSelected(false);
            TinhTrang = false;
        } else {
            TinhTrang = true;
        }

        HoatDongThuePhong hdtp = new HoatDongThuePhong(MaHD, MaPhong, MaNguoiThue, NgayThue, NgayTra, GhiChu, TinhTrang);
        return hdtp;
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
            java.util.logging.Logger.getLogger(HopDong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HopDong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HopDong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HopDong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HopDong dialog = new HopDong(new javax.swing.JFrame(), true);
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
    public static javax.swing.JLabel JLTenPhong;
    public static javax.swing.JLabel JLmaKhachThue;
    private javax.swing.JButton btResetNgayTraHD;
    private javax.swing.ButtonGroup btnGGioiTinh;
    private javax.swing.ButtonGroup btnGTrangThai;
    private javax.swing.JButton btnLamMoi1;
    private javax.swing.JButton btnTao;
    public static javax.swing.JComboBox<String> cbbMaKhachHang;
    public static javax.swing.JComboBox<String> cbbPhong;
    private com.toedter.calendar.JDateChooser dateNgayKetThuc;
    private com.toedter.calendar.JDateChooser dateNgayKy;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton rbDangThueAC;
    private javax.swing.JLabel txtCMND;
    private javax.swing.JLabel txtDiaChi;
    private javax.swing.JLabel txtDienTich;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JLabel txtGiaPhong;
    private javax.swing.JTextField txtMaHopDong;
    private javax.swing.JLabel txtSDT;
    private javax.swing.JLabel txtTenNguoiThueHD;
    // End of variables declaration//GEN-END:variables
}
