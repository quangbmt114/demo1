/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.NhanVien;
import DTO.HoatDongThuePhong;
import helper.ChuyenDoi;
import helper.sqlHelper;
import java.sql.ResultSet;

/**
 *
 * @author quang
 */
public class DALNhanVien {
    //Lấy thông tin từ CSDL
    public static ResultSet GetAllData(){
        String sql = "select * from NhanVien";
        return sqlHelper.executeQuery(sql);
    }
    
     public static void Add(NhanVien nv){
        String sql ="set dateformat dmy INSERT INTO [dbo].[NhanVien] " +
"           ([TenNhanVien]" +
"           ,[DiaChi]" +
"           ,[SoDienThoai]" +
"           ,[GioiTinh]" +
"           ,[ChucVu]" +
"           ,[NgaySinh]" +
"           ,[NgayVaoLam]" +
"           ,[Luong]" +
"           ,[TenDangNhap]" +
"           ,[MatKhau])" +
"     VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        sqlHelper.executeUpdate(sql, nv.getTenNhanVien(), nv.getDiaChi(), nv.getSoDienThoai(),
                nv.isGioiTinh(), nv.getChucVu(),ChuyenDoi.LayNgayString( nv.getNgaySinh()), 
                ChuyenDoi.LayNgayString(nv.getNgayVaoLam()), nv.getLuong(),nv.getTenDangNhap(),nv.getMatKhau());
    }
     public static void Update(NhanVien nv){
        String sql = "set dateformat dmy UPDATE [dbo].[NhanVien]" +
"   SET [TenNhanVien] = ?" +
"      ,[DiaChi] = ?" +
"      ,[SoDienThoai] = ?" +
"      ,[GioiTinh] = ?" +
"      ,[ChucVu] = ?" +
"      ,[NgaySinh] = ?" +
"      ,[NgayVaoLam] = ?" +
"      ,[Luong] = ?" +
"      ,[TenDangNhap] = ?" +
"      ,[MatKhau] = ?" +
" WHERE MaNhanVien = ?";
       sqlHelper.executeUpdate(sql, nv.getTenNhanVien(), nv.getDiaChi(), nv.getSoDienThoai(),
                nv.isGioiTinh(), nv.getChucVu(),ChuyenDoi.LayNgayString( nv.getNgaySinh()), 
                ChuyenDoi.LayNgayString(nv.getNgayVaoLam()), nv.getLuong(),nv.getTenDangNhap()
               ,nv.getMatKhau(),nv.getMaNhanVien());
    }
     public static void Delete(int MaNhanVien){
        String sql = "DELETE NhanVien where MaNhanVien= ?";
        sqlHelper.executeUpdate(sql, MaNhanVien);
    }
   
}
