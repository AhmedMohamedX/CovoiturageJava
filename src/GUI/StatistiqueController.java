/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;



import Services.CommentService;
import Services.TemoignageService;
import animation.FadeInDowntransition;
import animation.FadeInUpTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adel
 */
public class StatistiqueController extends Application implements Initializable {
    
    
    @FXML
    private AnchorPane paneadduser;

    @FXML
    private ComboBox<String> CBChoseStat;

    @FXML
    private AnchorPane UserComm;

    @FXML
    private BarChart<String, Integer> barchart;

    @FXML
    private AnchorPane commBar;

    @FXML
    private PieChart pieChart;

    int choix;
    CommentService a = new CommentService();
    TemoignageService t=new TemoignageService();

    public void CommentaireParUser() {

        new FadeInUpTransition(UserComm).play();
        new FadeInDowntransition(commBar).play();
        choix = 0;

        barchart.getData().clear();
        barchart.getData().add(a.StatCommentaireParUser());
        barchart.setLegendVisible(false);
        barchart.setAnimated(true);

    }

    public void StatTemoignageParMembre() {

        new FadeInUpTransition(commBar).play();
        new FadeInDowntransition(UserComm).play();
        choix = 1;
        pieChart.getData().clear();
        pieChart.setData(t.StatTemoignageParMembre());
        pieChart.setAnimated(true);
        pieChart.setVisible(true);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CBChoseStat.getItems().addAll("Commentaire par User", "Temoignage par User");
        CBChoseStat.getSelectionModel().select("Commentaire par User");
        CBChoseStat.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (newValue.equals("Commentaire par User")) {
                        StatTemoignageParMembre();
                    } else {
                        CommentaireParUser();
                    }
                }
            }

        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                barchart.getData().add(a.StatCommentaireParUser());
                barchart.setLegendVisible(false);
                barchart.setAnimated(true);

            }
        });

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Statistique.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
