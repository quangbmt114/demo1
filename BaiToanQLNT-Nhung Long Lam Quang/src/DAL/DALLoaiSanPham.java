/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.LoaiSanPham;
import helper.sqlHelper;
import java.sql.*;
/**
 *
 * @author cuong
 */
public class DALLoaiSanPham {
    
    //Hàm lấy tất cả dữ liệu
    public static ResultSet GetAllData(){
       String sql = "select * from LoaiSanPham";
       ResultSet rs = sqlHelper.executeQuery(sql);
       return rs;
    }
    
    //Hàm tìm kiếm Loại sản phẩm theo tên
    public static ResultSet FindByName(String tenLoai){
        String sql = "select * from LoaiSanPham where TenLoai = ?";
        return sqlHelper.executeQuery(sql, tenLoai);
    }
    
    //Hàm tìm kiếm Loại sản phẩm theo tên hoặc Mã loại 
    public static ResultSet FindByNameOrByID(String tuKhoa){
        String sql = "select * from LoaiSanPham where TenLoai like ? or MaLoaiSP = ?";
        return sqlHelper.executeQuery(sql, "%" + tuKhoa + "%", tuKhoa);
    }
    
    //Hàm thêm loại sản phẩm
    public static void Insert(LoaiSanPham lsp){
        String sql = "INSERT INTO [dbo].[LoaiSanPham]([TenLoai],[MoTa]) VALUES(?,?)";
        sqlHelper.executeUpdate(sql, lsp.getTenLoai(), lsp.getMoTa());
    }
    
    //Hàm sửa loại sản phẩm
    public static void Update(LoaiSanPham lsp){
        String sql = "UPDATE [dbo].[LoaiSanPham] SET [TenLoai] = ?,[MoTa] = ? "
                + "WHERE MaLoaiSP = ?";
        sqlHelper.executeUpdate(sql, lsp.getTenLoai(), lsp.getMoTa(), lsp.getMaLoaiSP());
    }
    //Hàm xóa loại sản phẩm
    public static void Delete(int MaLoaiSP){
        String sql = "DELETE LoaiSanPham where MaLoaiSP = ?";
        sqlHelper.executeUpdate(sql, MaLoaiSP);
    }
    
    
}
