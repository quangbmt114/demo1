/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Dell
 */
public class HoaDonPhongTro {
	String MaHoaDon,MaPhong ;
        Date ThangTieuThu;
        String ChiSoMoi,ChiSoCu;
        int TongTienPhong,TongTienDien ,TongTienNuoc,TongTienDV,TongTien;
        String GhiChu;

    public HoaDonPhongTro() {
    }

    public HoaDonPhongTro(String MaHoaDon, String MaPhong, Date ThangTieuThu, String ChiSoMoi, String ChiSoCu, int TongTienPhong, int TongTienDien, int TongTienNuoc, int TongTienDV, int TongTien, String GhiChu) {
        this.MaHoaDon = MaHoaDon;
        this.MaPhong = MaPhong;
        this.ThangTieuThu = ThangTieuThu;
        this.ChiSoMoi = ChiSoMoi;
        this.ChiSoCu = ChiSoCu;
        this.TongTienPhong = TongTienPhong;
        this.TongTienDien = TongTienDien;
        this.TongTienNuoc = TongTienNuoc;
        this.TongTienDV = TongTienDV;
        this.TongTien = TongTien;
        this.GhiChu = GhiChu;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public Date getThangTieuThu() {
        return ThangTieuThu;
    }

    public void setThangTieuThu(Date ThangTieuThu) {
        this.ThangTieuThu = ThangTieuThu;
    }

    public String getChiSoMoi() {
        return ChiSoMoi;
    }

    public void setChiSoMoi(String ChiSoMoi) {
        this.ChiSoMoi = ChiSoMoi;
    }

    public String getChiSoCu() {
        return ChiSoCu;
    }

    public void setChiSoCu(String ChiSoCu) {
        this.ChiSoCu = ChiSoCu;
    }

    public int getTongTienPhong() {
        return TongTienPhong;
    }

    public void setTongTienPhong(int TongTienPhong) {
        this.TongTienPhong = TongTienPhong;
    }

    public int getTongTienDien() {
        return TongTienDien;
    }

    public void setTongTienDien(int TongTienDien) {
        this.TongTienDien = TongTienDien;
    }

    public int getTongTienNuoc() {
        return TongTienNuoc;
    }

    public void setTongTienNuoc(int TongTienNuoc) {
        this.TongTienNuoc = TongTienNuoc;
    }

    public int getTongTienDV() {
        return TongTienDV;
    }

    public void setTongTienDV(int TongTienDV) {
        this.TongTienDV = TongTienDV;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
        
}
