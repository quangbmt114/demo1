/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author quang nguyen
 */
public class Login {
    String usename,password;
    String MaKhuTro;
    String Email;
    public Login() {
    }

    public Login(String usename, String password, String MaKhuTro, String Email) {
        this.usename = usename;
        this.password = password;
        this.MaKhuTro = MaKhuTro;
        this.Email = Email;
    }

   

    public Login(String usename, String password) {
        this.usename = usename;
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    
    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMaKhuTro() {
        return MaKhuTro;
    }

    public void setMaKhuTro(String MaKhuTro) {
        this.MaKhuTro = MaKhuTro;
    }
    
}
