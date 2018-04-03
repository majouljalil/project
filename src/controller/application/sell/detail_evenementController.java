/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.sell;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author djoe
 */
public class detail_evenementController implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private ImageView iv_evenement;
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
    private TextField tf_image_name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
    }
    
}
