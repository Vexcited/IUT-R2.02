package unilim.info.ihm.td2.exo2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application {
  RadioButton rbtn2;
  RadioButton rbtn4;
  RadioButton rbtn6;
  RadioButton rbtn8;
  RadioButton rbtn10;
  RadioButton rbtn12;
  RadioButton rbtn20;
  RadioButton rbtn100;

  ToggleGroup group;

  Button btnRoll;
  Button btnFin;

  TextArea txaGauche;
  TextArea txaDroite;

  Label lblCentral;

  int nbTirages = 0;

  private static final String ROLL_THE_DICE = "Roll the DICE";
  private static final String TIRAGES = "Tirages :";
  private static final String RETOUR_CHARIOT = System.getProperty("line.separator");

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Initialisation des composants
    this.rbtn2 = new RadioButton("2");
    this.rbtn4 = new RadioButton("4");
    this.rbtn6 = new RadioButton("6");
    this.rbtn8 = new RadioButton("8");
    this.rbtn10 = new RadioButton("10");
    this.rbtn12 = new RadioButton("12");
    this.rbtn20 = new RadioButton("20");
    this.rbtn100 = new RadioButton("100");

    this.rbtn2.setUserData("2");
    this.rbtn4.setUserData("4");
    this.rbtn6.setUserData("6");
    this.rbtn8.setUserData("8");
    this.rbtn10.setUserData("10");
    this.rbtn20.setUserData("20");
    this.rbtn100.setUserData("100");

    EcouteurRadioBouton listenerRbtn = new EcouteurRadioBouton();
    this.rbtn2.setOnMouseClicked(listenerRbtn);
    this.rbtn4.setOnMouseClicked(listenerRbtn);
    this.rbtn6.setOnMouseClicked(listenerRbtn);
    this.rbtn8.setOnMouseClicked(listenerRbtn);
    this.rbtn10.setOnMouseClicked(listenerRbtn);
    this.rbtn12.setOnMouseClicked(listenerRbtn);
    this.rbtn20.setOnMouseClicked(listenerRbtn);
    this.rbtn100.setOnMouseClicked(listenerRbtn);

    this.group = new ToggleGroup();
    group.getToggles().addAll(rbtn2, rbtn4, rbtn6, rbtn8, rbtn10, rbtn12, rbtn20, rbtn100);
    // Sinon on peut aussi faire :
    // this.rbtn2.setToggleGroup(group);
    // this.rbtn4.setToggleGroup(group);
    // this.rbtn6.setToggleGroup(group);
    // this.rbtn8.setToggleGroup(group);
    // this.rbtn10.setToggleGroup(group);
    // this.rbtn12.setToggleGroup(group);
    // this.rbtn20.setToggleGroup(group);
    // this.rbtn100.setToggleGroup(group);

    this.lblCentral = new Label("0");
    this.lblCentral.setFont(Font.font(null, FontWeight.BOLD, 100));
    this.lblCentral.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    this.lblCentral.setAlignment(Pos.CENTER);
    this.lblCentral.setBorder(
      new Border(
        new BorderStroke(
          Color.BLACK,
          BorderStrokeStyle.SOLID,
          CornerRadii.EMPTY,
          BorderWidths.DEFAULT
        )
      )
    );

    
    this.btnRoll = new Button("Roll");
    this.btnFin = new Button("Fin");

    EcouteurBouton listenerBtn = new EcouteurBouton();
    this.btnRoll.setOnMouseClicked(listenerBtn);
    this.btnFin.setOnMouseClicked(listenerBtn);

    this.txaGauche = new TextArea(ROLL_THE_DICE);
    this.txaGauche.setPrefSize(100, 200);
    
    this.txaDroite = new TextArea(TIRAGES);
    this.txaDroite.setPrefSize(100, 200);

    BorderPane root = new BorderPane();

    HBox hbHaut = new HBox();
    hbHaut.getChildren().addAll(rbtn2, rbtn4, rbtn6, rbtn8, rbtn10, rbtn12, rbtn20, rbtn100);
    hbHaut.setAlignment(Pos.CENTER);
    hbHaut.setSpacing(20);
    
    HBox hbBas = new HBox();
    hbBas.getChildren().addAll(btnRoll, btnFin);
    hbBas.setAlignment(Pos.CENTER);
    hbBas.setSpacing(20);
    
    // Ajout des composants en haut
    BorderPane.setAlignment(hbHaut, Pos.CENTER);
    BorderPane.setMargin(hbHaut, new Insets(10, 0, 10, 0));
    root.setTop(hbHaut);
    
    // Ajout des composants en bas
    BorderPane.setAlignment(hbBas, Pos.CENTER);
    BorderPane.setMargin(hbBas, new Insets(10, 0, 10, 0));
    root.setBottom(hbBas);

    root.setLeft(txaGauche);
    root.setRight(txaDroite);

    root.setCenter(this.lblCentral);
    
    // Création de la scène
    Scene scene = new Scene(root, 600, 400);

    primaryStage.setScene(scene);
    primaryStage.setTitle("Application de dé en JavaFX");
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch();
  }

  private class EcouteurRadioBouton implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      Object obj = event.getSource();
      RadioButton rbtn = (RadioButton) obj;
      // Peut être raccourci en :
      // RadioButton rbtn = (RadioButton) event.getSource();
      String typeDe = (String) rbtn.getUserData();
  
      lblCentral.setText("");
      txaGauche.setText(ROLL_THE_DICE + RETOUR_CHARIOT + RETOUR_CHARIOT + "à " + typeDe + " faces");
    }
  }

  private class EcouteurBouton implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      if (event.getSource() == btnRoll) {
        if (group.getSelectedToggle() != null) {
          Toggle tog = group.getSelectedToggle();
          RadioButton rbtn = (RadioButton) tog;
          
          int nbFaces = Integer.parseInt(rbtn.getUserData().toString());
          int resultat = (int) (Math.random() * nbFaces) + 1;
          
          lblCentral.setText(String.valueOf(resultat));

          txaDroite.appendText(RETOUR_CHARIOT + String.valueOf(resultat));

          nbTirages++;
          if (nbTirages > 10) {
            btnRoll.setDisable(true);
          }
        }
      }
      else if (event.getSource() == btnFin) {
        Platform.exit();
      }
    }
  }
}
