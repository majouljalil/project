package com.esprit.application.Controller;

import com.esprit.entities.Evenement;
import com.gluonhq.impl.charm.a.b.b.i;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.fxml.Initializable;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * Created by rifat on 8/12/15.
 */
public class detail_evenementController implements Initializable {

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField theme;

    @FXML
    private JFXTextField lieu;

    @FXML
    private JFXTextField nbr_max;

    @FXML
    private DatePicker date;

    @FXML
    private JFXTextArea description;

    @FXML
    private Button btnClose;

    @FXML
    private TextField tf_image_name;
    @FXML
    private JFXButton btn_jeparticipe;

    String pdfToPrint = "";
    @FXML
    private Rectangle rectangle;
    @FXML
    private JFXTextField liii;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*String str = tf_image_name.getText();
        if (str != null) {
            Image image = new Image(tf_image_name.getText());
            rectangle.setFill(new ImagePattern(image));
        } else {*/
       
        /*String name_file = str.substring(29);
        System.out.println(name_file);
        Image image = new Image("C:/Users/djoe/Desktop/MYFTP/"+name_file);
         System.out.println(image);
        rectangle.setFill(new ImagePattern(image));
        // }*/

    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void setta(String nom_evenement, String theme_evenement, String lieu_evenement, String date_evenement, int nbr_participant, int nbr_max_participant, String description_evenement, String image_evenement) {

        nom.setText(nom_evenement);
        theme.setText(theme_evenement);
        lieu.setText(lieu_evenement);
        date.setPromptText(date_evenement);
        nbr_max.setText(String.valueOf(nbr_max_participant));
        tf_image_name.setText(image_evenement);
 String str=tf_image_name.getText();
      
       
       String name_file = str.substring(28);
        System.out.println(name_file);
        Image image = new Image("file:C:/Users/djoe/Desktop/MYFTP/"+name_file);
        rectangle.setFill(new ImagePattern(image));
    }

    @FXML
    private void onjeparticipe(ActionEvent event) throws FileNotFoundException, DocumentException {
        pdfToPrint = "Prix total de la réservation: " + Integer.valueOf(nbr_max.getText()) + " DT" + "\n"
                + "               Nombre de places reservé est: " + nbr_max.getText() + "\n"
                + "               Merci pour votre réservation";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("E:/"+nom.getText()+".pdf"));
        if (pdfToPrint.equalsIgnoreCase("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la génération de reservation");
            alert.setContentText("Vuillez réserver avant de demander la facture!");
            alert.showAndWait();
        } else {
            document.open();
            document.add(new Paragraph("              *****************Réservation***************"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("              " + pdfToPrint));
            document.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION");
            alert.setHeaderText("Génération du billet effectuée");
            alert.showAndWait();
        }

    }

}
