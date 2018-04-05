/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

/**
 *
 * @author Sanosuke
 */
public class Java_challenge_HikisuuModorichi1 extends HttpServlet {
    ArrayList<String> prof(String str){
        ArrayList<String> Asan = new ArrayList<String>();
            Asan.add("1111"/*ID*/);
            Asan.add("1111/11/11"/*生年月日*/);
            Asan.add("1丁目"/*住所*/);
        ArrayList<String> Bsan = new ArrayList<String>();
            Bsan.add("2222"/*ID*/);
            Bsan.add("2222/22/22"/*生年月日*/);
            Bsan.add("2丁目"/*住所*/);
        ArrayList<String> Csan = new ArrayList<String>();
            Csan.add("3333"/*ID*/);
            Csan.add("3333/33/33"/*生年月日*/);
            Csan.add("3丁目"/*住所*/);
            
        if (str.equals(Asan.get(0))){
            return Asan;
        }else if(str.equals(Bsan.get(0))){
            return Bsan;
        }else{
        return Csan;
    }
        
    }
    
    


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
            
            ArrayList<String> kojin = prof("2222");
            for(int i=0; i<3; i++){
                if (i ==0){
                    continue;
                }
                out.print(kojin.get(i));
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
