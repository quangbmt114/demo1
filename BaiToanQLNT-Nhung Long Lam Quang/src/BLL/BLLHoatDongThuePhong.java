/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.HoatDongThuePhong;
import DTO.KhachThue;
import helper.ChuyenDoi;
import helper.MyCombobox;
import helper.ThongBao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cuong
 */
public class BLLHoatDongThuePhong {

    //Hàm đổ dữ liệu Sản phẩm
    public static ArrayList<HoatDongThuePhong> GetAll() {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALHoatDongThuePhong.GetAllData();

        ArrayList<HoatDongThuePhong> arr = new ArrayList<>();

        try {
            while (rs.next()) {
                HoatDongThuePhong sp = new HoatDongThuePhong();
                sp.setMaHopDong(rs.getString("MaHopDong"));
                sp.setMaPhong(rs.getString("MaPhong"));
                sp.setMaNguoiThue(rs.getString("MaNguoiThue"));
                sp.setNgayThue(rs.getDate("NgayThue"));
                sp.setNgayTra(rs.getDate("NgayTra"));
                sp.setGhiChu(rs.getString("GhiChu"));
                sp.setTinhTrang(rs.getBoolean("TinhTrang"));

                arr.add(sp);   //Thêm sản phẩm lsp vào ArrayList
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.getMessage());
        }

        return arr; //Trả về danh sách  sản phẩm
    }

    public static ArrayList<HoatDongThuePhong> FindByMaHopDong(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALHoatDongThuePhong.FindByMaHopDong(TuKhoa);

        ArrayList<HoatDongThuePhong> arr = new ArrayList<>();

        try {
            while (rs.next()) {
                HoatDongThuePhong sp = new HoatDongThuePhong();

                sp.setMaHopDong(rs.getString("MaHopDong"));
                sp.setMaPhong(rs.getString("MaPhong"));
                sp.setMaNguoiThue(rs.getString("MaNguoiThue"));
                sp.setNgayThue(rs.getDate("NgayThue"));
                sp.setNgayTra(rs.getDate("NgayTra"));
                sp.setGhiChu(rs.getString("GhiChu"));
                sp.setTinhTrang(rs.getBoolean("TinhTrang"));

                arr.add(sp);   //Thêm sản phẩm lsp vào ArrayList
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.getMessage());
        }

        return arr; //Trả về danh sách  sản phẩm
    }

    public static ArrayList<HoatDongThuePhong> FindByMaNguoiThue(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALHoatDongThuePhong.FindByMaNguoiThue(TuKhoa);

        ArrayList<HoatDongThuePhong> arr = new ArrayList<>();

        try {
            while (rs.next()) {
                HoatDongThuePhong sp = new HoatDongThuePhong();

                sp.setMaHopDong(rs.getString("MaHopDong"));
                sp.setMaPhong(rs.getString("MaPhong"));
                sp.setMaNguoiThue(rs.getString("MaNguoiThue"));
                sp.setNgayThue(rs.getDate("NgayThue"));
                sp.setNgayTra(rs.getDate("NgayTra"));
                sp.setGhiChu(rs.getString("GhiChu"));
                sp.setTinhTrang(rs.getBoolean("TinhTrang"));

                arr.add(sp);   //Thêm sản phẩm lsp vào ArrayList
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.getMessage());
        }

        return arr; //Trả về danh sách  sản phẩm
    }

//    public static ArrayList<HoatDongThuePhong> FindByMaNguoiThue(String TuKhoa) {
//
//        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
//        ResultSet rs = DAL.DALHoatDongThuePhong.FindByMaNguoiThue(TuKhoa);
//
//        ArrayList<HoatDongThuePhong> arr = new ArrayList<>();
//
//        try {
//            while (rs.next()) {
//                HoatDongThuePhong sp = new HoatDongThuePhong();
//
//                sp.setMaHopDong(rs.getString("MaHopDong"));
//                sp.setMaPhong(rs.getString("MaPhong"));
//                sp.setMaNguoiThue(rs.getString("MaNguoiThue"));
//                sp.setNgayThue(rs.getDate("NgayThue"));
//                sp.setNgayTra(rs.getDate("NgayTra"));
//                sp.setGhiChu(rs.getString("GhiChu"));
//                sp.setTinhTrang(rs.getBoolean("TinhTrang"));
//
//                arr.add(sp);   //Thêm sản phẩm lsp vào ArrayList
//            }
//        } catch (SQLException ex) {
//            System.out.println("Lỗi lấy dữ liệu: " + ex.getMessage());
//        }
//
//        return arr; //Trả về danh sách  sản phẩm
//    }
    public static HoatDongThuePhong FindDaiDienPhong(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALHoatDongThuePhong.FindDaiDienPhong(TuKhoa);

//        ArrayList<HoatDongThuePhong> arr = new ArrayList<>();

        try {
            while (rs.next()) {
                HoatDongThuePhong sp = new HoatDongThuePhong();

                sp.setMaHopDong(rs.getString("MaHopDong"));
                sp.setMaPhong(rs.getString("MaPhong"));
                sp.setMaNguoiThue(rs.getString("MaNguoiThue"));
                sp.setNgayThue(rs.getDate("NgayThue"));
                sp.setNgayTra(rs.getDate("NgayTra"));
                sp.setGhiChu(rs.getString("GhiChu"));
                sp.setTinhTrang(rs.getBoolean("TinhTrang"));
                
                return sp;
//                arr.add(sp);   //Thêm sản phẩm lsp vào ArrayList
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.getMessage());
        }

        return null; //Trả về danh sách  sản phẩm
    }
    public static HoatDongThuePhong FindMaHopDong(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALHoatDongThuePhong.FindMaHopDong(TuKhoa);

//        ArrayList<HoatDongThuePhong> arr = new ArrayList<>();

        try {
            while (rs.next()) {
                HoatDongThuePhong sp = new HoatDongThuePhong();

                sp.setMaHopDong(rs.getString("MaHopDong"));
                sp.setMaPhong(rs.getString("MaPhong"));
                sp.setMaNguoiThue(rs.getString("MaNguoiThue"));
                sp.setNgayThue(rs.getDate("NgayThue"));
                sp.setNgayTra(rs.getDate("NgayTra"));
                sp.setGhiChu(rs.getString("GhiChu"));
                sp.setTinhTrang(rs.getBoolean("TinhTrang"));
                return sp;
//                arr.add(sp);   //Thêm sản phẩm lsp vào ArrayList
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.getMessage());
        }

        return null; //Trả về danh sách  sản phẩm
    }
    public static ArrayList<HoatDongThuePhong> FindByMaHopDongOrMaNguoiThue(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALHoatDongThuePhong.FindByMaHopDongOrMaNguoiThue(TuKhoa);

        ArrayList<HoatDongThuePhong> arr = new ArrayList<>();

        try {
            while (rs.next()) {
                HoatDongThuePhong sp = new HoatDongThuePhong();

                sp.setMaHopDong(rs.getString("MaHopDong"));
                sp.setMaPhong(rs.getString("MaPhong"));
                sp.setMaNguoiThue(rs.getString("MaNguoiThue"));
                sp.setNgayThue(rs.getDate("NgayThue"));
                sp.setNgayTra(rs.getDate("NgayTra"));
                sp.setGhiChu(rs.getString("GhiChu"));
                sp.setTinhTrang(rs.getBoolean("TinhTrang"));

                arr.add(sp);   //Thêm sản phẩm lsp vào ArrayList
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.getMessage());
        }

        return arr; //Trả về danh sách  sản phẩm
    }

