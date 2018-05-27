/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import Models.Annonce;
import Models.Reservations;

/**
 *
 * @author Hard-System-Info
 */
public interface IReservations {

    

    public void add(Annonce a, int userID);

    public ArrayList<Reservations> afficheres(int userID);

    public void remove(Reservations ress, int userID);

    public String getEmailChauffeur(Annonce an);

    public int getPlaces(Annonce an, Reservations ress);
    
    public void getStatistiques(Reservations rs,int userID);
    
   

}
