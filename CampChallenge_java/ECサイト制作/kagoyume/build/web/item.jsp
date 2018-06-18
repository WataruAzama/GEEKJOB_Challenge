<%-- 
    Document   : item
    Created on : 2018/06/06, 0:00:09
    Author     : Sanosuke
--%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="java.util.ArrayList"%>
<%
JumsHelper jh = JumsHelper.getInstance();
HttpSession hs = request.getSession();
String reURL = (String)hs.getAttribute("reItem");
String reSearch = (String)hs.getAttribute("reSearch");
    
ArrayList<String> resultSource = (ArrayList<String>)request.getAttribute("resultSource");
    String thum = resultSource.get(0);
    String name = resultSource.get(1);
    String price = resultSource.get(2);
    String headline = resultSource.get(3);
    String rate = resultSource.get(4);
    String code = resultSource.get(5);
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
        個数：
        <select name="count">
            <%for (int i=1; i<=10; i++) {%>
            <option value="<%=i%>"><%=i%></option>
            <%}%>
        </select>
            個
        <input type="submit" name="addItem" value="カートに追加する">
        </form>
        <form action="<%=reSearch%>" method="POST">
            <input type="submit" name="btnsubmit" value="検索結果へ戻る">
        </form>
    </body>
    <%if ((String)hs.getAttribute("logPass") == null) {%>
    <%=jh.loginPage(reURL)%>
    <%}else{%>
    <%=jh.logoutPage()%><br>
    <a href="Myhistory">購入履歴</a><br>
    <%}%>
    <a href="Cart">カートを確認</a><br>
    <%=jh.home()%>
</html>
