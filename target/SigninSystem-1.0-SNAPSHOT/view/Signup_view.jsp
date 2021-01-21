<%--
  Created by IntelliJ IDEA.
  User: longvux
  Date: 19/01/2021
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
    <form action="/signup" method="post">

        <table style="margin: auto;">
            <tr>
                <td>Username</td>
                <td><input type="text" name="username" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td>Role</td>
                <td>
                    <input type="radio" id="customer" name="role" value="customer">
                    <label for="customer">Customer</label><br>
                    <input type="radio" id="admin" name="role" value="admin">
                    <label for="admin">Admin</label><br>
                </td>
            </tr>
        </table>

        <input type="submit" value="Submit" style="display: block; margin: auto;" />
        <input type="hidden" name="from" value="${param.from}">

        <p>
            <c:if test="${requestScope.error != null}">
                <c:out value="${requestScope.error}"/>
            </c:if>
        </p>
    </form>
</body>
</html>
