package unilim.info.ihm.td1.exo2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginAppFX extends Application {
  private Label lblTitreHaut;

  private Button btnValider;

  private Label lblUtilisateur;
  private TextField tfdUtilisateur;

  private Label lblMotDePasse;
  private PasswordField pwfMotDePasse;

  private Label lblApplication;
  private ComboBox<String> cbbApplication;

  private Label lblInfosBas;

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.lblTitreHaut = new Label("Veuillez saisir vos informations de login");
    this.lblTitreHaut.setAlignment(Pos.CENTER);
    this.lblTitreHaut.setMaxWidth(Double.MAX_VALUE);
    this.lblTitreHaut.setUnderline(true);

    this.btnValider = new Button("Valider");
        
    this.lblUtilisateur = new Label("Utilisateur :");
    this.tfdUtilisateur = new TextField();

    this.lblMotDePasse = new Label("Mot de passe :");
    this.pwfMotDePasse = new PasswordField();

    this.lblApplication = new Label("Application :");
    this.cbbApplication = new ComboBox<>();
  
    String[] applications = { "Comptabilité", "Paye", "Gestion de production" };

    this.cbbApplication.getItems().addAll(applications);
    this.cbbApplication.setValue(applications[0]);

    this.lblInfosBas = new Label("login de <xxx> pour application <xxx> le xx/xx/xxxx à hh:mm:ss");
    this.lblInfosBas.setAlignment(Pos.CENTER);
    this.lblInfosBas.setMaxWidth(Double.MAX_VALUE);

    GridPane grid = new GridPane();
    grid.add(this.lblUtilisateur, 0, 0);
    grid.add(this.tfdUtilisateur, 1, 0);
    grid.add(this.lblMotDePasse, 0, 1);
    grid.add(this.pwfMotDePasse, 1, 1);
    grid.add(this.lblApplication, 0, 2);
    grid.add(this.cbbApplication, 1, 2);
  
    grid.setHgap(10);
    grid.setVgap(12);

    BorderPane root = new BorderPane();

    BorderPane.setMargin(this.lblTitreHaut, new Insets(0, 0, 16, 0));
    root.setTop(this.lblTitreHaut);

    BorderPane.setMargin(grid, new Insets(0, 0, 0, 16));
    root.setCenter(grid);

    root.setBottom(this.lblInfosBas);

    BorderPane.setAlignment(this.btnValider, Pos.CENTER);
    BorderPane.setMargin(this.btnValider, new Insets(15, 15, 15, 15));
    root.setRight(this.btnValider);

    Scene scene = new Scene(root, 400, 170);
    primaryStage.setScene(scene);

    primaryStage.setTitle("Application login");
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch();
  }
}