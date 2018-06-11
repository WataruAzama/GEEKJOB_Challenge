<%-- 
    Document   : myupdateresult
    Created on : 2018/06/08, 14:09:05
    Author     : Sanosuke
--%>
<%@page import="kagoyume.UserDataBeans"%>

<%
UserDataBeans user = (UserDataBeans)request.getAttribute("user");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員情報 - 更新完了画面</title>
    </head>
    <body>
        名前：<%=user.getName()%><br>
        パスワード：<%=user.getPassword()%><br>
        メール：<%=user.getMail()%><br>
        住所：<%=user.getAddress()%><br>
        以上の内容で更新しました。<br>
    </body>
</html>
