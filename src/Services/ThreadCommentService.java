/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Technique.MyConnection;
import Models.threadComment;
import Iservices.IthreadComment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AH Info
 */
public class ThreadCommentService implements IthreadComment{
  
    private Connection conn;
    public ThreadCommentService() {
       conn = MyConnection.getInstance().getConnection();
    }
    @Override
    public threadComment selectOne(int id) {
        try {
            PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM thread " + 
                " WHERE id = ?");
            st.setObject(1,id);
            ResultSet res = st.executeQuery();
            threadComment e = new threadComment();
            if (res.next()) {
                
                e.setId(res.getInt(1));
                e.setPermalink(res.getString(2));
                e.setIs_commentable(res.getInt(3));
                e.setNum_comments(res.getInt(4));
                e.setLast_comment_at(res.getDate(5));
            }
            
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }
        
    public threadComment selectOneByIdAnnonce(String id) {
        try {
            PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM thread " + 
                " WHERE id = ?");
            st.setObject(1,id);
            ResultSet res = st.executeQuery();
            threadComment e = new threadComment();
            if (res.next()) {
                
                e.setId(res.getInt(1));
                e.setPermalink(res.getString(2));
                e.setIs_commentable(res.getInt(3));
                e.setNum_comments(res.getInt(4));
                e.setLast_comment_at(res.getDate(5));
            }
            
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }

    @Override
    public void insertThreadComent(threadComment e) {
        try {
            PreparedStatement st = conn.prepareStatement(
            "INSERT INTO thread " + 
                "(id,permalink,is_commentable,num_comments,last_comment_at) values (?,?,?,?,?)");
            st.setObject(1, e.getId());
            st.setObject(2,e.getPermalink());
            st.setObject(3,e.getIs_commentable());
            st.setObject(4,e.getNum_comments());
            st.setObject(5,e.getLast_comment_at());
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        
        
    }
    }

    @Override
    public void deleteThreadComment(threadComment e) {
         try {
            PreparedStatement st = conn.prepareStatement(
            "DELETE FROM thread " + 
                " WHERE id = ?");
            st.setObject(1,e.getId());
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }

    @Override
    public ArrayList<threadComment> selectAll() {
         ArrayList<threadComment> le = new ArrayList();
        try {
            PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM thread");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                threadComment e = new threadComment();
                e.setId(res.getInt(1));
                e.setPermalink(res.getString(2));
                e.setIs_commentable(res.getInt(3));
                e.setNum_comments(res.getInt(4));
                e.setLast_comment_at(res.getDate(5));
                le.add(e);
            }
            return le;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }
        public void editNumComments(int id,int num) {

        try {
            PreparedStatement st = conn.prepareStatement(
            "UPDATE thread SET num_comments=? WHERE id=?");
            st.setObject(1,num);
            st.setObject(2,id);
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        
        
    }
    }
    
}
