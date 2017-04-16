<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <title>Jquery request</title>
    <script src="js/jquery-3.2.1.min.js"></script>
</head>
    <body>
    <h1>Jquery request example</h1>
    <form action="simple" method="post">
        <label for="username">Your name:</label>
        <input id="username" type="text" name="username" />
        <input type="submit" value="Send" />
    </form>
    <div id="result"></div>
    <a href="index.jsp"> Back </a>
        <script type="text/javascript">
            var frm = $('#form');
            var btn = frm.find('input[type=submit]');
            btn.click(function (f) {
                $.ajax({
                   type: frm.attr('method'),
                    url: frm.attr('action'),
                    data: frm.serialize(),
                    success: function (data) {
                        $('#result').html(data);
                    }
                });
                f.preventDefault();
                return false;
            })
        </script>
    </body>
</html>
