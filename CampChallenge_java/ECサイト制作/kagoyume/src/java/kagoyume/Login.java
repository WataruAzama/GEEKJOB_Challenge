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
import javax.servlet.http.Cookie;

/**
 *
 * @author Sanosuke
 */
public class Login extends HttpServlet {

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
            
            //ログイン入力フォームから来た場合
            if (request.getParameter("loginName") != null) {
                String name = request.getParameter("loginName");
                String password = request.getParameter("loginPassword");
                boolean bl = false;
                
                //空文字をチェック
                if (name.trim().equals("") || password.trim().equals("")) {
                    String erorrStr = "";
                    if (name.trim().equals("")) {
                        erorrStr += "名前が未入力です<br>";
                    }
                    if (password.trim().equals("")) {
                        erorrStr += "パスワードが未入力です<br>";
                    }
                    bl = true;
                    request.setAttribute("erorrStr", erorrStr);
                    request.setAttribute("loginName", name);
                    request.setAttribute("loginPassword", password);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }

                //ログインできるかチェック
                int loginUserID = 0;
                if (bl == false) {
                    ArrayList<UserDataDTO> checkLogin = UserDataDAO.getInstance().login();
                    boolean flg = false;
                    for (int i=0; i<checkLogin.size(); i++) {
                        if (name.equals(checkLogin.get(i).getName()) && password.equals(checkLogin.get(i).getPassword())) {
                            loginUserID = checkLogin.get(i).getUserID();
                            flg = true;
                            break;
                        }
                    }
                    //ログインに成功した時の処理
                    if (flg == true) {
                        hs.setAttribute("logPass", password);
                        hs.setAttribute("loginUserID", loginUserID);
                        hs.setAttribute("historyUserID" ,UserDataDAO.getInstance().historyUserID(password));
                        Cookie c = new Cookie("kagoyumeSessionID", hs.getId());
                        response.addCookie(c);
                        //ログイン前のカート情報を移動
                        if ((ArrayList<ArrayList<String>>)hs.getAttribute("goods") != null) {
                            ArrayList<ArrayList<String>> goods = (ArrayList<ArrayList<String>>)hs.getAttribute("goods");
                            String userPass = password;
                            hs.setAttribute(userPass+"goods", goods);
                        }
                        response.sendRedirect((String)hs.getAttribute("urlData"));
                    //ログインに失敗したためエラー文
                    }else {
                        request.setAttribute("noData", "noData");
                        request.setAttribute("loginName", name);
                        request.setAttribute("loginPassword", password);
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                }
                
            //既にログインしている場合
            }else if ((String)hs.getAttribute("logPass") != null) {
                //セッションの削除
                hs.removeAttribute("goods");
                hs.removeAttribute("logPass");
                //クッキーの削除
                Cookie cookies[] = request.getCookies();
                for (Cookie c : cookies) {
                    if ("kagoyumeSession".equals(c.getName())) {
                        c.setMaxAge(0);
                        response.addCookie(c);
                    }
                }
                request.getRequestDispatcher("/top.jsp").forward(request, response);
                
            //初めてきた場合の処理
            }else {
                String urlData = "";
                if (request.getParameter("utlData") != null) {
                    urlData = request.getParameter("utlData");
                }else {
                    urlData = (String)hs.getAttribute("urlCart");
                }
                hs.setAttribute("urlData", urlData);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
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
