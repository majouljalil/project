/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.application.Controller;

import com.esprit.entities.Evenement;
import com.esprit.entities.user_evenement;
import com.esprit.services.EvenementService;
import com.esprit.services.User_evenementService;
import java.io.IOException;


import java.net.URL;
import java.util.Properties;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
/**
 * FXML Controller class
 *
 * @author djoe
 */

public class Evenement_adminController implements Initializable {

    Evenement evenement = new Evenement();
    EvenementService evenementService = new EvenementService();
    @FXML
    private TableView<Evenement> table_event;
    public AnchorPane root;
    @FXML
    private TableColumn<Evenement, String> c_nom;

    @FXML
    private TableColumn<Evenement, String> c_date;

    @FXML
    private TableColumn<Evenement, String> c_description;

    @FXML
    private TableColumn<Evenement, String> c_theme;
    @FXML
    private TableColumn<Evenement, String> c_lieu;
    @FXML
    private TableColumn<Evenement, Integer> c_nbr_max_participant;
    @FXML
    private TableColumn<Evenement, Integer> c_nbr_participant;
   
    @FXML
    private TableColumn<Evenement, Image> c_lien;
    
    
    @FXML
    private TableColumn<Evenement, String> c_validation;
        ObservableList<Evenement> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
if (!LoginInterfaceController.isSplashLoaded) {
            loadSplashScreen();
        }
        EvenementService evenementService = new EvenementService();
        c_nom.setCellValueFactory(new PropertyValueFactory<>("nom_evenement"));
        c_theme.setCellValueFactory(new PropertyValueFactory<>("theme_evenement"));
        c_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu_evenement"));
        c_nbr_participant.setCellValueFactory(new PropertyValueFactory<>("nbr_max_participant"));
        c_nbr_max_participant.setCellValueFactory(new PropertyValueFactory<>("nbr_participant"));
        c_date.setCellValueFactory(new PropertyValueFactory<>("date_evenement"));
        c_validation.setCellValueFactory(new PropertyValueFactory<>("validation"));
        c_lien.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Evenement, Image>, ObservableValue<Image>>() {

            public ObservableValue<Image> call(TableColumn.CellDataFeatures<Evenement, Image> p) {
                System.out.println(p.getValue().getImage_evenement());
                return new SimpleObjectProperty<>(new Image("file:" + p.getValue().getImage_evenement(), 100, 100, true, true, true));
            }
        });
        c_lien.setCellFactory(new Callback<TableColumn<Evenement, Image>, TableCell<Evenement, Image>>() {

            public TableCell<Evenement, Image> call(TableColumn<Evenement, Image> p) {
                return new TableCell<Evenement, Image>() {

                    @Override
                    protected void updateItem(Image i, boolean empty) {
                        super.updateItem(i, empty);
                        setText(null);
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        ImageView imageView = (i == null || empty) ? null : ImageViewBuilder.create().image(i).build();
                        setGraphic(imageView);
                    }
                };
            }
        });

            c_description.setCellValueFactory(new PropertyValueFactory<>("description_evenement"));
            data.addAll(evenementService.lireEvenementenattente());
            System.out.println(data);
            table_event.setItems(data);
            table_event.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
          
            evenement.setId(table_event.getSelectionModel().getSelectedItem().getId());
            evenement.setNom_evenement(table_event.getSelectionModel().getSelectedItem().getNom_evenement());
            evenement.setTheme_evenement(table_event.getSelectionModel().getSelectedItem().getTheme_evenement());
            evenement.setLieu_evenement(table_event.getSelectionModel().getSelectedItem().getLieu_evenement());
            evenement.setDate_evenement(table_event.getSelectionModel().getSelectedItem().getDate_evenement());

            evenement.setNbr_max_participant(table_event.getSelectionModel().getSelectedItem().getNbr_max_participant());
           

            evenement.setDescription_evenement(table_event.getSelectionModel().getSelectedItem().getDescription_evenement());
            evenement.setValidation(table_event.getSelectionModel().getSelectedItem().getValidation());
            evenement.setImage_evenement(table_event.getSelectionModel().getSelectedItem().getImage_evenement());
        });
    }

    @FXML
    void refuserEvent(ActionEvent event) {
        evenement.setValidation("refuser");
        evenementService.modifierEvenement(evenement);
        ObservableList<Evenement> tmp = FXCollections.observableArrayList();
        tmp.addAll(evenementService.lireEvenementenattente());
        table_event.setItems(tmp);
        
        
        user_evenement user = new user_evenement();
        User_evenementService urs = new User_evenementService();
         user= urs.finduser(4);
        final String mail = user.getMail();
         final String MDP = user.getMDP();
        
        System.out.println(mail);
         System.out.println(MDP);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
       
       Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, MDP);
            }
        });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("djoedon12@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("djoedon12@gmail.com"));
            message.setSubject("REFUS");
           
                message.setText("Bonjour Monsieur  votre evenement ne peut pas etre publie \n Cordialement");
        

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
    }

    @FXML
    void validerEvent(ActionEvent event) {
        evenement.setValidation("valider");
        evenementService.modifierEvenement(evenement);
        ObservableList<Evenement> tmp = FXCollections.observableArrayList();
        tmp.addAll(evenementService.lireEvenementenattente());
        table_event.setItems(tmp);
        
        user_evenement user = new user_evenement();
        User_evenementService urs = new User_evenementService();
         user= urs.finduser(4);
        final String mail = user.getMail();
        final String MDP = user.getMDP();
System.out.println(mail);
         System.out.println(MDP);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail,MDP);
            }
            
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("djoedon12@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("djoedon12@gmail.com"));
            message.setSubject("Evenement publié");
           
                message.setText("Bonjour Monsieur votre evenement vient d'etre publié  \n Cordialement");
        

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/application/view/LoginInterface.fxml"));
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
     
         Scene scene = new Scene(root);
 

        stage.setScene(scene);
    
        stage.setFullScreen(false);
        stage.resizableProperty().set(false);
        stage.show();
    }
    void loadSplashScreen() {
        try {
            LoginInterfaceController.isSplashLoaded = true;
            
            StackPane pane = FXMLLoader.load(getClass().getResource(("/com/esprit/application/view/SplashFXML.fxml")));
        
            
             pane.setMinWidth(500);
   pane.setMinWidth(500);

            
            root.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/com/esprit/application/view/evenement_admin.fxml")));
                    root.getChildren().setAll(parentContent);
                    
        
 

        

                } catch (IOException ex) {
                    Logger.getLogger(Evenement_adminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(Evenement_adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}
