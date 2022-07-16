<%-- 
    Document   : calendar
    Created on : Jul 14, 2022, 9:21:49 PM
    Author     : trnha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="schedule">
            <div style="text-align:center;">
                Campus: <select>
                    <option> FU-HL</option>
                </select>  </div>
            <div style="text-align: center;">Lecture: <input type="text" name="lecture" value="sonnt"> 
                <input type="submit" value="View"> </div>
        </form>

        <form action="schedule" method="POST" id="schedule_form">
            <table border="1" id="schedule_table">
                <tr>
                    <td>
                        Year: 2022 <br/>
                        Week:
                        <select onchange="Submit()" id="week" name="week_index">
                            <c:forEach items="${requestScope.weeks}" var="w">
                                <option  value="${requestScope.weeks.indexOf(w)}"
                                         <c:forEach var="i" begin="0" end="6">
                                             <c:if test="${requestScope.date eq w.startDate.plusDays(i)}">
                                                 selected = "selected";
                                             </c:if>
                                         </c:forEach> >
                                    ${w.startDate.getDayOfMonth()}/${w.startDate.getMonthValue()} To ${w.endDate.getDayOfMonth()}/${w.endDate.getMonthValue()}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>Mon</td>
                    <td>Tue</td>
                    <td>Wed</td>
                    <td>Thu</td>
                    <td>Fri</td>
                    <td>Sat</td>
                    <td>Sun</td>
                </tr>
                <tr>
                    <td></td>
                    <c:forEach var="j" begin="0" end="6">
                        <td>${requestScope.week.startDate.plusDays(j).getDayOfMonth()}/${requestScope.week.startDate.plusDays(j).getMonthValue()}</td>
                    </c:forEach>
                </tr
                <c:forEach items="${requestScope.slots}" var="slot">
                    <tr>
                        <td>${slot.slot}</td>
                        <c:forEach var="i" begin="0" end="6">
                            <td>
                                <c:forEach items="${requestScope.sessions}" var="s">
                                    <c:if test="${s.slot.slot eq slot.slot and requestScope.week.startDate.plusDays(i) eq s.date.toLocalDate()}">
                                        ${s.date}
                                    </c:if>
                                </c:forEach> 
                            </td>

                        </c:forEach>  
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
