<%-- 
    Document   : buycomplete
    Created on : 2018/06/06, 22:36:05
    Author     : Sanosuke
--%>
<%@page import="kagoyume.JumsHelper"%>
<%
JumsHelper jh = JumsHelper.getInstance();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入 - 完了画面</title>
    </head>
    <body>
        購入が完了しました<br>
    </body>
    <%=jh.logoutPage()%><br>
    <a href="Myhistory">購入履歴</a><br>
    <%=jh.home()%>
</html>
