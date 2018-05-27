/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Iservices.Iannonce;
import Models.Annonce;
import Models.NavigatorData;
import Services.AnnonceService;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Mortadhafff
 */
public class ModifierAnnonceController implements Initializable {

    @FXML
    private Label title;
    
    
    @FXML
    private JFXSlider prixx;
    @FXML
    private JFXComboBox<String> devia;
       
    @FXML
    private JFXTextField comm;
   
    

    String comentaire = NavigatorData.getInstance().getSelectedAnnonce().getCommentaire();
    double prix = NavigatorData.getInstance().getSelectedAnnonce().getPrix();
    String dev = NavigatorData.getInstance().getSelectedAnnonce().getDeviation();


    @FXML
    private AnchorPane but;
    @FXML
    private JFXButton update;

    Iannonce AnnonceService = new AnnonceService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadscen();

    }
    private void loadscen() {
        
    
        
  
            update.setVisible(true);    
        

        update.getStyleClass().add("button-raised");       
  
        prixx.setValue(prix);
        String dev2="Moins de 15 minutes";
            
            if(dev.equals("1")){
                dev2="Moins de 15 minutes";
            }else if(dev.equals("2")){
                dev2="Entre 15-30 minutes";
            }else if(dev.equals("3")){
                dev2="Plus que 30 minutes";
            }
         ObservableList<String> competence = FXCollections.observableArrayList();
        List<String> a = new ArrayList<>();
        a.add("Moins de 15 minutes");
        a.add("Entre 15-30 minutes");
        a.add("Plus que 30 minutes");
        competence.addAll(a);
        devia.setItems(competence);
        devia.setValue(dev2);
        comm.setText(comentaire);
    }

    @FXML
    private void updateannonce(ActionEvent event) {
        
            
        
        Iannonce anService = new AnnonceService();
        Annonce quu=NavigatorData.getInstance().getSelectedAnnonce();
        quu.setCommentaire(comm.getText());
         String combo1 = devia.getSelectionModel().getSelectedItem().toString();
              String combo2="0";
              
           if(combo1 == "Moins de 15 minutes"){
               combo2="1";
            }
           if(combo1 == "Entre 15-30 minutes"){
               combo2="2";
            }
           if(combo1 == "Plus que 30 minutes"){
               combo2="3";
            }
         quu.setDeviation(combo2);
         quu.setPrix((int)prixx.getValue());
        anService.editAnnonce(quu);
        Navigator.LoadScene(Navigator.ListAnnonces);
    }

}
