/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.BLLDichVu;
import DTO.HoatDongThuePhong;
import DTO.KhachThue;
import DTO.LoaiPhong;
import DTO.PhongTro;
import helper.ChuyenDoi;
import helper.ThongBao;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.event.ConnectionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author quang nguyen
 */
public class TrangChu extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame1
     */
    ArrayList<DTO.LoaiPhong> listLoai = BLL.BLLLoaiPhong.GetAll();
    ArrayList<HoatDongThuePhong> arrHD = new ArrayList<>();
    ArrayList<KhachThue> arrKT = new ArrayList<>();
    ArrayList<PhongTro> arrPT = new ArrayList<>();

    public TrangChu() {
        initComponents();
        ArrayList<LoaiPhong> listLP = BLL.BLLLoaiPhong.GetAll();
        BLL.BLLLoaiPhong.DoVaoTable(listLP, tblLoaiPhong);
        ImageIcon icon = new ImageIcon("src/images/blue-home-icon.png");
        setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        DongHo();
        ArrayList<DTO.PhongTro> list = BLL.BLLPhongTro.GetAll();
        BLL.BLLPhongTro.DoVaoTable(list, tblPhongTro);
        //lấy dữ liệu ddataabase và setText Thống kê lương người đang thuê phòng
        ArrayList<DTO.KhachThue> listKhachThue = BLL.BLLKhachThue.GetAll();
        txtSoLuongNguoiThue.setText(String.valueOf(listKhachThue.size()));
        //tạo biến để mặc định để thống kê phòng
        int PhongTrong = 0;
        int PhongDaThue = 0;
        //chạy vòng lặp để kiểm tra tình trạng phòng
        for (DTO.PhongTro pt : list) {
            if (pt.isTrangThai() == false) {
                PhongTrong++;
            } else {
                PhongDaThue++;
            }
        }
        FillPhong();
        // set số liệu cho Bảng thống kê
        txtPhongTrong.setText(String.valueOf(PhongDaThue));
        txtPhongDaThue.setText(String.valueOf(PhongTrong));
        ArrayList<DTO.PhongTro> listPhongTro = BLL.BLLPhongTro.GetAll();
        BLL.BLLPhongTro.DoVaoTable(listPhongTro, tblPhongTro);
        panelMain.add(panelTrangChu, "TrangChu");
        panelMain.add(panelLoaiPhong, "LoaiPhong");
        panelMain.add(panelKhachThue2, "KhachThue");
        panelMain.add(panelHongDong, "HopDong");
        panelMain.add(panelFormHoaDon, "HoaDon");
        panelMain.add(panelFormDichVu, "DichVu");
        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, "TrangChu");
        opened();
    }

    public void LamMoiLoaiPhong() {
        // TODO add your handling code here:
        txtMaLoaiPhong.setText("");
        txtTenLoaiPhong.setText("");
        txtGiaTien.setText("");
        txtGiaDien.setText("");
        txtGiaNuoc.setText("");
        JOptionPane.showMessageDialog(this, "Làm mới thành công !!");
        btnSua.setEnabled(false);
    }

    public boolean validateform() {

        if (txtMaLoaiPhong.getText().equals("") || txtTenLoaiPhong.getText().equals("") || txtGiaTien.getText().equals("") || txtGiaDien.getText().equals("") || txtGiaNuoc.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ĐẦY ĐỦ THÔNG TIN!");
            return false;
        }
        return true;

    }

    public void FillPhong() {
        ArrayList<PhongTro> ListPhong = BLL.BLLPhongTro.GetAll();

        if (ListPhong != null) {
            panelDsPhongTro.removeAll();
            JButton[] btn = new JButton[ListPhong.size()];
            for (int i = 0; i < ListPhong.size(); i++) {
                btn[i] = new JButton();
                btn[i].setName(String.valueOf(ListPhong.get(i).getMaPhong()));
                String[] mb = ListPhong.get(i).getTenPhong().split(" ");
                btn[i].setText(mb[0] + " " + mb[1]);
                btn[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/house.png")));
                Border thickBorder = new LineBorder(Color.WHITE, 4);
                btn[i].setBorder(thickBorder);
                btn[i].setBackground(Color.decode("#8080ff"));
                btn[i].setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
                btn[i].setForeground(new java.awt.Color(51, 51, 51));
                System.out.println("Bàn" + i);
//                if(arrBan.get(i).getGhiChu().equals("Trống")){
//                    btn[i].setBackground(Color.decode("#ff6699"));
//                }
//                if(arrBan.get(i).isTrangThai()){
//                    btn[i].setBackground(Color.decode("#ff6699"));
//                } 

                btn[i].setPreferredSize(new Dimension(300, 250));
                btn[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {

                    }
                });

                panelDsPhongTro.add(btn[i]);
                panelDsPhongTro.updateUI();
            }
        }
    }

    public void DongHo() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Date now = new Date();
                    String st = sdf.format(now);
                    LabelDongho.setText(st);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
        thread.start();
    }

    public void opened() {// load thông tin panel hợp đồng
        //set kích thước table
        tbHopDongThue.getColumnModel().getColumn(0).setPreferredWidth(105);
        tbHopDongThue.getColumnModel().getColumn(1).setPreferredWidth(40);
        tbHopDongThue.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbHopDongThue.getColumnModel().getColumn(3).setPreferredWidth(125);
        //dổ dữ liệu vào bảng
        arrHD = BLL.BLLHoatDongThuePhong.GetAll();
        arrKT = BLL.BLLKhachThue.GetAll();
        BLL.BLLKhachThue.doComboBox(arrKT, cbbMaKhachHang);
//        TTkhackThue(khach);
        arrPT = BLL.BLLPhongTro.GetAll();
        BLL.BLLPhongTro.doComboBox(arrPT, cbbPhong);
        BLL.BLLHoatDongThuePhong.DoVaoTable(arrHD, tbHopDongThue);
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        PPMenuChiTietPhong = new javax.swing.JMenuItem();
        MenuSuaPhong = new javax.swing.JMenuItem();
        MenuXoa = new javax.swing.JMenuItem();
        MenuHopDong = new javax.swing.JMenuItem();
        MenuThemKhachThue = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelQLLP = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panelKhachThue = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panelHopDong = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        panelHoaDon = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        panelDichVu = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        LabelDongho = new javax.swing.JLabel();
        panelMain = new javax.swing.JPanel();
        panelTrangChu = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtDoanhThu = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtSoLuongNguoiThue = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtPhongDaThue = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtPhongTrong = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhongTro = new javax.swing.JTable();
        panelDsPhongTro = new javax.swing.JPanel();
        panelLoaiPhong = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLoaiPhong = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtMaLoaiPhong = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTenLoaiPhong = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtGiaTien = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtGiaDien = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtGiaNuoc = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        panelKhachThue2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        txtCMND = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        rbNam = new javax.swing.JRadioButton();
        rbNu = new javax.swing.JRadioButton();
        jLabel84 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        JLHinhAnh = new javax.swing.JLabel();
        btnHInhAnhCD = new javax.swing.JButton();
        btnThemKH = new javax.swing.JButton();
        btnXoaKH = new javax.swing.JButton();
        btnSuaKH = new javax.swing.JButton();
        btnResetKH = new javax.swing.JButton();
        txtTimKiemKH = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        rbDangThue = new javax.swing.JRadioButton();
        rbDaTra = new javax.swing.JRadioButton();
        jLabel88 = new javax.swing.JLabel();
        JDNgaySinh = new com.toedter.calendar.JDateChooser();
        JDNgayVao = new com.toedter.calendar.JDateChooser();
        JLHinhCMNDTrc = new javax.swing.JLabel();
        btnHInhCMNDTrc = new javax.swing.JButton();
        JLHinhCMNDSau = new javax.swing.JLabel();
        btnHInhCMNDSau = new javax.swing.JButton();
        jLabel89 = new javax.swing.JLabel();
        txtSDT2 = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblKhachThue = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        panelHongDong = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        txtMaHopDong = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        dateNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        dateNgayKy = new com.toedter.calendar.JDateChooser();
        dateNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel51 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel52 = new javax.swing.JLabel();
        rbDangThueAC = new javax.swing.JRadioButton();
        btnTao = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();
        btnLamMoi1 = new javax.swing.JButton();
        cbbMaKhachHang = new javax.swing.JComboBox<>();
        txtSDT1 = new javax.swing.JLabel();
        txtCMND1 = new javax.swing.JLabel();
        txtDiaChi1 = new javax.swing.JLabel();
        cbbPhong = new javax.swing.JComboBox<>();
        txtDienTich = new javax.swing.JLabel();
        txtGiaPhong = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel77 = new javax.swing.JLabel();
        txtTenNguoiThueHD = new javax.swing.JLabel();
        btResetNgayTraHD = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbHopDongThue = new javax.swing.JTable();
        jLabel53 = new javax.swing.JLabel();
        txtTimKiemHopDong = new javax.swing.JTextField();
        panelFormHoaDon = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel63 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel17 = new javax.swing.JPanel();
        jTextField12 = new javax.swing.JTextField();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelFormDichVu = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txtMaDichVu = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtTenDichVu = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        cbbDonViTinh = new javax.swing.JComboBox<>();
        jLabel59 = new javax.swing.JLabel();
        JRDangSuDung = new javax.swing.JRadioButton();
        JRKhongSuDung = new javax.swing.JRadioButton();
        btnThem1 = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnXoa1 = new javax.swing.JButton();
        btnLamMoi2 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDIchVu = new javax.swing.JTable();

        PPMenuChiTietPhong.setText("Chi Tiêt Phòng");
        PPMenuChiTietPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PPMenuChiTietPhongActionPerformed(evt);
            }
        });
        jPopupMenu1.add(PPMenuChiTietPhong);

        MenuSuaPhong.setText("Sửa Phòng");
        MenuSuaPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSuaPhongActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MenuSuaPhong);

        MenuXoa.setText("Xóa phòng");
        MenuXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuXoaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MenuXoa);

        MenuHopDong.setText("hợp đồng");
        MenuHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuHopDongActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MenuHopDong);

        MenuThemKhachThue.setText("Thêm Khách Thuê");
        jPopupMenu1.add(MenuThemKhachThue);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý nhà trọ");

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("UTM Times", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ NHÀ TRỌ");

        jLabel2.setFont(new java.awt.Font("UTM Cookies", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LAM HOUSE");

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Xin chào, Admin");

        jPanel5.setBackground(new java.awt.Color(255, 51, 51));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel24.setFont(new java.awt.Font("UTM Charlemagne", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 204, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("ĐĂNG XUẤT");
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel24, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bedroom.png"))); // NOI18N
        jLabel4.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(0, 204, 255));
        jLabel5.setFont(new java.awt.Font("UTM Charlemagne", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("QUẢN LÝ PHÒNG");
        jLabel5.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelQLLP.setBackground(new java.awt.Color(204, 204, 204));
        panelQLLP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelQLLP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelQLLPMouseClicked(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/house.png"))); // NOI18N
        jLabel6.setOpaque(true);

        jLabel7.setBackground(new java.awt.Color(0, 204, 255));
        jLabel7.setFont(new java.awt.Font("UTM Charlemagne", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("QUẢN LÝ LOẠI PHÒNG");
        jLabel7.setOpaque(true);

        javax.swing.GroupLayout panelQLLPLayout = new javax.swing.GroupLayout(panelQLLP);
        panelQLLP.setLayout(panelQLLPLayout);
        panelQLLPLayout.setHorizontalGroup(
            panelQLLPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLLPLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelQLLPLayout.setVerticalGroup(
            panelQLLPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelKhachThue.setBackground(new java.awt.Color(204, 204, 204));
        panelKhachThue.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelKhachThue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelKhachThueMouseClicked(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/youth.png"))); // NOI18N
        jLabel8.setOpaque(true);

        jLabel9.setBackground(new java.awt.Color(0, 204, 255));
        jLabel9.setFont(new java.awt.Font("UTM Charlemagne", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("QUẢN LÝ KHÁCH THUÊ");
        jLabel9.setOpaque(true);

        javax.swing.GroupLayout panelKhachThueLayout = new javax.swing.GroupLayout(panelKhachThue);
        panelKhachThue.setLayout(panelKhachThueLayout);
        panelKhachThueLayout.setHorizontalGroup(
            panelKhachThueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKhachThueLayout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelKhachThueLayout.setVerticalGroup(
            panelKhachThueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelHopDong.setBackground(new java.awt.Color(204, 204, 204));
        panelHopDong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelHopDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelHopDongMouseClicked(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cooperation.png"))); // NOI18N
        jLabel14.setOpaque(true);

        jLabel15.setBackground(new java.awt.Color(0, 204, 255));
        jLabel15.setFont(new java.awt.Font("UTM Charlemagne", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("QUẢN LÝ HỢP ĐỒNG");
        jLabel15.setOpaque(true);

        javax.swing.GroupLayout panelHopDongLayout = new javax.swing.GroupLayout(panelHopDong);
        panelHopDong.setLayout(panelHopDongLayout);
        panelHopDongLayout.setHorizontalGroup(
            panelHopDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHopDongLayout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelHopDongLayout.setVerticalGroup(
            panelHopDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelHoaDon.setBackground(new java.awt.Color(204, 204, 204));
        panelHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelHoaDonMouseClicked(evt);
            }
        });

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/invoice.png"))); // NOI18N
        jLabel21.setOpaque(true);

        jLabel22.setBackground(new java.awt.Color(0, 204, 255));
        jLabel22.setFont(new java.awt.Font("UTM Charlemagne", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("QUẢN LÝ HÓA ĐƠN");
        jLabel22.setOpaque(true);

        javax.swing.GroupLayout panelHoaDonLayout = new javax.swing.GroupLayout(panelHoaDon);
        panelHoaDon.setLayout(panelHoaDonLayout);
        panelHoaDonLayout.setHorizontalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDonLayout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelHoaDonLayout.setVerticalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelDichVu.setBackground(new java.awt.Color(204, 204, 204));
        panelDichVu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelDichVuMouseClicked(evt);
            }
        });

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customer-service-32px.png"))); // NOI18N
        jLabel23.setOpaque(true);

        jLabel25.setBackground(new java.awt.Color(0, 204, 255));
        jLabel25.setFont(new java.awt.Font("UTM Charlemagne", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("QUẢN LÝ DỊCH VỤ");
        jLabel25.setOpaque(true);

        javax.swing.GroupLayout panelDichVuLayout = new javax.swing.GroupLayout(panelDichVu);
        panelDichVu.setLayout(panelDichVuLayout);
        panelDichVuLayout.setHorizontalGroup(
            panelDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDichVuLayout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDichVuLayout.setVerticalGroup(
            panelDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        LabelDongho.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        LabelDongho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelDongho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alarm-64px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelQLLP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelKhachThue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelHopDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LabelDongho, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelQLLP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelKhachThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LabelDongho))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelMain.setLayout(new java.awt.CardLayout());

        panelTrangChu.setBackground(new java.awt.Color(255, 255, 255));

        jPanel20.setLayout(new javax.swing.BoxLayout(jPanel20, javax.swing.BoxLayout.LINE_AXIS));

        jPanel21.setBackground(new java.awt.Color(255, 51, 51));

        jLabel10.setFont(new java.awt.Font("UTM Times", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("DOANH THU");

        jLabel11.setFont(new java.awt.Font("UTM Times", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("VNĐ");

        txtDoanhThu.setFont(new java.awt.Font("UTM Times", 1, 18)); // NOI18N
        txtDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDoanhThu.setText("0");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(26, 26, 26))
        );

        jPanel20.add(jPanel21);

        jPanel22.setBackground(new java.awt.Color(51, 204, 0));

        jLabel12.setFont(new java.awt.Font("UTM Times", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("NGƯỜI THUÊ");

        txtSoLuongNguoiThue.setFont(new java.awt.Font("UTM Times", 1, 18)); // NOI18N
        txtSoLuongNguoiThue.setForeground(new java.awt.Color(255, 255, 255));
        txtSoLuongNguoiThue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSoLuongNguoiThue.setText("0");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoLuongNguoiThue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(txtSoLuongNguoiThue, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jPanel20.add(jPanel22);

        jPanel23.setBackground(new java.awt.Color(102, 0, 255));

        jLabel13.setFont(new java.awt.Font("UTM Times", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("PHÒNG");

        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.LINE_AXIS));

        jPanel12.setBackground(new java.awt.Color(51, 51, 255));

        jLabel20.setFont(new java.awt.Font("UTM Times", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Đã Thuê");

        txtPhongDaThue.setFont(new java.awt.Font("UTM Times", 1, 18)); // NOI18N
        txtPhongDaThue.setForeground(new java.awt.Color(255, 255, 255));
        txtPhongDaThue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPhongDaThue.setText("0");

        jPanel11.setBackground(new java.awt.Color(51, 51, 255));

        jLabel17.setFont(new java.awt.Font("UTM Times", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Trống");

        txtPhongTrong.setFont(new java.awt.Font("UTM Times", 1, 18)); // NOI18N
        txtPhongTrong.setForeground(new java.awt.Color(255, 255, 255));
        txtPhongTrong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPhongTrong.setText("0");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPhongTrong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(txtPhongTrong)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(txtPhongDaThue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(txtPhongDaThue)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.add(jPanel12);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel20.add(jPanel23);

        tblPhongTro.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblPhongTro);

        panelDsPhongTro.setBackground(new java.awt.Color(255, 255, 255));
        panelDsPhongTro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Danh Sách Phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("UTM Charlemagne", 1, 12), new java.awt.Color(255, 0, 51))); // NOI18N
        panelDsPhongTro.setAutoscrolls(true);
        panelDsPhongTro.setComponentPopupMenu(jPopupMenu1);
        panelDsPhongTro.setInheritsPopupMenu(true);

        javax.swing.GroupLayout panelTrangChuLayout = new javax.swing.GroupLayout(panelTrangChu);
        panelTrangChu.setLayout(panelTrangChuLayout);
        panelTrangChuLayout.setHorizontalGroup(
            panelTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 1234, Short.MAX_VALUE)
            .addComponent(panelDsPhongTro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTrangChuLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTrangChuLayout.setVerticalGroup(
            panelTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrangChuLayout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDsPhongTro, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE))
        );

        panelMain.add(panelTrangChu, "card3");

        jPanel8.setBackground(new java.awt.Color(0, 204, 255));

        jLabel16.setBackground(new java.awt.Color(0, 204, 255));
        jLabel16.setFont(new java.awt.Font("UTM Times", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 0, 51));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("QUẢN LÝ LOẠI PHÒNG");
        jLabel16.setToolTipText("");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel16.setOpaque(true);

        tblLoaiPhong.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        tblLoaiPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã loại phòng", "Tên loại phòng", "Giá tiền", "Giá điện", "Giá nước"
            }
        ));
        tblLoaiPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiPhongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLoaiPhong);

        jPanel9.setBackground(new java.awt.Color(0, 204, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel18.setText("Mã loại phòng");
        jPanel9.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 30));
        jPanel9.add(txtMaLoaiPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 30, 200, 30));

        jLabel19.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel19.setText("Tên loại phòng");
        jPanel9.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 70, 100, 30));
        jPanel9.add(txtTenLoaiPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 70, 200, 30));

        jLabel26.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel26.setText("Giá tiền");
        jPanel9.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 60, 30));
        jPanel9.add(txtGiaTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 110, 200, 30));

        jLabel27.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel27.setText("Giá điện");
        jPanel9.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 60, 30));
        jPanel9.add(txtGiaDien, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 150, 200, 30));

        jLabel28.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel28.setText("Giá nước");
        jPanel9.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 60, 30));
        jPanel9.add(txtGiaNuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 190, 200, 30));

        btnThem.setBackground(new java.awt.Color(255, 255, 255));
        btnThem.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel9.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 110, 30));

        btnXoa.setBackground(new java.awt.Color(255, 255, 255));
        btnXoa.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnXoa.setText("  Xóa");
        btnXoa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel9.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 110, 30));

        btnSua.setBackground(new java.awt.Color(255, 255, 255));
        btnSua.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/updated.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setEnabled(false);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel9.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 110, 30));

        btnLamMoi.setBackground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh-16px.png"))); // NOI18N
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel9.add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, -1, 30));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 1234, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout panelLoaiPhongLayout = new javax.swing.GroupLayout(panelLoaiPhong);
        panelLoaiPhong.setLayout(panelLoaiPhongLayout);
        panelLoaiPhongLayout.setHorizontalGroup(
            panelLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelLoaiPhongLayout.setVerticalGroup(
            panelLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelMain.add(panelLoaiPhong, "card3");

        panelKhachThue2.setBackground(new java.awt.Color(0, 204, 255));

        jPanel6.setBackground(new java.awt.Color(0, 204, 255));

        jPanel18.setBackground(new java.awt.Color(0, 204, 255));

        jLabel79.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel79.setText("Mã KH");

        jLabel80.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel80.setText("Tên KH");

        jLabel81.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel81.setText("Ngày sinh");

        jLabel82.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel82.setText("CCCD/CMND");

        jLabel83.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel83.setText("Giới tính");

        rbNam.setBackground(new java.awt.Color(0, 204, 255));
        rbNam.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        rbNam.setText("Nam");
        rbNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNamActionPerformed(evt);
            }
        });

        rbNu.setBackground(new java.awt.Color(0, 204, 255));
        rbNu.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        rbNu.setText("Nữ");

        jLabel84.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel84.setText("SDT");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
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

        btnXoaKH.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaKH.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnXoaKH.setText("Xóa");
        btnXoaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKHActionPerformed(evt);
            }
        });

        btnSuaKH.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaKH.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnSuaKH.setText("Sửa");
        btnSuaKH.setEnabled(false);
        btnSuaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKHActionPerformed(evt);
            }
        });

        btnResetKH.setBackground(new java.awt.Color(255, 255, 255));
        btnResetKH.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnResetKH.setText("Làm Mới");

        txtTimKiemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemKHActionPerformed(evt);
            }
        });
        txtTimKiemKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKHKeyReleased(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel86.setText("Ngày vào:");

        jLabel87.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel87.setText("Tình trạng");

        rbDangThue.setBackground(new java.awt.Color(0, 204, 255));
        rbDangThue.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        rbDangThue.setText("Đang thuê");

        rbDaTra.setBackground(new java.awt.Color(0, 204, 255));
        rbDaTra.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        rbDaTra.setText("Đã trả");
        rbDaTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDaTraActionPerformed(evt);
            }
        });

        jLabel88.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel88.setText("Tìm kiếm");

        JDNgaySinh.setDateFormatString("dd/MM/yyyy");

        JDNgayVao.setDateFormatString("dd/MM/yyyy");

        JLHinhCMNDTrc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLHinhCMNDTrc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/man-128px.png"))); // NOI18N

        btnHInhCMNDTrc.setBackground(new java.awt.Color(255, 255, 255));
        btnHInhCMNDTrc.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnHInhCMNDTrc.setText("CMND trước");
        btnHInhCMNDTrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHInhCMNDTrcActionPerformed(evt);
            }
        });

        JLHinhCMNDSau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLHinhCMNDSau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/man-128px.png"))); // NOI18N

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

        txtSDT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDT2ActionPerformed(evt);
            }
        });

        tblKhachThue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên KH", "CCCD/CMND", "SDT", "Email", "Địa Chỉ", "Ngày Sinh", "Ngày Vào", "GTinh", "Tình trạng", "Ảnh CD", "Mặt trước", "Mặt sau"
            }
        ));
        tblKhachThue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachThueMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblKhachThue);
        if (tblKhachThue.getColumnModel().getColumnCount() > 0) {
            tblKhachThue.getColumnModel().getColumn(8).setResizable(false);
            tblKhachThue.getColumnModel().getColumn(10).setResizable(false);
        }

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(btnThemKH)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetKH))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnHInhAnhCD, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHInhCMNDTrc)
                            .addComponent(JLHinhCMNDTrc, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLHinhCMNDSau, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHInhCMNDSau, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel80)
                                .addComponent(jLabel82))
                            .addComponent(jLabel79, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel84, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel89, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel85, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel86, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel83, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JDNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JDNgayVao, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtMaKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                        .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCMND, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtSDT2, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(rbDangThue)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbDaTra)
                                    .addComponent(rbNu)))))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rbNam)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel87)
                                .addGap(89, 89, 89)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(JDNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel81)))
                .addGap(12, 12, 12)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel86)
                    .addComponent(JDNgayVao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbNam)
                    .addComponent(rbNu)
                    .addComponent(jLabel83))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(rbDangThue)
                    .addComponent(rbDaTra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLHinhCMNDSau)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(JLHinhAnh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHInhAnhCD))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(JLHinhCMNDTrc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHInhCMNDTrc)
                            .addComponent(btnHInhCMNDSau))))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel88))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemKH)
                    .addComponent(btnXoaKH)
                    .addComponent(btnSuaKH)
                    .addComponent(btnResetKH))
                .addContainerGap(163, Short.MAX_VALUE))
            .addComponent(jScrollPane8)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel29.setBackground(new java.awt.Color(0, 204, 255));
        jLabel29.setFont(new java.awt.Font("UTM Times", 1, 36)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 0, 51));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("QUẢN LÝ KHÁCH THUÊ");
        jLabel29.setOpaque(true);

        javax.swing.GroupLayout panelKhachThue2Layout = new javax.swing.GroupLayout(panelKhachThue2);
        panelKhachThue2.setLayout(panelKhachThue2Layout);
        panelKhachThue2Layout.setHorizontalGroup(
            panelKhachThue2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhachThue2Layout.createSequentialGroup()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 1228, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelKhachThue2Layout.setVerticalGroup(
            panelKhachThue2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhachThue2Layout.createSequentialGroup()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelMain.add(panelKhachThue2, "card7");

        jPanel13.setBackground(new java.awt.Color(0, 204, 255));

        jPanel14.setBackground(new java.awt.Color(0, 204, 255));

        jLabel40.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel40.setText("Mã HĐ");

        jLabel41.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel41.setText("Tên KH");

        jLabel42.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel42.setText("Ngày Sinh");

        dateNgaySinh.setDateFormatString("dd/MM/yyyy");

        jLabel43.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel43.setText("SĐT");

        jLabel44.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel44.setText("Địa chỉ");

        jLabel45.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel45.setText("Phòng");

        jLabel46.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel46.setText("Giá phòng");

        jLabel47.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel47.setText("CCCD");

        jLabel48.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel48.setText("Diện tích");

        jLabel49.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel49.setText("Ngày Kí");

        jLabel50.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel50.setText("Ngày Hết Hạn");

        dateNgayKy.setDateFormatString("dd/MM/YYYY");

        dateNgayKetThuc.setDateFormatString("dd/MM/YYYY");

        jLabel51.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel51.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane4.setViewportView(txtGhiChu);

        jLabel52.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel52.setText("Trạng Thái");

        rbDangThueAC.setBackground(new java.awt.Color(0, 204, 255));
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

        btnSua1.setBackground(new java.awt.Color(255, 255, 255));
        btnSua1.setFont(new java.awt.Font("UTM Times", 1, 12)); // NOI18N
        btnSua1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/updated.png"))); // NOI18N
        btnSua1.setText("Cập Nhật");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        btnLamMoi1.setBackground(new java.awt.Color(255, 255, 255));
        btnLamMoi1.setFont(new java.awt.Font("UTM Times", 1, 12)); // NOI18N
        btnLamMoi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        btnLamMoi1.setText("Làm mới");
        btnLamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi1ActionPerformed(evt);
            }
        });

        cbbMaKhachHang.setFont(new java.awt.Font("UTM Times", 0, 12)); // NOI18N
        cbbMaKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMaKhachHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbMaKhachHangItemStateChanged(evt);
            }
        });

        txtSDT1.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtSDT1.setText("   ");

        txtCMND1.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtCMND1.setText("   ");

        txtDiaChi1.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtDiaChi1.setText("   ");

        cbbPhong.setFont(new java.awt.Font("UTM Times", 0, 12)); // NOI18N
        cbbPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbPhong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbPhongItemStateChanged(evt);
            }
        });

        txtDienTich.setFont(new java.awt.Font("UTM Times", 1, 12)); // NOI18N
        txtDienTich.setText("  ");

        txtGiaPhong.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtGiaPhong.setText("  ");

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh-16px.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel77.setText("Mã KH");

        txtTenNguoiThueHD.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtTenNguoiThueHD.setText("  ");

        btResetNgayTraHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh-16px.png"))); // NOI18N
        btResetNgayTraHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetNgayTraHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDiaChi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(cbbPhong, 0, 169, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel48)
                                .addGap(5, 5, 5)
                                .addComponent(txtDienTich, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
                            .addComponent(rbDangThueAC)
                            .addComponent(txtGiaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(txtSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel47)
                                .addGap(24, 24, 24)
                                .addComponent(txtCMND1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTenNguoiThueHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbMaKhachHang, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMaHopDong))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5)
                                .addGap(39, 39, 39))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                    .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dateNgayKy, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btResetNgayTraHD))))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnTao, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnSua1)
                        .addGap(40, 40, 40)
                        .addComponent(btnLamMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel40)
                        .addComponent(txtMaHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel77)
                    .addComponent(cbbMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel41)
                    .addComponent(txtTenNguoiThueHD))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel47)
                    .addComponent(txtSDT1)
                    .addComponent(txtCMND1))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtDiaChi1))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel48)
                    .addComponent(cbbPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDienTich))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel46)
                    .addComponent(txtGiaPhong))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel49)
                    .addComponent(dateNgayKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel50)
                        .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btResetNgayTraHD))
                .addGap(23, 23, 23)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel51)))
                .addGap(26, 26, 26)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(rbDangThueAC))
                .addGap(33, 33, 33)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(btnLamMoi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTao))
                .addContainerGap(181, Short.MAX_VALUE))
        );

        tbHopDongThue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã HĐ", "Phòng", "Mã người thuê", "Tên KH", "Ngày ký", "Ngày kết thúc", "Ghi chú", "Trạng thái"
            }
        ));
        tbHopDongThue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHopDongThueMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbHopDongThue);

        jLabel53.setBackground(new java.awt.Color(0, 204, 255));
        jLabel53.setFont(new java.awt.Font("UTM Times", 1, 36)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 0, 51));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("QUẢN LÝ HỢP ĐỒNG THUÊ");
        jLabel53.setOpaque(true);

        txtTimKiemHopDong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemHopDongKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(txtTimKiemHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(txtTimKiemHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5))))
        );

        javax.swing.GroupLayout panelHongDongLayout = new javax.swing.GroupLayout(panelHongDong);
        panelHongDong.setLayout(panelHongDongLayout);
        panelHongDongLayout.setHorizontalGroup(
            panelHongDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelHongDongLayout.setVerticalGroup(
            panelHongDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelMain.add(panelHongDong, "card6");

        jPanel16.setBackground(new java.awt.Color(0, 204, 255));

        jLabel60.setBackground(new java.awt.Color(0, 204, 255));
        jLabel60.setFont(new java.awt.Font("UTM Times", 1, 24)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 0, 51));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("HÓA ĐƠN");
        jLabel60.setOpaque(true);

        jLabel61.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel61.setText("Mã HĐ");

        jLabel62.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel62.setText("Phòng");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel63.setText("Tên KH");

        jLabel64.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel64.setText("Ngày tạo");

        jLabel65.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel65.setText("Điện TT");

        jLabel66.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel66.setText("Nước TT");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel67.setText("Giá");

        jLabel68.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel68.setText("Giá");

        jLabel69.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel69.setText("Tiền Phòng");

        jLabel70.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel70.setText("Tiền ĐIện");

        jLabel71.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel71.setText("Tiền Nước");

        jLabel72.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel72.setText("Tiền DV");

        jLabel73.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel73.setText("Tổng tiền");

        jLabel74.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel74.setText("Khách đưa");

        jLabel75.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel75.setText("Tình trạng");

        jRadioButton1.setBackground(new java.awt.Color(0, 204, 255));
        jRadioButton1.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jRadioButton1.setText("Chưa thanh toán");

        jRadioButton2.setBackground(new java.awt.Color(0, 204, 255));
        jRadioButton2.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jRadioButton2.setText("Thiếu");

        jRadioButton3.setBackground(new java.awt.Color(0, 204, 255));
        jRadioButton3.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jRadioButton3.setText("Đã thanh toán");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/notebook_1.png"))); // NOI18N
        jButton1.setText("Tạo Hóa Đơn");

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/updated.png"))); // NOI18N
        jButton2.setText("Cập nhật");
        jButton2.setEnabled(false);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        jButton3.setText("Làm Mới");

        jLabel76.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel76.setText("Thiêu");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(jRadioButton2))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65)
                    .addComponent(jLabel75)
                    .addComponent(jLabel74)
                    .addComponent(jLabel73)
                    .addComponent(jLabel72)
                    .addComponent(jLabel70)
                    .addComponent(jLabel69)
                    .addComponent(jLabel66)
                    .addComponent(jLabel64)
                    .addComponent(jLabel63)
                    .addComponent(jLabel61)
                    .addComponent(jLabel76))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(63, 63, 63)
                        .addComponent(jRadioButton3))
                    .addComponent(jTextField11)
                    .addComponent(jTextField10)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addComponent(jLabel68)
                            .addGap(23, 23, 23)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel62)
                                .addGap(33, 33, 33)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                    .addComponent(jTextField4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel67)
                                .addGap(23, 23, 23)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jTextField7)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel71)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField9))
                    .addComponent(jTextField13)
                    .addComponent(jTextField14)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel64)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addGap(21, 21, 21)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(252, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(0, 204, 255));

        jRadioButton4.setBackground(new java.awt.Color(0, 204, 255));
        jRadioButton4.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jRadioButton4.setText("Tất cả");

        jRadioButton5.setBackground(new java.awt.Color(0, 204, 255));
        jRadioButton5.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jRadioButton5.setText("Đã thanh toán");

        jRadioButton6.setBackground(new java.awt.Color(0, 204, 255));
        jRadioButton6.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jRadioButton6.setText("Thiếu tiền");

        jRadioButton7.setBackground(new java.awt.Color(0, 204, 255));
        jRadioButton7.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jRadioButton7.setText("Chưa thanh toán");

        jTable1.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã HĐ", "Phòng", "Tên KH", "Ngày tạo", "Tổng tiền", "Trạng thái"
            }
        ));
        jScrollPane7.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jRadioButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton5)
                        .addGap(39, 39, 39)
                        .addComponent(jRadioButton6)
                        .addGap(46, 46, 46)
                        .addComponent(jRadioButton7)))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton6)
                    .addComponent(jRadioButton7))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7))
        );

        javax.swing.GroupLayout panelFormHoaDonLayout = new javax.swing.GroupLayout(panelFormHoaDon);
        panelFormHoaDon.setLayout(panelFormHoaDonLayout);
        panelFormHoaDonLayout.setHorizontalGroup(
            panelFormHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFormHoaDonLayout.setVerticalGroup(
            panelFormHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelMain.add(panelFormHoaDon, "card5");

        jPanel15.setBackground(new java.awt.Color(0, 204, 255));

        jLabel54.setBackground(new java.awt.Color(0, 204, 255));
        jLabel54.setFont(new java.awt.Font("UTM Times", 1, 36)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 0, 51));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("QUẢN LÝ DỊCH VỤ");

        jLabel55.setFont(new java.awt.Font("UTM Times", 1, 18)); // NOI18N
        jLabel55.setText("Mã dịch vụ");

        jLabel56.setFont(new java.awt.Font("UTM Times", 1, 18)); // NOI18N
        jLabel56.setText("Tên dịch vụ");

        jLabel57.setFont(new java.awt.Font("UTM Times", 1, 18)); // NOI18N
        jLabel57.setText("Giá");

        jLabel58.setFont(new java.awt.Font("UTM Times", 1, 18)); // NOI18N
        jLabel58.setText("Đơn vị tính");

        cbbDonViTinh.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        cbbDonViTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Số", "Khối", "Phòng", "Kg", "Người" }));

        jLabel59.setFont(new java.awt.Font("UTM Times", 1, 18)); // NOI18N
        jLabel59.setText("Trạng thái");

        JRDangSuDung.setBackground(new java.awt.Color(0, 204, 255));
        JRDangSuDung.setFont(new java.awt.Font("UTM Times", 1, 18)); // NOI18N
        JRDangSuDung.setText("Đang sử dụng");

        JRKhongSuDung.setBackground(new java.awt.Color(0, 204, 255));
        JRKhongSuDung.setFont(new java.awt.Font("UTM Times", 1, 18)); // NOI18N
        JRKhongSuDung.setText("Không sử dụng");

        btnThem1.setBackground(new java.awt.Color(255, 255, 255));
        btnThem1.setFont(new java.awt.Font("UTM Times", 1, 16)); // NOI18N
        btnThem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btnThem1.setText("Thêm");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdate.setFont(new java.awt.Font("UTM Times", 1, 16)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/updated2-24px.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setEnabled(false);
        btnUpdate.setPreferredSize(new java.awt.Dimension(96, 29));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnXoa1.setBackground(new java.awt.Color(255, 255, 255));
        btnXoa1.setFont(new java.awt.Font("UTM Times", 1, 16)); // NOI18N
        btnXoa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnXoa1.setText("Xóa");
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        btnLamMoi2.setBackground(new java.awt.Color(255, 255, 255));
        btnLamMoi2.setFont(new java.awt.Font("UTM Times", 1, 16)); // NOI18N
        btnLamMoi2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh-16px.png"))); // NOI18N
        btnLamMoi2.setText("Làm mới");
        btnLamMoi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi2ActionPerformed(evt);
            }
        });

        tblDIchVu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblDIchVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã dịch vụ", "Tên dịch vụ", "Đơn vị tính", "Giá", "Trạng thái"
            }
        ));
        tblDIchVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDIchVuMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblDIchVu);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btnLamMoi2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel55)
                            .addComponent(jLabel56)
                            .addComponent(jLabel57)
                            .addComponent(jLabel58)
                            .addComponent(jLabel59))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JRDangSuDung)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtGia)
                                .addComponent(txtTenDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                                .addComponent(cbbDonViTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMaDichVu))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(189, 189, 189)
                                .addComponent(JRKhongSuDung)))))
                .addContainerGap(334, Short.MAX_VALUE))
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel55)
                    .addComponent(txtMaDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbDonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58))
                .addGap(25, 25, 25)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JRDangSuDung)
                    .addComponent(JRKhongSuDung)
                    .addComponent(jLabel59))
                .addGap(27, 27, 27)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelFormDichVuLayout = new javax.swing.GroupLayout(panelFormDichVu);
        panelFormDichVu.setLayout(panelFormDichVuLayout);
        panelFormDichVuLayout.setHorizontalGroup(
            panelFormDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelFormDichVuLayout.setVerticalGroup(
            panelFormDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelMain.add(panelFormDichVu, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panelQLLPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelQLLPMouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, "LoaiPhong");
    }//GEN-LAST:event_panelQLLPMouseClicked

    private void panelKhachThueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelKhachThueMouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, "KhachThue");
    }//GEN-LAST:event_panelKhachThueMouseClicked

    private void panelHopDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHopDongMouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, "HopDong");
    }//GEN-LAST:event_panelHopDongMouseClicked

    private void panelHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHoaDonMouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, "HoaDon");
    }//GEN-LAST:event_panelHoaDonMouseClicked

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked

        int dem = JOptionPane.YES_OPTION;
        if (JOptionPane.showConfirmDialog(this, "Bạn muốn đăng xuất khỏi tài khoản ??", "Đăng xuất", JOptionPane.YES_NO_OPTION) == dem) {
            setVisible(false);
            new DangNhap().setVisible(true);
        }
    }//GEN-LAST:event_jLabel24MouseClicked

    private void panelDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDichVuMouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, "DichVu");
    }//GEN-LAST:event_panelDichVuMouseClicked

    private void MenuHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuHopDongActionPerformed
        // TODO add your handling code here:
        new GUI.jdlKhachThue(this, true).setVisible(true);
    }//GEN-LAST:event_MenuHopDongActionPerformed
    public static String text = "";
    private void PPMenuChiTietPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PPMenuChiTietPhongActionPerformed
        // TODO add your handling code here:
        int count = tblPhongTro.getSelectedRow();
        text = (String) tblPhongTro.getValueAt(count, 0);
        System.out.println(text);
        new ChiTietPhong(this, true).setVisible(true);

    }//GEN-LAST:event_PPMenuChiTietPhongActionPerformed

    private void MenuSuaPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSuaPhongActionPerformed
        // TODO add your handling code here:
        ArrayList<DTO.PhongTro> listPhong = BLL.BLLPhongTro.GetAll();
        int count = tblPhongTro.getSelectedRow();
        text = (String) tblPhongTro.getValueAt(count, 0);
        System.out.println(text);
        new themPhong().setVisible(true);
        String LoaiPhong = "";
        for (LoaiPhong lp : listLoai) {
            if (tblPhongTro.getValueAt(count, 2).equals(lp.getMaLoaiPhong())) {
                themPhong.cbbLoaiPhong.setSelectedItem(lp.getTenLoaiPhong());
            }

        }
        for (PhongTro pt : listPhong) {
            if (pt.getMaPhong().equals(tblPhongTro.getValueAt(count, 0))) {
                themPhong.txtViTri.setText(pt.getViTri());
                themPhong.txtDienTich.setText(String.valueOf(pt.getDienTich()));
                themPhong.txtGiuong.setText(String.valueOf(pt.getGiuong()));
                themPhong.txtBan.setText(String.valueOf(pt.getBan()));
                themPhong.txtBongDen.setText(String.valueOf(pt.getBongDen()));
                themPhong.txtCuaKinh.setText(String.valueOf(pt.getKinhCua()));
                if (pt.isTrangThai()) {
                    themPhong.JRSuDung.setSelected(true);
                } else {
                    themPhong.JRKhongSuDung.setSelected(true);
                }
            }
        }
        themPhong.btnSua.setEnabled(true);
        themPhong.txtMaPhong.setEnabled(false);
        themPhong.txtTenPhong.setText((String) tblPhongTro.getValueAt(count, 1));
        themPhong.txtMaPhong.setText((String) tblPhongTro.getValueAt(count, 0));
    }//GEN-LAST:event_MenuSuaPhongActionPerformed

    private void MenuXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuXoaActionPerformed
        // TODO add your handling code here:
