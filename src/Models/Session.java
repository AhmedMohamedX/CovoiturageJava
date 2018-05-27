/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author RYAN
 */
public class Session {
    
    
   protected  static int Id_Adherent_Actuel ;
   protected static int Id_Annonce_Actuel;
   
   

    public static int getId_Annonce_Actuel() {
        return Id_Annonce_Actuel;
    }

    public static void setId_Annonce_Actuel(int Id_Annonce_Actuel) {
        Session.Id_Annonce_Actuel = Id_Annonce_Actuel;
    }

    public static int getId_Adherent_Actuel() {
        return Id_Adherent_Actuel;
    }

    public static void setId_Adherent_Actuel(int Id_Adherent_Actuel) {
        Session.Id_Adherent_Actuel = Id_Adherent_Actuel;
    }
   
    
}
