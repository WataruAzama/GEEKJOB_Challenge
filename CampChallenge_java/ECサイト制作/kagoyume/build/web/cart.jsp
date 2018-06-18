<%-- 
    Document   : cart
    Created on : 2018/06/06, 22:14:16
    Author     : Sanosuke
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%
JumsHelper jh = JumsHelper.getInstance();
HttpSession hs = request.getSession();

String userPass = "";
if ((String)hs.getAttribute("logPass") != null) {
    userPass = (String)hs.getAttribute("logPass");
}

ArrayList<ArrayList<String>> goods = new ArrayList();
if ((ArrayList<ArrayList<String>>)hs.getAttribute(userPass+"goods") != null) {
    goods = (ArrayList<ArrayList<String>>)hs.getAttribute(userPass+"goods");
}
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
        <title>カートページ</title>
    </head>
    <body>
        <form id="purchase" action="Buyconfirm" method="POST"></form>
            <table border="1">
                <tr>
                    <th>画像</th>
                    <th>商品名</th>
                    <th>金額</th>
                    <th>個数</th>
                    <th>削除</th>
                </tr>
                <% for (int i=0; i<goods.size(); i++) {%>
                <tr>
                    <td><img src="<%=goods.get(i).get(0)%>"></td>
                    <td><a href="<%=goods.get(i).get(6)%>"><%=goods.get(i).get(1)%></a></td>
                    <td><%=goods.get(i).get(2)%>円</td>
                    <td><%=goods.get(i).get(7)%>個</td>
                    <td>
                        <form  action="Cart" method="POST">
                            <select name="deleteCount">
                                <%for (int k=1; k<=Integer.parseInt(goods.get(i).get(7)); k++) {%>
                                <option value="<%=k%>"><%=k%></option>
                                <%}%>
                            </select>
                                個
                                <input type="hidden" name="delete" value="<%=i%>">
                                <input type="submit" name="btnsubmit" value="削除">
                        </form>
                    </td>
                </tr>
                <%}%>
            </table>
            合計金額：<%=num%>円<br>
            <%if (goods.size() != 0) {%>
                <input form="purchase" type="submit" name="btnsubmit" value="購入する">
            <%}%>
    </body>
    <%=jh.logoutPage()%><br>
    <a href="Myhistory">購入履歴</a><br>
    <%if ((String)hs.getAttribute("reSearch") != null) {%>
    <a href="<%=(String)hs.getAttribute("reSearch")%>">検索結果へ戻る</a><br>
    <%}%>
    <%=jh.home()%>
</html>
