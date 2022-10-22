/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.LoaiPhong;
import GUI.DichVu;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author quang nguyen
 */
public class BLLDichVu {
    public static ArrayList<DTO.DichVu> GetAll(){
        ResultSet rs = DAL.DALDichVu.GetAllData();
        ArrayList<DTO.DichVu> list = new ArrayList<>();
        try {
            while(rs.next()){
                DTO.DichVu dv = new DTO.DichVu();
              dv.setMaDichVu(rs.getString("MaDichVu"));
              dv.setTenDichVu(rs.getString("TenDichVu"));
              dv.setGia(rs.getFloat("GiaDichVu"));
              dv.setDonViTinh(rs.getString("DonViTinh"));
              dv.setTrangThai(rs.getBoolean("TrangThai"));
              list.add(dv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLLDichVu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static void DoVaoTable(ArrayList<DTO.DichVu> arr, JTable tbl){
        
        DefaultTableModel tbModel = (DefaultTableModel)tbl.getModel();
        tbModel.setRowCount(0);
        for(DTO.DichVu dv : arr){
            Object obj[] = new Object[5];
            
            obj[0] = dv.getMaDichVu();
            obj[1] = dv.getTenDichVu();
            obj[3] = dv.getGia();
             obj[2] = dv.getDonViTinh();
              obj[4] = dv.isTrangThai()?"Đang sử dụng":"Không sử dụng";
     
            tbModel.addRow(obj);
        }
    }
}
