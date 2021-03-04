<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>JSP - Hello World</title>
        <style>
            *{
                text-align: center;
                font-family: Arial;
            }
            td{
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <table border="2px solid">
            <tr>
                <td>
                    <b>Faculty name</b>
                </td>
                <td>
                    <b>Students amount</b>
                </td>
            </tr>
            <c:forEach var="faculty" items="${faculties}">
            <tr>
                <td><p><c:out value="${faculty.getName()}" /></p></td>
                <td><p><c:out value="${faculty.getStudents().size()}" /></p></td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
