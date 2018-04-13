/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackJack;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import java.util.ArrayList;
//import java.util.Random;

/**
 *
 * @author Sanosuke
 */

//クラスを継承
class Osugi extends Dealer{}
class Piko extends User{}


public class BJ_BlackJack extends HttpServlet {

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
            out.println("<title>Servlet BJ_BlackJack</title>");            
            out.println("</head>");
            out.println("<body>");
            
            //実行開始
            
                //インスタンス生成
                Osugi osugi = new Osugi();
                Piko piko = new Piko();           
                
                //カードを配る
                osugi.setCards(osugi.deal());
                piko.setCards(osugi.deal());
                out.print("おすぎがカードを配る<br><br>");
                
                //手札の合計
                out.print("おすぎの合計値"+osugi.open()+"<br>");
                out.print("ピーコの合計値"+piko.open()+"<br><br>");
                
                //ナチュラル21が出た場合終了
                if (osugi.open() == 21 && piko.open() == 21){
                    out.print("引き分け");
                }else if(osugi.open() == 21 && piko.open() < 21){
                    out.print("おすぎの勝ち");                                                                    
                }else if(osugi.open() < 21 && piko.open() ==21){
                    out.print("ピーコの勝ち");
                    
                //ナチュラル21が出なかった場合
                }else{
                    
                    //おすぎがもう一枚引くか確認
                while (osugi.open() < 17){
                    out.print("おすぎはもう一枚引く<br>");
                    osugi.setCards(osugi.hit());
                    out.print("おすぎの合計値"+osugi.open()+"<br><br>");
                    }

                    //おすぎが21を超えたら負け
                    if(osugi.open() > 21){
                        out.print("おすぎが21を超えたためピーコの勝ち");
                    }else{
                
                //ピーコがもう一枚引くか確認
                while (piko.open() < 17){
                    if (piko.checkSum() == true){
                    out.print("ピーコはもう一枚引く<br>");
                    piko.setCards(osugi.hit());
                    out.print("ピーコの合計値"+piko.open()+"<br><br>");
                    }
                }
                    
                    //ピーコが21を超えたら負け
                    if(piko.open() > 21){
                        out.print("ピーコが21を超えたためおすぎの勝ち");
                    }else{
                
                //勝敗
                if (osugi.open() > piko.open()){
                    out.print(osugi.open()+"対"+piko.open()+"により"+"おすぎの勝ち");
                }else if(osugi.open() < piko.open()){
                    out.print(osugi.open()+"対"+piko.open()+"により"+"ピーコの勝ち");
                }else{
                    out.print(osugi.open()+"対"+piko.open()+"により"+"引き分け");
                }
                    }
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
