/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import Iservices.ControlledScreen;
import Iservices.IService;
import Iservices.IUserService;
import Iservices.Iannonce;
import Mapapi.GoogleMapClass;
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
 * @author khmai
 */
public class AjouterAnnoncesController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback, ControlledScreen {

    private GoogleMap map;

    private GeocodingService geocodingService;

    ScreensController screen;

    private MarkerOptions markerOptions;

    protected DirectionsService directionsService;

    protected DirectionsPane directionsPane;

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
    @FXML
    private Label loc1l;

    @FXML
    private Label loc2l;
    @FXML
    private Label loc1a;
    @FXML
    private Label loc2a;
    @FXML
    private JFXComboBox<String> combos ;
    
    @FXML
    private JFXTextArea commentaire;
    
  GeocodingResult[] georesults;
   
         List<String> l = new ArrayList();
           List<String> l1 = new ArrayList();
String[] s;
String[] s1;
    @FXML
    private JFXDatePicker dateAnnoncePicker;

    @FXML
    private JFXDatePicker timeAnnoncePicker;

    @FXML
    private DatePicker test;

    @FXML
    private AnchorPane root;

    @FXML
    private GoogleMapView mapview;

    @FXML
    private GoogleMapView mapView;

    @FXML
    private JFXTextField fromTextField;

    @FXML
    private JFXTextField toTextField;

    @FXML
    JFXTextField prixTextField;

    @FXML
    JFXTextField numberTextField;

    @FXML
    private JFXSlider numberSlider;

    @FXML
    private JFXSlider prixSlider;

    @FXML
    private JFXSnackbar snackbar;
    
    @FXML 
    private JFXButton button;

    public static AnchorPane rootP;
    
    ValidationSupport vs = new ValidationSupport();
    VilleService vser = new VilleService();
    trajetService tser = new trajetService();
    
