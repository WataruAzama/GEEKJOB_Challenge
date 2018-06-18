<%-- 
    Document   : search
    Created on : 2018/06/05, 18:25:04
    Author     : Sanosuke
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%
JumsHelper jh = JumsHelper.getInstance();
HttpSession hs = request.getSession();
String reURL = (String)hs.getAttribute("reSearch");
    
    String retrieval = (String)hs.getAttribute("retrieval");
    String eRoot = (String)request.getAttribute("eRoot");
    ArrayList<String> thum = (ArrayList<String>)request.getAttribute("thum");
    ArrayList<String> name = (ArrayList<String>)request.getAttribute("name");
    ArrayList<String> price = (ArrayList<String>)request.getAttribute("price");
    ArrayList<String> code = (ArrayList<String>)request.getAttribute("code");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>検索結果</title>
    </head>
    <body>
        検索ワード：<%=retrieval%><br>
        ヒット数：<%=eRoot%>
        <table border="1">
            <tr>
                <th>画像</th>
                <th>商品名</th>
                <th>金額</th>
            </tr>
            <% for (int i=0; i<name.size(); i++) {%>
            <tr>
                <th><img src="<%=thum.get(i)%>"></th>
                <th><%=name.get(i)%>
                    <form action="Item" method="GET">
                        <input type="hidden" name="code" value="<%=code.get(i)%>">
                        <input type="submit" name="btnsubmit" value="商品詳細">
                    </form>
                </th>
                <th><%=price.get(i)%>円</th>
            </tr>
            <%}%>
        </table>
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
