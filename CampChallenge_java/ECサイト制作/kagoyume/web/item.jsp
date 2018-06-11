<%-- 
    Document   : item
    Created on : 2018/06/06, 0:00:09
    Author     : Sanosuke
--%>
<%@page import="javax.servlet.http.HttpSession"%>
<%
    String thum = (String)request.getAttribute("thum");
    String name = (String)request.getAttribute("name");
    String price = (String)request.getAttribute("price");
    String headline = (String)request.getAttribute("headline");
    String rate = (String)request.getAttribute("rate");
    String code = (String)request.getAttribute("code");
    
HttpSession hs = request.getSession();
String retrieval =(String)hs.getAttribute("retrieval");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>商品詳細</title>
    </head>
    <body>
        <form action="Add" method="POST">
        画像：<img src="<%=thum%>"><br>
        商品名：<%=name%><br>
        金額：<%=price%><br>
        商品詳細：<%=headline%><br>
        商品評価(5段階):<%=rate%><br>
        <input type="hidden" name="thum" value="<%=thum%>">
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="price" value="<%=price%>">
        <input type="hidden" name="headline" value="<%=headline%>">
        <input type="hidden" name="rate" value="<%=rate%>">
        <input type="hidden" name="code" value="<%=code%>">
        <input type="submit" name="addItem" value="カートに追加する">
        </form>
        <form action="Search" method="GET">
            <input type="hidden" name="retrieval" value="<%=retrieval%>">
            <input type="submit" name="btnsubmit" value="検索結果へ戻る">
        </form>
    </body>
</html>
