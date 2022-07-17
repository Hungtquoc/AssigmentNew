/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Session;
import model.group;

/**
 *
 * @author trnha
 */
public class SessionDBContext extends DBContext {
    
    public Session getSessionByID(int ses){
        try {
            
            String sql= "select s.id,s.gid,g.gname ,s.timeid, s.date, s.roomid, s.lid, g.courseid from [Session] s\n"
                    + "inner join [Group] g on g.id=s.gid "
                    + "inner join slot sl on sl.id = s.timeid "
                    + "where s.id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, ses);
            ResultSet rs= stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setId(ses);
                group g = new group();
                g.setGid(rs.getInt(2));
                g.setGname(rs.getString(3));
                g.setCourseid(rs.getString(8));
                s.setGroup(g);
                s.setTimeid(rs.getInt(4));
                s.setDate(rs.getDate(5));
                s.setRoom(rs.getString(6));
                s.setLid(rs.getInt(7));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Session> getFromToDate(LocalDate startDate, LocalDate endDate) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "select s.id,s.gid,g.gname ,s.timeid, s.date, s.roomid, s.lid, g.courseid from [Session] s\n"
                    + "inner join [Group] g on g.id=s.gid "
                    + "inner join slot sl on sl.id = s.timeid "
                    + "where s.lid=5 and s.date >= ? and s.date <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, Date.valueOf(startDate));
            stm.setDate(2, Date.valueOf(endDate));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setId(rs.getInt(1));
                group g = new group();
                g.setGid(rs.getInt(2));
                g.setGname(rs.getString(3));
                g.setCourseid(rs.getString(8));
                s.setGroup(g);
                s.setTimeid(rs.getInt(4));
                s.setDate(rs.getDate(5));
                s.setRoom(rs.getString(6));
                s.setLid(rs.getInt(7));
                
                sessions.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }
    

    public Session get(Session model) {

        try {
            String sql = "select id, gid,timeid,date,roomid,lid,checked from [Session]\n"
                    + "where id=?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getId());

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setId(rs.getInt("id"));
                GroupDBContext gDB = new GroupDBContext();
                group g = gDB.get(rs.getInt("gid"));
                s.setGroup(g);
                s.setTimeid(rs.getInt("timeid"));
                s.setDate(rs.getDate("date"));
                s.setRoom(rs.getString("room"));
                s.setLid(rs.getInt("lid"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    

}
