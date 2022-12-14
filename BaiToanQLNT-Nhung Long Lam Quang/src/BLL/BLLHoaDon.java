/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.*;
import helper.*;
import java.sql.*;
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
 * @author Dell
 */
public class BLLHoaDon {

    public static ArrayList<DTO.HoaDonPhongTro> GetAll() {
        ResultSet rs = DAL.DALHoaDonPhongTro.GetAllData();
        ArrayList<DTO.HoaDonPhongTro> list = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO.HoaDonPhongTro hdpt = new DTO.HoaDonPhongTro();
                hdpt.setMaHoaDon(rs.getString("MaHoaDon"));
                hdpt.setMaPhong(rs.getString("MaPhong"));
                hdpt.setThangTieuThu(rs.getDate("ThangTieuThu"));
                hdpt.setChiSoMoi(rs.getString("ChiSoMoi"));
                hdpt.setChiSoCu(rs.getString("ChiSoCu"));
                hdpt.setTongTienPhong(rs.getInt("TongTienPhong"));
                hdpt.setTongTienDien(rs.getInt("TongTienDien"));
                hdpt.setTongTienNuoc(rs.getInt("TongTienNuoc"));
                hdpt.setTongTienDV(rs.getInt("TongTienDV"));
                hdpt.setTongTien(rs.getInt("TongTien"));
                hdpt.setGhiChu(rs.getString("GhiChu"));
                list.add(hdpt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLLDichVu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static void Add(HoaDonPhongTro sp) {
            DAL.DALHoaDonPhongTro.Add(sp);
//        if (BLLKhachThue.CheckTrangThaiMaNguoiThue(sp.getMaNguoiThue())) {
//            System.out.println("hey hey");
//        }
    }
    public static void Update(HoaDonPhongTro sp) {
            DAL.DALHoaDonPhongTro.Update(sp);        
    }

    public static void DoVaoTable(ArrayList<DTO.HoaDonPhongTro> arr, JTable tbl) {

        DefaultTableModel tbModel = (DefaultTableModel) tbl.getModel();
        tbModel.setRowCount(0);
        for (DTO.HoaDonPhongTro hdpt : arr) {
            Object obj[] = new Object[11];
            obj[0] = hdpt.getMaHoaDon();
            obj[1] = hdpt.getMaPhong();
            obj[2] = ChuyenDoi.LayNgayString(hdpt.getThangTieuThu());
            obj[3] = hdpt.getChiSoMoi();
            obj[4] = hdpt.getChiSoCu();
            obj[5] = hdpt.getTongTienPhong();
            obj[6] = hdpt.getTongTienDien();
            obj[7] = hdpt.getTongTienNuoc();
            obj[8] = hdpt.getTongTienDV();
            obj[9] = hdpt.getTongTien();
            obj[10] = hdpt.getGhiChu();
//            obj[5] = hdpt.isTrangThai() ? "Đang sử dụng" : "Không sử dụng";

            tbModel.addRow(obj);
        }
    }

    public static HoaDonPhongTro FindMaPhong(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALHoaDonPhongTro.FindByMaPhong(TuKhoa);

        DTO.HoaDonPhongTro hdpt = new DTO.HoaDonPhongTro();
        try {
            while (rs.next()) {
                hdpt.setMaHoaDon(rs.getString("MaHoaDon"));
                hdpt.setMaPhong(rs.getString("MaPhong"));
                hdpt.setThangTieuThu(rs.getDate("ThangTieuThu"));
                hdpt.setChiSoMoi(rs.getString("ChiSoMoi"));
                hdpt.setChiSoCu(rs.getString("ChiSoCu"));
                hdpt.setTongTienPhong(rs.getInt("TongTienPhong"));
                hdpt.setTongTienDien(rs.getInt("TongTienDien"));
                hdpt.setTongTienNuoc(rs.getInt("TongTienNuoc"));
                hdpt.setTongTienDV(rs.getInt("TongTienDV"));
                hdpt.setTongTien(rs.getInt("TongTien"));
                hdpt.setGhiChu(rs.getString("GhiChu"));
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu" + ex.getMessage());
        }
        return hdpt;
    }

    public static ArrayList<HoaDonPhongTro> FindByMaPhong(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALHoaDonPhongTro.FindByMaPhong(TuKhoa);

        ArrayList<HoaDonPhongTro> arr=new ArrayList<>();
        try {
            while (rs.next()) {
                DTO.HoaDonPhongTro hdpt = new DTO.HoaDonPhongTro();
                hdpt.setMaHoaDon(rs.getString("MaHoaDon"));
                hdpt.setMaPhong(rs.getString("MaPhong"));
                hdpt.setThangTieuThu(rs.getDate("ThangTieuThu"));
                hdpt.setChiSoMoi(rs.getString("ChiSoMoi"));
                hdpt.setChiSoCu(rs.getString("ChiSoCu"));
                hdpt.setTongTienPhong(rs.getInt("TongTienPhong"));
                hdpt.setTongTienDien(rs.getInt("TongTienDien"));
                hdpt.setTongTienNuoc(rs.getInt("TongTienNuoc"));
                hdpt.setTongTienDV(rs.getInt("TongTienDV"));
                hdpt.setTongTien(rs.getInt("TongTien"));
                hdpt.setGhiChu(rs.getString("GhiChu"));
                arr.add(hdpt);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu" + ex.getMessage());
        }
        return arr;
    }
    public static ArrayList<HoaDonPhongTro> FindByMaHoaDon(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALHoaDonPhongTro.FindByMaHoaDon(TuKhoa);

        ArrayList<HoaDonPhongTro> arr=new ArrayList<>();
        try {
            while (rs.next()) {
                DTO.HoaDonPhongTro hdpt = new DTO.HoaDonPhongTro();
                hdpt.setMaHoaDon(rs.getString("MaHoaDon"));
                hdpt.setMaPhong(rs.getString("MaPhong"));
                hdpt.setThangTieuThu(rs.getDate("ThangTieuThu"));
                hdpt.setChiSoMoi(rs.getString("ChiSoMoi"));
                hdpt.setChiSoCu(rs.getString("ChiSoCu"));
                hdpt.setTongTienPhong(rs.getInt("TongTienPhong"));
                hdpt.setTongTienDien(rs.getInt("TongTienDien"));
                hdpt.setTongTienNuoc(rs.getInt("TongTienNuoc"));
                hdpt.setTongTienDV(rs.getInt("TongTienDV"));
                hdpt.setTongTien(rs.getInt("TongTien"));
                hdpt.setGhiChu(rs.getString("GhiChu"));
                arr.add(hdpt);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu" + ex.getMessage());
        }
        return arr;
    }

//    public static void doComboBox(ArrayList<PhongTro> arr, JComboBox cbb) {
//        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbb.getModel();
//        cbb.removeAllItems();
//        MyCombobox myCbb = new MyCombobox("", "--Chọn phòng--");
//        cbbModel.addElement(myCbb);
//        for (PhongTro lsp : arr) {
//            Object value = lsp.getMaPhong();
//            Object text = lsp.getMaPhong();
//            myCbb = new MyCombobox(value, text);
//            cbbModel.addElement(myCbb);
//        }
//    }
//    public static void HienThiHoaDonPhongTroCBB(JComboBox cbb, String tenLoai) {
//        for (int i = 0; i < cbb.getItemCount(); i++) {
//            MyCombobox myCbb = (MyCombobox) cbb.getItemAt(i);
//            if (myCbb.toString().equals(tenLoai)) {
//                cbb.setSelectedIndex(i);
//            }
//        }
//    }
    public static String SoHoaDon(String maPhong, Date thangTruoc, Date thangNay) {
        String soHoaDon = "HD" + maPhong + "-";
        //HDPA01-120622-120722
        DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        soHoaDon += dateFormat.format(thangTruoc)+"-"+dateFormat.format(thangNay);
//            Date d = new Date();

//            soHoaDon += dateFormat.format(d);
//            System.out.println("soHoaDon: " + soHoaDon);
//            ResultSet rs = DAL.DALHoatDongThuePhong.CountSoHoaDon(soHoaDon);
//            int rowCount = 0;
//            if (rs.next()) {
//                rowCount = rs.getInt(1);
//            }
//            boolean dup = false;
//            do {
//                if (rowCount > 98) {
//                    soHoaDon +=(rowCount + 1);
//                } else if (rowCount > 8) {
//                    soHoaDon += "0" + (rowCount + 1);
//                } else {
//                    soHoaDon += "00" + (rowCount + 1);
//                }
////                System.out.println("soHoaDon: " + soHoaDon);
//                ResultSet rs2 = DAL.DALHoatDongThuePhong.GetHoaDonBySo(soHoaDon);
//                if (rs2.next()) {
//                    dup = true;
//                    rowCount++;
//                    soHoaDon = maPhong+dateFormat.format(d);
//                } else {
//                    dup = false;
//                }
//            } while (dup);

        return soHoaDon;
    }

}
