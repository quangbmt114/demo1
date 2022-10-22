/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

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
    public static void Create(DTO.LoaiPhong lp){
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
    public static void Delete(DTO.LoaiPhong lp){
        String sql = "delete from [dbo].[LoaiPhong] where MaLoaiPhong = ? ";
        sqlHelper.executeUpdate(sql, lp.getMaLoaiPhong());
    }
    public static void Update(DTO.LoaiPhong lp){
        String sql = "UPDATE [dbo].[LoaiPhong]" +
"   SET [MaLoaiPhong] = ?" +
"      ,[TenLoaiPhong] = ?" +
"      ,[GiaPhong] = ?" +
"      ,[GiaDien] = ?" +
"      ,[GiaNuoc] = ?" +
" WHERE MaLoaiPhong = ? ";
       sqlHelper.executeUpdate(sql, lp.getMaLoaiPhong(),lp.getTenLoaiPhong()
       ,lp.getGiaPhong(),lp.getGiaDien(),lp.getGiaNuoc(),lp.getMaLoaiPhong());
    }
    public static ResultSet FindByMaLoaiPhong(String MaNguoiThue){
        String sql = "select * from LoaiPhong where MaLoaiPhong = ? ";//like tìm gần giống , = tìm chính xác
        return sqlHelper.executeQuery(sql,MaNguoiThue);
    }
}
