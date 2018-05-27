/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;




import Iservices.IService;
import Technique.MyConnection;

import Models.reclamations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import Models.User;


/**
 *
 * @author user
 */
public class ReclamationService implements IService<reclamations, Integer>{
    private Connection connection;
    private PreparedStatement ps;

    public ReclamationService() {
                connection = MyConnection.getInstance().getConnection();

    }

    @Override
    public void add(reclamations t) {
        String req = "insert into reclamations (membre_id,sujet,date,message,reponse) values (?,?,?,?,?)";
    
        if (t.getTargetMember().getId() != 0)
            req = "insert into reclamations (membre_id,sujet,date,message,reponse, targetMemberID) values (?,?,?,?,?,?)";
        
        try
        {
            ps = connection.prepareStatement(req);
          
            ps.setInt(1,t.getMembre_id());
            ps.setString(2,t.getSujet());
            
            Date now = new Date();
            java.sql.Date dateNow = new java.sql.Date (now.getTime());
            ps.setDate(3, dateNow);
            ps.setString(4,t.getMessage());
            ps.setString(5,t.getReponse());
            
            if (t.getTargetMember().getId() != 0)
                ps.setInt(6, t.getTargetMember().getId());
            
             ps.executeUpdate();
            
            } catch (Exception e) {
            e.printStackTrace();
        }      }

    @Override
    public void delete(Integer r) {
 String req = "delete from reclamations where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1,r);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }//T    }    }
    }
    
    public reclamations findById(Integer r) {
    String req = "select * from reclamations where id = ?";
        reclamations r1 = null;
        
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            System.out.println(ps);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
            r1 = new reclamations(resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getDate(5),
                    resultSet.getString(6),
                    resultSet.getString(7)); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r1;      }
 

    public List<reclamations> searchbox(String searchinput) {
    String req = "select * from reclamations where sujet like concat('%',?,'%') or reponse like concat('%',?,'%')";
        List<reclamations> reclamations = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, searchinput);
            ps.setString(2, searchinput);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                reclamations r1 = new reclamations(resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getDate(5),
                    resultSet.getString(6),
                    resultSet.getString(7));
                
                String getMembre = "select nom, prenom from membre where id= ?";
                ps = connection.prepareStatement(getMembre);
                ps.setInt(1, resultSet.getInt(4));
                ResultSet singleRow = ps.executeQuery();
                
                if (singleRow.next())
                {
                    r1.getTargetMember().setId(resultSet.getInt(4));
                    r1.getTargetMember().setNom(singleRow.getString("nom"));
                    r1.getTargetMember().setPrenom(singleRow.getString("prenom"));
                }
                reclamations.add(r1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reclamations;    
    }
 
    public List<reclamations> search(String searchinput) {
   String req = "select * from reclamations where message like concat('%',?,'%') ";
        List<reclamations> reclamations = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, searchinput);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                reclamations r1 = new reclamations(resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getDate(5),
                    resultSet.getString(6),
                    resultSet.getString(7));
                
                String getMembre = "select nom, prenom from membre where id= ?";
                ps = connection.prepareStatement(getMembre);
                ps.setInt(1, resultSet.getInt(4));
                ResultSet singleRow = ps.executeQuery();
                
                if (singleRow.next())
                {
                    r1.getTargetMember().setId(resultSet.getInt(4));
                    r1.getTargetMember().setNom(singleRow.getString("nom"));
                    r1.getTargetMember().setPrenom(singleRow.getString("prenom"));
                }
                reclamations.add(r1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reclamations;    
    }
    @Override
    public List<reclamations> getAll() {
    String req = "select * from reclamations";
        List<reclamations> reclamations = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                reclamations r1 = new reclamations(resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getDate(5),
                    resultSet.getString(6),
                    resultSet.getString(7));
                
                String getMembre = "select nom, prenom from membre where id= ?";
                ps = connection.prepareStatement(getMembre);
                ps.setInt(1, resultSet.getInt(4));
                ResultSet singleRow = ps.executeQuery();
                
                if (singleRow.next())
                {
                    r1.getTargetMember().setId(resultSet.getInt(4));
                    r1.getTargetMember().setNom(singleRow.getString("nom"));
                    r1.getTargetMember().setPrenom(singleRow.getString("prenom"));
                }
                reclamations.add(r1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reclamations;    
    }
   
   
    public List<reclamations> getAllbymember() {
    String req = "select * from reclamations";
        List<reclamations> reclamations = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                reclamations r1 = new reclamations(resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getDate(5),
                    resultSet.getString(6),
                        resultSet.getString(7),
                    resultSet.getInt(8));
                
                String getMembre = "select nom, prenom from membre where id= ?";
                ps = connection.prepareStatement(getMembre);
                ps.setInt(1, resultSet.getInt(4));
                ResultSet singleRow = ps.executeQuery();
                
                if (singleRow.next())
                {
                    r1.getTargetMember().setId(resultSet.getInt(4));
                    r1.getTargetMember().setNom(singleRow.getString("nom"));
                    r1.getTargetMember().setPrenom(singleRow.getString("prenom"));
                }
                reclamations.add(r1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reclamations;    
    }

    
    public void modifier(reclamations t) {

String req="UPDATE reclamations SET reponse='"+t.getReponse()+"',vu=1  WHERE  id="+t.getId();
         try {
            ps = connection.prepareStatement(req);
           
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }      }
    
    public void modifier1(reclamations t) {

String req="UPDATE reclamations SET vu=2  WHERE  id="+t.getId();
         try {
            ps = connection.prepareStatement(req);
           
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }      }

    // returns all members list except current user
    public ObservableSet<String> getMembres(int currentUserID)
    {
        ObservableSet<String> listMembres = FXCollections.observableSet();
        
        try
        {
            String getAllMembresQuery = "select id, nom, prenom from membre where id != ?";
            
            ps = connection.prepareStatement(getAllMembresQuery);
            ps.setInt(1, currentUserID);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next())
            {
                User membre = new User();
                membre.setId(resultSet.getInt("id"));
                membre.setNom(resultSet.getString("nom"));
                membre.setPrenom(resultSet.getString("prenom"));
                
                listMembres.add(membre.getId() + " " + membre.getNom() + " " + membre.getPrenom());
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listMembres;
    }

    @Override
    public reclamations FindById(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
}
