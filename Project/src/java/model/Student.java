/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author trnha
 */
public class Student {

    private int index;
    private int sid;
    private String name;
    private ArrayList<Attendance> attends = new ArrayList<>();

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Attendance> getAttends() {
        return attends;
    }

    public void setAttends(ArrayList<Attendance> attends) {
        this.attends = attends;
    }

    public boolean isAttend(Session s) {
        for (Attendance a : attends) {
            if (a.getSesid().getId() == s.getId()) {
                if (a.isStatus()) {
                    return true;
                }
            }
        }
        return false;
    }
}
