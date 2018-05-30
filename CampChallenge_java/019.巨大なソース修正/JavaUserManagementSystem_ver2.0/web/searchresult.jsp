<%@page import="jums.JumsHelper" %>
<%@page import="jums.UserDataDTO"%>
<%@page import="java.util.ArrayList" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    ArrayList<UserDataDTO> udd = (ArrayList<UserDataDTO>)request.getAttribute("resultData");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS検索結果画面</title>
    </head>
    <body>
        <h1>検索結果</h1>
        <% if (udd.size() == 0) {out.print("該当するデータはありません");}else{%>
        <table border=1>
            <tr>
                <th>名前</th>
                <th>生年</th>
                <th>種別</th>
                <th>登録日時</th>
            </tr>
            <% for (int i=0; i<udd.size(); i++) { ;%>
            <tr>
            <form action="ResultDetail?id=<%= udd.get(i).getUserID()%>">
                <td><button type="hidden" name="ID" value="<%=udd.get(i).getUserID()%>"><%=udd.get(i).getName()%></button></td>
            </form>
                <td><%= udd.get(i).getBirthday()%></td>
                <td><%= udd.get(i).getType()%></td>
                <td><%= udd.get(i).getNewDate()%></td>
            </tr>
            <% }} %>
        </table>
        <form action="Search" method="POST">
            <input type="submit" name="no" value="検索画面に戻る">
            <input type="hidden" name="modo" value="REINPUT">
        </form>
    </body>
    <%=jh.home()%>
</html>
