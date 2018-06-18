<%-- 
    Document   : error
    Created on : 2018/06/10, 15:33:52
    Author     : Sanosuke
--%>
<%@page import="kagoyume.JumsHelper"%>
<%
JumsHelper jh = JumsHelper.getInstance();
String error = (String)request.getAttribute("error");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>error</title>
    </head>
    <body>
        エラーが発生しました。以下の項目を確認してください。<br>
        <%=error%><br>
    </body>
    <%=jh.home()%>
</html>
