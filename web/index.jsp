<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Start page</title>
  </head>
  <body>
    <h1>Demo how make async web</h1>
    <h2>Send requests examples</h2>
      <ul>
          <li><a href="simple.jsp">Simple request</a></li>
          <li><a href="purejs.jsp">JavaScript ajax request</a></li>
          <li><a href="jquery.jsp">JQuery ajax request</a></li>
      </ul>
    <h2>Process requests examples</h2>
      <ul>
          <li><a href="async-simple">Simple async request</a></li>
          <li><a href="async-executor">Example with custom async executor</a></li>
          <li><a href="executor-pool">Example with executor pool</a></li>
      </ul>
    <h2>Spring process requests examples</h2>
    <ul>
        <li><a href="spring-hello">Spring simple async request</a></li>
        <li><a href="async-executor">Example with custom async executor</a></li>
        <li><a href="executor-pool">Example with executor pool</a></li>
    </ul>
  </body>
</html>