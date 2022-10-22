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
    private String MaLoaiPhong,TenLoaiPhong;
    private float GiaPhong;
    private float GiaDien;
    private float GiaNuoc;

    public LoaiPhong(String MaLoaiPhong, String TenLoaiPhong, float GiaPhong, float GiaDien, float GiaNuoc) {
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

    public float getGiaPhong() {
        return GiaPhong;
    }

    public void setGiaPhong(float GiaPhong) {
        this.GiaPhong = GiaPhong;
    }

    public float getGiaDien() {
        return GiaDien;
    }

    public void setGiaDien(float GiaDien) {
        this.GiaDien = GiaDien;
    }

    public float getGiaNuoc() {
        return GiaNuoc;
    }

    public void setGiaNuoc(float GiaNuoc) {
        this.GiaNuoc = GiaNuoc;
    }
    
}
