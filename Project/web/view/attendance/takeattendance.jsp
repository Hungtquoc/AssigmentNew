

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="../css/style.css" rel="stylesheet" type="text/css"/>
        <!-- CSS only -->
    </head>
    <body>
        <div class="container">
            <ol class="breadcrumb">
                <span>
                    <a href="../index.html" >Home</a> | 
                    <b>Take Attendance</b>
                </span>
            </ol>

            <form id="formTake" action= "takeattendance" method="POST">
                <input type="date" name="currentDate" value="${requestScope.currentDate}"> <br>
                <select name="lectureId">

                    <c:forEach items="${requestScope.lectures}" var="lec">
                        <option <c:if test="${param.lectureId eq lec.id}">
                                selected="selected"
                            </c:if>
                            value="${lec.id}" > ${lec.lectureName} </option>
                    </c:forEach>
                </select>
                
                <input type="submit" value="View">
                </form>
                <c:if test="${requestScope.lectureId ne null}">
                    <h3>Today(${requestScope.currentDate}) class by ${requestScope.lectureId}</h3>
                    <div class="row">
                        <div class="col-md-8">
                            <table>
                                <tr>
                                    <td>Slot</td>
                                    <td>Class</td>
                                    <td>Course</td>
                                    <td>Status</td>
                                    <td>Take Attendance</td>
                                </tr>
                                <c:forEach items= "${requestScope.sessions}" var="s"> 
                                    <tr>
                                        <td>${s.timeSlotId}</td>
                                        <td>${s.group.id}</td>
                                        <td>${s.group.courseId}</td>
                                        <td><c:if test="${s.status eq true}">
                                                <font color = "Green"> Attendance </font>
                                            </c:if>
                                            <c:if test="${s.status eq false}">
                                                <font color = "Red"> Not yet </font>
                                            </c:if> </td>
                                        <td>
                                            <form action="takeattendance" method="POST" >
                                                <input type="hidden" value="${s.id}" name="sid">
                                                <input type="submit" value="Edit"/>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>         
                            </table>
                        </div>
                    </div>
                </c:if> 
            
        </div>
    </body>
</html>
