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
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Sanosuke
 */
public class Myupdateresult extends HttpServlet {

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
            
            //文字を適切に受け取り
            request.setCharacterEncoding("UTF-8");
            HttpSession hs = request.getSession();
            
            //変更前の情報を取得
            UserDataBeans checkUDB = (UserDataBeans)hs.getAttribute("userUDB");
            
            //更新予定の値を取得
            UserDataBeans user = new UserDataBeans();
            user.setName(request.getParameter("upName"));
            user.setPassword(request.getParameter("upPass"));
            user.setMail(request.getParameter("upMail"));
            user.setAddress(request.getParameter("upAddress"));
            
            //空文字があったらエラー
            ArrayList<String> checkList = user.checkList();
            if (checkList.size() != 0) {
                String str = JumsHelper.getInstance().checkInput(checkList);
                request.setAttribute("str", str);
                
                request.getRequestDispatcher("/Myupdate").forward(request, response);
            }else if(checkUDB.checkUpdate(user) == true) {
                String str = "情報が変更されていません。";
                request.setAttribute("str", str);
                
                RequestDispatcher rd = request.getRequestDispatcher("/Myupdate");
                rd.forward(request, response);
            }else {
                UserDataDTO dto = new UserDataDTO();
                user.udbDTOMapping(dto);
                dto.setUserID(Integer.parseInt(request.getParameter("userID")));

                UserDataDAO.getInstance().update(dto);
                
                request.setAttribute("user", user);
                request.getRequestDispatcher("/myupdateresult.jsp").forward(request, response);
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
