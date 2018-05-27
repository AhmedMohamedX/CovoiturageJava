/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.User;
import Services.UserServices;
import Iservices.IUserService;
import com.jfoenix.controls.JFXTextField;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adel
 */
public class ConfirmAccountController implements Initializable {

    @FXML
    private JFXTextField code;

    @FXML
    void Confirmaccount(ActionEvent event) throws IOException {
  IUserService userservice = new UserServices();

User u = userservice.FindById(User.getIdofconnecteduser());
      
if((code.getText()).equals(u.getCodeConfimation())){
    u.setEnabled(1);
    userservice.setenabledtrue(User.getIdofconnecteduser());
    
     Navigator.LoadScene(Navigator.authentification);
    
    
    
}
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
