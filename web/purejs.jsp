<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <script type="text/javascript" language="javascript">
        function ajaxRequest() {
            var activexmodes = ["Msxml2.XMLHTTP", "Microsoft.XMLHTTP"]; //activeX versions to check for in IE
            if (window.ActiveXObject) { //Test for support for ActiveXObject in IE first (as XMLHttpRequest in IE7 is broken)
                for (var i = 0; i < activexmodes.length; i++) {
                    try {
                        return new ActiveXObject(activexmodes[i]);
                    }
                    catch (ex) {
                        //suppress error
                    }
                }
            }
            else if (window.XMLHttpRequest) // if Mozilla, Safari etc.
                return new XMLHttpRequest();
            else
                return false;
        }
        function sendPostRequest() {
            var mypostrequest = new ajaxRequest();
            mypostrequest.onreadystatechange = function () {
                if (mypostrequest.readyState === 4) {
                    if (mypostrequest.status === 200 || window.location.href.indexOf("http") === -1) {
                        document.getElementById("username").value = "";
                        document.getElementById("result").innerHTML = mypostrequest.responseText;
                    }
                    else {
                        alert("An error has occured making the request");
                    }
                }
            };
            var username = encodeURIComponent(document.getElementById("username").value);
            var parameters = "username=" + username;
            mypostrequest.open("post", "say-hello", true);
            mypostrequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            mypostrequest.send(parameters);
        }
    </script>
</head>
<body>
<h1>JavaScript request</h1>
<form>
    <label for="username">Your name:</label>
    <input id="username" type="text" name="username" />
    <input type="button" value="Send" onclick="sendPostRequest()" />
</form>
<div id="result"></div>
<a href="index.jsp">Back</a>
</body>
</html>