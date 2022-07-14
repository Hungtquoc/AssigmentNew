/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.security.acl.Group;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.group;

/**
 *
 * @author trnha
 */
public class GroupDBContext extends DBContext<group> {

    public ArrayList<group> getListByLecture(String lectureId) {
        ArrayList<group> groups = new ArrayList<>();
        try {
            String sql = "select id, gname,lectureid, courseid  from [Group] where lectureid =? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lectureId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                group g = new group();
                g.setGid(rs.getInt("id"));
                g.setGname(rs.getString("gname"));
                g.setCourseid(rs.getString("courseid"));
                g.setLectureid(rs.getString("lectureid"));
                groups.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }

    @Override
    public group get(int id) {
        
        try {
            String sql = "select id, gname,lectureid, courseid  from [Group] where id=? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                group g = new group();
                g.setGid(rs.getInt("id"));
                g.setGname(rs.getString("gname"));
                g.setCourseid(rs.getString("courseid"));
                g.setLectureid(rs.getString("lectureid"));
                return g;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(group model) {
        
    }

    @Override
    public void delete(group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<group> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
