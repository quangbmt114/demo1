/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class BLLChiTietDichVuPhong {

    public static ArrayList<CTDV> GetAll() {
        ResultSet rs = DAL.DALCTDV.GetAllData();
        ArrayList<CTDV> list = new ArrayList<>();
        try {
            while (rs.next()) {
                CTDV dv = new CTDV();
                dv.setMaPhong(rs.getString("MaPhong"));
                dv.setMaDichVu(rs.getString("MaDichVu"));
//              dv.setGia(rs.getInt("GiaDichVu"));
//              dv.setDonViTinh(rs.getString("DonViTinh"));
//              dv.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(dv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLLDichVu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static ArrayList<CTDV> FindByMaPhong(String MaPhong) {
        ResultSet rs = DAL.DALCTDV.FindByMaPhong(MaPhong);
        ArrayList<CTDV> list = new ArrayList<>();
        try {
            while (rs.next()) {
                CTDV dv = new CTDV();
                dv.setMaPhong(rs.getString("MaPhong"));
                dv.setMaDichVu(rs.getString("MaDichVu"));
//              dv.setGia(rs.getInt("GiaDichVu"));
//              dv.setDonViTinh(rs.getString("DonViTinh"));
//              dv.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(dv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLLDichVu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static void DoVaoTable(ArrayList<CTDV> arr, JTable tbl) {

        DefaultTableModel tbModel = (DefaultTableModel) tbl.getModel();
        tbModel.setRowCount(0);
        for (CTDV dv : arr) {
            Object obj[] = new Object[2];

//            obj[0] = dv.getMaPhong();
            obj[0] = dv.getMaDichVu();
            obj[1] = BLLDichVu.getDichVu(dv.getMaDichVu()).getTenDichVu();

//             obj[2] = dv.getDonViTinh();Y
            tbModel.addRow(obj);
        }
    }
    public static void Add(CTDV dv){
        DAL.DALCTDV.Insert(dv);
    } 
    public static void Delete(String MaDichVu,String MaPhong){
        DAL.DALCTDV.Delete(MaDichVu,MaPhong);
    } 
    public static boolean CheckHopLe(CTDV dv){
        ArrayList<CTDV> arr=GetAll();
        for(CTDV i:arr){
            if(i.getMaDichVu().equals(dv.getMaDichVu())&&i.getMaPhong().equals(dv.getMaPhong())){
                return false;
            }
        }
        return true;
    } 
}
