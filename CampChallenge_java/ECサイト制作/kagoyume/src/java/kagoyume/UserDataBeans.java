/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 入力フォームのデータを格納。チェックもする。
 * DTOに格納・DTOから取得するためのメソッドを持つ。
 *
 * @author Sanosuke
 */
public class UserDataBeans implements Serializable {
    private String name = "";
    private String password = "";
    private String mail = "";
    private String address = "";
    
    //インスタンスオブジェクトを返却させてコードを簡略
    public static UserDataBeans getInstance() {
        return new UserDataBeans();
    }
    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        //未入力(または空白のみ)の場合から文字をセット
        if (name.trim().length() == 0) {
            this.name = "";
        }else{
            this.name = name;
        }
    }
    
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        if (password.trim().length() == 0) {
            this.password = "";
        }else{
            this.password = password;
        }
    }
    
    public String getMail() {
        return this.mail;
    }
    public void setMail(String mail) {
        if (mail.trim().length() == 0) {
            this.mail = "";
        }else{
            this.mail = mail;
        }
    }
        
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        if (address.trim().length() == 0) {
            this.address = "";
        }else{
            this.address = address;
        }
    }
        
    //空のフィールドをArrayListで返す
    public ArrayList<String> checkList() {
        ArrayList<String> chkList = new ArrayList();
        if (this.name.equals("")) {
            chkList.add("name");
        }
        if (this.password.equals("")) {
            chkList.add("password");
        }
        if (this.mail.equals("")) {
            chkList.add("mail");
        }
        if (this.address.equals("")) {
            chkList.add("address");
        }
        return chkList;
    }
    
    //フィールドの値を比数(DTO)に格納
    public void udbDTOMapping(UserDataDTO udd) {
        udd.setName(this.name);
        udd.setPassword(this.password);
        udd.setMail(this.mail);
        udd.setAddress(this.address);
    }
    
    //DTOをUDBに戻す
    public UserDataBeans reUDB(UserDataDTO dto) {
        UserDataBeans udb = new UserDataBeans();
        udb.setName(dto.getName());
        udb.setPassword(dto.getPassword());
        udb.setMail(dto.getMail());
        udb.setAddress(dto.getAddress());
        return udb;
    }
    
    //update時に前と同じ情報かチェック
    public boolean checkUpdate(UserDataBeans udb) {
        boolean flag = false;
        int num = 0;
        if (this.name.equals(udb.getName())) {
            num ++;
        }
        if (this.password.equals(udb.getPassword())) {
            num ++;
        }
        if (this.mail.equals(udb.getMail())) {
            num ++;
        }
        if (this.address.equals(udb.getAddress())) {
            num ++;
        }
        if (num == 4) {
            flag = true;
        }
        return flag;
    }
    
}
