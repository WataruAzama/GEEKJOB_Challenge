<%-- 
    Document   : add
    Created on : 2018/06/06, 17:33:12
    Author     : Sanosuke
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%
JumsHelper jh = JumsHelper.getInstance();
HttpSession hs = request.getSession();
String reURL = "Add";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>カートに追加</title>
    </head>
    <body>
        カートに追加しました。<br>
    </body>
    <form action="Cart" method="POST">
        <input type="submit" name="btnsubmit" value="カートページへ移動">
    </form>
    <a href="<%=(String)hs.getAttribute("reSearch")%>">検索結果へ戻る</a><br>
    <a href="<%=(String)hs.getAttribute("reItem")%>">商品詳細へ戻る</a><br>
    <%if ((String)hs.getAttribute("logPass") == null) {%>
    <%=jh.loginPage(reURL)%>
    <%}else{%>
    <%=jh.logoutPage()%><br>
    <a href="Myhistory">購入履歴</a><br>
    <%}%>
    <%=jh.home()%>
</html>
