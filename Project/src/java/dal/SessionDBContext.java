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
public class SessionDBContext extends DBContext<Session> {

    public ArrayList<Session> getFromToDate(int lec, LocalDate startDate, LocalDate endDate) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "select s.id,s.gid,g.gname ,s.timeid, s.date, s.roomid, s.lid, g.courseid from [Session] s\n"
                    + "                    inner join [Group] g on g.id=s.gid and s.lid=?\n"
                    + "                    where date>=? and date<=?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lec);
            stm.setDate(2, Date.valueOf(startDate));
            stm.setDate(3, Date.valueOf(endDate));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setId(rs.getInt("s.id"));
                group g = new group();
                g.setGid(rs.getInt("s.gid"));
                g.setGname(rs.getString("g.gname"));
                g.setCourseid(rs.getString("g.courseid"));
                s.setGroup(g);
                s.setTimeid(rs.getInt("s.timeid"));
                s.setDate(rs.getDate("s.date"));
                s.setRoom(rs.getString("s.roomid"));
                s.setLid(rs.getInt("s.lid"));
                sessions.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    @Override
    public ArrayList<Session> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    @Override
    public void insert(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
