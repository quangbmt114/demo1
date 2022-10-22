/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author quang nguyen
 */
public class DichVu {
    private String MaDichVu,TenDichVu;
    private float Gia;
    private String DonViTinh;
    private boolean TrangThai;

    public DichVu() {
    }

    public DichVu(String MaDichVu, String TenDichVu, float Gia, String DonViTinh, boolean TrangThai) {
        this.MaDichVu = MaDichVu;
        this.TenDichVu = TenDichVu;
        this.Gia = Gia;
        this.DonViTinh = DonViTinh;
        this.TrangThai = TrangThai;
    }

    public String getMaDichVu() {
        return MaDichVu;
    }

    public void setMaDichVu(String MaDichVu) {
        this.MaDichVu = MaDichVu;
    }

    public String getTenDichVu() {
        return TenDichVu;
    }

    public void setTenDichVu(String TenDichVu) {
        this.TenDichVu = TenDichVu;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String DonViTinh) {
        this.DonViTinh = DonViTinh;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
