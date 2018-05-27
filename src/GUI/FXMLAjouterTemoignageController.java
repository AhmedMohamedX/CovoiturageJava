/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.User;
import Services.TemoignageService;
import Iservices.ITemoignage;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Models.temoignage;
import Services.UserServices;
import Iservices.IUserService;
import java.sql.Date;
import java.util.Calendar;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author adel
 */
public class FXMLAjouterTemoignageController implements Initializable {
    @FXML
    private JFXTextArea message;

    @FXML
    void ajouterTemoignange(ActionEvent event) {
        if(message.getText().isEmpty()){
              Notifications NotificationBuilder = Notifications.create()
                                .title("error")
                                .text("veuiller remplir tt les champs")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.TOP_RIGHT);
                        NotificationBuilder.showError();
        }else{
        ITemoignage temoignageservices = new TemoignageService();
            IUserService userservice = new UserServices();
             int id = User.getIdofconnecteduser();
            User user=userservice.FindById(id);
       
        temoignage te = new temoignage(user, message.getText(), new Date(Calendar.getInstance().getTime().getTime()));
        temoignageservices.add(te);
          Notifications NotificationBuilder = Notifications.create()
                                .title("succée")
                                .text("")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.TOP_RIGHT);
                        NotificationBuilder.showConfirm();}
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
