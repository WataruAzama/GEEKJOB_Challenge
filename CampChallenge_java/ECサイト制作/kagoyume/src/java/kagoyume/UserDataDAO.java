/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import base.DBManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *DTOとDBManagerクラスを使ってDBから格納や取り出しの処理
 * 
 * @author Sanosuke
 */
public class UserDataDAO {
    
    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance() {
        return new UserDataDAO();
    }
    
    //DTOに格納された値をDBに挿入
    public void insert(UserDataDTO ud) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO user_t(name,password,mail,address,total,newDate) VALUES(?,?,?,?,?,?)");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());
            st.setInt(5, 0);
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }catch(SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    public ArrayList<UserDataDTO> search() throws SQLException {
        ArrayList<UserDataDTO> box = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            String sql = "SELECT userID,name,password,mail,address,total,newDate FROM user_t WHERE deleteFlg = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, 0);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                UserDataDTO dto = new UserDataDTO();
                dto.setUserID(rs.getInt(1));
                dto.setName(rs.getString(2));
                dto.setPassword(rs.getString(3));
                dto.setMail(rs.getString(4));
                dto.setAddress(rs.getString(5));
                dto.setTotal(rs.getInt(6));
                dto.setNewDate(rs.getTimestamp(7));
                box.add(dto);
            }
        return box;
        }catch(SQLException e) {
            throw new SQLException(e);
        }finally{
            if(con != null) {
                con.close();
            }
        }
    }
    
    public void update(UserDataDTO ud) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE User_t SET name=?,password=?,mail=?,address=? WHERE userID=?");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());
            st.setInt(5, ud.getUserID());
            st.executeUpdate();
        }catch(SQLException e) {
            throw new SQLException(e);
        }finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void delete(int num) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE User_t SET deleteFlg=? WHERE UserID=?");
            st.setInt(1, 1);
            st.setInt(2, num);
            st.execute();
        }catch(SQLException e) {
            throw new SQLException(e);
        }finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    public ArrayList<UserDataDTO> login() throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            ArrayList<UserDataDTO> box = new ArrayList();
            con = DBManager.getConnection();
            String sql = "SELECT userID,name,password FROM user_t WHERE deleteFlg = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, 0);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                UserDataDTO dto = new UserDataDTO();
                dto.setUserID(rs.getInt(1));
                dto.setName(rs.getString(2));
                dto.setPassword(rs.getString(3));
                box.add(dto);
            }
        return box;
        }catch(SQLException e) {
            throw new SQLException(e);
        }finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void buyInsert(int userNum, int typeNum, ArrayList<ArrayList<String>> goods) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO buy_t(userID,itemCode,type,buyDate) VALUES(?,?,?,?)");
            for (int i=0; i<goods.size(); i++) {
                st.setInt(1, userNum);
                st.setString(2, goods.get(i).get(5));
                st.setInt(3, typeNum);
                st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                st.executeUpdate();
            }
        }catch(SQLException e) {
            throw new SQLException(e);
        }finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void buyUpdate(int userNum,int money) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            //これまでの購入金額を取得
            con = DBManager.getConnection();
            st = con.prepareStatement("SELECT total FROM user_t WHERE userID=?");
            st.setInt(1, userNum);
            ResultSet rs = st.executeQuery();
            int numTotal = 0;
            while (rs.next()) {
                numTotal = rs.getInt(1);
                numTotal += money;
            }
            //今回購入した金額をプラス
            st = con.prepareStatement("UPDATE user_t SET total=? WHERE userID=?");
            st.setInt(1, numTotal);
            st.setInt(2, userNum);
            st.executeUpdate();
        }catch(SQLException e) {
            throw new SQLException(e);
        }finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    public ArrayList<String> historySearch(int userID) throws SQLException {
        ArrayList<String> productCords = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            String sql = "SELECT itemCode FROM buy_t WHERE userID = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                productCords.add(rs.getString(1));
            }
            return productCords;
        }catch(SQLException e) {
            throw new SQLException(e);
        }finally{
            if(con != null) {
                con.close();
            }
        }
    }
    
    public Integer historyUserID(String str) throws SQLException {
        int num = 0;
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("SELECT userID FROM user_t WHERE password=? AND deleteFlg=?");
            st.setString(1, str);
            st.setInt(2, 0);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                num = rs.getInt(1);
            }
            return num;
        }catch(SQLException e) {
            throw new SQLException(e);
        }finally{
            if(con != null) {
                con.close();
            }
        }
    }
    
    public ArrayList<String> checkPassword() throws SQLException {
        ArrayList<String> alPassword = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("SELECT password FROM user_t WHERE deleteFlg=?");
            st.setInt(1,0);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                alPassword.add(rs.getString(1));
            }
            return alPassword;
        }catch(SQLException e) {
            throw new SQLException(e);
        }finally{
            if(con != null) {
                con.close();
            }
        }
    }
    
}
