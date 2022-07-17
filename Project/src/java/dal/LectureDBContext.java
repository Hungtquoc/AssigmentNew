/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.lecture;
/**
 *
 * @author trnha
 */
public class LectureDBContext extends DBContext{
    
    public ArrayList<lecture> list() {
        ArrayList<lecture> lecs = new ArrayList<>();
        try {
            String sql = "select id, lname from Lecture";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                lecture l = new lecture();
                l.setLid(rs.getInt("id"));
                l.setLname(rs.getString("lname"));
                lecs.add(l);
            }
            return lecs;
        } catch (SQLException ex) {
            Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lecs;
    }
    public lecture getLectureByint(int user) {
        lecture lec = new lecture();
        try {
            String sql = "select id, lname from Lecture where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, user);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                lec.setLid(rs.getInt("id"));
                lec.setLname(rs.getString("lname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lec;
    }
    
    
}
