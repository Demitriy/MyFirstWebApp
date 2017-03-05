<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>It`s working</p>
<table border="2px">
    <th>Login</th>
    <th>Password</th>
    <th>Name</th>
    <th>Surname</th>
    <th>Number</th>
    <c:forEach var="tmp" items="${list}">
        <tr>
            <c:forEach var="inner" items="${tmp}">
                <td>
                    ${inner}
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
<c:forEach var="tmp" items="${list2}">
    <p>${tmp}</p>
</c:forEach>
<a href = "/page/create">Click me to create</a>
</body>
</html>
