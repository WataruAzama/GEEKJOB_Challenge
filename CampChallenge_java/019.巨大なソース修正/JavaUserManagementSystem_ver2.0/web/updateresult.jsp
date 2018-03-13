<%@page import="jums.JumsHelper" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS更新結果画面</title>
    </head>
    <body>
        <h1>変更結果</h1><br>
        名前:<br>
        生年月日:<br>
        種別:<br>
        電話番号:<br>
        自己紹介:<br>
        以上の内容で登録しました。<br>
    </body>
    <%=jh.home()%>
    </body>
</html>
