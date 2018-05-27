/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.User;
import Models.voiture;
import Services.VoitureService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
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
import java.sql.Time;
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
import javax.print.attribute.standard.MediaSize;

/**
 * FXML Controller class
 *
 * @author fenina
 */
public class AjoutiniController implements Initializable {

   @FXML
    private JFXTextField marque;
    @FXML
    private JFXTextField couleur;
    @FXML
    private Rectangle pane;
    @FXML
    private JFXButton ajouter;
    @FXML
    private ComboBox<String> nbp;
    @FXML
    private Label alerteM;
    @FXML
    private Label alerteC;
    @FXML
    private Label alertN;
    @FXML
    private Label path;
    @FXML
    private ImageView img;
    @FXML
    private ComboBox<String> comfort;
    @FXML
    private JFXButton modifier1;
    @FXML
    private ComboBox<String> cat;
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
          comfort.getItems().addAll("peu comfortable");
          comfort.getItems().addAll("comfortable");
          comfort.getItems().addAll("trés comfortable");
          cat.getItems().addAll("Mini");
          cat.getItems().addAll("Familiale");
          cat.getItems().addAll("Luxe & Sport");
       
          
    }    

    @FXML
    private void Ajouter(ActionEvent event)throws IOException, ParseException {
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

         voiture v = new voiture(User.getIdofconnecteduser(), marque.getText(), comfort.getSelectionModel().getSelectedItem(),nb , couleur.getText(), cat.getSelectionModel().getSelectedItem(),d);
         vs.add(v);
          Navigator.LoadScene(Navigator.listeVoiture);
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException{
    
        
        Navigator.LoadScene(Navigator.listeVoiture);

    
    }
    }
    

