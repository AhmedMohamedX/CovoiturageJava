/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Iservices.IUserService;
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
import Technique.GeoLocation;
import Technique.ServerLocation;
import Technique.getIpAddress;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.controlsfx.control.Rating;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * FXML Controller class
 *
 * @author Mortadhafff
 */
public class DerniereAnnoncesController implements Initializable {


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
            getIpAddress gi = new getIpAddress();
            String myIp = gi.findIp();
            System.out.println(myIp);
            GeoLocation obj = new GeoLocation();
            ServerLocation location = obj.getLocation(myIp);
            
            String loc = location.getCity();
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String d1 = dateFormat.format(date);
            System.out.println(d1);
            
            Date date2 = new Date();
            
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR_OF_DAY, 3);
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String d2 = dateFormat1.format(cal.getTime());
            System.out.println(d2);
            
            
            if(q.last(d1,d2,loc).isEmpty()){
                
                
                   HBox h = new HBox();
            h.setSpacing(50);
            //grid1
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(4);
            grid.setPadding(new Insets(0, 10, 0, 10));                    
            //label datep
            Label datep = new Label("                                                                                                         pas d'annonces denière minute");
            datep.getStyleClass().add("title");
            grid.add(datep, 0, 1);
             h.getChildren().addAll(grid);
            list.getItems().add(h);
            list.setExpanded(true);
            list.depthProperty().set(1);   
            list.setCellFactory(AnnoncesListView -> new JFXListCell());  
            }else{
        for (Annonce que : q.last(d1,d2,loc)) {
            
            UserServices us = new UserServices();
            
            User u = us.FindById(que.getMembre_id().getId());
            
            //hbox
            HBox h = new HBox();
            ImageView img;
            ImageView taba;
            ImageView anim;
            File file=null ;
            String name="";
            h.setSpacing(50);
            //grid1
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(4);
            grid.setPadding(new Insets(0, 10, 0, 10));     
            Label idann = new Label(String.valueOf(que.getId()));
            grid.add(idann, 0, 0);
            idann.setVisible(false);
            //label datep
            if( u.getPhotoprofilpath() == (null) ){
                String url ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/téléchargement.png";
                img = new ImageView(url);
                
            }else{
                file = new File("/C:/wamp/www/covoiturage-EDT-ONE/web/"+u.getPhotoprofilpath()); 
                img = new ImageView(file.toURI().toString());
                
            }
            
                
            img.getStyleClass().add("image");
            img.setFitWidth(150);
            img.setFitHeight(150);
            grid.add(img, 0, 1);
            //label nom
           
            if(u.getPrenom() == null){
                name= name+"";
            }else{
                name="           "+u.getPrenom();
            }

            Label name1 = new Label(name);
            name1.getStyleClass().add("text2");
            grid.add(name1, 0, 2);
            String num = "           "+String.valueOf(u.getTelephone())+"                                            ";
            Label numtel = new Label(num);
            numtel.getStyleClass().add("text2");
            grid.add(numtel, 0, 3);
            //dated
            Label id = new Label("                                                            "+String.valueOf(que.getDate_depart().toString())+"                                                                            ");
            id.getStyleClass().add("text1");
            grid.add(id, 1, 0);
            
            //trajet
             trajetService ts = new trajetService();
           
           trajet traj = ts.FindById(que.getTrajet_id().getId());
            VilleService vs = new VilleService();
            int ivd = traj.getVille_dep();
            Ville vd1 = vs.findById(ivd);
            int iva = traj.getVille_arr();
            Ville va1 = vs.findById(iva);
            
            GridPane grid1 = new GridPane();
            grid1.setHgap(10);
            grid1.setVgap(4);
            grid1.setPadding(new Insets(0, 10, 0, 10));  
            String Newligne=System.getProperty("line.separator"); 
            Label prix = new Label(Newligne+"                                       "+vd1.getNom()+" => "+va1.getNom());
            
            grid1.add(prix, 0, 0);
                        //label comm     
            if(que.getCommentaire() == null){
                
            }else{
            Label nbrp = new Label("Commntaire : "+que.getCommentaire());
            nbrp.getStyleClass().add("text1");
            grid1.add(nbrp, 0, 3);
            prix.setId("ff");
            }
            
            if(que.getNbrrate() == 0){
                
            }else{
            Rating rate = new Rating(5, que.getRating());
            rate.setDisable(true);
            grid1.add(rate, 0, 4);
            prix.setId("ff");
            }
            System.out.println(u.getMusique());
            if(u.getMusique() == null){
                
            }else{
            Label musique = new Label("Musique : "+u.getMusique());
            musique.getStyleClass().add("text1");
            grid1.add(musique, 0, 1);
            prix.setId("ff");
            }
            if(u.getSexe()== null){
                
            }else{
            Label sexe = new Label("Sexe : "+u.getSexe());
            sexe.getStyleClass().add("text2");
            grid1.add(sexe, 0, 2);
            prix.setId("ff");
            }
            
            prix.getStyleClass().add("text2");
            grid.add(grid1, 1, 1);
            prix.setId("tt");
            
            
            GridPane grid2 = new GridPane();
            grid2.setHgap(10);
            grid2.setVgap(4);
            grid2.setPadding(new Insets(0, 10, 0, 10)); 
            
            Label price = new Label("      "+String.valueOf(que.getPrix())+Newligne+"dt/personne");
            price.getStyleClass().add("text2");
            grid2.add(price, 0, 0);
            prix.setId("ff");
            
            Label nb = new Label("        "+String.valueOf(que.getNb_places())+Newligne+"     places"+Newligne);
            nb.getStyleClass().add("text1");
            grid2.add(nb, 0, 1);
            prix.setId("ff");
            
            
            
            if(u.getTabagime()== 0){
                String url1 ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/No_smoking_symbol.svg.png";
                taba = new ImageView(url1);
                taba.getStyleClass().add("image");
                 taba.setFitWidth(25);
                 taba.setFitHeight(25);
                 
                 grid2.add(taba, 0, 2);
            }else{

            }
            if(u.getAnimaux()== 0){
                String url2 ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/forbidden_dog_animal-512.png";
                anim = new ImageView(url2);
                anim.getStyleClass().add("image");
                 anim.setFitWidth(25);
                 anim.setFitHeight(25);
                 grid2.add(anim, 0, 3);
            }else{

            }
           
            
            grid.add(grid2, 2, 1);
            h.getChildren().addAll(grid);
            list.getItems().add(h);
            list.setExpanded(true);
            list.depthProperty().set(1);   
            list.setCellFactory(AnnoncesListView -> new JFXListCell());     
            
        }}
        
    }

   
    @FXML
    private void getAnnonce(MouseEvent event) {
        GridPane g = ((GridPane) list.getSelectionModel().getSelectedItem().getChildren().get(0));
        Label l = (Label) g.getChildren().get(0); 
        NavigatorData.getInstance().setSelectedAnnonce(q.selectOne(Integer.parseInt(l.getText())));
        Navigator.LoadScene(Navigator.affrech);

    }   
  
}
