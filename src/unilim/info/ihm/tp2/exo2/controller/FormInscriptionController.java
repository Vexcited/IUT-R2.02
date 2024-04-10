package unilim.info.ihm.tp2.exo2.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import unilim.info.ihm.tp2.exo2.model.FormInscriptionPane;

public class FormInscriptionController implements EventHandler<MouseEvent> {
  private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy 'à' HH:mm:ss");
  private static final Pattern EMAIL_VALIDATOR = Pattern.compile("^(.+)@(.+)$");
  private static final String FOOTER_USER_SIGNED_OUT = "Utilisateur non inscrit.";
  
  private FormInscriptionPane form;
  private Label userStatusLabel;

  public FormInscriptionController (FormInscriptionPane form, Label userStatusLabel) {
    this.form = form;
    this.userStatusLabel = userStatusLabel;
  }

  @Override
  public void handle(MouseEvent event) {
    Boolean nomFilled = !this.form.getNomInput().getText().isEmpty();
    Boolean prenomFilled = !this.form.getPrenomInput().getText().isEmpty();
    Boolean emailFilled = !this.form.getEmailInput().getText().isEmpty();
    Boolean confirmEmailFilled = !this.form.getConfirmEmailInput().getText().isEmpty();
    Boolean cguAccepted = this.form.getAgreeTermsCheckbox().isSelected();

    this.form.getNomErrorLabel().setVisible(!nomFilled);
    this.form.getPrenomErrorLabel().setVisible(!prenomFilled);

    // On remet la valeur par défaut.
    if (!emailFilled) this.form.getConfirmEmailErrorLabel().setText("La vérification du mail est obligatoire");
    this.form.getEmailErrorLabel().setVisible(!emailFilled);

    this.form.getConfirmEmailErrorLabel().setVisible(!confirmEmailFilled);
    this.form.getCguErrorLabel().setVisible(!cguAccepted);

    if (nomFilled && prenomFilled && emailFilled && confirmEmailFilled && cguAccepted) {
      if (this.form.getEmailInput().getText().equals(this.form.getConfirmEmailInput().getText())) {
        Matcher matcher = EMAIL_VALIDATOR.matcher(this.form.getEmailInput().getText());

        if (matcher.matches()) {
          Date currentDate = new Date();

          userStatusLabel.setText(String.format(
            "%s %s %s s'est inscrit(e) le %s",
            this.form.getCivilityComboBox().getValue(),
            this.form.getNomInput().getText(),
            this.form.getPrenomInput().getText(),
            DATE_FORMATTER.format(currentDate)
          ));

          return; // On arrête la fonction ici, car l'inscription est réussie.
        }

        this.form.getEmailErrorLabel().setText("Le mail n'est pas conforme.");
        this.form.getEmailErrorLabel().setVisible(true);
      }
      else {
        this.form.getConfirmEmailErrorLabel().setText("Les adresse mails doivent correspondre.");
        this.form.getConfirmEmailErrorLabel().setVisible(true);
      }
    }

    // On remet la valeur par défaut dans le texte du footer.
    userStatusLabel.setText(FOOTER_USER_SIGNED_OUT);
  }
}
