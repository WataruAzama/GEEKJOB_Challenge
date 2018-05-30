package jums;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hayashi-s
 */
public class SearchResult extends HttpServlet {

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
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            
            HttpSession hs = request.getSession();
            UserDataDTO searchData = new UserDataDTO();
            UserDataBeans searchUDB = (UserDataBeans)hs.getAttribute("searchUDB");
            
            //セッション情報の有無、入力フォームからの情報の有無、それらを使用する準備。
            String udbname = "";
            String udbyear = "";
            String udbtype = "";
            if (searchUDB != null) {
                udbname = searchUDB.getName();
                udbyear = String.valueOf(searchUDB.getYear());
                udbtype = String.valueOf(searchUDB.getType());
            }
            String strname = request.getParameter("name");
            String stryear = request.getParameter("year");
            String strtype = request.getParameter("type");            
            
            //検索情報が残ってる場合
            if (searchUDB != null && udbname.equals(strname) && udbyear.equals(stryear) && udbtype.equals(strtype)) {
                UserDataBeans seUDB = searchUDB;
                seUDB.UD2DTOMapping(searchData);
                ArrayList<UserDataDTO> resultData = UserDataDAO .getInstance().search(searchData);
                
                //forwardのrequestにセット
                request.setAttribute("resultData", resultData);           
                
                request.getRequestDispatcher("/searchresult.jsp").forward(request, response);
                
            //search.jsp意外のページから飛んできた場合
            }else if(searchUDB != null && strname==null && stryear==null && strtype==null) {
                UserDataBeans seUDB = searchUDB;
                seUDB.UD2DTOMapping(searchData);
                ArrayList<UserDataDTO> resultData = UserDataDAO .getInstance().search(searchData);
                
                //forwardのrequestにセット
                request.setAttribute("resultData", resultData);           
                
                request.getRequestDispatcher("/searchresult.jsp").forward(request, response);
                
            //始めて検索した場合
            //フォームからの入力を取得して、JavaBeansに格納
            }else{
                UserDataBeans udb = new UserDataBeans();
                udb.setName(request.getParameter("name"));
                udb.setYear(request.getParameter("year"));
                udb.setType(request.getParameter("type"));
                //セッションに格納し、次にこの画面に来た時の処理に使用
                hs.setAttribute("searchUDB",udb);
                
                //UDBを使用してDTOオブジェクトにマッピング。DB専用のパラメータに変換。上の3つ以外も格納
                udb.UD2DTOMapping(searchData);

                //DAOを使用してDBから該当データを検索(ArrayListにDTOをセット)
                ArrayList<UserDataDTO> resultData = UserDataDAO .getInstance().search(searchData);
                //セッションにudbをせっと
                //forwardのrequestにセット
                request.setAttribute("resultData", resultData);

                request.getRequestDispatcher("/searchresult.jsp").forward(request, response);  
            }
            
            
        }catch(Exception e){
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
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
