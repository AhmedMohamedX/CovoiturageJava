/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package covoiturageedt;

import GUI.MainFXMLController;
import GUI.Navigator;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author AH Info
 */
public class CovoiturageEDT extends Application {
    
      @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image("/IMG/logo2.png"));
        stage.setScene(
                createScene(
                        loadMainPane()
                )
        );

        stage.show();
    }

    private Pane loadMainPane() throws IOException {
        
        FXMLLoader loader = new FXMLLoader();           
        Pane mainPane = (Pane) loader.load(
                getClass().getResourceAsStream(
                        Navigator.Main
                )
        );
        MainFXMLController mainController = loader.getController();

        Navigator.setMainController(mainController);
        Navigator.LoadScene(Navigator.authentification);
        

        return mainPane;
    }

    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
                mainPane
        );
        return scene;
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
