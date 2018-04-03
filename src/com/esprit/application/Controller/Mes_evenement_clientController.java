/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.application.Controller;

import com.esprit.application.DataStorage.FTPUploadFile;
import com.esprit.entities.Evenement;
import com.esprit.services.EvenementService;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import static java.util.Optional.empty;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import javafx.scene.image.ImageViewBuilder;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author GodSlayer
 */
public class Mes_evenement_clientController implements Initializable {

    FTPUploadFile F = new FTPUploadFile();
    FileChooser fileChooser = new FileChooser();
    File file;
    @FXML
    private JFXTextField nom_evenement;

    @FXML
    private JFXTextField theme_evenement;

    @FXML
    private JFXTextField lieu_evenement;

    @FXML
    private DatePicker date_evenement;

    @FXML
    private JFXTextArea description_evenement;

    @FXML
    private JFXTextField nbr_max_participant;

    @FXML
    private TableView<Evenement> table_event;

    @FXML
    private TableColumn<Evenement, String> c_nom;

    @FXML
    private TableColumn<Evenement, String> c_theme;

    @FXML
    private TableColumn<Evenement, String> c_lieu;

    @FXML
    private TableColumn<Evenement, Integer> c_nbr_max_participant;

    @FXML
    private TableColumn<Evenement, Integer> c_nbr_participant;

    @FXML
    private TableColumn<Evenement, String> c_date;

    @FXML
    private TableColumn<Evenement, String> c_description;

    ObservableList<Evenement> data = FXCollections.observableArrayList();
    @FXML
    private JFXButton bt_ajouter;
    @FXML
    private JFXButton bt_supprimer;
    @FXML
    private JFXButton bt_modfiier;
    @FXML
    private JFXButton bt_choose_image;
    @FXML
    private ImageView iv_evenement;
    @FXML
    private TableColumn<Evenement, Image> c_lien;
    @FXML
    private TextField tf_image_name;
    @FXML
    private TableColumn<Evenement, String> c_validation;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        data.addAll(evenementService.lireEvenement());
        System.out.println(data);
        table_event.setItems(data);
        table_event.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            nom_evenement.clear();
            theme_evenement.clear();
            lieu_evenement.clear();
            nbr_max_participant.clear();
            date_evenement.setPromptText("");
            description_evenement.clear();
            nom_evenement.setText(table_event.getSelectionModel().getSelectedItem().getNom_evenement());
            theme_evenement.setText(table_event.getSelectionModel().getSelectedItem().getTheme_evenement());
            lieu_evenement.setText(table_event.getSelectionModel().getSelectedItem().getLieu_evenement());
            date_evenement.setPromptText(table_event.getSelectionModel().getSelectedItem().getDate_evenement());

            nbr_max_participant.setText(String.valueOf(table_event.getSelectionModel().getSelectedItem().getNbr_max_participant()));
            tf_image_name.setText(table_event.getSelectionModel().getSelectedItem().getImage_evenement());

