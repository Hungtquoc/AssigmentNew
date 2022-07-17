/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Session;
import org.apache.tomcat.util.digester.ArrayStack;
import model.Week;
import dal.SlotTime;
import java.time.format.DateTimeFormatter;
import model.lecture;
/**
 *
 * @author trnha
 */
@WebServlet(name = "TimeTableController", urlPatterns = {"/calendar"})
public class TimeTableController extends HttpServlet {

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Week> weeks= getWeeksOfYear();
        SlotTime slot= new SlotTime();
        LocalDate currentDate= LocalDate.now();
        Week currentWeek = getWeekByDate(weeks, currentDate);
        SessionDBContext sDBC = new SessionDBContext();
        int lectureId = 5;
        ArrayList<Session> sessions= sDBC.getFromToDate(lectureId, currentWeek.getStartDate(), currentWeek.getEndDate());
        request.setAttribute("sessions", sessions);
        request.setAttribute("week", currentWeek);
        request.setAttribute("slot", slot.list());
        request.setAttribute("date", currentDate);
        request.setAttribute("weeks", weeks);
        request.setAttribute("sessions", sessions);
        request.setAttribute("week", currentWeek);
        request.getRequestDispatcher("view/Calendar/calendar.jsp").forward(request, response);
    }


    public ArrayList<Week> getWeeksOfYear() {
        ArrayList<Week> weeks = new ArrayList<>();
        LocalDate startDate = LocalDate.parse("03-01-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        for (int i = 0; i < 365; i += 7) {
            LocalDate endDate = startDate.plusDays(6);
            Week week = new Week();
            week.setStartDate(startDate);
            week.setEndDate(endDate);
            weeks.add(week);
            startDate = endDate.plusDays(1);
        }
        return weeks;
    }
    public Week getWeekByDate(ArrayList<Week> weeks, LocalDate date) {
        Week currentWeek = new Week();
        for (Week w : weeks) {
            for (int i = 0; i < 6; i++) {
                if (w.getStartDate().plusDays(i).equals(date)) {
                    currentWeek = w;
                    break;
                }
            }
        }
        return currentWeek;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Week> weeks = getWeeksOfYear();
        int index = Integer.parseInt(request.getParameter("week_index"));
        Week w = weeks.get(index);
        LocalDate currentDate = w.getStartDate();
        Week currentWeek = getWeekByDate(weeks, currentDate);
        SessionDBContext sDB= new SessionDBContext();
        SlotTime slot= new SlotTime(); 
        //get session
        int lec = 5;
        ArrayList<Session> sessions = sDB.getFromToDate(lec, currentWeek.getStartDate(), currentWeek.getEndDate());
        //set attributes
        request.setAttribute("sessions", sessions);
        request.setAttribute("slots", slot.list());
        request.setAttribute("week", currentWeek);
        request.setAttribute("date", currentDate);
        request.setAttribute("weeks", weeks);
        request.getRequestDispatcher("view/Calendar/calendar.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
