package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.management.Notification;
import Models.User;
import Models.reclamations;
import Services.ReclamationService;
import Iservices.IService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Mehdi9951
 */
public class AjouterReclamationController implements Initializable{

    @FXML
    private Rectangle pane;
    @FXML
    private Label alerteM;
    @FXML
    private Label alerteC;
    @FXML
    private Label alertN;
    @FXML
    private TextArea message;
    @FXML
    private CheckBox site;
    @FXML
    private CheckBox membre;
    @FXML
    protected ListView<String> lstMembres;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       lstMembres.setVisible(false);
       
       lstMembres.setItems(FXCollections.observableArrayList(
            new ReclamationService().getMembres(User.getIdofconnecteduser())));
           
       membre.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            lstMembres.setVisible(newValue);
            site.setSelected(!newValue);
       });
       
       site.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            membre.setSelected(!newValue);
       });
    }
    
  @FXML
    public void ajouter(ActionEvent ae) throws IOException {
        // String model = carmodel.getText();
        String message = this.message.getText();
        String reponse = "en attente de reponse";
        String sujet ="";
        
        Alert alertBox = new Alert(Alert.AlertType.ERROR);
            alertBox.setTitle("Error");
        
        if (!site.isSelected() && !membre.isSelected())
        {
            alertBox.setHeaderText("Veuillez selectionnez le type de reclamation");
            alertBox.show();
        }
        else if (message.isEmpty())
        {
            alertBox.setHeaderText("Veuillez saisir le motif de votre reclamation");
            alertBox.show();
        }
        else if (membre.isSelected() && lstMembres.getSelectionModel().getSelectedItem() == null)
        {
            alertBox.setHeaderText("Veuillez Selectionnez un membre");
            alertBox.show();
        }
        else
        {
            setNotifcaion();
            
            if (site.isSelected())
                sujet = "Application";
            else if (membre.isSelected())
            {
                sujet = "membre";
            }
            
            Date d = new Date() ;
            
            reclamations r1 = new reclamations(User.getIdofconnecteduser(), sujet, d, message, reponse);
            
            if (membre.isSelected())
            {
                String member = lstMembres.getSelectionModel().getSelectedItem();
                r1.getTargetMember().setId(Integer.valueOf(member.substring(0, member.indexOf(' '))));
            }
            
            IService vs = new ReclamationService();
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Reclamation ajouter avec succes");
        alert.showAndWait();
            vs.add(r1);
           
//             Navigator.LoadScene(Navigator.ListeReclamation);
        }
    }

    private void setNotifcaion() {
        String title = "Ajout Reussi";
        String message = "Ajouté avec Succes";
        
        TrayNotification tray = new TrayNotification();
        
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setAnimationType(AnimationType.FADE);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
    }
    
}
