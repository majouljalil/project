/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.application.Controller;

import com.esprit.entities.user_evenement;
import com.esprit.services.User_evenementService;
import com.jfoenix.controls.JFXTextField;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author GodSlayer
 */
public class LoginInterfaceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
        public static Boolean isSplashLoaded = false;

      @FXML
    private TextField if_username;

    @FXML
    private PasswordField tf_password;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    user_evenement user=new user_evenement() ;
@FXML
    void logIn(ActionEvent event) throws IOException {
        String login=if_username.getText();
        String password=tf_password.getText();
        User_evenementService a =new User_evenementService();
        user_evenement user=new user_evenement() ;
         user= a.rechercheuser(login,password);
         int iduser=user.getId();
         if (user != null){
             if  (user.getRole().equalsIgnoreCase("admin"))  {
             
             Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/application/view/evenement_admin.fxml"));
             GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
int width = gd.getDisplayMode().getWidth();
int height = gd.getDisplayMode().getHeight();
         Scene scene = new Scene(root,width,height);
             
             
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
     
       
 

        stage.setScene(scene);
    
       stage.centerOnScreen();
             
         } else {
               
            Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/application/view/MenuPrincipale.fxml"));
            
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
     GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
int width = gd.getDisplayMode().getWidth();
int height = gd.getDisplayMode().getHeight();
         Scene scene = new Scene(root,width,height);
 

        stage.setScene(scene);
    stage.centerOnScreen();

            
         
        
        }}
            else{
                 Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/application/view/LoginInterface.fxml"));
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
     
         Scene scene = new Scene(root);
 

        stage.setScene(scene);
    
        stage.setFullScreen(false);
        stage.resizableProperty().set(false);
        stage.show();
        
        
            }
    }}    
    

