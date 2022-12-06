/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.*;
import helper.*;
import java.sql.ResultSet;

/**
 *
 * @author Dell
 */
public class DALHoaDonPhongTro {

    //Lay 
    public static ResultSet GetAllData() {
        String sql = "select * from HoaDon";
        return sqlHelper.executeQuery(sql);
    }

    //Ham tim kiem
    public static ResultSet FindByMaHoaDon(String tuKhoa) {
        String sql = "select * from HoaDon "
                + " where MaHoaDon like ?";
        tuKhoa = "%" + tuKhoa + "%";
        return sqlHelper.executeQuery(sql, tuKhoa);
    }

    public static ResultSet FindByMaPhong(String MaPhong) {
        String sql = "select * from HoaDon where MaPhong = ?";
        return sqlHelper.executeQuery(sql, MaPhong);
    }

    public static void Add(HoaDonPhongTro hdpt) {

        String sql = "INSERT INTO [dbo].[HoaDon] ([MaHoaDon],[MaPhong],[ThangTieuThu],[ChiSoMoi],[ChiSoCu],[TongTienPhong],[TongTienDien],[TongTienNuoc],[TongTienDV],[TongTien],[GhiChu]) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        sqlHelper.executeUpdate(sql, hdpt.getMaHoaDon(), hdpt.getMaPhong(), ChuyenDoi.LayNgayString(hdpt.getThangTieuThu()), hdpt.getChiSoMoi(), hdpt.getChiSoCu(),
                hdpt.getTongTienPhong(), hdpt.getTongTienDien(), hdpt.getTongTienNuoc(), hdpt.getTongTienDV(), hdpt.getTongTien(), hdpt.getGhiChu());
    }

    public static void Update(HoaDonPhongTro hdpt) {
        String sql;
        sql = "UPDATE [dbo].[HoaDon]\n"
                + "   SET [MaPhong] = ?,[ThangTieuThu] = ?,[ChiSoMoi] = ?,[ChiSoCu] = ?,[TongTienPhong] = ?,[TongTienDien] = ?,\n"
                + "   [TongTienNuoc] = ?,[TongTienDV] = ?,[TongTien] = ?,[GhiChu] = ? WHERE [MaHoaDon] = ?";
        sqlHelper.executeUpdate(sql, hdpt.getMaPhong(), ChuyenDoi.LayNgayString(hdpt.getThangTieuThu()), hdpt.getChiSoMoi(), hdpt.getChiSoCu(),
                hdpt.getTongTienPhong(), hdpt.getTongTienDien(), hdpt.getTongTienNuoc(), hdpt.getTongTienDV(), hdpt.getTongTien(), hdpt.getGhiChu(), hdpt.getMaHoaDon());
    }
//    public static void Add(HoaDonPhongTro hdpt, int i) {
//        if (i == 0) {
//            String sql = "INSERT INTO [dbo].[HoaDon]([MaHoaDon],[MaPhong],[MaNguoiThue],[NgayThue],[NgayTra],[GhiChu],[TinhTrang]) VALUES(?,?,?,?,?,?,?)";
//            sqlHelper.executeUpdate(sql, hdpt.getMaHoaDon(), hdpt.getMaPhong(), hdpt.getMaNguoiThue(), ChuyenDoi.LayNgayString(hdpt.getNgayThue()), ChuyenDoi.LayNgayString(hdpt.getNgayTra()),
//                    hdpt.getGhiChu(), (hdpt.isTinhTrang() ? 1 : 0));
//            sql = "UPDATE [dbo].[NguoiThue] SET [TrangThai] = 0 WHERE MaNguoiThue = ?";
//            sqlHelper.executeUpdate(sql, hdpt.getMaNguoiThue());
//            sql = "UPDATE [dbo].[PhongTro] set[TrangThai] = 0 WHERE MaPhong=?";
//            sqlHelper.executeUpdate(sql, hdpt.getMaPhong());
//        } else {
//            String sql = "INSERT INTO [dbo].[HoaDon]([MaHoaDon],[MaPhong],[MaNguoiThue],[NgayThue],[GhiChu],[TinhTrang]) VALUES(?,?,?,?,?,?)";
//            sqlHelper.executeUpdate(sql, hdpt.getMaHoaDon(), hdpt.getMaPhong(), hdpt.getMaNguoiThue(), ChuyenDoi.LayNgayString(hdpt.getNgayThue()),
//                    hdpt.getGhiChu(), (hdpt.isTinhTrang() ? 1 : 0));
//            sql = "UPDATE [dbo].[NguoiThue] SET [TrangThai] = 1 WHERE MaNguoiThue = ?";
//            sqlHelper.executeUpdate(sql, hdpt.getMaNguoiThue());
//            sql = "UPDATE [dbo].[PhongTro] set[TrangThai] = 1 WHERE MaPhong=?";
//            sqlHelper.executeUpdate(sql, hdpt.getMaPhong());
//        }
//
////        System.out.println(hdpt.getMaHoaDon() + hdpt.getMaPhong() + hdpt.getMaNguoiThue()
////                + ChuyenDoi.LayNgayString(hdpt.getNgayThue())
////                + ChuyenDoi.LayNgayString(hdpt.getNgayTra()) + hdpt.getGhiChu() + (hdpt.isTinhTrang() ? 0 : 1));
//    }

