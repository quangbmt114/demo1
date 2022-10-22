/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.LoaiPhong;
import DTO.LoaiSanPham;
import GUI.QuanLyLoaiPhong2;
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
        ArrayList<LoaiPhong> list = new ArrayList<>();
        try {
            while (rs.next()) {
                LoaiPhong lp = new LoaiPhong();
                lp.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
                lp.setTenLoaiPhong(rs.getString("TenLoaiPhong"));
                lp.setGiaPhong(rs.getFloat("GiaPhong"));
                lp.setGiaDien(rs.getFloat("GiaDien"));
                lp.setGiaNuoc(rs.getFloat("GiaNuoc"));
                list.add(lp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLLLoaiPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void DoVaoTable(ArrayList<LoaiPhong> arr, JTable tbl) {

        DefaultTableModel tbModel = (DefaultTableModel) tbl.getModel();
        tbModel.setRowCount(0);
        for (LoaiPhong lp : arr) {
            Object obj[] = new Object[5];

            obj[0] = lp.getMaLoaiPhong();
            obj[1] = lp.getTenLoaiPhong();
            obj[2] = lp.getGiaPhong();
            obj[3] = lp.getGiaDien();
            obj[4] = lp.getGiaNuoc();

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
}
