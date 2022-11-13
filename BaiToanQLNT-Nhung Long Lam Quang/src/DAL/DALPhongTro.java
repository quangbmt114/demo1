/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;
import DTO.*;
import helper.sqlHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author quang nguyen
 */
public class DALPhongTro {
    public static ResultSet GetAll(){
       String sql = "select * from PhongTro";
       ResultSet rs = helper.sqlHelper.executeQuery(sql);
       return rs;
    }
    public static void Create(DTO.PhongTro pt){
        String sql = "INSERT INTO [dbo].[PhongTro]" +
"           ([MaPhong]" +
"           ,[TenPhong]" +
"           ,[MaLoaiPhong]" +
"           ,[MaKhuTro]" +
"           ,[ViTri]" +
"           ,[DienTich]" +
"           ,[Giuong]" +
"           ,[Ban]" +
"           ,[BongDen]" +
"           ,[KinhCua]" +
"           ,[TrangThai])" +
"     VALUES" +
"           (?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?)";
        helper.sqlHelper.executeUpdate(sql, pt.getMaPhong(),pt.getTenPhong(),pt.getMaLoaiPhong()
        ,pt.getMaKhuTro(),pt.getViTri(),pt.getDienTich(),pt.getGiuong(),pt.getBan(),pt.getBongDen(),
        pt.getKinhCua(),null,pt.isTrangThai());
    }
    
    public static void Update(DTO.PhongTro pt){
        String sql ="UPDATE [dbo].[PhongTro]" +
"   SET " +
"      [TenPhong] = ?" +
"      ,[MaLoaiPhong] = ?" +
"      ,[MaKhuTro] = ?" +
"      ,[ViTri] = ?" +
"      ,[DienTich] = ?" +
"      ,[Giuong] = ?" +
"      ,[Ban] = ?" +
"      ,[BongDen] = ?" +
"      ,[KinhCua] = ?" +
"      ,[TrangThai] = ?" +
" WHERE MaPhong=?";
        helper.sqlHelper.executeUpdate(sql,pt.getTenPhong(),pt.getMaLoaiPhong()
        ,pt.getMaKhuTro(),pt.getViTri(),pt.getDienTich(),pt.getGiuong(),pt.getBan(),pt.getBongDen(),
        pt.getKinhCua(),null,pt.isTrangThai(),pt.getMaPhong());
    }
    public static void Delete(DTO.PhongTro pt){
        String sql = "delete from [dbo].[PhongTro] where MaPhong = ? ";
        helper.sqlHelper.executeUpdate(sql, pt.getMaPhong());
    }
    public static ResultSet FindByMaPhong(String name){
        String sql = "select * from PhongTro where MaPhong = ? ";//like tìm gần giống , = tìm chính xác
        return sqlHelper.executeQuery(sql,name);
    }
}
