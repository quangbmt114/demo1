/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author cuong
 */
public class HoatDongThuePhong {

    String MaHopDong;
    String MaPhong;
    String MaNguoiThue;
    Date NgayThue;
    Date NgayTra;
    String GhiChu;
    boolean TinhTrang;
    boolean DaiDien;

    public HoatDongThuePhong() {
    }

    public HoatDongThuePhong(String MaHopDong, String MaPhong, String MaNguoiThue, Date NgayThue, Date NgayTra, String GhiChu, boolean TinhTrang, boolean DaiDien) {
        this.MaHopDong = MaHopDong;
        this.MaPhong = MaPhong;
        this.MaNguoiThue = MaNguoiThue;
        this.NgayThue = NgayThue;
        this.NgayTra = NgayTra;
        this.GhiChu = GhiChu;
        this.TinhTrang = TinhTrang;
        this.DaiDien = DaiDien;
    }

    public HoatDongThuePhong(String MaHopDong, String MaPhong, String MaNguoiThue, Date NgayThue, Date NgayTra, String GhiChu, boolean TinhTrang) {
        this.MaHopDong = MaHopDong;
        this.MaPhong = MaPhong;
        this.MaNguoiThue = MaNguoiThue;
        this.NgayThue = NgayThue;
        this.NgayTra = NgayTra;
        this.GhiChu = GhiChu;
        this.TinhTrang = TinhTrang;
    }

    public String getMaHopDong() {
        return MaHopDong;
    }

    public boolean isDaiDien() {
        return DaiDien;
    }

    public void setDaiDien(boolean DaiDien) {
        this.DaiDien = DaiDien;
    }

    public void setMaHopDong(String MaHopDong) {
        this.MaHopDong = MaHopDong;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public String getMaNguoiThue() {
        return MaNguoiThue;
    }

    public void setMaNguoiThue(String MaNguoiThue) {
        this.MaNguoiThue = MaNguoiThue;
    }

    public Date getNgayThue() {
        return NgayThue;
    }

    public void setNgayThue(Date NgayThue) {
        this.NgayThue = NgayThue;
    }

    public Date getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(Date NgayTra) {
        this.NgayTra = NgayTra;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public boolean isTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(boolean TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

}
