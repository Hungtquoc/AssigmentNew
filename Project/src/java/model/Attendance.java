/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;


/**
 *
 * @author trnha
 */
public class Attendance {
    private int id;
    private int sesid;
    private Student student;
    private boolean status;
    private Date taketime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSesid() {
        return sesid;
    }

    public void setSesid(int sesid) {
        this.sesid = sesid;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getTaketime() {
        return taketime;
    }

    public void setTaketime(Date taketime) {
        this.taketime = taketime;
    }
    
}
