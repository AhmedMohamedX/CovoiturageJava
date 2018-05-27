/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author Hard-System-Info
 */
public class Reservations1 {
    private int id;
    private int  annonce;
    private String chauffeur;
    private String passager1;
    private String passager2;
    private String passager3;
    private String passager4;
    private Date DateReservation;
    private int nbplace;

    public Reservations1(int annonce, String chauffeur, String passager1, String passager2, String passager3, String passager4, Date DateReservation) {
        this.annonce = annonce;
        this.chauffeur = chauffeur;
        this.passager1 = passager1;
        this.passager2 = passager2;
        this.passager3 = passager3;
        this.passager4 = passager4;
        this.DateReservation = DateReservation;
    }

    public Reservations1() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnnonce() {
        return annonce;
    }

    public void setAnnonce(int annonce) {
        this.annonce = annonce;
    }

    public String getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(String chauffeur) {
        this.chauffeur = chauffeur;
    }

    public String getPassager1() {
        return passager1;
    }

    public void setPassager1(String passager1) {
        this.passager1 = passager1;
    }

    public String getPassager2() {
        return passager2;
    }

    public void setPassager2(String passager2) {
        this.passager2 = passager2;
    }

    public String getPassager3() {
        return passager3;
    }

    public void setPassager3(String passager3) {
        this.passager3 = passager3;
    }

    public String getPassager4() {
        return passager4;
    }

    public void setPassager4(String passager4) {
        this.passager4 = passager4;
    }

    public Date getDateReservation() {
        return DateReservation;
    }

    public void setDateReservation(Date DateReservation) {
        this.DateReservation = DateReservation;
    }



    public int getNbplace() {
        return nbplace;
    }

    public void setNbplace(int nbplace) {
        this.nbplace = nbplace;
    }
    
    
    
    
}
