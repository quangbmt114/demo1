/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.CTDV;
import helper.sqlHelper;
import java.sql.*;
/**
 *
 * @author cuong
 */
public class DALCTDV {
    
    //Hàm lấy tất cả dữ liệu
    public static ResultSet GetAllData(){
       String sql = "select * from CTDV";
       ResultSet rs = sqlHelper.executeQuery(sql);
       return rs;
    }
    
    //Hàm tìm kiếm Loại sản phẩm theo tên
    public static ResultSet FindByMaPhong(String MaPhong){
        String sql = "select * from CTDV where MaPhong = ?";
        return sqlHelper.executeQuery(sql, MaPhong);
    }
    
    //Hàm tìm kiếm Loại sản phẩm theo tên hoặc Mã loại 
    public static ResultSet FindByMaPhongandMaDichVu(String MaDichVu,String MaPhong){
        String sql = "select * from CTDV where MaDichVu = ? and MaPhong = ?";
        return sqlHelper.executeQuery(sql,MaDichVu, MaPhong);
    }
    
    public static ResultSet SumTienByMaPhong(String MaPhong){
        String sql = "select sum(giadichvu) as TongTien from ctdv inner join dichvu on ctdv.MaDichVu=DichVu.MaDichVu where ctdv.MaPhong=?";
        return sqlHelper.executeQuery(sql, MaPhong);
    }
    //Hàm thêm loại sản phẩm
    public static void Insert(CTDV lsp){
        String sql = "INSERT INTO [dbo].[CTDV]([MaPhong],[MaDichVu]) VALUES(?,?)";
        sqlHelper.executeUpdate(sql, lsp.getMaPhong(), lsp.getMaDichVu());
    }
    
    //Hàm sửa loại sản phẩm
    public static void Update(CTDV lsp){
        String sql = "UPDATE [dbo].[CTDV] SET [MaPhong] = ? WHERE MaPhong = ?";
        sqlHelper.executeUpdate(sql, lsp.getMaDichVu(), lsp.getMaPhong());
    }
    //Hàm xóa loại sản phẩm
    public static void Delete(String MaDichVu,String MaPhong){
        String sql = "DELETE CTDV where MaDichVu = ? and MaPhong = ?";
        sqlHelper.executeUpdate(sql,MaDichVu, MaPhong);
    }
    
    
}
