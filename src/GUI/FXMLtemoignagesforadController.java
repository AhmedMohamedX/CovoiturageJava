/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.User;
import Models.temoignage;
import Services.TemoignageService;
import Iservices.ITemoignage;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author adel
 */
public class FXMLtemoignagesforadController implements Initializable {

     @FXML
    private Label idtem;
       @FXML
    private TableColumn<temoignage, String> messagecolumn;

    @FXML
    private TableView<temoignage> tableview;

    @FXML
    private JFXTextField message;

    @FXML
    private TableColumn<temoignage, Date> datecolumn;

      @FXML
    void supprimer(ActionEvent event) {
         ITemoignage temservices = new TemoignageService();
         int id = Integer.parseInt(idtem.getText());
          System.out.println(id);
         temservices.delete(id);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ITemoignage temservices = new TemoignageService();
     List<temoignage> list1 = temservices.selectAll();
        System.out.println(User.getIdofconnecteduser());
     
     ObservableList<temoignage> listtem = FXCollections.observableArrayList(list1);
      messagecolumn.setCellValueFactory(new PropertyValueFactory<>("message"));    
      datecolumn.setCellValueFactory(new PropertyValueFactory<>("date_tem"));
     tableview.setItems(listtem);
     tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
      ObservableList<temoignage>   selecteditem,allitems;
      allitems = tableview.getItems();
       selecteditem = tableview.getSelectionModel().getSelectedItems();
       
        idtem.setText( selecteditem.get(0).getId()+"");
    
        
        if (newSelection != null) {
        tableview.getSelectionModel().clearSelection();
    }
    });
    }    
    
}
