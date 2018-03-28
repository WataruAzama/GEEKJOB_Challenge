<%-- 
    Document   : Java_challenge_forBun1
    Created on : 2018/03/28, 16:59:27
    Author     : Sanosuke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%

long total = 8;
for (int i=1;i<20;i++){
    total = total * 8;
}

out.print(total);

%>
</html>
