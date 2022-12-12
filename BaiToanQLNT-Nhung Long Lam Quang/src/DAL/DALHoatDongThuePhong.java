/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.HoatDongThuePhong;
import helper.ChuyenDoi;
import helper.sqlHelper;
import java.sql.*;

/**
 *
 * @author cuong
 */
public class DALHoatDongThuePhong {

    //Lay 
    public static ResultSet GetAllData() {
        String sql = "select * from HoatDongThuePhong";
        return sqlHelper.executeQuery(sql);
    }

    //Ham tim kiem
    public static ResultSet FindByMaHopDong(String tuKhoa) {
        String sql = "select * from HoatDongThuePhong "
                + " where MaHopDong like ?";
        tuKhoa = "%" + tuKhoa + "%";
        return sqlHelper.executeQuery(sql, tuKhoa);
    }

    public static ResultSet FindMaHopDong(String tuKhoa) {
        String sql = "select * from HoatDongThuePhong "
                + " where MaHopDong = ?";
        return sqlHelper.executeQuery(sql, tuKhoa);
    }

    public static ResultSet FindByMaNguoiThue(String tuKhoa) {
        String sql = "select * from HoatDongThuePhong "
                + " where MaNguoiThue like ?";
        tuKhoa = "%" + tuKhoa + "%";
        return sqlHelper.executeQuery(sql, tuKhoa);
    }

    public static ResultSet FindByMaPhong(String MaPhong) {
        String sql = "select * from HoatDongThuePhong where MaPhong = ?";
        return sqlHelper.executeQuery(sql, MaPhong);
    }

    public static ResultSet FindDaiDienPhong(String MaPhong) {
        String sql = "select * from HoatDongThuePhong where MaPhong = ? and TinhTrang=1 and DaiDien = 1";
        return sqlHelper.executeQuery(sql, MaPhong);
    }

    public static ResultSet FindByMaHopDongOrMaNguoiThue(String tuKhoa) {
        String sql = "select * from HoatDongThuePhong "
                + " where MaHopDong like ? or MaNguoiThue like ?";
        tuKhoa = "%" + tuKhoa + "%";
        return sqlHelper.executeQuery(sql, tuKhoa, tuKhoa);
    }

    public static void Add(HoatDongThuePhong sp, int i) {
        if (i == 0) {
            String sql = "INSERT INTO [dbo].[HoatDongThuePhong]([MaHopDong],[MaPhong],[MaNguoiThue],[NgayThue],[NgayTra],[GhiChu],[TinhTrang]) VALUES(?,?,?,?,?,?,?)";
            sqlHelper.executeUpdate(sql, sp.getMaHopDong(), sp.getMaPhong(), sp.getMaNguoiThue(), ChuyenDoi.LayNgayString(sp.getNgayThue()), ChuyenDoi.LayNgayString(sp.getNgayTra()),
                    sp.getGhiChu(), 0);
        } else {
            String sql = "INSERT INTO [dbo].[HoatDongThuePhong]([MaHopDong],[MaPhong],[MaNguoiThue],[NgayThue],[GhiChu],[TinhTrang]) VALUES(?,?,?,?,?,?)";
            sqlHelper.executeUpdate(sql, sp.getMaHopDong(), sp.getMaPhong(), sp.getMaNguoiThue(), ChuyenDoi.LayNgayString(sp.getNgayThue()),
                    sp.getGhiChu(), (sp.isTinhTrang() ? 1 : 0));
            sql = "UPDATE [dbo].[NguoiThue] SET [TrangThai] = 1 WHERE MaNguoiThue = ?";
            sqlHelper.executeUpdate(sql, sp.getMaNguoiThue());
            sql = "UPDATE [dbo].[PhongTro] set[TrangThai] = 1 WHERE MaPhong=?";
            sqlHelper.executeUpdate(sql, sp.getMaPhong());
            sql = " if (select count(*) from hoatdongthuephong where maphong= ? and tinhtrang=1)=1 UPDATE [dbo].[hoatdongthuephong] set[DaiDien] = 1 WHERE MaHopDong=?";
            sqlHelper.executeUpdate(sql, sp.getMaPhong(), sp.getMaHopDong());
        }

//        System.out.println(sp.getMaHopDong() + sp.getMaPhong() + sp.getMaNguoiThue()
//                + ChuyenDoi.LayNgayString(sp.getNgayThue())
//                + ChuyenDoi.LayNgayString(sp.getNgayTra()) + sp.getGhiChu() + (sp.isTinhTrang() ? 0 : 1));
    }

