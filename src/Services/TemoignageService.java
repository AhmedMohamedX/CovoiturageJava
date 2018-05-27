/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Models.temoignage;
import Iservices.ITemoignage;
import Technique.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author Hard-System-Info
 */
public class TemoignageService implements ITemoignage{
    Connection con;

    public TemoignageService() {
       con=MyConnection.getInstance().getConnection();
    }
   

    @Override
    public void add(temoignage t) {
       
  String req = "insert into temoignage (id, membre_id,message,date_tem) values (?,?,?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(req);
            stm.setInt(1, t.getId() );
            stm.setInt(2, t.getUser().getId());
            stm.setString(3, t.getMessage());
            stm.setDate(4,t.getDate_tem() );
            stm.executeUpdate();
            System.out.println("ajout ok");
        } catch (SQLException ex) {
                ex.printStackTrace();
        } 

    }

    @Override
    public ArrayList<temoignage> selectAll() {
     ArrayList<temoignage> temoignage = new ArrayList<>();
        try {
            String req = "select * from temoignage";
           
         PreparedStatement stm = con.prepareStatement(req);
           
            ResultSet result = stm.executeQuery();
           
            while (result.next()) {
                temoignage t = new temoignage();
                t.setId(result.getInt(1));
                t.setUser(new UserServices().FindById(result.getInt(2)));
                t.setMessage(result.getString(3));
                
                t.setDate_tem(result.getDate(4));
                
                
                temoignage.add(t);
            }
            
            
        } catch (SQLException ex) {
                  ex.printStackTrace();
         }

        return temoignage;
    }

    @Override
    public void delete(Integer id) {
  try {
              String req = "delete from temoignage where id=?";
         
               PreparedStatement  stm = con.prepareStatement(req);
            stm.setInt(1,id);
             stm.executeUpdate();
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
          
    }

    @Override
    public void update(temoignage t) {
  String req = "update temoignage set message=? where id=?";
        try {
            PreparedStatement stm = con.prepareStatement(req);
            stm.setInt(2, t.getId() );
            stm.setString(1,  t.getMessage());
            stm.executeUpdate();
            System.out.println("ajout ok");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }     }

@Override
    public temoignage FindById(Integer id) {
 
  temoignage tem=null;
    try {
        String request = "select * from temoignag where id=?";
        PreparedStatement st = con.prepareStatement(request);
        st.setInt(1, id);
        ResultSet rs =st.executeQuery();
        while(rs.next()){
           tem = new temoignage(rs.getInt(1),new UserServices().FindById(rs.getInt(2)),rs.getString(3),rs.getDate(4));
                    
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
    }
return tem;
    }
    
       
    public ObservableList<PieChart.Data> StatTemoignageParMembre() {
        ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
        try {
            PreparedStatement st = con.prepareStatement("select count(*),U.username from temoignage C inner join membre U where C.membre_id= U.id group by U.username");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new PieChart.Data(rs.getString(2), rs.getInt(1)));
            }
            ObservableList<PieChart.Data> observableList;
            observableList = FXCollections.observableList(list);
            //System.out.println("ici" + observableList.size());
            return observableList;

        } catch (SQLException ex) {
            Logger.getLogger(TemoignageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
