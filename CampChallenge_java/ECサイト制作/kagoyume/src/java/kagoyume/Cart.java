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

import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sanosuke
 */
public class Cart extends HttpServlet {

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
            
            HttpSession hs = request.getSession();
            ArrayList<ArrayList<String>> goods = new ArrayList();
            String userPass = "check";
            boolean flg = false;
            
            //ログインせずにここに来た場合
            if ((String)hs.getAttribute("logPass") == null) {
                hs.setAttribute("urlCart", "Cart");
                response.sendRedirect("Login");
            //ログインしてきた場合
            }else {
                userPass = (String)hs.getAttribute("logPass");
                flg = true;
            }
            //ログイン前にカートに追加していた場合と、ログイン後にカートに追加した場合の処理
            if (flg == true) {
                if (request.getParameter("delete") == null) {
                    if ((ArrayList<ArrayList<String>>)hs.getAttribute("checkgoods") != null) {
                        //ログイン前にカートに追加していて、ログイン後そのアカウントにもカートに商品があった場合
                        if ((ArrayList<ArrayList<String>>)hs.getAttribute(userPass+"goods") != null) {
                            goods = (ArrayList<ArrayList<String>>)hs.getAttribute(userPass+"goods");
                            ArrayList<ArrayList<String>> nGoods = (ArrayList<ArrayList<String>>)hs.getAttribute("checkgoods");
                            for (int i=0; i<nGoods.size(); i++) {
                                goods.add(nGoods.get(i));
                                hs.removeAttribute("checkgoods");
                            }
                        //ログイン前にカートに追加していて、ログイン後にそのアカウントのカートに商品がなかった場合
                        }else {
                            goods = (ArrayList<ArrayList<String>>)hs.getAttribute("checkgoods");
                            hs.removeAttribute("checkgoods");
                        }
                    //ログイン前にカートに追加していない場合
                    }else {
                        goods = (ArrayList<ArrayList<String>>)hs.getAttribute(userPass+"goods");
                    }
                    
                    //上記の処理を終えセッションに格納、jspへ飛ぶ
                    hs.setAttribute(userPass+"goods", goods);
                    request.getRequestDispatcher("/cart.jsp").forward(request, response);
                    
                    
                //削除ボタンを押された場合
                }else {
                    int num = Integer.parseInt(request.getParameter("delete"));
                    int deleteCount = Integer.parseInt(request.getParameter("deleteCount"));
                    goods = (ArrayList<ArrayList<String>>)hs.getAttribute(userPass+"goods");
                    int goodsCount = Integer.parseInt(goods.get(num).get(7));
                    //削除の個数分減らす
                    goodsCount -= deleteCount;
                    //個数が0になったら商品を消去
                    if (goodsCount == 0) {
                    goods.remove(num);
                    //商品が残ってる場合は改めて格納
                    }else {
                        String newGoodsCount = String.valueOf(goodsCount);
                        goods.get(num).set(7, newGoodsCount);
                    }
                    hs.setAttribute(userPass+"goods", goods);

                    request.getRequestDispatcher("/cart.jsp").forward(request, response);
                }
            }
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
