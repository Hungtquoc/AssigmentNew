/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import dal.LectureDBContext;
import java.util.ArrayList;
import model.lecture;
import dal.StuGroupDBContext;
import dal.AttendanceDBContext;
import model.Attendance;
import model.Session;
import model.Student;
/**
 *
 * @author trnha
 */
public class TakeAttendance extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TakeAttendance</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TakeAttendance at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Date currentDate = new Date(System.currentTimeMillis());
        SessionDBContext sDB = new SessionDBContext();
        String lectureId = null;
        LectureDBContext lDBC= new LectureDBContext();
        ArrayList<lecture> lectures = lDBC.list();
        request.setAttribute("lectures", lectures);
        request.setAttribute("currentDate", currentDate.toString());

        request.setAttribute("lectureId", lectureId);
        request.getRequestDispatcher("../view/attendance/takeattendance.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String lectureId = request.getParameter("lectureId");
        String currentDate = request.getParameter("currentDate");
        String sid = request.getParameter("sid");
        SessionDBContext sDB = new SessionDBContext();
        LectureDBContext lDB = new LectureDBContext();
        ArrayList<lecture> lectures = lDB.list();
        request.setAttribute("lectures", lectures);
        if (sid != null) {
            if (sid.length() > 0) {
                Session s = sDB.get(Integer.parseInt(request.getParameter("id")));
                if (s.isStatus()) {
                    AttendanceDBContext arDB = new AttendanceDBContext();
                    ArrayList<Attendance> ars = arDB.getSession(s.getId());
                    request.setAttribute("ars", ars);
                } else {
                    StuGroupDBContext gsDB = new StuGroupDBContext();
                    ArrayList<Student> students = gsDB.getStudentsByGroup(s.getGroup().getGid());
                    request.setAttribute("students", students);
                }
                request.setAttribute("currentDate", currentDate);
                request.setAttribute("lectureId", lectureId);
                request.setAttribute("session", s);
                request.getRequestDispatcher("../view/lecture/attendancereport.jsp").forward(request, response);
            }            
        } 
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
