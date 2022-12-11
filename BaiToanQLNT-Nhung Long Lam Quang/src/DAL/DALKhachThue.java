/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.KhachThue;
import helper.ChuyenDoi;
import helper.sqlHelper;
import java.sql.ResultSet;
/**
 *
 * @author ASUS VIVOBOOK
 */
public class DALKhachThue {
    public static ResultSet GetAllData(){
        String sql = "select * from NguoiThue";
        ResultSet rs = sqlHelper.executeQuery(sql);
        return rs;
    }
    public static ResultSet FindByName(String name){
        String sql = "select * from NguoiThue where TenNguoiThue like ? ";//like tìm gần giống , = tìm chính xác
        return sqlHelper.executeQuery(sql,"%"+name+"%");
    }
    public static ResultSet FindByMaKhachThue(String name){
        String sql = "select * from NguoiThue where MaNguoiThue like ? ";//like tìm gần giống , = tìm chính xác
        ResultSet rs = sqlHelper.executeQuery(sql,"%"+name+"%");
        return rs;
    }
    public static ResultSet FindByNameOrID(String name){
        String sql = "select * from NguoiThue where TenNguoiThue = ? or MaNguoiThue = ? ";//like tìm gần giống , = tìm chính xác
        return sqlHelper.executeQuery(sql,name,name);
    }
    public static ResultSet FindByNameAndAddr(String name, String addr){
        String sql = "select * from NguoiThue where TenNguoiThue = ? and DiaChi = ? ";//like tìm gần giống , = tìm chính xác
        return sqlHelper.executeQuery(sql,name,addr);
    }
    public static ResultSet FindBySDT(String name){
        String sql = "select * from NguoiThue where SDT = ? ";//like tìm gần giống , = tìm chính xác
        return sqlHelper.executeQuery(sql,name);
    }
    public static ResultSet FindByCMND(String name){
        String sql = "select * from NguoiThue where CMND = ? ";//like tìm gần giống , = tìm chính xác
        return sqlHelper.executeQuery(sql,name);
    }
    public static ResultSet FindByTrangThai(String so){
        String sql = "select * from NguoiThue where TrangThai = ? ";//like tìm gần giống , = tìm chính xác
        return sqlHelper.executeQuery(sql,so);
    }
    public static ResultSet FindByMaNguoiThue(String MaNguoiThue){
        String sql = "select * from NguoiThue where MaNguoiThue = ? ";// = tìm chính xác
        return sqlHelper.executeQuery(sql,MaNguoiThue);
    }
    public static void Insert(KhachThue kh){
         //set dateformat DMY + insert into
        String sql =" INSERT INTO [dbo].[NguoiThue]([MaNguoiThue],[TenNguoiThue],[CMND],[SDT],[Email],[DiaChi]" +
"           ,[NgaySinh],[GioiTinh],[NgayTaoDT],[TrangThai],[AnhChanDung],[AnhCCTruoc],[AnhCCSau])" +
"     VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        sqlHelper.executeUpdate(sql, kh.getMaNguoiThue(),kh.getTenNguoiThue(),kh.getCMND(),kh.getSDT(),kh.getEmail()
                , kh.getDiaChi(),kh.getNgaySinh() ,kh.isGioiTinh(), kh.getNgayTaoDT(),kh.isTrangThai()
        ,kh.getAnhCD(),kh.getAnhCCTruoc(),kh.getAnhCCSau());
    }
    public static void Update(KhachThue kh){
        String sql ="UPDATE [dbo].[NguoiThue]SET [TenNguoiThue] = ?,[CMND] = ? ,[SDT] = ? ,[Email]=?,[DiaChi] = ?" +
"     ,[NgaySinh] = ?,[GioiTinh]=?,[NgayTaoDT] = ?WHERE MaNguoiThue = ?";
        sqlHelper.executeUpdate(sql,kh.getTenNguoiThue(), kh.getCMND(),kh.getSDT(),kh.getEmail(),kh.getDiaChi(),
                 kh.getNgaySinh(),kh.isGioiTinh(), kh.getNgayTaoDT(),kh.getMaNguoiThue());
    }
    public static void UpdateDaiDien(KhachThue kh){
        String sql ="UPDATE [dbo].[HoatDongThuePhong]SET [DaiDien]=? WHERE MaNguoiThue = ?";
        sqlHelper.executeUpdate(sql,kh.isDaiDien(),kh.getMaNguoiThue());
    }
    public static void Delete(String MaKhachHang){
        String sql =" if(select TrangThai from NguoiThue where MaNguoiThue=?)=0 begin "
                + "DELETE FROM [dbo].[HoatDongThuePhong] WHERE MaNguoiThue =?"
                + "DELETE FROM [dbo].[NguoiThue] WHERE MaNguoiThue =? end";
        sqlHelper.executeUpdate(sql, MaKhachHang, MaKhachHang, MaKhachHang);
    }
    
    public static ResultSet CountNguoiThueTrangThai(int So) {
        String sql = "select count(*) from  NguoiThue where TrangThai = ?";
        return sqlHelper.executeQuery(sql, So);
    }
    
    
    
    
}
