/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Technique.MyConnection;
import Models.comment;
import Models.threadComment;
import Iservices.Icomment;
import Iservices.IthreadComment;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;

/**
 *
 * @author AH Info
 */
public class CommentService implements Icomment{
 private Connection conn;
 UserServices us = new UserServices();
  ThreadCommentService ts = new ThreadCommentService();
    public CommentService() {
       conn = MyConnection.getInstance().getConnection();
    }
    @Override
    public comment selectOne(int id) {
         try {
            PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM comment " + 
                " WHERE id = ?");
            st.setObject(1,id);
            ResultSet res = st.executeQuery();
            comment e = new comment();
            if (res.next()) {
                
                e.setId(res.getInt(1));
                e.setThreadComment(new threadComment(res.getInt(2)));
                e.setBody(res.getString(3));
                e.setAncestors(res.getString(4));
                e.setDepth(res.getInt(5));
                e.setCreated_at(res.getDate(6));
                e.setState(res.getInt(7));
            }
            
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }

    @Override
    public void insertComment(comment e) {
        try {
            PreparedStatement st = conn.prepareStatement(
            "INSERT INTO comment " + 
                "(thread_id,body,ancestors,depth,created_at,state,membre_id) values (?,?,?,?,?,?,?)");
            st.setObject(1,e.getThreadComment().getId());
            st.setObject(2,e.getBody());
            st.setObject(3,e.getAncestors());
            st.setObject(4,e.getDepth());
            st.setObject(5,e.getCreated_at());
            st.setObject(6,e.getState());
            st.setObject(7, e.getMembre_id().getId());
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        
        
    }
    }

    @Override
    public void deleteComment(comment e) {
         try {
            PreparedStatement st = conn.prepareStatement(
            "DELETE FROM comment " + 
                " WHERE id = ?");
            st.setObject(1,e.getId());
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }

    @Override
    public ArrayList<comment> selectAll() {
       ArrayList<comment> le = new ArrayList();
        try {
            PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM comment");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                comment e = new comment();
                e.setId(res.getInt(1));
                e.setThreadComment(new threadComment(res.getInt(2)));
                e.setBody(res.getString(3));
                e.setAncestors(res.getString(4));
                e.setDepth(res.getInt(5));
                e.setCreated_at(res.getDate(6));
                e.setState(res.getInt(7));
                le.add(e);
            }
            return le;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }
        
    public ArrayList<comment> selectAllByAnnonce(String id) {
       threadComment tc=ts.selectOneByIdAnnonce(id);
       int idt = tc.getId();
       ArrayList<comment> le = new ArrayList();
        try {
            PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM comment WHERE thread_id=?");
            st.setObject(1, idt);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                comment e = new comment();
                e.setId(res.getInt(1));
                e.setThreadComment(new threadComment(res.getInt(2)));
                e.setBody(res.getString(3));
                e.setAncestors(res.getString(4));
                e.setDepth(res.getInt(5));
                e.setCreated_at(res.getDate(6));
                e.setState(res.getInt(7));
                User u =us.FindById(res.getInt(8));
                e.setMembre_id(u);
                le.add(e);
            }
            return le;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }
      public XYChart.Series<String, Integer> StatCommentaireParUser() {
        try {
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            PreparedStatement st = conn.prepareStatement("select count(*),U.username from temoignage C inner join membre U where C.membre_id= U.id group by U.username");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(2), rs.getInt(1)));
            }
            return series;
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
