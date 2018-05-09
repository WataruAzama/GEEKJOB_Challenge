package jums;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * insertconfirm.jspと対応するサーブレット
 * フォーム入力された情報はここでセッションに格納し、以降持ちまわることになる
 * 直接アクセスした場合はerror.jspに振り分け
 * @author hayashi-s
 */
public class InsertConfirm extends HttpServlet {

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
        try{
            HttpSession session = request.getSession();
            request.setCharacterEncoding("UTF-8");//セッションに格納する文字コードをUTF-8に変更
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }                        
            
            //値が入力されていない時にエラーにする
            String year = request.getParameter("year");
            String month = request.getParameter("month");
            String day = request.getParameter("day");
            String tell = request.getParameter("tell");
            String comment = request.getParameter("comment");
            //生年月日のエラー
            if (year.length() == 0 || month.length() == 0 || day.length() == 0) {
                request.setAttribute("error","生年月日の入力が不完全です。");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            //電話番号で未入力、英字、全角をはじく処理(特殊文字はスルー)
            } else if (tell.length() == 0 || tell.matches(".*[a-zA-Z].*") || tell.getBytes().length != tell.length()){
                request.setAttribute("error","電話番号を半角で入力してください。");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            //自己紹介文未入力もエラー
            } else if (comment.length() == 0){
                request.setAttribute("error","自己紹介文を入力してください。");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } else{
                
                //正確な値を入力された場合、新規作成JavaBeansに格納
                UserDataBeans userdata = new UserDataBeans();
                userdata.setName(request.getParameter("name"));
                userdata.setYear(year);
                userdata.setMonth(month);
                userdata.setDay(day);
                userdata.setType(request.getParameter("type"));
                userdata.setTell(request.getParameter("tell"));
                userdata.setComment(request.getParameter("comment"));

                //セッションに格納
                UserDataBeans.getInstance().insert(userdata,request);

                request.getRequestDispatcher("/insertconfirm.jsp").forward(request, response);
            }
        }catch(Exception e){
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
