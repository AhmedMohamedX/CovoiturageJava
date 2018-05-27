/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Iservices.IService;
import Models.trajet;
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
public class trajetService implements IService<trajet ,Integer> {
    private PreparedStatement ps;
    private Connection connection;
    public trajetService() {
       connection = MyConnection.getInstance().getConnection();
    }
    
    
    
    @Override
    public void add(trajet t) {
            
        try {
            PreparedStatement st = connection.prepareStatement(
            "insert into trajet(ville_depart_id,ville_arrive_id,distance,escale) values (?,?,?,?)");
            st.setObject(1,t.getVille_dep());
            st.setObject(2,t.getVille_arr());
            st.setObject(3,t.getDistance());
            st.setObject(4,t.getEscale());
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        
        
        }
    }

    @Override
    public void delete(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public trajet FindById(Integer r) {
String req = "select * from trajet where id= ?";
        trajet v1 = null;
        
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
            v1= new trajet(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getFloat(4), resultSet.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v1;     
    }

    @Override
    public List<trajet> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    public trajet FindByIdV(Integer r) {
String req = "select * from trajet where ville_arrive_id= ?";
        trajet v1 = null;
        
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
            v1= new trajet(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getFloat(4), resultSet.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v1;     
    }

    
    
}
