/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Technique.MyConnection;
import Models.Annonce;
import Iservices.Iannonce;
import Models.User;
import Models.trajet;
import java.time.LocalDate;
import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author AH Info
 */
public class AnnonceService implements Iannonce{
    trajetService ts = new trajetService();
    UserServices us = new UserServices();
            private Connection conn;
    public AnnonceService() {
       conn = MyConnection.getInstance().getConnection();
    }
    @Override
    public Annonce selectOne(int id) {
        try {
            PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM annonce " + 
                " WHERE id = ?");
            st.setObject(1,id);
            ResultSet res = st.executeQuery();
            Annonce e = new Annonce();
            if (res.next()) {
                
                e.setId(res.getInt(1));
                e.setDate(res.getDate(4));
                e.setPrix(res.getDouble(5));
                e.setDeviation(res.getString(6));
                e.setDate_depart(res.getDate(7));
                e.setCommentaire(res.getString(8));
                e.setNb_places(res.getInt(9));
                trajet t = ts.FindById(res.getInt(3));
                User u = us .FindById(res.getInt(2));
                e.setMembre_id(u);
                e.setTrajet_id(t);
                e.setRating(res.getInt(10));
                e.setNbrrate(res.getInt(11));
            }
            
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }

    @Override
    public void insertAnnonce(Annonce e) {
           
        try {
            PreparedStatement st = conn.prepareStatement(
            "INSERT INTO annonce " + 
                "(membre_id,date,prix,deviation,date_depart,commentaire,nb_places,trajet_id,heure_depart) values (?,?,?,?,?,?,?,?,?)");
            st.setObject(1, e.getMembre_id().getId());
            st.setObject(2,e.getDate());
            st.setObject(3,e.getPrix());
            st.setObject(4,e.getDeviation());
            st.setObject(5,e.getDate_depart());
            st.setObject(6,e.getCommentaire());
            st.setObject(7,e.getNb_places());
            st.setObject(8,e.getTrajet_id().getId());
            st.setObject(9,e.getHeure_depart());
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        
        
    }
    }

    @Override
    public void deleteAnnonce(Annonce e) {
        try {
            PreparedStatement st = conn.prepareStatement(
            "DELETE FROM annonce " + 
                " WHERE id = ?");
            st.setObject(1,e.getId());
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }

    @Override
    public ArrayList<Annonce> selectAll() {
         ArrayList<Annonce> le = new ArrayList();
        try {
            PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM annonce");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Annonce e = new Annonce();
                e.setId(res.getInt(1));
                e.setDate(res.getDate(4));
                e.setPrix(res.getDouble(5));
                e.setDeviation(res.getString(6));
                e.setDate_depart(res.getDate(7));
                e.setCommentaire(res.getString(8));
                e.setNb_places(res.getInt(9));
                User u = us .FindById(res.getInt(2));
                e.setMembre_id(u);
                e.setRating(res.getInt(10));
                e.setNbrrate(res.getInt(11));
                le.add(e);
            }
            return le;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }

    @Override
    public ArrayList<Annonce> findByPrix(double prix,int id) {
        ArrayList<Annonce> le = new ArrayList();
        try {
            PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM annonce" + 
                " WHERE prix = ? AND membre_id=?");
            st.setObject(1,prix);
            st.setObject(2, id);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Annonce e = new Annonce();
                e.setId(res.getInt(1));
                e.setDate(res.getDate(4));
                e.setPrix(res.getDouble(5));
                e.setDeviation(res.getString(6));
                e.setDate_depart(res.getDate(7));
                e.setCommentaire(res.getString(8));
                e.setNb_places(res.getInt(9));
                User u = us .FindById(res.getInt(2));
                e.setMembre_id(u);
                e.setRating(res.getInt(10));
                e.setNbrrate(res.getInt(11));
                le.add(e);
            }
            return le;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }

    @Override
    public void editAnnonce(Annonce e) {

        try {
            PreparedStatement st = conn.prepareStatement(
            "UPDATE annonce SET prix=?,deviation=?,commentaire=? WHERE id=?");
            st.setObject(1,e.getPrix());
            st.setObject(2,e.getDeviation());
            st.setObject(3,e.getCommentaire());
            st.setObject(4,e.getId());
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        
        
    }
    }
        
    public void editrateAnnonce(int id,int nbr,int r) {

        try {
            PreparedStatement st = conn.prepareStatement(
            "UPDATE annonce SET rating=?,nbrrate=? WHERE id=?");
            st.setObject(1,r);
            st.setObject(2,nbr);
            st.setObject(3,id);
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        
        
    }
    }

