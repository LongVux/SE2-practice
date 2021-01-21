<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Homepage</title>
</head>
<body>
<h1>Homepage</h1>
<br/>
<h2>welcome!</h2>

<c:if test="${sessionScope.role eq null}" >
    <button onclick="window.location.href='/signin?from=/homepage'">Sign in</button>
    <button onclick="window.location.href='/signup?from=/homepage'">Sign up</button>
</c:if>

<c:if test="${sessionScope.role != null}" >
    <button onclick="window.location.href='/signout'">Sign out</button>
</c:if>

<button onclick="window.location.href='/explore'">Explore!</button>
</body>
</html>