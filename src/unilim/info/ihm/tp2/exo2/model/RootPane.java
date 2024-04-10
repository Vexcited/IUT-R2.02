package unilim.info.ihm.tp2.exo2.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import unilim.info.ihm.tp2.exo2.controller.FormInscriptionController;

public class RootPane extends BorderPane {
  private static final String HEADER_TITLE = "Souscrivez à notre newsletter";
  private static final String HEADER_DESCRIPTION = "Bienvenue à notre newsletter, inscrivez-vous pour avoir des nouvelles.";
  
  private FormInscriptionPane form;
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

  private VBox footer() {
    VBox footer = new VBox();
    footer.setSpacing(8);

    Button subscribeButton = new Button("Souscrire");
    this.userStatusLabel = new Label("Utilisateur non inscrit."); // Valeur par défaut.

    subscribeButton.setOnMouseClicked(new FormInscriptionController(this.form, this.userStatusLabel));
    
    footer.getChildren().addAll(subscribeButton, this.userStatusLabel);
    return footer;
  }

  public RootPane() {
    super();
    
    VBox header = this.header();
    this.setTop(header);
    header.setAlignment(Pos.CENTER);
    BorderPane.setMargin(header, new Insets(16, 0, 0, 0));
    
    this.form = new FormInscriptionPane();
    this.setCenter(form);
    BorderPane.setMargin(form, new Insets(0, 0, 0, 32));
    
    VBox footer = this.footer();    
    this.setBottom(footer);
    footer.setAlignment(Pos.CENTER);
    BorderPane.setMargin(footer, new Insets(0, 0, 16, 0));
  }
}
