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
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.prism.impl.Disposer.Record;
import java.io.IOException;


import java.net.URL;
import java.util.Properties;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

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
public class Evenement_publieController implements Initializable {

    Evenement evenement = new Evenement();
    EvenementService evenementService = new EvenementService();
     

  
    @FXML
    private TableView<Evenement> table_event;

    @FXML
    private TableColumn<Evenement, String> c_nom;

    @FXML
    private TableColumn<Evenement, String> c_date;

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
 @FXML
    private Button Button;

     
   public void initialize(URL url, ResourceBundle rb) {
        EvenementService evenementService = new EvenementService();
        c_nom.setCellValueFactory(new PropertyValueFactory<>("nom_evenement"));
        c_theme.setCellValueFactory(new PropertyValueFactory<>("theme_evenement"));
        c_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu_evenement"));
        c_nbr_participant.setCellValueFactory(new PropertyValueFactory<>("nbr_max_participant"));
        c_nbr_max_participant.setCellValueFactory(new PropertyValueFactory<>("nbr_participant"));
        c_date.setCellValueFactory(new PropertyValueFactory<>("date_evenement"));

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

     
        c_validation.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFactory
                =                 //
        (final TableColumn<Evenement, String> param) -> {
            final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {
                
                final Button btn = new Button("detail de cet evenement");
                
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btn.setOnAction((ActionEvent event) -> {
                            Evenement Eve = getTableView().getItems().get(getIndex());
                            
                            
                             detail_evenementController detail_evenementController = new detail_evenementController();
      
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/com/esprit/application/view/detail_evenement.fxml"));
        try {
            fXMLLoader.load();
            Parent parent = fXMLLoader.getRoot();
             detail_evenementController acc = fXMLLoader.getController();
           
               
            acc.setta(Eve.getNom_evenement(),Eve.getTheme_evenement(),Eve.getLieu_evenement(),Eve.getDate_evenement(),Eve.getNbr_participant(),Eve.getNbr_max_participant(),Eve.getDescription_evenement(),Eve.getImage_evenement());
             
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
          
            
        
           
           
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
          
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Evenement_publieController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        });
                        setGraphic(btn);
                        setText(null);
                    }
                }
            };
            return cell;
        };

        c_validation.setCellFactory(cellFactory);
        data.addAll(evenementService.lireEvenement());
        System.out.println(data);
        table_event.setItems(data);
        table_event.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            
        });
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
}
