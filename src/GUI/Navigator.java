/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Mortadhafff
 */
public class Navigator {

    public static final String ListAnnonces = "/GUI/ListAnnonces.fxml";    
    public static final String Main = "/GUI/MainFXMLController.fxml";
    public static final String AfficherAnnonce = "/GUI/AfficherAnnonce.fxml";
        public static final String Email = "/GUI/Email.fxml";
          public static final String ajouterannonce = "/GUI/AjouterAnnonces.fxml";
          public static final String modifierannonce = "/GUI/ModifierAnnonce.fxml";
          public static final String authentification = "/GUI/Authentification.fxml";
          public static final String inscription = "/GUI/register.fxml";
          public static final String accueil = "/GUI/Acceuil.fxml";
          public static final String confirmation = "/GUI/ConfirmAccount.fxml";
          public static final String recherche = "/GUI/RechercheAnnonces.fxml";
          public static final String affrech = "/GUI/AnnonceAff.fxml";
          public static final String last = "/GUI/DerniereAnnonces.fxml";
          public static final String chat = "/GUI/Chat.fxml";
           public static final String listeVoiture = "/GUI/ListVoiture.fxml";
           public static final String AjoutVoiture = "/GUI/Ajoutini.fxml";
           public static final String ModifierVoiture = "/GUI/modifiervoiture.fxml";
          public static final String recharge = "/GUI/PayPalfxml.fxml";
          public static final String listRes = "/GUI/ListReservation.fxml";
          public static final String stat = "/GUI/FXMLStatistiques.fxml";
          public static final String ajoutrec = "/GUI/AjouterReclamation.fxml";
          public static final String listrec = "/GUI/ListeReclamation.fxml";
          public static final String Repondre = "/GUI/RepondreFXML.fxml";
          public static final String ListeReclamationAdmin = "/GUI/ListReclamationAdmin.fxml";
    private static MainFXMLController mainControlleur;

    public static void setMainController(MainFXMLController mainControlleur) {
        
        Navigator.mainControlleur = mainControlleur;
    }

    public static void LoadScene(String fxml) {
        try {
            mainControlleur.setAPance(FXMLLoader.load(Navigator.class.getResource(fxml)
            )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