    @FXML
    private void toTextFieldAction(ActionEvent event) {

        // System.out.println("to text field action "+event.toString());
        DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));

        //GoogleMapClass.getInstance().configDirections(from.get(), to.get(), mapView, directionsPane);
    }

    @FXML
    private void submitAnnonceButtonAction(ActionEvent event) {
        if(fromTextField.getText().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Vous devez choisir une ville de départ");
        alert.showAndWait();
        }
        else if(toTextField.getText().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Vous devez choisir une ville d'arrivée");
        alert.showAndWait();
        }
        else if(dateAnnoncePicker.getValue().compareTo(LocalDate.now()) <0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Vous devez choisir une date supérieure ou égale à la date d'aujoud'hui");
        alert.showAndWait();
        


        } else {
            String s = dateAnnoncePicker.getValue().toString() + " " + timeAnnoncePicker.getTime().toString();
            String from = fromTextField.getText();
            String to = toTextField.getText();
            double prix = (float) prixSlider.getValue();
            int number = (int) numberSlider.getValue();
              String combo1 = combos.getSelectionModel().getSelectedItem().toString();
              String combo2="0";
              
           if(combo1 == "Moins de 15 minutes"){
               combo2="1";
            }
           if(combo1 == "Entre 15-30 minutes"){
               combo2="2";
            }
           if(combo1 == "Plus de 30 minutes"){
               combo2="3";
            }
            
            Date d = null;

            Date d1 = new Date();
            String com = commentaire.getText();
            try {
                d = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(s);

            } catch (ParseException ex) {
                Logger.getLogger(AjouterAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            int id_user=User.getIdofconnecteduser();
            IUserService userservice = new UserServices();
            User us = userservice.FindById(id_user);
            int prix1 = (int)prix;
            Ville vvd = new Ville(fromTextField.getText(),loc1l.getText() , loc1a.getText());
            Ville vva = new Ville(toTextField.getText(),loc2l.getText() , loc2a.getText());
            vser.add(vvd);
            vser.add(vva);
            Ville vvd1 = vser.FindByName(fromTextField.getText());
            Ville vva1 = vser.FindByName(toTextField.getText());
            trajet tt = new trajet(vvd1.getId(), vva1.getId(), 0, "");
            tser.add(tt);
            
            trajet tt1 = tser.FindByIdV(vva1.getId());
            
            Annonce an = new Annonce(tt1,us,d1,prix1,combo2,d,com,number);
            
            an.setHeure_depart(timeAnnoncePicker.getTime().toString());
            Iannonce service = new AnnonceService();
            service.insertAnnonce(an);
            
            System.out.println("to text field action " + event.toString());

            System.out.println("date and time " + d);
            Navigator.LoadScene(Navigator.ListAnnonces);
        }
         
    }

    /*  public Date dateTime(Date date, Date time) {

    Calendar aDate = Calendar.getInstance();
    aDate.setTime(date);

    Calendar aTime = Calendar.getInstance();
    aTime.setTime(time);

    Calendar aDateTime = Calendar.getInstance();
    aDateTime.set(Calendar.DAY_OF_MONTH, aDate.get(Calendar.DAY_OF_MONTH));
    aDateTime.set(Calendar.MONTH, aDate.get(Calendar.MONTH));
    aDateTime.set(Calendar.YEAR, aDate.get(Calendar.YEAR));
    aDateTime.set(Calendar.HOUR, aTime.get(Calendar.HOUR));
    aDateTime.set(Calendar.MINUTE, aTime.get(Calendar.MINUTE));

    return aDateTime.getTime();
}   */
    public void setDirection() {
        DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));

    }

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
    }
    

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mapView.addMapInializedListener(this);
        ObservableList<String> competence = FXCollections.observableArrayList();
        List<String> a = new ArrayList<>();
        a.add("Moins de 15 minutes");
        a.add("Entre 15-30 minutes");
        a.add("Plus de 30 minutes");
        competence.addAll(a);
        combos.setItems(competence);
        combos.setSelectionModel(combos.getSelectionModel());
        rootP = root;
        snackbar.registerSnackbarContainer(root);

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("ce champ est obligatoire");
        toTextField.getValidators().add(validator);
        fromTextField.getValidators().add(validator);
        combos.setValue("Moins de 15 minutes");
        dateAnnoncePicker.setValue(LocalDate.now());
        timeAnnoncePicker.setTime(LocalTime.now());
        

        mapView.addMapInializedListener(this);

        to.bindBidirectional(toTextField.textProperty());
        from.bindBidirectional(fromTextField.textProperty());

        toTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    System.out.println("Textfield on focus");
                } else {
                    System.out.println("Textfield out focus");
                    if (toTextField.getText() != "") {
                        setDirection();
                    }
                    toTextField.validate();
                }
            }
        });
        fromTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    fromTextField.validate();
                }
            }
        });

       
    }

    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        MapOptions options = new MapOptions();

        options.center(new LatLong(34.3055732, 11.255412))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();

    }

    /*    @FXML
    public void addressTextFieldAction(ActionEvent event) {
        geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {
            
            LatLong latLong = null;

            
            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
               
AddMarker(latLong,address.get());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                AddMarker(latLong,address.get());
            }
            
            map.setCenter(latLong);

        });
    }*/
    private void AddMarker(LatLong l, String address) {

        Marker myMarker = null;
        markerOptions = new MarkerOptions();
        markerOptions.position(l)
                .title("My new Marker")
                .visible(true);

        myMarker = new Marker(markerOptions);
        InfoWindowOptions infoOptions = new InfoWindowOptions();
        infoOptions.content(address)
                .position(l);

        InfoWindow window = new InfoWindow(infoOptions);
        window.open(map, myMarker);
        map.addMarker(myMarker);

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screen = screenPage;
    }

    
    
    
     
  
         




    @FXML
    void fromOnkeyTypedEvent(KeyEvent event) {


   geocodingService.geocode(fromTextField.getText(), (GeocodingResult[] results, GeocoderStatus status) -> {
        l.clear();
        //int i;                 
  for(int i =0;i<results.length;i++){
        s=new String[results.length];
      s[i] = results[i].getFormattedAddress();
    
         l.add(results[i].getFormattedAddress());
        
         
  }
     
            
       for (GeocodingResult result : results) {
           
       
              TextFields.bindAutoCompletion(fromTextField, s);
              loc1a.setText(result.getGeometry().getLocation().getLatitude()+"");
              loc1l.setText(result.getGeometry().getLocation().getLongitude()+"");

            }
       
        TextFields.bindAutoCompletion(fromTextField, t-> {
 
            return l;
 
        });
       
          

        });
    }

    @FXML
    void toOnkeyTypedEvent(KeyEvent event) {
        
        geocodingService.geocode(toTextField.getText(), (GeocodingResult[] results, GeocoderStatus status) -> {
        l1.clear();
        //int i;                 
  for(int i =0;i<results.length;i++){
        s1=new String[results.length];
      s1[i] = results[i].getFormattedAddress();
    
         l1.add(results[i].getFormattedAddress());
         
  }
     
            
       for (GeocodingResult result : results) {
           
       
                              TextFields.bindAutoCompletion(toTextField, s1);
            loc2a.setText(result.getGeometry().getLocation().getLatitude()+"");
              loc2l.setText(result.getGeometry().getLocation().getLongitude()+"");


            }
       
        TextFields.bindAutoCompletion(toTextField, t-> {
 
            return l1;
 
        });
       
          

        });
        
        

    }



        

 
    


    
}
