<%-- 
    Document   : cart
    Created on : 2018/06/06, 22:14:16
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
        <title>カート</title>
    </head>
    <body>
        <form action="Buyconfirm" method="POST">
            <table border="1">
                <tr>
                    <th>画像</th>
                    <th>商品名</th>
                    <th>金額</th>
                </tr>
                <% for (int i=0; i<goods.size(); i++) {%>
                <tr>
                    <td><img src="<%=goods.get(i).get(0)%>"></td>
                    <td><%=goods.get(i).get(1)%></td>
                    <td><%=goods.get(i).get(2)%>円</td>
                </tr>
                <%}%>
            </table>
            合計金額：<%=num%>
            <input type="submit" name="btnsubmit" value="購入確認画面へ">
        </form>
    </body>
</html>
