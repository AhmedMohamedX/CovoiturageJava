/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Iservices.Iannonce;
import Models.Annonce;
import Models.NavigatorData;
import Models.Ville;
import Models.trajet;
import Services.AnnonceService;
import Services.ReservationsService;
import Services.VilleService;
import Services.trajetService;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
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
public class AfficherAnnonceController implements Initializable {
    
    @FXML
    private ImageView partager;
    @FXML
    private Label title;
    
    @FXML
    private Label dated;
    @FXML
    private Label datep;
    @FXML
    private Label prixx;
    @FXML
    private Label devia;
        @FXML
    private Label nbrplaces;
                @FXML
    private Label comm;
   
    Integer ti =NavigatorData.getInstance().getSelectedAnnonce().getTrajet_id().getId();
    Date dd = NavigatorData.getInstance().getSelectedAnnonce().getDate_depart();
    Date dp = NavigatorData.getInstance().getSelectedAnnonce().getDate();
    String comentaire = NavigatorData.getInstance().getSelectedAnnonce().getCommentaire();
    double prix = NavigatorData.getInstance().getSelectedAnnonce().getPrix();
    String dev = NavigatorData.getInstance().getSelectedAnnonce().getDeviation();
    int nbrp= NavigatorData.getInstance().getSelectedAnnonce().getNb_places();

    @FXML
    private AnchorPane but;
    @FXML
    private JFXButton delete;
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
        
 
    
                trajetService ts = new trajetService();
           
           trajet traj = ts.FindById(ti);
            VilleService vs = new VilleService();
            int ivd = traj.getVille_dep();
            Ville vd = vs.findById(ivd);
            int iva = traj.getVille_arr();
            Ville va = vs.findById(iva);
        
        delete.setVisible(true);
        update.setVisible(true);    
        
        delete.getStyleClass().add("button-raised");
        update.getStyleClass().add("button-raised");       
        title.setText(vd.getNom()+" -> "+va.getNom());
        dated.setText(dd.toString());
        datep.setText(dp.toString());    
        prixx.setText(String.valueOf(prix)+" dt");
        String dev2="Moins de 15 minutes";
            
            if(dev.equals("1")){
                dev2="Moins de 15 minutes";
            }else if(dev.equals("2")){
                dev2="Entre 15-30 minutes";
            }else if(dev.equals("3")){
                dev2="Plus que 30 minutes";
            }
        devia.setText(dev2);
        nbrplaces.setText(String.valueOf(nbrp));
        comm.setText(comentaire);
    }
    @FXML
    private void deleteannonce(ActionEvent event) {
        Iannonce anService = new AnnonceService();
        Annonce quu = anService.selectOne(NavigatorData.getInstance().getSelectedAnnonce().getId());
        ReservationsService rs = new ReservationsService();
        int test = rs.FindByIdAnnonce(quu.getId()).getId();
      
        if(test != 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Vous ne pouver pas supprimer cette annonce puisque queleq'un à déjà reservé !");
        alert.showAndWait();
        }else{
                
        anService.deleteAnnonce(quu);
        Navigator.LoadScene(Navigator.ListAnnonces);
    }
    }

    @FXML
    private void updateannonce(ActionEvent event) {
       
        Iannonce anService = new AnnonceService();
        Annonce quu = anService.selectOne(NavigatorData.getInstance().getSelectedAnnonce().getId());
        long maintenant = System.currentTimeMillis();
        long CONST_DURATION_OF_DAY = 1000l * 60 * 60 * 24;
        long diff = Math.abs(quu.getDate_depart().getTime() - maintenant);
		long numberOfDay = (long)diff/CONST_DURATION_OF_DAY; 
                
        if( numberOfDay > 3){
            NavigatorData.getInstance().setSelectedAnnonce(quu);
        Navigator.LoadScene(Navigator.modifierannonce);
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Vous ne pouver pas modifier cette annonce car la date de départ est inférieur à trois jours ou elle est ancienne");
        alert.showAndWait();
         }
        
    }
@FXML
    private void partager(ActionEvent event) {
        String dev2="Moins de 15 minutes";
            
            if(dev.equals("1")){
                dev2="Moins de 15 minutes";
            }else if(dev.equals("2")){
                dev2="Entre 15-30 minutes";
            }else if(dev.equals("3")){
                dev2="Plus que 30 minutes";
            }
               String accessToken = "EAACEdEose0cBANvvrQPShAfSZBlUXcjmaS5pQxHvkFnGdzXkZANg0QUw04flLrsL1QrWZBd9B84iRTdfT19AbKnnoXPwJZA3JNE5g9eJkha82I1trHrhNvZBZBoATXBJBNFRg9RgHMJOO6mZB4Fo1QVowIivbMu5sKq5xbjTHLzfoQTZBLJAn35D7z6vy0q9lWUZD";
        Scanner s = new Scanner(System.in);
        FacebookClient fbClient= new DefaultFacebookClient(accessToken);
         FacebookType response = fbClient.publish("me/feed", FacebookType.class,
                           Parameter.with("message", "Annonce publié le "+dp.toString()+" pour un départ le "
                           +dd+" avec un prix de "+prix+" dt/personne, pour "+nbrp+" places, et une déviation de "+dev2
                           +" ,voici le commentaire d'autheur: "+comentaire),
                           Parameter.with("link", "http://127.168.0.1/covoiturage-EDT-ONE/web/app_dev.php/accueil"));
         System.out.println("fb.com/"+response.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("success");
        alert.setContentText("Votre annonce à été publié");
        alert.showAndWait();
    }
    
}
