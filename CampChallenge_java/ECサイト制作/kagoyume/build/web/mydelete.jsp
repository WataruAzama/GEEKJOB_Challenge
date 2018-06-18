<%-- 
    Document   : mydelete
    Created on : 2018/06/10, 12:25:27
    Author     : Sanosuke
--%>
<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%
JumsHelper jh = JumsHelper.getInstance();
HttpSession hs = request.getSession();
String reURL = "Mydelete";

UserDataDTO user = (UserDataDTO)hs.getAttribute("deleteUser");
String newTime = (String)hs.getAttribute("deleteTime");
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
    </body>
    <%if ((String)hs.getAttribute("logPass") == null) {%>
    <%=jh.loginPage(reURL)%>
    <%}else{%>
    <%=jh.logoutPage()%><br>
    <%}%>
    <a href="Mydata">会員情報一覧</a><br>
    <%=jh.home()%>
</html>
