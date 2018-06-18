<%-- 
    Document   : mydeleteresult
    Created on : 2018/06/10, 13:06:11
    Author     : Sanosuke
--%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%
JumsHelper jh = JumsHelper.getInstance();
HttpSession hs = request.getSession();
String reURL = "Mydeleteresult";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員情報 - 削除完了画面</title>
    </head>
    <body>
        削除しました<br>
    </body>
    <%if ((String)hs.getAttribute("logPass") == null) {%>
    <%=jh.loginPage(reURL)%>
    <%}else{%>
    <%=jh.logoutPage()%><br>
    <%}%>
    <a href="Mydata">会員情報一覧</a><br>
    <%=jh.home()%>
</html>
