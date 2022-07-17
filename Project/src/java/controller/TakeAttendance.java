/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AttendanceDBContext;
import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.StudentDBContext;
import dal.SessionDBContext;
import java.util.ArrayList;
import model.Attendance;
import model.Session;
import model.Student;

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
        StudentDBContext sDB=  new StudentDBContext();
        SessionDBContext sesDB= new SessionDBContext();
        AttendanceDBContext aDB= new AttendanceDBContext();
        int sesid=Integer.parseInt(request.getParameter("sesid"));
        Session s= sesDB.getSessionByID(sesid);
        request.setAttribute("session", s);
        ArrayList<Attendance> alist= aDB.getAttendancesBySession(s.getId());
        if(alist.isEmpty()){
            ArrayList<Student> stulist = sDB.listStudentBy(sesid);
            request.setAttribute("stulist", stulist);
        }else{
            request.setAttribute("alist", alist);
        }
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
        StudentDBContext sDB=  new StudentDBContext();
        SessionDBContext sesDB= new SessionDBContext();
        AttendanceDBContext aDB= new AttendanceDBContext();
        int sesid = Integer.parseInt(request.getParameter("sesid"));
        Session ses= sesDB.getSessionByID(sesid);
        String[] aid= request.getParameterValues("aid");
        String[] stuid=request.getParameterValues("stuid");
        String[] stuname = request.getParameterValues("stuname");
        ArrayList<Attendance> alist= new ArrayList<>();
        for (int i = 0; i < stuid.length; i++) {
            Attendance a= new Attendance();
            if(aid== null){
                a.setId(-1);
            }else{
                a.setId(Integer.parseInt(aid[i]));
            }
            Student s = new Student();
            s.setSid(Integer.parseInt(stuid[i]));
            s.setName(stuname[i]);
            a.setStudent(s);
            a.setSesid(ses);
            a.setStatus(request.getParameter("status" + i).equals("1"));
            alist.add(a);
        }
        aDB.InsertorUpdate(alist);
        response.sendRedirect("take?sesid="+sesid);
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
