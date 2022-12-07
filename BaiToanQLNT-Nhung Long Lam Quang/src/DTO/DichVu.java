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
    private int Gia;
//    private String DonViTinh;
//    private boolean TrangThai;

    public DichVu() {
    }

    public DichVu(String MaDichVu, String TenDichVu, int Gia) {
        this.MaDichVu = MaDichVu;
        this.TenDichVu = TenDichVu;
        this.Gia = Gia;
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

    public int getGia() {
        return Gia;
    }

    public void setGia(int Gia) {
        this.Gia = Gia;
    }

    
}
