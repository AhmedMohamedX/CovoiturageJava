package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.controlsfx.control.Notifications;
import Models.NavigatorData;
import Models.User;
import Models.reclamations;
import Services.ReclamationService;
import Services.UserServices;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Mehdi9951
 */
public class ListeReclamationController implements Initializable{

    @FXML
    private TableView<reclamations> tabp;
    @FXML
    private TableColumn<reclamations, String> sujet;
    @FXML
    private TableColumn<reclamations, Date> date;
    @FXML
    private TableColumn<reclamations, String> message;
    @FXML
    private TableColumn<reclamations, String> reponse;
    @FXML
    private Rectangle pane;
    @FXML
    private TableColumn<reclamations, String> tcDetail;
    @FXML
    private Button supprimer;
    @FXML
    private Button btnExportPDF;
    
    @FXML
    private Button AJOUTERBUTTON;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        acualiser();
        
        btnExportPDF.setOnAction(e -> {
            exportDataToPDF();
        });
    }
    @FXML
    public void supprimer()
    {
       
        ReclamationService s=new ReclamationService();
        Integer recID=tabp.getSelectionModel().getSelectedItem().getId();
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("etes vous sur de vouloir supprimer ");
        alert.showAndWait();
        if (alert.getResult().getText().equals("OK")) {
            s.delete(recID);
        acualiser();
        
        String title = "Suppression Reussi";
        String message = "Suppression effectuée avec Succes";
        
        TrayNotification tray = new TrayNotification();
        
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.NOTICE);
        tray.showAndDismiss(Duration.seconds(5));
        }
    }
      
      @FXML
      private void ajouter(ActionEvent event) throws IOException {
        Navigator.LoadScene(Navigator.ajoutrec);
    }
  
      public void acualiser(){
          ReclamationService a = new ReclamationService();
          for (reclamations reccc : a.getAllbymember()) {
               if (reccc.getVu() == 1) {
                    String title = "Notfication Reclamation";
        String message = "l'administrateur a repondu a votre reclamation";
        
        TrayNotification tray = new TrayNotification();
        
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setAnimationType(AnimationType.SLIDE);
        tray.setNotificationType(NotificationType.NOTICE);
        tray.showAndDismiss(Duration.seconds(5));   
        a.modifier1(reccc);
              }
            }
     
           supprimer.getStyleClass().add("button-raised");
    
    btnExportPDF.getStyleClass().add("button-raised");
   
    
   
     AJOUTERBUTTON.getStyleClass().add("button-raised");
    
     
        
        
           
        
          tabp.getItems().clear();
          
          
        List <reclamations> ls1= a.getAll();
          // int id = Pi_User.getConnectedUser();
           int id=User.getIdofconnecteduser();
           List<reclamations> Liste2 = ls1.stream().filter(x->x.getMembre_id()==id).collect(Collectors.toList());
           ObservableList<reclamations> ls=FXCollections.observableArrayList(Liste2);
            sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            message.setCellValueFactory(new PropertyValueFactory<>("message"));
            reponse.setCellValueFactory(new PropertyValueFactory<>("reponse"));
            
            tcDetail.setCellValueFactory(c-> new SimpleStringProperty( c.getValue().getTargetMember().getPrenom()));

            for (Object row : tabp.getItems()) {
                for (TableColumn column : tabp.getColumns()) {
                    
                }
            }

            
//        tabp.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
//      ObservableList<reclamations>   selecteditem,allitems;
//      allitems = tabp.getItems();
//       selecteditem = tabp.getSelectionModel().getSelectedItems();
//       
//      // description.setText(selecteditem.get(0).getDescription());
//      
//        if (newSelection != null) {
//        tabp.getSelectionModel().clearSelection();;
//    }
//    });
       

        tabp.setItems(ls);

//       forceRefresh();
    
    }

     public void forceRefresh() {
        final TableColumn< reclamations, ?> firstColumn = tabp.getColumns().get(0);
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

    private void exportDataToPDF() {
        
        try
        {
            // prompting file chooser to get target location
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File targetLocation  = directoryChooser.showDialog(btnExportPDF.getScene().getWindow());
            
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
                doc.save(targetLocation.getAbsolutePath() + "\\Reclamations de "  + yy.getNom()+ ".pdf");
            }
        }
        catch(IOException  e)
        {
            e.printStackTrace();
        }
    }
}