//    public static void Update(HoaDonPhongTro hdpt, int i) {
//        String sql;
//        if (i == 0) {
//
//            
//            sql = "UPDATE [dbo].[HoaDon] SET [MaPhong] = ? ,[MaNguoiThue] = ? ,[NgayThue] =? ,[NgayTra] =?,[GhiChu] = ?,[TinhTrang] = ? WHERE MaHoaDon = ?";
//            sqlHelper.executeUpdate(sql, hdpt.getMaPhong(), hdpt.getMaNguoiThue(),
//                    ChuyenDoi.LayNgayString(hdpt.getNgayThue()), ChuyenDoi.LayNgayString(hdpt.getNgayTra()), hdpt.getGhiChu(),
//                    (hdpt.isTinhTrang() ? 1 : 0), hdpt.getMaHoaDon());
//            sql = "UPDATE [dbo].[NguoiThue] SET [TrangThai] = 0 WHERE MaNguoiThue = ?";
//            sqlHelper.executeUpdate(sql, hdpt.getMaNguoiThue());
//            sql = "UPDATE [dbo].[PhongTro] set[TrangThai] = 0 WHERE MaPhong=?";
//            sqlHelper.executeUpdate(sql, hdpt.getMaPhong());
//        } else {
//
////update HoaDonPhongTro set NgayTra=null where MaHoaDon='PA0300000000001'
//            sql = "UPDATE [dbo].[HoaDon] SET [MaPhong] = ? ,[MaNguoiThue] = ? ,[NgayThue] =? ,[GhiChu] = ?,[TinhTrang] = ? WHERE MaHoaDon = ?";
//            sqlHelper.executeUpdate(sql, hdpt.getMaPhong(), hdpt.getMaNguoiThue(),
//                    ChuyenDoi.LayNgayString(hdpt.getNgayThue()), hdpt.getGhiChu(),
//                    (hdpt.isTinhTrang() ? 1 : 0), hdpt.getMaHoaDon());
//            sql = "update HoaDonPhongTro set NgayTra=null where MaHoaDon=?";
//            sqlHelper.executeUpdate(sql,hdpt.getMaHoaDon());
//            sql = "UPDATE [dbo].[NguoiThue] SET [TrangThai] = 1 WHERE MaNguoiThue = ?";
//            sqlHelper.executeUpdate(sql, hdpt.getMaNguoiThue());
//            sql = "UPDATE [dbo].[PhongTro] set[TrangThai] = 1 WHERE MaPhong=?";
//            sqlHelper.executeUpdate(sql, hdpt.getMaPhong());
//        }
//
////        String sql = "UPDATE [dbo].[HoaDon] SET [MaPhong] = ? ,[MaNguoiThue] = ? ,[NgayThue] =? ,[NgayTra] =?,[GhiChu] = ?,[TinhTrang] = ? WHERE MaHoaDon = ?";
////        sqlHelper.executeUpdate(sql, hdpt.getMaPhong(), hdpt.getMaNguoiThue(),
////                hdpt.getNgayThue(), hdpt.getNgayTra(), hdpt.getGhiChu(),
////                hdpt.isTinhTrang(), hdpt.getMaHoaDon());
//    }
    public static void Delete(int getMaHoaDon) {
        String sql = "DELETE HoaDonPhongTro where MaHoaDon = ?";
        sqlHelper.executeUpdate(sql, getMaHoaDon);
    }

    public static ResultSet CountSoHoaDon(String SoHoaDon) {
        String sql = "select count(*) from HoaDon where MaHoaDon like ?";
        return sqlHelper.executeQuery(sql, "%" + SoHoaDon + "%");
    }

    public static ResultSet GetHoaDonBySo(String SoHoaDon) {
        String sql = "select * from HoaDon where MaHoaDon = ?";
        return sqlHelper.executeQuery(sql, SoHoaDon);
    }

//    public static ResultSet FindByMaNguoiThue(String tuKhoa) {
//        String sql = "select * from HoaDon "
//                + " where MaNguoiThue like ?";
//        tuKhoa = "%" + tuKhoa + "%";
//        return sqlHelper.executeQuery(sql, tuKhoa);
//    }
//    public static ResultSet FindByMaHoaDonOrMaNguoiThue(String tuKhoa) {
//        String sql = "select * from HoaDon "
//                + " where MaHoaDon like ? or MaNguoiThue like ?";
//        tuKhoa = "%" + tuKhoa + "%";
//        return sqlHelper.executeQuery(sql, tuKhoa, tuKhoa);
//    }
}
