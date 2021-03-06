<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <link rel="stylesheet" href="css/shared.css">
        <title>${faculty.getName()}</title>
    </head>

    <div class="container">
        <form method="post" action="${pageContext.request.contextPath}/faculty?action=submit&faculty=${faculty.getName()}">
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" name="firstName" placeholder="Your first name..">

            <label for="lastName">Last Name</label>
            <input type="text" id="lastName" name="lastName" placeholder="Your last name..">

            <label for="bookNumber">Student Book Number</label>
            <input type="text" id="bookNumber" name="bookNumber" placeholder="Your phone number..">

            <label for="phoneNumber">Phone Number</label>
            <input type="text" id="phoneNumber" name="phoneNumber" placeholder="Your last name..">

            <label for="averageMark">Average Mark</label>
            <input type="text" id="averageMark" name="averageMark" placeholder="Your last name..">

            <input type="submit" value="Submit">
        </form>
    </div>

    <hr>

    <table id="myTable">
        <tr>
            <th>Student book id</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Phone number</th>
            <th>Average mark</th>
        </tr>

        <c:forEach var="student" items="${faculty.getStudents()}">
            <tr>
                <td><c:out value="${student.getBookNumber()}"/></td>
                <td><c:out value="${student.getFirstName()}"/></td>
                <td><c:out value="${student.getLastName()}"/></td>
                <td><c:out value="${student.getPhoneNumber()}"/></td>
                <td><c:out value="${student.getAverageMark()}"/></td>
            </tr>
        </c:forEach>
    </table>

    </body>
</html>
