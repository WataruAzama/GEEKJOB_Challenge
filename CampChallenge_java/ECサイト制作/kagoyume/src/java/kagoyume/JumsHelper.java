/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.util.ArrayList;

/**
 *画面系の処理や表示を簡略化するためのヘルパークラス。
 * 
 * @author Sanosuke
 */
public class JumsHelper {
    
    //呼び出しの簡略化
    public static JumsHelper getInstance() {
        return new JumsHelper();
    }
    
    //トップへのリンク
    private final String homeURL = "top.jsp";
    //トップへのリンクを返却
    public String home() {
        return "<a href=\""+homeURL+"\">トップへ戻る</a>";
    }
    
    //ログイン(ログアウト)画面へのリンク
    private final String loginURL = "Login";
    public String loginPage(String str) {
        return "<form action=\""+this.loginURL+"\" method=\"POST\">"
                + "<input type=\"hidden\" name=\"utlData\" value=\"/"+str+"\">"
                + "<input type=\"submit\" name=\"btnsubmit\" value=\"ログイン画面へ\">"
                + "</form>";
    }
    public String logoutPage() {
        return "<a href=\""+loginURL+"\">ログアウト画面へ</a>";
    }
    
    public String checkInput(ArrayList<String> checkList) {
        String output = "";
        for (String val : checkList) {
            if (val.equals("name")) {
                output += "名前";
            }
            if (val.equals("password")) {
                output += "パスワード";
            }
            if (val.equals("mail")) {
                output += "メール";
            }
            if (val.equals("address")) {
                output += "住所";
            }
            output += "が未入力です<br>";
        }
        return output;
    }
    
}
