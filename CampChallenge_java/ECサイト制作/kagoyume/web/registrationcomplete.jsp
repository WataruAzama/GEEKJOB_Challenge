<%-- 
    Document   : registrationcomplete
    Created on : 2018/06/07, 18:22:59
    Author     : Sanosuke
--%>
<%@page import="kagoyume.UserDataBeans"%>
<%
UserDataBeans udb = (UserDataBeans)request.getAttribute("udb");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員登録 - 登録完了</title>
    </head>
    <body>
        名前：<%=udb.getName()%><br>
        パスワード：<%=udb.getPassword()%><br>
        メール：<%=udb.getMail()%><br>
        住所：<%=udb.getAddress()%><br>
        以上の内容で登録しました。
    </body>
</html>
