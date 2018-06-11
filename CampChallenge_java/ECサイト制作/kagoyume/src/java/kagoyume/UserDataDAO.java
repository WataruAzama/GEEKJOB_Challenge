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
            String sql = "SELECT name,password FROM user_t WHERE deleteFlg = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, 0);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                UserDataDTO dto = new UserDataDTO();
                dto.setName(rs.getString(1));
                dto.setPassword(rs.getString(2));
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
    
}
