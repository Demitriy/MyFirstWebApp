<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
 <form action="/auth" method="GET" name="auth">
     <p>Login: <input type="text" name="login" value="Enter login"></p>
     <p>Password: <input type="password" name="password" value="Some password"></p>
     <button type="submit" name="authButton">Sign in</button>
 </form>
 <p><c:if test="${flag}">Access denied</c:if></p>
</body>
</html>
