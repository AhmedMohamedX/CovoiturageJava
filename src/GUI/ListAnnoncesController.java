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
import Services.VilleService;
import Services.trajetService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author Mortadhafff
 */
public class ListAnnoncesController implements Initializable {

    @FXML
    private JFXTextField search;
    @FXML
    private JFXListView<HBox> list;
    private ObservableList<Annonce> annoncesl = FXCollections.observableArrayList();
//    FilteredList<Question> filtredQuestions = new FilteredList(question, predicate -> true);
    JFXButton b = new JFXButton();
     Iannonce q = new AnnonceService();   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        chargelistview();
    
    }

    private void chargelistview() {  
  
            if(q.selectByUser(User.getIdofconnecteduser()).isEmpty()){
                
                
                   HBox h = new HBox();
            h.setSpacing(50);
            //grid1
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(4);
            grid.setPadding(new Insets(0, 10, 0, 10));                    
            //label datep
            Label datep = new Label("                                                                                                         Vous n'avez pas d'annonces");
            datep.getStyleClass().add("title");
            grid.add(datep, 0, 1);
             h.getChildren().addAll(grid);
            list.getItems().add(h);
            list.setExpanded(true);
            list.depthProperty().set(1);   
            list.setCellFactory(AnnoncesListView -> new JFXListCell());  
            }else{
        for (Annonce que : q.selectByUser(User.getIdofconnecteduser())) {
   
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
        list.getItems().removeAll(list.getItems());
        if(search.getText().isEmpty()){
            chargelistview();
        }else{
        String s = search.getText();
        double s2 = Double.parseDouble(s);
        list.getItems().removeAll(list.getItems());
        for (Annonce qu : q.findByPrix(s2,User.getIdofconnecteduser() )) {
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
        }
    }
    @FXML
    private void getAnnonce(MouseEvent event) {
        GridPane g = ((GridPane) list.getSelectionModel().getSelectedItem().getChildren().get(0));
      Label l = (Label) g.getChildren().get(2); 
        NavigatorData.getInstance().setSelectedAnnonce(q.selectOne(Integer.parseInt(l.getText())));
        Navigator.LoadScene(Navigator.AfficherAnnonce);

    }   
  
}
