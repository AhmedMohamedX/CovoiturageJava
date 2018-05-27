/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.User;
import Services.UserServices;
import Iservices.IUserService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author adel
 */
public class FXMLUpdateProfileController implements Initializable {

    @FXML
    private JFXComboBox<String> animaux;

    @FXML
    private JFXComboBox<String> tabagisme;

    @FXML
    private JFXComboBox<String> musique;

    @FXML
    private JFXComboBox<String> sexe;
     
    @FXML
    private JFXTextField imagepath;
    @FXML
    private JFXTextField telephone;

    @FXML
    private JFXTextField nom;
  @FXML
    private JFXTextField photoprofilimagepath;
    @FXML
    private JFXTextField prenom;
    private static Path destination;
    private static File selectedfile;
    private static Path destination1;
    private static File selectedfile1;
   @FXML
    void ajouterphoto(ActionEvent event) {
FileChooser fc = new FileChooser();
       selectedfile = fc.showOpenDialog(null);
        
        destination = Paths.get("C:\\wamp\\www\\covoiturage-EDT-ONE\\web\\photo_membre",selectedfile.getName());
    imagepath.setText(selectedfile.getName());
    
    }
    @FXML
    void ajouterphotoprofil(ActionEvent event) {
FileChooser fc = new FileChooser();
       selectedfile1 = fc.showOpenDialog(null);
        
     destination1 = Paths.get("C:\\wamp\\www\\covoiturage-EDT-ONE\\web\\photo_membre",selectedfile1.getName());
    photoprofilimagepath.setText(selectedfile1.getName());
    }
    
    
    
    @FXML
    void UpdateProfile(ActionEvent event) throws IOException {
        IUserService userservice = new UserServices();
        int ani;
        int tab ; 
        if(tabagisme.getValue().equals("fumeur")){
            tab=1;
        }else
        {
            tab=0;
        }
        if( animaux.getValue().equals("accepte")){
            ani=1;
        }else
        {
            ani=0;
        }
      
        User user = new User(nom.getText(), prenom.getText(), musique.getValue(),tab, sexe.getValue(),ani,Integer.parseInt(telephone.getText()));
          if(selectedfile==null ||selectedfile1==null){
        user.setPermisConduire(null);
        user.setPhotoprofilpath(null);
        }else{
            Files.copy(selectedfile.toPath(),destination,StandardCopyOption.REPLACE_EXISTING); 
             user.setPermisConduire("photo_membre/"+selectedfile.getName());
             Files.copy(selectedfile1.toPath(),destination1,StandardCopyOption.REPLACE_EXISTING); 
             user.setPhotoprofilpath("photo_membre/"+selectedfile1.getName());       }
        
        user.setId(User.getIdofconnecteduser());
        userservice.update(user);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     tabagisme.getItems().addAll("fumeur","non fumeur");
     musique.getItems().addAll("Rock","POP","Blues","JAZZ","RAP");
     sexe.getItems().addAll("Homme","Femme");
     animaux.getItems().addAll("accepte","Refuse");
    }    
    
}
