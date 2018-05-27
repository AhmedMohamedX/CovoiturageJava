/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import Technique.Mail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author raed
 */
public class EmailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField recepteur;

    @FXML
    private JFXTextField objet;

    @FXML
    private TextField description;

    @FXML
    private JFXButton inscription;

    @FXML
    void envoiMail(ActionEvent event) {
        Mail.send(recepteur.getText(), objet.getText(), description.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Votre mail à été envoyé");
        alert.showAndWait();
        

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
