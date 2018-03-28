<%-- 
    Document   : Java_challenge_forBun3
    Created on : 2018/03/28, 17:26:54
    Author     : Sanosuke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%

int total = 0;
for (int i=0;i<=100;i++){
    total = total + i;
}

out.print(total);

%>
</html>
