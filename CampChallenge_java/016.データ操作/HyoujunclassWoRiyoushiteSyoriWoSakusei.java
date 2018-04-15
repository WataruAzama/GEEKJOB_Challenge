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

import java.util.TreeSet;
import java.util.Random;
import java.util.ArrayList;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Sanosuke
 */
public class HyoujunclassWoRiyoushiteSyoriWoSakusei extends HttpServlet {

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

            out.print("1.TreeSetに1～100までの数字をランダムで10個入れ、50以上で最も小さい値を表示させる<br>");
            
            Date start = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String dateString = sdf.format(start);
            
            out.print("2.処理開始"+dateString+"<br>");            
            
            TreeSet<Integer> ts = new TreeSet();
            Random rand = new Random();
            ArrayList<Integer> hako = new ArrayList();            
            //1~100を箱に入れる
            for (int i=1; i<=100; i++){
                hako.add(i);
            }                            
            //箱の中から10個ランダムでtsに入れる
            for (int i=0; i<10; i++){                
                ts.add(rand.nextInt(hako.size()));
            }
            
            out.print(ts+"<br>");
            
            out.print(ts.ceiling(50)+"<br>");
            
            Calendar c = Calendar.getInstance();
            c.set(2018,3,15,23,59,59);
            Date d = c.getTime();
            String end = sdf.format(d);
            
            out.print("3.処理終了"+end+"<br>");
            
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
