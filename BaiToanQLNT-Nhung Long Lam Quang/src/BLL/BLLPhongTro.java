/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.PhongTro;
import DTO.PhongTro;
import helper.MyCombobox;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author quang nguyen
 */
public class BLLPhongTro {

    public static ArrayList<DTO.PhongTro> GetAll() {
        ResultSet rs = DAL.DALPhongTro.GetAll();
        ArrayList<DTO.PhongTro> list = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO.PhongTro pt = new DTO.PhongTro();
                pt.setMaPhong(rs.getString("MaPhong"));
                pt.setTenPhong(rs.getString("TenPhong"));
                pt.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
                pt.setMaKhuTro(rs.getString("MaKhuTro"));
                pt.setViTri(rs.getString("Vitri"));
                pt.setDienTich(rs.getFloat("DienTich"));
                pt.setGiuong(rs.getInt("Giuong"));
                pt.setBan(rs.getInt("Ban"));
                pt.setBongDen(rs.getInt("BongDen"));
                pt.setKinhCua(rs.getInt("KinhCua"));
                pt.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(pt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLLDichVu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void DoVaoTable(ArrayList<DTO.PhongTro> arr, JTable tbl) {

        DefaultTableModel tbModel = (DefaultTableModel) tbl.getModel();
        tbModel.setRowCount(0);
        for (DTO.PhongTro dv : arr) {
            Object obj[] = new Object[6];

            obj[0] = dv.getMaPhong();
            obj[1] = dv.getTenPhong();
            obj[2] = dv.getMaLoaiPhong();
            obj[3] = dv.getViTri();
            obj[4] = dv.getDienTich();
            obj[5] = dv.isTrangThai() ? "Đang sử dụng" : "Không sử dụng";

            tbModel.addRow(obj);
        }
    }

    public static PhongTro FindMaPhong(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALPhongTro.FindByMaPhong(TuKhoa);

        DTO.PhongTro pt = new DTO.PhongTro();
        try {
            while (rs.next()) {
                pt.setMaPhong(rs.getString("MaPhong"));
                pt.setTenPhong(rs.getString("TenPhong"));
                pt.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
                pt.setMaKhuTro(rs.getString("MaKhuTro"));
                pt.setViTri(rs.getString("Vitri"));
                pt.setDienTich(rs.getFloat("DienTich"));
                pt.setGiuong(rs.getInt("Giuong"));
                pt.setBan(rs.getInt("Ban"));
                pt.setBongDen(rs.getInt("BongDen"));
                pt.setKinhCua(rs.getInt("KinhCua"));
                pt.setTrangThai(rs.getBoolean("TrangThai"));
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu" + ex.getMessage());
        }
        return pt;
    }
    public static ArrayList<DTO.PhongTro> FindOnlyMaPhong(String TuKhoa) {

        //Lấy tất cả dữ liệu Loại sản phẩm từ SQL
        ResultSet rs = DAL.DALPhongTro.FindByOnlyMaPhong(TuKhoa);
        ArrayList<DTO.PhongTro> list = new ArrayList<>();
        DTO.PhongTro pt = new DTO.PhongTro();
        try {
            while (rs.next()) {
                pt.setMaPhong(rs.getString("MaPhong"));
                pt.setTenPhong(rs.getString("TenPhong"));
                pt.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
                pt.setMaKhuTro(rs.getString("MaKhuTro"));
                pt.setViTri(rs.getString("Vitri"));
                pt.setDienTich(rs.getFloat("DienTich"));
                pt.setGiuong(rs.getInt("Giuong"));
                pt.setBan(rs.getInt("Ban"));
                pt.setBongDen(rs.getInt("BongDen"));
                pt.setKinhCua(rs.getInt("KinhCua"));
                pt.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(pt);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu" + ex.getMessage());
        }
        return list ;
    }

    public static void doComboBox(ArrayList<PhongTro> arr, JComboBox cbb) {
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbb.getModel();
        cbb.removeAllItems();
        MyCombobox myCbb = new MyCombobox("", "--Chọn phòng--");
        cbbModel.addElement(myCbb);
        for (PhongTro lsp : arr) {
            Object value = lsp.getMaPhong();
            Object text = lsp.getMaPhong();
            myCbb = new MyCombobox(value, text);
            cbbModel.addElement(myCbb);
        }
    }

    public static void HienThiPhongTroCBB(JComboBox cbb, String tenLoai) {
        for (int i = 0; i < cbb.getItemCount(); i++) {
            MyCombobox myCbb = (MyCombobox) cbb.getItemAt(i);
            if (myCbb.toString().equals(tenLoai)) {
                cbb.setSelectedIndex(i);
            }
        }
    }
}
