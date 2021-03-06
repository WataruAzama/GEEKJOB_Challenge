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
public class Item extends HttpServlet {

    public String searchXmlValue(String[] targetNodeName, int nodeNo, Node node) {
        try {
            String s = "取得失敗";
            //ノードか探索範囲がnullなら再帰から抜ける。
            if(node.getNodeName() == null)return s;
            if(targetNodeName[nodeNo] == null)return s;
            //兄弟ノードを走査する。
            for (Node ch = node.getFirstChild(); ch != null; ch = ch.getNextSibling()) {
                //ターゲットと一致したら値を抜いてみる
                if (targetNodeName[nodeNo].equals(ch.getNodeName())) {
                    s = ch.getFirstChild().getNodeValue();
                    //もし値がnullで帰ってきたら子ノードありという事で再帰
                    if(s == null){
                        s = searchXmlValue(targetNodeName, nodeNo + 1, ch);
                    }
                    break;
                }
            }
            return s;
        }catch (Exception e){
            return "取得失敗";
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
            
            
            String code = request.getParameter("code");
            ArrayList<ArrayList<String>> resultGoods = (ArrayList<ArrayList<String>>)hs.getAttribute("resultGoods");
            
            ArrayList<String> resultSource = new ArrayList();
            for (int i=0; i<resultGoods.get(5).size(); i++) {
                if (code.equals(resultGoods.get(5).get(i))) {
                    resultSource.add(resultGoods.get(0).get(i));
                    resultSource.add(resultGoods.get(1).get(i));
                    resultSource.add(resultGoods.get(2).get(i));
                    resultSource.add(resultGoods.get(3).get(i));
                    resultSource.add(resultGoods.get(4).get(i));
                    resultSource.add(resultGoods.get(5).get(i));
                    break;
                }
            }
            
            if (resultSource.size() != 0) {
                String strURL = request.getRequestURL()+"?code="+code+"&btnsubmit=%E8%A9%B3%E7%B4%B0%E3%81%B8";
                hs.setAttribute("reItem", strURL);

                request.setAttribute("resultSource", resultSource);
                request.setAttribute("code", code);

                request.getRequestDispatcher("/item.jsp").forward(request, response);
            //上記で上手取得できなかった場合
            }else {
            
            //URL接続
            String str = "https://shopping.yahooapis.jp/ShoppingWebService/V1/itemLookup?appid=dj00aiZpPXRvUk1TSW9IREs0NyZzPWNvbnN1bWVyc2VjcmV0Jng9MTI-&itemcode=";
            
            String str2 = code;
            
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
                //キャッチコピーを取得
                String headline = "";
                for (int i=0; i<localNodeList.getLength(); i++) {
                    Element elementItem = (Element)localNodeList.item(i);
                    String elementName[] = {"Headline"};
                    String s = searchXmlValue(elementName, 0, elementItem);
                    headline = s;
                }
                //評価値を取得
                String rate = "";
                for (int i=0; i<localNodeList.getLength(); i++) {
                    Element elementItem = (Element)localNodeList.item(i);
                    String elementName[] = {"Review","Rate"};
                    String s = searchXmlValue(elementName, 0, elementItem);
                    rate = s;
                }
                resultSource.add(thum);
                resultSource.add(name);
                resultSource.add(price);
                resultSource.add(headline);
                resultSource.add(rate);
                resultSource.add(code);
            
                request.setAttribute("resultSource", resultSource);
                request.setAttribute("code", code);

                request.getRequestDispatcher("/item.jsp").forward(request, response);
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
