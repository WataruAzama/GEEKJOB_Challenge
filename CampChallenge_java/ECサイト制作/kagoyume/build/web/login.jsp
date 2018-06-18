<%-- 
    Document   : login
    Created on : 2018/06/10, 21:35:33
    Author     : Sanosuke
--%>
<%@page import="kagoyume.JumsHelper"%>
<%
JumsHelper jh = JumsHelper.getInstance();
//存在しないデータを入力された場合
String noData = "";
if ((String)request.getAttribute("noData") != null) {
    noData = "noData";
}
//空文字入力された場合
String erorrStr = "";
if ((String)request.getAttribute("erorrStr") != null) {
    erorrStr = (String)request.getAttribute("erorrStr");
}
//ログイン失敗時に記入済み文字を残す処理
String sName = ""; 
if ((String)request.getAttribute("loginName") != null) {
    sName = (String)request.getAttribute("loginName");
}
String sPassword = "";
if ((String)request.getAttribute("loginPassword") != null) {
    sPassword = (String)request.getAttribute("loginPassword");
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ログインページ</title>
    </head>
    <body>
        登録済みの情報を入力してください。<br>
        <form action="Login" method="POST">
            名前：<input type="text" name="loginName" value="<%=sName%>"><br>
            パスワード：<input type="text" name="loginPassword" value="<%=sPassword%>"><br>
            <input type="submit" name="btnsubmit" value="ログイン"><br>
        </form>
        <%if (!erorrStr.equals("")) {%>
        <%=erorrStr%><br>
        <%}%>
        <%if (noData.equals("noData")) {%>
        名前またはパスワードが違います。<br>
        <%}%>
        <a href="Registration">新規会員登録</a>
        <a href="Mydata">会員情報一覧</a>
    </body>
    <%=jh.home()%>
</html>
