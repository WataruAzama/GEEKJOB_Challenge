<%-- 
    Document   : myupdate
    Created on : 2018/06/08, 13:34:57
    Author     : Sanosuke
--%>
<%@page import="kagoyume.UserDataBeans"%>
<%
UserDataBeans udb = (UserDataBeans)request.getAttribute("userUDB");
int userID = (Integer)request.getAttribute("userID");
String str = "";
if ((String)request.getAttribute("str") != null) {
    str = (String)request.getAttribute("str");
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員情報 - 更新画面</title>
    </head>
    <body>
        <form action="Myupdateresult" method="POST">
        ユーザー名：<br>
        <input type="text" name="upName" value="<%=udb.getName()%>"><br>
        パスワード：<br>
        <input type="text" name="upPass" value="<%=udb.getPassword()%>"><br>
        メールアドレス：<br>
        <input type="text" name="upMail" value="<%=udb.getMail()%>"><br>
        住所：<br>
        <input type="text" name="upAddress" value="<%=udb.getAddress()%>"><br>
        <input type="hidden" name="userID" value="<%=userID%>">
        <input type="submit" name="btnsubmit" value="更新">
        </form>
        <%if (!str.equals("")) {%>
        <br>
        <%=str%>
        <%}%>
    </body>
</html>