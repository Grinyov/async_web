<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <h1>Simple not async example</h1>
    <form action="simple" method="post">
        <label for="username">Input your name:</label>
        <input id="username" type="text" name="username" />
        <input type="submit" value="Send" />
    </form>

    <a href="index.jsp"> Back </a>
</body>
</html>
