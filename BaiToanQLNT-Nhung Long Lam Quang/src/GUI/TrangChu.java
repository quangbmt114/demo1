/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.*;
import DTO.ChiSoDienNuoc;
import DTO.DichVu;
import DTO.HoaDonPhongTro;
import DTO.HoatDongThuePhong;
import DTO.KhachThue;
import DTO.LoaiPhong;
import DTO.PhongTro;
import static GUI.FormDaiDien.cbbDaiDien;
import groovy.time.TimeCategory;
import helper.ChuyenDoi;
import helper.MyCombobox;
import helper.ThongBao;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.event.ConnectionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

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
    ArrayList<HoaDonPhongTro> arrHDPT = new ArrayList<>();
    ArrayList<ChiSoDienNuoc> arrCSDN = new ArrayList<>();
    ArrayList<DTO.DichVu> arrDV = new ArrayList<>();
    String AnhCD;
    String AnhCMNDTrc;
    String AnhCMNDSau;

    public TrangChu() {
        initComponents();

        ArrayList<LoaiPhong> listLP = BLL.BLLLoaiPhong.GetAll();
        BLL.BLLLoaiPhong.DoVaoTable(listLP, tblLoaiPhong);
        ArrayList<KhachThue> listKH = BLL.BLLKhachThue.GetAll();
        BLL.BLLKhachThue.DoVaoTable(listKH, tblKhachThue);
        ImageIcon icon = new ImageIcon("src/images/blue-home-icon.png");
        setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        DongHo();

        ArrayList<DTO.PhongTro> list = BLL.BLLPhongTro.GetAll();
        BLL.BLLPhongTro.DoVaoTable(list, tblPhongTro);
        //lấy dữ liệu ddataabase và setText Thống kê lương người đang thuê phòng

        FillPhong();
        ArrayList<DTO.PhongTro> listPhongTro = BLL.BLLPhongTro.GetAll();
        BLL.BLLPhongTro.DoVaoTable(listPhongTro, tblPhongTro);
        panelMain.add(panelTrangChu, "TrangChu");
        panelMain.add(panelLoaiPhong, "LoaiPhong");
        panelMain.add(panelKhachThue2, "KhachThue");
        panelMain.add(panelHongDong, "HopDong");
        panelMain.add(panelFormHoaDon, "HoaDon");
        panelMain.add(panelFormDichVu, "DichVu");
        panelMain.add(panelFormDienNuoc, "DienNuoc");

        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, "TrangChu");
        JLQLPhong.setBackground(Color.decode("#3333ff"));
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

        if (txtMaLoaiPhong.getText().equals("") || txtTenLoaiPhong.getText().equals("")
                || txtGiaTien.getText().equals("") || txtGiaDien.getText().equals("") || txtGiaNuoc.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ĐẦY ĐỦ THÔNG TIN!");
            return false;
        }
        return true;

    }

    public boolean validateformKhach() {

        if (txtMaKH.getText().equals("") || txtTenKH.getText().equals("")
                || txtCMND.getText().equals("") || txtSDT.getText().equals("") || txtDiaChi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ĐẦY ĐỦ THÔNG TIN!");
            return false;
        }
        return true;

    }

    public void FillPhong() {
        Date now = new Date();
        ArrayList<KhachThue> arrCountKT = BLLKhachThue.CountKhachThue();
        txtSoLuongNguoiThue.setText(String.valueOf(arrCountKT.size()));
        ArrayList<PhongTro> ListPhong = BLL.BLLPhongTro.GetAll();
        //tạo biến để mặc định để thống kê phòng
        int PhongTrong = 0;
        int PhongDaThue = 0;

        //chạy vòng lặp để kiểm tra tình trạng phòng
        for (DTO.PhongTro pt : ListPhong) {
            if (pt.isTrangThai()) {
                PhongTrong++;
            } else {
                PhongDaThue++;
            }
        }
        float Total = 0;
        String thangHienTai = String.valueOf(now.getMonth());
        String namHienTai = String.valueOf(now.getYear());
        txtThangHienTai.setText(String.valueOf(Integer.parseInt(thangHienTai) + 1) + "/ " + String.valueOf(Integer.parseInt(namHienTai) - 100));
        arrHDPT = BLLHoaDon.GetAll();
        for (HoaDonPhongTro hoaDonPhongTro : arrHDPT) {
            Date thang1 = ChuyenDoi.LayNgayDate(String.valueOf(hoaDonPhongTro.getThangTieuThu()));
            String thangHoaDon = String.valueOf(thang1.getMonth());
            String namHoaDon = String.valueOf(thang1.getYear());
            if (thangHoaDon.equals(thangHienTai) && namHoaDon.equals(namHienTai)) {
                Total += hoaDonPhongTro.getTongTien();
                System.out.println(hoaDonPhongTro.getTongTien());
            }
            System.out.println(thang1.getMonth());
        }
        txtDoanhThu.setText(String.valueOf(Total));
        // set số liệu cho Bảng thống kê
        txtPhongTrong.setText(String.valueOf(PhongDaThue));
        txtPhongDaThue.setText(String.valueOf(PhongTrong));

        if (ListPhong != null) {
            panelDsPhongTro.removeAll();
            JButton[] btn = new JButton[ListPhong.size()];
            for (int i = 0; i < ListPhong.size(); i++) {
                ArrayList<KhachThue> arrKT = BLLKhachThue.FindByMaKhachThueAndTrangThai(String.valueOf(ListPhong.get(i).getMaPhong()));
                btn[i] = new JButton();
                btn[i].setName(String.valueOf(ListPhong.get(i).getMaPhong()));
                String[] mb = ListPhong.get(i).getTenPhong().split(" ");
                String tenPhong = "";
                for (int kt = 0; kt < (mb.length); kt++) {
                    tenPhong += mb[kt];
                    if (kt < mb.length - 1) {
                        tenPhong += " ";
                    }
                }
                btn[i].setText("<html> " + tenPhong
                        + "<br> Số lượng khách : " + arrKT.size()
                        + "<br>Trạng thái : " + String.valueOf(ListPhong.get(i).isTrangThai() ? "Đã thuê" : "Chưa thuê") + "<html>");
                String fileAnhcd = "./src/images/house.png";
                ImageIcon icon = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH));
                btn[i].setIcon(icon);
                Border thickBorder = new LineBorder(Color.WHITE, 4);
                btn[i].setBorder(thickBorder);
                btn[i].setBackground(Color.decode("#8080ff"));
                btn[i].setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                btn[i].setForeground(new java.awt.Color(51, 51, 51));
                System.out.println("Bàn" + i);
                if (ListPhong.get(i).isTrangThai()) {
                    btn[i].setBackground(Color.GREEN);
                }
                String maPhong = ListPhong.get(i).getTenPhong();
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
                        FormChiTiet ct = new FormChiTiet(null, true);
                        ct.TenPhong.setText(maPhong);
                        ct.setLocationRelativeTo(null);
                        ct.setVisible(true);

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

    public void openedPanelHopDong() {// load thông tin panel hợp đồng
        //set kích thước table
        tblHopDongThue.getColumnModel().getColumn(0).setPreferredWidth(105);
        tblHopDongThue.getColumnModel().getColumn(1).setPreferredWidth(40);
        tblHopDongThue.getColumnModel().getColumn(2).setPreferredWidth(80);
        tblHopDongThue.getColumnModel().getColumn(3).setPreferredWidth(125);
        //dổ dữ liệu vào bảng
        arrHD = BLL.BLLHoatDongThuePhong.GetAll();
        arrKT = BLL.BLLKhachThue.GetAll();
        BLL.BLLKhachThue.doComboBox(arrKT, cbbMaKhachHang);
//        TTkhackThue(khach);
        arrPT = BLL.BLLPhongTro.GetAll();
        BLL.BLLPhongTro.doComboBox(arrPT, cbbPhong);
        BLL.BLLPhongTro.doComboBox(arrPT, cbbJtbHopDong);
        BLL.BLLHoatDongThuePhong.DoVaoTable(arrHD, tblHopDongThue);
        //set default value
        LamMoi();
        dateNgayKy.setDate(new Date());
        rbDangThueAC.setSelected(true);
    }

    public void openedPanelHoaDon() {// load thông tin panel hợp đồng
        //set kích thước table
//        tbHopDongThue.getColumnModel().getColumn(0).setPreferredWidth(105);
//        tbHopDongThue.getColumnModel().getColumn(1).setPreferredWidth(40);
//        tbHopDongThue.getColumnModel().getColumn(2).setPreferredWidth(80);
//        tbHopDongThue.getColumnModel().getColumn(3).setPreferredWidth(125);
        //dổ dữ liệu vào bảng
//        jTblHoaDon
//cbbPhongTroHDPT
//        arrHD = BLL.BLLHoatDongThuePhong.GetAll();
//        arrKT = BLL.BLLKhachThue.GetAll();
//        BLL.BLLKhachThue.doComboBox(arrKT, cbbMaKhachHang);
//        TTkhackThue(khach);
        arrPT = BLL.BLLPhongTro.GetAll();
        BLL.BLLPhongTro.doComboBox(arrPT, cbbPhongTroHDPT);
//        arrPT = BLL.BLLPhongTro.GetAll();
        BLL.BLLPhongTro.doComboBox(arrPT, cbbJtbHDPT);
        arrHDPT = BLL.BLLHoaDon.GetAll();
        BLL.BLLHoaDon.DoVaoTable(arrHDPT, jTblHoaDon);
//        BLL.BLLHoatDongThuePhong.DoVaoTable(arrHD, tbHopDongThue);
//        //set default value
//        dateNgayKy.setDate(new Date());
//        rbDangThueAC.setSelected(true);
//        txtMaHoaDonPT.setText(BLL.BLLHoaDon.SoHoaDon("PA01", new Date(), new Date()));
    }

    public void openedPanelDichVu() {// load thông tin panel hợp đồng
        //set kích thước table
//        tbHopDongThue.getColumnModel().getColumn(0).setPreferredWidth(105);
//        tbHopDongThue.getColumnModel().getColumn(1).setPreferredWidth(40);
//        tbHopDongThue.getColumnModel().getColumn(2).setPreferredWidth(80);
//        tbHopDongThue.getColumnModel().getColumn(3).setPreferredWidth(125);
        //dổ dữ liệu vào bảng
//        jTblHoaDon
//cbbPhongTroHDPT
//        arrHD = BLL.BLLHoatDongThuePhong.GetAll();
//        arrKT = BLL.BLLKhachThue.GetAll();
//        BLL.BLLKhachThue.doComboBox(arrKT, cbbMaKhachHang);
//        TTkhackThue(khach);
        arrDV = BLL.BLLDichVu.GetAll();
        BLL.BLLDichVu.DoVaoTable(arrDV, tblDIchVu);
//        BLL.BLLHoatDongThuePhong.DoVaoTable(arrHD, tbHopDongThue);
//        //set default value
//        dateNgayKy.setDate(new Date());
//        rbDangThueAC.setSelected(true);
//        txtMaHoaDonPT.setText(BLL.BLLHoaDon.SoHoaDon("PA01", new Date(), new Date()));
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
        JLQLPhong = new javax.swing.JLabel();
        panelQLLP = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        JLQLLoaiPhong = new javax.swing.JLabel();
        panelKhachThue = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        JLQLKhachThue = new javax.swing.JLabel();
        panelHopDong = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        JLQLHopDong = new javax.swing.JLabel();
        panelHoaDon = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        JLQLHoaDon = new javax.swing.JLabel();
        panelDichVu = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        JLQLDichVu = new javax.swing.JLabel();
        LabelDongho = new javax.swing.JLabel();
        panelDienNuoc = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        JLQLDienNuoc = new javax.swing.JLabel();
        panelMain = new javax.swing.JPanel();
        panelTrangChu = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtDoanhThu = new javax.swing.JLabel();
        txtThangHienTai = new javax.swing.JLabel();
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
        jButton2 = new javax.swing.JButton();
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
        txtEmail = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblKhachThue = new javax.swing.JTable();
        TBEmail = new javax.swing.JLabel();
        cbbJtbKhachThue = new javax.swing.JComboBox<>();
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
        txtSDTHopDong = new javax.swing.JLabel();
        txtCMNDHopDong = new javax.swing.JLabel();
        txtDiaChiHopDong = new javax.swing.JLabel();
        cbbPhong = new javax.swing.JComboBox<>();
        txtDienTich = new javax.swing.JLabel();
        txtGiaPhong = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel77 = new javax.swing.JLabel();
        txtTenNguoiThueHD = new javax.swing.JLabel();
        btResetNgayTraHD = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHopDongThue = new javax.swing.JTable();
        jLabel53 = new javax.swing.JLabel();
        txtTimKiemHopDong = new javax.swing.JTextField();
        cbbJtbHopDong = new javax.swing.JComboBox<>();
        panelFormHoaDon = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnUpdHoaDonHDPT = new javax.swing.JButton();
        btnLamMoiHoaDon = new javax.swing.JButton();
        txtSoDienMoiHD = new javax.swing.JLabel();
        txtSoNuocMoiHD = new javax.swing.JLabel();
        txtNgayGhiMoiHD = new javax.swing.JLabel();
        txtSoDienTieuThuHD = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        txtSoNuocTieuThuHD = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        cbbPhongTroHDPT = new javax.swing.JComboBox<>();
        jLabel78 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        cbbChiSoMoiHDPT = new javax.swing.JComboBox<>();
        jLabel90 = new javax.swing.JLabel();
        cbbChiSoCuHDPT = new javax.swing.JComboBox<>();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        txtSoDienCuHD = new javax.swing.JLabel();
        txtSoNuocCuHD = new javax.swing.JLabel();
        txtNgayGhiCuHD = new javax.swing.JLabel();
        txtGiaDienHDPT = new javax.swing.JLabel();
        txtGiaNuocHDPT = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        txtTienDienHDPT = new javax.swing.JLabel();
        txtTienNuocHDPT = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        txtTienPhongHDPT = new javax.swing.JLabel();
        txtTienDichVuHDPT = new javax.swing.JLabel();
        txtMaHoaDonPT = new javax.swing.JLabel();
        txtTongTienHDPT = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        txtDaiDienPhongHDPT = new javax.swing.JLabel();
        txtMailLLHDPT = new javax.swing.JLabel();
        btnXuatHoaDon = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        txtSearchHoaDon = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTblHoaDon = new javax.swing.JTable();
        cbbJtbHDPT = new javax.swing.JComboBox<>();
        panelFormDichVu = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txtMaDichVu = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtTenDichVu = new javax.swing.JTextField();
        txtGiaDichVu = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        btnThem1 = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnXoa1 = new javax.swing.JButton();
        btnLamMoi2 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDIchVu = new javax.swing.JTable();
        txtErrGiaDichVu = new javax.swing.JLabel();
        panelFormDienNuoc = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDienNuocQLCS = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        dateNgayGhiDienNuoc = new com.toedter.calendar.JDateChooser();
        txtSoDienQLDN = new javax.swing.JTextField();
        lblMaSP2 = new javax.swing.JLabel();
        lblMaSP = new javax.swing.JLabel();
        lblTenSP = new javax.swing.JLabel();
        txtSoNuocQLDN = new javax.swing.JTextField();
        lblGiaNhap = new javax.swing.JLabel();
        cbMaPhongQLDN = new javax.swing.JComboBox<>();
        btnUpdCSDienNuoc = new javax.swing.JButton();
        btnAddCSDN = new javax.swing.JButton();
        btnDelCSDN = new javax.swing.JButton();
        lblMaSP3 = new javax.swing.JLabel();
        txtMaChiSoCSDN = new javax.swing.JLabel();
        btnSetMaChiSo = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        btnResetFormCSDN = new javax.swing.JButton();
        txtErrSoDienCSDN = new javax.swing.JLabel();
        txtErrSoNuocCSDN = new javax.swing.JLabel();
        cbPhongtbQLCSDN = new javax.swing.JComboBox<>();

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

        JLQLPhong.setBackground(new java.awt.Color(51, 204, 255));
        JLQLPhong.setFont(new java.awt.Font("UTM Charlemagne", 1, 14)); // NOI18N
        JLQLPhong.setForeground(new java.awt.Color(255, 255, 255));
        JLQLPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLQLPhong.setText("QUẢN LÝ PHÒNG");
        JLQLPhong.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JLQLPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
            .addComponent(JLQLPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        JLQLLoaiPhong.setBackground(new java.awt.Color(0, 204, 255));
        JLQLLoaiPhong.setFont(new java.awt.Font("UTM Charlemagne", 1, 14)); // NOI18N
        JLQLLoaiPhong.setForeground(new java.awt.Color(255, 255, 255));
        JLQLLoaiPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLQLLoaiPhong.setText("QUẢN LÝ LOẠI PHÒNG");
        JLQLLoaiPhong.setOpaque(true);

        javax.swing.GroupLayout panelQLLPLayout = new javax.swing.GroupLayout(panelQLLP);
        panelQLLP.setLayout(panelQLLPLayout);
        panelQLLPLayout.setHorizontalGroup(
            panelQLLPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLLPLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLQLLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelQLLPLayout.setVerticalGroup(
            panelQLLPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
            .addComponent(JLQLLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        JLQLKhachThue.setBackground(new java.awt.Color(0, 204, 255));
        JLQLKhachThue.setFont(new java.awt.Font("UTM Charlemagne", 1, 14)); // NOI18N
        JLQLKhachThue.setForeground(new java.awt.Color(255, 255, 255));
        JLQLKhachThue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLQLKhachThue.setText("QUẢN LÝ KHÁCH THUÊ");
        JLQLKhachThue.setOpaque(true);

        javax.swing.GroupLayout panelKhachThueLayout = new javax.swing.GroupLayout(panelKhachThue);
        panelKhachThue.setLayout(panelKhachThueLayout);
        panelKhachThueLayout.setHorizontalGroup(
            panelKhachThueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKhachThueLayout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLQLKhachThue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelKhachThueLayout.setVerticalGroup(
            panelKhachThueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
            .addComponent(JLQLKhachThue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        JLQLHopDong.setBackground(new java.awt.Color(0, 204, 255));
        JLQLHopDong.setFont(new java.awt.Font("UTM Charlemagne", 1, 14)); // NOI18N
        JLQLHopDong.setForeground(new java.awt.Color(255, 255, 255));
        JLQLHopDong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLQLHopDong.setText("QUẢN LÝ HỢP ĐỒNG");
        JLQLHopDong.setOpaque(true);

        javax.swing.GroupLayout panelHopDongLayout = new javax.swing.GroupLayout(panelHopDong);
        panelHopDong.setLayout(panelHopDongLayout);
        panelHopDongLayout.setHorizontalGroup(
            panelHopDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHopDongLayout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLQLHopDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelHopDongLayout.setVerticalGroup(
            panelHopDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
            .addComponent(JLQLHopDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        JLQLHoaDon.setBackground(new java.awt.Color(0, 204, 255));
        JLQLHoaDon.setFont(new java.awt.Font("UTM Charlemagne", 1, 14)); // NOI18N
        JLQLHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        JLQLHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLQLHoaDon.setText("QUẢN LÝ HÓA ĐƠN");
        JLQLHoaDon.setOpaque(true);

        javax.swing.GroupLayout panelHoaDonLayout = new javax.swing.GroupLayout(panelHoaDon);
        panelHoaDon.setLayout(panelHoaDonLayout);
        panelHoaDonLayout.setHorizontalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDonLayout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLQLHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelHoaDonLayout.setVerticalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
            .addComponent(JLQLHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        JLQLDichVu.setBackground(new java.awt.Color(0, 204, 255));
        JLQLDichVu.setFont(new java.awt.Font("UTM Charlemagne", 1, 14)); // NOI18N
        JLQLDichVu.setForeground(new java.awt.Color(255, 255, 255));
        JLQLDichVu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLQLDichVu.setText("QUẢN LÝ DỊCH VỤ");
        JLQLDichVu.setOpaque(true);

        javax.swing.GroupLayout panelDichVuLayout = new javax.swing.GroupLayout(panelDichVu);
        panelDichVu.setLayout(panelDichVuLayout);
        panelDichVuLayout.setHorizontalGroup(
            panelDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDichVuLayout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLQLDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDichVuLayout.setVerticalGroup(
            panelDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
            .addComponent(JLQLDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        LabelDongho.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        LabelDongho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelDongho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alarm-64px.png"))); // NOI18N

        panelDienNuoc.setBackground(new java.awt.Color(204, 204, 204));
        panelDienNuoc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelDienNuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelDienNuocMouseClicked(evt);
            }
        });

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/water-tap-32px.png"))); // NOI18N
        jLabel32.setOpaque(true);

        JLQLDienNuoc.setBackground(new java.awt.Color(0, 204, 255));
        JLQLDienNuoc.setFont(new java.awt.Font("UTM Charlemagne", 1, 14)); // NOI18N
        JLQLDienNuoc.setForeground(new java.awt.Color(255, 255, 255));
        JLQLDienNuoc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLQLDienNuoc.setText("QUẢN LÝ ĐIỆN NƯỚC");
        JLQLDienNuoc.setOpaque(true);

        javax.swing.GroupLayout panelDienNuocLayout = new javax.swing.GroupLayout(panelDienNuoc);
        panelDienNuoc.setLayout(panelDienNuocLayout);
        panelDienNuocLayout.setHorizontalGroup(
            panelDienNuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDienNuocLayout.createSequentialGroup()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLQLDienNuoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDienNuocLayout.setVerticalGroup(
            panelDienNuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
            .addComponent(JLQLDienNuoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
                    .addComponent(LabelDongho, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDienNuoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDienNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jLabel10.setText("DOANH THU THÁNG");

        jLabel11.setFont(new java.awt.Font("UTM Times", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("VNĐ");

        txtDoanhThu.setFont(new java.awt.Font("UTM Times", 1, 36)); // NOI18N
        txtDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDoanhThu.setText("0");

        txtThangHienTai.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtThangHienTai.setForeground(new java.awt.Color(255, 255, 255));
        txtThangHienTai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtThangHienTai.setText("THANG");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThangHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThangHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
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
                .addComponent(txtSoLuongNguoiThue, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jPanel23.setBackground(new java.awt.Color(51, 51, 255));

        jLabel13.setBackground(new java.awt.Color(51, 51, 255));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
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
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
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
        panelDsPhongTro.setInheritsPopupMenu(true);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jButton2.setText("THÊM PHÒNG");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTrangChuLayout = new javax.swing.GroupLayout(panelTrangChu);
        panelTrangChu.setLayout(panelTrangChuLayout);
        panelTrangChuLayout.setHorizontalGroup(
            panelTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTrangChuLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(panelDsPhongTro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTrangChuLayout.setVerticalGroup(
            panelTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrangChuLayout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDsPhongTro, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                .addContainerGap())
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
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 1334, Short.MAX_VALUE)
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

        txtCMND.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCMNDKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCMNDKeyTyped(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel83.setText("Giới tính");

        rbNam.setBackground(new java.awt.Color(0, 204, 255));
        buttonGroup1.add(rbNam);
        rbNam.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        rbNam.setText("Nam");
        rbNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNamActionPerformed(evt);
            }
        });

        rbNu.setBackground(new java.awt.Color(0, 204, 255));
        buttonGroup1.add(rbNu);
        rbNu.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        rbNu.setText("Nữ");

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
        btnResetKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetKHActionPerformed(evt);
            }
        });

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

        TBEmail.setForeground(new java.awt.Color(255, 0, 51));

        cbbJtbKhachThue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbJtbKhachThue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbJtbKhachThue.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbJtbKhachThueItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(btnHInhCMNDTrc)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHInhCMNDSau, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(62, 62, 62))
                        .addGroup(jPanel18Layout.createSequentialGroup()
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                                .addComponent(TBEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel18Layout.createSequentialGroup()
                                            .addGap(43, 43, 43)
                                            .addComponent(rbDangThue)
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(rbDaTra)
                                                .addComponent(rbNu)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtCMND, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(JDNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(JDNgayVao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(jPanel18Layout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(rbNam)
                                        .addGroup(jPanel18Layout.createSequentialGroup()
                                            .addComponent(jLabel87)
                                            .addGap(89, 89, 89))))
                                .addGroup(jPanel18Layout.createSequentialGroup()
                                    .addComponent(btnThemKH)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnXoaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnSuaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnResetKH))
                                .addGroup(jPanel18Layout.createSequentialGroup()
                                    .addComponent(JLHinhCMNDTrc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(JLHinhCMNDSau, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(15, 15, 15)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel18Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(btnHInhAnhCD, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                    .addComponent(JLHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10))))
                        .addGap(17, 17, 17)
                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(cbbJtbKhachThue, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(cbbJtbKhachThue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
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
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel89))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TBEmail)
                        .addGap(2, 2, 2)
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
                            .addComponent(JDNgayVao, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JLHinhCMNDSau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLHinhCMNDTrc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHInhCMNDTrc)
                            .addComponent(btnHInhCMNDSau))
                        .addGap(18, 18, 18)
                        .addComponent(JLHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHInhAnhCD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel88))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemKH)
                            .addComponent(btnXoaKH)
                            .addComponent(btnSuaKH)
                            .addComponent(btnResetKH))
                        .addGap(15, 15, 15))))
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
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 1328, Short.MAX_VALUE)
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

        dateNgayKy.setDateFormatString("dd/MM/yyyy");

        dateNgayKetThuc.setDateFormatString("dd/MM/yyyy");

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

        txtSDTHopDong.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtSDTHopDong.setText("   ");

        txtCMNDHopDong.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtCMNDHopDong.setText("   ");

        txtDiaChiHopDong.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtDiaChiHopDong.setText("   ");

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
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                            .addComponent(txtDiaChiHopDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addComponent(txtSDTHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel47)
                                .addGap(24, 24, 24)
                                .addComponent(txtCMNDHopDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnTao, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnLamMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
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
                    .addComponent(txtSDTHopDong)
                    .addComponent(txtCMNDHopDong))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtDiaChiHopDong))
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
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel50))
                    .addComponent(btResetNgayTraHD)
                    .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(191, Short.MAX_VALUE))
        );

        tblHopDongThue.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHopDongThue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHopDongThueMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblHopDongThue);

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

        cbbJtbHopDong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbJtbHopDong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbJtbHopDong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbJtbHopDongItemStateChanged(evt);
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
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(cbbJtbHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiemHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbJtbHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jLabel72.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel72.setText("Tiền DV");

        jLabel73.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel73.setText("Tổng tiền");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/notebook_1.png"))); // NOI18N
        jButton1.setText("Tạo HĐ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnUpdHoaDonHDPT.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdHoaDonHDPT.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnUpdHoaDonHDPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/updated.png"))); // NOI18N
        btnUpdHoaDonHDPT.setText("Cập nhật");
        btnUpdHoaDonHDPT.setEnabled(false);
        btnUpdHoaDonHDPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdHoaDonHDPTActionPerformed(evt);
            }
        });

        btnLamMoiHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        btnLamMoiHoaDon.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnLamMoiHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        btnLamMoiHoaDon.setText("Làm Mới");
        btnLamMoiHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiHoaDonActionPerformed(evt);
            }
        });

        txtSoDienMoiHD.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtSoDienMoiHD.setText("   ");

        txtSoNuocMoiHD.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtSoNuocMoiHD.setText("   ");

        txtNgayGhiMoiHD.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtNgayGhiMoiHD.setText("   ");

        txtSoDienTieuThuHD.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtSoDienTieuThuHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSoDienTieuThuHD.setText("   ");

        jDateChooser2.setDateFormatString("dd-MM-yyyy");

        txtSoNuocTieuThuHD.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtSoNuocTieuThuHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSoNuocTieuThuHD.setText("   ");

        jLabel61.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel61.setText("Mã HĐ");

        jLabel62.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel62.setText("Phòng");

        cbbPhongTroHDPT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbPhongTroHDPT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbPhongTroHDPT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbPhongTroHDPTItemStateChanged(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel78.setText("Chỉ số mới");

        jLabel63.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel63.setText("ĐD phòng");

        cbbChiSoMoiHDPT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbChiSoMoiHDPT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbChiSoMoiHDPT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbChiSoMoiHDPTItemStateChanged(evt);
            }
        });

        jLabel90.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel90.setText("Chỉ só cũ");

        cbbChiSoCuHDPT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbChiSoCuHDPT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbChiSoCuHDPT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbChiSoCuHDPTItemStateChanged(evt);
            }
        });

        jLabel91.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel91.setText("Số điện mới");

        jLabel92.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel92.setText("Số nước mới");

        jLabel93.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel93.setText("Ngày ghi");

        jLabel64.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel64.setText("Ngày tạo");

        jLabel65.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel65.setText("Điện TT");

        jLabel66.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel66.setText("Nước TT");

        jLabel67.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel67.setText("Giá");

        jLabel68.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel68.setText("Giá");

        jLabel97.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel97.setText("Số điện cũ");

        jLabel98.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel98.setText("Số nước cũ");

        jLabel99.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel99.setText("Ngày ghi");

        txtSoDienCuHD.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtSoDienCuHD.setText("   ");

        txtSoNuocCuHD.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtSoNuocCuHD.setText("   ");

        txtNgayGhiCuHD.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtNgayGhiCuHD.setText("   ");

        txtGiaDienHDPT.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtGiaDienHDPT.setText("   ");

        txtGiaNuocHDPT.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtGiaNuocHDPT.setText("   ");

        jLabel69.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel69.setText("Tiền điện");

        jLabel70.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel70.setText("Tiền nước");

        txtTienDienHDPT.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtTienDienHDPT.setText("   ");

        txtTienNuocHDPT.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtTienNuocHDPT.setText("   ");

        jLabel71.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel71.setText("Tiền phòng");

        txtTienPhongHDPT.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtTienPhongHDPT.setText("   ");

        txtTienDichVuHDPT.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtTienDichVuHDPT.setText("   ");

        txtMaHoaDonPT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaHoaDonPT.setText("Mã hợp đồng");

        txtTongTienHDPT.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        txtTongTienHDPT.setText("   ");

        jLabel74.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jLabel74.setText("Email LL");

        txtDaiDienPhongHDPT.setText("   ");

        txtMailLLHDPT.setText("   ");

        btnXuatHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        btnXuatHoaDon.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        btnXuatHoaDon.setText("Xuất phiếu");
        btnXuatHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel63)
                                    .addComponent(jLabel61)
                                    .addComponent(jLabel74))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDaiDienPhongHDPT)
                                    .addComponent(txtMailLLHDPT)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                        .addComponent(txtMaHoaDonPT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel62)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbPhongTroHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40))))
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtTienPhongHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel71)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel65)
                                            .addComponent(jLabel66))
                                        .addGap(1, 1, 1)
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel16Layout.createSequentialGroup()
                                                .addComponent(txtSoNuocTieuThuHD, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel68))
                                            .addGroup(jPanel16Layout.createSequentialGroup()
                                                .addComponent(txtSoDienTieuThuHD, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel67)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtGiaDienHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGiaNuocHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel16Layout.createSequentialGroup()
                                                .addComponent(jLabel69)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtTienDienHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel16Layout.createSequentialGroup()
                                                .addComponent(jLabel70)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtTienNuocHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jLabel93)
                                    .addComponent(jLabel92)
                                    .addComponent(jLabel91)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel64)
                                            .addComponent(jLabel78))
                                        .addGap(31, 31, 31)
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel16Layout.createSequentialGroup()
                                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cbbChiSoMoiHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                                        .addGap(2, 2, 2)
                                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(txtSoNuocMoiHD, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtNgayGhiMoiHD, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtSoDienMoiHD, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(7, 7, 7)
                                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel99)
                                                            .addComponent(jLabel98)
                                                            .addComponent(jLabel97))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(txtSoNuocCuHD, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtNgayGhiCuHD, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtSoDienCuHD, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                                        .addComponent(jLabel90)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(cbbChiSoCuHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(jLabel72)
                                        .addGap(40, 40, 40)
                                        .addComponent(txtTienDichVuHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(txtTongTienHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel73))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdHoaDonHDPT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLamMoiHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXuatHoaDon)
                        .addGap(46, 46, 46))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel61)
                        .addComponent(txtMaHoaDonPT)
                        .addComponent(cbbPhongTroHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel63)
                    .addComponent(txtDaiDienPhongHDPT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMailLLHDPT)
                    .addComponent(jLabel74))
                .addGap(23, 23, 23)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel64)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel78)
                    .addComponent(cbbChiSoCuHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbChiSoMoiHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel90)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel91)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel92)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel93))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel97)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel98)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel99))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(txtSoDienCuHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSoNuocCuHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNgayGhiCuHD))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(txtSoDienMoiHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSoNuocMoiHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNgayGhiMoiHD)))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(jLabel67)
                    .addComponent(txtSoDienTieuThuHD)
                    .addComponent(txtGiaDienHDPT)
                    .addComponent(jLabel69)
                    .addComponent(txtTienDienHDPT))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(jLabel68)
                    .addComponent(txtSoNuocTieuThuHD)
                    .addComponent(txtGiaNuocHDPT)
                    .addComponent(jLabel70)
                    .addComponent(txtTienNuocHDPT))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(txtTienPhongHDPT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(txtTienDichVuHDPT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(txtTongTienHDPT))
                .addGap(98, 98, 98)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdHoaDonHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(246, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(0, 204, 255));

        txtSearchHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchHoaDonKeyReleased(evt);
            }
        });

        jTblHoaDon.setFont(new java.awt.Font("UTM Times", 1, 14)); // NOI18N
        jTblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã HĐ", "Mã phòng", "Ngày tạo", "Chỉ số mới", "Chỉ số cũ", "Tiền phòng", "Tiền điện", "Tiền nước", "Tiền DV", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTblHoaDon);

        cbbJtbHDPT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbJtbHDPT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbJtbHDPT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbJtbHDPTItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbJtbHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearchHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbJtbHDPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7))
        );

        javax.swing.GroupLayout panelFormHoaDonLayout = new javax.swing.GroupLayout(panelFormHoaDon);
        panelFormHoaDon.setLayout(panelFormHoaDonLayout);
        panelFormHoaDonLayout.setHorizontalGroup(
            panelFormHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormHoaDonLayout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFormHoaDonLayout.setVerticalGroup(
            panelFormHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        txtGiaDichVu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiaDichVuKeyReleased(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("UTM Times", 1, 18)); // NOI18N
        jLabel57.setText("Giá");

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
                "Mã dịch vụ", "Tên dịch vụ", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDIchVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDIchVuMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblDIchVu);

        txtErrGiaDichVu.setForeground(new java.awt.Color(255, 51, 0));
        txtErrGiaDichVu.setText("   ");

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
                            .addComponent(jLabel57))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGiaDichVu)
                            .addComponent(txtTenDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                            .addComponent(txtMaDichVu)
                            .addComponent(txtErrGiaDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(434, Short.MAX_VALUE))
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
                    .addComponent(txtGiaDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtErrGiaDichVu)
                .addGap(106, 106, 106)
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

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Điện nước hàng tháng");

        tbDienNuocQLCS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã chỉ số", "Mã phòng", "Điện", "Nước", "Ngày ghi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDienNuocQLCS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDienNuocQLCSMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbDienNuocQLCS);

        dateNgayGhiDienNuoc.setDateFormatString("yyy-MM-dd");

        txtSoDienQLDN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoDienQLDNKeyReleased(evt);
            }
        });

        lblMaSP2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMaSP2.setForeground(new java.awt.Color(255, 102, 0));
        lblMaSP2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaSP2.setText("Mã phòng:");

        lblMaSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMaSP.setForeground(new java.awt.Color(255, 102, 0));
        lblMaSP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaSP.setText("Số điện");

        lblTenSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTenSP.setForeground(new java.awt.Color(255, 102, 0));
        lblTenSP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenSP.setText("Số nước");

        txtSoNuocQLDN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoNuocQLDNKeyReleased(evt);
            }
        });

        lblGiaNhap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblGiaNhap.setForeground(new java.awt.Color(255, 102, 0));
        lblGiaNhap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGiaNhap.setText("Ngày ghi");

        cbMaPhongQLDN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMaPhongQLDN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMaPhongQLDNItemStateChanged(evt);
            }
        });

        btnUpdCSDienNuoc.setText("Sửa");
        btnUpdCSDienNuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdCSDienNuocActionPerformed(evt);
            }
        });

        btnAddCSDN.setText("Thêm");
        btnAddCSDN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCSDNActionPerformed(evt);
            }
        });

        btnDelCSDN.setText("Xóa");
        btnDelCSDN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelCSDNActionPerformed(evt);
            }
        });

        lblMaSP3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMaSP3.setForeground(new java.awt.Color(255, 102, 0));
        lblMaSP3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaSP3.setText("Mã chỉ số:");

        txtMaChiSoCSDN.setText("Mã chỉ số");

        btnSetMaChiSo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh-16px.png"))); // NOI18N
        btnSetMaChiSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetMaChiSoActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Phòng Trọ");

        btnResetFormCSDN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh-16px.png"))); // NOI18N
        btnResetFormCSDN.setText("Làm mới");
        btnResetFormCSDN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetFormCSDNActionPerformed(evt);
            }
        });

        txtErrSoDienCSDN.setForeground(new java.awt.Color(255, 51, 0));
        txtErrSoDienCSDN.setText("   ");

        txtErrSoNuocCSDN.setForeground(new java.awt.Color(255, 51, 0));
        txtErrSoNuocCSDN.setText("   ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaSP3)
                                    .addComponent(lblGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                        .addComponent(txtMaChiSoCSDN, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSetMaChiSo))
                                    .addComponent(dateNgayGhiDienNuoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMaSP)
                                    .addComponent(lblMaSP2))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtErrSoDienCSDN, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtSoDienQLDN)
                                        .addComponent(txtSoNuocQLDN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtErrSoNuocCSDN, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbMaPhongQLDN, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btnAddCSDN, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdCSDienNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelCSDN, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetFormCSDN, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(168, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSP3)
                            .addComponent(txtMaChiSoCSDN)
                            .addComponent(btnSetMaChiSo))
                        .addGap(18, 18, 18)
                        .addComponent(lblGiaNhap))
                    .addComponent(dateNgayGhiDienNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaSP2)
                    .addComponent(cbMaPhongQLDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSoDienQLDN, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaSP))
                .addGap(6, 6, 6)
                .addComponent(txtErrSoDienCSDN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenSP)
                    .addComponent(txtSoNuocQLDN, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtErrSoNuocCSDN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddCSDN)
                    .addComponent(btnUpdCSDienNuoc)
                    .addComponent(btnDelCSDN)
                    .addComponent(btnResetFormCSDN))
                .addContainerGap())
        );

        cbPhongtbQLCSDN.setBackground(new java.awt.Color(204, 255, 204));
        cbPhongtbQLCSDN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbPhongtbQLCSDN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbPhongtbQLCSDNItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelFormDienNuocLayout = new javax.swing.GroupLayout(panelFormDienNuoc);
        panelFormDienNuoc.setLayout(panelFormDienNuocLayout);
        panelFormDienNuocLayout.setHorizontalGroup(
            panelFormDienNuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelFormDienNuocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormDienNuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormDienNuocLayout.createSequentialGroup()
                        .addComponent(cbPhongtbQLCSDN, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelFormDienNuocLayout.setVerticalGroup(
            panelFormDienNuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormDienNuocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormDienNuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormDienNuocLayout.createSequentialGroup()
                        .addComponent(cbPhongtbQLCSDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE))
                    .addGroup(panelFormDienNuocLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        panelMain.add(panelFormDienNuoc, "card8");

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
        ArrayList<LoaiPhong> listKH = BLL.BLLLoaiPhong.GetAll();
        BLL.BLLLoaiPhong.DoVaoTable(listKH, tblLoaiPhong);
        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, "LoaiPhong");
        JLQLPhong.setBackground(Color.decode("#33ccff"));
        JLQLLoaiPhong.setBackground(Color.decode("#3333ff"));
        JLQLKhachThue.setBackground(Color.decode("#33ccff"));
        JLQLHopDong.setBackground(Color.decode("#33ccff"));
        JLQLHoaDon.setBackground(Color.decode("#33ccff"));
        JLQLDichVu.setBackground(Color.decode("#33ccff"));
        JLQLDienNuoc.setBackground(Color.decode("#33ccff"));
    }//GEN-LAST:event_panelQLLPMouseClicked

    private void panelKhachThueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelKhachThueMouseClicked
        // TODO add your handling code here:
        ArrayList<KhachThue> listKH = BLL.BLLKhachThue.GetAll();
        BLL.BLLKhachThue.DoVaoTable(listKH, tblKhachThue);
        arrPT = BLL.BLLPhongTro.GetAll();
        BLL.BLLPhongTro.doComboBox(arrPT, cbbJtbKhachThue);
        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, "KhachThue");
        JLQLPhong.setBackground(Color.decode("#33ccff"));
        JLQLLoaiPhong.setBackground(Color.decode("#33ccff"));
        JLQLKhachThue.setBackground(Color.decode("#3333ff"));
        JLQLHopDong.setBackground(Color.decode("#33ccff"));
        JLQLHoaDon.setBackground(Color.decode("#33ccff"));
        JLQLDichVu.setBackground(Color.decode("#33ccff"));
        JLQLDienNuoc.setBackground(Color.decode("#33ccff"));
    }//GEN-LAST:event_panelKhachThueMouseClicked

    private void panelHopDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHopDongMouseClicked
        // TODO add your handling code here:
        ArrayList<HoatDongThuePhong> listKH = BLL.BLLHoatDongThuePhong.GetAll();
        BLL.BLLHoatDongThuePhong.DoVaoTable(listKH, tblHopDongThue);
        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, "HopDong");
        JLQLPhong.setBackground(Color.decode("#33ccff"));
        JLQLLoaiPhong.setBackground(Color.decode("#33ccff"));
        JLQLKhachThue.setBackground(Color.decode("#33ccff"));
        JLQLHopDong.setBackground(Color.decode("#3333ff"));
        JLQLHoaDon.setBackground(Color.decode("#33ccff"));
        JLQLDichVu.setBackground(Color.decode("#33ccff"));
        JLQLDienNuoc.setBackground(Color.decode("#33ccff"));
        openedPanelHopDong();
    }//GEN-LAST:event_panelHopDongMouseClicked

    private void panelHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHoaDonMouseClicked
        // TODO add your handling code here:
        ArrayList<HoaDonPhongTro> listKH = BLL.BLLHoaDon.GetAll();
        BLL.BLLHoaDon.DoVaoTable(listKH, jTblHoaDon);
        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, "HoaDon");
        JLQLPhong.setBackground(Color.decode("#33ccff"));
        JLQLLoaiPhong.setBackground(Color.decode("#33ccff"));
        JLQLKhachThue.setBackground(Color.decode("#33ccff"));
        JLQLHopDong.setBackground(Color.decode("#33ccff"));
        JLQLHoaDon.setBackground(Color.decode("#3333ff"));
        JLQLDichVu.setBackground(Color.decode("#33ccff"));
        JLQLDienNuoc.setBackground(Color.decode("#33ccff"));
        openedPanelHoaDon();
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
        ArrayList<DichVu> listKH = BLL.BLLDichVu.GetAll();
        BLL.BLLDichVu.DoVaoTable(listKH, tblDIchVu);
        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, "DichVu");
        JLQLPhong.setBackground(Color.decode("#33ccff"));
        JLQLLoaiPhong.setBackground(Color.decode("#33ccff"));
        JLQLKhachThue.setBackground(Color.decode("#33ccff"));
        JLQLHopDong.setBackground(Color.decode("#33ccff"));
        JLQLHoaDon.setBackground(Color.decode("#33ccff"));
        JLQLDichVu.setBackground(Color.decode("#3333ff"));
        JLQLDienNuoc.setBackground(Color.decode("#33ccff"));
        openedPanelDichVu();
    }//GEN-LAST:event_panelDichVuMouseClicked
    public static String text = "";
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
        ArrayList<KhachThue> arrCountKT = BLLKhachThue.CountKhachThue();
        txtSoLuongNguoiThue.setText(String.valueOf(arrCountKT.size()));
        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, "TrangChu");
        FillPhong();
        JLQLPhong.setBackground(Color.decode("#3333ff"));
        JLQLLoaiPhong.setBackground(Color.decode("#33ccff"));
        JLQLKhachThue.setBackground(Color.decode("#33ccff"));
        JLQLHopDong.setBackground(Color.decode("#33ccff"));
        JLQLHoaDon.setBackground(Color.decode("#33ccff"));
        JLQLDichVu.setBackground(Color.decode("#33ccff"));
        JLQLDienNuoc.setBackground(Color.decode("#33ccff"));
    }//GEN-LAST:event_jPanel4MouseClicked
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

    public void LamMoi() {
        // TODO add your handling code here:
        txtMaHopDong.setText("");
        cbbMaKhachHang.setSelectedIndex(0);
        txtTenNguoiThueHD.setText("");
        txtSDTHopDong.setText("");
        txtCMNDHopDong.setText("");
        txtDiaChiHopDong.setText("");
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

    public void LamMoiKhachThue() {
        // TODO add your handling code here:
        txtMaKH.setText("");
        txtTenKH.setText("");
        JDNgaySinh.setDate(null);
        JDNgayVao.setDate(null);
        txtSDT.setText("");
        txtCMND.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        rbNam.setSelected(false);
        rbNu.setSelected(false);
        rbDangThue.setSelected(false);
        rbDaTra.setSelected(false);
        String fileAnhcd = "./src/images/man-128px.png";
        ImageIcon iconAnhCD = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(140, 180, Image.SCALE_SMOOTH));
        ImageIcon iconAnhCMNDtrc = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
        ImageIcon iconAnhCMNDSau = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
        JLHinhAnh.setIcon(iconAnhCD);
        JLHinhCMNDTrc.setIcon(iconAnhCMNDtrc);
        JLHinhCMNDSau.setIcon(iconAnhCMNDSau);
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
            if (hoatDong != null) {
                Date ngayky = dateNgayKy.getDate();
                if (new Date().before(ngayky)) {
                    ThongBao.ThongBaoDonGian("Thông báo", "Ngày ký không được sau ngày hiện tại");
                    return;
                }
                if (hoatDong.getNgayTra() != null) {
                    Date ngaykt = dateNgayKetThuc.getDate();
                    if ((new Date().before(ngaykt) && ngaykt != null)) {
                        ThongBao.ThongBaoDonGian("Thông báo", "Ngày kết thúc không được sau ngày hiện tại");
                        return;
                    }
                    if (dateNgayKetThuc.getDate() != null) {
                        if (ngayky.after(ngaykt)) {
                            ThongBao.ThongBaoDonGian("Thông báo", "Ngày ký không đước sau ngày kết thúc!");
                            return;
                        }
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
//                LamMoi();
                }
                arrHD = BLL.BLLHoatDongThuePhong.GetAll();
                BLL.BLLHoatDongThuePhong.DoVaoTable(arrHD, tblHopDongThue);
                ArrayList<DTO.PhongTro> list = BLL.BLLPhongTro.GetAll();
                BLL.BLLPhongTro.DoVaoTable(list, tblPhongTro);
            } else {
                ThongBao.ThongBaoDonGian("Thông báo", "Vui lòng chọn người thuê và phòng thuê!");
            }
        }
        LamMoi();
    }//GEN-LAST:event_btnTaoActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        // TODO add your handling code here:
