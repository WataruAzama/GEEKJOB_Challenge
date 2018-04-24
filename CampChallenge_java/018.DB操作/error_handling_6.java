/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package error;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 *
 * @author Sanosuke
 */
public class error_handling_6 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            Connection db_con = null;
            PreparedStatement db_st = null;
            ResultSet db_data = null;
            
            try{
                //mySQLへ接続
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                db_con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Challenge_db?useUnicode=true&characterEncoding=utf8",
                        "wataru","magotyan");
                //3番をデリート
                db_st = db_con.prepareStatement("delete from profiles where profilesID = ?");
                db_st.setInt(1,3);
                db_st.executeUpdate();
                //全て表示
                db_st = db_con.prepareStatement("select * from profiles");
                db_data = db_st.executeQuery();
                while (db_data.next()) {
                    out.print(db_data.getInt("profilesID")+"<br>");
                    out.print(db_data.getString("name")+"<br>");
                    out.print(db_data.getString("tel")+"<br>");
                    out.print(db_data.getInt("age")+"<br>");
                    out.print(db_data.getDate("birthday")+"<br>");                    
                }
                //閉じる
                db_data.close();
                db_st.close();
                db_con.close();
            } catch (SQLException e_sql) {
                out.println("接続時にエラーが発生しました(sql):"+e_sql.toString());
            } catch (Exception e) {
                out.println("接続時にエラーが発生しました(java):"+e.toString());
            } finally {
                if (db_con != null) {
                    try {
                        db_con.close();                        
                    } catch (Exception e_con) {
                        System.out.println(e_con.getMessage());
                    }
                }
            }   
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
