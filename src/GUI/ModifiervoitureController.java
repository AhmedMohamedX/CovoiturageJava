/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.NavigatorData;
import Models.voiture;
import Services.VoitureService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author fenina
 */
public class ModifiervoitureController implements Initializable {

    @FXML
    private Rectangle pane;
    @FXML
    private JFXComboBox<String> nbp;
    @FXML
    private JFXTextField marque;
    @FXML
    private Label idvoiture;
    @FXML
    private JFXTextField couleur;
    @FXML
    private JFXComboBox<String> categorie;
    @FXML
    private JFXComboBox<String> confort;
    @FXML
    private JFXButton modifier11;
    @FXML
    private JFXDatePicker date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               marque.getText();
          couleur.getText();
          nbp.getItems().addAll("1");
          nbp.getItems().addAll("2");
          nbp.getItems().addAll("3");
          nbp.getItems().addAll("4");
          
          confort.getItems().addAll("peu comfortable");
          confort.getItems().addAll("comfortable");
          confort.getItems().addAll("Trés comfortable");
          categorie.getItems().addAll("Mini");
          categorie.getItems().addAll("Familiale");
          categorie.getItems().addAll("Luxe & Sport");
              VoitureService vs = new VoitureService();
                voiture v= NavigatorData.getInstance().getSelectedvoiture();
                nbp.setValue(String.valueOf(v.getNb_places()));
                            marque.setText(v.getMarque());
                            couleur.setText(v.getCouleur());
                            confort.setValue(v.getComfort());
                            categorie.setValue(v.getCategorie());

    }    

    @FXML
    private void Modifier1(ActionEvent event) throws IOException, ParseException {
           if(date.getValue().compareTo(LocalDate.now()) <0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Vous devez choisir une date supérieure ou égale à la date d'aujoud'hui");
        alert.showAndWait();
        


        }
                  VoitureService vs = new VoitureService();
                 int nb = Integer.parseInt(nbp.getSelectionModel().getSelectedItem());
       String s = date.getValue().toString();
         Date d = null;

          //  Date d1 = new Date();
                d = new SimpleDateFormat("yyyy-MM-dd").parse(s);
    java.util.Date dd = java.sql.Date.valueOf(date.getValue());
       voiture v=NavigatorData.getInstance().getSelectedvoiture();
      
      v.setMarque(marque.getText()); 
      v.setCouleur(couleur.getText());
      v.setCategorie(categorie.getSelectionModel().getSelectedItem());
      v.setComfort(confort.getSelectionModel().getSelectedItem());
      v.setNb_places(Integer.parseInt(nbp.getSelectionModel().getSelectedItem()));
      v.setVisite(dd);

        System.out.print(v);
       
       vs.modifier(v);
   Navigator.LoadScene(Navigator.listeVoiture);
    }
}
