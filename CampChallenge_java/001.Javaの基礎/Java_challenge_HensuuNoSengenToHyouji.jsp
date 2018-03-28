<%-- 
    Document   : Java_challenge_HensuuNoSengenToHyouji
    Created on : 2018/03/28, 11:49:36
    Author     : Sanosuke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        int num = 3;
        if (num == 1){
            out.print("1です！");
        }else if (num == 2){
                    out.print("プログラミングキャンプ！");
                    }else{
            out.print("その他です！");
        }
        %>
        
</html>
