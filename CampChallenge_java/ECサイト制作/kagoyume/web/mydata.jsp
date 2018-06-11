<%-- 
    Document   : mydata
    Created on : 2018/06/08, 11:19:54
    Author     : Sanosuke
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.UserDataDTO"%>
<%
ArrayList<UserDataDTO> userAl = (ArrayList<UserDataDTO>)request.getAttribute("userAl");
String str = null;
if ((String)request.getAttribute("str") == null) {
    str = (String)request.getAttribute("str");
}
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員情報 - 確認画面</title>
    </head>
    <body>
        <table border="1">
            <tr>
                <th>名前</th>
                <th>パスワード</th>
                <th>メール</th>
                <th>住所</th>
                <th>合計購入金額</th>
                <th>登録日</th>
                <th>情報の更新</th>
                <th>情報の削除</th>
            </tr>
            <%for (int i=0; i<userAl.size(); i++) {%>
            <tr>
                <td><%=userAl.get(i).getName()%></td>
                <td><%=userAl.get(i).getPassword()%></td>
                <td><%=userAl.get(i).getMail()%></td>
                <td><%=userAl.get(i).getAddress()%></td>
                <td><%=userAl.get(i).getTotal()%></td>
                <td><%=userAl.get(i).getNewDate()%></td>
            <form action="Myupdate" method="POST">
                <input type="hidden" name="alNum" value="<%=i%>">
                <td><input type="submit" name="btnsubmit" value="更新画面へ"></td>
            </form>
            <form action="Mydelete" method="POST">
                <input type="hidden" name="alNum" value="<%=i%>">
                <td><input type="submit" name="btnsubmit" value="削除画面へ"></td>
            </form>
            </tr>
            <%}%>
        </table>
        <%if (str != null) {%>
        <%=str%>
        <%}%>
    </body>
</html>
