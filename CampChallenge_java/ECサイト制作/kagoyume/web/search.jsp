<%-- 
    Document   : search
    Created on : 2018/06/05, 18:25:04
    Author     : Sanosuke
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.JumsHelper"%>

<%
JumsHelper jh = JumsHelper.getInstance();
HttpSession hs = request.getSession();
    
    String retrieval = (String)request.getAttribute("retrieval");
    String eRoot = (String)request.getAttribute("eRoot");
    ArrayList<String> thum = (ArrayList<String>)request.getAttribute("thum");
    ArrayList<String> name = (ArrayList<String>)request.getAttribute("name");
    ArrayList<String> price = (ArrayList<String>)request.getAttribute("price");
    ArrayList<String> headline = (ArrayList<String>)request.getAttribute("headline");
    ArrayList<String> rate = (ArrayList<String>)request.getAttribute("rate");
    ArrayList<String> code = (ArrayList<String>)request.getAttribute("code");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>検索結果</title>
    </head>
    <body>
        検索ワード：<%=retrieval%><br>
        ヒット数：<%=eRoot%>
        <table border="1">
            <tr>
                <th>画像</th>
                <th>商品名</th>
                <th>金額</th>
            </tr>
            <% for (int i=0; i<name.size(); i++) {%>
            <tr>
                <th><img src="<%=thum.get(i)%>"></th>
                <th><%=name.get(i)%>
                    <form action="Item" method="POST">
                        <input type="hidden" name="thum" value="<%=thum.get(i)%>">
                        <input type="hidden" name="name" value="<%=name.get(i)%>">
                        <input type="hidden" name="price" value="<%=price.get(i)%>">
                        <input type="hidden" name="headline" value="<%=headline.get(i)%>">
                        <input type="hidden" name="rate" value="<%=rate.get(i)%>">
                        <input type="hidden" name="code" value="<%=code.get(i)%>">
                        <input type="submit" name="btnsubmit" value="詳細へ">
                    </form>
                </th>
                <th><%=price.get(i)%>円</th>
            </tr>
            <%}%>
        </table>
    </body>
    <%if ((String)hs.getAttribute("logPass") == null) {%>
    <%=jh.loginPage("Search")%>
    <%}else{%>
    <%=jh.logoutPage()%>
    <%}%><br>
    <%=jh.home()%>
</html>
