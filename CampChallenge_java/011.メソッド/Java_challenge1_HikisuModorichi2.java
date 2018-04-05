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
public class Java_challenge1_HikisuModorichi2 extends HttpServlet {
    ArrayList<String> prof(String str){
        ArrayList<String> Asan = new ArrayList<String>();
            Asan.add("1111"/*ID*/);
            Asan.add("1111/11/11"/*生年月日*/);
            Asan.add("1丁目"/*住所*/);
        ArrayList<String> Bsan = new ArrayList<String>();
            Bsan.add("2222"/*ID*/);
            Bsan.add("2222/22/22"/*生年月日*/);
            Bsan.add(null/*住所*/);
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Java_challenge1_HikisuModorichi2</title>");            
            out.println("</head>");
            out.println("<body>");

            ArrayList<String> Asan = prof("1111");
            ArrayList<String> Bsan = prof("2222");
            ArrayList<String> Csan = prof("3333");
            
            for (int i=0; i<3; i++){                
            if (Asan.get(i) == null){
                continue;            
        }else{
                out.print(Asan.get(i));
            }
            }
            
            for (int i=0; i<3; i++){
            if (Bsan.get(i) == null){
                continue;
            }else{
                out.print(Bsan.get(i));
            }
            }
            
            for (int i=0; i<3; i++){
            if (Csan.get(i) == null){
                continue;
            }else{
                out.print(Csan.get(i));
            }
        }
            
            out.println("</body>");
            out.println("</html>");
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
