<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Simple example</h1>
        <form action="simple" method="post">
            <label for="username">Input your name:</label>
            <input id="username" type="text" name="username" />
            <input type="submit" value="Send" />
        </form>
        <c:choose>
            <c:when test="${error != null}">
                <p style="color: red;">${error}</p>
            </c:when>
            <c:when test="${message != null}">
                <p style="color: green;">${message}</p>
            </c:when>
        </c:choose>
        <a href="index.jsp">Back</a>
    </body>
</html>
