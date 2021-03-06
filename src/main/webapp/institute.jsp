<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>${institute.getName()}</title>
        <link rel="stylesheet" href="css/shared.css">
    </head>
    <body>

    <c:if test="${validationResult != null}">
        <script>
            alert("${validationResult.getErrorsString()}")
        </script>
    </c:if>

    <p><a href="${pageContext.request.contextPath}/main">Back to main page</a></p>

    <hr>
    <div class="container">
        <form method="post" action="${pageContext.request.contextPath}/institute?action=submit&instituteName=${institute.getName()}">
            <h3>New faculty</h3>

            <label for="facultyName">Faculty Name</label>
            <input type="text" id="facultyName" name="facultyName" placeholder="Faculty name..">
            <input type="submit" value="Submit">
        </form>
    </div>

    <hr>
    <table>
        <tr>
            <td>Total amount of students </td>
            <td><c:out value="${institute.getTotalAmountOfStudents()}"/></td>
        </tr>
        <tr>
            <td>Biggest Faculty </td>
            <td><c:out value="${institute.getBiggestFaculty().getName()}"/></td>
        </tr>
    </table>

    <hr>

    <h3>Students with average mark in range from 95 to 100</h3>

    <table>
        <tr>
            <th>Student book id</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Phone number</th>
            <th>Average mark</th>
        </tr>

        <c:forEach var="student" items="${institute.getStudentsWithMarkInRange()}">
            <tr>
                <td><c:out value="${student.getBookNumber()}"/></td>
                <td><c:out value="${student.getFirstName()}"/></td>
                <td><c:out value="${student.getLastName()}"/></td>
                <td><c:out value="${student.getPhoneNumber()}"/></td>
                <td><c:out value="${student.getAverageMark()}"/></td>
            </tr>
        </c:forEach>
    </table>

    <hr>

    <h3>Faculties at <c:out value="${institute.getName()}"/> </h3>

    <table>
        <tr>
            <th>Faculty Name</th>
            <th>Students Amount</th>
        </tr>

        <c:forEach var="faculty" items="${institute.getFaculties()}">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/faculty?instituteName=${institute.getName()}&facultyName=${faculty.getName()}">
                    <c:out value="${faculty.getName()}" /></a>
                </td>
                <td><c:out value="${faculty.getStudents().size()}" /></td>
            </tr>
        </c:forEach>
    </table>
    </body>
</html>