//        ThongBao.ThongBaoDonGian("Thông báo", "Hiện không thế xóa!");
        int count = tblPhongTro.getSelectedRow();
        String MaPhong = (String) tblPhongTro.getValueAt(count, 0);
        DTO.PhongTro pt = new PhongTro(MaPhong);
        int check = JOptionPane.YES_OPTION;
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa !!", "Xóa Phòng", JOptionPane.YES_NO_OPTION) == check) {
            DAL.DALPhongTro.Delete(pt);
            JOptionPane.showMessageDialog(this, "Xóa thành công !!");
        }
        ArrayList<DTO.PhongTro> list = BLL.BLLPhongTro.GetAll();
        BLL.BLLPhongTro.DoVaoTable(list, tblPhongTro);
        ArrayList<DTO.KhachThue> listKhachThue = BLL.BLLKhachThue.GetAll();
        TrangChu.txtSoLuongNguoiThue.setText(String.valueOf(listKhachThue.size()));
        //tạo biến để mặc định để thống kê phòng
        int PhongTrong = 0;
        int PhongDaThue = 0;
        //chạy vòng lặp để kiểm tra tình trạng phòng
        for (DTO.PhongTro ptro : list) {
            if (ptro.isTrangThai() == false) {
                PhongTrong++;
            } else {
                PhongDaThue++;
            }
        }
        // set số liệu cho Bảng thống kê
        TrangChu.txtPhongTrong.setText(String.valueOf(PhongDaThue));
        TrangChu.txtPhongDaThue.setText(String.valueOf(PhongTrong));

        BLL.BLLPhongTro.DoVaoTable(list, tblPhongTro);
    }//GEN-LAST:event_MenuXoaActionPerformed

    private void tblLoaiPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiPhongMouseClicked
        // TODO add your handling code here:
        txtMaLoaiPhong.setEnabled(true);
        int count = tblLoaiPhong.getSelectedRow();
        txtMaLoaiPhong.setText((String) tblLoaiPhong.getValueAt(count, 0));
        txtTenLoaiPhong.setText((String) tblLoaiPhong.getValueAt(count, 1));
        txtGiaTien.setText(String.valueOf(tblLoaiPhong.getValueAt(count, 2)));
        txtGiaDien.setText(String.valueOf(tblLoaiPhong.getValueAt(count, 3)));
        txtGiaNuoc.setText((String.valueOf(tblLoaiPhong.getValueAt(count, 4))));
        btnSua.setEnabled(true);
    }//GEN-LAST:event_tblLoaiPhongMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        String MaLoaiPhong, TenLoaiPhong;
        double GiaPhong;
        double GiaDien;
        double GiaNuoc;
        if (validateform()) {
            MaLoaiPhong = txtMaLoaiPhong.getText();
            TenLoaiPhong = txtTenLoaiPhong.getText();
            GiaPhong = ChuyenDoi.SoDouble(txtGiaTien.getText());
            GiaDien = ChuyenDoi.SoDouble(txtGiaDien.getText());
            GiaNuoc = ChuyenDoi.SoDouble(txtGiaNuoc.getText());
            LoaiPhong lp = new LoaiPhong(MaLoaiPhong, TenLoaiPhong, GiaPhong, GiaDien, GiaNuoc);
            BLL.BLLLoaiPhong.Add(lp);
            ArrayList<LoaiPhong> arr = BLL.BLLLoaiPhong.GetAll();
            BLL.BLLLoaiPhong.DoVaoTable(arr, tblLoaiPhong);
        }
        LamMoiLoaiPhong();

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int dongDangChon = tblLoaiPhong.getSelectedRow();
        if (dongDangChon < 0) {
            ThongBao.ThongBaoDonGian("Thông Báo", "Bạn chưa chọn loại phòng cần xóa");
        } else {
            int XacNhan = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa không?", "Thông báo xác nhận", JOptionPane.OK_CANCEL_OPTION);
            if (XacNhan == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        //Lấy danh sách các sp  cần xóa
        int dongCanXoa[] = tblLoaiPhong.getSelectedRows();
        for (int i = 0; i < dongCanXoa.length; i++) {
            String MaLoaiPhong = tblLoaiPhong.getValueAt(dongCanXoa[i], 0).toString();
            BLL.BLLLoaiPhong.Delete(MaLoaiPhong);
        }
        ArrayList<LoaiPhong> arr = BLL.BLLLoaiPhong.GetAll();
        BLL.BLLLoaiPhong.DoVaoTable(arr, tblLoaiPhong);
        LamMoiLoaiPhong();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        String TenLoaiPhong;
        double GiaPhong;
        double GiaDien;
        double GiaNuoc;
        if (validateform()) {
            TenLoaiPhong = txtTenLoaiPhong.getText();
            GiaPhong = ChuyenDoi.SoDouble(txtGiaTien.getText());
            GiaDien = ChuyenDoi.SoDouble(txtGiaDien.getText());
            GiaNuoc = ChuyenDoi.SoDouble(txtGiaNuoc.getText());
            int dongDangChon = tblLoaiPhong.getSelectedRow();
            String MaLoaiPhong = tblLoaiPhong.getValueAt(dongDangChon, 0).toString();
            LoaiPhong lp = new LoaiPhong(MaLoaiPhong, TenLoaiPhong, GiaPhong, GiaDien, GiaNuoc);
            BLL.BLLLoaiPhong.Update(lp);
            ArrayList<LoaiPhong> arr = BLL.BLLLoaiPhong.GetAll();
            BLL.BLLLoaiPhong.DoVaoTable(arr, tblLoaiPhong);
        }
        LamMoiLoaiPhong();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtMaLoaiPhong.setText("");
        txtTenLoaiPhong.setText("");
        txtGiaTien.setText("");
        txtGiaDien.setText("");
        txtGiaNuoc.setText("");
        JOptionPane.showMessageDialog(this, "Làm mới thành công !!");
        btnSua.setEnabled(false);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, "TrangChu");
    }//GEN-LAST:event_jPanel4MouseClicked
    public HoatDongThuePhong LayDataForm() {
        String MaHD = txtMaHopDong.getText();
        if (MaHD.equals("")) {
            ThongBao.ThongBaoDonGian("Thông báo", "Chưa có mã hợp đồng    ");
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
//            rbDaKetHDAC.setSelected(true);
            rbDangThueAC.setSelected(false);
        }else{
            TinhTrang = false;
        }

//        if (rbDaKetHDAC.isSelected()) {
////        ThongBao.ThongBaoDonGian("  ", NgayTra.toString());
//            TinhTrang = false;
//        }
        HoatDongThuePhong hdtp = new HoatDongThuePhong(MaHD, MaPhong, MaNguoiThue, NgayThue, NgayTra, GhiChu, TinhTrang);
        return hdtp;
    }

    public void LamMoi() {
        // TODO add your handling code here:
        txtMaHopDong.setText("");
        cbbMaKhachHang.setSelectedIndex(0);
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

    public void khackThue(KhachThue khach) {
        dateNgaySinh.setDate(ChuyenDoi.LayNgayDate(khach.getNgaySinh()));
        txtSDT.setText(khach.getSDT());
        txtCMND.setText(khach.getCMND());
        txtDiaChi.setText(khach.getDiaChi());
    }

    public void phong(PhongTro phongChon) {
        txtDienTich.setText(ChuyenDoi.SoString(phongChon.getDienTich()));
        LoaiPhong loaiPhong = BLL.BLLLoaiPhong.FindMaLoaiPhong(phongChon.getMaLoaiPhong().toString());
        System.out.println(loaiPhong.getGiaPhong() + "   " + loaiPhong.getMaLoaiPhong());
        txtGiaPhong.setText(ChuyenDoi.SoString(loaiPhong.getGiaPhong()));
    }
    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed
        // TODO add your handling code here:
        if (cbbMaKhachHang.getSelectedIndex() > 0 && cbbPhong.getSelectedIndex() > 0) {
            HoatDongThuePhong hoatDong = LayDataForm();
            if (!BLL.BLLHoatDongThuePhong.CheckMaHopDong(hoatDong.getMaPhong())) {
                ThongBao.ThongBaoDonGian("Thông báo", "Không hợp lệ!   ");

            } else {
                ThongBao.ThongBaoDonGian("Thông báo", "Mã hợp đồng hợp lệ!   ");
                if (hoatDong.getNgayTra() != null) {
                    BLL.BLLHoatDongThuePhong.Add(hoatDong, 0);
                } else {
                    BLL.BLLHoatDongThuePhong.Add(hoatDong, 1);
                }
//                LamMoi();
            }
            arrHD = BLL.BLLHoatDongThuePhong.GetAll();
            BLL.BLLHoatDongThuePhong.DoVaoTable(arrHD, tbHopDongThue);
            ArrayList<DTO.PhongTro> list = BLL.BLLPhongTro.GetAll();
            BLL.BLLPhongTro.DoVaoTable(list, tblPhongTro);
        } else {
            ThongBao.ThongBaoDonGian("Thông báo", "Vui lòng chọn người thuê và phòng thuê!");
        }
    }//GEN-LAST:event_btnTaoActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        // TODO add your handling code here:
        try {
            HoatDongThuePhong hoatDong = LayDataForm();
            if (hoatDong.getNgayTra() != null) {
                BLL.BLLHoatDongThuePhong.Update(hoatDong, 0);
            } else {
                BLL.BLLHoatDongThuePhong.Update(hoatDong, 1);

            }
            ThongBao.ThongBaoDonGian("Thông báo", "Đã cập nhật!   ");
            LamMoi();

        } catch (Exception e) {
            ThongBao.ThongBaoDonGian("Thông báo", "Vui lòng kiểm tra thông tin!   ");
        }

        arrHD = BLL.BLLHoatDongThuePhong.GetAll();
        BLL.BLLHoatDongThuePhong.DoVaoTable(arrHD, tbHopDongThue);
        ArrayList<DTO.PhongTro> list = BLL.BLLPhongTro.GetAll();
        BLL.BLLPhongTro.DoVaoTable(list, tblPhongTro);
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void btnLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi1ActionPerformed
        LamMoi();
        JOptionPane.showMessageDialog(this, "Làm mới Thành Công !!");
    }//GEN-LAST:event_btnLamMoi1ActionPerformed

    private void cbbMaKhachHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbMaKhachHangItemStateChanged
        // TODO add your handling code here:
        if (cbbMaKhachHang.getItemCount() > 1) {
            KhachThue khach = BLL.BLLKhachThue.FindByMaNguoiThue(cbbMaKhachHang.getSelectedItem().toString());
            TTkhackThue(khach);
        }
    }//GEN-LAST:event_cbbMaKhachHangItemStateChanged

    public void TTphong(PhongTro phongChon) {
        txtDienTich.setText(ChuyenDoi.SoString(phongChon.getDienTich()));
        LoaiPhong loaiPhong = BLL.BLLLoaiPhong.FindMaLoaiPhong(phongChon.getMaLoaiPhong().toString());
        System.out.println(loaiPhong.getGiaPhong() + "   " + loaiPhong.getMaLoaiPhong());
        txtGiaPhong.setText(ChuyenDoi.SoString(loaiPhong.getGiaPhong()));
    }

    public void TTkhackThue(KhachThue khach) {
        txtTenNguoiThueHD.setText(khach.getTenNguoiThue());
        dateNgaySinh.setDate(ChuyenDoi.LayNgayDate(khach.getNgaySinh()));
        txtSDT1.setText(khach.getSDT());
        txtCMND1.setText(khach.getCMND());
        txtDiaChi1.setText(khach.getDiaChi());
    }
    private void cbbPhongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbPhongItemStateChanged
        // TODO add your handling code here:
        System.out.println(cbbPhong.getItemCount());
        if (cbbPhong.getItemCount() > 1) {
            PhongTro phongChon = BLL.BLLPhongTro.FindMaPhong(cbbPhong.getSelectedItem().toString());
            TTphong(phongChon);
            String SoHopDong = BLL.BLLHoatDongThuePhong.SoHoaDon(cbbPhong.getSelectedItem().toString());
            txtMaHopDong.setText(SoHopDong);
        }
    }//GEN-LAST:event_cbbPhongItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        if (cbbPhong.getSelectedIndex() > 0) {
            String SoHopDong = BLL.BLLHoatDongThuePhong.SoHoaDon(cbbPhong.getSelectedItem().toString());
            txtMaHopDong.setText(SoHopDong);
        } else {
            ThongBao.ThongBaoDonGian("Thông báo", "Vui lòng chọn phòng trước!");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tbHopDongThueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHopDongThueMouseClicked
        // TODO add your handling code here:
        int dongDangChon = tbHopDongThue.getSelectedRow();
        if (dongDangChon < 0) {
            return;//Thoát
        }
        txtMaHopDong.setText(tbHopDongThue.getValueAt(dongDangChon, 0).toString());
        //Phòng và thông tin phòng thêm vào đây
        PhongTro phongChon = BLL.BLLPhongTro.FindMaPhong(tbHopDongThue.getValueAt(dongDangChon, 1).toString());
        BLL.BLLPhongTro.HienThiPhongTroCBB(cbbPhong, phongChon.getMaPhong().toString());
        phong(phongChon);
        //phòng

        KhachThue khach = BLL.BLLKhachThue.FindByMaNguoiThue(tbHopDongThue.getValueAt(dongDangChon, 2).toString());
        BLL.BLLKhachThue.HienThiKhachThueCBB(cbbMaKhachHang, khach.getMaNguoiThue());//tbHopDongThue.getValueAt(dongDangChon, 3).toString()
        khackThue(khach);
        dateNgayKy.setDate(ChuyenDoi.LayNgayDate(tbHopDongThue.getValueAt(dongDangChon, 4).toString()));
        if (tbHopDongThue.getValueAt(dongDangChon, 5) != null) {
            dateNgayKetThuc.setDate(ChuyenDoi.LayNgayDate(tbHopDongThue.getValueAt(dongDangChon, 5).toString()));
        } else {
            dateNgayKetThuc.setCalendar(null);
        }
        if (tbHopDongThue.getValueAt(dongDangChon, 6) != null) {
            txtGhiChu.setText(tbHopDongThue.getValueAt(dongDangChon, 6).toString());
        } else {
            txtGhiChu.setText(null);
        }
        if (tbHopDongThue.getValueAt(dongDangChon, 7).toString() == "Đang thuê") {
//            rbDaKetHDAC.setSelected(false);
            rbDangThueAC.setSelected(true);
        } else {
//            rbDaKetHDAC.setSelected(true);
            rbDangThueAC.setSelected(false);
        }
        //        txtDiaChi.setText(tbHopDongThue.getValueAt(dongDangChon, 5).toString());
        //        JDNgaySinh.setDate(ChuyenDoi.LayNgayDate(tbHopDongThue.getValueAt(dongDangChon, 6).toString()));
        //        if (tbHopDongThue.getValueAt(dongDangChon, 7) == null) {
        //            JDNgayVao.setDate(new Date());
        //        } else {
        //            JDNgayVao.setDate(ChuyenDoi.LayNgayDate(tbHopDongThue.getValueAt(dongDangChon, 7).toString()));
        //        }
        //        btnSuaKH.setEnabled(true);
    }//GEN-LAST:event_tbHopDongThueMouseClicked

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
        boolean TrangThai = JRDangSuDung.isSelected() ? true : false;
        DTO.DichVu dv = new DTO.DichVu(txtMaDichVu.getText(), txtTenDichVu.getText(),
                Float.parseFloat(txtGia.getText()), (String) cbbDonViTinh.getSelectedItem(),
                TrangThai);
        DAL.DALDichVu.Add(dv);
        ArrayList<DTO.DichVu> arr = BLL.BLLDichVu.GetAll();
        BLL.BLLDichVu.DoVaoTable(arr, tblDIchVu);
        LamMoi();
        helper.ThongBao.ThongBaoDonGian("thông báo", "Thêm thành công !!");
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        boolean TrangThai = JRDangSuDung.isSelected() ? true : false;
        DTO.DichVu dv = new DTO.DichVu(txtMaDichVu.getText(), txtTenDichVu.getText(),
                Float.parseFloat(txtGia.getText()), (String) cbbDonViTinh.getSelectedItem(),
                TrangThai);
        DAL.DALDichVu.Update(dv);
        ArrayList<DTO.DichVu> list = BLLDichVu.GetAll();
        BLLDichVu.DoVaoTable(list, tblDIchVu);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        // TODO add your handling code here:
        DTO.DichVu dv = new DTO.DichVu(txtMaDichVu.getText(), txtTenDichVu.getText(),
                Float.parseFloat(txtGia.getText()), (String) cbbDonViTinh.getSelectedItem(),
                true);
        DAL.DALDichVu.Delete(dv);
        ArrayList<DTO.DichVu> arr = BLL.BLLDichVu.GetAll();
        BLL.BLLDichVu.DoVaoTable(arr, tblDIchVu);
        LamMoi();
        helper.ThongBao.ThongBaoDonGian("thông báo", "Xóa thành công !!");
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void btnLamMoi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi2ActionPerformed
        LamMoi();
        JOptionPane.showMessageDialog(this, "Làm mới thành công !!");
        btnUpdate.setEnabled(false);
    }//GEN-LAST:event_btnLamMoi2ActionPerformed

    private void tblDIchVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDIchVuMouseClicked
        // TODO add your handling code here:
        btnUpdate.setEnabled(true);
        int count = tblDIchVu.getSelectedRow();
        txtMaDichVu.setText((String) tblDIchVu.getValueAt(count, 0));
        txtTenDichVu.setText((String) tblDIchVu.getValueAt(count, 1));
        txtGia.setText(String.valueOf(tblDIchVu.getValueAt(count, 3)));
        cbbDonViTinh.setSelectedItem(tblDIchVu.getValueAt(count, 2));
        if (tblDIchVu.getValueAt(count, 4).equals("Đang sử dụng")) {
            JRDangSuDung.setSelected(true);
        } else {
            JRKhongSuDung.setSelected(true);
        }
    }//GEN-LAST:event_tblDIchVuMouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void btResetNgayTraHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetNgayTraHDActionPerformed
        // TODO add your handling code here:
        rbDangThueAC.setSelected(false);
        dateNgayKetThuc.setDate(null);
    }//GEN-LAST:event_btResetNgayTraHDActionPerformed

    private void rbNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbNamActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void btnHInhAnhCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHInhAnhCDActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(btnHInhAnhCD);
        File file = chooser.getSelectedFile();
        String st = file.getPath();
        ImageIcon icon = new ImageIcon(new ImageIcon(file.getPath()).getImage().getScaledInstance(140, 200, Image.SCALE_SMOOTH));
        JLHinhAnh.setIcon(icon);
        System.out.println(st);
    }//GEN-LAST:event_btnHInhAnhCDActionPerformed

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        String MaNguoiThue;
        String TenNguoiThue;
        String CMND;
        String SDT;
        String DiaChi;
        String NgaySinh;

        String NgayTaoDT;
        boolean GioiTinh;
        boolean TrangThai;
        //            String NgayTra;
        if (validateform()) {
            MaNguoiThue = txtMaKH.getText();

            TenNguoiThue = txtTenKH.getText();
            DiaChi = txtDiaChi.getText();
            SDT = txtSDT.getText();

            //            if (rbNu.isSelected()) {
            //                GioiTinh = false;
            //            } else {
            //                GioiTinh = true;
            //            }
            if (rbDaTra.isSelected()) {
                TrangThai = true;
            } else {
                TrangThai = false;
            }
            CMND = txtCMND.getText();
            NgaySinh = ChuyenDoi.LayNgayString(JDNgaySinh.getDate());
            NgayTaoDT = ChuyenDoi.LayNgayString(JDNgayVao.getDate());

            KhachThue kh = new KhachThue(MaNguoiThue, TenNguoiThue, CMND, SDT, DiaChi, NgaySinh, NgayTaoDT, TrangThai);
            BLL.BLLKhachThue.Add(kh);
            ThongBao.ThongBaoDonGian("Thông báo", "Đã thêm!");
            ArrayList<KhachThue> arr = BLL.BLLKhachThue.GetAll();

            BLL.BLLKhachThue.DoVaoTable(arr, tblKhachThue);
        }
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void btnXoaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKHActionPerformed
        int dongDangChon = tblKhachThue.getSelectedRow();
        if (dongDangChon < 0) {
            ThongBao.ThongBaoDonGian("Thông Báo", "Bạn chưa chọn khách hàng cần xóa");
        } else {
            int XacNhan = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa không?", "Thông báo xác nhận", JOptionPane.OK_CANCEL_OPTION);
            if (XacNhan == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        //Lấy danh sách các KH  cần xóa
        int dongCanXoa[] = tblKhachThue.getSelectedRows();

        for (int i = 0; i < dongCanXoa.length; i++) {
            String MaKhachHang = tblKhachThue.getValueAt(dongCanXoa[i], 0).toString();
            BLL.BLLKhachThue.Delete(MaKhachHang);
        }
        ArrayList<KhachThue> arr = BLL.BLLKhachThue.GetAll();
        BLL.BLLKhachThue.DoVaoTable(arr, tblKhachThue);
    }//GEN-LAST:event_btnXoaKHActionPerformed

    private void btnSuaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKHActionPerformed
        String MaNguoiThue;
        String TenNguoiThue;
        String CMND;
        String SDT;
        String DiaChi;
        String NgaySinh;

        String NgayTaoDT;
        boolean GioiTinh;
        boolean TrangThai;
        //            String NgayTra;
        if (validateform()) {
            MaNguoiThue = txtMaKH.getText();

            TenNguoiThue = txtTenKH.getText();
            DiaChi = txtDiaChi.getText();
            SDT = txtSDT.getText();

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

            int dongDangChon = tblKhachThue.getSelectedRow();
            if (dongDangChon < 0) {
                ThongBao.ThongBaoDonGian("Thông Báo", "Bạn chưa chọn khách hàng cần sửa");
            }
            String MaKhachHang = tblKhachThue.getValueAt(dongDangChon, 0).toString();
            KhachThue kh = new KhachThue(MaNguoiThue, TenNguoiThue, CMND, SDT, DiaChi, NgaySinh, NgayTaoDT, TrangThai);
            BLL.BLLKhachThue.Update(kh);

            ArrayList<KhachThue> arr = BLL.BLLKhachThue.GetAll();

            BLL.BLLKhachThue.DoVaoTable(arr, tblKhachThue);
        }
    }//GEN-LAST:event_btnSuaKHActionPerformed

    private void txtTimKiemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKHActionPerformed

    private void txtTimKiemKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKHKeyReleased
        // TODO add your handling code here:

        String tuKhoa = txtTimKiemKH.getText();
        ArrayList<KhachThue> arrSP = BLL.BLLKhachThue.FindByName(tuKhoa);
        BLL.BLLKhachThue.DoVaoTable(arrSP, tblKhachThue);
    }//GEN-LAST:event_txtTimKiemKHKeyReleased

    private void rbDaTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDaTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbDaTraActionPerformed

    private void btnHInhCMNDTrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHInhCMNDTrcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHInhCMNDTrcActionPerformed

    private void btnHInhCMNDSauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHInhCMNDSauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHInhCMNDSauActionPerformed

    private void txtSDT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDT2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDT2ActionPerformed

    private void tblKhachThueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachThueMouseClicked
        int dongDangChon = tblKhachThue.getSelectedRow();
        if (dongDangChon < 0) {
            return;//Thoát
        }
        txtMaKH.setText(tblKhachThue.getValueAt(dongDangChon, 0).toString());
        txtTenKH.setText(tblKhachThue.getValueAt(dongDangChon, 1).toString());
        txtSDT.setText(tblKhachThue.getValueAt(dongDangChon, 2).toString());

        txtCMND.setText(tblKhachThue.getValueAt(dongDangChon, 3).toString());

        //        if (tblKhachThue.getValueAt(dongDangChon, 4).toString() == "Nam") {
        //            rbNu.setSelected(false);
        //            rbNam.setSelected(true);
        //        } else {
        //            rbNu.setSelected(true);
        //            rbNam.setSelected(false);
        //        }
        if (tblKhachThue.getValueAt(dongDangChon, 4).toString() == "Đang Thuê") {
            rbDaTra.setSelected(false);
            rbDangThue.setSelected(true);
        } else {
            rbDaTra.setSelected(true);
            rbDangThue.setSelected(false);
        }
        txtDiaChi.setText(tblKhachThue.getValueAt(dongDangChon, 5).toString());
        JDNgaySinh.setDate(ChuyenDoi.LayNgayDate(tblKhachThue.getValueAt(dongDangChon, 6).toString()));
        if (tblKhachThue.getValueAt(dongDangChon, 7) == null) {
            JDNgayVao.setDate(new Date());
        } else {
            JDNgayVao.setDate(ChuyenDoi.LayNgayDate(tblKhachThue.getValueAt(dongDangChon, 7).toString()));
        }
        btnSuaKH.setEnabled(true);
    }//GEN-LAST:event_tblKhachThueMouseClicked

    private void txtTimKiemHopDongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemHopDongKeyReleased
        // TODO add your handling code here:
        String tukhoa = txtTimKiemHopDong.getText();
        arrHD = BLL.BLLHoatDongThuePhong.FindByMaHopDongOrMaNguoiThue(tukhoa);
        BLL.BLLHoatDongThuePhong.DoVaoTable(arrHD, tbHopDongThue);
