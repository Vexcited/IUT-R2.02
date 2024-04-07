package unilim.info.ihm.tp1.exo2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FormInscriptionFX extends Application {
  private static final String[] CIVILITY_ITEMS = {"M.", "Mme", "Mlle"};
  private static final String HEADER_TITLE = "Souscrivez à notre newsletter";
  private static final String HEADER_DESCRIPTION = "Bienvenue à notre newsletter, inscrivez-vous pour avoir des nouvelles.";

  public VBox header () {
    VBox header = new VBox();
    header.setSpacing(8);

    Label title = new Label(HEADER_TITLE);
    title.setAlignment(Pos.CENTER);
    title.setMaxWidth(Double.MAX_VALUE);

    title.setUnderline(true);
    title.setFont(Font.font(null, FontWeight.BOLD, 16));

    Label description = new Label(HEADER_DESCRIPTION);
    description.setAlignment(Pos.CENTER);
    description.setMaxWidth(Double.MAX_VALUE);
    description.setFont(Font.font(null, FontWeight.NORMAL, 12));

    header.getChildren().addAll(title, description);
    return header;
  }

  public VBox textInputWithLabel (String label) {
    VBox textInputWithLabel = new VBox();
    textInputWithLabel.setSpacing(2);

    Label textInputLabel = new Label(label);
    TextField textInput = new TextField();

    textInputWithLabel.getChildren().addAll(textInputLabel, textInput);
    return textInputWithLabel;
  }

  public VBox comboBoxWithLabel (String label, String[] items) {
    VBox comboBoxWithLabel = new VBox();
    comboBoxWithLabel.setSpacing(2);

    Label comboBoxLabel = new Label(label);
    ComboBox<String> comboBox = new ComboBox<>();
    comboBox.getItems().addAll(items);
    comboBox.setValue(items[0]);

    comboBoxWithLabel.getChildren().addAll(comboBoxLabel, comboBox);
    return comboBoxWithLabel;
  }
  
  public GridPane form () {
    GridPane form = new GridPane();
    form.setAlignment(Pos.CENTER_LEFT);
    form.setHgap(6);
    form.setVgap(12);

    form.add(comboBoxWithLabel("Civilité", CIVILITY_ITEMS), 0, 0);
    form.add(textInputWithLabel("Nom"), 0, 1);
    form.add(textInputWithLabel("Prénom"), 1, 1);
    form.add(textInputWithLabel("Adresse email"), 0, 2);
    form.add(textInputWithLabel("Confirmez votre email"), 1, 2);

    return form;
  }

  @Override
  public void start(Stage primaryStage) {
    BorderPane layout = new BorderPane();

    VBox header = header();
    layout.setTop(header);
    BorderPane.setAlignment(header, Pos.CENTER);
    BorderPane.setMargin(header, new Insets(16, 0, 0, 0));
    
    GridPane form = form();
    layout.setCenter(form);
    BorderPane.setMargin(form, new Insets(0, 0, 0, 32));

    Button subscribeButton = new Button("Souscrire");
    layout.setBottom(subscribeButton);

    BorderPane.setAlignment(subscribeButton, Pos.CENTER);
    BorderPane.setMargin(subscribeButton, new Insets(0, 0, 16, 0));

    Scene scene = new Scene(layout, 600, 450);    
    primaryStage.setTitle("Application Newsletter");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}