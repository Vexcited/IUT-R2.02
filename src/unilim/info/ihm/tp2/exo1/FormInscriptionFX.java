package unilim.info.ihm.tp2.exo1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FormInscriptionFX extends Application {
  private static final String[] CIVILITY_ITEMS = {"M.", "Mme", "Mlle"};
  private static final String HEADER_TITLE = "Souscrivez à notre newsletter";
  private static final String HEADER_DESCRIPTION = "Bienvenue à notre newsletter, inscrivez-vous pour avoir des nouvelles.";
  private static final String FOOTER_USER_SIGNED_OUT = "Utilisateur non inscrit.";
  private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy 'à' HH:mm:ss");
  private static final Pattern EMAIL_VALIDATOR = Pattern.compile("^(.+)@(.+)$");

  private Label userStatusLabel;

  private VBox header () {
    VBox header = new VBox();
    header.setSpacing(8);

    Label title = new Label(HEADER_TITLE);
    title.setUnderline(true);
    title.setFont(Font.font(null, FontWeight.BOLD, 16));

    Label description = new Label(HEADER_DESCRIPTION);
    description.setFont(Font.font(null, FontWeight.NORMAL, 12));

    header.getChildren().addAll(title, description);
    return header;
  }

  private VBox textInputWithLabel (String label, TextField textField) {
    VBox textInputWithLabel = new VBox();
    textInputWithLabel.setSpacing(4);

    Label textInputLabel = new Label(label);

    textInputWithLabel.getChildren().addAll(textInputLabel, textField);
    return textInputWithLabel;
  }

  private VBox comboBoxWithLabel (String label, ComboBox<String> comboBox, String[] items) {
    VBox comboBoxWithLabel = new VBox();
    comboBoxWithLabel.setSpacing(4);

    Label comboBoxLabel = new Label(label);
    comboBox.getItems().addAll(items);
    comboBox.setValue(items[0]);

    comboBoxWithLabel.getChildren().addAll(comboBoxLabel, comboBox);
    return comboBoxWithLabel;
  }

  private static Label createErrorLabel (String message) {
    Label errorLabel = new Label(message);
    errorLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
    errorLabel.setTextFill(Color.RED);

    // Par défaut, le message d'erreur est caché.
    errorLabel.setVisible(false);
    return errorLabel;
  }
  
  private ComboBox<String> civilitéComboBox = new ComboBox<>();
  private TextField nomInput = new TextField();
  private TextField prenomInput = new TextField();
  private TextField emailInput = new TextField();
  private TextField confirmEmailInput = new TextField();
  private Label nomErrorLabel = createErrorLabel("Le nom est obligatoire");
  private Label prenomErrorLabel = createErrorLabel("Le prénom est obligatoire");
  private Label emailErrorLabel = createErrorLabel("Le mail est obligatoire");
  private Label confirmEmailErrorLabel = createErrorLabel(""); // Sera rempli dynamiquement, dans l'écouteur de "Souscrire".
  private Label cguErrorLabel = createErrorLabel("Il faut accepter les CGU");
  private CheckBox agreeTermsCheckbox = new CheckBox("J'accepte les conditions générales d'utilisation de la newsletter.");

  private GridPane form () {
    GridPane form = new GridPane();
    form.setAlignment(Pos.CENTER_LEFT);
    form.setHgap(6);
    form.setVgap(12);

    form.add(comboBoxWithLabel("Civilité", this.civilitéComboBox, CIVILITY_ITEMS), 0, 0);

    form.add(textInputWithLabel("Nom", this.nomInput), 0, 1);
    form.add(textInputWithLabel("Prénom", this.prenomInput), 1, 1);
    form.add(this.nomErrorLabel, 0, 2);
    form.add(this.prenomErrorLabel, 1, 2);
    
    form.add(textInputWithLabel("Adresse email", this.emailInput), 0, 3);
    form.add(textInputWithLabel("Confirmez votre email", this.confirmEmailInput), 1, 3);
    form.add(this.emailErrorLabel, 0, 4);
    form.add(this.confirmEmailErrorLabel, 1, 4);
    
    form.add(this.agreeTermsCheckbox, 0, 5);
    form.add(this.cguErrorLabel, 0, 6);

    // On dit que la checkbox va s'étendre sur deux colonnes.
    GridPane.setColumnSpan(this.agreeTermsCheckbox, 2);

    return form;
  }

  private VBox footer() {
    VBox footer = new VBox();
    footer.setSpacing(8);

    Button subscribeButton = new Button("Souscrire");
    this.userStatusLabel = new Label(FOOTER_USER_SIGNED_OUT); // Valeur par défaut.

    subscribeButton.setOnMouseClicked(event -> {
      if (event.getSource() != subscribeButton) return;
      

      Boolean nomFilled = !nomInput.getText().isEmpty();
      Boolean prenomFilled = !prenomInput.getText().isEmpty();
      Boolean emailFilled = !emailInput.getText().isEmpty();
      Boolean confirmEmailFilled = !confirmEmailInput.getText().isEmpty();
      Boolean cguAccepted = agreeTermsCheckbox.isSelected();

      nomErrorLabel.setVisible(!nomFilled);
      prenomErrorLabel.setVisible(!prenomFilled);

      // On remet la valeur par défaut.
      if (!emailFilled) confirmEmailErrorLabel.setText("La vérification du mail est obligatoire");
      emailErrorLabel.setVisible(!emailFilled);

      confirmEmailErrorLabel.setVisible(!confirmEmailFilled);
      cguErrorLabel.setVisible(!cguAccepted);

      if (nomFilled && prenomFilled && emailFilled && confirmEmailFilled && cguAccepted) {
        if (emailInput.getText().equals(confirmEmailInput.getText())) {
          Matcher matcher = EMAIL_VALIDATOR.matcher(emailInput.getText());

          if (matcher.matches()) {
            Date currentDate = new Date();

            userStatusLabel.setText(String.format(
              "%s %s %s s'est inscrit(e) le %s",
              civilitéComboBox.getValue(),
              nomInput.getText(),
              prenomInput.getText(),
              DATE_FORMATTER.format(currentDate)
            ));

            return; // On arrête la fonction ici, car l'inscription est réussie.
          }

          emailErrorLabel.setText("Le mail n'est pas conforme.");
          emailErrorLabel.setVisible(true);
        }
        else {
          confirmEmailErrorLabel.setText("Les adresse mails doivent correspondre.");
          confirmEmailErrorLabel.setVisible(true);
        }
      }

      // On remet la valeur par défaut dans le texte du footer.
      userStatusLabel.setText(FOOTER_USER_SIGNED_OUT);
    });
    
    footer.getChildren().addAll(subscribeButton, this.userStatusLabel);
    return footer;
  }

  @Override
  public void start(Stage primaryStage) {
    BorderPane layout = new BorderPane();

    VBox header = this.header();
    layout.setTop(header);
    header.setAlignment(Pos.CENTER);
    BorderPane.setMargin(header, new Insets(16, 0, 0, 0));
    
    GridPane form = this.form();
    layout.setCenter(form);
    BorderPane.setMargin(form, new Insets(0, 0, 0, 32));
    
    VBox footer = this.footer();    
    layout.setBottom(footer);
    footer.setAlignment(Pos.CENTER);
    BorderPane.setMargin(footer, new Insets(0, 0, 16, 0));

    Scene scene = new Scene(layout, 600, 450);    
    primaryStage.setTitle("Application Newsletter");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}