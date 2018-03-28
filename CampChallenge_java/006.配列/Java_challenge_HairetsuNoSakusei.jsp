<%-- 
    Document   : Java_challenge_HairetsuNoSakusei
    Created on : 2018/03/28, 15:17:23
    Author     : Sanosuke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@page import="java.util.ArrayList" %>
    <%

ArrayList<String>datas = new ArrayList<String>();

datas.add("10");
datas.add("100");
datas.add("soeda");
datas.add("hayashi");
datas.add("-20");
datas.add("118");
datas.add("END");

out.print(datas.get(0));
out.print(datas.get(1));
out.print(datas.get(2));
out.print(datas.get(3));
out.print(datas.get(4));
out.print(datas.get(5));
out.print(datas.get(6));

%>
</html>
