/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Models.Message;
import Models.NavigatorData;
import Models.User;

import Services.MessageService;
import Services.UserServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;


public class ChatController implements Initializable {


    @FXML
    private JFXListView<HBox> listuser;
    @FXML
    private JFXTextArea send;

    MessageService mess = new MessageService();

    List<String> UsersName = new ArrayList<>();
    File file;
    private ObservableList<Message> Messages;
    List list = new ArrayList();
    @FXML
    private JFXListView<Message> ListMessage;

    UserServices userv = new UserServices();
    @FXML
    private JFXButton Upload;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chargeScene();
    }

    private void chargeScene() {

        for (User us : userv.getAll()) {
            //hbox
            
            HBox h = new HBox();
            h.setSpacing(50);
            //grid1
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(4);
            grid.setPadding(new Insets(0, 10, 0, 10));
            //image profil
            ImageView Person;
                if( us.getPhotoprofilpath() == (null) ){
                String url ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/téléchargement.png";
                Person = new ImageView(url);
                
            }else{
                file = new File("/C:/wamp/www/covoiturage-EDT-ONE/web/"+us.getPhotoprofilpath()); 
                Person = new ImageView(file.toURI().toString());
                
            }
            
            Person.setFitWidth(50);
            Person.setFitHeight(50);
            grid.add(Person, 0, 0, 1, 2);
            //label nom et prenom
            Label nom = new Label(String.valueOf(us.getPrenom()));
            nom.getStyleClass().add("title");
            grid.add(nom, 0, 2); 
            ImageView connect;
                if( us.getConnect()== 0 ){
                String url ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/button-red.png";
                connect = new ImageView(url);
                
            }else{
                String url ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/button-green.png";
                connect = new ImageView(url);
                
            }
            
            connect.setFitWidth(10);
            connect.setFitHeight(10);
            grid.add(connect, 3, 1);
            
            h.getChildren().addAll(grid);
            listuser.getItems().add(h);
            listuser.setExpanded(true);
            listuser.depthProperty().set(1);
            listuser.setCellFactory(QuestionListView -> new JFXListCell());
        }

          list = mess.getMessageAll();
          Messages = FXCollections.observableArrayList();
          Messages.addAll(list);
          ListMessage.setItems(Messages);
        ListMessage.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
            @Override
            public ListCell<Message> call(ListView<Message> ListViewReponses) {
                ListCell<Message> cell = new ListCell<Message>() {
                    @Override
                    protected void updateItem(Message m, boolean bln) {
                        super.updateItem(m, bln);
                        if (m != null) {
                            VBox vb1 = new VBox();
                            VBox vb2 = new VBox(20);
                            HBox hbC = new HBox(20);
                            vb2.setPrefWidth(400);
                            vb1.setAlignment(Pos.CENTER);
                            vb2.setAlignment(Pos.CENTER_LEFT);
                            Text contenu = new Text();
                            String contenuString = m.getContenu();
                            String Newligne=System.getProperty("line.separator"); 
                             SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
    
                            String strDate = sdfDate.format(m.getDateEnvoie());
                            contenu.setText(contenuString+Newligne+Newligne+strDate);
                            
                            Label userNameL = new Label();
                            userNameL.setStyle("-fx-text-fill: #C8C81E;-fx-font-weight: bold;-fx-font-family: \"Arial\";");
                            String userName = m.getEmetteur().getPrenom();
                            userNameL.setText(userName);
                            //image profile
                            ImageView Person;
                           if( m.getEmetteur().getPhotoprofilpath() == (null) ){
                              String url ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/téléchargement.png";
                              Person = new ImageView(url);
                
                              }else{
                              file = new File("/C:/wamp/www/covoiturage-EDT-ONE/web/"+m.getEmetteur().getPhotoprofilpath()); 
                                Person = new ImageView(file.toURI().toString());
                
                              }
                            Person.setFitWidth(50);
                            Person.setFitHeight(50);
                            Person.getStyleClass().add("imageProfil");
                            vb1.getChildren().addAll(Person, userNameL);
                            hbC.getChildren().addAll(vb1, vb2);
                            vb2.getChildren().addAll(contenu);
                            setGraphic(hbC);
                        }
                    }

                };

                return cell;
            }
        });

    }

    @FXML
    private void sendmessage(ActionEvent event) {        
      
          String contenu = send.getText();
            if (!contenu.equals("")) {
                User uu = userv.FindById(User.getIdofconnecteduser());
                Message r = new Message(contenu, uu);
                mess.add(r);
                list = mess.getMessageAll();
                Messages = FXCollections.observableArrayList();
                Messages.addAll(list);
                ListMessage.setItems(Messages);
                ListMessage.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
                    @Override
                    public ListCell<Message> call(ListView<Message> ListViewReponses) {
                        ListCell<Message> cell = new ListCell<Message>() {
                            @Override
                            protected void updateItem(Message m, boolean bln) {
                                super.updateItem(m, bln);
                                if (m != null) {
                                    VBox vb1 = new VBox();
                            VBox vb2 = new VBox(20);
                            HBox hbC = new HBox(20);
                            vb2.setPrefWidth(400);
                            vb1.setAlignment(Pos.CENTER);
                            vb2.setAlignment(Pos.CENTER_LEFT);
                            Text contenu = new Text();
                            String contenuString = m.getContenu();
                            contenu.setText(contenuString);
                            
                            Label userNameL = new Label();
                            userNameL.setStyle("-fx-text-fill: #C8C81E;-fx-font-weight: bold;-fx-font-family: \"Arial\";");
                            String userName = m.getEmetteur().getPrenom();
                            userNameL.setText(userName);
                            //image profile
                            ImageView Person;
                           if( m.getEmetteur().getPhotoprofilpath() == (null) ){
                              String url ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/téléchargement.png";
                              Person = new ImageView(url);
                
                              }else{
                              file = new File("/C:/wamp/www/covoiturage-EDT-ONE/web/"+m.getEmetteur().getPhotoprofilpath()); 
                                Person = new ImageView(file.toURI().toString());
                
                              }
                            Person.setFitWidth(50);
                            Person.setFitHeight(50);
                            Person.getStyleClass().add("imageProfil");
                            vb1.getChildren().addAll(Person, userNameL);
                            hbC.getChildren().addAll(vb1, vb2);
                            vb2.getChildren().addAll(contenu);
                            setGraphic(hbC);
                                }
                            }

                        };

                        return cell;
                    }
                });
                send.clear();
            }
      }

    @FXML
    private void ref(MouseEvent event) {
        listuser.getItems().removeAll(listuser.getItems());
         for (User us : userv.getAll()) {
            //hbox
            
            HBox h = new HBox();
            h.setSpacing(50);
            //grid1
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(4);
            grid.setPadding(new Insets(0, 10, 0, 10));
            //image profil
            ImageView Person;
                if( us.getPhotoprofilpath() == (null) ){
                String url ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/téléchargement.png";
                Person = new ImageView(url);
                
            }else{
                file = new File("/C:/wamp/www/covoiturage-EDT-ONE/web/"+us.getPhotoprofilpath()); 
                Person = new ImageView(file.toURI().toString());
                
            }
            
            Person.setFitWidth(50);
            Person.setFitHeight(50);
            grid.add(Person, 0, 0, 1, 2);
            //label nom et prenom
            Label nom = new Label(String.valueOf(us.getPrenom()));
            nom.getStyleClass().add("title");
            grid.add(nom, 0, 2); 
            ImageView connect;
                if( us.getConnect()== 0 ){
                String url ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/button-red.png";
                connect = new ImageView(url);
                
            }else{
                String url ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/button-green.png";
                connect = new ImageView(url);
                
            }
            
            connect.setFitWidth(10);
            connect.setFitHeight(10);
            grid.add(connect, 3, 1);
            
            h.getChildren().addAll(grid);
            listuser.getItems().add(h);
            listuser.setExpanded(true);
            listuser.depthProperty().set(1);
            listuser.setCellFactory(QuestionListView -> new JFXListCell());
        }

         list = mess.getMessageAll();
                Messages = FXCollections.observableArrayList();
                Messages.addAll(list);
                ListMessage.setItems(Messages);
                ListMessage.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
                    @Override
                    public ListCell<Message> call(ListView<Message> ListViewReponses) {
                        ListCell<Message> cell = new ListCell<Message>() {
                            @Override
                            protected void updateItem(Message m, boolean bln) {
                                super.updateItem(m, bln);
                                if (m != null) {
                                    VBox vb1 = new VBox();
                            VBox vb2 = new VBox(20);
                            HBox hbC = new HBox(20);
                            vb2.setPrefWidth(400);
                            vb1.setAlignment(Pos.CENTER);
                            vb2.setAlignment(Pos.CENTER_LEFT);
                            Text contenu = new Text();
                            String contenuString = m.getContenu();
                            contenu.setText(contenuString);
                            
                            Label userNameL = new Label();
                            userNameL.setStyle("-fx-text-fill: #C8C81E;-fx-font-weight: bold;-fx-font-family: \"Arial\";");
                            String userName = m.getEmetteur().getPrenom();
                            userNameL.setText(userName);
                            //image profile
                            ImageView Person;
                           if( m.getEmetteur().getPhotoprofilpath() == (null) ){
                              String url ="file:/C:/Users/AH%20Info/Documents/NetBeansProjects/CovoiturageEDT/src/IMG/téléchargement.png";
                              Person = new ImageView(url);
                
                              }else{
                              file = new File("/C:/wamp/www/covoiturage-EDT-ONE/web/"+m.getEmetteur().getPhotoprofilpath()); 
                                Person = new ImageView(file.toURI().toString());
                
                              }
                            Person.setFitWidth(50);
                            Person.setFitHeight(50);
                            Person.getStyleClass().add("imageProfil");
                            vb1.getChildren().addAll(Person, userNameL);
                            hbC.getChildren().addAll(vb1, vb2);
                            vb2.getChildren().addAll(contenu);
                            setGraphic(hbC);
                                }
                            }

                        };

                        return cell;
                    }
                });
                
    }
      
    


}