    //Hàm đổ dữ liệu lên Table
    public static void DoVaoTable(ArrayList<HoatDongThuePhong> arr, JTable tbl) {

        DefaultTableModel tbModel = (DefaultTableModel) tbl.getModel();

        tbModel.setRowCount(0); //Xóa het cac dong trong Table

        for (HoatDongThuePhong sp : arr) {
            Object obj[] = new Object[8];
            obj[0] = sp.getMaHopDong();
            obj[1] = sp.getMaPhong();
            KhachThue kt = BLLKhachThue.FindByMaNguoiThue(sp.getMaNguoiThue());
            
            obj[2] = kt.getMaNguoiThue();
            obj[3] = kt.getTenNguoiThue();
            obj[4] = sp.getNgayThue();
            obj[5] = sp.getNgayTra();
            obj[6] = sp.getGhiChu();
            obj[7] = sp.isTinhTrang()== true ? "Đang thuê" : "Đã kết HĐ";
            tbModel.addRow(obj);
        }
    }

    public static boolean Check(String Manguoithue) {
        return true;
    }

    public static void Add(HoatDongThuePhong sp, int i) {
            DAL.DALHoatDongThuePhong.Add(sp,i);
//        if (BLLKhachThue.CheckTrangThaiMaNguoiThue(sp.getMaNguoiThue())) {
//            System.out.println("hey hey");
//        }
    }
    public static void Update(HoatDongThuePhong sp, int i) {
            DAL.DALHoatDongThuePhong.Update(sp,i);        
    }
    public static void Delete(String maHopDong,String maPhong,String maKhachThue) {
            DAL.DALHoatDongThuePhong.Delete(maHopDong,maPhong,maKhachThue);        
    }
    
    public static String SoHoaDon(String maPhong) {
        String soHoaDon = ""+maPhong;
        try {

            DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

            Date d = new Date();

            soHoaDon += dateFormat.format(d);
//            System.out.println("soHoaDon: " + soHoaDon);
            ResultSet rs = DAL.DALHoatDongThuePhong.CountSoHoaDon(soHoaDon);
            int rowCount = 0;
            if (rs.next()) {
                rowCount = rs.getInt(1);
            }
            boolean dup = false;
            do {
                if (rowCount > 98) {
                    soHoaDon +=(rowCount + 1);
                } else if (rowCount > 8) {
                    soHoaDon += "0" + (rowCount + 1);
                } else {
                    soHoaDon += "00" + (rowCount + 1);
                }
//                System.out.println("soHoaDon: " + soHoaDon);
                ResultSet rs2 = DAL.DALHoatDongThuePhong.GetHoaDonBySo(soHoaDon);
                if (rs2.next()) {
                    dup = true;
                    rowCount++;
                    soHoaDon = maPhong+dateFormat.format(d);
                } else {
                    dup = false;
                }
            } while (dup);

        } catch (SQLException ex) {
            System.out.println("Lỗi số hóa đơn");
        }

        return soHoaDon;
    }
    public static boolean CheckMaHopDong(String MaHopDong){
        ArrayList<HoatDongThuePhong> arr=FindByMaHopDong(MaHopDong);
        for (HoatDongThuePhong hd:arr){
//            ThongBao.ThongBaoDonGian("", "'"+arr.get(0).getMaHopDong()+"'"+MaHopDong+"'");
            if(hd.getMaHopDong().equals(MaHopDong)){
                return false;
            }
        }
        return true;
    }

    public static void doComboBox(ArrayList<HoatDongThuePhong> arrHD, JComboBox<String> cbbPhong) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
