/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import Technique.MyConnection;
import Models.Reservations;
import Models.User;
import Services.ReservationServices;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

/**
 * FXML Controller class
 *
 * @author Hard-System-Info
 */
public class FXMLStatistiquesController implements Initializable {

    @FXML
    private Label lb;

    Connection con;
    public static int userID;
    public Reservations rv;
    @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    private Button pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLStatistiquesController.userID = User.getIdofconnecteduser();
        lb.setText(String.valueOf(userID));
        loadchart();
    }

    public FXMLStatistiquesController() {

        con = MyConnection.getInstance().getConnection();
    }

    private void loadchart() {
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

    @FXML
    private void onload(ActionEvent event) {
        try {
            WritableImage image = barchart.snapshot(new SnapshotParameters(), null);

            // TODO: probably use a file chooser here
            File file = new File("chart.png");

            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);

            OutputStream file2 = new FileOutputStream(new File("C:pdffile.pdf"));

            Document document = new Document();

            PdfWriter.getInstance(document, file2);

            document.open();

            Image img = Image.getInstance("chart.png");

            document.add(img);

            document.add(new Paragraph(new Date().toString()));

            document.close();

            file2.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

}
