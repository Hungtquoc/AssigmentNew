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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Session;
import org.apache.tomcat.util.digester.ArrayStack;

/**
 *
 * @author trnha
 */
@WebServlet(name = "CalendarController", urlPatterns = {"/Calendar"})
public class CalendarController extends HttpServlet {

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
            out.println("<title>Servlet Calendar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Calendar at " + request.getContextPath() + "</h1>");
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
        SessionDBContext sDBC = new SessionDBContext();
        int lectureId = 5;
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-mm-dd");
        Date chooseDate = new Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        ArrayList<Date> dates= new ArrayStack<>();
        for (int i = 0; i < 7; i++) {
            dates.add(Date.valueOf(fm.format(c.getTime())));
            c.add(Calendar.DAY_OF_WEEK, 1);
        }
        ArrayList<Session> sessions= sDBC.getFromToDate(dates.get(0), dates.get(dates.size()-1), lectureId);
        request.setAttribute("chooseDate", chooseDate.toString());
        request.setAttribute("sessions", sessions);
        request.setAttribute("dates", dates);
        request.setAttribute("lectureId", lectureId);
        request.getRequestDispatcher("../view/Calendar/calendar.jsp").forward(request, response);
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
        SessionDBContext sDBC = new SessionDBContext();
        int lectureId = 5;
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-mm-dd");
        Calendar c= Calendar.getInstance();
        String chooseDate=request.getParameter("chooseDate");
        try {
            c.setTime(fm.parse(chooseDate));
        } catch (ParseException ex) {
            Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        ArrayList<Date> dates= new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            dates.add(Date.valueOf(fm.format(c.getTime())));
            c.add(Calendar.DAY_OF_WEEK, 1);
        }
        ArrayList<Session> sessions=  sDBC.getFromToDate(dates.get(0), dates.get(dates.size()-1), lectureId);
        request.setAttribute("chooseDate", chooseDate);
        request.setAttribute("sessions", sessions);
        request.setAttribute("dates", dates);
        request.setAttribute("letureId", lectureId);
        request.getRequestDispatcher("../view/Calendar/calendar.jsp").forward(request, response);
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
