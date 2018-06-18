<%-- 
    Document   : registrationconfirm
    Created on : 2018/06/06, 21:51:10
    Author     : Sanosuke
--%>
<%@page import="kagoyume.UserDataBeans"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%
JumsHelper jh = JumsHelper.getInstance();
HttpSession hs = request.getSession();
String reURL = "Registrationconfirm";

UserDataBeans udb = (UserDataBeans)hs.getAttribute("udb");
ArrayList<String> checkList = udb.checkList();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員登録 - 確認画面</title>
    </head>
    <body>
        <%if (checkList.size() == 0) {%>
            <form action="Registrationcomplete" method="POST">
                名前：<%=udb.getName()%><br>
                パスワード：<%=udb.getPassword()%><br>
                メール：<%=udb.getMail()%><br>
                住所：<%=udb.getAddress()%><br>
                上記の内容で登録いたします。よろしいですか？<br>
                <input type="hidden" name="hidden" value="hidden">
                <input type="submit" name="btnsubmit" value="はい">
            </form>
            <form action="Registration" method="POST">
                <input type="submit" name="btnsubmit" value="いいえ">
            </form>
        <%}else{%>
            <b>入力が不完全です。</b><br>
            <%=jh.checkInput(checkList)%>
        <form action="Registration" method="POST">
            <input type="submit" name="btnsubmit" value="入力画面へ戻る">
        </form>
        <%}%>
    </body>
    <%if ((String)hs.getAttribute("logPass") == null) {%>
    <%=jh.loginPage(reURL)%>
    <%}else{%>
    <%=jh.logoutPage()%>
    <%}%><br>
    <%=jh.home()%>
</html>
