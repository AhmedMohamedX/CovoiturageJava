/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;




import Models.User;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mortadhafff
 */
public class MainFXMLController implements Initializable {

    @FXML
    private AnchorPane child;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger ham;
    @FXML
    private Label person;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        uu.add(u);
        
           
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/GUI/SideBar.fxml"));
            drawer.setSidePane(box);
//            NavigatorData.getInstance().VBox = box;
            for (Node node : box.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, e
                            -> {
                        switch (node.getAccessibleText()) {
                            case "pro":
                                if(User.getIdofconnecteduser()==0){
                                 Alert alert = new Alert(Alert.AlertType.ERROR);
                                 alert.setHeaderText(null);
                                 alert.setContentText("Vous ne pouver accéder à cette page !");
                                 alert.showAndWait();
                                 break;
                                }else{
                                Navigator.LoadScene(Navigator.ajouterannonce);
                                break;
                                }
                            case "mes":
                                if(User.getIdofconnecteduser()==0){
                                 Alert alert = new Alert(Alert.AlertType.ERROR);
                                 alert.setHeaderText(null);
                                 alert.setContentText("Vous ne pouver accéder à cette page !");
                                 alert.showAndWait();
                                 break;
                                }else{
                               Navigator.LoadScene(Navigator.ListAnnonces);
                               break;
                                }
                            case "chat":
                                if(User.getIdofconnecteduser()==0){
                                 Alert alert = new Alert(Alert.AlertType.ERROR);
                                 alert.setHeaderText(null);
                                 alert.setContentText("Vous ne pouver accéder à cette page !");
                                 alert.showAndWait();
                                 break;
                                }else{
                               Navigator.LoadScene(Navigator.chat);
                               break;
                                }
                            case "versA":
                                 if(User.getIdofconnecteduser()==0){
                                 Alert alert = new Alert(Alert.AlertType.ERROR);
                                 alert.setHeaderText(null);
                                 alert.setContentText("Vous ne pouver accéder à cette page !");
                                 alert.showAndWait();
                                 break;
                                }else{
                                Navigator.LoadScene(Navigator.accueil);
                                break;
                                 }
                                  case "for":
                                 if(User.getIdofconnecteduser()==0){
                                 Alert alert = new Alert(Alert.AlertType.ERROR);
                                 alert.setHeaderText(null);
                                 alert.setContentText("Vous ne pouver accéder à cette page !");
                                 alert.showAndWait();
                                 break;
                                }else{
                                Navigator.LoadScene(Navigator.last);
                                break;
                                 }
                            case "gro":

                                Navigator.LoadScene(Navigator.Email);
                                break;
                                 
                            case "exi":
                                System.exit(0);
                        }
                    });
                }
            }
            HamburgerSlideCloseTransition burgerTask = new HamburgerSlideCloseTransition(ham);
            burgerTask.setRate(-1);
            ham.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burgerTask.setRate(burgerTask.getRate() * -1);
                burgerTask.play();
                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setAPance(Node a) {
        child.getChildren().setAll(a);
    }

    public void LoadWindow(String loc, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Navigator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
