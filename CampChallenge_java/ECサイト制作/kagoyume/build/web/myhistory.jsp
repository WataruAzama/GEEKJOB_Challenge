<%-- 
    Document   : myhistory
    Created on : 2018/06/14, 12:55:06
    Author     : Sanosuke
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%
JumsHelper jh = JumsHelper.getInstance();
HttpSession hs = request.getSession();
String reURL = "Myhistory";

ArrayList<ArrayList<String>> goodsReview = (ArrayList<ArrayList<String>>)request.getAttribute("goodsReview");
int totalPrice = 0;
for (int i=0; i<goodsReview.size(); i++) {
    totalPrice += Integer.parseInt(goodsReview.get(i).get(2));
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員情報 - 購入履歴</title>
    </head>
    <body>
        <table border="1">
            <tr>
                <th>画像</th>
                <th>商品名</th>
                <th>金額</th>
            </tr>
            <% for (int i=0; i<goodsReview.size(); i++) {%>
            <tr>
                <th><img src="<%=goodsReview.get(i).get(0)%>"></th>
                <th><%=goodsReview.get(i).get(1)%></th>
                <th><%=goodsReview.get(i).get(2)%>円</th>
            </tr>
            <%}%>
        </table>
            <%if (goodsReview.size() == 0) {%>
            購入した商品はありません。<br>
            <%}%>
            合計購入金額：<%=totalPrice%><br>
    </body>
    <%if ((String)hs.getAttribute("logPass") == null) {%>
    <%=jh.loginPage(reURL)%>
    <%}else{%>
    <%=jh.logoutPage()%>
    <%}%><br>
    <a href="Mydata">会員一覧へ</a><br>
    <a href="Cart">カートを確認</a><br>
    <%=jh.home()%>
</html>
