

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
        Attendance for lecture Sonnt5 <br>
        <form action="take" method="post">
            <table>
                <thead>
                    <tr>
                        <td>No</td>
                        <td>Sid</td>
                        <td>Name</td>
                        <td>Status</td>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${requestScope.stulist.size()>0}">
                        <c:forEach begin="0" end="${requestScope.stulist.size()-1}" var="i" step="1">
                            <tr>
                                <td>${i+1}</td>
                                <td>${requestScope.stulist.get(i).getSid()}</td>
                                <td>${requestScope.stulist.get(i).getName()}</td>
                        <input type="hidden" name="stuid" value="${requestScope.stulist.get(i).getSid()}">
                        <input type="hidden" name="stuname" value="${requestScope.stulist.get(i).getName()}">
                        <td><input type="radio" name="status${i}" value="0" checked="checked"> Absent
                            <input type="radio" name="status${i}" value="1"> Attended</td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test = "${requestScope.alist.size() > 0}">
                    <c:forEach begin = "0" end="${requestScope.alist.size()-1}" var = "i" step = "1">
                        <tr>
                            <td>${i+1}</td>
                            <td>${requestScope.alist.get(i).getStudent().getSid()}</td>
                            <td>${requestScope.alist.get(i).getStudent().getName()}</td>
                        <input type="hidden" name="stuid"
                               value="${requestScope.alist.get(i).getStudent().getSid()}">
                        <input type="hidden" name="stuname"
                               value="${requestScope.alist.get(i).getStudent().getName()}">
                        <input type="hidden" name="aid"
                               value="${requestScope.alist.get(i).getId()}">
                        <td><input type="radio" name="status${i}" value="0"<c:if test="${requestScope.alist.get(i).isStatus() eq false}">checked="checked"</c:if>> Absent
                            <input type="radio" name="status${i}" value="1"<c:if test="${requestScope.alist.get(i).isStatus() eq true}">checked="checked"</c:if>> Attended
                            </td>
                            </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <input type="hidden" name="sesid" value="${requestScope.session.getId()}">
            <input type="submit" value="Save">
        </form>
    </body>
</html>