//        try {
        if (cbbMaKhachHang.getSelectedIndex() > 0 && cbbPhong.getSelectedIndex() > 0) {
            HoatDongThuePhong hoatDong = LayDataForm();
            if (hoatDong != null) {
                if (BLL.BLLHoatDongThuePhong.CheckMaHopDong(hoatDong.getMaHopDong())) {
                    ThongBao.ThongBaoDonGian("Thông báo", "Mã hợp đồng không tồn tại!   ");
                } else {
                    if (!BLLHoatDongThuePhong.FindMaHopDong(txtMaHopDong.getText()).isTinhTrang() && hoatDong.getNgayTra() == null) {
                        ThongBao.ThongBaoDonGian("Thông báo", "Không thể chỉnh hợp đồng từ đã kết thúc thành hoạt động!!");
                        return;
                    }
                    if (hoatDong.getNgayTra() != null) {
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
//                ThongBao.ThongBaoDonGian("Thông báo", "Mã hợp đồng hợp lệ!");

                        if (BLLHoatDongThuePhong.FindMaHopDong(txtMaHopDong.getText()).isTinhTrang()) {
                            BLL.BLLHoatDongThuePhong.Update(hoatDong, 0);
                        } else {
                            BLL.BLLHoatDongThuePhong.Update(hoatDong, 2);
                        }

                    } else {
                        BLL.BLLHoatDongThuePhong.Update(hoatDong, 1);

                    }
                    ThongBao.ThongBaoDonGian("Thông báo", "Đã cập nhật!   ");
                    LamMoi();
                }
            }
        } else {
            ThongBao.ThongBaoDonGian("Thông báo", "Vui lòng điền thông tin cần cập nhật!");
        }
