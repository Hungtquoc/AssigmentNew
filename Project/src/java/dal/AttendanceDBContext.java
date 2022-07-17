/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Session;
import model.Student;
import model.group;

/**
 *
 * @author trnha
 */
public class AttendanceDBContext extends DBContext<Attendance> {

   

    @Override
    public ArrayList<Attendance> list() {
        ArrayList<Attendance> attendances= new ArrayList<>();
        try {
            
            String sql = "select a.id, a.sid,a.sesid,s.date,s.gid,checked from Attendance a inner join Session s\n"
                    + "on a.sesid= s.id";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs= stm.executeQuery();
            while (rs.next()) {
                Attendance a= new Attendance();
                Student s= new Student();
                Session ses= new Session();
                group g= new group();
                g.setGid(rs.getInt("gid"));
                s.setSid(rs.getInt("a.sid"));
                ses.setId(rs.getInt("a.sesid"));
                ses.setGroup(g);
                a.setStudent(s);
                a.setSesid(ses);
                a.setStatus(rs.getBoolean("checked"));
                attendances.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attendances;
    }

    @Override
    public Attendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Attendance model) {
        try {
            String sql = "insert into Attendance(id, sesid, sid, checked)\n"
                    + "values(?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getId());
            stm.setInt(2, model.getSesid().getId());
            stm.setInt(3, model.getStudent().getSid());
            stm.setBoolean(4, model.isStatus());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean isExist(Attendance entity) {
        boolean isExist = false;
        ArrayList<Attendance> list = list();
        for (Attendance a : list) {
            if (a.getStudent().getSid()== entity.getStudent().getSid()&& a.getSesid().getId()== entity.getSesid().getId()) {
                isExist = true;
            }
        }
        return isExist;
    }
    @Override
    public void update(Attendance model) {
        try {
            String sql = "update Attendance \n"
                    + "set checked=?\n"
                    + "where sid =?";
            PreparedStatement stm = connection.prepareStatement(sql);
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