    @Override
    public ArrayList<Annonce> selectByUser(int id) {
       ArrayList<Annonce> le = new ArrayList();
       
        try {
            PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM annonce WHERE membre_id=?");
            st.setObject(1, id);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Annonce e = new Annonce();
                e.setId(res.getInt(1));
                e.setDate(res.getDate(4));
                e.setPrix(res.getDouble(5));
                e.setDeviation(res.getString(6));
                e.setDate_depart(res.getDate(7));
                e.setCommentaire(res.getString(8));
                e.setNb_places(res.getInt(9));
                trajet t = ts.FindById(res.getInt(3));
                e.setTrajet_id(t);
                User u = us .FindById(res.getInt(2));
                e.setMembre_id(u);
                e.setRating(res.getInt(10));
                e.setNbrrate(res.getInt(11));
                le.add(e);
            }
            return le;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }

    @Override
    public ArrayList<Annonce> recherche(String vd, String va, LocalDate d) {
       ArrayList<Annonce> le = new ArrayList();
       
        try {
            PreparedStatement st = conn.prepareStatement(
            "SELECT a.* from annonce a, "+
              "trajet t, "+
               "ville v, "+
               "ville v1 "+
               "WHERE a.date_depart LIKE ? AND a.trajet_id=t.id AND t.ville_depart_id=v.id AND v.nom=? "+
               "AND t.ville_arrive_id=v1.id AND v1.nom=?");
            
            st.setObject(1,d.toString()+"%");
            st.setObject(2, vd);
            st.setObject(3, va);
            
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Annonce e = new Annonce();
                e.setId(res.getInt(1));
                e.setDate(res.getDate(4));
                e.setPrix(res.getDouble(5));
                e.setDeviation(res.getString(6));
                e.setDate_depart(res.getDate(7));
                e.setCommentaire(res.getString(8));
                e.setNb_places(res.getInt(9));
                trajet t = ts.FindById(res.getInt(3));
                e.setTrajet_id(t);
                User u = us .FindById(res.getInt(2));
                e.setMembre_id(u);
                e.setRating(res.getInt(10));
                e.setNbrrate(res.getInt(11));
                le.add(e);
            }
            return le;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }
     @Override
    public ArrayList<Annonce> last(String d1,String d2,String location) {
       ArrayList<Annonce> le = new ArrayList();
       
        try {
            PreparedStatement st = conn.prepareStatement(
            "SELECT a.* from annonce a, "+
              "trajet t, "+
               "ville v "+
               "WHERE a.date_depart > ? AND a.date_depart < ? AND a.trajet_id=t.id AND t.ville_depart_id=v.id AND v.nom LIKE ? ");
            
            st.setObject(1,d1);
            st.setObject(2, d2);
            st.setObject(3, "%"+location+"%");
            System.out.println(st);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Annonce e = new Annonce();
                e.setId(res.getInt(1));
                e.setDate(res.getDate(4));
                e.setPrix(res.getDouble(5));
                e.setDeviation(res.getString(6));
                e.setDate_depart(res.getDate(7));
                e.setCommentaire(res.getString(8));
                e.setNb_places(res.getInt(9));
                trajet t = ts.FindById(res.getInt(3));
                e.setTrajet_id(t);
                User u = us .FindById(res.getInt(2));
                e.setMembre_id(u);
                e.setRating(res.getInt(10));
                e.setNbrrate(res.getInt(11));
                le.add(e);
            }
            return le;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }

    @Override
    public ArrayList<Annonce> findByLogin(String d1) {
        User u11 = us.FindByLogin(d1);
        int ff = u11.getId();
    
        ArrayList<Annonce> le = new ArrayList();
        try {
            PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM annonce" + 
                " WHERE membre_id=?");
            st.setObject(1,ff);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Annonce e = new Annonce();
                e.setId(res.getInt(1));
                e.setDate(res.getDate(4));
                e.setPrix(res.getDouble(5));
                e.setDeviation(res.getString(6));
                e.setDate_depart(res.getDate(7));
                e.setCommentaire(res.getString(8));
                e.setNb_places(res.getInt(9));
                User u = us .FindById(res.getInt(2));
                e.setMembre_id(u);
                e.setRating(res.getInt(10));
                e.setNbrrate(res.getInt(11));
                le.add(e);
            }
            return le;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }
}