//        } catch (Exception e) {
//            ThongBao.ThongBaoDonGian("Thông báo", "Vui lòng kiểm tra thông tin!   ");
//        }

        arrHD = BLL.BLLHoatDongThuePhong.GetAll();
        BLL.BLLHoatDongThuePhong.DoVaoTable(arrHD, tblHopDongThue);
        ArrayList<DTO.PhongTro> list = BLL.BLLPhongTro.GetAll();
        BLL.BLLPhongTro.DoVaoTable(list, tblPhongTro);
        LamMoi();

    }//GEN-LAST:event_btnSua1ActionPerformed

    private void btnLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi1ActionPerformed
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

    public void TTphong(PhongTro phongChon) {
        txtDienTich.setText(ChuyenDoi.SoString(phongChon.getDienTich()));
        LoaiPhong loaiPhong = BLL.BLLLoaiPhong.FindMaLoaiPhong(phongChon.getMaLoaiPhong().toString());
        txtGiaPhong.setText(ChuyenDoi.SoString(loaiPhong.getGiaPhong()));
    }

    public void TTkhackThue(KhachThue khach) {
        txtTenNguoiThueHD.setText(khach.getTenNguoiThue());
        dateNgaySinh.setDate(ChuyenDoi.LayNgayDate(khach.getNgaySinh()));
        txtSDTHopDong.setText(khach.getSDT());
        txtCMNDHopDong.setText(khach.getCMND());
        txtDiaChiHopDong.setText(khach.getDiaChi());
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        if (cbbPhong.getSelectedIndex() > 0) {
            String SoHopDong = BLL.BLLHoatDongThuePhong.SoHoaDon(cbbPhong.getSelectedItem().toString());
            txtMaHopDong.setText(SoHopDong);
        } else {
            ThongBao.ThongBaoDonGian("Thông báo", "Vui lòng chọn phòng trước!");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tblHopDongThueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHopDongThueMouseClicked
        // TODO add your handling code here:
        int dongDangChon = tblHopDongThue.getSelectedRow();
        if (dongDangChon < 0) {
            return;//Thoát
        }
        //Phòng và thông tin phòng thêm vào đây
        PhongTro phongChon = BLL.BLLPhongTro.FindMaPhong(tblHopDongThue.getValueAt(dongDangChon, 1).toString());
        BLL.BLLPhongTro.HienThiPhongTroCBB(cbbPhong, phongChon.getMaPhong().toString());
        phong(phongChon);
        //phòng

        KhachThue khach = BLL.BLLKhachThue.FindByMaNguoiThue(tblHopDongThue.getValueAt(dongDangChon, 2).toString());
        BLL.BLLKhachThue.HienThiKhachThueCBB(cbbMaKhachHang, khach.getMaNguoiThue());//tbHopDongThue.getValueAt(dongDangChon, 3).toString()
        khackThue(khach);
        dateNgayKy.setDate(ChuyenDoi.LayNgayDate(tblHopDongThue.getValueAt(dongDangChon, 4).toString()));
        if (tblHopDongThue.getValueAt(dongDangChon, 5) != null) {
            dateNgayKetThuc.setDate(ChuyenDoi.LayNgayDate(tblHopDongThue.getValueAt(dongDangChon, 5).toString()));
        } else {
            dateNgayKetThuc.setCalendar(null);
        }
        if (tblHopDongThue.getValueAt(dongDangChon, 6) != null) {
            txtGhiChu.setText(tblHopDongThue.getValueAt(dongDangChon, 6).toString());
        } else {
            txtGhiChu.setText(null);
        }
        if (tblHopDongThue.getValueAt(dongDangChon, 7).toString() == "Đang thuê") {
            rbDangThueAC.setSelected(true);
        } else {
            rbDangThueAC.setSelected(false);
        }
        txtMaHopDong.setText(tblHopDongThue.getValueAt(dongDangChon, 0).toString());
    }//GEN-LAST:event_tblHopDongThueMouseClicked

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
//        boolean TrangThai = JRDangSuDung.isSelected() ? true : false;
        if (txtErrGiaDichVu.getText().equals("   ") || txtMaDichVu.getText().equals("") || txtTenDichVu.getText().equals("") || txtGiaDichVu.getText().equals("")) {
            DichVu dv = new DichVu(txtMaDichVu.getText(), txtTenDichVu.getText(),
                    Integer.parseInt(txtGiaDichVu.getText()));
//                ,(String) cbbDonViTinh.getSelectedItem(),                TrangThai
            try {
                BLL.BLLDichVu.Add(dv);
                ArrayList<DTO.DichVu> arr = BLL.BLLDichVu.GetAll();
                BLL.BLLDichVu.DoVaoTable(arr, tblDIchVu);
//        LamMoiFormDV();
                helper.ThongBao.ThongBaoDonGian("thông báo", "Thêm thành công !!");

            } catch (Exception e) {
                helper.ThongBao.ThongBaoDonGian("thông báo", "Vui lòng kiểm tra lại thông tin !!");
            }
        } else {
            helper.ThongBao.ThongBaoDonGian("thông báo", "Vui lòng kiểm tra lại thông tin !!");
        }
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
//        boolean TrangThai = JRDangSuDung.isSelected() ? true : false;
        if (txtErrGiaDichVu.getText().equals("   ") || txtMaDichVu.getText().equals("") || txtTenDichVu.getText().equals("") || txtGiaDichVu.getText().equals("")) {
            if (!BLLDichVu.CheckMaDichVu(txtMaDichVu.getText())) {
                DTO.DichVu dv = new DTO.DichVu(txtMaDichVu.getText(), txtTenDichVu.getText(),
                        Integer.parseInt(txtGiaDichVu.getText()));
//                , (String) cbbDonViTinh.getSelectedItem(), TrangThai
                DAL.DALDichVu.Update(dv);

                ThongBao.ThongBaoDonGian("Thông Báo", "Đã sửa!");
                ArrayList<DTO.DichVu> list = BLLDichVu.GetAll();
                BLLDichVu.DoVaoTable(list, tblDIchVu);
            } else {
                ThongBao.ThongBaoDonGian("Thông Báo", "Không có mã dịch vụ!");
            }
//        LamMoiFormDV(); 
        } else {
            helper.ThongBao.ThongBaoDonGian("thông báo", "Vui lòng kiểm tra lại thông tin !!");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        // TODO add your handling code here:
        int dongDangChon = tblDIchVu.getSelectedRow();
        if (dongDangChon < 0) {
            ThongBao.ThongBaoDonGian("Thông Báo", "Bạn chưa chọn đối tượng cần xóa!");
            return;
        } else {
            int XacNhan = JOptionPane.showConfirmDialog(null, "Một số phòng có thể vẫn đang dùng dịch vụ, bạn có chắc xóa không?", "Thông báo xác nhận", JOptionPane.OK_CANCEL_OPTION);
            if (XacNhan == JOptionPane.CANCEL_OPTION) {
                return;
            }
//        }
            //Lấy danh sách các sp  cần xóa
            try {
                int dongCanXoa[] = tblDIchVu.getSelectedRows();

//            MyCombobox tenPhong = (MyCombobox) cbPhong.getSelectedItem();
//            System.out.println(cbPhong.getSelectedItem());
//            ArrayList<Phong> arrP = BLL.BLLPhong.FindByName(tenPhong + "");
//        arrDV=BLLDichVu.
//            if (arrP.size() > 0) {
                for (int i = 0; i < dongCanXoa.length; i++) {
//                    String maPhong = arrP.get(0).getMaPhong();
//                    Date ngayGhi = ChuyenDoi.LayNgayDate(tblDIchVu.getValueAt(dongCanXoa[i], 0).toString());
                    BLL.BLLDichVu.Delete(tblDIchVu.getValueAt(dongCanXoa[i], 0).toString());
                }
//            }
                ThongBao.ThongBaoDonGian("Thông Báo", "Đã xóa");
            } catch (Exception e) {
                ThongBao.ThongBaoDonGian("Thông Báo", "Dịch vụ đang được dùng, vui lòng xem lại!!");
            }
//        DTO.frmDichVu dv = new DTO.frmDichVu(txtMaDichVu.getText(), txtTenDichVu.getText(),
//                Float.parseFloat(txtGia.getText()), (String) cbbDonViTinh.getSelectedItem(),
//                true);
//        DAL.DALDichVu.Delete(dv);
            ArrayList<DTO.DichVu> arr = BLL.BLLDichVu.GetAll();
            BLL.BLLDichVu.DoVaoTable(arr, tblDIchVu);
//        LamMoiFormDV();
//        helper.ThongBao.ThongBaoDonGian("thông báo", "Xóa thành công !!");
        }
    }//GEN-LAST:event_btnXoa1ActionPerformed

    public void LamMoiFormDV() {
        txtMaDichVu.setText("");
        txtTenDichVu.setText("");
        txtGiaDichVu.setText("");
    }
    private void btnLamMoi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi2ActionPerformed
        LamMoiFormDV();
        JOptionPane.showMessageDialog(this, "Làm mới thành công !!");
        btnUpdate.setEnabled(false);
    }//GEN-LAST:event_btnLamMoi2ActionPerformed

    private void tblDIchVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDIchVuMouseClicked
        // TODO add your handling code here:
        btnUpdate.setEnabled(true);
        int count = tblDIchVu.getSelectedRow();
        txtMaDichVu.setText((String) tblDIchVu.getValueAt(count, 0));
        txtTenDichVu.setText((String) tblDIchVu.getValueAt(count, 1));
        txtGiaDichVu.setText(String.valueOf(tblDIchVu.getValueAt(count, 2)));
