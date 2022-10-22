/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author cuong
 */
public class LoaiSanPham {
    int MaLoaiSP;
    String TenLoai; //Len 60
    String MoTa;    //Len 255

    public LoaiSanPham() {
    }

    public LoaiSanPham(int MaLoaiSP, String TenLoai, String MoTa) {
        this.MaLoaiSP = MaLoaiSP;
        this.TenLoai = TenLoai;
        this.MoTa = MoTa;
    }

    public LoaiSanPham(String TenLoai, String MoTa) {
        this.TenLoai = TenLoai;
        this.MoTa = MoTa;
    }

    
    
    public int getMaLoaiSP() {
        return MaLoaiSP;
    }

    public void setMaLoaiSP(int MaLoaiSP) {
        this.MaLoaiSP = MaLoaiSP;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    
    
}
