<%-- 
    Document   : login
    Created on : 2018/06/06, 21:09:47
    Author     : Sanosuke
--%>
<%@page import="kagoyume.UserDataBeans"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.JumsHelper"%>
<%
JumsHelper jh = JumsHelper.getInstance();
HttpSession hs = request.getSession();
String reURL = "Registration";

UserDataBeans udb = new UserDataBeans();
if ((UserDataBeans)hs.getAttribute("udb") != null) {
    udb = (UserDataBeans)hs.getAttribute("udb");
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員登録 - 入力画面</title>
    </head>
    <body>
        <form action="Registrationconfirm" method="POST">
        ユーザー名：<br>
        <input type="text" name="newName" value="<%=udb.getName()%>"><br>
        パスワード：<br>
        <input type="text" name="newPass" value="<%=udb.getPassword()%>"><br>
        メールアドレス：<br>
        <input type="text" name="newMail" value="<%=udb.getMail()%>"><br>
        住所：<br>
        <input type="text" name="newAddress" value="<%=udb.getAddress()%>"><br>
        <%if ((String)hs.getAttribute("usedPassword") != null) {%>
        <%=(String)hs.getAttribute("usedPassword")%>
        <%}%>
        <input type="hidden" name="parameters" value="parameters">
        <input type="submit" name="btnsubmit" value="確認フォームへ">
        </form>
    </body>
    <%if ((String)hs.getAttribute("logPass") == null) {%>
    <%=jh.loginPage(reURL)%>
    <%}else{%>
    <%=jh.logoutPage()%>
    <%}%><br>
    <%=jh.home()%>
</html>
