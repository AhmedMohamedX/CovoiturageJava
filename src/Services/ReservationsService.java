/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Iservices.IService;
import Models.Annonce;
import Models.Reservations;
import Models.User;
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
public class ReservationsService implements IService<Reservations ,Integer> {
    private PreparedStatement ps;
    private Connection connection;
    public ReservationsService() {
       connection = MyConnection.getInstance().getConnection();
    }

    @Override
    public void add(Reservations t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reservations FindById(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservations> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    public Reservations FindByIdAnnonce(int r) {
          try {
            PreparedStatement st = connection.prepareStatement(
            "SELECT * FROM reservations " + 
                " WHERE annonce_id = ?");
            st.setObject(1,r);
            ResultSet res = st.executeQuery();
            Reservations e = new Reservations();
            if (res.next()) {
                
                e.setId(res.getInt(1));
                e.setAnnonce(res.getInt(2));
                e.setChauffeur(res.getString(3));
                e.setPassager1(res.getString(4));
                e.setPassager2(res.getString(5));
                e.setPassager3(res.getString(6));
                e.setPassager4(res.getString(7));
                e.setDateReservation(res.getDate(8));
                e.setNbplace(res.getInt(9));
            }
            
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(covoiturageedt.CovoiturageEDT.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }
    
    


    
    
}
