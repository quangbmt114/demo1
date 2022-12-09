/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.DichVu;
import DTO.LoaiPhong;
import GUI.frmDichVu;
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
public class BLLDichVu {

    public static ArrayList<DTO.DichVu> GetAll() {
        ResultSet rs = DAL.DALDichVu.GetAllData();
        ArrayList<DTO.DichVu> list = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO.DichVu dv = new DTO.DichVu();
                dv.setMaDichVu(rs.getString("MaDichVu"));
                dv.setTenDichVu(rs.getString("TenDichVu"));
                dv.setGia(rs.getInt("GiaDichVu"));
//              dv.setDonViTinh(rs.getString("DonViTinh"));
//              dv.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(dv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLLDichVu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void DoVaoTable(ArrayList<DTO.DichVu> arr, JTable tbl) {

        DefaultTableModel tbModel = (DefaultTableModel) tbl.getModel();
        tbModel.setRowCount(0);
        for (DTO.DichVu dv : arr) {
            Object obj[] = new Object[5];

            obj[0] = dv.getMaDichVu();
            obj[1] = dv.getTenDichVu();
            obj[2] = dv.getGia();
//             obj[2] = dv.getDonViTinh();Y

            tbModel.addRow(obj);
        }
    }

    public static void Add(DichVu dv) {
        DAL.DALDichVu.Add(dv);
    }

    public static void Delete(String dv) {
        DAL.DALDichVu.Delete(dv);
    }

    public static boolean CheckMaDichVu(String dv) {
        ArrayList<DichVu> arr = GetAll();
        for (DichVu i : arr) {
            if (i.getMaDichVu().equals(dv)) {
                return false;
            }
        }
        return true;
    }

    public static DichVu getDichVu(String dv) {
        ArrayList<DichVu> arr = GetAll();
        for (DichVu i : arr) {
            if (i.getMaDichVu().equals(dv)) {
                return i;
            }
        }
        return null;
    }
}
