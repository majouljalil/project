/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.application.Controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class MenuPrincipaleController implements Initializable {

    @FXML
    private AnchorPane acpanel;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       if (!LoginInterfaceController.isSplashLoaded) {
            loadSplashScreen();
        }
    }

    @FXML
    private void AdoptionClick(ActionEvent event) throws IOException {
         acpanel.getChildren().clear();
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource(""));
        acpanel.getChildren().add(newLoadedPane);
    }

    @FXML
    private void BulleClick(ActionEvent event) throws IOException {
        acpanel.getChildren().clear();
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/com/esprit/application/view/mes_evenement_client.fxml"));
        acpanel.getChildren().add(newLoadedPane);
    }

    @FXML
    private void DemandeClick(ActionEvent event) throws IOException {
        acpanel.getChildren().clear();
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/com/esprit/application/view/evenement_publie.fxml"));
        acpanel.getChildren().add(newLoadedPane);
    }

    @FXML
    private void feedbackClick(ActionEvent event) throws IOException {
        
         acpanel.getChildren().clear();
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/com/esprit/application/view/feedback.fxml"));
        acpanel.getChildren().add(newLoadedPane);
       
        
        
        
        
    }

    @FXML
    private void InscriptionClick(ActionEvent event) {
    }



    
    
    void loadSplashScreen() {
        try {
            LoginInterfaceController.isSplashLoaded = true;
            
            StackPane pane = FXMLLoader.load(getClass().getResource("/com/esprit/application/view/SplashFXML.fxml"));
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
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/com/esprit/application/view/MenuPrincipale.fxml")));
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
