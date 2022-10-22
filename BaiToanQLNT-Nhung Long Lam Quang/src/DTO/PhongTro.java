/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author quang nguyen
 */
public class PhongTro {
    private String MaPhong,TenPhong,MaLoaiPhong,MaKhuTro,ViTri;
    private float DienTich;
    private int Giuong,Ban,BongDen,KinhCua;
    private String Anh;
    private boolean TrangThai;

    public PhongTro(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public PhongTro(String MaPhong, String TenPhong, String MaLoaiPhong, String MaKhuTro, String ViTri, float DienTich, int Giuong, int Ban, int BongDen, int KinhCua, String Anh, boolean TrangThai) {
        this.MaPhong = MaPhong;
        this.TenPhong = TenPhong;
        this.MaLoaiPhong = MaLoaiPhong;
        this.MaKhuTro = MaKhuTro;
        this.ViTri = ViTri;
        this.DienTich = DienTich;
        this.Giuong = Giuong;
        this.Ban = Ban;
        this.BongDen = BongDen;
        this.KinhCua = KinhCua;
        this.Anh = Anh;
        this.TrangThai = TrangThai;
    }

    public PhongTro(String MaPhong, String TenPhong, String MaLoaiPhong, String MaKhuTro, String ViTri, float DienTich, int Giuong, int Ban, int BongDen, int KinhCua, boolean TrangThai) {
        this.MaPhong = MaPhong;
        this.TenPhong = TenPhong;
        this.MaLoaiPhong = MaLoaiPhong;
        this.MaKhuTro = MaKhuTro;
        this.ViTri = ViTri;
        this.DienTich = DienTich;
        this.Giuong = Giuong;
        this.Ban = Ban;
        this.BongDen = BongDen;
        this.KinhCua = KinhCua;
        this.TrangThai = TrangThai;
    }

    public PhongTro() {
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String TenPhong) {
        this.TenPhong = TenPhong;
    }

    public String getMaLoaiPhong() {
        return MaLoaiPhong;
    }

    public void setMaLoaiPhong(String MaLoaiPhong) {
        this.MaLoaiPhong = MaLoaiPhong;
    }

    public String getMaKhuTro() {
        return MaKhuTro;
    }

    public void setMaKhuTro(String MaKhuTro) {
        this.MaKhuTro = MaKhuTro;
    }

    public String getViTri() {
        return ViTri;
    }

    public void setViTri(String ViTri) {
        this.ViTri = ViTri;
    }

    public float getDienTich() {
        return DienTich;
    }

    public void setDienTich(float DienTich) {
        this.DienTich = DienTich;
    }

    public int getGiuong() {
        return Giuong;
    }

    public void setGiuong(int Giuong) {
        this.Giuong = Giuong;
    }

    public int getBan() {
        return Ban;
    }

    public void setBan(int Ban) {
        this.Ban = Ban;
    }

    public int getBongDen() {
        return BongDen;
    }

    public void setBongDen(int BongDen) {
        this.BongDen = BongDen;
    }

    public int getKinhCua() {
        return KinhCua;
    }

    public void setKinhCua(int KinhCua) {
        this.KinhCua = KinhCua;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String Anh) {
        this.Anh = Anh;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    

 
}
   