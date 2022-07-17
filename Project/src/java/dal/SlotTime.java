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
import model.slot;

/**
 *
 * @author trnha
 */
public class SlotTime extends DBContext<slot>{

    @Override
    public ArrayList<slot> list() {
        ArrayList<slot> slots= new ArrayList<>();
        try {
            String sql = "select id from slot";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs= stm.executeQuery();
            while(rs.next()) {
                slot slot = new slot();
                slot.setId(rs.getInt("id"));
                slots.add(slot);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SlotTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return slots;
    }

    @Override
    public slot get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(slot model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(slot model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(slot model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
