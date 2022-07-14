/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.SessionDBContext;
import java.sql.Date;
import java.util.ArrayList;
import model.Session;
/**
 *
 * @author trnha
 */
@WebServlet(name="Calendar", urlPatterns={"/Calendar"})
public class Calendar extends HttpServlet {
   
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
            out.println("<title>Servlet Calendar</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Calendar at " + request.getContextPath () + "</h1>");
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
        SessionDBContext sDBC= new SessionDBContext();
        Date from = Date.valueOf("2022-07-11");
        Date to = Date.valueOf("2022-07-1");
        int lectureid=5;
        ArrayList<Session> sessions=sDBC.getFromToDate(from, to, lectureid);
        ArrayList<Date> dates= new ArrayList<>();
        dates.add(Date.valueOf("2022-07-11"));
        dates.add(Date.valueOf("2022-07-12"));
        dates.add(Date.valueOf("2022-07-13"));
        dates.add(Date.valueOf("2022-07-14"));
        dates.add(Date.valueOf("2022-07-15"));
        dates.add(Date.valueOf("2022-07-16"));
        dates.add(Date.valueOf("2022-07-17"));
        request.setAttribute("sessions", sessions);
        request.setAttribute("dates", dates);
        request.setAttribute("lectureid", lectureid);
        request.getRequestDispatcher("../view/Calendar/calendar.jsp").forward(request, response);
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
        processRequest(request, response);
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
