/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.User;
import Services.UserServices;
import Iservices.IUserService;
import Technique.GeoLocation;
import Technique.ServerLocation;
import Technique.getIpAddress;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.restfb.util.DateUtils;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.internet.MailDateFormat;
import org.controlsfx.control.Notifications;
import org.joda.time.DateTime;

/**
 *
 * @author adel
 */
public class AuthentificationController implements Initializable {
    
   @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField login;

    @FXML
    void auth(ActionEvent event) throws IOException {
        Stage stage1=null;
        IUserService userservice = new UserServices();
        boolean check = userservice.Authentification(login.getText(), password.getText());
        if(check==true){
          User u = userservice.FindById(User.getIdofconnecteduser());
          if(u.getEnabled()==0){
     Navigator.LoadScene(Navigator.confirmation);
     
          }else{
              if(u.getRole().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
                 Parent parent1 = FXMLLoader.load(getClass().getResource("/GUI/FXMLBackOffice.fxml"));
       Scene scene1 = new Scene(parent1);
       stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();   
              }else{
      userservice.setConnected(User.getIdofconnecteduser());
      Navigator.LoadScene(Navigator.accueil);}
          }
        }else{
              Notifications NotificationBuilder = Notifications.create()
                                .title("Succée")
                                .text("error")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.TOP_RIGHT);
                        NotificationBuilder.showError();
        }
    }
    
    @FXML
    void inscription(ActionEvent event) throws IOException {
    Navigator.LoadScene(Navigator.inscription);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

   
            
    }    
    
   
}
