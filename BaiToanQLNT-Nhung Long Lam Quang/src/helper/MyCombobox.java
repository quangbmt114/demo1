/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

/**
 *
 * @author Dell
 */
public class MyCombobox {
    Object value;   //Chứa mã loại
    Object text;    //Chứa tên loại

    public MyCombobox(Object value, Object text) {
        this.value = value;
        this.text = text;
    }
    
    @Override
    public String toString(){
        return text.toString();
    }
    
    //Hàm lấy value kiểu int
    public int MaInt(){
        return Integer.parseInt(value.toString());
    }    
    //Hàm lấy value kiểu String
    public String MaString(){
        return value.toString();
    }
    
}