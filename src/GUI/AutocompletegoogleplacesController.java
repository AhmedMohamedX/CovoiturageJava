/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.NavigatorData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import static java.util.Date.from;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.TextFields;




/**
 * FXML Controller class
 *
 * @author adel
 */
public class AutocompletegoogleplacesController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton rech;
    @FXML
    private JFXDatePicker dated;
    @FXML
    private JFXTextField fromTextField;

    @FXML
    private JFXTextField toTextField;
    @FXML
    private Label loc1a;
    @FXML
    private GoogleMapView mapView;
    
      protected DirectionsService directionsService;

    protected DirectionsPane directionsPane;
    @FXML
    private Label loc1l;

    @FXML
    private Label loc2l;
    
    @FXML
    private Label loc2a;
 
   private GeocodingService geocodingService;
    GeocodingResult[] georesults;
    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
         List<String> l = new ArrayList();
           List<String> l1 = new ArrayList();
String[] s;
String[] s1;
         




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



        

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dated.setValue(LocalDate.now());
        mapView.addMapInializedListener(this);
    }    

    @Override
    public void mapInitialized() {
geocodingService = new GeocodingService();
   
    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {

 DirectionsResult e = dr;
        try {
            

            System.out.println("Distance total = " + e.getRoutes().get(0).getLegs().get(0).getDistance().getText());
        } catch (Exception ex) {
            System.out.println("ERRO: " + ex.getMessage());
        }
    }

    @FXML
    private void OnTapEvent(ActionEvent event) {
        if(fromTextField.getText().isEmpty() || toTextField.getText().isEmpty() ){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Vous devez remplir tout les champs");
        alert.showAndWait();
        }else if(dated.getValue().compareTo(LocalDate.now()) <0){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Vous devez choisir une date supérieure ou égale à la date d'aujoud'hui");
        alert.showAndWait();
            
        }
        else{
            NavigatorData.getInstance().setVd(fromTextField.getText());
            NavigatorData.getInstance().setVa(toTextField.getText());
            NavigatorData.getInstance().setD(dated.getValue());
            Navigator.LoadScene(Navigator.recherche);
        }
    }
    
    
}
