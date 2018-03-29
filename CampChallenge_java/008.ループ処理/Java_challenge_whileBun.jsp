<%-- 
    Document   : Java_challenge_whileBun
    Created on : 2018/03/29, 13:26:09
    Author     : Sanosuke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%

int num = 1000;

while((num < 100 ) == false){
    num =num / 2;
}

out.print(num);

%>
</html>
