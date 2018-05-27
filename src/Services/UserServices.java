/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.User;
import Iservices.IUserService;
import Models.Annonce;
import Technique.MyConnection;
import Technique.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adel
 */
public class UserServices implements IUserService{
  private PreparedStatement ps;
    private Connection connection;
    public UserServices() {
       connection = MyConnection.getInstance().getConnection();
    }
   

    @Override
    public boolean Authentification(String login, String password) {
 boolean exist = false;
    try {
      String request = "select * from membre where username=?";
    PreparedStatement st;
        st = connection.prepareStatement(request);
         st.setString(1,login);
        ResultSet rs = st.executeQuery();
       
        int i = 0;
        while(rs.next()){
            i++; 
            if(i==0){
                exist=false;
            }else{
                if(BCrypt.checkpw(password,rs.getString(8))==true){
                    
                    User.setIdofconnecteduser(rs.getInt(1));
                    
                    exist=true;
                }else{
                    exist=false;
                }
            }
        }
    } catch (SQLException ex) {
     
    }
       
        return exist;
        
    }

    @Override
    public void add(User u) {
   String request = "insert into membre SET username=?,username_canonical=?,email=?,email_canonical=?,enabled=?,password=?,roles=?,telephone=?,codeconfirmation=?,photo_membre=?";
    try {
        String passwd = BCrypt.hashpw(u.getPassword(),BCrypt.gensalt(13));
        PreparedStatement st = connection.prepareStatement(request);
        st.setString(1,u.getNom());
        st.setString(2,u.getNom());
        st.setString(3,u.getEmail());
        st.setString(4,u.getEmail());
       st.setInt(5,0);
        st.setString(6,passwd );
        st.setString(7, "a:1:{i:0;s:11:\"ROLE_MEMBRE\";}");
        st.setInt(8,u.getTelephone());
        st.setString(9,u.getCodeConfimation());
        st.setString(10,"photo_membre/1.jpg");
        st.executeUpdate();
        
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
        
    
    
    }

   
    public void update(User u) {
     String request = "UPDATE membre SET username=?,username_canonical=?,photo_membre=?,sexe=?,tabagisme=?,musique=?,animaux=?,prenom=?,telephone=?,premis_conduite=? WHERE id=?";
    try {
      
        PreparedStatement st = connection.prepareStatement(request);
        st.setString(1,u.getNom());
        st.setString(2,u.getNom());
        st.setString(3,u.getPhotoprofilpath());
        st.setString(4,u.getSexe());
        st.setInt(5,u.getTabagime());
        st.setString(6,u.getMusique());
        st.setInt(7, u.getAnimaux());
        st.setString(8, u.getPrenom());
        st.setInt(9,u.getTelephone());
        st.setString(10,u.getPermisConduire());
       st.setInt(11,u.getId());
  
        st.executeUpdate();
        
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
        
    }
    @Override
  public void setConnected(int r) {
     String request = "UPDATE membre SET connect=? WHERE id=?";
    try {
      
        PreparedStatement st = connection.prepareStatement(request);
        st.setObject(1, 1);
        st.setObject(2, r);
  
        st.executeUpdate();
        
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
        
    }
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAll() {
       ArrayList<User> le = new ArrayList();
        try {
            PreparedStatement st = connection.prepareStatement(
            "SELECT * FROM membre WHERE roles = \"a:1:{i:0;s:11:\\\"ROLE_MEMBRE\\\";}\" AND id != ? ");
            st.setObject(1, User.getIdofconnecteduser());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
               User user = new User(rs.getInt(1),rs.getString(2),rs.getString(19),rs.getString(8),rs.getString(12),rs.getInt(6),rs.getString(22),rs.getInt(20));
             user.setPhotoprofilpath(rs.getString(13));
             user.setPermisConduire(rs.getString(21));
             user.setMusique(rs.getString(16));
             user.setSexe(rs.getString(14));
             user.setTabagime(rs.getInt(15));
             user.setAnimaux(rs.getInt(17));
             user.setConnect(rs.getInt(23));
                le.add(user);
            }
            return le;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }

    @Override
    public User FindById(Integer id) {
 
   User user=null;
    try {
        String request = "select * from membre where id=?";
        PreparedStatement st = connection.prepareStatement(request);
        st.setInt(1, id);
        ResultSet rs =st.executeQuery();
        while(rs.next()){
            user = new User(rs.getInt(1),rs.getString(2),rs.getString(19),rs.getString(8),rs.getString(12),rs.getInt(6),rs.getString(22),rs.getInt(20));
             user.setPhotoprofilpath(rs.getString(13));
             user.setPermisConduire(rs.getString(21));
             user.setMusique(rs.getString(16));
             user.setSexe(rs.getString(14));
             user.setTabagime(rs.getInt(15));
             user.setAnimaux(rs.getInt(17));
             user.setJetons(rs.getInt(24));
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
    }
return user;
    }

    @Override
    public void setenabledtrue(int id) {
         String request = "update membre set enabled=? where id=?";
        PreparedStatement st;
        
    try {
              st = connection.prepareStatement(request);
              st.setInt(1, 1);
              st.setInt(2, id);
              st.executeUpdate();
              
    } catch (SQLException ex) {
        Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
    }
  
    }

    @Override
    public void setNotConnected(int r) {
          String request = "UPDATE membre SET connect=? WHERE id=?";
    try {
      
        PreparedStatement st = connection.prepareStatement(request);
        st.setObject(1, null);
        st.setObject(2, r);
  
        st.executeUpdate();
        
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
        
    }
    @Override
    public void Payer(int r,int s) {
          String request = "UPDATE membre SET solde=? WHERE id=?";
    try {
      
        PreparedStatement st = connection.prepareStatement(request);
        st.setObject(1, s);
        st.setObject(2, r);
  
        st.executeUpdate();
        
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
        
    }
    @Override
    public void GetPayer(int r,int s) {
          String request = "UPDATE membre SET solde=? WHERE id=?";
    try {
      
        PreparedStatement st = connection.prepareStatement(request);
        st.setObject(1, s);
        st.setObject(2, r);
  
        st.executeUpdate();
        
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
        
    }

    @Override
    public User FindByLogin(String id) {
        User user=null;
    try {
        String request = "select * from membre where username=?";
        PreparedStatement st = connection.prepareStatement(request);
        st.setString(1, id);
        ResultSet rs =st.executeQuery();
        while(rs.next()){
            user = new User(rs.getInt(1),rs.getString(2),rs.getString(19),rs.getString(8),rs.getString(12),rs.getInt(6),rs.getString(22),rs.getInt(20));
             user.setPhotoprofilpath(rs.getString(13));
             user.setPermisConduire(rs.getString(21));
             user.setMusique(rs.getString(16));
             user.setSexe(rs.getString(14));
             user.setTabagime(rs.getInt(15));
             user.setAnimaux(rs.getInt(17));
             user.setJetons(rs.getInt(24));
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
    }
return user;
    }
     @Override
    public User findById(Integer r) {
User user=null;
    try {
        String request = "select * from membre where id=?";
        PreparedStatement st = connection.prepareStatement(request);
        st.setInt(1, r);
        ResultSet rs =st.executeQuery();
        while(rs.next()){
            user = new User(rs.getInt(1),rs.getString(2),rs.getString(19),rs.getString(8),rs.getString(12),rs.getInt(6),rs.getString(22),rs.getInt(20));
             user.setPhotoprofilpath(rs.getString(13));
             user.setPermisConduire(rs.getString(21));
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
    }
return user;     }

}
