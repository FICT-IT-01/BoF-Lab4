<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Admin Panel</title>
    <link rel="stylesheet" href="css/shared.css">
</head>
<body>
    <div class="container">
        <form method="post" action="${pageContext.request.contextPath}?action=submit">
            <h3>New Institute</h3>

            <label for="instituteName">Institute Name</label>
            <input type="text" id="instituteName" name="instituteName" placeholder="Faculty name..">
            <input type="submit" value="Submit">
        </form>
    </div>

    <hr>

    <h3>Institutes</h3>

    <table>
        <tr>
            <th>Name</th>
            <th>Faculties</th>
            <th>Students</th>
        </tr>

        <c:forEach var="institute" items="${institutes}">
            <tr>
                <td><a href="${pageContext.request.contextPath}/institute?instituteName=${institute.getName()}">
                    <c:out value="${institute.getName()}" /></a></td>
                <td><c:out value="${institute.getFaculties().size()}"/></td>
                <td><c:out value="${institute.getTotalAmountOfStudents()}"/></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
