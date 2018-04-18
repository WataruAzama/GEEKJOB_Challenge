<%-- 
    Document   : shopping
    Created on : 2018/04/17, 17:12:13
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
            //情報を受け取った時の文字を指定
        request.setCharacterEncoding("UTF-8");
        //typeの答えを表示
        int syurui = Integer.parseInt(request.getParameter("type"));
        switch (syurui){
            
            case 1:
            out.print("雑貨<br>");
            break;
            
            case 2:
                out.print("生鮮食品<br>");
                break;
                
            case 3:
                out.print("その他<br>");
                break;
                
            default:
                out.print("Error<br>");
                break;
                
        }
        
        //商品の単価を求め，画面に表示
        //文字を数値に変換
        int goukei = Integer.parseInt(request.getParameter("total"));
        int kosuu = Integer.parseInt(request.getParameter("count"));
        //単価を計算し画面に表示
        out.print("1つ"+goukei/kosuu+"円<br>");
        
        //商品購入総額に応じてポイントを計算し，その値を画面に表示
        if(goukei < 3000){
                out.print("ポイントなし");
        }else if(goukei >= 3000 && goukei <5000){
            out.print(goukei/25+"ポイント");
        }else{
            out.print(goukei/20+"ポイント");
        }        

        %>
        
    </body>
</html>
