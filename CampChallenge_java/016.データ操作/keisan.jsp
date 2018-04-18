<%-- 
    Document   : keisan
    Created on : 2018/04/18, 12:58:48
    Author     : Sanosuke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        //文字を受け取る
        request.setCharacterEncoding("UTF-8");
        //文字を数値に変換
        int num = Integer.parseInt(request.getParameter("num"));

        out.print(num+" = ");
        
        int[] sosuu = {2,3,5,7};
        
        while (num % 2 == 0){
            num = num / 2;
            out.print("2");
            for (int i=0; i<sosuu.length; i++){
                if(num % sosuu[i] == 0){
                    out.print(" * ");
                    break;
                }
            }
            }
        while (num % 3 == 0){
            num = num / 3;
            out.print("3");
            for(int i=1; i<sosuu.length; i++){
                if (num % sosuu[i] == 0){
                    out.print(" * ");
                    break;
                }
            }
        }
        while (num % 5 == 0){
            num = num / 5;        
            out.print("5");
            for (int i=2; i<sosuu.length; i++){
                if (num % sosuu[i] == 0){
                    out.print(" * ");
                    break;
                }
            }
        }
        while (num % 7 == 0){
            num = num / 7;
            out.print("7");
            for (int i=3; i<sosuu.length; i++){
                if (num % sosuu[i] == 0){
                    out.print(" * ");
                    break;
                }
            }
        }
        
        if (num != 1){
            out.print(" あまり "+num);
        }


        %>
    </body>
</html>
