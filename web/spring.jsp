<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <script src="js/jquery-2.1.3.min.js"></script>
</head>
<body>
<h1>JQuery request</h1>
<form id="formx" method="post" action="spring-hello">
    <label for="username">Your name:</label>
    <input id="username" type="text" name="username" />
    <input type="submit" value="Send" />
</form>
<div id="result"></div>
<a href="index.jsp">Back</a>
<script type="text/javascript">
    var frm = $('#formx');
    var btn = frm.find('input[type=submit]');
    btn.click(function (e) {
        $.ajax({
            type: frm.attr('method'),
            url: frm.attr('action'),
            data: frm.serialize(),
            success: function (data) {
                $('#result').html(data);
            }
        });
        e.preventDefault();
        return false;
    });
</script>
</body>
</html>