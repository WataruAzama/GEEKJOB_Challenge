<%-- 
    Document   : RensouHairetsu
    Created on : 2018/03/28, 16:24:31
    Author     : Sanosuke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%@page import="java.util.HashMap"%>
    
<%

HashMap<String,String>nazo = new HashMap<String,String>();

nazo.put("1", "AAA");
nazo.put("hello","world");
nazo.put("soeda","33");
nazo.put("20","20");

out.print(nazo.get("1"));
out.print(nazo.get("hello"));
out.print(nazo.get("soeda"));
out.print(nazo.get("20"));

%>

</html>
