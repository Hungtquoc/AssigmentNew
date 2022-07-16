/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public void updateStatus(int id) {
        try {
            String sql = "UPDATE [dbo].[Session]\n"
                    + "   SET \n"
                    + "      [Status] = 1\n"
                    + " WHERE ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Session> getSessionByDate(Date currentDate, int lectureid) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "select id, gid,timeid,date,roomid,lid from [Session]\n"
                    + "where date=? and lid=?"
                    + "order by date";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, currentDate.toString());
            stm.setInt(2, lectureid);
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
                sessions.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    public ArrayList<Session> getFromToDate(Date fromDate, Date toDate, int lectureid) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "select s.id, s.gid, g.gname ,s.timeid, s.date, s.roomid, s.lid , status from [Session] s\n"
                    + "inner join [Group] g on s.gid=g.id and s.lid=?\n"
                    + "where s.date >= ? and s.date <= ?\n"
                    + "order by date, timeid";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lectureid);
            stm.setDate(2, fromDate);
            stm.setDate(3, toDate);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setId(rs.getInt(1));
                group g = new group();
                g.setGid(rs.getInt(2));
                g.setGname(rs.getString(3));
                s.setGroup(g);
                s.setTimeid(rs.getInt(4));
                s.setDate(rs.getDate(5));
                s.setRoom(rs.getString(6));
                s.setLid(rs.getInt(7));
                s.setStatus(rs.getBoolean(8));
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

    @Override
    public Session get(int id) {

        try {

            String sql = "select id, gid,timeid,date,roomid,lid,checked from [Session]\n"
                    + "where id=?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);

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

}
