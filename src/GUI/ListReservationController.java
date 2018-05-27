/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.mysql.jdbc.PreparedStatement;
import com.sun.javafx.tk.quantum.MasterTimer;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import Technique.MyConnection;
import Models.Reservations;
import Models.Annonce;
import Models.User;
import Services.ReservationServices;



/**
 * FXML Controller class
 *
 * @author Hard-System-Info
 */
public class ListReservationController implements Initializable {

    @FXML
    private TableView<Reservations> tableress;
    @FXML
    private TableColumn<Reservations, Integer> idreservation;
    @FXML
    private TableColumn<Reservations, String> chauffeur;
    @FXML
    private TableColumn<Reservations, String> pass1;
    @FXML
    private TableColumn<Reservations, String> pass2;
    @FXML
    private TableColumn<Reservations, String> pass3;
    @FXML
    private TableColumn<Reservations, String> pass4;

    @FXML
    private TableColumn<Reservations, Date> date;

    @FXML
    private Button annuler;
    private ObservableList list2;
    public Reservations ress;
    public Annonce an;
    public Reservations r;

    @FXML
    private Label lab;
    public  int userID = User.getIdofconnecteduser();
    Connection con;
    @FXML
    private AnchorPane tableres;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    public ListReservationController() {
        con = MyConnection.getInstance().getConnection();
    }
    ReservationServices res55 = new ReservationServices();

    @FXML
    private void remove(ActionEvent event) {
      
        res55.remove(ress, userID);

        res55.getPlaces(an, ress);
        list2 = FXCollections.observableArrayList(res55.afficheres(userID));
        tableress.setItems(list2);
    

    }

    /*
        if (!rss.equals(null)) {

            String req = "Update  reservations set passager1_id = NULL where id=" + rs5.getId() + "";
            try {
                Statement stm = con.createStatement();
                stm.executeUpdate(req);
                System.out.println("supp1  ok");
            } catch (SQLException ex) {
                Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        lab.setText(String.valueOf(userID));

        ReservationServices resService1 = new ReservationServices();

        chauffeur.setCellValueFactory(new PropertyValueFactory<>("chauffeur"));
        idreservation.setCellValueFactory(new PropertyValueFactory<>("id"));

        pass1.setCellValueFactory(new PropertyValueFactory<>("passager1"));
        pass2.setCellValueFactory(new PropertyValueFactory<>("passager2"));
        pass3.setCellValueFactory(new PropertyValueFactory<>("passager3"));
        pass4.setCellValueFactory(new PropertyValueFactory<>("passager4"));
        date.setCellValueFactory(new PropertyValueFactory<>("DateReservation"));

        // label1.setText(l);
        list2 = FXCollections.observableArrayList(resService1.afficheres(userID));

        tableress.setItems(list2);
        tableress.getSelectionModel().selectedItemProperty().addListener((Observable, oldvalue, newvalue) -> SetValues(newvalue));

        //////////////////
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Reservations> filteredData = new FilteredList<>(list2, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getChauffeur().toLowerCase().equals(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (person.getDateReservation().equals(newValue))/*.toLowerCase().contains(lowerCaseFilter)) */ {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Reservations> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableress.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableress.setItems(sortedData);

        ////
    }

    private void SetValues(Reservations newvalue) {

        ress = newvalue;
        System.err.println(ress);

    }

    @FXML
    private void res(ActionEvent event) {
        Navigator.LoadScene(Navigator.stat);
    }

}
