/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.LoaiPhong;
import helper.sqlHelper;
import java.sql.ResultSet;

/**
 *
 * @author quang nguyen
 */
public class DALLoaiPhong {
    public static ResultSet GetAllData(){
       String sql = "select * from LoaiPhong";
       ResultSet rs = sqlHelper.executeQuery(sql);
       return rs;
    }
    public static void Create(LoaiPhong lp){
        String sql = "INSERT INTO [dbo].[LoaiPhong]" +
"           ([MaLoaiPhong]" +
"           ,[TenLoaiPhong]" +
"           ,[GiaPhong]" +
"           ,[GiaDien]" +
"           ,[GiaNuoc])" +
"     VALUES" +
"         (?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?)";
        sqlHelper.executeUpdate(sql, lp.getMaLoaiPhong(),lp.getTenLoaiPhong(),lp.getGiaPhong(),lp.getGiaDien(),lp.getGiaNuoc());
    }
    public static void Delete(String IDLoaiPhong){
        String sql = "delete from [dbo].[LoaiPhong] where MaLoaiPhong = ? ";
        sqlHelper.executeUpdate(sql, IDLoaiPhong);
    }
    public static void Update(LoaiPhong lp){
        String sql = "UPDATE [dbo].[LoaiPhong]" +
"   SET [TenLoaiPhong] = ?,[GiaPhong] = ?,[GiaDien] = ?,[GiaNuoc] = ? WHERE MaLoaiPhong = ?";
       sqlHelper.executeUpdate(sql,lp.getTenLoaiPhong()
       ,lp.getGiaPhong(),lp.getGiaDien(),lp.getGiaNuoc(),lp.getMaLoaiPhong());
    }
    
    public static ResultSet FindByMaLoaiPhong(String MaLoaiPhong){
        String sql = "select * from LoaiPhong where MaLoaiPhong = ? ";//like tìm gần giống , = tìm chính xác
        return sqlHelper.executeQuery(sql,MaLoaiPhong);
    }
    
    public static ResultSet FindByTenLoaiPhong(String TenLoaiPhong){
        String sql = "select * from LoaiPhong where TenLoaiPhong = ? ";//like tìm gần giống , = tìm chính xác
        return sqlHelper.executeQuery(sql,TenLoaiPhong);
    }
    
    
     
}
