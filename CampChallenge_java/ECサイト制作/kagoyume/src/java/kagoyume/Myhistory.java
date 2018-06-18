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

import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.InputStream;
import java.net.URLEncoder;
import org.w3c.dom.Node;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sanosuke
 */
public class Myhistory extends HttpServlet {

    String searchXmlValue(String[] targetNodeName, int nodeNo, Node node) {
        try {
            String s = null;
            //ノードか探索範囲がnullなら再帰から抜ける。
            if(node.getNodeName() == null)return s;
            if(targetNodeName[nodeNo] == null)return s;
            //兄弟ノードを走査する。
            for (Node ch=node.getFirstChild(); ch!=null; ch=ch.getNextSibling()) {
                //ターゲットと一致したら値を抜いてみる
                if (targetNodeName[nodeNo].equals(ch.getNodeName())) {
                    s = ch.getFirstChild().getNodeValue();
                    //もし値がnullで帰ってきたら子ノードありという事で再帰
                    if (s == null) {
                        s = searchXmlValue(targetNodeName, nodeNo+1, ch);
                    }
                    break;
                }
            }
            return s;
        }catch (Exception e) {
            return "情報なし";
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
        try {
            
            //リクエストパラメータの文字コードをUTF-8に変更
            request.setCharacterEncoding("UTF-8");
            HttpSession hs = request.getSession();
            int userID = 0;
            if (request.getParameter("userID") != null) {
                userID = Integer.parseInt(request.getParameter("userID"));
                hs.setAttribute("historyUserID", userID);
            }else {
                userID = (Integer)hs.getAttribute("historyUserID");
            }
            
            ArrayList<String> productCode = UserDataDAO.getInstance().historySearch(userID);
            
            //URL接続
            String str = "https://shopping.yahooapis.jp/ShoppingWebService/V1/itemLookup?appid=dj00aiZpPXRvUk1TSW9IREs0NyZzPWNvbnN1bWVyc2VjcmV0Jng9MTI-&itemcode=";
            
            String str2 = "";
            ArrayList<ArrayList<String>> goodsReview = new ArrayList();
            //このfor文で一つの商品情報を取り出せる。
            for (int i=0; i<productCode.size(); i++) {
                str2 = productCode.get(i);
                
                URL url = new URL(str+str2);
                URLConnection con = url.openConnection();
                InputStream is = con.getInputStream();
                
                Document document = null;
                
                //DOMを使うためのインスタンス生成
                try {
                    document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
                }catch(SAXException e) {
                    request.setAttribute("error", e.getMessage());
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }catch (ParserConfigurationException e) {
                    request.setAttribute("error", e.getMessage());
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
                //root要素を取得
                Element elementRoot = document.getDocumentElement();
                //Hit以下を探すようにする処理
                NodeList localNodeList = elementRoot.getElementsByTagName("Hit");
                
                ArrayList<String> productReview = new ArrayList();
                //サムネイルを取得
                String thum = "";
                for (int k=0; k<localNodeList.getLength(); k++) {
                    Element elementItem = (Element)localNodeList.item(k);
                    String elementName[] = {"Image", "Small"};
                    String s = searchXmlValue(elementName, 0, elementItem);
                    thum = s;
                }
                //商品名を取得
                String name = "";
                for (int k=0; k<localNodeList.getLength(); k++) {
                    Element elementItem = (Element)localNodeList.item(k);
                    String elementName[] = {"Name"};
                    String s = searchXmlValue(elementName, 0, elementItem);
                    name = s;
                }
                //金額を取得
                String price = "";
                for (int k=0; k<localNodeList.getLength(); k++) {
                    Element elementItem = (Element)localNodeList.item(k);
                    String elementName[] = {"Price"};
                    String s = searchXmlValue(elementName, 0, elementItem);
                    price = s;
                }
                productReview.add(thum);
                productReview.add(name);
                productReview.add(price);
                goodsReview.add(productReview);
            }
            
            request.setAttribute("goodsReview", goodsReview);
            request.getRequestDispatcher("/myhistory.jsp").forward(request, response);
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