//        cbbDonViTinh.setSelectedItem(tblDIchVu.getValueAt(count, 2));
//        if (tblDIchVu.getValueAt(count, 4).equals("Đang sử dụng")) {
//            JRDangSuDung.setSelected(true);
//        } else {
//            JRKhongSuDung.setSelected(true);
//        }
    }//GEN-LAST:event_tblDIchVuMouseClicked

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
        JFileChooser chooser = new JFileChooser("./src/images");
        chooser.showOpenDialog(btnHInhAnhCD);
        File file = chooser.getSelectedFile();
        AnhCD = file.getName();
        ImageIcon icon = new ImageIcon(new ImageIcon(file.getPath()).getImage().getScaledInstance(140, 200, Image.SCALE_SMOOTH));
        JLHinhAnh.setIcon(icon);

    }//GEN-LAST:event_btnHInhAnhCDActionPerformed

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
        //            String NgayTra;
        if (validateformKhach()) {
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
                TrangThai = false;
            } else {
                TrangThai = true;
            }
            CMND = txtCMND.getText();
            NgaySinh = ChuyenDoi.LayNgayString(JDNgaySinh.getDate());
            NgayTaoDT = ChuyenDoi.LayNgayString(JDNgayVao.getDate());
            System.out.println("abc" + AnhCD);
            KhachThue kh = new KhachThue(MaNguoiThue, TenNguoiThue, CMND, SDT, Email,
                    DiaChi, NgaySinh, NgayTaoDT, GioiTinh, TrangThai, AnhCD, AnhCMNDTrc, AnhCMNDSau);
            BLL.BLLKhachThue.Add(kh);
            ThongBao.ThongBaoDonGian("Thông báo", "Đã thêm!");
            ArrayList<KhachThue> arr = BLL.BLLKhachThue.GetAll();
            BLL.BLLKhachThue.DoVaoTable(arr, tblKhachThue);
        }
        LamMoiKhachThue();
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
            if (tblKhachThue.getValueAt(dongCanXoa[i], 0).toString().equals("Đã trả")) {
                String MaKhachHang = tblKhachThue.getValueAt(dongCanXoa[i], 9).toString();
                BLL.BLLKhachThue.Delete(MaKhachHang);
            } else {
                ThongBao.ThongBaoDonGian("Thông báo", "Khách hàng mã số " + tblKhachThue.getValueAt(dongCanXoa[i], 0).toString() + " đang thuê phòng, không thể xóa!");
            }
        }
        ArrayList<KhachThue> arr = BLL.BLLKhachThue.GetAll();
        BLL.BLLKhachThue.DoVaoTable(arr, tblKhachThue);
        LamMoiKhachThue();
    }//GEN-LAST:event_btnXoaKHActionPerformed

    private void btnSuaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKHActionPerformed
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

        //            String NgayTra;
        if (validateformKhach()) {
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
                TrangThai = false;
            } else {
                TrangThai = true;
            }
            CMND = txtCMND.getText();
            NgaySinh = ChuyenDoi.LayNgayString(JDNgaySinh.getDate());
            NgayTaoDT = ChuyenDoi.LayNgayString(JDNgayVao.getDate());

            int dongDangChon = tblKhachThue.getSelectedRow();
            if (dongDangChon < 0) {
                ThongBao.ThongBaoDonGian("Thông Báo", "Bạn chưa chọn khách hàng cần sửa");
            }