            description_evenement.setText(table_event.getSelectionModel().getSelectedItem().getDescription_evenement());

        });
    }

    @FXML
    private void addEvent(ActionEvent event) {

        EvenementService evenementService = new EvenementService();

        if (nom_evenement.getText().equalsIgnoreCase("") || description_evenement.getText().equalsIgnoreCase("")
                || theme_evenement.getText().equalsIgnoreCase("") || lieu_evenement.getText().equalsIgnoreCase("")
                || tf_image_name.getText().equalsIgnoreCase("")
                || date_evenement.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equalsIgnoreCase("")
                || nbr_max_participant.getText().equalsIgnoreCase("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de l'ajout d'un evènement");
            alert.setContentText("Vérifiez vos infomartions!");
            alert.showAndWait();
        } else if (date_evenement.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de l'ajout d'un evènement");
            alert.setContentText("la date ne doit pas être null!");
            alert.showAndWait();
        } else {
            String Nom_evenement = nom_evenement.getText();
            String Theme_evenement = theme_evenement.getText();
            String Lieu_evenement = lieu_evenement.getText();
            String Description_evenement = description_evenement.getText();
            String Date_evenement = date_evenement.getPromptText();
            int Nbr_max_participant = Integer.parseInt(nbr_max_participant.getText());
            int Nbr_participant = 0;

            Evenement evenement = new Evenement();
            evenement.setNom_evenement(Nom_evenement);
            evenement.setTheme_evenement(Theme_evenement);
            evenement.setLieu_evenement(Lieu_evenement);
            evenement.setValidation("En attente");
            evenement.setDate_evenement(Date_evenement);
            evenement.setNbr_max_participant(Nbr_max_participant);

            evenement.setNbr_participant(0);
            evenement.setImage_evenement("C:/Users/djoe/Desktop/MYFTP/" + file.getName());
            System.out.println(file.getPath());
            System.out.println("TEST AJOUT IMAGE :!!!");
            F.envoyerficher(file);
            evenement.setDescription_evenement(Description_evenement);

            evenementService.ajouterEvenement(evenement);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succés");
            alert.setHeaderText("Evenement ajouter avec succés");
            alert.showAndWait();
            ObservableList<Evenement> tmp = FXCollections.observableArrayList();
            tmp.addAll(evenementService.lireEvenement());
            table_event.setItems(tmp);
        }

    }

    @FXML
    void pickImage(ActionEvent event) {
        file = fileChooser.showOpenDialog(null);
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        try {
            // BufferedImage bufferedImage = ImageIO.read(file);
            //Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            //image = new Image(file.toURI().toString());

            Image image_evenement = SwingFXUtils.toFXImage(ImageIO.read(file), null);
            //image.setImage(img);
            tf_image_name.setText(file.getPath());
            iv_evenement.setImage(image_evenement);
            iv_evenement.setFitWidth(100);
            iv_evenement.setPreserveRatio(true);
            iv_evenement.setSmooth(true);
            iv_evenement.setCache(true);
        } catch (IOException ex) {
            Logger.getLogger(Mes_evenement_clientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void deleteEvent(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("attention,  Confirmation de suppression");
        alert.setContentText("Etes vous sur de vouloir supprimer cet evenement?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            EvenementService evenementService = new EvenementService();
            Evenement E = table_event.getSelectionModel().getSelectedItem();
            int id = E.getId();
            evenementService.supprimerEvenement(id);

            ObservableList<Evenement> tmp = FXCollections.observableArrayList();
            tmp.addAll(evenementService.lireEvenement());
            table_event.setItems(tmp);
            nom_evenement.clear();
            theme_evenement.clear();
            lieu_evenement.clear();
            description_evenement.clear();
            nbr_max_participant.clear();
            tf_image_name.clear();
            iv_evenement.setImage(null);
            date_evenement.setPromptText("");
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    @FXML
    void updateEvent(ActionEvent event) {
        if (nom_evenement.getText().equalsIgnoreCase("") || description_evenement.getText().equalsIgnoreCase("")
                || theme_evenement.getText().equalsIgnoreCase("") || lieu_evenement.getText().equalsIgnoreCase("")
                || tf_image_name.getText().equalsIgnoreCase("")
                 || date_evenement.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equalsIgnoreCase("")
                || nbr_max_participant.getText().equalsIgnoreCase("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de l'ajout d'un evènement");
            alert.setContentText("Vérifiez vos infomartions!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("modification");
            alert.setHeaderText("attention,  Confirmation de modification");
            alert.setContentText("Etes vous sur de vouloir modifier cet evenement??");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                EvenementService evenementService = new EvenementService();

                Evenement evenement = new Evenement();
                Evenement E = table_event.getSelectionModel().getSelectedItem();
                int id = E.getId();
                evenement.setId(id);

                evenement.setNom_evenement(nom_evenement.getText());

                evenement.setDescription_evenement(description_evenement.getText());
                evenement.setTheme_evenement(theme_evenement.getText());
                evenement.setLieu_evenement(lieu_evenement.getText());
                evenement.setNbr_max_participant(Integer.valueOf(nbr_max_participant.getText()));
                evenement.setNbr_participant(table_event.getSelectionModel().getSelectedItem().getNbr_participant());
                evenement.setDate_evenement(date_evenement.getPromptText());
evenement.setValidation("En attente");
                if ((tf_image_name.getText()) == null ? table_event.getSelectionModel().getSelectedItem().getImage_evenement() == null : (tf_image_name.getText()).equals(table_event.getSelectionModel().getSelectedItem().getImage_evenement())) {
                    evenement.setImage_evenement(table_event.getSelectionModel().getSelectedItem().getImage_evenement());
                } else {
                    evenement.setImage_evenement(tf_image_name.getText());
                    String str = tf_image_name.getText();
                    String name_file = str.substring(29);

                    evenement.setImage_evenement("C:/Users/djoe/Desktop/MYFTP/" + name_file);
                }
                evenementService.modifierEvenement(evenement);
                ObservableList<Evenement> tmp = FXCollections.observableArrayList();
                tmp.addAll(evenementService.lireEvenement());
                table_event.setItems(tmp);
            } else {
                // ... user chose CANCEL or closed the dialog
            }

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

}
