/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.Login;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author quang nguyen
 */
public class BLLLogin {
    public static ArrayList<Login> GetAll() {
        ResultSet rs = DAL.DALLogin.GetAllData();

        ArrayList<Login> arr = new ArrayList<>();
        try {
            while (rs.next()) {
                Login login = new Login();
               login.setUsename(rs.getString("UserN"));
               login.setPassword(rs.getString("PassW"));
               login.setMaKhuTro(rs.getString("MaNhaTro"));
               login.setEmail(rs.getString("Email"));
                arr.add(login);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.getMessage());
        }

        
        return arr;
    }
}
