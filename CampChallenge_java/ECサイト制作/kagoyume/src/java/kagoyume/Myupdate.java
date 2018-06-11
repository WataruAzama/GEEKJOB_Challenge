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
public class Myupdate extends HttpServlet {

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
            ArrayList<UserDataDTO> userAl = (ArrayList<UserDataDTO>)hs.getAttribute("userAl");
            
            int alNum = 0;
            if (request.getParameter("alNum") != null) {
                alNum = Integer.parseInt(request.getParameter("alNum"));
                hs.setAttribute("alNum", alNum);
            }else {
                alNum = (Integer)hs.getAttribute("alNum");
            }
            
            if ((String)request.getAttribute("str") != null) {
                String str = (String)request.getAttribute("str") ;
                request.setAttribute("str", str);
            }
            
            UserDataDTO user = userAl.get(alNum);
            UserDataBeans userUDB = UserDataBeans.getInstance().reUDB(user);
            
            hs.setAttribute("userUDB", userUDB);
            request.setAttribute("userID", user.getUserID());
            request.setAttribute("userUDB", userUDB);
            request.getRequestDispatcher("/myupdate.jsp").forward(request, response);
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
