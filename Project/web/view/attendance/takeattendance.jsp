

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
        Attendance for lecture Sonnt5 at slot 1 on 11/7/2022<br>
        <form action="take" method="post">
            <table>
                <thead>
                    <tr>
                        <td>No</td>
                        <td>Group</td>
                        <td>Sid</td>
                        <td>Name</td>
                        <td>Status</td>
                        <td>Taker</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sessionScope.stu_group}" var="sg">
                        <tr>
                            <td>${stu_group.indexOf(sg)+1}</td>
                            <td>${sg.getGroup().getGid()}</td>
                            <td>${sg.getStudents().getSid()}</td>
                            <td>${sg.getStudents().getName()}</td>
                            <td>
                                <input type="radio" value="false" 
                                       <c:if test="${!sg.getStudents().isAttend(session)}">checked="checked"</c:if>
                                       name="check_${sg.getStudents().getSid()}"> absent
                                <input type="radio" value="true" 
                                       <c:if test="${!sg.getStudents().isAttend(session)}">checked="checked"</c:if>
                                       name="check_${sg.getStudents().getSid()}"> present
                            </td>
                            <td>${sg.getGroup().getLectureid()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br> <input type="submit" value="Save">
        </form>
    </body>
</html>
