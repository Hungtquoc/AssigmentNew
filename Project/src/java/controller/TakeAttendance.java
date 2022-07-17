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
import java.util.ArrayList;
import dal.StuGroupDBContext;
import dal.AttendanceDBContext;
import model.Attendance;
import model.Session;
import model.stu_group;

/**
 *
 * @author trnha
 */
public class TakeAttendance extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TakeAttendance</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TakeAttendance at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionDBContext sDB = new SessionDBContext();
        StuGroupDBContext stuDB = new StuGroupDBContext();
        AttendanceDBContext aDB = new AttendanceDBContext();
        int id =5;
        Session s = new Session();
        s.setId(id);
        Session session = sDB.get(s);
        ArrayList<stu_group> stu_group = stuDB.getStuGroupBySession(session);
        ArrayList<Attendance> attends = aDB.list();
        for (stu_group stugroup : stu_group) {
            for (Attendance attend : attends) {
                if (attend.getStudent().getSid() == stugroup.getStudents().getSid()) {
                    stugroup.getStudents().getAttends().add(attend);
                }
            }
        }
        request.getSession().setAttribute("session", session);
        request.getSession().setAttribute("stu_group", stu_group);
        request.getRequestDispatcher("view/attendance/takeattendance.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AttendanceDBContext attendDB = new AttendanceDBContext();
        SessionDBContext sessionDB = new SessionDBContext();
        ArrayList<stu_group> stu_groups = (ArrayList<stu_group>) request.getSession().getAttribute("stu_group");
        for (stu_group stu_group1 : stu_groups) {
            Attendance attendance = new Attendance();
            boolean attend = request.getParameter("check_" + stu_group1.getStudents().getSid()).equals("true");
            attendance.setStatus(attend);
            attendance.setStudent(stu_group1.getStudents());
            Session s = (Session) request.getSession().getAttribute("session");
            s.setStatus(true);
            attendance.setSesid(s);
            if (!attendDB.isExist(attendance)) {
                attendDB.insert(attendance);
            } else {
                attendDB.update(attendance);
            }
            request.getSession().removeAttribute("session");
            request.getSession().removeAttribute("enrolls");
            response.sendRedirect("schedule");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
