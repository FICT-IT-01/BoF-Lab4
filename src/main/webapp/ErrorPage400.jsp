<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Validation Error</title>
    <link rel="stylesheet" href="css/shared.css">
</head>
<body>
    <table>
        <tr>
            <th>Error</th>
        </tr>

        <c:forEach var="error" items="${validationResult.getErrors()}">
            <tr>
                <td><c:out value="${error}"/></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
