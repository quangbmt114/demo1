/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import helper.sqlHelper;
import java.sql.ResultSet;

/**
 *
 * @author quang nguyen
 */
public class DALLogin {
    public static ResultSet GetAllData(){
       String sql = "select * from TaiKhoan ";
       ResultSet rs = sqlHelper.executeQuery(sql);
       return rs;
    }
}
