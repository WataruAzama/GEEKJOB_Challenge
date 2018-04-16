<%-- 
    Document   : yokohama
    Created on : 2018/04/16, 17:52:31
    Author     : Sanosuke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%
        request.setCharacterEncoding("UTF-8");
            out.print(request.getParameter("name"));
            out.print(request.getParameter("male"));
            out.print(request.getParameter("female"));
            out.print(request.getParameter("syumi"));
        %>
        
    </body>
</html>
