/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * https://www.google.com/settings/security/lesssecureapps
 */
package Services;

import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import Iservices.IReservations;
import Technique.MyConnection;
import Models.Annonce;
import Models.Reservations;
import java.util.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import Models.User;

/**
 *
 * @author Hard-System-Info
 */
public class ReservationServices implements IReservations {

    Connection con;
    @FXML
    private BarChart<String, Integer> barchart;

    // public static int userID = 0;
    public ReservationServices() {
        con = MyConnection.getInstance().getConnection();
    }



    @Override
    public void add(Annonce a, int userID) {

        String query1 = "select id from membre where id='" + a.getMembre_id().getId()+ "' ";
        PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) con.prepareStatement(query1);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = null;
        try {
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (rs.next()) {
                System.out.println(a);

                String reqres = "SELECT  * from reservations r WHERE r.annonce_id=" + a.getId() + " ";

                PreparedStatement pst2 = (PreparedStatement) con.prepareStatement(reqres);
                ResultSet rs2 = pst2.executeQuery();
                System.out.println("select ok");

                if (rs2.next()) {
                    // System.err.println(rs2.getInt("annonce_id"));
                    int aa = rs2.getInt(4);
                    if (aa != 0) {
                        int ab = rs2.getInt(5);
                        int abz = rs2.getInt(7);
                        int an1 = a.getId();
                        if (ab == 0 && abz==0) {

                            String req = "Update  reservations set passager2_id=" + userID + ",date_reservation='" + new Date(Calendar.getInstance().getTime().getTime())+ "'  where annonce_id=" + an1 + "";
                            try {
                                Statement stm = con.createStatement();
                                stm.executeUpdate(req);
                                System.out.println("modif 2 ok");
                            } catch (SQLException ex) {
                                Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vous etes le 2ème passager ", ButtonType.OK);
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            alert.setHeaderText("Reservation éffectué avec succées!");
                            alert.show();
                        }
                        if (a.getNb_places()!= 0) {
                            int nbplc2 = a.getNb_places()- 1;

                            String req4 = "Update  annonce set nb_places=" + nbplc2 + " WHERE id=" + a.getId() + " ";
                            try {
                                Statement stm3 = con.createStatement();
                                stm3.executeUpdate(req4);

                            } catch (SQLException ex) {
                                Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Reservation non effectuée,Nombre de place Complet!!!", ButtonType.OK);
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            alert.show();
                        }

                        int abc = rs2.getInt(6);
                        if (abc == 0 && ab != 0) {
                            String req = "Update  reservations set passager3_id=" + userID + ",date_reservation='" + new Date(Calendar.getInstance().getTime().getTime())+ "'  where annonce_id=" + an1 + "";
                            try {
                                Statement stm = con.createStatement();
                                stm.executeUpdate(req);
                                System.out.println("modif 3 ok");
                            } catch (SQLException ex) {
                                Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vous etes le 3ème passager ", ButtonType.OK);
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            alert.setHeaderText("Reservation éffectué avec succées!");
                            alert.show();
                        }
                        if (a.getNb_places()!= 0) {
                            int nbplc1 = a.getNb_places()- 1;

                            String req4 = "Update  annonce set nb_places=" + nbplc1 + " WHERE id=" + a.getId() + " ";
                            try {
                                Statement stm3 = con.createStatement();
                                stm3.executeUpdate(req4);

                            } catch (SQLException ex) {
                                Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Reservation non effectuée,Nombre de place Complet!!!", ButtonType.OK);
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            alert.show();
                        }

                        int abcd = rs2.getInt(7);
                        if (abcd == 0 && abc != 0 && ab!=0) {

                            String req = "Update  reservations set passager4_id=" + userID + ",date_reservation='" + new Date(Calendar.getInstance().getTime().getTime())+ "'  where annonce_id=" + an1 + "";
                            try {
                                Statement stm = con.createStatement();
                                stm.executeUpdate(req);
                                System.out.println("modif 4 ok");
                            } catch (SQLException ex) {
                                Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vous etes le 4ieme passager ", ButtonType.OK);
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            alert.setHeaderText("Reservation éffectué avec succées!");
                            alert.show();
                        }
                        if (a.getNb_places()!= 0) {
                            int nbplc = a.getNb_places()- 1;

                            String req4 = "Update  annonce set nb_places=" + nbplc + " WHERE id=" + a.getId() + " ";
                            try {
                                Statement stm3 = con.createStatement();
                                stm3.executeUpdate(req4);

                            } catch (SQLException ex) {
                                Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Reservation non effectuée,Nombre de place Complet!!!", ButtonType.OK);
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            alert.show();

                        }
                    } else {

                        int aa5 = rs2.getInt(4);
                        int aa1 = rs2.getInt(5);
                        int ab = rs2.getInt(6);
                        int ac = rs2.getInt(7);
                        if (aa5 == 0 && (aa1 != 0 || ab != 0 || ac != 0)) {

                            String req15 = "Update  reservations set passager1_id=" + userID + ",nbplace=" + a.getNb_places()+ ",date_reservation='" + new Date(Calendar.getInstance().getTime().getTime())+ "' where annonce_id=" + a.getId() + "";
                            try {
                                Statement stm = con.createStatement();
                                stm.executeUpdate(req15);
                                System.out.println("modif 1 ok");
                            } catch (SQLException ex) {
                                Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                        int nbplc = a.getNb_places()- 1;

                        String req4 = "Update  annonce set nb_places=" + nbplc + " WHERE id=" + a.getId() + " ";
                        try {
                            Statement stm3 = con.createStatement();
                            stm3.executeUpdate(req4);

                        } catch (SQLException ex) {
                            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {

                    Date dd = new Date(Calendar.getInstance().getTime().getTime());

                    String req = "insert into reservations (annonce_id,chauffeur_id,passager1_id,date_reservation) values (" + a.getId() + "," + rs.getInt("id") + "," + userID + ",'" + dd + "')";

                    try {
                        Statement stm = con.createStatement();
                        stm.executeUpdate(req);
                        System.out.println("ajout ok");
                    } catch (SQLException ex) {
                        Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (a.getNb_places()!= 0) {
                        int nbplc = a.getNb_places()- 1;

                        String req4 = "Update  annonce set nb_places=" + nbplc + " WHERE id=" + a.getId() + " ";
                        try {
                            Statement stm3 = con.createStatement();
                            stm3.executeUpdate(req4);

                        } catch (SQLException ex) {
                            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        /*
                        if (a.getDateDepart().compareTo(dd) < 0) {
                            System.err.println("Date départ est inférieur à la date d'aujourd'hui ");
                        } else {
                            System.err.println("aaaaaa44444");
                        }
                         */
                    }

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ArrayList<Reservations> afficheres(int userID) {
        ArrayList<Reservations> reservations = new ArrayList<>();
        try {

            String req = "SELECT r.id,m0.username,m.username,m2.username,m3.username,m4.username,r.date_reservation from reservations r, membre m,membre m2,membre m3,membre m4,membre m0 where (m.id=" + userID + "   or m2.id=" + userID + "   or m3.id=" + userID + "  or m4.id= " + userID + ")   and r.chauffeur_id=m0.id and r.passager1_id=m.id and r.passager2_id=m2.id and r.passager3_id=m3.id and r.passager4_id=m4.id  ";
                System.out.println(req);
            Statement stm5 = con.createStatement();

            ResultSet result = stm5.executeQuery(req);

            while (result.next()) {

                Reservations res = new Reservations();
                res.setId(result.getInt(1));
                res.setChauffeur(result.getString(2));
                res.setPassager1(result.getString(3));
                res.setPassager2(result.getString(4));
                res.setPassager3(result.getString(5));
                res.setPassager4(result.getString(6));
                res.setDateReservation(result.getDate(7));
                reservations.add(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservations;
    }

    @Override
    public void remove(Reservations ress, int userID) {

        try {
            String query1 = "select id from Membre where   username='" + ress.getPassager1() + "' ";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(query1);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getInt(1));
                int r1 = rs.getInt(1);
                if (r1 == userID) {

                    String req = "Update  reservations set passager1_id = NULL   where id=" + ress.getId() + "";
                    try {
                        Statement stm = con.createStatement();
                        stm.executeUpdate(req);
                        System.out.println("supp1  ok");
                    } catch (SQLException ex) {
                        Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String query2 = "select id from Membre where   username='" + ress.getPassager2() + "' ";
            PreparedStatement pst2 = (PreparedStatement) con.prepareStatement(query2);
            ResultSet rs2 = pst2.executeQuery();

            if (rs2.next()) {
                System.out.println(rs2.getInt(1));
                int r2 = rs2.getInt(1);
                if (r2 == userID) {

                    String req = "Update  reservations set passager2_id = NULL   where id=" + ress.getId() + "";
                    try {
                        Statement stm = con.createStatement();
                        stm.executeUpdate(req);
                        System.out.println("supp2  ok");
                    } catch (SQLException ex) {
                        Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String query3 = "select id from Membre where   username='" + ress.getPassager3() + "' ";
            PreparedStatement pst3 = (PreparedStatement) con.prepareStatement(query3);
            ResultSet rs3 = pst3.executeQuery();

            if (rs3.next()) {
                System.out.println(rs3.getInt(1));
                int r3 = rs3.getInt(1);
                if (r3 == userID) {

                    String req = "Update  reservations set passager3_id = NULL   where id=" + ress.getId() + "";
                    try {
                        Statement stm = con.createStatement();
                        stm.executeUpdate(req);
                        System.out.println("supp3  ok");
                    } catch (SQLException ex) {
                        Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String query4 = "select id from Membre where   username='" + ress.getPassager4() + "' ";
            PreparedStatement pst4 = (PreparedStatement) con.prepareStatement(query4);
            ResultSet rs4 = pst4.executeQuery();

            if (rs4.next()) {

                int r4 = rs4.getInt(1);
                if (r4 == userID) {

                    String req = "Update  reservations set passager4_id = NULL   where id=" + ress.getId() + "";
                    try {
                        Statement stm = con.createStatement();
                        stm.executeUpdate(req);
                        System.out.println("supp4  ok");
                    } catch (SQLException ex) {
                        Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getEmailChauffeur(Annonce an) {
        String mail = "";
        try {
            String query5 = "SELECT m.email  FROM membre m , annonce a WHERE a.membre_id=m.id and a.id=" + an.getId() + "";
            PreparedStatement pst5 = (PreparedStatement) con.prepareStatement(query5);
            ResultSet rs5 = pst5.executeQuery();

            if (rs5.next()) {
                mail = rs5.getString(1);
                System.err.println(rs5.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mail;
    }

    @Override
    public int getPlaces(Annonce an, Reservations ress) {
        int nbv = 0;
        int annonce = 0;
        try {
            String query6 = "SELECT  annonce_id,4-( COALESCE(COUNT(passager1_id),null) + COALESCE(COUNT(passager2_id),null) +COALESCE(COUNT(passager3_id),null) +COALESCE(COUNT(passager4_id),null)) "
                    + "   FROM reservations\n"
                    + "   WHERE  id=" + ress.getId() + "";
            PreparedStatement pst6 = (PreparedStatement) con.prepareStatement(query6);
            ResultSet rs6 = pst6.executeQuery();

            if (rs6.next()) {
                nbv = rs6.getInt(2);
                annonce = rs6.getInt(1);
                System.out.println(rs6.getInt(1));

                String req = "Update  annonce  set nb_places = " + nbv + "   where id=" + annonce;
                try {
                    Statement stm = con.createStatement();
                    stm.executeUpdate(req);
                    System.out.println("modification de nombre de places  ok");
                } catch (SQLException ex) {
                    Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.err.println(nbv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nbv;
    }

    @Override
    public void getStatistiques(Reservations rs, int userID) {
        
                     String req = "SELECT date_reservation,count( date_reservation)  from reservations r where  (r.passager1_id= " + userID + "    or r.passager2_id= " + userID + "    or r.passager3_id=" + userID + " or r.passager4_id=" + userID + ")  GROUP by date_reservation";
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        try {
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(req);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                series.getData().add(new XYChart.Data<>(rst.getString(1), rst.getInt(2)));
            }
            barchart.getData().add(series);

        } catch (SQLException ex) {
            Logger.getLogger(ReservationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
