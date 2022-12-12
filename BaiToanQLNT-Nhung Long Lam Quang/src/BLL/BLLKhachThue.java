/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.KhachThue;
import GUI.jdlKhachThue;
import helper.MyCombobox;
import helper.ThongBao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS VIVOBOOK
 */
public class BLLKhachThue {
    //Hàm đổ dữ liệu

    public static ArrayList<KhachThue> GetAll() {
        //Lấy tất cả dữ liệu từ SQL
        ResultSet rs = DAL.DALKhachThue.GetAllData();
        ArrayList<KhachThue> arr = new ArrayList<>();

        try {
            while (rs.next()) {
                KhachThue kh = new KhachThue();
                kh.setMaNguoiThue(rs.getString("MaNguoiThue"));
                kh.setTenNguoiThue(rs.getString("TenNguoiThue"));
                kh.setCMND(rs.getString("CMND"));
                kh.setSDT(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setNgaySinh(rs.getString("NgaySinh"));
                kh.setNgayTaoDT(rs.getString("NgayTaoDT"));
                kh.setGioiTinh(rs.getByte("GioiTinh") == 0 ? false : true);
                kh.setTrangThai(rs.getByte("TrangThai") == 0 ? false : true);
                kh.setAnhCD(rs.getString("AnhChanDung"));
                kh.setAnhCCTruoc(rs.getString("AnhCCTruoc"));
                kh.setAnhCCSau(rs.getString("AnhCCSau"));
                arr.add(kh);
//                System.out.println(nv.getMaNhanVien());
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu" + ex.getMessage());
        }
        return arr;

    }

    public static void DoVaoTable(ArrayList<KhachThue> arr, JTable tblKhachThue) {
        DefaultTableModel tbModel = (DefaultTableModel) tblKhachThue.getModel();
        tbModel.setRowCount(0);

        tbModel.setRowCount(0);

        for (KhachThue kh : arr) {
            Object obj[] = new Object[13];

            obj[0] = kh.getMaNguoiThue();
            obj[1] = kh.getTenNguoiThue();
            obj[2] = kh.getCMND();
            obj[3] = kh.getSDT();
            obj[4] = kh.getEmail();
            obj[5] = kh.getDiaChi();
            obj[6] = kh.getNgaySinh();
            obj[7] = kh.getNgayTaoDT();
            obj[8] = kh.isGioiTinh()== false ? "Nữ" : "Nam";
            obj[9] = kh.isTrangThai()== true ? "Đang Thuê" : "Đã Trả";
            obj[10]=kh.getAnhCD();
            obj[11]=kh.getAnhCCTruoc();
            obj[12]=kh.getAnhCCSau();
            
            tbModel.addRow(obj);

        }

//        tblNhanVien.setModel(tbModel);
//        
    }

    public static ArrayList<KhachThue> FindByName(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALKhachThue.FindByName(TuKhoa);

        ArrayList<KhachThue> arr = new ArrayList<>();
        try {
            while (rs.next()) {
                KhachThue kh = new KhachThue();
                kh.setMaNguoiThue(rs.getString("MaNguoiThue"));
                kh.setTenNguoiThue(rs.getString("TenNguoiThue"));
                kh.setCMND(rs.getString("CMND"));
                kh.setSDT(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setNgaySinh(rs.getString("NgaySinh"));
                kh.setNgayTaoDT(rs.getString("NgayTaoDT"));
                kh.setGioiTinh(rs.getByte("GioiTinh") == 0 ? false : true);
                kh.setTrangThai(rs.getByte("TrangThai") == 0 ? false : true);
                kh.setAnhCD(rs.getString("AnhChanDung"));
                kh.setAnhCCTruoc(rs.getString("AnhCCTruoc"));
                kh.setAnhCCSau(rs.getString("AnhCCSau"));
                arr.add(kh);
//                System.out.println(nv.getMaNhanVien());
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu" + ex.getMessage());
        }
        return arr;
    }

    public static KhachThue FindByMaNguoiThue(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALKhachThue.FindByMaNguoiThue(TuKhoa);
        ArrayList<KhachThue> arrKT = new ArrayList<>();
        KhachThue kh = new KhachThue();
        try {
            while (rs.next()) {
                
                kh.setMaNguoiThue(rs.getString("MaNguoiThue"));
                kh.setTenNguoiThue(rs.getString("TenNguoiThue"));
                kh.setCMND(rs.getString("CMND"));
                kh.setSDT(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setNgaySinh(rs.getString("NgaySinh"));
                kh.setNgayTaoDT(rs.getString("NgayTaoDT"));
                kh.setGioiTinh(rs.getByte("GioiTinh") == 0 ? false : true);
                kh.setTrangThai(rs.getByte("TrangThai") == 0 ? false : true);
                kh.setAnhCD(rs.getString("AnhChanDung"));
                kh.setAnhCCTruoc(rs.getString("AnhCCTruoc"));
                kh.setAnhCCSau(rs.getString("AnhCCSau"));
                return kh;
                
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu" + ex.getMessage());
        }
        return kh;
        
    }
    
        
    public static KhachThue FindByMaOrTen(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALKhachThue.FindByNameOrID(TuKhoa);

        KhachThue kh = new KhachThue();
        try {
            while (rs.next()) {
                kh.setMaNguoiThue(rs.getString("MaNguoiThue"));
                kh.setTenNguoiThue(rs.getString("TenNguoiThue"));
                kh.setCMND(rs.getString("CMND"));
                kh.setSDT(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setNgaySinh(rs.getString("NgaySinh"));
                kh.setNgayTaoDT(rs.getString("NgayTaoDT"));
               kh.setGioiTinh(rs.getByte("GioiTinh") == 0 ? false : true);
                kh.setTrangThai(rs.getByte("TrangThai") == 0 ? false : true);
                kh.setAnhCD(rs.getString("AnhChanDung"));
                kh.setAnhCCTruoc(rs.getString("AnhCCTruoc"));
                kh.setAnhCCSau(rs.getString("AnhCCSau"));
                return kh;
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu" + ex.getMessage());
        }
        return kh;
    }
    public static ArrayList<KhachThue> FindCheckDaiDien(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALKhachThue.FindCheckDaiDien(TuKhoa);

        ArrayList<KhachThue> arr = new ArrayList<>();
        try {
            while (rs.next()) {
                KhachThue kh = new KhachThue();
                kh.setMaNguoiThue(rs.getString("MaNguoiThue"));
                kh.setTenNguoiThue(rs.getString("TenNguoiThue"));
                kh.setCMND(rs.getString("CMND"));
                kh.setSDT(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setNgaySinh(rs.getString("NgaySinh"));
                kh.setNgayTaoDT(rs.getString("NgayTaoDT"));
                kh.setGioiTinh(rs.getByte("GioiTinh") == 0 ? false : true);
                kh.setTrangThai(rs.getByte("TrangThai") == 0 ? false : true);
                kh.setAnhCD(rs.getString("AnhChanDung"));
                kh.setAnhCCTruoc(rs.getString("AnhCCTruoc"));
                kh.setAnhCCSau(rs.getString("AnhCCSau"));
                arr.add(kh);
//                System.out.println(nv.getMaNhanVien());
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu" + ex.getMessage());
        }
        return arr;
    }
    
   public static ArrayList<KhachThue> FindByMangMaOrTen(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALKhachThue.FindByMaKhachThue(TuKhoa);

        ArrayList<KhachThue> arr = new ArrayList<>();
        try {
            while (rs.next()) {
                KhachThue kh = new KhachThue();
                kh.setMaNguoiThue(rs.getString("MaNguoiThue"));
                kh.setTenNguoiThue(rs.getString("TenNguoiThue"));
                kh.setCMND(rs.getString("CMND"));
                kh.setSDT(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setNgaySinh(rs.getString("NgaySinh"));
                kh.setNgayTaoDT(rs.getString("NgayTaoDT"));
                kh.setGioiTinh(rs.getByte("GioiTinh") == 0 ? false : true);
                kh.setTrangThai(rs.getByte("TrangThai") == 0 ? false : true);
                kh.setAnhCD(rs.getString("AnhChanDung"));
                kh.setAnhCCTruoc(rs.getString("AnhCCTruoc"));
                kh.setAnhCCSau(rs.getString("AnhCCSau"));
                arr.add(kh);
//                System.out.println(nv.getMaNhanVien());
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu" + ex.getMessage());
        }
        return arr;
    }

    
    public static KhachThue FindByNameAndAddr(String TuKhoa, String TuKhoa1) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALKhachThue.FindByNameAndAddr(TuKhoa, TuKhoa1);

        KhachThue kh = new KhachThue();
        try {
            while (rs.next()) {
                kh.setMaNguoiThue(rs.getString("MaNguoiThue"));
                kh.setTenNguoiThue(rs.getString("TenNguoiThue"));
                kh.setCMND(rs.getString("CMND"));
                kh.setSDT(rs.getString("SDT"));
                 kh.setEmail(rs.getString("Email"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setNgaySinh(rs.getString("NgaySinh"));
                kh.setNgayTaoDT(rs.getString("NgayTaoDT"));
                kh.setGioiTinh(rs.getByte("GioiTinh") == 0 ? false : true);
                kh.setTrangThai(rs.getByte("TrangThai") == 0 ? false : true);
                kh.setAnhCD(rs.getString("AnhChanDung"));
                kh.setAnhCCTruoc(rs.getString("AnhCCTruoc"));
                kh.setAnhCCSau(rs.getString("AnhCCSau"));
                return kh;
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu" + ex.getMessage());
        }
        return kh;
    }

    public static boolean Check(KhachThue kh, boolean them) {
        if (them) {
            //Kiểm tra mã khách trùng
            ResultSet rs = DAL.DALKhachThue.FindByMaNguoiThue(kh.getMaNguoiThue());
            try {
                if (rs.next()) {
                    ThongBao.ThongBaoDonGian("Báo Lỗi", " mã Khách đã tồn tại");
                    return false;
                }
            } catch (SQLException ex) {
                ThongBao.ThongBaoDonGian("Báo Lỗi", "Lỗi check");
                return false;
            }
        }
        if (them) {
            //Kiểm tra mã khách trùng
            ResultSet rs = DAL.DALKhachThue.FindByNameOrID(kh.getTenNguoiThue());
            try {
                if (rs.next()) {
                    ThongBao.ThongBaoDonGian("Báo Lỗi", "Tên Khách đã tồn tại");
                    return false;
                }
            } catch (SQLException ex) {
                ThongBao.ThongBaoDonGian("Báo Lỗi", "Lỗi check");
                return false;
            }
        } if (them) {
            ResultSet rs = DAL.DALKhachThue.FindBySDT(kh.getSDT());
            try {
                if (rs.next()) {
                    ThongBao.ThongBaoDonGian("Báo Lỗi", "Số điện thoại đã tồn tại");
                    return false;
                }
            } catch (SQLException ex) {
                ThongBao.ThongBaoDonGian("Báo Lỗi", "Lỗi check");
                return false;
            }
        } if (them) {
            ResultSet rs = DAL.DALKhachThue.FindByCMND(kh.getCMND());
            try {
                if (rs.next()) {
                    ThongBao.ThongBaoDonGian("Báo Lỗi", "CMND đã tồn tại");
                    return false;
                }
            } catch (SQLException ex) {
                ThongBao.ThongBaoDonGian("Báo Lỗi", "Lỗi check");
                return false;
            }
        }
        return true;
    }

    public static boolean CheckTrangThaiMaNguoiThue(String MaNguoiThue) {
        ResultSet rs = DAL.DALKhachThue.FindByMaNguoiThue(MaNguoiThue);
        try {
            while (rs.next()) {
                if ((rs.getByte("TrangThai")) == 0) {
                    return false;
                }
            }
        } catch (SQLException ex) {
        }
        return true;
    }

    public static void Add(KhachThue kh) {
        if (Check(kh, true)) {
            DAL.DALKhachThue.Insert(kh);
            jdlKhachThue.check = true;
            ThongBao.ThongBaoDonGian("Thông báo", "Đã thêm!");
            jdlKhachThue khachthue = new jdlKhachThue(null, true);
            khachthue.LamMoi();
        }else{
            jdlKhachThue.check = false;
        }
    }

    public static void Update(KhachThue kh) {

        DAL.DALKhachThue.Update(kh);

    }
    public static void UpdateDaiDien(KhachThue kh) {

        DAL.DALKhachThue.UpdateDaiDien(kh);

    }

    public static void Delete(String MaKhachHang) {

        DAL.DALKhachThue.Delete(MaKhachHang);

    }

    public static void doComboBox(ArrayList<KhachThue> arr, JComboBox cbb) {
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbb.getModel();
        cbb.removeAllItems();
        MyCombobox myCbb = new MyCombobox("", "--Chọn mã người thuê--");
        cbbModel.addElement(myCbb);
        for (KhachThue lsp : arr) {
            Object value = lsp.getMaNguoiThue();
            Object text = lsp.getMaNguoiThue();
            myCbb = new MyCombobox(value, text);
            cbbModel.addElement(myCbb);
        }
    }

    public static void HienThiKhachThueCBB(JComboBox cbb, String tenLoai) {
        for (int i = 0; i < cbb.getItemCount(); i++) {
            MyCombobox myCbb = (MyCombobox) cbb.getItemAt(i);
            if (myCbb.toString().equals(tenLoai)) {
                cbb.setSelectedIndex(i);
            }
        }
    }

    public static int CountNguoiThueTrangThai(int i) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALKhachThue.CountNguoiThueTrangThai(i);
        try {
            while (rs.next()) {
                return rs.getInt(2);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu" + ex.getMessage());
        }
        return 0;
    }
}
