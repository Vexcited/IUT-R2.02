package unilim.info.ihm.tp3.controller;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import unilim.info.ihm.tp3.view.Formulaire;

import java.io.File;
import java.net.MalformedURLException;

public class ChoixPhotoController implements EventHandler<MouseEvent> {
    private final Label lblPhoto;
    private final Stage stage;

    public ChoixPhotoController(Label lblPhoto, Stage mainStage) {
        this.stage = mainStage;
        this.lblPhoto = lblPhoto;
    }

    @Override
    public void handle(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une photo");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichiers images", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                String imagePath = selectedFile.toURI().toURL().toString();
                lblPhoto.setText(imagePath);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
