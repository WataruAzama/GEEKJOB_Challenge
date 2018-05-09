<%-- 
    Document   : error_handling_13_2
    Created on : 2018/05/01, 12:07:33
    Author     : Sanosuke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <!-- ログアウトで初期画面に戻る -->
        <script type="text/javascript">
            function goServletA(){location.href = "./error_handling_13_1.html";}
        </script>
        
    </head>
    <body>                        
            
            <%
                
                Connection db_con = null;
                PreparedStatement db_st = null;
                ResultSet db_data = null;
                            
                request.setCharacterEncoding("UTF-8");
                String ID = request.getParameter("ID");
                String pass = request.getParameter("pass");
                
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    db_con = DriverManager.getConnection(                        
                            "jdbc:mysql://localhost:3306/Challenge_db?useUnicode=true&characterEncoding=utf8",
                                     ID,pass);
                    
                    //submitで同じページに戻る
                    out.print("<form action=\"./error_handling_13_2.jsp\" method=\"post\">");
                    
                    //入力フォーム
                    out.print("<input type=\"text\" name=\"productCord\" placeholder=\"商品コード\">");
                    out.print("<input type=\"text\" name=\"type\" placeholder=\"種類\">");
                    out.print("<input type=\"text\" name=\"registantID\" placeholder=\"ユーザー番号\">");
                    //送信フォーム
                    out.print("<input type=\"submit\" name=\"submit\">");
                    out.print("<input type=\"button\" name=\"btnsub\" value=\"ログアウト\" onclick=\"goServletA()\">"+"<br>");
                    //ログインを継続するための処理
                    out.print("<input type=\"hidden\" name=\"ID\" value="+ID+">");
                    out.print("<input type=\"hidden\" name=\"pass\" value="+pass+">");
                    
                    //商品コード入力処理
                    
                    String productCord = request.getParameter("productCord");
                    String type = request.getParameter("type");
                    String registantID = request.getParameter("registantID");
                    
                    if (productCord != null && type != null && registantID != null) {
                        //75行目をチェックして飛ぶ
                        int productCord1 = Integer.parseInt(productCord);
                        int registantID1 = Integer.parseInt(registantID);
                    
                        db_st = db_con.prepareStatement("insert into product values (?,?,?)");
                        db_st.setInt(1,productCord1);
                        db_st.setString(2,type);
                        db_st.setInt(3,registantID1);
                        db_st.executeUpdate();
                    }
                    
                    //現在の商品情報を表示
                    db_st = db_con.prepareStatement("select * from product");
                    db_data = db_st.executeQuery();
                    out.print("<table border=\"1\">");
                    out.print("<tr>");
                    out.print("<th>"+"商品コード"+"</th>");
                    out.print("<th>"+"種類"+"</th>");
                    out.print("<th>"+"登録者番号"+"</th>");
                    out.print("</tr>");                    
                    
                    while (db_data.next()) {
                        out.print("<tr>");
                        out.print("<th>"+db_data.getInt("productCord")+"</th>");
                        out.print("<th>"+db_data.getString("type")+"</th>");
                        out.print("<th>"+db_data.getInt("registantID")+"</th>");
                        out.print("</tr>");
                    }
                    out.print("</table>");
                    
                    db_data.close();
                    db_st.close();
                    db_con.close();

                } catch (SQLException e_sql) {
                    out.print("接続時にエラーが発生しました(SQL)："+e_sql.toString());
                    //現在の商品情報を表示
                    db_st = db_con.prepareStatement("select * from product");
                    db_data = db_st.executeQuery();
                    out.print("<table border=\"1\">");
                    out.print("<tr>");
                    out.print("<th>"+"商品コード"+"</th>");
                    out.print("<th>"+"種類"+"</th>");
                    out.print("<th>"+"登録者番号"+"</th>");
                    out.print("</tr>");                    
                    
                    while (db_data.next()) {
                        out.print("<tr>");
                        out.print("<th>"+db_data.getInt("productCord")+"</th>");
                        out.print("<th>"+db_data.getString("type")+"</th>");
                        out.print("<th>"+db_data.getInt("registantID")+"</th>");
                        out.print("</tr>");
                    }
                    out.print("</table>");
                } catch (Exception e) {
                    out.print("接続時にエラーが発生しました(Java)："+e.toString());
                    //現在の商品情報を表示
                    db_st = db_con.prepareStatement("select * from product");
                    db_data = db_st.executeQuery();
                    out.print("<table border=\"1\">");
                    out.print("<tr>");
                    out.print("<th>"+"商品コード"+"</th>");
                    out.print("<th>"+"種類"+"</th>");
                    out.print("<th>"+"登録者番号"+"</th>");
                    out.print("</tr>");                    
                    
                    while (db_data.next()) {
                        out.print("<tr>");
                        out.print("<th>"+db_data.getInt("productCord")+"</th>");
                        out.print("<th>"+db_data.getString("type")+"</th>");
                        out.print("<th>"+db_data.getInt("registantID")+"</th>");
                        out.print("</tr>");
                    }
                    out.print("</table>");
                } finally {
                    if (db_con != null) {
                        try {
                            db_con.close();
                        } catch (Exception e_con) {
                            out.print(e_con.getMessage());
                        }
                    }
                }
                
                
            %>                
        
    </body>
</html>
