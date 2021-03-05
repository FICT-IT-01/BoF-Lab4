<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>${faculty.getName()}</title>
        <style>
            *{
                font-family: Arial;
            }
            td{
                padding: 10px;
                text-align: center;
            }
        </style>
    </head>
    <body>
    <a href="${pageContext.request.contextPath}/kpi">👈Go back</a>
    <table border="2px solid">
        <tr>
            <td>
                <b>Name</b>
            </td>
        </tr>
        <c:forEach var="student" items="${faculty.getStudents()}">
            <tr>
                <td>
                    <c:out value="${student.getName()}" />
                </td>
            </tr>
        </c:forEach>
    </table>
    <form method="post" action="${pageContext.request.contextPath}/faculty?action=submit">
        <h5>New student</h5>
        <dl>
            <dd><input type="text" name="name" placeholder="Enter name" /></dd>
        </dl>
        <button type="submit">Add</button>
    </form>
    </body>
</html>
