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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Sanosuke
 */
public class Buycomplete extends HttpServlet {

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
            
            //受け取りの文字指定
            request.setCharacterEncoding("UTF-8");
            HttpSession hs = request.getSession();
            String userPass = (String)hs.getAttribute("logPass");
            String userStr = (String)hs.getAttribute("logPass");
            int userNum = (Integer)hs.getAttribute("loginUserID");
            int typeNum = Integer.parseInt(request.getParameter("delivery"));
            int money = Integer.parseInt(request.getParameter("money"));
            ArrayList<ArrayList<String>> goods = (ArrayList<ArrayList<String>>)hs.getAttribute(userStr+"goods");
            
            //複数購入の商品を単品ずつに分ける処理
            ArrayList<ArrayList<String>> buyGoods = new ArrayList();
            for (int i=0; i<goods.size(); i++) {
                int count = Integer.parseInt(goods.get(i).get(7));
                for (int k=0; k<count; k++) {
                    buyGoods.add(goods.get(i));
                }
            }
            
            //セッションIDのチェック
            String check = hs.getId();
            String sessionID = "";
            Cookie[] cs = request.getCookies();
            if (cs != null) {
                for (int i=0; i<cs.length; i++) {
                    if (cs[i].getName().equals("kagoyumeSessionID")) {
                        sessionID = cs[i].getValue();
                        break;
                    }
                }
            }
            if (check.equals(sessionID)) {
                //DBに格納
                UserDataDAO.getInstance().buyInsert(userNum, typeNum, buyGoods);
                //購入ユーザーの総購入金額の更新
                UserDataDAO.getInstance().buyUpdate(userNum, money);
                //カートの中身を削除
                hs.removeAttribute(userPass+"goods");
            }else {
                throw new Exception("セッションの有効期限が切れました。<br>"
                        + "ログイン状態が続いている場合は一度ログアウトしてください。");
            }
            
            request.getRequestDispatcher("/buycomplete.jsp").forward(request, response);
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
