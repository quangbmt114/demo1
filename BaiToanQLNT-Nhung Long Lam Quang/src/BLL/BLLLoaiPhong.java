/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.LoaiPhong;
import DTO.LoaiSanPham;
import GUI.QuanLyLoaiPhong2;
import helper.ChuyenDoi;
import helper.ThongBao;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author quang nguyen
 */
public class BLLLoaiPhong {

    public static ArrayList<LoaiPhong> GetAll() {
        ResultSet rs = DAL.DALLoaiPhong.GetAllData();

        ArrayList<LoaiPhong> arr = new ArrayList<>();
        try {
            while (rs.next()) {
                LoaiPhong lp = new LoaiPhong();
                lp.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
                lp.setTenLoaiPhong(rs.getString("TenLoaiPhong"));
                lp.setGiaPhong(rs.getFloat("GiaPhong"));
                lp.setGiaDien(rs.getFloat("GiaDien"));
                lp.setGiaNuoc(rs.getFloat("GiaNuoc"));
                arr.add(lp);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.getMessage());
        }

        
        return arr;
    }

    public static void DoVaoTable(ArrayList<LoaiPhong> arr, JTable tbl) {

        DefaultTableModel tbModel = (DefaultTableModel) tbl.getModel();
        tbModel.setRowCount(0);
        for (LoaiPhong lp : arr) {
            Object obj[] = new Object[6];

            obj[0] = lp.getMaLoaiPhong();
            obj[1] = lp.getTenLoaiPhong();
            obj[2] = ChuyenDoi.SoString(lp.getGiaPhong());
            obj[3] = ChuyenDoi.SoString(lp.getGiaDien());
            obj[4] = ChuyenDoi.SoString(lp.getGiaNuoc());

            tbModel.addRow(obj);
        }
    }

    public static LoaiPhong FindMaLoaiPhong(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALLoaiPhong.FindByMaLoaiPhong(TuKhoa);

                LoaiPhong lp = new LoaiPhong();
        try {
            while (rs.next()) {
                lp.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
                lp.setTenLoaiPhong(rs.getString("TenLoaiPhong"));
                lp.setGiaPhong(rs.getFloat("GiaPhong"));
                lp.setGiaDien(rs.getFloat("GiaDien"));
                lp.setGiaNuoc(rs.getFloat("GiaNuoc"));
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu" + ex.getMessage());
        }
        return lp;
    }
    
    
     public static boolean Check(LoaiPhong sp, boolean them) {
        if (sp.getGiaPhong() < 0) {
            ThongBao.ThongBaoDonGian("Báo lỗi", "Giá phòng phải >=0");
            return false;
        }

        if (sp.getGiaDien() < 0) {
            ThongBao.ThongBaoDonGian("Báo lỗi", "Giá điện phải >=0");
            return false;
        }
        if (sp.getGiaNuoc()< 0) {
            ThongBao.ThongBaoDonGian("Báo lỗi", "Giá nước phải >=0");
            return false;
        }
        
        if (them) {
            //Kiểm tra mã sp trùng với Database
            ResultSet rs = DAL.DALLoaiPhong.FindByMaLoaiPhong(sp.getMaLoaiPhong());

            try {
                if (rs.next()) {
                    ThongBao.ThongBaoDonGian("Báo lỗi", "Mã Loại Phòng không được trùng");
                    return false;
                }
            } catch (SQLException ex) {
                ThongBao.ThongBaoDonGian("Báo lỗi", "Lỗi lấy dữ liệu");
                return false;
            }
        }
        
        if (them) {
            //Kiểm tra mã sp trùng với Database
            ResultSet rs = DAL.DALLoaiPhong.FindByTenLoaiPhong(sp.getTenLoaiPhong());

            try {
                if (rs.next()) {
                    ThongBao.ThongBaoDonGian("Báo lỗi", "Loại Phòng đã tồn tại");
                    return false;
                }
            } catch (SQLException ex) {
                ThongBao.ThongBaoDonGian("Báo lỗi", "Lỗi lấy dữ liệu");
                return false;
            }
        }

        return true;
    }
      public static void Add(LoaiPhong lp) {
        if (Check(lp, true)) {
            DAL.DALLoaiPhong. Create(lp);
        }
    }
      
       public static void Update(LoaiPhong lp) {
        
            DAL.DALLoaiPhong.Update(lp);
        
    }
    
        public static void Delete(String IDLoaiPhong){
        try {
            DAL.DALLoaiPhong.Delete(IDLoaiPhong);
        } catch (Exception e) {
            ThongBao.ThongBaoCoIcon("Thông báo lỗi",
                    "Không thể xóa dữ liệu loại phòng đang được sử dụng",
                    2);
        }
    }
}