//            String MaKhachHang = tblKhachThue.getValueAt(dongDangChon, 0).toString();
            KhachThue kh = new KhachThue(MaNguoiThue, TenNguoiThue, CMND, SDT, Email, DiaChi, NgaySinh, NgayTaoDT, GioiTinh, TrangThai, AnhCD, AnhCMNDTrc, AnhCMNDSau);
            BLL.BLLKhachThue.Update(kh);
            ArrayList<KhachThue> arr = BLL.BLLKhachThue.GetAll();
            BLL.BLLKhachThue.DoVaoTable(arr, tblKhachThue);
            LamMoiKhachThue();
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
        JFileChooser chooser = new JFileChooser("./src/images");
        chooser.showOpenDialog(btnHInhCMNDTrc);
        File file = chooser.getSelectedFile();
        AnhCMNDTrc = file.getName();
        ImageIcon icon = new ImageIcon(new ImageIcon(file.getPath()).getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH));
        JLHinhCMNDTrc.setIcon(icon);

    }//GEN-LAST:event_btnHInhCMNDTrcActionPerformed

    private void btnHInhCMNDSauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHInhCMNDSauActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser("./src/images");
        chooser.showOpenDialog(btnHInhCMNDSau);
        File file = chooser.getSelectedFile();
        AnhCMNDSau = file.getName();
        ImageIcon icon = new ImageIcon(new ImageIcon(file.getPath()).getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH));
        JLHinhCMNDSau.setIcon(icon);

    }//GEN-LAST:event_btnHInhCMNDSauActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void tblKhachThueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachThueMouseClicked
        int dongDangChon = tblKhachThue.getSelectedRow();
        if (dongDangChon < 0) {
            return;//Thoát
        }
        txtMaKH.setText(tblKhachThue.getValueAt(dongDangChon, 0).toString());
        txtTenKH.setText(tblKhachThue.getValueAt(dongDangChon, 1).toString());
        txtCMND.setText(tblKhachThue.getValueAt(dongDangChon, 2).toString());
        txtSDT.setText(tblKhachThue.getValueAt(dongDangChon, 3).toString());
        txtEmail.setText(tblKhachThue.getValueAt(dongDangChon, 4).toString());
        txtDiaChi.setText(tblKhachThue.getValueAt(dongDangChon, 5).toString());
        JDNgaySinh.setDate(ChuyenDoi.LayNgayDate(tblKhachThue.getValueAt(dongDangChon, 6).toString()));

        if (tblKhachThue.getValueAt(dongDangChon, 7) == null) {
            JDNgayVao.setDate(new Date());
        } else {
            JDNgayVao.setDate(ChuyenDoi.LayNgayDate(tblKhachThue.getValueAt(dongDangChon, 7).toString()));
        }
        if (tblKhachThue.getValueAt(dongDangChon, 8).toString() == "Nam") {
            rbNu.setSelected(false);
            rbNam.setSelected(true);
        } else {
            rbNu.setSelected(true);
            rbNam.setSelected(false);
        }
        if (tblKhachThue.getValueAt(dongDangChon, 9).toString() == "Đang Thuê") {
            rbDaTra.setSelected(false);
            rbDangThue.setSelected(true);
        } else {
            rbDaTra.setSelected(true);
            rbDangThue.setSelected(false);
        }
        String fileAnhCD = "./src/images/" + tblKhachThue.getValueAt(dongDangChon, 10);
        String fileAnhCMNDtrc = "./src/images/" + tblKhachThue.getValueAt(dongDangChon, 11);
        String fileAnhCMNDSau = "./src/images/" + tblKhachThue.getValueAt(dongDangChon, 12);
        if (tblKhachThue.getValueAt(dongDangChon, 10) == null && tblKhachThue.getValueAt(dongDangChon, 11) == null
                && tblKhachThue.getValueAt(dongDangChon, 12) == null) {
            String fileAnhcd = "./src/images/man-128px.png";
            ImageIcon iconAnhCD = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(140, 180, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDtrc = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDSau = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            JLHinhAnh.setIcon(iconAnhCD);
            JLHinhCMNDTrc.setIcon(iconAnhCMNDtrc);
            JLHinhCMNDSau.setIcon(iconAnhCMNDSau);
        } else if (tblKhachThue.getValueAt(dongDangChon, 11) == null && tblKhachThue.getValueAt(dongDangChon, 10) == null) {
            String fileAnhcd = "./src/images/man-128px.png";
            ImageIcon iconAnhCD = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(140, 180, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDtrc = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDSau = new ImageIcon(new ImageIcon(fileAnhCMNDSau).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            JLHinhAnh.setIcon(iconAnhCD);
            JLHinhCMNDTrc.setIcon(iconAnhCMNDtrc);
            JLHinhCMNDSau.setIcon(iconAnhCMNDSau);
        } else if (tblKhachThue.getValueAt(dongDangChon, 12) == null && tblKhachThue.getValueAt(dongDangChon, 10) == null) {
            String fileAnhcd = "./src/images/man-128px.png";
            ImageIcon iconAnhCD = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(140, 180, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDtrc = new ImageIcon(new ImageIcon(fileAnhCMNDtrc).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDSau = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            JLHinhAnh.setIcon(iconAnhCD);
            JLHinhCMNDTrc.setIcon(iconAnhCMNDtrc);
            JLHinhCMNDSau.setIcon(iconAnhCMNDSau);
        } else if (tblKhachThue.getValueAt(dongDangChon, 11) == null && tblKhachThue.getValueAt(dongDangChon, 12) == null) {
            String fileAnhcd = "./src/images/man-128px.png";
            ImageIcon iconAnhCD = new ImageIcon(new ImageIcon(fileAnhCD).getImage().getScaledInstance(140, 180, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDtrc = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDSau = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            JLHinhAnh.setIcon(iconAnhCD);
            JLHinhCMNDTrc.setIcon(iconAnhCMNDtrc);
            JLHinhCMNDSau.setIcon(iconAnhCMNDSau);
        } else if (tblKhachThue.getValueAt(dongDangChon, 10) == null) {
            String fileAnhcd = "./src/images/man-128px.png";
            ImageIcon iconAnhCD = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(140, 180, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDtrc = new ImageIcon(new ImageIcon(fileAnhCMNDtrc).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDSau = new ImageIcon(new ImageIcon(fileAnhCMNDSau).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            JLHinhAnh.setIcon(iconAnhCD);
            JLHinhCMNDTrc.setIcon(iconAnhCMNDtrc);
            JLHinhCMNDSau.setIcon(iconAnhCMNDSau);
        } else if (tblKhachThue.getValueAt(dongDangChon, 11) == null) {
            String fileAnhcd = "./src/images/man-128px.png";
            ImageIcon iconAnhCD = new ImageIcon(new ImageIcon(fileAnhCD).getImage().getScaledInstance(140, 180, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDtrc = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDSau = new ImageIcon(new ImageIcon(fileAnhCMNDSau).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            JLHinhAnh.setIcon(iconAnhCD);
            JLHinhCMNDTrc.setIcon(iconAnhCMNDtrc);
            JLHinhCMNDSau.setIcon(iconAnhCMNDSau);
        } else if (tblKhachThue.getValueAt(dongDangChon, 12) == null) {
            String fileAnhcd = "./src/images/man-128px.png";
            ImageIcon iconAnhCD = new ImageIcon(new ImageIcon(fileAnhCD).getImage().getScaledInstance(140, 180, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDtrc = new ImageIcon(new ImageIcon(fileAnhCMNDtrc).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDSau = new ImageIcon(new ImageIcon(fileAnhcd).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            JLHinhAnh.setIcon(iconAnhCD);
            JLHinhCMNDTrc.setIcon(iconAnhCMNDtrc);
            JLHinhCMNDSau.setIcon(iconAnhCMNDSau);
        } else {
            ImageIcon iconAnhCD = new ImageIcon(new ImageIcon(fileAnhCD).getImage().getScaledInstance(140, 180, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDtrc = new ImageIcon(new ImageIcon(fileAnhCMNDtrc).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            ImageIcon iconAnhCMNDSau = new ImageIcon(new ImageIcon(fileAnhCMNDSau).getImage().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            JLHinhAnh.setIcon(iconAnhCD);
            JLHinhCMNDTrc.setIcon(iconAnhCMNDtrc);
            JLHinhCMNDSau.setIcon(iconAnhCMNDSau);

        }
        btnSuaKH.setEnabled(true);
    }//GEN-LAST:event_tblKhachThueMouseClicked

    private void txtTimKiemHopDongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemHopDongKeyReleased
        // TODO add your handling code here:
        String tukhoa = txtTimKiemHopDong.getText();
        arrHD = BLL.BLLHoatDongThuePhong.FindByMaHopDongOrMaNguoiThue(tukhoa);
        BLL.BLLHoatDongThuePhong.DoVaoTable(arrHD, tblHopDongThue);
//        ArrayList<Phong> arrSP = BLL.BLLPhong.FindByName(tukhoa);
//        BLL.BLLPhong.doDataTable(arrSP, tblPhong);

    }//GEN-LAST:event_txtTimKiemHopDongKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        String Email = "^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,3}$";
        Pattern email = Pattern.compile(Email);
        Matcher match = email.matcher(txtEmail.getText());
        if (!match.matches()) {
            TBEmail.setText("Nhập chưa đúng định dạng Email!!");
        } else {
            TBEmail.setText(null);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtSDTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyTyped
        char a = evt.getKeyChar();
        if (!Character.isDigit(a)) {
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTKeyTyped

    private void txtCMNDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCMNDKeyTyped
        char a = evt.getKeyChar();
        if (!Character.isDigit(a)) {
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtCMNDKeyTyped

    private void txtCMNDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCMNDKeyReleased
        String CMND = txtCMND.getText();
        if (CMND.length() > 14) {
            JOptionPane.showMessageDialog(null, "Số CMND tối đa 14 số ");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtCMNDKeyReleased

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        String SDT = txtSDT.getText();
        if (SDT.length() > 10) {
            JOptionPane.showMessageDialog(null, "Số điện thoại tối đa 10 số ");
        }
    }//GEN-LAST:event_txtSDTKeyReleased

    private void panelDienNuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDienNuocMouseClicked
        // TODO add your handling code here:
        ArrayList<ChiSoDienNuoc> listKH = BLL.BLLChiSoDienNuoc.GetAll();
        BLL.BLLChiSoDienNuoc.DoVaoTable(listKH, tbDienNuocQLCS);
        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, "DienNuoc");
        JLQLPhong.setBackground(Color.decode("#33ccff"));
        JLQLLoaiPhong.setBackground(Color.decode("#33ccff"));
        JLQLKhachThue.setBackground(Color.decode("#33ccff"));
        JLQLHopDong.setBackground(Color.decode("#33ccff"));
        JLQLHoaDon.setBackground(Color.decode("#33ccff"));
        JLQLDichVu.setBackground(Color.decode("#33ccff"));
        JLQLDienNuoc.setBackground(Color.decode("#3333ff"));

        arrPT = BLLPhongTro.GetAll();
        BLLPhongTro.doComboBox(arrPT, cbMaPhongQLDN);
        BLLPhongTro.doComboBox(arrPT, cbPhongtbQLCSDN);
    }//GEN-LAST:event_panelDienNuocMouseClicked

    private void tbDienNuocQLCSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDienNuocQLCSMouseClicked
        // TODO add your handling code here:
        int indexRow = tbDienNuocQLCS.getSelectedRow();
        txtMaChiSoCSDN.setText(tbDienNuocQLCS.getValueAt(indexRow, 0).toString());
        BLL.BLLPhongTro.HienThiPhongTroCBB(cbMaPhongQLDN, tbDienNuocQLCS.getValueAt(indexRow, 1).toString());
        txtSoDienQLDN.setText(tbDienNuocQLCS.getValueAt(indexRow, 2).toString());
        txtSoNuocQLDN.setText(tbDienNuocQLCS.getValueAt(indexRow, 3).toString());
        dateNgayGhiDienNuoc.setDate(ChuyenDoi.LayNgayDate(tbDienNuocQLCS.getValueAt(indexRow, 4).toString()));
    }//GEN-LAST:event_tbDienNuocQLCSMouseClicked

    private void btnUpdCSDienNuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdCSDienNuocActionPerformed
        // TODO add your handling code here:
        if (cbMaPhongQLDN.getSelectedIndex() > 0) {
            if (BLLChiSoDienNuoc.FindMaChiSo(txtMaChiSoCSDN.getText()) != null) {
                if (txtMaChiSoCSDN.getText().equals("") || txtSoDienQLDN.getText().equals("") || txtSoNuocQLDN.getText().equals("") || !txtErrSoDienCSDN.getText().equals("   ") || !txtErrSoNuocCSDN.getText().equals("   ")) {
                    ThongBao.ThongBaoDonGian("Thông báo", "Vui lòng điền đầy đủ thông tin!");
                } else {

                    String maChiSo = txtMaChiSoCSDN.getText();
                    String maPhong = cbMaPhongQLDN.getSelectedItem() + "";
                    int soDien = Integer.parseInt(txtSoDienQLDN.getText());
                    int soNuoc = Integer.parseInt(txtSoNuocQLDN.getText());
                    Date ngayGhi = dateNgayGhiDienNuoc.getDate();
                    BLLChiSoDienNuoc.Update(new ChiSoDienNuoc(maPhong, maChiSo, soDien, soNuoc, ngayGhi));
                    int XacNhan = JOptionPane.showConfirmDialog(null, "Bạn có chắc sửa không?", "Thông báo xác nhận", JOptionPane.OK_CANCEL_OPTION);
                    if (XacNhan == JOptionPane.CANCEL_OPTION) {
                        return;
                    }
                    ThongBao.ThongBaoDonGian("Thông báo", "Đã sửa!");

                }
                arrCSDN = BLLChiSoDienNuoc.GetAll();
                BLLChiSoDienNuoc.DoVaoTable(arrCSDN, tbDienNuocQLCS);
            } else {
                ThongBao.ThongBaoDonGian("Thông báo", "Mã chỉ số không có trong dữ liệu!");
            }
        } else {
            ThongBao.ThongBaoDonGian("Thông báo", "Vui lòng điền đầy đủ thông tin!");
        }
    }//GEN-LAST:event_btnUpdCSDienNuocActionPerformed

    private void btnAddCSDNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCSDNActionPerformed
        // TODO add your handling code here:
        if (cbMaPhongQLDN.getSelectedIndex() > 0) {
            if (BLLChiSoDienNuoc.FindMaChiSo(txtMaChiSoCSDN.getText()) == null) {
                if (txtMaChiSoCSDN.getText().equals("") || txtSoDienQLDN.getText().equals("") || txtSoNuocQLDN.getText().equals("") || !txtErrSoDienCSDN.getText().equals("   ") || !txtErrSoNuocCSDN.getText().equals("   ")) {
                    ThongBao.ThongBaoDonGian("Thông báo", "Vui lòng điền thông tin hợp lệ!");
                } else {

                    String maChiSo = txtMaChiSoCSDN.getText();
                    String maPhong = cbMaPhongQLDN.getSelectedItem() + "";
                    int soDien = Integer.parseInt(txtSoDienQLDN.getText());
                    int soNuoc = Integer.parseInt(txtSoNuocQLDN.getText());
                    Date ngayGhi = dateNgayGhiDienNuoc.getDate();
                    BLLChiSoDienNuoc.Add(new ChiSoDienNuoc(maPhong, maChiSo, soDien, soNuoc, ngayGhi));
                    ThongBao.ThongBaoDonGian("Thông báo", "Đã thêm!");
                }
                arrCSDN = BLLChiSoDienNuoc.GetAll();
                BLLChiSoDienNuoc.DoVaoTable(arrCSDN, tbDienNuocQLCS);
            } else {
                ThongBao.ThongBaoDonGian("Thông báo", "Mã chỉ số đã có trong dữ liệu!");
            }
        } else {
            ThongBao.ThongBaoDonGian("Thông báo", "Vui lòng chọn phòng và điền đầy đủ thông tin!");
        }
    }//GEN-LAST:event_btnAddCSDNActionPerformed

    private void btnDelCSDNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelCSDNActionPerformed
        // TODO add your handling code here:        
        int dongDangChon = tbDienNuocQLCS.getSelectedRow();
        if (dongDangChon < 0) {
            ThongBao.ThongBaoDonGian("Thông Báo", "Bạn chưa chọn đối tượng cần xóa");
        } else {
            int XacNhan = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa không?", "Thông báo xác nhận", JOptionPane.OK_CANCEL_OPTION);
            if (XacNhan == JOptionPane.CANCEL_OPTION) {
                return;
            }
            //Lấy danh sách các sp  cần xóa
            try {
                int dongCanXoa[] = tbDienNuocQLCS.getSelectedRows();

//            MyCombobox tenPhong = (MyCombobox) cbMaPhongQLDN.getSelectedItem();
//            System.out.println(cbMaPhongQLDN.getSelectedItem());
//            ArrayList<Phong> arrP = BLL.BLLPhong.FindByName(tenPhong + "");
//
//            if (arrP.size() > 0) {
                for (int i = 0; i < dongCanXoa.length; i++) {
//                    String maPhong = arrP.get(0).getMaPhong();
//                    Date ngayGhi = ChuyenDoi.LayNgayDate(tbDienNuoc.getValueAt(dongCanXoa[i], 0).toString());
                    BLL.BLLChiSoDienNuoc.Delete(tbDienNuocQLCS.getValueAt(dongDangChon, 0).toString());
                }
//            }
                ThongBao.ThongBaoDonGian("Thông Báo", "Đã xóa");
            } catch (Exception e) {
                ThongBao.ThongBaoDonGian("Thông Báo", "Không xóa được");
            }
        }
//        arrHD =BLLHoatDongThuePhong.GetAll();
//        BLLHoatDongThuePhong.DoVaoTable(arrHD, tbHopDongThue);
        loadTB();
    }//GEN-LAST:event_btnDelCSDNActionPerformed

    private void cbPhongtbQLCSDNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbPhongtbQLCSDNItemStateChanged
        // TODO add your handling code here:
        loadTB();
    }//GEN-LAST:event_cbPhongtbQLCSDNItemStateChanged

    public void loadTB() {
        MyCombobox tenPhong = (MyCombobox) cbPhongtbQLCSDN.getSelectedItem();
//        System.out.println(cbPhongtbQLCSDN.getSelectedItem());
        if (cbPhongtbQLCSDN.getSelectedIndex() == 0) {
            arrCSDN = BLLChiSoDienNuoc.GetAll();
            BLL.BLLChiSoDienNuoc.DoVaoTable(arrCSDN, tbDienNuocQLCS);
        } else {

            ArrayList<ChiSoDienNuoc> arrCS = BLL.BLLChiSoDienNuoc.FindByMaPhong(tenPhong + "");
            if (arrCS.size() > 0) {
                BLL.BLLChiSoDienNuoc.DoVaoTable(arrCS, tbDienNuocQLCS);
            } else {
                DefaultTableModel tbModel = (DefaultTableModel) tbDienNuocQLCS.getModel();
                tbModel.setRowCount(0);
            }
        }
    }
    private void btnSetMaChiSoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetMaChiSoActionPerformed
        // TODO add your handling code here:
        if (cbMaPhongQLDN.getSelectedIndex() > 0 && dateNgayGhiDienNuoc.getDate() != null) {
            txtMaChiSoCSDN.setText(BLLChiSoDienNuoc.SoHoaDon(cbMaPhongQLDN.getSelectedItem() + "", dateNgayGhiDienNuoc.getDate()));
        } else {
            ThongBao.ThongBaoDonGian("Thông báo", "Vui lòng chọn phòng trước!");
        }
    }//GEN-LAST:event_btnSetMaChiSoActionPerformed

    private void btnResetFormCSDNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetFormCSDNActionPerformed
        // TODO add your handling code here:
        ResetFormQLDN();

    }//GEN-LAST:event_btnResetFormCSDNActionPerformed

    public void ResetFormQLDN() {
        txtMaChiSoCSDN.setText("Mã chỉ số");
        cbMaPhongQLDN.setSelectedIndex(0);
        txtSoDienQLDN.setText("");
        txtSoNuocQLDN.setText("");
        dateNgayGhiDienNuoc.setDate(new Date());
    }
    private void cbMaPhongQLDNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMaPhongQLDNItemStateChanged
        // TODO add your handling code here:
        if (cbMaPhongQLDN.getItemCount() > 0) {
            if (cbMaPhongQLDN.getSelectedIndex() > 0) {
                txtMaChiSoCSDN.setText(BLLChiSoDienNuoc.SoHoaDon(cbMaPhongQLDN.getSelectedItem() + "", dateNgayGhiDienNuoc.getDate()));
            }
        }
    }//GEN-LAST:event_cbMaPhongQLDNItemStateChanged

    public void loadTTChiSoByMaChiSo(JComboBox maChiSo, JLabel a, JLabel b, JLabel c) {
        if (maChiSo.getSelectedIndex() > 0) {
            ChiSoDienNuoc csdt = BLLChiSoDienNuoc.FindMaChiSo(maChiSo.getSelectedItem() + "");
            a.setText(csdt.getSoDien() + "");
            b.setText(csdt.getSoNuoc() + "");
            c.setText(ChuyenDoi.LayNgayString(csdt.getNgayGhi()) + "");
        } else {
            a.setText("");
            b.setText("");
            c.setText("");
        }
    }
    private void cbbPhongTroHDPTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbPhongTroHDPTItemStateChanged
        // TODO add your handling code here:

        if (cbbPhongTroHDPT.getSelectedIndex() > 0) {

            arrCSDN = BLLChiSoDienNuoc.FindByMaPhong(cbbPhongTroHDPT.getSelectedItem() + "");
            BLLChiSoDienNuoc.doComboBox(arrCSDN, cbbChiSoMoiHDPT);
            cbbChiSoMoiHDPT.setSelectedIndex(1);
            arrCSDN = BLLChiSoDienNuoc.FindByMaPhong(cbbPhongTroHDPT.getSelectedItem() + "");
            BLLChiSoDienNuoc.doComboBox(arrCSDN, cbbChiSoCuHDPT);
            cbbChiSoCuHDPT.setSelectedIndex(2);
            PhongTro phong = BLLPhongTro.FindMaPhong(cbbPhongTroHDPT.getSelectedItem() + "");
            LoaiPhong loaiPhongPT = BLLLoaiPhong.FindMaLoaiPhong(phong.getMaLoaiPhong() + "");
            txtGiaDienHDPT.setText(ChuyenDoi.SoString(loaiPhongPT.getGiaDien()));
            txtGiaNuocHDPT.setText(ChuyenDoi.SoString(loaiPhongPT.getGiaNuoc()));
            txtTienPhongHDPT.setText(ChuyenDoi.SoString(loaiPhongPT.getGiaPhong()));
            double tienDV = BLLChiTietDichVuPhong.SumTienByMaPhong(cbbPhongTroHDPT.getSelectedItem() + "");
            txtTienDichVuHDPT.setText(ChuyenDoi.SoString(tienDV) + "");
            HoatDongThuePhong hdtp = BLLHoatDongThuePhong.FindDaiDienPhong(cbbPhongTroHDPT.getSelectedItem() + "");
            if (BLLHoatDongThuePhong.FindDaiDienPhong(cbbPhongTroHDPT.getSelectedItem() + "") != null) {
                KhachThue kt = BLLKhachThue.FindByMaNguoiThue(BLLHoatDongThuePhong.FindDaiDienPhong(cbbPhongTroHDPT.getSelectedItem() + "").getMaNguoiThue());
                txtDaiDienPhongHDPT.setText(kt.getTenNguoiThue());
                txtMailLLHDPT.setText(kt.getEmail());
            } else {
                txtDaiDienPhongHDPT.setText("   ");
                txtMailLLHDPT.setText("   ");
            }

        } else {
            arrCSDN = BLLChiSoDienNuoc.GetAll();
            BLLChiSoDienNuoc.doComboBox(arrCSDN, cbbChiSoCuHDPT);
            arrCSDN = BLLChiSoDienNuoc.GetAll();
            BLLChiSoDienNuoc.doComboBox(arrCSDN, cbbChiSoMoiHDPT);
            txtGiaDienHDPT.setText("");
            txtGiaNuocHDPT.setText("");
            txtTienPhongHDPT.setText("");
            txtTienDichVuHDPT.setText("");
        }
    }//GEN-LAST:event_cbbPhongTroHDPTItemStateChanged
    public void loadTTDienNuocTieuThu() {
        if (cbbPhongTroHDPT.getSelectedIndex() > 0 && cbbChiSoMoiHDPT.getSelectedIndex() > 0 && cbbChiSoCuHDPT.getSelectedIndex() > 0) {
            txtMaHoaDonPT.setText(BLLHoaDon.SoHoaDon(cbbPhongTroHDPT.getSelectedItem() + "", ChuyenDoi.LayNgayDate(txtNgayGhiCuHD.getText()), ChuyenDoi.LayNgayDate(txtNgayGhiMoiHD.getText())));
            txtSoDienTieuThuHD.setText((Integer.parseInt(txtSoDienMoiHD.getText()) - Integer.parseInt(txtSoDienCuHD.getText())) + "");
            txtSoNuocTieuThuHD.setText((Integer.parseInt(txtSoNuocMoiHD.getText()) - Integer.parseInt(txtSoNuocCuHD.getText())) + "");
            double tienDien = Integer.parseInt(txtSoDienTieuThuHD.getText()) * (ChuyenDoi.SoDouble(txtGiaDienHDPT.getText()));
            double tienNuoc = Integer.parseInt(txtSoNuocTieuThuHD.getText()) * (ChuyenDoi.SoDouble(txtGiaNuocHDPT.getText()));
            double tienPhong = ChuyenDoi.SoDouble(txtTienPhongHDPT.getText());
            double tienDV = ChuyenDoi.SoDouble(txtTienDichVuHDPT.getText());
            txtTienDienHDPT.setText(ChuyenDoi.SoString(tienDien) + "");
            txtTienNuocHDPT.setText(ChuyenDoi.SoString(tienNuoc) + "");
            double tongTien = tienDien + tienNuoc + tienPhong + tienDV;
            txtTongTienHDPT.setText(ChuyenDoi.SoString(tongTien) + "");
        } else {
            txtSoDienTieuThuHD.setText("   ");
            txtSoDienTieuThuHD.setText("  ");
            txtSoNuocTieuThuHD.setText("  ");
            txtTienDienHDPT.setText("  ");
            txtTienNuocHDPT.setText("  ");
            txtTongTienHDPT.setText("  ");
        }
    }
    private void cbbChiSoMoiHDPTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbChiSoMoiHDPTItemStateChanged
        // TODO add your handling code here:
        if (cbbPhongTroHDPT.getSelectedIndex() > 0) {
            if (cbbChiSoMoiHDPT.getSelectedIndex() > 0) {
                arrCSDN = BLLChiSoDienNuoc.FindChiSoCuByMaPhongAndChiSoMoi(cbbPhongTroHDPT.getSelectedItem() + "", cbbChiSoMoiHDPT.getSelectedItem() + "");
                BLLChiSoDienNuoc.doComboBox(arrCSDN, cbbChiSoCuHDPT);
                loadTTChiSoByMaChiSo(cbbChiSoMoiHDPT, txtSoDienMoiHD, txtSoNuocMoiHD, txtNgayGhiMoiHD);
                loadTTDienNuocTieuThu();
            } else {
                loadTTChiSoByMaChiSo(cbbChiSoMoiHDPT, txtSoDienMoiHD, txtSoNuocMoiHD, txtNgayGhiMoiHD);
                arrCSDN = BLLChiSoDienNuoc.FindByMaPhong(cbbPhongTroHDPT.getSelectedItem() + "");
                BLLChiSoDienNuoc.doComboBox(arrCSDN, cbbChiSoCuHDPT);
            }
        } else {
            arrCSDN = BLLChiSoDienNuoc.GetAll();
            BLLChiSoDienNuoc.doComboBox(arrCSDN, cbbChiSoCuHDPT);
        }
        loadTTChiSoByMaChiSo(cbbChiSoMoiHDPT, txtSoDienMoiHD, txtSoNuocMoiHD, txtNgayGhiMoiHD);
        loadTTDienNuocTieuThu();
    }//GEN-LAST:event_cbbChiSoMoiHDPTItemStateChanged

    private void cbbChiSoCuHDPTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbChiSoCuHDPTItemStateChanged
        // TODO add your handling code here:
        loadTTChiSoByMaChiSo(cbbChiSoCuHDPT, txtSoDienCuHD, txtSoNuocCuHD, txtNgayGhiCuHD);
        loadTTDienNuocTieuThu();
    }//GEN-LAST:event_cbbChiSoCuHDPTItemStateChanged

    private void jTblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblHoaDonMouseClicked
        // TODO add your handling code here:
        btnUpdHoaDonHDPT.setEnabled(true);
        int indexRow = jTblHoaDon.getSelectedRow();
        BLLPhongTro.HienThiPhongTroCBB(cbbPhongTroHDPT, jTblHoaDon.getValueAt(indexRow, 1).toString());
        BLLChiSoDienNuoc.HienThiHoaDonPhongTroCBB(cbbChiSoMoiHDPT, jTblHoaDon.getValueAt(indexRow, 3).toString());
        BLLChiSoDienNuoc.HienThiHoaDonPhongTroCBB(cbbChiSoCuHDPT, jTblHoaDon.getValueAt(indexRow, 4).toString());
        txtTienDichVuHDPT.setText(jTblHoaDon.getValueAt(indexRow, 8).toString());
        txtMaHoaDonPT.setText(jTblHoaDon.getValueAt(indexRow, 0).toString());
        jDateChooser2.setDate(ChuyenDoi.LayNgayDate(jTblHoaDon.getValueAt(indexRow, 2).toString()));
        Date thang = ChuyenDoi.LayNgayDate(jTblHoaDon.getValueAt(indexRow, 2).toString());

        System.out.println(thang.getMonth());
        txtTienDichVuHDPT.setText(ChuyenDoi.SoString(ChuyenDoi.SoDouble(jTblHoaDon.getValueAt(indexRow, 8).toString())));
        txtTienPhongHDPT.setText(ChuyenDoi.SoString(ChuyenDoi.SoDouble(jTblHoaDon.getValueAt(indexRow, 5).toString())));
        txtTongTienHDPT.setText(ChuyenDoi.SoString(ChuyenDoi.SoDouble(jTblHoaDon.getValueAt(indexRow, 9).toString())));
        txtGiaDienHDPT.setText(ChuyenDoi.SoString((ChuyenDoi.SoDouble(jTblHoaDon.getValueAt(indexRow, 6).toString()) / ChuyenDoi.SoDouble(txtSoDienTieuThuHD.getText()))));
        txtGiaNuocHDPT.setText(ChuyenDoi.SoString((ChuyenDoi.SoDouble(jTblHoaDon.getValueAt(indexRow, 7).toString()) / ChuyenDoi.SoDouble(txtSoNuocTieuThuHD.getText()))));
    }//GEN-LAST:event_jTblHoaDonMouseClicked

    private void txtSoDienQLDNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienQLDNKeyReleased
        // TODO add your handling code here:
        try {
            Integer.parseInt(txtSoDienQLDN.getText());
            txtErrSoDienCSDN.setText("   ");
        } catch (Exception e) {
            txtErrSoDienCSDN.setText("Cần điền số!");
//            ThongBao.ThongBaoDonGian("Thông báo", ");
        }
    }//GEN-LAST:event_txtSoDienQLDNKeyReleased

    private void txtSoNuocQLDNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoNuocQLDNKeyReleased
        // TODO add your handling code here:
        try {
            Integer.parseInt(txtSoNuocQLDN.getText());
            txtErrSoNuocCSDN.setText("   ");
        } catch (Exception e) {
            txtErrSoNuocCSDN.setText("Cần điền số!");
//            ThongBao.ThongBaoDonGian("Thông báo", "Cần điền số!");
        }
    }//GEN-LAST:event_txtSoNuocQLDNKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String maHoaDon = txtMaHoaDonPT.getText();
        if (!txtDaiDienPhongHDPT.getText().equals("   ")) {
            if (maHoaDon != null || jDateChooser2.getDate() == null) {
                try {
                    String maPhong = cbbPhongTroHDPT.getSelectedItem() + "";
                    String chiSoMoi = cbbChiSoMoiHDPT.getSelectedItem() + "";
                    String chiSoCu = cbbChiSoCuHDPT.getSelectedItem() + "";
                    Date ngayGhi = jDateChooser2.getDate();
                    int tienDien = (int) ChuyenDoi.SoDouble(txtTienDienHDPT.getText());
                    int tienPhong = (int) ChuyenDoi.SoDouble(txtTienPhongHDPT.getText());
                    int tienNuoc = (int) ChuyenDoi.SoDouble(txtTienNuocHDPT.getText());
                    int tienDV = (int) ChuyenDoi.SoDouble(txtTienDichVuHDPT.getText());
                    int tongTien = (int) ChuyenDoi.SoDouble(txtTongTienHDPT.getText());
                    String ghiChu = null;
                    BLLHoaDon.Add(new HoaDonPhongTro(maHoaDon, maPhong, ngayGhi, chiSoMoi, chiSoCu, tienPhong, tienDien, tienNuoc, tienDV, tongTien, ghiChu));
                    ThongBao.ThongBaoDonGian("Thông báo", "Đã thêm");
                    LamMoiHoaDon();
                } catch (Exception e) {

                    ThongBao.ThongBaoDonGian("Thông báo", "Hóa đơn đã tồn tại hoặc sai thông tin, vui lòng kiểm tra lại!!");
                }
            } else {
                ThongBao.ThongBaoDonGian("Thông báo", "Chưa đủ thông tin!!");
            }
        } else {
            ThongBao.ThongBaoDonGian("Thông báo", "Phòng không có đại diện hoặc đang trống!!");
        }
        arrHDPT = BLL.BLLHoaDon.GetAll();
        BLL.BLLHoaDon.DoVaoTable(arrHDPT, jTblHoaDon);

    }//GEN-LAST:event_jButton1ActionPerformed

    public static void XuatPhieuThu(String MaHoaDon) {
        try {
            Hashtable map = new Hashtable();
            JasperReport jasper = JasperCompileManager.compileReport("src/GUI/rptPhieuThu.jrxml");

            // put parameter
            map.put("MaHoaDon", MaHoaDon);

//            Connection con = (Connection) helper.sqlHelper.getConnection();
            JasperPrint printer = JasperFillManager.fillReport(jasper, map, helper.sqlHelper.getConnection());
            JasperViewer.viewReport(printer, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void btnUpdHoaDonHDPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdHoaDonHDPTActionPerformed
        // TODO add your handling code here:
        String maHoaDon = txtMaHoaDonPT.getText();
        if (maHoaDon != null || jDateChooser2.getDate() == null) {
            try {
                String maPhong = cbbPhongTroHDPT.getSelectedItem() + "";
                String chiSoMoi = cbbChiSoMoiHDPT.getSelectedItem() + "";
                String chiSoCu = cbbChiSoCuHDPT.getSelectedItem() + "";
                Date ngayGhi = jDateChooser2.getDate();
                int tienDien = (int) ChuyenDoi.SoDouble(txtTienDienHDPT.getText());
                int tienPhong = (int) ChuyenDoi.SoDouble(txtTienPhongHDPT.getText());
                int tienNuoc = (int) ChuyenDoi.SoDouble(txtTienNuocHDPT.getText());
                int tienDV = (int) ChuyenDoi.SoDouble(txtTienDichVuHDPT.getText());
                int tongTien = (int) ChuyenDoi.SoDouble(txtTongTienHDPT.getText());
                String ghiChu = null;
                BLLHoaDon.Update(new HoaDonPhongTro(maHoaDon, maPhong, ngayGhi, chiSoMoi, chiSoCu, tienPhong, tienDien, tienNuoc, tienDV, tongTien, ghiChu));
                ThongBao.ThongBaoDonGian("Thông báo", "Đã sửa!");
                LamMoiHoaDon();
            } catch (Exception e) {

                ThongBao.ThongBaoDonGian("Thông báo", "Hóa đơn không tồn tại hoặc sai thông tin, vui lòng kiểm tra lại!!");
            }
        } else {
            ThongBao.ThongBaoDonGian("Thông báo", "Chưa đủ thông tin!!");
        }
        arrHDPT = BLL.BLLHoaDon.GetAll();
        BLL.BLLHoaDon.DoVaoTable(arrHDPT, jTblHoaDon);

    }//GEN-LAST:event_btnUpdHoaDonHDPTActionPerformed

    private void btnLamMoiHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiHoaDonActionPerformed

        LamMoiHoaDon();

    }//GEN-LAST:event_btnLamMoiHoaDonActionPerformed

    public void LamMoiHoaDon() {
        // TODO add your handling code here:
        txtDaiDienPhongHDPT.setText("");
        txtMailLLHDPT.setText("");
        btnUpdHoaDonHDPT.setEnabled(false);
        txtMaHoaDonPT.setText("Mã hóa đơn");
        cbbPhongTroHDPT.setSelectedIndex(0);
        jDateChooser2.setDate(null);
    }

    private void txtGiaDichVuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaDichVuKeyReleased
        // TODO add your handling code here:
        try {
            Integer.parseInt(txtGiaDichVu.getText());
            txtErrGiaDichVu.setText("   ");
        } catch (Exception e) {
            txtErrGiaDichVu.setText("Cần ghi số");
        }
    }//GEN-LAST:event_txtGiaDichVuKeyReleased

    private void btnXoaHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHopDongActionPerformed
        // TODO add your handling code here:
        int dongDangChon = tblHopDongThue.getSelectedRow();
        if (dongDangChon < 0) {
            ThongBao.ThongBaoDonGian("Thông Báo", "Bạn chưa chọn đối tượng cần xóa");
        } else {
            int XacNhan = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa không?", "Thông báo xác nhận", JOptionPane.OK_CANCEL_OPTION);
            if (XacNhan == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        //Lấy danh sách các sp  cần xóa
        try {
            int dongCanXoa[] = tblHopDongThue.getSelectedRows();

//            MyCombobox tenPhong = (MyCombobox) cbMaPhongQLDN.getSelectedItem();
//            System.out.println(cbMaPhongQLDN.getSelectedItem());
//            ArrayList<Phong> arrP = BLL.BLLPhong.FindByName(tenPhong + "");
//
//            if (arrP.size() > 0) {
            for (int i = 0; i < dongCanXoa.length; i++) {
//                    String maPhong = arrP.get(0).getMaPhong();
//                    Date ngayGhi = ChuyenDoi.LayNgayDate(tbDienNuoc.getValueAt(dongCanXoa[i], 0).toString());
                BLL.BLLHoatDongThuePhong.Delete(tblHopDongThue.getValueAt(dongDangChon, 0).toString(), tblHopDongThue.getValueAt(dongDangChon, 1).toString(), tblHopDongThue.getValueAt(dongDangChon, 2).toString());
            }
//            }
            ThongBao.ThongBaoDonGian("Thông Báo", "Đã xóa");
        } catch (Exception e) {
            ThongBao.ThongBaoDonGian("Thông Báo", "Chưa xóa");
        }
        LamMoi();
//        loadTB();

        arrHD = BLLHoatDongThuePhong.GetAll();
        BLLHoatDongThuePhong.DoVaoTable(arrHD, tblHopDongThue);

    }//GEN-LAST:event_btnXoaHopDongActionPerformed

    private void btnResetKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetKHActionPerformed
        // TODO add your handling code here:
        LamMoiKhachThue();
        JOptionPane.showMessageDialog(this, "Làm Mới Thành Công !!");
    }//GEN-LAST:event_btnResetKHActionPerformed

    private void cbbJtbHDPTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbJtbHDPTItemStateChanged
        // TODO add your handling code here:

        if (cbbJtbHDPT.getSelectedIndex() > 0) {
            arrHDPT = BLL.BLLHoaDon.FindByMaPhong(cbbJtbHDPT.getSelectedItem() + "");
            BLLHoaDon.DoVaoTable(arrHDPT, jTblHoaDon);
        } else {
            arrHDPT = BLL.BLLHoaDon.GetAll();
            BLLHoaDon.DoVaoTable(arrHDPT, jTblHoaDon);
        }


    }//GEN-LAST:event_cbbJtbHDPTItemStateChanged

    private void cbbJtbHopDongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbJtbHopDongItemStateChanged
        // TODO add your handling code here:
        if (cbbJtbHopDong.getSelectedIndex() > 0) {
            arrHD = BLL.BLLHoatDongThuePhong.FindByMaHopDong(cbbJtbHopDong.getSelectedItem() + "");
            BLLHoatDongThuePhong.DoVaoTable(arrHD, tblHopDongThue);
        } else {
            arrHD = BLL.BLLHoatDongThuePhong.GetAll();
            BLLHoatDongThuePhong.DoVaoTable(arrHD, tblHopDongThue);
        }
    }//GEN-LAST:event_cbbJtbHopDongItemStateChanged

    private void cbbJtbKhachThueItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbJtbKhachThueItemStateChanged
        // TODO add your handling code here:
        if (cbbJtbKhachThue.getSelectedIndex() > 0) {
            arrKT = BLL.BLLKhachThue.FindByMaKhachThue(cbbJtbKhachThue.getSelectedItem() + "");
            BLL.BLLKhachThue.DoVaoTable(arrKT, tblKhachThue);
        } else {
            arrKT = BLL.BLLKhachThue.GetAll();
            BLL.BLLKhachThue.DoVaoTable(arrKT, tblKhachThue);
        }
    }//GEN-LAST:event_cbbJtbKhachThueItemStateChanged

    private void txtSearchHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchHoaDonKeyReleased
        // TODO add your handling code here:
        cbbJtbHDPT.setSelectedIndex(0);
        String tuKhoa = txtSearchHoaDon.getText();
        if (tuKhoa != null) {
            arrHDPT = BLLHoaDon.FindByMaHoaDon(tuKhoa);
            BLLHoaDon.DoVaoTable(arrHDPT, jTblHoaDon);
        }
    }//GEN-LAST:event_txtSearchHoaDonKeyReleased

    private void btnXuatHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatHoaDonActionPerformed
        // TODO add your handling code here:
        int dongDangChon = jTblHoaDon.getSelectedRow();
        if (dongDangChon < 0) {
            ThongBao.ThongBaoDonGian("Thông Báo", "Bạn chưa chọn hóa đơn cần xuất!");
            return;
        } else {
            int XacNhan = JOptionPane.showConfirmDialog(null, "Bạn có chắc xuất hóa đơn không?", "Thông báo xác nhận", JOptionPane.OK_CANCEL_OPTION);
            if (XacNhan == JOptionPane.CANCEL_OPTION) {
                return;
            }
//        }
            //Lấy danh sách các sp  cần xóa
            try {
                int dongCanXoa[] = jTblHoaDon.getSelectedRows();

//            MyCombobox tenPhong = (MyCombobox) cbPhong.getSelectedItem();
//            System.out.println(cbPhong.getSelectedItem());
//            ArrayList<Phong> arrP = BLL.BLLPhong.FindByName(tenPhong + "");
//        arrDV=BLLDichVu.
//            if (arrP.size() > 0) {
                for (int i = 0; i < dongCanXoa.length; i++) {
//                    String maPhong = arrP.get(0).getMaPhong();
//                    Date ngayGhi = ChuyenDoi.LayNgayDate(tblDIchVu.getValueAt(dongCanXoa[i], 0).toString());
//                    BLL.BLLDichVu.Delete(tblDIchVu.getValueAt(dongCanXoa[i], 0).toString());
                    XuatPhieuThu(jTblHoaDon.getValueAt(i, 0).toString());
                }
//            }
//                ThongBao.ThongBaoDonGian("Thông Báo", "Đã xóa");
            } catch (Exception e) {
                ThongBao.ThongBaoDonGian("Thông Báo", "Không xuất được!!");
            }
//        DTO.frmDichVu dv = new DTO.frmDichVu(txtMaDichVu.getText(), txtTenDichVu.getText(),
//                Float.parseFloat(txtGia.getText()), (String) cbbDonViTinh.getSelectedItem(),
//                true);
//        DAL.DALDichVu.Delete(dv);
//            ArrayList<DTO.DichVu> arr = BLL.BLLDichVu.GetAll();
//            BLL.BLLDichVu.DoVaoTable(arr, tblDIchVu);
//        LamMoiFormDV();
//        helper.ThongBao.ThongBaoDonGian("thông báo", "Xóa thành công !!");
        }
    }//GEN-LAST:event_btnXuatHoaDonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new themPhong().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(TrangChu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JLabel JLQLDichVu;
    private javax.swing.JLabel JLQLDienNuoc;
    private javax.swing.JLabel JLQLHoaDon;
    private javax.swing.JLabel JLQLHopDong;
    private javax.swing.JLabel JLQLKhachThue;
    private javax.swing.JLabel JLQLLoaiPhong;
    private javax.swing.JLabel JLQLPhong;
    private javax.swing.JLabel LabelDongho;
    private javax.swing.JLabel TBEmail;
    private javax.swing.JButton btResetNgayTraHD;
    private javax.swing.JButton btnAddCSDN;
    private javax.swing.JButton btnDelCSDN;
    private javax.swing.JButton btnHInhAnhCD;
    private javax.swing.JButton btnHInhCMNDSau;
    private javax.swing.JButton btnHInhCMNDTrc;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoi1;
    private javax.swing.JButton btnLamMoi2;
    private javax.swing.JButton btnLamMoiHoaDon;
    private javax.swing.JButton btnResetFormCSDN;
    private javax.swing.JButton btnResetKH;
    private javax.swing.JButton btnSetMaChiSo;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnSuaKH;
    private javax.swing.JButton btnTao;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnUpdCSDienNuoc;
    private javax.swing.JButton btnUpdHoaDonHDPT;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa1;
    private javax.swing.JButton btnXoaKH;
    private javax.swing.JButton btnXuatHoaDon;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbMaPhongQLDN;
    private javax.swing.JComboBox<String> cbPhongtbQLCSDN;
    private javax.swing.JComboBox<String> cbbChiSoCuHDPT;
    private javax.swing.JComboBox<String> cbbChiSoMoiHDPT;
    private javax.swing.JComboBox<String> cbbJtbHDPT;
    private javax.swing.JComboBox<String> cbbJtbHopDong;
    private javax.swing.JComboBox<String> cbbJtbKhachThue;
    private javax.swing.JComboBox<String> cbbMaKhachHang;
    private javax.swing.JComboBox<String> cbbPhong;
    private javax.swing.JComboBox<String> cbbPhongTroHDPT;
    private com.toedter.calendar.JDateChooser dateNgayGhiDienNuoc;
    private com.toedter.calendar.JDateChooser dateNgayKetThuc;
    private com.toedter.calendar.JDateChooser dateNgayKy;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
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
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
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
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
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
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTblHoaDon;
    private javax.swing.JLabel lblGiaNhap;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblMaSP2;
    private javax.swing.JLabel lblMaSP3;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JPanel panelDichVu;
    private javax.swing.JPanel panelDienNuoc;
    private javax.swing.JPanel panelDsPhongTro;
    private javax.swing.JPanel panelFormDichVu;
    private javax.swing.JPanel panelFormDienNuoc;
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
    private javax.swing.JTable tbDienNuocQLCS;
    private javax.swing.JTable tblDIchVu;
    private javax.swing.JTable tblHopDongThue;
    private javax.swing.JTable tblKhachThue;
    private javax.swing.JTable tblLoaiPhong;
    public static javax.swing.JTable tblPhongTro;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JLabel txtCMNDHopDong;
    private javax.swing.JLabel txtDaiDienPhongHDPT;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JLabel txtDiaChiHopDong;
    private javax.swing.JLabel txtDienTich;
    private javax.swing.JLabel txtDoanhThu;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JLabel txtErrGiaDichVu;
    private javax.swing.JLabel txtErrSoDienCSDN;
    private javax.swing.JLabel txtErrSoNuocCSDN;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGiaDichVu;
    private javax.swing.JTextField txtGiaDien;
    private javax.swing.JLabel txtGiaDienHDPT;
    private javax.swing.JTextField txtGiaNuoc;
    private javax.swing.JLabel txtGiaNuocHDPT;
    private javax.swing.JLabel txtGiaPhong;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JLabel txtMaChiSoCSDN;
    private javax.swing.JTextField txtMaDichVu;
    private javax.swing.JLabel txtMaHoaDonPT;
    private javax.swing.JTextField txtMaHopDong;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaLoaiPhong;
    private javax.swing.JLabel txtMailLLHDPT;
    private javax.swing.JLabel txtNgayGhiCuHD;
    private javax.swing.JLabel txtNgayGhiMoiHD;
    public static javax.swing.JLabel txtPhongDaThue;
    public static javax.swing.JLabel txtPhongTrong;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JLabel txtSDTHopDong;
    private javax.swing.JTextField txtSearchHoaDon;
    private javax.swing.JLabel txtSoDienCuHD;
    private javax.swing.JLabel txtSoDienMoiHD;
    private javax.swing.JTextField txtSoDienQLDN;
    private javax.swing.JLabel txtSoDienTieuThuHD;
    public static javax.swing.JLabel txtSoLuongNguoiThue;
    private javax.swing.JLabel txtSoNuocCuHD;
    private javax.swing.JLabel txtSoNuocMoiHD;
    private javax.swing.JTextField txtSoNuocQLDN;
    private javax.swing.JLabel txtSoNuocTieuThuHD;
    private javax.swing.JTextField txtTenDichVu;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenLoaiPhong;
    private javax.swing.JLabel txtTenNguoiThueHD;
    private javax.swing.JLabel txtThangHienTai;
    private javax.swing.JLabel txtTienDichVuHDPT;
    private javax.swing.JLabel txtTienDienHDPT;
    private javax.swing.JLabel txtTienNuocHDPT;
    private javax.swing.JLabel txtTienPhongHDPT;
    private javax.swing.JTextField txtTimKiemHopDong;
    private javax.swing.JTextField txtTimKiemKH;
    private javax.swing.JLabel txtTongTienHDPT;
    // End of variables declaration//GEN-END:variables
}
