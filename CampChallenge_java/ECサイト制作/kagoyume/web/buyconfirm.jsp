<%-- 
    Document   : buyconfirm
    Created on : 2018/06/06, 23:28:20
    Author     : Sanosuke
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.JumsHelper"%>
<%
JumsHelper jh = JumsHelper.getInstance();
ArrayList<ArrayList<String>> goods = (ArrayList<ArrayList<String>>)request.getAttribute("goods");

//金額をintにして計算する
int num = 0;
for (int i=0; i<goods.size(); i++) {
    num += Integer.parseInt(goods.get(i).get(2)) * Integer.parseInt(goods.get(i).get(7));
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入 - 確認画面</title>
    </head>
    <body>
        <form action="Buycomplete" method="POST">
            <table border="1">
                <tr>
                    <th>画像</th>
                    <th>商品名</th>
                    <th>金額</th>
                    <th>個数</th>
                </tr>
                <% for (int i=0; i<goods.size(); i++) {%>
                <tr>
                    <th><img src="<%=goods.get(i).get(0)%>"></th>
                    <th><%=goods.get(i).get(1)%></th>
                    <th><%=goods.get(i).get(2)%>円</th>
                    <th><%=goods.get(i).get(7)%>個</th>
                </tr>
                <%}%>
            </table>
            合計金額：<%=num%>円<br>
            配送方法：<br>
            <input type="radio" name="delivery" value="1">鈍行<br>
            <input type="radio" name="delivery" value="2" checked="checked">通常<br>
            <input type="radio" name="delivery" value="3">速達<br>
            <input type="hidden" name="money" value="<%=num%>">
            <input type="submit" name="btnsubmit" value="上記の内容で購入する">
        </form>
    </body>
    <%=jh.logoutPage()%><br>
    <a href="Cart">カートを確認</a><br>
    <a href="Myhistory">購入履歴</a><br>
    <%=jh.home()%>
</html>
