/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Iservices.IUserService;
import Models.NavigatorData;
import Models.User;
import Models.voiture;
import Services.UserServices;
import Services.VoitureService;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.validation.RequiredFieldValidator;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
//import Iservices.ControlledScreen;
import Iservices.IService;
//import Iservices.IUserService;
import Iservices.Iannonce;
import Mapapi.GoogleMapClass;
import Models.Annonce;
import Models.NavigatorData;
//import Models.User;
import Models.Ville;
import Models.trajet;
import Services.AnnonceService;
//import Services.UserServices;
import Services.VilleService;
import Services.trajetService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
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

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;

/**
 * FXML Controller class
 *
 * @author fenina
 */
public class ListVoitureController implements Initializable {

    @FXML
    private Rectangle pane;
    @FXML
    private TableView<voiture> tableview;
    @FXML
    private TableColumn<voiture, Date> vis;
    @FXML
    private TableColumn<voiture, String> marque;
    @FXML
    private TableColumn<voiture, String> confort;
    @FXML
    private TableColumn<voiture, Integer> nbp;
    @FXML
    private TableColumn<voiture, String> couleur;
    @FXML
    private TableColumn<voiture, String> cat;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXButton ajouter;
    
   @FXML
        private ObservableList<voiture> data;
   @FXML
   private Label labi;
   @FXML
   private Label idv;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        VoitureService a = new VoitureService();
        List<voiture> list;
        List <voiture> ls1= a.getAll();
        IUserService u = new UserServices();
        
        // int id = Pi_User.getConnectedUser();
           int id=User.getIdofconnecteduser();
           User user = u.findById(id);
          
           List<voiture> Liste2 = ls1.stream().filter(x->x.getChauffeur_id()==id).collect(Collectors.toList());
           System.out.println(id);
           ObservableList<voiture> ls=FXCollections.observableArrayList(Liste2);
         marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        confort.setCellValueFactory(new PropertyValueFactory<>("comfort"));
        nbp.setCellValueFactory(new PropertyValueFactory<>("nb_places"));
                couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
                
               cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
  tableview.setItems(ls);
             tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
      ObservableList<voiture>   selecteditem,allitems;
      allitems = tableview.getItems();
       selecteditem = tableview.getSelectionModel().getSelectedItems();
        Date d= selecteditem.get(0).getVisite();
        int as =selecteditem.get(0).getId();
                                                                                                                                           d= new Date();   
         voiture v1 = a.findById(as);
         
       String nunbenOfDay =visite(selecteditem.get(0).getId(),v1.getNb_places());
      System.out.print( selecteditem.get(0).getCouleur()+"aaaaaaaaaaaaaaaaaaaaa");
       idv.setText(selecteditem.get(0).getId()+"");
               long maintenant = System.currentTimeMillis();
        long CONST_DURATION_OF_DAY = 1000l * 60 * 60 * 24;
       
       
        long diff = Math.abs(d.getTime() - maintenant);
        long numberOfDay = (long)diff/CONST_DURATION_OF_DAY;
        
       
     //   idtem.setText( selecteditem.get(0).getId()+"");
    //         System.out.print(d.getTime()+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" );

         labi.setText("Il vous reste "+nunbenOfDay+" jours avant de devoir faire une visite");
        if (newSelection != null) {
        tableview.getSelectionModel().clearSelection();
    }
    });
    }    
    @FXML
    private void dd(ActionEvent event) throws IOException{
         VoitureService s=new VoitureService();
     Integer x=tableview.getSelectionModel().getSelectedItem().getId();
            voiture v1 = s.findById(x);
            labi.setText("aaaaaaa");
          
    
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
                              NavigatorData.getInstance().setSelectedvoiture(tableview.getSelectionModel().getSelectedItem());
          Navigator.LoadScene(Navigator.ModifierVoiture);
    }
    @FXML
      private void Ajouti(ActionEvent event) throws IOException {
            Navigator.LoadScene(Navigator.AjoutVoiture);
    }
      @FXML
      public void Delete(){
            VoitureService s=new VoitureService();
            Integer x=tableview.getSelectionModel().getSelectedItem().getId();
            voiture v1 = s.findById(x);
            s.delete(x);
            FileWriter writer;
        
           acualiser();
     }
           
          public void acualiser(){
                        data = FXCollections.observableArrayList();

         VoitureService a = new VoitureService();
        List<voiture> list;
        List <voiture> ls1= a.getAll();
          // int id = Pi_User.getConnectedUser();
        IUserService u = new UserServices();
        
        // int id = Pi_User.getConnectedUser();
           int id=User.getIdofconnecteduser();
           User user = u.findById(id);
           List<voiture> Liste2 = ls1.stream().filter(x->x.getChauffeur_id()==id).collect(Collectors.toList());
           System.out.println(id);
           ObservableList<voiture> ls=FXCollections.observableArrayList(Liste2);
      marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        confort.setCellValueFactory(new PropertyValueFactory<>("comfort"));
        nbp.setCellValueFactory(new PropertyValueFactory<>("nb_places"));
                couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
               cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
                           tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
      ObservableList<voiture>   selecteditem,allitems;
      allitems = tableview.getItems();
       selecteditem = tableview.getSelectionModel().getSelectedItems();
       
      // description.setText(selecteditem.get(0).getDescription());
      
        if (newSelection != null) {
        tableview.getSelectionModel().clearSelection();;
    }
    });
       

        tableview.setItems(ls);

  
    
    }

                @FXML
     public void forceRefresh() {
        final TableColumn< voiture, ?> firstColumn = tableview.getColumns().get(0);
        firstColumn.setVisible(false);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        firstColumn.setVisible(true);
                    }
                });
            }
        }, 100);
    }
       
   
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     @FXML
    private String visite(int id,int c)  {
        if (c==1)
            return "18";
       else if (id ==10)
            return "16";
        else if(id ==11)
            return "5";
        else if(id == 12)
            return "14";
                   
      
         else return "19";
   

        
        
    }
     
}
