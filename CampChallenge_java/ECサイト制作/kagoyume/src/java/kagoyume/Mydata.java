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
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

/**
 *
 * @author Sanosuke
 */
public class Mydata extends HttpServlet {

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
            
            ArrayList<UserDataDTO> userAl = UserDataDAO.getInstance().search();
            //登録日時の0.x秒の表記を無くすための処理
            ArrayList<String> dateFormat = new ArrayList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            for (int i=0; i<userAl.size(); i++) {
                Timestamp ts = userAl.get(i).getNewDate();
                dateFormat.add(sdf.format(ts));
            }
            
            HttpSession hs = request.getSession();
            hs.setAttribute("userAl", userAl);
            
            //情報の更新が不適格だった場合の処理
            if ((String)request.getAttribute("str") == null) {
                String str = (String)request.getAttribute("str");
                request.setAttribute("str", str);
            }
            request.setAttribute("dateFormat", dateFormat);
            request.getRequestDispatcher("/mydata.jsp").forward(request, response);
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
