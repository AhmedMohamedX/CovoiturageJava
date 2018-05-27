/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Models.NavigatorData;
import Models.reclamations;
import Services.ReclamationService;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
/**
 * FXML Controller class
 *
 * @author Mehdi9951
 */
public class RepondreFXMLController implements Initializable {

    @FXML
    private JFXButton Repondre;
    @FXML
    private JFXTextArea textrepondre;
    ReclamationService recc = new ReclamationService();
    ValidationSupport validationSupport = new ValidationSupport();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       NavigatorData.getInstance().getSelectedReclamation();
                  Repondre.getStyleClass().add("button-raised");
    }    

    @FXML
    private void RepondreNav(ActionEvent event) throws IOException {        
        String textrepond = textrepondre.getText();
        if (textrepond.isEmpty()) {
                        validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(textrepondre, "required name"));
        }else
        {
                        validationSupport.getValidationDecorator().removeDecorations(textrepondre);
            reclamations r = NavigatorData.getInstance().getSelectedReclamation();
              System.out.println(NavigatorData.getInstance().getSelectedReclamation());
         
            r.setReponse(textrepond);
            recc.modifier(r);
            
            
         
       Parent parent125 = FXMLLoader.load(getClass().getResource("/GUI/ListReclamationAdmin.fxml"));
       Scene scene1116 = new Scene(parent125 );
       Stage stage1116  = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage1116 .hide();
       stage1116 .setScene(scene1116 );
       stage1116.show();
        }
      
    }
    
}
