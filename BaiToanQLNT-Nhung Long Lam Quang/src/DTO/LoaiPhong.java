/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author quang nguyen
 */
public class LoaiPhong {
     String MaLoaiPhong,TenLoaiPhong;
     double GiaPhong;
     double GiaDien;
     double GiaNuoc;

    public LoaiPhong(String MaLoaiPhong, String TenLoaiPhong, double GiaPhong, double GiaDien, double GiaNuoc) {
        this.MaLoaiPhong = MaLoaiPhong;
        this.TenLoaiPhong = TenLoaiPhong;
        this.GiaPhong = GiaPhong;
        this.GiaDien = GiaDien;
        this.GiaNuoc = GiaNuoc;
    }

    public LoaiPhong() {
    }

    
    public String getMaLoaiPhong() {
        return MaLoaiPhong;
    }

    public void setMaLoaiPhong(String MaLoaiPhong) {
        this.MaLoaiPhong = MaLoaiPhong;
    }

    public String getTenLoaiPhong() {
        return TenLoaiPhong;
    }

    public void setTenLoaiPhong(String TenLoaiPhong) {
        this.TenLoaiPhong = TenLoaiPhong;
    }

    public double getGiaPhong() {
        return GiaPhong;
    }

    public void setGiaPhong(float GiaPhong) {
        this.GiaPhong = GiaPhong;
    }

    public double getGiaDien() {
        return GiaDien;
    }

    public void setGiaDien(float GiaDien) {
        this.GiaDien = GiaDien;
    }

    public double getGiaNuoc() {
        return GiaNuoc;
    }

    public void setGiaNuoc(float GiaNuoc) {
        this.GiaNuoc = GiaNuoc;
    }
    
}
