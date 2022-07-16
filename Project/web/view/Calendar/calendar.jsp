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
        <form id="formtable" action="calendar" method="get">
            <table border="1px">
                <thead>
                    <tr>
                        <th>SelectDate<input type="date" name="chooseDate" value="${requestScope.chooseDate}" onchange="document.getElementById('formtable').submit();"> </th>
                        <th>WEEK</th>
                        <th>MON</th>
                        <th>TUE</th>
                        <th>WED</th>
                        <th>THU</th>
                        <th>FRI</th>
                        <th>SAT</th>
                        <th>SUN</th>
                    </tr>
                    <tr>
                        <th></th>
                        <th></th>
                        <c:forEach items="${requestScope.dates}" var="d">
                            <th>${d.toLocalDate().getDayOfMonth()}/${d.toLocalDate().getMonthValue()}</th>
                            </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="i" begin="1" end="8">
                        <tr>
                            <td>Slot ${i}</td>
                            <c:forEach items="${requestScope.dates}" var="date">
                                <td <c:if test="${requestScope.chooseDate eq date}">bgcolor="#e0fffe"</c:if>></td>
                                <c:set var="flag" value="0"></c:set>
                                <c:forEach items="${requestScope.sessions}" var="s">
                                    <c:if test="${(s.getDate() eq date) and (s.getTimeid() eq i) }" >
                                        <c:set var="flag" value="1"></c:set>
                                <a href=""> ${s.getGroup().getGname()}<br>-${s.getGroup().getCourseid()} </a> <br>
                                at ${s.getRoom()} <br>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </body>
</html>
