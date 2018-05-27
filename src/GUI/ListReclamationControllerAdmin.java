
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import Models.NavigatorData;
import Models.User;
import Models.reclamations;
import Services.ReclamationService;
import Services.UserServices;
import javafx.scene.Node;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Mortadhafff
 */
public class ListReclamationControllerAdmin implements Initializable {

    @FXML
    private JFXTextField search;
    @FXML
    private JFXListView<HBox> list;
    private ObservableList<reclamations> reclamation = FXCollections.observableArrayList();
//    FilteredList<Question> filtredQuestions = new FilteredList(question, predicate -> true);
    JFXButton b = new JFXButton();
    ReclamationService rec = new ReclamationService();   
    
    @FXML
    private JFXComboBox<String> SearchCombo;
    @FXML
    private JFXButton pdfbutton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chargelistview();
    
    }

    private void chargelistview() {     
    
        for (reclamations reclam : rec.getAll()) {
            //hbox
            HBox h = new HBox();
            h.setSpacing(50);
            //grid1
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(4);
            grid.setPadding(new Insets(0, 10, 0, 10));
            //grid2
            GridPane gridq = new GridPane();
            gridq.setHgap(10);
            gridq.setVgap(4);
            gridq.setPadding(new Insets(0, 10, 0, 10)); 
                //grid3
            GridPane gridmessage = new GridPane();
            gridmessage.setHgap(10);
            gridmessage.setVgap(4);
            gridmessage.setPadding(new Insets(0, 10, 0, 10)); 
          //grid4
            GridPane gridbutton = new GridPane();
            gridbutton.setHgap(10);
            gridbutton.setVgap(4);
            gridbutton.setPadding(new Insets(0, 10, 0, 10)); 
            Label REPONSE = new Label(reclam.getReponse());
            REPONSE.getStyleClass().add("title");
            gridbutton.add(REPONSE, 0, 0, 1, 2);
            //label message
            Label message = new Label(String.valueOf(reclam.getMessage()));
            message.getStyleClass().add("title");
            gridmessage.add(message, 0, 0, 1, 2);
            //image profil
            
            ImageView Person = new ImageView("/IMG/téléchargement.png");
            Person.setFitWidth(50);
            Person.setFitHeight(50);
            grid.add(Person, 0, 0, 1, 2);
            //label nom et prenom
            Label nom = new Label(String.valueOf(reclam.getId()));
            nom.getStyleClass().add("title");
            grid.add(nom, 0, 2);
            //idQuestion   
        
            //label titre
            Label title = new Label(reclam.getSujet());
            title.getStyleClass().add("title");
            gridq.add(title, 1, 0);
            title.setId("tt");
            //label date              
            Label date = new Label(String.valueOf(reclam.getDate()));
            date.getStyleClass().add("time");
            gridq.add(date, 1, 1);
//            //Competence 
//            for (Competence com : que.getCompetences()) {
//               Label tag= new  Label(com.getNom());
//               gridq.add(tag, 1, 2);
//            }            
            h.getChildren().addAll(grid, gridq,gridmessage,gridbutton);
            list.getItems().add(h);
            list.setExpanded(true);
            list.depthProperty().set(1);   
            list.setCellFactory(ReclamationListView -> new JFXListCell());     
            
        }
         ObservableList<String> reclam = FXCollections.observableArrayList();
    List<String> a = new ArrayList<>();
     a.add("tout");
        a.add("membre");
        a.add("Application");
        a.add("en attente de reponse");
        reclam.addAll(a);
        SearchCombo.setItems(reclam);
    }

    @FXML
    private void recherche(KeyEvent event) {
        String s = search.getText();
        System.out.println(s);
        list.getItems().removeAll(list.getItems());
         for (reclamations reclam : rec.search(s)) {
            //hbox
            HBox h = new HBox();
            h.setSpacing(50);
            //grid1
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(4);
            grid.setPadding(new Insets(0, 10, 0, 10));
            //grid2
            GridPane gridq = new GridPane();
            gridq.setHgap(10);
            gridq.setVgap(4);
            gridq.setPadding(new Insets(0, 10, 0, 10));
            //grid3
            GridPane gridmessage = new GridPane();
            gridmessage.setHgap(10);
            gridmessage.setVgap(4);
            gridmessage.setPadding(new Insets(0, 10, 0, 10)); 
          //grid4
            GridPane gridbutton = new GridPane();
            gridbutton.setHgap(10);
            gridbutton.setVgap(4);
            gridbutton.setPadding(new Insets(0, 10, 0, 10)); 
            Label REPONSE = new Label(reclam.getReponse());
            REPONSE.getStyleClass().add("title");
            gridbutton.add(REPONSE, 0, 0, 1, 2);
            //label message
            Label message = new Label(String.valueOf(reclam.getMessage()));
            message.getStyleClass().add("title");
            gridmessage.add(message, 0, 0, 1, 2);
            
               //image profil
            
            ImageView Person = new ImageView("/IMG/téléchargement.png");
            Person.setFitWidth(50);
            Person.setFitHeight(50);
            grid.add(Person, 0, 0, 1, 2);
            //label nom et prenom
            Label nom = new Label(String.valueOf(reclam.getId()));
            nom.getStyleClass().add("title");
            grid.add(nom, 0, 2);
            //idQuestion   
        
            //label titre
            Label title = new Label(reclam.getSujet());
            title.getStyleClass().add("title");
            gridq.add(title, 1, 0);
            title.setId("tt");
            //label date              
            Label date = new Label(String.valueOf(reclam.getDate()));
            date.getStyleClass().add("time");
            gridq.add(date, 1, 1);       
            h.getChildren().addAll(grid, gridq,gridmessage,gridbutton);
            list.getItems().add(h);
        }
        list.refresh();
    }
    @FXML
    private void getReclamation(MouseEvent event) throws IOException {        
         GridPane g = ((GridPane) list.getSelectionModel().getSelectedItem().getChildren().get(0));
        Label l = (Label) g.getChildren().get(1); 
        Integer ll = Integer.valueOf(l.getText());
        
        
        NavigatorData.getInstance().setSelectedReclamation(rec.findById(ll)); 
       Parent parent125 = FXMLLoader.load(getClass().getResource("/GUI/RepondreFXML.fxml"));
       Scene scene1116 = new Scene(parent125 );
       Stage stage1116  = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage1116 .hide();
       stage1116 .setScene(scene1116 );
       stage1116.show();
    }       
