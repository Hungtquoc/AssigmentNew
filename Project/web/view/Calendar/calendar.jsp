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
        <script>
            function Submit() {
                let form = document.getElementById("schedule_form");
                form.submit();
            }
        </script>
    </head>
    <body
            <div style="text-align:center;">
                Campus: <select>
                    <option> FU-HL</option>
                </select>  </div>
            <div style="text-align: center;">Lecture: <input type="text" name="lecture" value="sonnt">                
        <form action="calendar" method="POST" id="schedule_form">
            <table border="1" id="schedule_table" >
                <tr>
                    <td>
                        Year: 2022 <br/>
                        Week:
                        <select onchange="Submit()" id="week" name="week_index">
                            <c:forEach items="${requestScope.weeks}" var="w">
                                <option  value="${requestScope.weeks.indexOf(w)}"
                                         <c:forEach var="i" begin="0" end="6">
                                             <c:if test="${requestScope.date eq w.getStartDate().plusDays(i-1)}">
                                                 selected = "selected";
                                             </c:if>
                                         </c:forEach> >
                                    ${w.startDate.getDayOfMonth()}/${w.getStartDate().getMonthValue()} To ${w.getEndDate().getDayOfMonth()}/${w.endDate.getMonthValue()}
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
                        <td>${requestScope.week.getStartDate().plusDays(j).getDayOfMonth()}/${requestScope.week.getStartDate().plusDays(j).getMonthValue()}</td>
                    </c:forEach>
                </tr>
                <c:forEach items="${requestScope.slot}" var="sl">
                    <tr>
                        <td>Slot ${sl.getId()}</td>
                        <c:forEach var="i" begin="0" end="6">
                            <td>
                                <c:forEach items="${requestScope.sessions}" var="s" >
                                    <c:if test="${s.getTimeid() eq sl.getId() and requestScope.week.getStartDate().plusDays(i-1) eq s.getDate().toLocalDate()}">
                                        ${s.getGroup().getCourseid()} 
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
