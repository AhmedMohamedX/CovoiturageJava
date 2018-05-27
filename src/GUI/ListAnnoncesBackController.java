/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Iservices.Iannonce;
import Models.Annonce;
import Models.NavigatorData;
import Models.User;
import Models.Ville;
import Models.trajet;
import Services.AnnonceService;
import Services.UserServices;
import Services.VilleService;
import Services.trajetService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Mortadhafff
 */
public class ListAnnoncesBackController implements Initializable {

    @FXML
    private JFXTextField search;
    @FXML
    private JFXListView<HBox> list;
    private ObservableList<Annonce> annoncesl = FXCollections.observableArrayList();
//    FilteredList<Question> filtredQuestions = new FilteredList(question, predicate -> true);
    JFXButton b = new JFXButton();
     Iannonce q = new AnnonceService();   
    @FXML
    private Label deco;
    UserServices uu = new UserServices();
    List<String> UsersName = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       for (User user : uu.getAll()) {
            
                UsersName.add(user.getNom());
            
        }
       TextFields.bindAutoCompletion(search, UsersName);
        chargelistview();
    
    }

    private void chargelistview() {  
                  
        
            if(q.selectAll().isEmpty()){
                
                
                   HBox h = new HBox();
            h.setSpacing(50);
            //grid1
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(4);
            grid.setPadding(new Insets(0, 10, 0, 10));                    
            //label datep
            Label datep = new Label("                                                                                                          pas d'annonces");
            datep.getStyleClass().add("title");
            grid.add(datep, 0, 1);
             h.getChildren().addAll(grid);
            list.getItems().add(h);
            list.setExpanded(true);
            list.depthProperty().set(1);   
            list.setCellFactory(AnnoncesListView -> new JFXListCell());  
            }else{
        for (Annonce que : q.selectAll()) {
   
            //hbox
            HBox h = new HBox();
            h.setSpacing(50);
            //grid1
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(4);
            grid.setPadding(new Insets(0, 10, 0, 10));                    
            //label datep
            Label datep = new Label(String.valueOf(que.getDate_depart().toString())+"                                                                 ");
            datep.getStyleClass().add("title");
            grid.add(datep, 0, 1);
            //label vide
            Label vide = new Label("");
            vide.getStyleClass().add("title");
            grid.add(vide, 0, 2);
            //idAnnonce  
            Label id = new Label(String.valueOf(que.getId()));
            grid.add(id, 1, 1);
            id.setVisible(false);
            //label titre
            Label prix = new Label(String.valueOf(que.getPrix())+"                                                                 ");
            prix.getStyleClass().add("title");
            grid.add(prix, 1, 1);
            prix.setId("tt");
            //label date              
            Label nbrp = new Label(String.valueOf(que.getNb_places())+"                                                                 ");
            nbrp.getStyleClass().add("title");
            grid.add(nbrp, 2, 1);
            prix.setId("ff");
            //label déviation  
            String dev2="Moins de 15 minutes                                                                 ";
            String dev = que.getDeviation();
            if(dev.equals("1")){
                dev2="Moins de 15 minutes                                                                 ";
            }else if(dev.equals("2")){
                dev2="Entre 15-30 minutes                                                                 ";
            }else if(dev.equals("3")){
                dev2="Plus que 30 minutes                                                                 ";
            }
            Label dev1 = new Label(dev2);
            dev1.getStyleClass().add("title");
            grid.add(dev1, 3, 1);
            
            

            
            
            h.getChildren().addAll(grid);
            list.getItems().add(h);
            list.setExpanded(true);
            list.depthProperty().set(1);   
            list.setCellFactory(AnnoncesListView -> new JFXListCell());     
            
        }}
        
    }

    @FXML
    private void recherche(KeyEvent event) {
        String s = search.getText();
        User ls =uu.FindByLogin(s);
        if(search.getText().isEmpty()){
            list.getItems().removeAll(list.getItems());
            chargelistview();
        }else{
        if(ls == null){
            
        }else{
        
        
        list.getItems().removeAll(list.getItems());
        for (Annonce qu : q.findByLogin(s) ) {
            //hbox
         HBox h = new HBox();
            h.setSpacing(50);
            //grid1
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(4);
            grid.setPadding(new Insets(0, 10, 0, 10));                    
            //label datep
            Label datep = new Label(String.valueOf(qu.getDate().toString())+"                                                                 ");
            datep.getStyleClass().add("title");
            grid.add(datep, 0, 1);
            //label vide
            Label vide = new Label("");
            vide.getStyleClass().add("title");
            grid.add(vide, 0, 2);
            //idAnnonce  
            Label id = new Label(String.valueOf(qu.getId()));
            grid.add(id, 1, 1);
            id.setVisible(false);
            //label titre
            Label prix = new Label(String.valueOf(qu.getPrix())+"                                                                 ");
            prix.getStyleClass().add("title");
            grid.add(prix, 1, 1);
            prix.setId("tt");
            //label date              
            Label nbrp = new Label(String.valueOf(qu.getNb_places())+"                                                                 ");
            nbrp.getStyleClass().add("title");
            grid.add(nbrp, 2, 1);
            prix.setId("ff");
            //label déviation  
            String dev2="Moins de 15 minutes                                                                 ";
            String dev = qu.getDeviation();
            if(dev.equals("1")){
                dev2="Moins de 15 minutes                                                                 ";
            }else if(dev.equals("2")){
                dev2="Entre 15-30 minutes                                                                 ";
            }else if(dev.equals("3")){
                dev2="Plus que 30 minutes                                                                 ";
            }
            Label dev1 = new Label(dev2);
            dev1.getStyleClass().add("title");
            grid.add(dev1, 3, 1);         
            h.getChildren().addAll(grid);
            list.getItems().add(h);
        }
        list.refresh();
        }}
  
    }
    @FXML
    private void getAnnonce(MouseEvent event) {
        GridPane g = ((GridPane) list.getSelectionModel().getSelectedItem().getChildren().get(0));
      Label l = (Label) g.getChildren().get(2); 
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous supprimer cette annonce ?");
        alert.showAndWait();
        if(alert.getResult().getText().equals("OK")){
            Annonce aaa = q.selectOne(Integer.parseInt(l.getText()));
            q.deleteAnnonce(aaa);
            list.getItems().removeAll(list.getItems());
            chargelistview();
                   Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setHeaderText(null);
        alert1.setContentText("Supprimé avec succées");
        alert1.showAndWait();
        }
        
     

    }   

    @FXML
    private void deco(MouseEvent event) throws IOException{
             Parent parent125 = FXMLLoader.load(getClass().getResource("/GUI/Authentification.fxml"));
       Scene scene1116 = new Scene(parent125 );
       Stage stage1116  = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage1116 .hide();
       stage1116 .setScene(scene1116 );
       stage1116.show();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
           Parent parent125 = FXMLLoader.load(getClass().getResource("/GUI/FXMLBackOffice.fxml"));
       Scene scene1116 = new Scene(parent125 );
       Stage stage1116  = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage1116 .hide();
       stage1116 .setScene(scene1116 );
       stage1116.show();
    }
  
}
