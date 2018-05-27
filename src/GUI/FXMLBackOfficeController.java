/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adel
 */
public class FXMLBackOfficeController implements Initializable {
  @FXML
    private ScrollPane container;


    void fcf8f8(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
     Parent parent125 = FXMLLoader.load(getClass().getResource("/GUI/FXMLAuthentification.fxml"));
       Scene scene1116 = new Scene(parent125 );
       Stage stage1116  = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage1116 .hide();
       stage1116 .setScene(scene1116 );
       stage1116.show();
    }
     @FXML
    void statistiques(ActionEvent event) throws IOException {
    AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Statistique.fxml"));
    container.setContent(pane); 
    }
    @FXML
    void loadtem(ActionEvent event) throws IOException {
    AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLtemforad.fxml"));
    container.setContent(pane); 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void annonces(ActionEvent event) throws IOException {
             Parent parent125 = FXMLLoader.load(getClass().getResource("/GUI/ListAnnoncesBack.fxml"));
       Scene scene1116 = new Scene(parent125 );
       Stage stage1116  = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage1116 .hide();
       stage1116 .setScene(scene1116 );
       stage1116.show();
    }

    @FXML
    private void rec(ActionEvent event) throws IOException {
          Parent parent125 = FXMLLoader.load(getClass().getResource("/GUI/ListReclamationAdmin.fxml"));
       Scene scene1116 = new Scene(parent125 );
       Stage stage1116  = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage1116 .hide();
       stage1116 .setScene(scene1116 );
       stage1116.show();
    }

  
}
