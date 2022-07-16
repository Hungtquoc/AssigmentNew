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
import model.Session;
import model.Student;
import model.group;

import model.stu_group;

/**
 *
 * @author trnha
 */
public class StuGroupDBContext extends DBContext<stu_group> {

    public ArrayList<stu_group> getStuGroupBySession(Session session) {
        ArrayList<stu_group> stu_groups = new ArrayList<>();
        try {
            String sql = "select s.id, s.sname, g.id, g.lectureid, date from Student s inner join stu_group sg\n"
                    + "on s.id=sg.sid inner join [Group] g on g.id= sg.groupid\n"
                    + "inner join [Session] ses on ses.gid = g.id where g.id=? and date = ? and slot =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, session.getGroup().getGid());
            stm.setDate(2, session.getDate());
            stm.setInt(3, session.getTimeid());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                stu_group sg= new stu_group();
                Student s = new Student();
                s.setSid(rs.getInt("id"));
                s.setName(rs.getString("sname"));
                group g= new group();
                g.setGid(rs.getInt("g.id"));
                g.setLectureid(rs.getInt("lectureid"));
                sg.setGroup(g);
                sg.setStudents(s);
                stu_groups.add(sg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StuGroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stu_groups;
    }

    @Override
    public ArrayList<stu_group> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public stu_group get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(stu_group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(stu_group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(stu_group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
