<%-- 
    Document   : NyuuryokuFealdNoSakuseiToJissou
    Created on : 2018/04/20, 16:20:27
    Author     : Sanosuke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
                //cookieを取得               
        Cookie[] cs = request.getCookies();
        String naming = "";
        String typing = "";
        String male = "";
        String female = "";
        String syuming = "";
        //for文で値を取得
        if (cs != null){
            for (int i=0; i<cs.length; i++){
                //名前を取得
                if (cs[i].getName().equals("Name")){
                    naming = URLDecoder.decode(cs[i].getValue(),"UTF-8");                                        
                }
                //性別を取得
                if (cs[i].getName().equals("Type")){                    
                    typing = URLDecoder.decode(cs[i].getValue(),"UTF-8");
                    if (typing.equals("男性")){
                        male = "selected=\"selected\"";
                    }else if (typing.equals("女性")){
                        female = "selected=\"selected\"";
                    }
                }
                //趣味を取得
                if (cs[i].getName().equals("Syumi")){
                    syuming = URLDecoder.decode(cs[i].getValue(),"UTF-8");
                }
            }
        }
        %>
        <form action="./DataSousa.jsp" method="post">
            
            <p>
                名前
                <input type="text" name="name" value="<%=naming%>">
            </p>           
            <p>
                性別
                <select name="type">
                <option value="男性" <%=male%>>男性</option>
                <option value="女性" <%=female%>>女性</option>
                </select>
            </p>            
            <p>
                趣味
                <textarea name="mulText"><%= syuming %></textarea>
            </p>
            <input type="submit" name="btnSubmit">                            
            </form>
    </body>
</html>