//        ArrayList<Phong> arrSP = BLL.BLLPhong.FindByName(tukhoa);
//        BLL.BLLPhong.doDataTable(arrSP, tblPhong);

    }//GEN-LAST:event_txtTimKiemHopDongKeyReleased

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
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JDNgaySinh;
    private com.toedter.calendar.JDateChooser JDNgayVao;
    private javax.swing.JLabel JLHinhAnh;
    private javax.swing.JLabel JLHinhCMNDSau;
    private javax.swing.JLabel JLHinhCMNDTrc;
    private javax.swing.JRadioButton JRDangSuDung;
    private javax.swing.JRadioButton JRKhongSuDung;
    private javax.swing.JLabel LabelDongho;
    public static javax.swing.JMenuItem MenuHopDong;
    private javax.swing.JMenuItem MenuSuaPhong;
    public static javax.swing.JMenuItem MenuThemKhachThue;
    private javax.swing.JMenuItem MenuXoa;
    private javax.swing.JMenuItem PPMenuChiTietPhong;
    private javax.swing.JButton btResetNgayTraHD;
    private javax.swing.JButton btnHInhAnhCD;
    private javax.swing.JButton btnHInhCMNDSau;
    private javax.swing.JButton btnHInhCMNDTrc;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoi1;
    private javax.swing.JButton btnLamMoi2;
    private javax.swing.JButton btnResetKH;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnSuaKH;
    private javax.swing.JButton btnTao;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa1;
    private javax.swing.JButton btnXoaKH;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbDonViTinh;
    private javax.swing.JComboBox<String> cbbMaKhachHang;
    private javax.swing.JComboBox<String> cbbPhong;
    private com.toedter.calendar.JDateChooser dateNgayKetThuc;
    private com.toedter.calendar.JDateChooser dateNgayKy;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel panelDichVu;
    private javax.swing.JPanel panelDsPhongTro;
    private javax.swing.JPanel panelFormDichVu;
    private javax.swing.JPanel panelFormHoaDon;
    private javax.swing.JPanel panelHoaDon;
    private javax.swing.JPanel panelHongDong;
    private javax.swing.JPanel panelHopDong;
    private javax.swing.JPanel panelKhachThue;
    private javax.swing.JPanel panelKhachThue2;
    private javax.swing.JPanel panelLoaiPhong;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelQLLP;
    private javax.swing.JPanel panelTrangChu;
    private javax.swing.JRadioButton rbDaTra;
    private javax.swing.JRadioButton rbDangThue;
    private javax.swing.JRadioButton rbDangThueAC;
    private javax.swing.JRadioButton rbNam;
    private javax.swing.JRadioButton rbNu;
    private javax.swing.JTable tbHopDongThue;
    private javax.swing.JTable tblDIchVu;
    private javax.swing.JTable tblKhachThue;
    private javax.swing.JTable tblLoaiPhong;
    public static javax.swing.JTable tblPhongTro;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JLabel txtCMND1;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JLabel txtDiaChi1;
    private javax.swing.JLabel txtDienTich;
    private javax.swing.JLabel txtDoanhThu;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtGiaDien;
    private javax.swing.JTextField txtGiaNuoc;
    private javax.swing.JLabel txtGiaPhong;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JTextField txtMaDichVu;
    private javax.swing.JTextField txtMaHopDong;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaLoaiPhong;
    public static javax.swing.JLabel txtPhongDaThue;
    public static javax.swing.JLabel txtPhongTrong;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JLabel txtSDT1;
    private javax.swing.JTextField txtSDT2;
    public static javax.swing.JLabel txtSoLuongNguoiThue;
    private javax.swing.JTextField txtTenDichVu;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenLoaiPhong;
    private javax.swing.JLabel txtTenNguoiThueHD;
    private javax.swing.JTextField txtTimKiemHopDong;
    private javax.swing.JTextField txtTimKiemKH;
    // End of variables declaration//GEN-END:variables
}
