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
public class AttendanceDBContext extends DBContext {

    public ArrayList<Attendance> getAttendancesBySession(int sesid) {
        ArrayList<Attendance> attendances = new ArrayList<>();
        try {
            String sql = "select aid,sesid,a.sid,a.checked,s.sname,g.id,g.courseid,g.gname,ses.id,ses.timeid,ses.date,c.id,c.cname,l.lname from Attendance a inner join Student s\n"
                    + "on a.sid=s.id inner join  stu_group sg \n"
                    + "on sg.sid=s.id inner join  [Group] g \n"
                    + "on g.id=sg.groupid inner join Session ses \n"
                    + "on ses.gid= g.id inner join course c\n"
                    + "on c.id= g.courseid inner join Lecture l\n"
                    + "on l.id= ses.lid\n"
                    + "where ses.id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sesid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                a.setId(rs.getInt(1));
                a.setStatus(rs.getBoolean(4));
                Student s = new Student();
                s.setSid(rs.getInt(3));
                s.setName(rs.getString(5));
                a.setStudent(s);
                SessionDBContext sdb = new SessionDBContext();
                Session ses = sdb.getSessionByID(sesid);
                a.setSesid(ses);
                attendances.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attendances;
    }

    public void InsertorUpdate(ArrayList<Attendance> attendances) {

        try {
            connection.setAutoCommit(false);
            for (Attendance a : attendances) {
                if (a.getId() == -1) {
                    String sql = "insert into Attendance (sesid, sid, checked)\n"
                            + "values(? ,?,?)";
                    PreparedStatement stm = connection.prepareStatement(sql);
                    stm.setBoolean(3, a.isStatus());
                    stm.setInt(2, a.getStudent().getSid());
                    stm.setInt(1, a.getSesid().getId());
                    stm.executeUpdate();
                } else {
                    String sql = "update Attendance set checked = ? where aid = ?";
                    PreparedStatement stm = connection.prepareStatement(sql);
                    stm.setBoolean(1, a.isStatus());
                    stm.setInt(2, a.getId());
                    stm.executeUpdate();
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
