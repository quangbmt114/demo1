/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

/**
 *
 * @author ASUS VIVOBOOK
 */
public class KhachThue {
   String  MaNguoiThue;
   String TenNguoiThue;
   String CMND;
   String SDT;
   String DiaChi;
   String NgaySinh;
   Image AnhCD;
   String NgayTaoDT;
//   boolean GioiTinh;
   boolean TrangThai;

    public KhachThue() {
    }

    public KhachThue(String MaNguoiThue, String TenNguoiThue, String CMND, String SDT, String DiaChi, String NgaySinh, String NgayTaoDT, boolean TrangThai,Image AnhCD) {
        this.MaNguoiThue = MaNguoiThue;
        this.TenNguoiThue = TenNguoiThue;
        this.CMND = CMND;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.NgaySinh = NgaySinh;
        this.NgayTaoDT = NgayTaoDT;
//        this.GioiTinh = GioiTinh;
        this.TrangThai = TrangThai;
        this.AnhCD = AnhCD;
    }

    public KhachThue(String MaNguoiThue, String TenNguoiThue, String CMND, String SDT, String DiaChi, String NgaySinh, String NgayTaoDT, boolean TrangThai) {
        this.MaNguoiThue = MaNguoiThue;
        this.TenNguoiThue = TenNguoiThue;
        this.CMND = CMND;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.NgaySinh = NgaySinh;
        this.NgayTaoDT = NgayTaoDT;
//        this.GioiTinh = GioiTinh;
        this.TrangThai = TrangThai;
    }

    public KhachThue(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Image getAnhCD() {
        return AnhCD;
    }

    public void setAnhCD(Image AnhCD) {
        this.AnhCD = AnhCD;
    }

    public String getMaNguoiThue() {
        return MaNguoiThue;
    }

    public void setMaNguoiThue(String MaNguoiThue) {
        this.MaNguoiThue = MaNguoiThue;
    }

    public String getTenNguoiThue() {
        return TenNguoiThue;
    }

    public void setTenNguoiThue(String TenNguoiThue) {
        this.TenNguoiThue = TenNguoiThue;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getNgayTaoDT() {
        return NgayTaoDT;
    }

    public void setNgayTaoDT(String NgayTaoDT) {
        this.NgayTaoDT = NgayTaoDT;
    }

//   public boolean getGioiTinh() {
//        return GioiTinh;
//    }
//
//
//    public void setGioiTinh(boolean GioiTinh) {
//        this.GioiTinh = GioiTinh;
//    }

    public boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    

}
