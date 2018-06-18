<%-- 
    Document   : top
    Created on : 2018/06/01, 13:21:33
    Author     : Sanosuke
--%>
<%@page import="kagoyume.JumsHelper"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest;"%>
<%
JumsHelper jh = JumsHelper.getInstance();
HttpSession hs = request.getSession();
String reURL = request.getRequestURL()+"";
String errorStr = "";
if ((String)request.getAttribute("errorStr") != null) {
    errorStr = (String)request.getAttribute("errorStr");
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かごゆめ</title>
    </head>
    <body>
        無課金で買い物した気分になりましょう。<br>
        <form action="Search" method="GET">
            <input type="text" name="retrieval" value="">
            <input type="submit" name="btnsubmit" value="検索">
        </form>
        <%if (!errorStr.equals("")) {%>
        <%=errorStr%>
        <%}%>
    </body>
    <%if ((String)hs.getAttribute("logPass") == null) {%>
    <%=jh.loginPage(reURL)%>
    <%}else{%>
    <%=jh.logoutPage()%><br>
    <a href="Myhistory">購入履歴</a><br>
    <%}%>
    <a href="Mydata">会員情報一覧</a><br>
    <a href="Cart">カートを確認</a><br>
</html>
