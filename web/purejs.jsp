
<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 14.04.2017
  Time: 1:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <title>Pure js example</title>
    <script type="text/javascript" language="JavaScript">
        function ajaxRequest() {
            var activexmodes = ["msxml2.XMLHTTP", "Microsoft.XMLHTTP"]; // activeX vers check
            if (window.ActiveXObject){
                for (var i = 0; i < activexmodes.length; i++) {
                    try {
                        return ActiveXObject(activexmodes[i]);
                    }
                    catch (exception){
                        // suppress error
                    }
                }
            }
            else if (window.XMLHttpRequest) // if other browsers
                return nre XMLHttpRequest();
            else return false;
        }

        function sendPostRequest() {
            var postrequest = new ajaxRequest();
            postrequest.onreadystatechange = function () {
                if (postrequest.readyState === 4) {
                    if (postrequest.status === 200 || window.location.href.indexOf("http") === -1){
                        document.getElementById("username").value = "";
                        document.getElementById("result").innerHTML = postrequest.responseText;
                    }
                    else {
                        alert(" An error has occured making the request")
                    }
                }
            };
            var username = encodeURIComponent(document.getElementById("username").value);
            var parameters = "username" + username;
            postrequest.open("post", "say-hello", true);
            postrequest.setRequestHeader("content-type", "application/x-www-form-urlencoded");
            postrequest.send(parameters);
        }
    </script>
</head>
<body>
<h1>Pure js request example</h1>
<form action="simple" method="post">
    <label for="username">Input your name:</label>
    <input id="username" type="text" name="username" />
    <input type="button" value="Send" onclick="sendPostRequest()"/>
</form>
<div id="result"></div>
<a href="index.jsp"> Back </a>
</body>
</html>
