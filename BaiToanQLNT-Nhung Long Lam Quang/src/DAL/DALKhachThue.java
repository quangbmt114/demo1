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
        String sql = "select * from NguoiThue where MaNguoiThue = ? ";//like tìm gần giống , = tìm chính xác
        return sqlHelper.executeQuery(sql,MaNguoiThue);
    }
    public static void Insert(KhachThue kh){
         //set dateformat DMY + insert into
        String sql =" INSERT INTO [dbo].[NguoiThue]([MaNguoiThue],[TenNguoiThue],[CMND],[SDT],[DiaChi],[NgaySinh]" +
"           ,[NgayTaoDT],[TrangThai])VALUES (?,?,?,?,?,?,?,?)";
        sqlHelper.executeUpdate(sql, kh.getMaNguoiThue(),kh.getTenNguoiThue(),kh.getCMND(),kh.getSDT()
                , kh.getDiaChi(),kh.getNgaySinh() , kh.getNgayTaoDT(),kh.getTrangThai());
    }
    public static void Update(KhachThue kh){
        String sql ="UPDATE [dbo].[NguoiThue]SET [TenNguoiThue] = ?,[CMND] = ? ,[SDT] = ? ,[DiaChi] = ?" +
"     ,[NgaySinh] = ?,[NgayTaoDT] = ?,[TrangThai] = ? WHERE MaNguoiThue = ?";
        sqlHelper.executeUpdate(sql,kh.getTenNguoiThue(), kh.getCMND(),kh.getSDT(),kh.getDiaChi(),
                 kh.getNgaySinh(), kh.getNgayTaoDT(),kh.getTrangThai(),kh.getMaNguoiThue());
    }
    public static void Delete(String MaKhachHang){
        String sql ="DELETE FROM [dbo].[NguoiThue] WHERE MaNguoiThue =?";
        sqlHelper.executeUpdate(sql, MaKhachHang);
    }
    
    public static ResultSet CountNguoiThueTrangThai(int So) {
        String sql = "select count(*) from  NguoiThue where TrangThai = ?";
        return sqlHelper.executeQuery(sql, So);
    }
    
    
    
    
}
