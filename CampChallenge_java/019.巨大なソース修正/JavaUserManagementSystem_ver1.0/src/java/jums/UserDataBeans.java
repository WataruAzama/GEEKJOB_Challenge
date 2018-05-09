package jums;

import java.io.Serializable;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *InsertConfirm.javaで使用
 * 
 * @author Sanosuke
 */
public class UserDataBeans implements Serializable {
    private String name;
    private String year;
    private String month;
    private String day;
    private String type;
    private String tell;
    private String comment;     
    
    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataBeans getInstance(){
        return new UserDataBeans();
    }
    
    //取得した内容をまとめてセッションに登録する処理
    public void insert(UserDataBeans ud,HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("name", ud.getName());
        session.setAttribute("year", ud.getYear());
        session.setAttribute("month",ud.getMonth());
        session.setAttribute("day", ud.getDay());
        session.setAttribute("type", ud.getType());
        session.setAttribute("tell", ud.getTell());
        session.setAttribute("comment", ud.getComment());
    }    
    
    public String getName() {
    return name;
    }
    public void setName(String name) {
    this.name = name;
    }
    
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
    public String getTell() {
        return tell;
    }
    public void setTell(String tell) {
        this.tell = tell;
    }
    
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    //セッションを利用して格納し直すメソッド
    public void setting(UserDataBeans ud,HttpServletRequest request) {
        HttpSession session = request.getSession();
        setName((String)session.getAttribute("name"));
        setYear((String)session.getAttribute("year"));
        setMonth((String)session.getAttribute("month"));
        setDay((String)session.getAttribute("day"));
        setType((String)session.getAttribute("type"));
        setTell((String)session.getAttribute("tell"));
        setComment((String)session.getAttribute("comment"));
    }
    
}
