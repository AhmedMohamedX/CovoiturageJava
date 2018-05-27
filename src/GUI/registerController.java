/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Sms;
import Models.User;
import Services.SmsService;
import Iservices.IUserService;
import Services.UserServices;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author adel
 */
public class registerController implements Initializable {
    @FXML
    private JFXPasswordField pwd1;

    @FXML
    private JFXPasswordField pwd2;
    @FXML
    private JFXTextField numerotel;
     @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField email;
    @FXML
    void Back(ActionEvent event) throws IOException{
     Navigator.LoadScene(Navigator.authentification);
       }
    @FXML
    void Inscription(ActionEvent event) {
        if(nom.getText().isEmpty()||pwd2.getText().isEmpty()|pwd1.getText().isEmpty()||email.getText().isEmpty()){
            Notifications NotificationBuilder = Notifications.create()
                                .title("error")
                                .text("veuiller remplir tt les champs")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.TOP_RIGHT);
                        NotificationBuilder.showError(); 
        }else if(!pwd1.getText().equals(pwd2.getText())){
            Notifications NotificationBuilder = Notifications.create()
                                .title("error")
                                .text("mdp ne sont pas identiques")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.TOP_RIGHT);
                        NotificationBuilder.showError();
        }else{
            User user = new User(nom.getText(), email.getText(), pwd1.getText());
            IUserService userservice = new UserServices();
            user.setTelephone(Integer.parseInt(numerotel.getText()));
            
               Random rand = new Random();

                int  n = rand.nextInt(4000) + 1000;
                user.setCodeConfimation(n+"");
                 new SmsService().sendSms(new Sms("+216"+numerotel.getText(), "acount confirmation !! "+n));
            userservice.add(user);
     
            Notifications NotificationBuilder = Notifications.create()
                                .title("Succée")
                                .text("successfully inserted")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.TOP_RIGHT);
                        NotificationBuilder.showConfirm();
                        Navigator.LoadScene(Navigator.authentification);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
