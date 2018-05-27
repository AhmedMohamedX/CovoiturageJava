/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Iservices.Icomment;
import Models.NavigatorData;
import Models.Ville;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import Models.comment;
import Models.trajet;
import Services.VilleService;
import Services.trajetService;
import java.util.Date;
import Services.CommentService;
import Iservices.Icomment;
import Models.Annonce;
import Models.User;
import Models.threadComment;
import Services.AnnonceService;
import Services.ReservationServices;
import Services.ThreadCommentService;
import Services.UserServices;
import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import org.controlsfx.control.Rating;
/**
 * FXML Controller class
 *
 * @author Mortadhafff
 */
public class AnnonceAffController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Label content;
    @FXML
    private Label prixx;
    @FXML
    private Label nbplaces;
    @FXML
    private JFXButton rate;
    @FXML
    private Label commentaire;
    @FXML
    private Label name;
    @FXML
    private Label tel;
    @FXML
    private ImageView image;
    @FXML
    private Rating rating;
    @FXML
    private JFXTextArea Answer;
    @FXML
    private Label numComments;
    @FXML
    private JFXListView<comment> LisyReponse;
    @FXML
    private JFXButton AnswerButton;
    private ObservableList<comment> Reponses;
    List list = new ArrayList();
    CommentService cs = new CommentService();
    UserServices us= new UserServices();
    ThreadCommentService tser= new ThreadCommentService();
    ReservationServices res = new ReservationServices();
    @FXML
    private AnchorPane but;
    Integer ti =NavigatorData.getInstance().getSelectedAnnonce().getTrajet_id().getId();
    Date dd = NavigatorData.getInstance().getSelectedAnnonce().getDate_depart();
    Date dp = NavigatorData.getInstance().getSelectedAnnonce().getDate();
    String comentaire = NavigatorData.getInstance().getSelectedAnnonce().getCommentaire();
    double prix = NavigatorData.getInstance().getSelectedAnnonce().getPrix();
    String dev = NavigatorData.getInstance().getSelectedAnnonce().getDeviation();
    int nbrp= NavigatorData.getInstance().getSelectedAnnonce().getNb_places();
    int idann = NavigatorData.getInstance().getSelectedAnnonce().getId();
    AnnonceService aser = new AnnonceService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadscen();

    }
    private void loadscen() {
        

        AnswerButton.getStyleClass().add("button-raised"); 
        rate.getStyleClass().add("button-raised");
        trajetService ts = new trajetService();
           String Newligne=System.getProperty("line.separator"); 
           trajet traj = ts.FindById(ti);
            VilleService vs = new VilleService();
            int ivd = traj.getVille_dep();
            Ville vd = vs.findById(ivd);
            int iva = traj.getVille_arr();
            Ville va = vs.findById(iva);
        title.setText(vd.getNom()+" -> "+va.getNom());
        title.getStyleClass().add("text2");
        
        content.setText("Date de départ :"+Newligne+dd.toString());
        content.getStyleClass().add("text3");
        prixx.setText("Prix par personne :"+Newligne+String.valueOf(prix));
        prixx.getStyleClass().add("text4");
        
        nbplaces.setText("Nombre de places :"+Newligne+String.valueOf(nbrp));
        nbplaces.getStyleClass().add("text3");
        
        name.setText(NavigatorData.getInstance().getSelectedAnnonce().getMembre_id().getPrenom());
        name.getStyleClass().add("text2");
        
        tel.setText(String.valueOf(NavigatorData.getInstance().getSelectedAnnonce().getMembre_id().getTelephone()));
        tel.getStyleClass().add("text2");
        if( NavigatorData.getInstance().getSelectedAnnonce().getMembre_id().getPhotoprofilpath() == (null) ){
                String url ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/téléchargement.png";
                Image img = new Image(url);
                img.widthProperty().add(150);
                img.heightProperty().add(150);
                image.setImage(img);
                
            }else{
                File file = new File("/C:/wamp/www/covoiturage-EDT-ONE/web/"+NavigatorData.getInstance().getSelectedAnnonce().getMembre_id().getPhotoprofilpath()); 
                Image img = new Image(file.toURI().toString());
                img.widthProperty().add(150);
                img.heightProperty().add(150);
                image.setImage(img);
                
            }
        
        if(comentaire == null){
            
        }else{
            commentaire.setText("Commentaire :"+Newligne+comentaire);
            commentaire.getStyleClass().add("text4");
        }
        
        
        
        
        list = cs.selectAllByAnnonce(String.valueOf(idann));
        threadComment ttt = tser.selectOneByIdAnnonce(String.valueOf(idann));
        if(ttt.getId() == 0){
            numComments.setText("0");
        }else{
            numComments.setText(String.valueOf(ttt.getNum_comments()));
        }
        
        Reponses = FXCollections.observableArrayList();
        Reponses.addAll(list);
        LisyReponse.setItems(Reponses);
        LisyReponse.setCellFactory(new Callback<ListView<comment>, ListCell<comment>>() {
            @Override
            public ListCell<comment> call(ListView<comment> ListViewReponses) {
                ListCell<comment> cell = new ListCell<comment>() {
                    @Override
                    protected void updateItem(comment r, boolean bln) {
                        super.updateItem(r, bln);
                        if (r != null) {
                            
                            VBox vb1 = new VBox();
                            VBox vb2 = new VBox(20);
                            HBox hbC = new HBox(20);
                            vb2.setPrefWidth(400);
                            vb1.setAlignment(Pos.CENTER);
                            vb2.setAlignment(Pos.CENTER_LEFT);
//                            hbC.setStyle("-fx-border-color: #C8C8C8 ;");
                            Text contenu = new Text();
                            String contenuString = r.getBody();
                          
                            contenu.setText(contenuString);
                            Label userNameL = new Label();
                            userNameL.setStyle("-fx-text-fill: #C8C81E;-fx-font-weight: bold;-fx-font-family: \"Arial\";");
                            
                            
                            String userName = r.getMembre_id().getPrenom();
                        
                            userNameL.setText(userName);
                            //image profile
                            ImageView Person;
                             if( r.getMembre_id().getPhotoprofilpath() == (null) ){
                              String url ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/téléchargement.png";
                                   Person = new ImageView(url);
                
                               }else{
                                   File file = new File("/C:/wamp/www/covoiturage-EDT-ONE/web/"+r.getMembre_id().getPhotoprofilpath()); 
                                   Person = new ImageView(file.toURI().toString());
                
                               }
                            
                            Person.setFitWidth(50);
                            Person.setFitHeight(50);
                            Person.getStyleClass().add("imageProfil");
                            vb1.getChildren().addAll(Person, userNameL);
                            vb2.getChildren().addAll(contenu);
                            hbC.getChildren().addAll(vb1, vb2);
                            setGraphic(hbC);                            
                        }
                    }

                };

                return cell;
            }
        });
        
    }
    @FXML
    private void RateAction(ActionEvent event){
        rate.setVisible(false);
        rating.setDisable(true);
        double r =rating.getRating();
        
        int r1 = (int)r;
        
        if(NavigatorData.getInstance().getSelectedAnnonce().getRating() == 0){
            aser.editrateAnnonce(idann, 1, r1);
        }else{
            Annonce aa = aser.selectOne(idann);
                int nbr1 = aa.getNbrrate();
                int r2 = aa.getRating();
                int nbr2 = nbr1+1;
                float rate1= ((nbr1*r2)+r1)/nbr2;
                int rat = (int)rate1;
                aser.editrateAnnonce(idann, nbr2, rat);
        }       
                
        
    }
    @FXML
    private void AnswerAction(ActionEvent event) {
        String contenu = Answer.getText();  
        User u1 = us.FindById(User.getIdofconnecteduser());
        if (!contenu.equals("")) {  
            Date d1 = new Date();
            threadComment tc = tser.selectOneByIdAnnonce(String.valueOf(idann));
            
           if(tc.getId() == 0){
               threadComment t1 = new threadComment(idann,"http://127.168.0.1/covoiturage-EDT-ONE/web/app_dev.php/membre/reservation/"+String.valueOf(idann), 1, 1, d1);
                tser.insertThreadComent(t1);
                threadComment t2 = tser.selectOneByIdAnnonce(String.valueOf(idann));
                
                comment c1 = new comment(Answer.getText(), "", 0, 0, d1, t2, u1);
               cs.insertComment(c1);
               
           }else{
               threadComment tt = tser.selectOneByIdAnnonce(String.valueOf(idann));
               int nbb = tt.getNum_comments()+1;
               tser.editNumComments(idann, nbb);
               comment c1 = new comment(Answer.getText(), "", 0, 0, d1, tc, u1);
               cs.insertComment(c1);
               
           }
            
            
            list = cs.selectAllByAnnonce(String.valueOf(idann));
            Reponses = FXCollections.observableArrayList();
            Reponses.addAll(list);
            LisyReponse.setItems(Reponses);
          LisyReponse.setCellFactory(new Callback<ListView<comment>, ListCell<comment>>() {
            @Override
            public ListCell<comment> call(ListView<comment> ListViewReponses) {
                ListCell<comment> cell = new ListCell<comment>() {
                    @Override
                    protected void updateItem(comment r, boolean bln) {
                        super.updateItem(r, bln);
                        if (r != null) {
                            threadComment t5 = tser.selectOneByIdAnnonce(String.valueOf(idann));
                            numComments.setText(String.valueOf(t5.getNum_comments()));
                            VBox vb1 = new VBox();
                            VBox vb2 = new VBox(20);
                            HBox hbC = new HBox(20);
                            vb2.setPrefWidth(400);
                            vb1.setAlignment(Pos.CENTER);
                            vb2.setAlignment(Pos.CENTER_LEFT);
//                            hbC.setStyle("-fx-border-color: #C8C8C8 ;");
                            Text contenu = new Text();
                            String contenuString = r.getBody();
                          
                            contenu.setText(contenuString);
                            Label userNameL = new Label();
                            userNameL.setStyle("-fx-text-fill: #C8C81E;-fx-font-weight: bold;-fx-font-family: \"Arial\";");
                            
                            
                            String userName = r.getMembre_id().getPrenom();
                        
                            userNameL.setText(userName);
                            //image profile
                            ImageView Person;
                             if( r.getMembre_id().getPhotoprofilpath() == (null) ){
                              String url ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/téléchargement.png";
                                   Person = new ImageView(url);
                
                               }else{
                                   File file = new File("/C:/wamp/www/covoiturage-EDT-ONE/web/"+r.getMembre_id().getPhotoprofilpath()); 
                                   Person = new ImageView(file.toURI().toString());
                
                               }
                            
                            Person.setFitWidth(50);
                            Person.setFitHeight(50);
                            Person.getStyleClass().add("imageProfil");
                            vb1.getChildren().addAll(Person, userNameL);
                            vb2.getChildren().addAll(contenu);
                            hbC.getChildren().addAll(vb1, vb2);
                            setGraphic(hbC);                            
                        }
                    }

                };

                return cell;
            }
            });
            Answer.clear();
        }
    }

    @FXML
    private void payer(ActionEvent event) {
        User uu = us.FindById(User.getIdofconnecteduser());
        int anS = uu.getJetons();
        if(anS< (int)prix){
             Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Solde insuffisant!");
        alert.showAndWait();
        }else{
            us.Payer(User.getIdofconnecteduser(),anS-(int)prix);
            us.GetPayer(NavigatorData.getInstance().getSelectedAnnonce().getMembre_id().getId(),NavigatorData.getInstance().getSelectedAnnonce().getMembre_id().getJetons()+(int)prix);
             if (NavigatorData.getInstance().getSelectedAnnonce().getNb_places() != 0) {
            
                

            res.add(NavigatorData.getInstance().getSelectedAnnonce(), User.getIdofconnecteduser());
            
            

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Reservation non effectuée,Nombre de place Complet!!!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        
       }
            Navigator.LoadScene(Navigator.accueil);
              Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Payement avec succées!");
        alert.showAndWait();
        }
    }

    @FXML
    private void ref(MouseEvent event) {
        LisyReponse.getItems().removeAll(LisyReponse.getItems());
        threadComment ttt = tser.selectOneByIdAnnonce(String.valueOf(idann));
        if(ttt.getId() == 0){
            numComments.setText("0");
        }else{
            numComments.setText(String.valueOf(ttt.getNum_comments()));
        }
        Reponses = FXCollections.observableArrayList();
        list = cs.selectAllByAnnonce(String.valueOf(idann));
        Reponses.addAll(list);
        LisyReponse.setItems(Reponses);
        LisyReponse.setCellFactory(new Callback<ListView<comment>, ListCell<comment>>() {
            @Override
            public ListCell<comment> call(ListView<comment> ListViewReponses) {
                ListCell<comment> cell = new ListCell<comment>() {
                    @Override
                    protected void updateItem(comment r, boolean bln) {
                        super.updateItem(r, bln);
                        if (r != null) {
                            
                            VBox vb1 = new VBox();
                            VBox vb2 = new VBox(20);
                            HBox hbC = new HBox(20);
                            vb2.setPrefWidth(400);
                            vb1.setAlignment(Pos.CENTER);
                            vb2.setAlignment(Pos.CENTER_LEFT);
//                            hbC.setStyle("-fx-border-color: #C8C8C8 ;");
                            Text contenu = new Text();
                            String contenuString = r.getBody();
                          
                            contenu.setText(contenuString);
                            Label userNameL = new Label();
                            userNameL.setStyle("-fx-text-fill: #C8C81E;-fx-font-weight: bold;-fx-font-family: \"Arial\";");
                            
                            
                            String userName = r.getMembre_id().getPrenom();
                        
                            userNameL.setText(userName);
                            //image profile
                            ImageView Person;
                             if( r.getMembre_id().getPhotoprofilpath() == (null) ){
                              String url ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/téléchargement.png";
                                   Person = new ImageView(url);
                
                               }else{
                                   File file = new File("/C:/wamp/www/covoiturage-EDT-ONE/web/"+r.getMembre_id().getPhotoprofilpath()); 
                                   Person = new ImageView(file.toURI().toString());
                
                               }
                            
                            Person.setFitWidth(50);
                            Person.setFitHeight(50);
                            Person.getStyleClass().add("imageProfil");
                            vb1.getChildren().addAll(Person, userNameL);
                            vb2.getChildren().addAll(contenu);
                            hbC.getChildren().addAll(vb1, vb2);
                            setGraphic(hbC);                            
                        }
                    }

                };

                return cell;
            }
        });
    }


}
