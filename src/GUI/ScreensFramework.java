/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License"). You
 * may not use this file except in compliance with the License. You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package GUI;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;

/**
 *
 * @author Angie
 */
public class ScreensFramework extends Application {

    public static String screen1ID = "annoncesList";
    public static String screen1File = "AnnoncesList.fxml";
     public static String screen2ID = "ajouterAnnonce";
    public static String screen2File = "AjouterAnnonces.fxml";
     public static String screen3ID = "InterfaceAnnonce";
    public static String screen3File = "InterfaceAnnonce.fxml";
     public static String screen4ID = "sideMenu";
    public static String screen4File = "SidePanelContentController.fxml";
       public static String screen5ID = "recherche";
    public static String screen5File = "RechercheListeAnnonce.fxml";
     public static String screen6ID = "login";
    public static String screen6File = "Login.fxml";
     public static String screen7ID = "GestionReservation";
    public static String screen7File = "GestionReservation.fxml";
     public static String screen8ID = "PayementFxml";
    public static String screen8File = "PayementFxml.fxml";
        public static int annonceId;
      

   

    @Override
    public void start(Stage primaryStage) throws Exception {

        ScreensController mainContainer = new ScreensController();
        

        mainContainer = new ScreensController();
        mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
                mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
                        mainContainer.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
                        mainContainer.loadScreen(ScreensFramework.screen4ID, ScreensFramework.screen4File);
                        mainContainer.loadScreen(ScreensFramework.screen5ID, ScreensFramework.screen5File);

                        mainContainer.loadScreen(ScreensFramework.screen6ID, ScreensFramework.screen6File);
                        mainContainer.loadScreen(ScreensFramework.screen7ID, ScreensFramework.screen7File);
                        mainContainer.loadScreen(ScreensFramework.screen8ID, ScreensFramework.screen8File);



 mainContainer.setScreen(ScreensFramework.screen6ID);
        

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
       // scene.getStylesheets().add(getClass().getResource("/resources/css/jfoenix-fonts.css").toExternalForm());
		//scene.getStylesheets().add(getClass().getResource("../resources/css/jfoenix-design.css").toExternalForm());
		//scene.getStylesheets().add(getClass().getResource("../resources/css/jfoenix-main-demo.css").toExternalForm());

        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            public void handle(WindowEvent e) {
                //primaryStage.close();
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.show();
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
