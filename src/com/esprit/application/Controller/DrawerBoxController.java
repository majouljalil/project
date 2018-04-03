/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.application.Controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author GodSlayer
 */
public class DrawerBoxController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
     @FXML
    void eventAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/application/view/mes_evenement_client.fxml"));
 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.setFullScreen(false);
         stage.resizableProperty().set(false);
         stage.show();
    }

    @FXML
    void promotionAction(ActionEvent event) throws IOException {
 Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/application/view/evenement_admin.fxml"));
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
         Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.resizableProperty().set(false);
        stage.show();
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
/*
      @FXML
    void toEvent(ActionEvent event) throws IOException {
 Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/application/view/Evenement.fxml"));
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.setFullScreen(false);
         stage.resizableProperty().set(false);
         stage.show();
    }
*/

