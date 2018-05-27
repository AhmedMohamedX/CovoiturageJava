/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Iservices.ITemoignage;
import Models.User;
import Services.UserServices;
import Iservices.IUserService;
import Models.temoignage;
import Services.TemoignageService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class AcceuilController implements Initializable {
 

  


    @FXML
    private JFXComboBox<String> dropdown;
      
  
 
    @FXML
    private ScrollPane container;

        @FXML
    private ImageView Photoprofil;
   
        
    @FXML

    private Label NomUtil;
    @FXML

    private Label jetons;
     @FXML

    private Label jet;
    UserServices userv = new UserServices();
   /*
     void ajouterTemoignage(ActionEvent event) throws IOException {
    
    }

    @FXML
    void voirtemoignage(ActionEvent event) throws IOException {

    }
    */
    @FXML
    private ScrollPane temoignage;
    
  @FXML
    void handlebutton(ActionEvent event) throws IOException {
if(dropdown.getValue().equals("ajouter Temoignage")){
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAjouterTemoignage.fxml"));
    container.setContent(pane);
}else if(dropdown.getValue().equals("Voir Temoignage")){
       AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLUpdateDeleteTemoignage.fxml"));
    container.setContent(pane);   
    }
    }
     @FXML
    void modifierprofil(ActionEvent event) throws IOException {
    AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLUpdateProfile.fxml"));
    container.setContent(pane); 
    }

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        File file=null ;
         int id = User.getIdofconnecteduser();
        IUserService userservice = new UserServices();
       User user =  userservice.FindById(id);
       jetons.setText(String.valueOf(user.getJetons()));
       String prenom  = user.getPrenom();

             file = new File("/C:/wamp/www/covoiturage-EDT-ONE/web/"+user.getPhotoprofilpath()); 
       
     
        Image image = new Image(file.toURI().toString());
      
         Photoprofil.setImage(image);
    
      
        NomUtil.setText(user.getNom());
       AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/Autocompletegoogleplaces.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    container.setContent(pane);
    
 
  
      
        
    
      
        
        dropdown.getItems().addAll(
        "ajouter Temoignage",
        "Voir Temoignage"
      
        );
                 ITemoignage temservices = new TemoignageService();
         List<temoignage> listetem = temservices.selectAll();
   
       ImageView imagev;
       
      HBox vb = new HBox();
      
      vb.setSpacing(30);
      int i =0;
          for(temoignage p :listetem){
          HBox vbox = new HBox();
               System.out.println(p.getUser().getPhotoprofilpath());
             
                    
           
               
               String url0 ="file:/C:/wamp/www/covoiturage-EDT-ONE/web/"+p.getUser().getPhotoprofilpath();
               System.out.println(url0);
               ImageView connect = new ImageView(url0);
               connect.setFitWidth(100);
               connect.setFitHeight(100);
               connect.getStyleClass().add("imageProfil");
               Label l1=new Label(p.getMessage());
               Label l2=new Label(""+p.getDate_tem());
              
               VBox vv = new VBox();
              Label vide1 = new Label("");
               Label vide2 = new Label(""); 
              Label vide = new Label("");
              vv.getChildren().addAll(vide1,vide2,l2,l1);
               
               vbox.getChildren().addAll(connect,vv);
               
               
               
              vb.getChildren().addAll(vbox);
     
    }    
    temoignage.setContent(vb);
;
    
}
    @FXML
     void logout(ActionEvent event) throws IOException {
         userv.setNotConnected(User.getIdofconnecteduser());
     Navigator.LoadScene(Navigator.authentification);
     }

    @FXML
    private void recharge(MouseEvent event) {
        User uu = userv.FindById(User.getIdofconnecteduser());
        userv.GetPayer(User.getIdofconnecteduser(),uu.getJetons()+100);
        Navigator.LoadScene(Navigator.recharge);
    }

    @FXML
    private void voitures(ActionEvent event) throws IOException {
         Navigator.LoadScene(Navigator.listeVoiture);
    }

    @FXML
    private void reservations(ActionEvent event) {
        Navigator.LoadScene(Navigator.listRes);
    }

    @FXML
    private void rec(ActionEvent event) {
        Navigator.LoadScene(Navigator.listrec);
    }
  
    
    
}