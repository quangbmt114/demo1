/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.*;
import helper.ChuyenDoi;
import helper.sqlHelper;
import java.sql.ResultSet;

/**
 *
 * @author quang
 */
public class DALChiSoDienNuoc {

    //Lấy thông tin từ CSDL
    public static ResultSet GetAllData() {
        String sql = "select * from ChiSoDienNuoc";
        return sqlHelper.executeQuery(sql);
    }

    public static void Add(ChiSoDienNuoc cs) {
        String sql = "INSERT INTO [dbo].[ChiSoDienNuoc]([MaChiSo],[MaPhong],[SoDien],[SoNuoc],[NgayGhi]) "
                + "     VALUES(?,?,?,?,?)";
        sqlHelper.executeUpdate(sql, cs.getMaChiSo(), cs.getMaPhong(), cs.getSoDien(), cs.getSoNuoc(), ChuyenDoi.LayNgayString(cs.getNgayGhi()));
    }

    public static void Update(ChiSoDienNuoc cs) {
        String sql = "UPDATE [dbo].[ChiSoDienNuoc] SET [MaPhong] = ?,[SoDien] = ?,[SoNuoc] = ?,[NgayGhi] = ? WHERE  [MaChiSo] = ?" ;
       sqlHelper.executeUpdate(sql, cs.getMaPhong(), cs.getSoDien(), cs.getSoNuoc(), ChuyenDoi.LayNgayString(cs.getNgayGhi()), cs.getMaChiSo());
    }

    public static void Delete(int MaChiSoDienNuoc) {
        String sql = "DELETE ChiSoDienNuoc where MaChiSo= ?";
        sqlHelper.executeUpdate(sql, MaChiSoDienNuoc);
    }

}
