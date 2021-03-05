<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>${institute}</title>
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
                <td>
                    <a href="${pageContext.request.contextPath}/kpi?action=goToFacutly&facultyName=${faculty.getName()}">
                        <c:out value="${faculty.getName()}" />
                    </a>
                </td>
                <td><p><c:out value="${faculty.getStudents().size()}" /></p></td>
            </tr>
            </c:forEach>
        </table>
        <form method="post" action="${pageContext.request.contextPath}/kpi?action=submit">
            <h5>New faculty</h5>
            <dl>
                <dd><input type="text" name="name" placeholder="Enter name" /></dd>
            </dl>
            <button type="submit">Add</button>
        </form>
    </body>
</html>
