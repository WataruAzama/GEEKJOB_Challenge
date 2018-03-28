<%-- 
    Document   : Java_challenge_switchBun-1
    Created on : 2018/03/28, 13:12:40
    Author     : Sanosuke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        int num = 3;
                switch(num){
        case 1:
        out.print("one");
        break;
        
        case 2:
            out.print("twe");
            break;
            
        default:
            out.print("想定外");
            break;
    }
                %>
</html>