public void LoadWindow(String loc, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Navigator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchcombo(ActionEvent event) {
            List<reclamations> reclamationscombo = new ArrayList<>();
          String s = SearchCombo.getSelectionModel().getSelectedItem();
          if (s.equals("membre")) {
            reclamationscombo.addAll(rec.searchbox(s));
        } else if(s.equals("Application")) 
        {
            reclamationscombo.addAll(rec.searchbox(s));
        }else if(s.equals("en attente de reponse"))
        {
            reclamationscombo.addAll(rec.searchbox(s));
        }
        else
        {
            reclamationscombo.addAll(rec.getAll());
        }
        System.out.println(reclamationscombo);
        list.getItems().removeAll(list.getItems());
         for (reclamations reclam : reclamationscombo) {
            //hbox
            HBox h = new HBox();
            h.setSpacing(50);
            //grid1
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(4);
            grid.setPadding(new Insets(0, 10, 0, 10));
            //grid2
            GridPane gridq = new GridPane();
            gridq.setHgap(10);
            gridq.setVgap(4);
            gridq.setPadding(new Insets(0, 10, 0, 10));
            //grid3
            GridPane gridmessage = new GridPane();
            gridmessage.setHgap(10);
            gridmessage.setVgap(4);
            gridmessage.setPadding(new Insets(0, 10, 0, 10)); 
          //grid4
            GridPane gridbutton = new GridPane();
            gridbutton.setHgap(10);
            gridbutton.setVgap(4);
            gridbutton.setPadding(new Insets(0, 10, 0, 10)); 
            Label REPONSE = new Label(reclam.getReponse());
            REPONSE.getStyleClass().add("title");
            gridbutton.add(REPONSE, 0, 0, 1, 2);
            //label message
            Label message = new Label(String.valueOf(reclam.getMessage()));
            message.getStyleClass().add("title");
            gridmessage.add(message, 0, 0, 1, 2);
            
               //image profil
            
            ImageView Person = new ImageView("/IMG/téléchargement.png");
            Person.setFitWidth(50);
            Person.setFitHeight(50);
            grid.add(Person, 0, 0, 1, 2);
            //label nom et prenom
            Label nom = new Label(String.valueOf(reclam.getId()));
            nom.getStyleClass().add("title");
            grid.add(nom, 0, 2);
            //idQuestion   
        
            //label titre
            Label title = new Label(reclam.getSujet());
            title.getStyleClass().add("title");
            gridq.add(title, 1, 0);
            title.setId("tt");
            //label date              
            Label date = new Label(String.valueOf(reclam.getDate()));
            date.getStyleClass().add("time");
            gridq.add(date, 1, 1);       
            h.getChildren().addAll(grid, gridq,gridmessage,gridbutton);
            list.getItems().add(h);
        }
        list.refresh();
    }

    @FXML
    private void pdf(ActionEvent event) {
        
         try
        {
            // prompting file chooser to get target location
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File targetLocation  = directoryChooser.showDialog(pdfbutton.getScene().getWindow());
            
            if (targetLocation == null)
            {
                String title = "Erreur Export PDF";
                String message = "Veuillez Selectionnez un emplacement pour exporter vos reclamations";
        
                TrayNotification tray = new TrayNotification();
        
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setAnimationType(AnimationType.FADE);
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.seconds(5));
            }
            else
            {
                boolean titleOneTimeOnly = false;

                PDDocument doc = new PDDocument();

                ArrayList<reclamations> lstReks = (ArrayList<reclamations>) new ReclamationService().getAll();
                
                for (int count = 0; count<lstReks.size(); count+=2)
//                for (reclamations reclamation : new ReclamationService().getAll())
                {          
                    PDPage page = new PDPage();
                    doc.addPage(page);

                    PDFont font = PDType1Font.HELVETICA_BOLD;

                    PDPageContentStream contents = new PDPageContentStream(doc, page);

                    if (!titleOneTimeOnly)
                    {
                        contents.beginText();
                        contents.setFont(PDType1Font.HELVETICA, 26);
                        contents.newLineAtOffset(185, 750);
                        contents.showText("Liste de vos reclamations");
                        contents.endText();

                        titleOneTimeOnly = !titleOneTimeOnly;
                    }

                    contents.beginText();
                    contents.setFont(font, 12);
                    contents.newLineAtOffset(100, 700);
                    contents.showText("Sujet");
                    contents.endText();

                    contents.beginText();
                    contents.setFont(font, 12);
                    contents.newLineAtOffset(200, 700);
                    contents.showText(lstReks.get(count).getSujet());
                    contents.endText();

                    contents.beginText();
                    contents.setFont(font, 12);
                    contents.newLineAtOffset(100, 650);
                    contents.showText("Message");
                    contents.endText();

                    contents.beginText();
                    contents.setFont(font, 12);
                    contents.newLineAtOffset(200, 650);
                    contents.showText(lstReks.get(count).getMessage());
                    contents.endText();

                    contents.beginText();
                    contents.setFont(font, 12);
                    contents.newLineAtOffset(100, 600);
                    contents.showText("Date");
                    contents.endText();

                    contents.beginText();
                    contents.setFont(font, 12);
                    contents.newLineAtOffset(200, 600);
                    contents.showText(lstReks.get(count).getDate().toString());
                    contents.endText();

                    contents.beginText();
                    contents.setFont(font, 12);
                    contents.newLineAtOffset(100, 550);
                    contents.showText("Reponse");
                    contents.endText();

                    contents.beginText();
                    contents.setFont(font, 12);
                    contents.newLineAtOffset(200, 550);
                    contents.showText(lstReks.get(count).getReponse());
                    contents.endText();

                    if (!lstReks.get(count).getTargetMember().getNom().isEmpty())
                    {
                        contents.beginText();
                        contents.setFont(font, 12);
                        contents.newLineAtOffset(100, 500);
                        contents.showText("Membre signalé");
                        contents.endText();

                        contents.beginText();
                        contents.setFont(font, 12);
                        contents.newLineAtOffset(200, 500);
                        contents.showText(lstReks.get(count).getTargetMember().getNom() + " " + lstReks.get(count).getTargetMember().getPrenom());
                        contents.endText();
                    }

                    // 2nd
                    // cas impair size & count+1 > size cause IndexOutOfBoundException
                    if (count+1 < lstReks.size())
                    {
                        contents.beginText();
                        contents.setFont(font, 12);
                        contents.newLineAtOffset(100, 400);
                        contents.showText("Sujet");
                        contents.endText();

                        contents.beginText();
                        contents.setFont(font, 12);
                        contents.newLineAtOffset(200, 400);
                        contents.showText(lstReks.get(count+1).getSujet());
                        contents.endText();

                        contents.beginText();
                        contents.setFont(font, 12);
                        contents.newLineAtOffset(100, 350);
                        contents.showText("Message");
                        contents.endText();

                        contents.beginText();
                        contents.setFont(font, 12);
                        contents.newLineAtOffset(200, 350);
                        contents.showText(lstReks.get(count+1).getMessage());
                        contents.endText();

                        contents.beginText();
                        contents.setFont(font, 12);
                        contents.newLineAtOffset(100, 300);
                        contents.showText("Date");
                        contents.endText();

                        contents.beginText();
                        contents.setFont(font, 12);
                        contents.newLineAtOffset(200, 300);
                        contents.showText(lstReks.get(count+1).getDate().toString());
                        contents.endText();

                        contents.beginText();
                        contents.setFont(font, 12);
                        contents.newLineAtOffset(100, 250);
                        contents.showText("Reponse");
                        contents.endText();

                        contents.beginText();
                        contents.setFont(font, 12);
                        contents.newLineAtOffset(200, 250);
                        contents.showText(lstReks.get(count+1).getReponse());
                        contents.endText();

                        if (!lstReks.get(count+1).getTargetMember().getNom().isEmpty())
                        {
                            contents.beginText();
                            contents.setFont(font, 12);
                            contents.newLineAtOffset(100, 200);
                            contents.showText("Membre signalé");
                            contents.endText();

                            contents.beginText();
                            contents.setFont(font, 12);
                            contents.newLineAtOffset(200, 200);
                            contents.showText(lstReks.get(count+1).getTargetMember().getNom() + " " + lstReks.get(count+1).getTargetMember().getPrenom());
                            contents.endText();
                        }
                    }
                    
                    contents.close();
                }
                UserServices ff = new UserServices();
                User yy = ff.FindById(User.getIdofconnecteduser());
                doc.save(targetLocation.getAbsolutePath() + "\\Reclamations de "  + yy.getNom() + ".pdf");
            }
        }
        catch(IOException  e)
        {
            e.printStackTrace();
        }
    }
}
