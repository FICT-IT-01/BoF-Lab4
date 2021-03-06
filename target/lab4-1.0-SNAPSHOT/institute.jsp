<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>${institute}</title>
        <link rel="stylesheet" href="css/shared.css">
    </head>
    <body>
    <div class="container">
        <form method="post" action="${pageContext.request.contextPath}/kpi?action=submit">
            <h3>New faculty</h3>

            <label for="name">Faculty Name</label>
            <input type="text" id="name" name="name" placeholder="Faculty name..">
            <input type="submit" value="Submit">
        </form>
    </div>

    <hr>

    <table id="myTable">
        <tr>
            <th>Faculty Name</th>
            <th>Students Amount</th>
        </tr>

        <c:forEach var="faculty" items="${faculties}">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/kpi?action=goToFacutly&facultyName=${faculty.getName()}">
                    <c:out value="${faculty.getName()}" /></a>
                </td>
                <td><c:out value="${faculty.getStudents().size()}" /></td>
            </tr>
        </c:forEach>
    </table>
    </body>
</html>
