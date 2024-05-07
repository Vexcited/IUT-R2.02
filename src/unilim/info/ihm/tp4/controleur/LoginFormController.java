package unilim.info.ihm.tp4.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginFormController {
    @FXML
    private ComboBox<String> idCb;

    @FXML
    private Label idLibBas;

    @FXML
    private PasswordField idPwd;

    @FXML
    private TextField idUser;

    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    void validerSaisies(MouseEvent event) {
        String user = idUser.getText();
        String pwd = idPwd.getText();
        String app = idCb.getValue();

        // On reset le texte du login.
        idLibBas.setText("Aucun login saisi");

        if (user.trim().isEmpty()) {
            afficherErreur("Veuillez saisir un nom d'utilisateur");
        }
        else if (pwd.isEmpty()) {
            afficherErreur("Veuillez saisir un mot de passe");
        }
        else if (app == null) {
            afficherErreur("Veuillez choisir une application");
        }
        else {
            Date dateCourante = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy 'à' HH:mm:ss");

            idLibBas.setText("Login de <" + user + "> pour application <" + app + "> le " + format.format(dateCourante));
        }
    }

    @FXML
    void initialize() {
        idCb.getItems().addAll("Comptabilité", "Paye", "Gestion de Production");
    }
}


