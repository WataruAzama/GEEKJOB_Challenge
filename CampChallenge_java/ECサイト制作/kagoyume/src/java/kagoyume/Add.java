/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import java.util.ArrayList;

/**
 *
 * @author Sanosuke
 */
public class Add extends HttpServlet {

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
        try {
            
            //リクエストパラメータの文字コードをUTF-8に変更
            request.setCharacterEncoding("UTF-8");
            
            HttpSession hs = request.getSession();
            //購入予定商品をまとめるアレイリスト
            ArrayList<ArrayList<String>> goods = new ArrayList();
            //セッション名はpassも追加
            String userPass = "check";
            if ((String)hs.getAttribute("logPass") != null) {
                userPass = (String)hs.getAttribute("logPass");
            }
            if ((ArrayList<ArrayList<String>>)hs.getAttribute(userPass+"goods") !=null) {
                goods = (ArrayList<ArrayList<String>>)hs.getAttribute(userPass+"goods");
            }
            if (request.getParameter("thum") != null) {
                ArrayList<String> al = new ArrayList();
                al.add(request.getParameter("thum"));
                al.add(request.getParameter("name"));
                al.add(request.getParameter("price"));
                al.add(request.getParameter("headline"));
                al.add(request.getParameter("rate"));
                al.add(request.getParameter("code"));
                al.add((String)hs.getAttribute("reItem"));
                al.add(request.getParameter("count"));
                goods.add(al);
            }
            
            hs.setAttribute(userPass+"goods", goods);
            
            request.getRequestDispatcher("/add.jsp").forward(request, response);
        }catch(Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
