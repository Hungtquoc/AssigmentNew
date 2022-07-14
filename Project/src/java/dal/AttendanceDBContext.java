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
import model.Attendance;
import model.Student;

/**
 *
 * @author trnha
 */
public class AttendanceDBContext extends DBContext<Attendance> {

    public ArrayList<Attendance> getSession(int sessionid) {
        ArrayList<Attendance> as = new ArrayList<>();
        try {

            String sql = "select id, sesid, sid, checked, taketime from Attendance \n"
                    + "where sesid =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sessionid);
            ResultSet rs = stm.executeQuery();
            while (true) {
                Attendance a = new Attendance();
                a.setId(rs.getInt("id"));
                a.setSesid(rs.getInt("sesid"));
                Student s = new Student();
                s.setSid(rs.getInt("sid"));
                a.setStudent(s);
                a.setStatus(rs.getBoolean("checked"));
                a.setTaketime(rs.getDate("taketime"));
                as.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return as;
    }

    @Override
    public ArrayList<Attendance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance model) {
        try {
            String sql = "update Attendance \n"
                    + "set checked=?\n"
                    + "where sid =?";
            PreparedStatement stm= connection.prepareStatement(sql);
            stm.setBoolean(1, model.isStatus());
            stm.setInt(2, model.getStudent().getSid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
