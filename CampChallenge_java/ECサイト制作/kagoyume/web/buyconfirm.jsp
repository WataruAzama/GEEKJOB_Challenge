<%-- 
    Document   : buyconfirm
    Created on : 2018/06/06, 23:28:20
    Author     : Sanosuke
--%>
<%@page import="java.util.ArrayList"%>

<%
ArrayList<ArrayList<String>> goods = (ArrayList<ArrayList<String>>)request.getAttribute("goods");
//金額をintにして計算する
int num = 0;
for (int i=0; i<goods.size(); i++) {
    num += Integer.parseInt(goods.get(i).get(2));
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
        <form action="Buyconplete" method="POST">
            <table border="1">
                <tr>
                    <th>画像</th>
                    <th>商品名</th>
                    <th>金額</th>
                </tr>
                <% for (int i=0; i<goods.size(); i++) {%>
                <tr>
                    <th><img src="<%=goods.get(i).get(0)%>"></th>
                    <th><%=goods.get(i).get(1)%></th>
                    <th><%=goods.get(i).get(2)%>円</th>
                </tr>
                <%}%>
            </table>
            合計金額：<%=num%><br>
            配送方法：<br>
            <input type="radio" name="delivery" value="slow">鈍行<br>
            <input type="radio" name="delivery" value="nomal" checked="checked">通常<br>
            <input type="radio" name="delivery" value="fast">速達<br>
            <input type="submit" name="btnsubmit" value="上記の内容で購入する">
        </form>
        <form action="Cart" method="POST">
            <input type="submit" name="btnsubmit" value="カートに戻る">
        </form>
    </body>
</html>
