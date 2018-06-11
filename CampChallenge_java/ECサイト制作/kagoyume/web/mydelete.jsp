<%-- 
    Document   : mydelete
    Created on : 2018/06/10, 12:25:27
    Author     : Sanosuke
--%>
<%@page import="kagoyume.UserDataDTO"%>
<%@page import="java.util.ArrayList"%>
<%
UserDataDTO user = (UserDataDTO)request.getAttribute("user");
String newTime = (String)request.getAttribute("newTime");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員情報 - 削除画面</title>
    </head>
    <body>
        <p>このユーザーをマジで削除しますか？</p>
        名前：<%=user.getName()%><br>
        パスワード：<%=user.getPassword()%><br>
        メール：<%=user.getMail()%><br>
        住所：<%=user.getAddress()%><br>
        合計購入金額：<%=user.getTotal()%><br>
        登録日時：<%=newTime%><br>
        <form action="Mydeleteresult" method="POST">
            <input type="hidden" name="userID" value="<%=user.getUserID()%>">
            <input type="submit" name="btnsubmit" value="はい">
        </form>
            <form action="top.jsp" method="POST">
                <input type="submit" name="btnsubmit" value="トップページへ戻る">
            </form>
    </body>
</html>