    public static void Update(HoatDongThuePhong sp, int i) {
        String sql;
        if (i == 0) {
            sql = "if (select count(*) from hoatdongthuephong where MaNguoiThue= ? and tinhtrang=1)=1 UPDATE [dbo].[NguoiThue] SET [TrangThai] = 0 WHERE MaNguoiThue = ?";
            sqlHelper.executeUpdate(sql, sp.getMaNguoiThue(), sp.getMaNguoiThue());
            sql = " if (select count(*) from hoatdongthuephong where maphong= ? and tinhtrang=1)=1 UPDATE [dbo].[PhongTro] set[TrangThai] = 0 WHERE MaPhong=?";
            sqlHelper.executeUpdate(sql, sp.getMaPhong(), sp.getMaPhong());

            sql = "UPDATE [dbo].[HoatDongThuePhong] SET [MaPhong] = ? ,[MaNguoiThue] = ? ,[NgayThue] =? ,[NgayTra] =?,[GhiChu] = ?,[TinhTrang] = ? WHERE MaHopDong = ?";
            sqlHelper.executeUpdate(sql, sp.getMaPhong(), sp.getMaNguoiThue(),
                    ChuyenDoi.LayNgayString(sp.getNgayThue()), ChuyenDoi.LayNgayString(sp.getNgayTra()), sp.getGhiChu(),
                    0, sp.getMaHopDong());
        } else {

//update HoatDongThuePhong set NgayTra=null where MaHopDong='PA0300000000001'
            sql = "update HoatDongThuePhong set NgayTra=null where MaHopDong=?";
            sqlHelper.executeUpdate(sql, sp.getMaHopDong());
            sql = "if (select count(*) from hoatdongthuephong where MaNguoiThue= ? and tinhtrang=1)=0 UPDATE [dbo].[NguoiThue] SET [TrangThai] = 1 WHERE MaNguoiThue = ?";
            sqlHelper.executeUpdate(sql, sp.getMaNguoiThue(), sp.getMaNguoiThue());
            sql = "if (select count(*) from hoatdongthuephong where maphong= ? and tinhtrang=1)=0 UPDATE [dbo].[PhongTro] set[TrangThai] = 1 WHERE MaPhong=?";
            sqlHelper.executeUpdate(sql, sp.getMaPhong(), sp.getMaPhong());
            sql = "UPDATE [dbo].[HoatDongThuePhong] SET [MaPhong] = ? ,[MaNguoiThue] = ? ,[NgayThue] =? ,[GhiChu] = ?,[TinhTrang] = ? WHERE MaHopDong = ?";
            sqlHelper.executeUpdate(sql, sp.getMaPhong(), sp.getMaNguoiThue(),
                    ChuyenDoi.LayNgayString(sp.getNgayThue()), sp.getGhiChu(),
                    1, sp.getMaHopDong());
        }

//        String sql = "UPDATE [dbo].[HoatDongThuePhong] SET [MaPhong] = ? ,[MaNguoiThue] = ? ,[NgayThue] =? ,[NgayTra] =?,[GhiChu] = ?,[TinhTrang] = ? WHERE MaHopDong = ?";
//        sqlHelper.executeUpdate(sql, sp.getMaPhong(), sp.getMaNguoiThue(),
//                sp.getNgayThue(), sp.getNgayTra(), sp.getGhiChu(),
//                sp.isTinhTrang(), sp.getMaHopDong());
    }

    public static void Delete(String getMaHopDong, String maPhong, String maKhachThue) {
        String sql = "if (select count(*) from hoatdongthuephong where MaNguoiThue= ? and tinhtrang=1)=1 UPDATE [dbo].[NguoiThue] SET [TrangThai] = 0 WHERE MaNguoiThue = ?";
        sqlHelper.executeUpdate(sql, maKhachThue, maKhachThue);
        sql = " if (select count(*) from hoatdongthuephong where maphong= ? and tinhtrang=1)=1 UPDATE [dbo].[PhongTro] set[TrangThai] = 0 WHERE MaPhong=?";
        sqlHelper.executeUpdate(sql, maPhong, maPhong);
        sql = "DELETE HoatDongThuePhong where MaHopDong = ?";
        sqlHelper.executeUpdate(sql, getMaHopDong);
    }

    public static ResultSet CountSoHoaDon(String SoHoaDon) {
        String sql = "select count(*) from  HoatDongThuePhong where MaHopDong like ?";
        return sqlHelper.executeQuery(sql, SoHoaDon);
    }

    public static ResultSet GetHoaDonBySo(String SoHoaDon) {
        String sql = "select * from HoatDongThuePhong where MaHopDong = ?";
        return sqlHelper.executeQuery(sql, SoHoaDon);
    }

}
