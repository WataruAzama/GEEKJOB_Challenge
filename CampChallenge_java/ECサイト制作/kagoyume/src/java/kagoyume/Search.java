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

class CommonFunc {
    public String searchXmlValue(String[] targetNodeName, int nodeNo, Node node) {
        try {
            String s = null;
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
            return "情報なし";
        }
    }
}

/**
 *
 * @author Sanosuke
 */
public class Search extends HttpServlet {

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
            
            String retrieval = "";
            
            //ログイン画面から戻ってきた場合の処理
            if ((String)hs.getAttribute("reSearch") != null && request.getParameter("retrieval") == null) {
                retrieval = (String)hs.getAttribute("retrieval");
            //値が空文字の場合エラー
            }else if (request.getParameter("retrieval").trim().equals("")) {
                request.setAttribute("errorStr", "値を入力してください");
                request.getRequestDispatcher("/top.jsp").forward(request, response);
            }
            //URL接続
            String str = "https://shopping.yahooapis.jp/ShoppingWebService/V1/itemSearch?appid=dj00aiZpPXRvUk1TSW9IREs0NyZzPWNvbnN1bWVyc2VjcmV0Jng9MTI-&query=";
            if (retrieval.equals("")) {
                retrieval = request.getParameter("retrieval");
            }
            //検索ワードを他ページでも使えるようにセッションに格納
            hs.setAttribute("retrieval", retrieval);
            //日本語を検索やクッキー用の文字列に変換
            String str2 = URLEncoder.encode(retrieval,"UTF-8");
            String str3 = "&hits=10";
            URL url = new URL(str+str2+str3);
            
            //ログイン等から戻ってくるための準備
            String strURL = request.getRequestURL()+"?retrieval="+str2+"&btnsubmit=%E6%A4%9C%E7%B4%A2";
            hs.setAttribute("reSearch", strURL);

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

            //root要素(一番最初のnode)を取得
            Element elementRoot = document.getDocumentElement();
            //検索ヒット数を取得
            String eRoot = elementRoot.getAttribute("totalResultsAvailable");                        

            //Hit以下しか探さないようにする処理
            NodeList localNodeList = elementRoot.getElementsByTagName("Hit");
            //XMLから要素を抜き取る自作関数
            CommonFunc common = new CommonFunc();
            
            ArrayList<ArrayList<String>> resultGoods = new ArrayList();
            //サムネイルを取得
            ArrayList<String> thum = new ArrayList();
            for (int i=0; i<localNodeList.getLength(); i++) {
                Element elementItem = (Element)localNodeList.item(i);
                String elementName[] = {"Image","Small"};
                String s = common.searchXmlValue(elementName, 0, elementItem);
                thum.add(s);
            }
            //商品名を取得            
            ArrayList<String> name = new ArrayList();
            for (int i=0; i<localNodeList.getLength(); i++) {
                Element elementItem = (Element)localNodeList.item(i);
                String elementName[] = {"Name"};
                String s = common.searchXmlValue(elementName, 0, elementItem);
                name.add(s);
            }
            //金額を取得            
            ArrayList<String> price = new ArrayList();
            for (int i=0; i<localNodeList.getLength(); i++) {
                Element elementItem = (Element)localNodeList.item(i);
                String elementName[] = {"Price"};
                String s = common.searchXmlValue(elementName, 0, elementItem);
                price.add(s);
            }
            //キャッチコピーを取得(持ち回り用)
            ArrayList<String> headline = new ArrayList();
            for (int i=0; i<localNodeList.getLength(); i++) {
                Element elementItem = (Element)localNodeList.item(i);
                String elementName[] = {"Headline"};
                String s = common.searchXmlValue(elementName, 0, elementItem);
                headline.add(s);
            }
            //評価値を取得(持ち回り用)
            ArrayList<String> rate = new ArrayList();
            for (int i=0; i<localNodeList.getLength(); i++) {
                Element elementItem = (Element)localNodeList.item(i);
                String elementName[] = {"Review","Rate"};
                String s = common.searchXmlValue(elementName, 0, elementItem);
                rate.add(s);
            }
            //商品コードを取得(持ちまわり用)
            ArrayList<String> code = new ArrayList();
            for (int i=0; i<localNodeList.getLength(); i++) {
                Element elementItem = (Element)localNodeList.item(i);
                String elementName[] = {"Code"};
                String s = common.searchXmlValue(elementName, 0, elementItem);
                code.add(s);
            }
            
            //検索結果を持ちまわる
            resultGoods.add(thum);
            resultGoods.add(name);
            resultGoods.add(price);
            resultGoods.add(headline);
            resultGoods.add(rate);
            resultGoods.add(code);
            hs.setAttribute("resultGoods", resultGoods);
            
            //jspに送るデータ
            request.setAttribute("eRoot", eRoot);
            request.setAttribute("thum", thum);
            request.setAttribute("name", name);
            request.setAttribute("price", price);
            request.setAttribute("code", code);
            
            request.getRequestDispatcher("/search.jsp").forward(request, response);
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
