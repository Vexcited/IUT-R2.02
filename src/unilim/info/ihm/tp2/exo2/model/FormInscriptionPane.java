package unilim.info.ihm.tp2.exo2.model;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FormInscriptionPane extends GridPane {
  private static final String[] CIVILITY_ITEMS = {"M.", "Mme", "Mlle"};

  private static Label createErrorLabel (String message) {
    Label errorLabel = new Label(message);
    errorLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
    errorLabel.setTextFill(Color.RED);

    // Par défaut, le message d'erreur est caché.
    errorLabel.setVisible(false);
    return errorLabel;
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

  public FormInscriptionPane () {
    super();

    this.setAlignment(Pos.CENTER_LEFT);
    this.setHgap(6);
    this.setVgap(12);

    this.add(comboBoxWithLabel("Civilité", this.civilitéComboBox, CIVILITY_ITEMS), 0, 0);

    this.add(textInputWithLabel("Nom", this.nomInput), 0, 1);
    this.add(textInputWithLabel("Prénom", this.prenomInput), 1, 1);
    this.add(this.nomErrorLabel, 0, 2);
    this.add(this.prenomErrorLabel, 1, 2);
    
    this.add(textInputWithLabel("Adresse email", this.emailInput), 0, 3);
    this.add(textInputWithLabel("Confirmez votre email", this.confirmEmailInput), 1, 3);
    this.add(this.emailErrorLabel, 0, 4);
    this.add(this.confirmEmailErrorLabel, 1, 4);
    
    this.add(this.agreeTermsCheckbox, 0, 5);
    this.add(this.cguErrorLabel, 0, 6);

    // On dit que la checkbox va s'étendre sur deux colonnes.
    GridPane.setColumnSpan(this.agreeTermsCheckbox, 2);
  }

  public ComboBox<String> getCivilityComboBox () {
    return this.civilitéComboBox;
  }

  public TextField getNomInput () {
    return this.nomInput;
  }

  public TextField getPrenomInput () {
    return this.prenomInput;
  }

  public TextField getEmailInput () {
    return this.emailInput;
  }

  public TextField getConfirmEmailInput () {
    return this.confirmEmailInput;
  }

  public Label getNomErrorLabel () {
    return this.nomErrorLabel;
  }

  public Label getPrenomErrorLabel () {
    return this.prenomErrorLabel;
  }

  public Label getEmailErrorLabel () {
    return this.emailErrorLabel;
  }

  public Label getConfirmEmailErrorLabel () {
    return this.confirmEmailErrorLabel;
  }

  public Label getCguErrorLabel () {
    return this.cguErrorLabel;
  }

  public CheckBox getAgreeTermsCheckbox () {
    return this.agreeTermsCheckbox;
  }
}
