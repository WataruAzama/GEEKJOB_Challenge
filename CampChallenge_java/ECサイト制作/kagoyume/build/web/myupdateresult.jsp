<%-- 
    Document   : myupdateresult
    Created on : 2018/06/08, 14:09:05
    Author     : Sanosuke
--%>
<%@page import="kagoyume.UserDataBeans"%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%
JumsHelper jh = JumsHelper.getInstance();
HttpSession hs = request.getSession();
String reURL = "Myupdateresult";

UserDataBeans user = (UserDataBeans)hs.getAttribute("resultUpdateUDB");
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
    <%if ((String)hs.getAttribute("logPass") == null) {%>
    <%=jh.loginPage(reURL)%>
    <%}else{%>
    <%=jh.logoutPage()%>
    <%}%><br>
    <a href="Mydata">会員情報一覧</a><br>
    <%=jh.home()%>
</html>
