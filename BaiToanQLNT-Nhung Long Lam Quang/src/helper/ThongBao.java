/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import javax.swing.JOptionPane;

/**
 *
 * @author cuong
 */
public class ThongBao {
    //Hàm thông báo đơn giản
    public static void ThongBaoDonGian(String TieuDe, String NoiDung){
        JOptionPane.showMessageDialog(null, 
                NoiDung, TieuDe, JOptionPane.INFORMATION_MESSAGE );
    }
    
    public static void ThongBaoCoIcon(String TieuDe, String NoiDung, int KieuTB){
        JOptionPane.showMessageDialog(null, NoiDung, TieuDe, KieuTB);
    }
}
