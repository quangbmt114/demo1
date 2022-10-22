/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import static BLL.BLLHoatDongThuePhong.Check;
import DTO.LoaiSanPham;
import DTO.NhanVien;
import DTO.HoatDongThuePhong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author quang
 */
public class BLLNhanVien {
    public static ArrayList<NhanVien> GetAll(){
        ResultSet rs = DAL.DALNhanVien.GetAllData();
        
        ArrayList<NhanVien> arr = new ArrayList<>();
        try {
            while(rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getInt("MaNhanVien"));
                nv.setTenNhanVien(rs.getString("TenNhanVien"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setChucVu(rs.getString("ChucVu"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
                nv.setLuong(rs.getDouble("Luong"));
                nv.setTenDangNhap(rs.getString("TenDangNhap"));
                nv.setMatKhau(rs.getString("MatKhau"));
                arr.add(nv);   // Thêm Nhân Viên Vào ArrayList
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.getMessage());
        }
        
        return arr;
    }
    public static void DoVaoTable(ArrayList<NhanVien> arr, JTable tbl){
        
        DefaultTableModel tbModel = (DefaultTableModel)tbl.getModel();
        tbModel.setRowCount(0);
        for(NhanVien nv : arr){
            Object obj[] = new Object[11];
            
            obj[0] = nv.getMaNhanVien();
            obj[1] =nv.getTenNhanVien();
            obj[2] =nv.getDiaChi();
            obj[3] =nv.getSoDienThoai();
            obj[4] =nv.isGioiTinh()? "Nữ":"Nam";
            obj[5] =nv.getChucVu();
            obj[6] =nv.getNgaySinh();
            obj[7] =nv.getNgayVaoLam();
            obj[8]= nv.getLuong();
            obj[9] =nv.getTenDangNhap();
            obj[10]= nv.getMatKhau();
            tbModel.addRow(obj);
        }
    }
    public static void Add(NhanVien nv){
            DAL.DALNhanVien.Add(nv);
        }
    public static void Delete(int nv){
            DAL.DALNhanVien.Delete(nv);
        }
    public static void Update(NhanVien nv){
            DAL.DALNhanVien.Update(nv);
        }

}
