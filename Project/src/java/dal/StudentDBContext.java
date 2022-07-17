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
import model.Student;

/**
 *
 * @author trnha
 */
public class StudentDBContext extends DBContext {
    ArrayList<Student> students= new ArrayList<>();
    public ArrayList<Student> listStudentBy(int sesid) {
        try {
            
            String sql = "select s.id,s.sname,g.id,l.id,l.lname,ses.id,g.courseid,g.gname,g.courseid,ses.roomid,ses.timeid,ses.date,c.cname from Student s inner join stu_group sg \n"
                    + "on s.id=sg.sid inner join [Group] g \n"
                    + "on g.id= sg.groupid inner join Lecture l \n"
                    + "on l.id= g.lectureid inner join Session ses\n"
                    + "on ses.gid = g.id inner join course c\n"
                    + "on c.id= g.courseid where ses.id= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sesid);
            ResultSet rs= stm.executeQuery();
            while (rs.next()) {                
                Student s= new Student();
                s.setSid(rs.getInt(1));
                s.setName(rs.getString(2));
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
}
