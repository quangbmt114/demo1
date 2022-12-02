/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import static BLL.BLLHoatDongThuePhong.Check;
import DTO.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author quang
 */
public class BLLChiSoDienNuoc {
    public static ArrayList<ChiSoDienNuoc> GetAll(){
        ResultSet rs = DAL.DALChiSoDienNuoc.GetAllData();
        
        ArrayList<ChiSoDienNuoc> arr = new ArrayList<>();
        try {
            while(rs.next()){
                ChiSoDienNuoc cs = new ChiSoDienNuoc();
                cs.setMaChiSo(rs.getString("MaChiSo"));
                cs.setMaPhong(rs.getString("MaPhong"));
                cs.setSoDien(rs.getInt("SoDien"));
                cs.setSoNuoc(rs.getInt("SoNuoc"));
                cs.setNgayGhi(rs.getDate("NgayGhi"));
                arr.add(cs);   // Thêm Nhân Viên Vào ArrayList
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.getMessage());
        }
        
        return arr;
    }
    public static void DoVaoTable(ArrayList<ChiSoDienNuoc> arr, JTable tbl){
        
        DefaultTableModel tbModel = (DefaultTableModel)tbl.getModel();
        tbModel.setRowCount(0);
        for(ChiSoDienNuoc cs : arr){
            Object obj[] = new Object[11];
            
            obj[0] = cs.getMaChiSo();
            obj[1] =cs.getMaPhong();
            obj[2] =cs.getSoDien();
            obj[3] =cs.getSoNuoc();
//            obj[4] =cs.isGioiTinh()? "Nữ":"Nam";
            obj[4] =cs.getNgayGhi();
            tbModel.addRow(obj);
        }
    }
    public static void Add(ChiSoDienNuoc cs){
            DAL.DALChiSoDienNuoc.Add(cs);
        }
    public static void Delete(int cs){
            DAL.DALChiSoDienNuoc.Delete(cs);
        }
    public static void Update(ChiSoDienNuoc cs){
            DAL.DALChiSoDienNuoc.Update(cs);
        }

    public static ArrayList<ChiSoDienNuoc> FindByMaPhong(String tukhoa) {
        ResultSet rs = DAL.DALChiSoDienNuoc.FindByMaPhong(tukhoa);
        ArrayList<ChiSoDienNuoc> arr = new ArrayList<>();
        try {
            while (rs.next()) {
                ChiSoDienNuoc cs = new ChiSoDienNuoc();
                cs.setMaChiSo(rs.getString("MaChiSo"));
                cs.setMaPhong(rs.getString("MaPhong"));
                cs.setSoDien(rs.getInt("SoDien"));
                cs.setSoNuoc(rs.getInt("SoNuoc"));
                cs.setNgayGhi(rs.getDate("NgayGhi"));
                arr.add(cs);  
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.getMessage());
        }
        return arr;
    }
    
    public static String SoHoaDon(String maPhong) {
        String soHoaDon = ""+maPhong;
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date d = new Date();
        soHoaDon += dateFormat.format(d);
////            System.out.println("soHoaDon: " + soHoaDon);
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
