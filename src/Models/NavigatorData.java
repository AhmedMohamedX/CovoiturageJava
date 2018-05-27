/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.time.LocalDate;
import java.util.Date;
import javafx.scene.layout.VBox;


public class NavigatorData {
    private final static NavigatorData instance = new NavigatorData();

    public static NavigatorData getInstance() {
        return instance;
    }        
        private reclamations SelectedReclamation;   
    private Annonce SelectedAnnonce;
    private String vd;
    private String va;
    private LocalDate d;

    public String getVd() {
        return vd;
    }

    public void setVd(String vd) {
        this.vd = vd;
    }

    public String getVa() {
        return va;
    }

    public void setVa(String va) {
        this.va = va;
    }

    public LocalDate getD() {
        return d;
    }

    public void setD(LocalDate d) {
        this.d = d;
    }

     public void setSelectedReclamation(reclamations SelectedReclamation){
        this.SelectedReclamation=SelectedReclamation;
    }
    
    public reclamations getSelectedReclamation() {
        return SelectedReclamation;
    }   

  

   

  

   
    
    public VBox VBox;
    
    public void setSelectedAnnonce(Annonce selectedAnnonce){
        this.SelectedAnnonce=selectedAnnonce;
    }
    
    public Annonce getSelectedAnnonce() {
        return SelectedAnnonce;
    }   


   private voiture Selectedvoiture;

    
    
    public void setSelectedvoiture(voiture selectedvoiture){
        this.Selectedvoiture=selectedvoiture;
    }
    
    public voiture getSelectedvoiture() {
        return Selectedvoiture;
    }       
  
}
