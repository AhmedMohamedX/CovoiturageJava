/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Iservices.IService;
import Models.Annonce;
import Models.Ville;
import Technique.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fenina
 */
public class VilleService implements IService<Ville ,Integer>{
               private PreparedStatement ps;
    private Connection connection;
    public VilleService() {
       connection = MyConnection.getInstance().getConnection();
    }
    @Override
    public void add(Ville t) {
        String req="insert into ville(nom,longitude,latitude) values (?,?,?)";
               try {
            ps = connection.prepareStatement(req);
            ps.setString(1,t.getNom());
            ps.setString(2, t.getLongitude());
            ps.setString(3,t.getLatitude());        
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Ville findById(int r) {
        try {
            PreparedStatement st = connection.prepareStatement(
            "SELECT * FROM ville " + 
                " WHERE id = ?");
            st.setObject(1,r);
            ResultSet res = st.executeQuery();
            Ville e = new Ville();
            if (res.next()) {
                
                e.setId(res.getInt(1));
                e.setNom(res.getString(2));
                e.setLongitude(res.getString(3));
                e.setLatitude(res.getString(4));
            }
            
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    
    }

    @Override
    public List<Ville> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void modifier(Ville t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Ville FindByName(String r){
    String req = "select * from ville where nom= ?";
        Ville v1 = null;
        
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, r);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
            v1 = new Ville(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4) );
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v1;  
    
    }

    @Override
    public Ville FindById(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
