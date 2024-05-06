package unilim.info.ihm.td3;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CarteVisiteAppFX extends Application implements EventHandler<MouseEvent> {

  private Label lblTitreHaut;
  
  private Label lblNom;
  private TextField tfdSaisieNom;
  private Label lblPrenom;
  private TextField tfdSaisiePrenom;
  private Label lblEmail;
  private TextField tfdSaisieEmail;

  private Label lblNomPhoto;
  private Label lblCheminPhoto;

  private Button btnCréerCarte;
  // Button qui permet de choisir sa photo.
  private Button btnPhoto;

  private Text txtCivilité;
  private Text txtNom;
  private Text txtPrénom;
  private Text txtEmail;

  private Group zoneDessin;

  private Stage maFenêtre;

  private ImageView imgV;

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.maFenêtre = primaryStage;
    
    // Initialisation des composants.
    this.lblTitreHaut = new Label("Saisir ma carte de visite");
    this.lblTitreHaut.setAlignment(Pos.CENTER);
    this.lblTitreHaut.setMaxWidth(Double.MAX_VALUE);
    
    this.lblNom = new Label("Nom :");
    this.tfdSaisieNom = new TextField();
    
    this.lblPrenom = new Label("Prénom :");
    this.tfdSaisiePrenom = new TextField();
    
    this.lblEmail = new Label("Email :");
    this.tfdSaisieEmail = new TextField();
    
    this.lblNomPhoto = new Label("Photo :");
    this.lblCheminPhoto = new Label("...");

    this.btnCréerCarte = new Button("Créer carte");
    this.btnPhoto = new Button("Photo");

    this.btnCréerCarte.setOnMouseClicked(this);
    this.btnPhoto.setOnMouseClicked(this);

    // pour désactiver le bouton si aucun champ est rempli
    // this.btnCréerCarte.disableProperty().bind(tfdSaisieNom.textProperty().isEmpty().or(tfdSaisiePrenom.textProperty().isEmpty()).or(tfdSaisieEmail.textProperty().isEmpty()));

    // Création du formulaire central.
    GridPane form = this.créerFormulaire();

    FlowPane containerBoutons = new FlowPane(Orientation.HORIZONTAL);
    containerBoutons.getChildren().addAll(this.btnCréerCarte, this.btnPhoto);
    containerBoutons.setAlignment(Pos.CENTER);
    containerBoutons.setHgap(20);

    this.zoneDessin = this.dessiner();
    
    // Nœud racine.
    VBox root = new VBox();
    root.setSpacing(20);
    root.setAlignment(Pos.TOP_CENTER);

    // Ajouter une icône à la fenêtre.
    Image icon = new Image(this.getClass().getResourceAsStream("tazz.png"));
    primaryStage.getIcons().add(icon);
    
    root.getChildren().addAll(this.lblTitreHaut, form, containerBoutons, zoneDessin);

    Scene scene = new Scene(root, 600, 400);
    primaryStage.setScene(scene);

    primaryStage.setTitle("Application carte de visite en JavaFX");
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  private Group dessiner() {
    Group zoneDessin = new Group();
    Rectangle rect = new Rectangle();
    rect.setX(0);
    rect.setY(0);
    rect.setWidth(600);
    rect.setHeight(200);
    rect.setFill(Color.PINK);

    this.txtCivilité = new Text(5, 20, "Mr/Mrs.");
    this.txtNom = new Text();
    this.txtNom.textProperty().bind(this.tfdSaisieNom.textProperty());
    this.txtPrénom = new Text();
    this.txtPrénom.textProperty().bind(this.tfdSaisiePrenom.textProperty());
    
    HBox textes = new HBox(this.txtCivilité, this.txtNom, this.txtPrénom);
    textes.setSpacing(5);
    
    this.imgV = new ImageView();
    this.txtEmail = new Text(5, 160, "Email : ");
    
    Image img = new Image(this.getClass().getResource("tazz.png").toString(), 140, 140, true, false);
    imgV.setImage(img);
    imgV.setX(430);
    imgV.setY(10);

    zoneDessin.getChildren().addAll(rect, textes, this.txtEmail, imgV);
    
    zoneDessin.setVisible(false);

    return zoneDessin;
  }

  private GridPane créerFormulaire () {
    GridPane form = new GridPane();
    form.setHgap(20);
    form.setVgap(10);
    form.setAlignment(Pos.CENTER);

    form.add(this.lblNom, 0, 0);
    form.add(this.tfdSaisieNom, 1, 0);

    form.add(this.lblPrenom, 0, 1);
    form.add(this.tfdSaisiePrenom, 1, 1);
    
    form.add(this.lblEmail, 0, 2);
    form.add(this.tfdSaisieEmail, 1, 2);

    form.add(this.lblNomPhoto, 0, 3);
    form.add(this.lblCheminPhoto, 1, 3);

    return form;
  }
  
  public static void main (String[] args) {
    Application.launch(args);
  }

  @Override
  public void handle(MouseEvent event) {
    if (event.getSource() == this.btnCréerCarte) {
      if (tfdSaisieNom.getText().isEmpty() || tfdSaisiePrenom.getText().isEmpty() || tfdSaisieEmail.getText().equals("")) {
        Alert dialog = new Alert(AlertType.ERROR);
        dialog.setTitle("Erreur de saisie");
        // remove error title
        dialog.setHeaderText(null);
        dialog.setContentText("Votre saisie est vide, voulez-vous ?");

        ButtonType btnType1 = new ButtonType("Quitter");
        ButtonType btnType2 = new ButtonType("Charger un exemple par défaut");
        ButtonType btnType3 = new ButtonType("Revenir à la saisie");
        
        dialog.getButtonTypes().setAll(btnType1, btnType2, btnType3);
        Optional<ButtonType> choix = dialog.showAndWait();
        if (choix.isPresent()) {
          // quitter (l'application)
          if (choix.get() == btnType1) {
            Platform.exit();
          }
          // charger un exemple par défaut
          else if (choix.get() == btnType2) {
            tfdSaisieNom.setText("Doe");
            tfdSaisiePrenom.setText("John");
            tfdSaisieEmail.setText("john.doe@example.com");
          }
          // revenir à la saisie
          else if (choix.get() == btnType3) {
            // no-op : rien à faire
          }
        }
      }
      else {
        this.zoneDessin.setVisible(true);
      }
    }
    else if (event.getSource() == this.btnPhoto) {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Choisir une photo");
      
      // PS : pour choisir que des images
      // fileChooser.getExtensionFilters().addAll(
      //   new FileChooser.ExtensionFilter("Fichiers images", "*.png", "*.jpg", "*.jpeg", "*.gif")
      // );

      File selectedFile = fileChooser.showOpenDialog(this.maFenêtre);
      if (selectedFile != null) {
        try {
          Image img = new Image(selectedFile.toURI().toURL().toString(), 140, 140, true, false);
          this.imgV.setImage(img);
        } catch (MalformedURLException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
