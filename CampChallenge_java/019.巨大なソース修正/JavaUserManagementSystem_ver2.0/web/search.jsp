<%@page 
        import="jums.JumsHelper"
        import="java.util.ArrayList"
        import="jums.UserDataBeans" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserDataBeans udb = null;
    boolean reinput = false;
    if (request.getParameter("modo") != null && request.getParameter("modo").equals("REINPUT")) {
        reinput = true;
        udb = (UserDataBeans)hs.getAttribute("searchUDB");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMSユーザー情報検索画面</title>
    </head>
    <body>
         <form action="SearchResult" method="POST">
        名前:
        <input type="text" name="name" value="<% if(reinput){out.print(udb.getName());} %>">
        <br><br>

        生年:　
        <select name="year">
            <option value="">----</option>
            <% for(int i=1950; i<=2010; i++){ %>
            <option value="<%=i%>" <% if(reinput && udb.getYear() == i){out.print("selected = \"selected\"");}%>><%=i%></option>
            <% } %>
        </select>年生まれ
        <br><br>

        種別:
        <br>
            <% for(int i = 1; i<=3; i++){ %>
            <input type="radio" name="type" value="<%=i%>"<%if(reinput && udb.getType() == i){out.print("checked = \"checked\"");}%>><%=jh.exTypenum(i)%><br>
            <% } %>
        <br>

        <input type="submit" name="btnSubmit" value="検索">
    </form>
        <br>
        <%=jh.home()%>
    </body>
</html>
