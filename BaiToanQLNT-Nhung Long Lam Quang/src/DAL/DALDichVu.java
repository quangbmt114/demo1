/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;
import java.sql.ResultSet;
/**
 *
 * @author quang nguyen
 */
public class DALDichVu {
    public static ResultSet GetAllData(){
        String sql = "select * from DichVu";
        ResultSet rs = helper.sqlHelper.executeQuery(sql);
        return rs;
    }
    public static void Add(DTO.DichVu dv){
        String sql = "insert into DichVu values(?,?,?,?,?)";
         helper.sqlHelper.executeUpdate(sql, dv.getMaDichVu(),dv.getTenDichVu(),dv.getGia()
         ,dv.getDonViTinh(),dv.isTrangThai()
         );      
    }
    public static void Delete(DTO.DichVu dv){
        String sql = "delete DichVu where MaDichVu = ?";
        helper.sqlHelper.executeUpdate(sql, dv.getMaDichVu());
    }
    public static void Update(DTO.DichVu dv){
        String sql = "UPDATE [dbo].[DichVu]" +
"   SET [MaDichVu] = ?" +
"      ,[TenDichVu] = ?" +
"      ,[GiaDichVu] = ?" +
"      ,[DonViTinh] = ?" +
"      ,[TrangThai] = ?" +
" WHERE  MaDichVu=?";
        helper.sqlHelper.executeUpdate(sql, dv.getMaDichVu(),dv.getTenDichVu(),dv.getGia(),
                dv.getDonViTinh(),dv.isTrangThai(),dv.getMaDichVu());
    }
    
}
