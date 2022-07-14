/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author trnha
 */
public class Session {
    private int id;
    private group group;
    private int timeid;
    private Date date;
    private String room;
    private int lid;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public group getGroup() {
        return group;
    }

    public void setGroup(group group) {
        this.group = group;
    }

    public int getTimeid() {
        return timeid;
    }

    public void setTimeid(int timeid) {
        this.timeid = timeid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    
    
}
