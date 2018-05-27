/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Iservices.IService;
import Technique.MyConnection;
import Models.voiture;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author fenina
 */
public class VoitureService  implements IService<voiture ,Integer>{
      private Connection connection;
    private PreparedStatement ps;

    public VoitureService() {
                connection = MyConnection.getInstance().getConnection();

    }
    

    @Override
    public void add(voiture t) {
          String req = "insert into voiture (chauffeur_id,marque,comfort,nb_places,couleur,categorie,visite) values (?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1,t.getChauffeur_id());
            ps.setString(2, t.getMarque());
            ps.setString(3,t.getComfort());
            ps.setInt(4,t.getNb_places());
            ps.setString(5,t.getCouleur());
            ps.setString(6,t.getCategorie());
            ps.setObject(7, t.getVisite());
            
            
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

    @Override
    public void delete(Integer r) {
String req = "delete from voiture where id=?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1,r);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public voiture findById(Integer r) {
String req = "select * from voiture where id= ?";
        voiture v1 = null;
        
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
            v1 = new voiture(resultSet.getInt(1), resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getString(7) );
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v1;  
    }
    public voiture findByChauffeur_id(Integer r) {
String req = "select * from voiture where chauffeur_id= ?";
        voiture v1 = null;
        
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
            v1 = new voiture(resultSet.getInt(1), resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getString(7) );
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v1;  
    }

    @Override
    public List<voiture> getAll() {
        String req = "select * from voiture";
        List<voiture> voitures = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                voiture v= new voiture(resultSet.getInt(1), resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getString(7) );
                voitures.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voitures; 

    }
    
   
    public void modifier(voiture t) {
        String req="UPDATE voiture SET marque=?,comfort=?, nb_places=?,couleur=?,categorie=?,visite=? WHERE id=?";
      
        try {          ps = connection.prepareStatement(req);
                 ps.setString (1,t.getMarque());
                 ps.setString(2,t.getComfort());
                 ps.setInt(3, t.getNb_places());
                 ps.setString(4,t.getCouleur());
                 ps.setString(5,t.getCategorie());
                 ps.setDate(6, (Date) t.getVisite());
                 ps.setInt(7,t.getId());
                 

                 
          

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    

    }   

    @Override
    public voiture FindById(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
