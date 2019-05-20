package gui;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pamatky.Zamek;

/**
 *
 * @author Tomáš Vondra
 */
public class VlozZamekDialog extends Dialog<Zamek> {

    public VlozZamekDialog() {
        DialogPane dialogPane = this.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Label lblNazev = new Label("Název:");
        Label lblId = new Label("Id:");
        Label lblGPS = new Label("lokace:");

        TextField txtFieldNazev = new TextField();
        TextField txtFieldId = new TextField();
        TextField txtFieldGPS = new TextField();


        dialogPane.setContent(new VBox(
                new HBox(143, lblId, lblNazev, lblGPS),
                new HBox(5, txtFieldId, txtFieldNazev, txtFieldGPS)
        ));
        
        
        

        Platform.runLater(txtFieldId::requestFocus);
        this.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                if (validate(txtFieldId.getText()) 
                        && !txtFieldGPS.getText().isEmpty()
                        && !txtFieldNazev.getText().isEmpty()) {
                    
                    return new Zamek(
                            Integer.parseInt(txtFieldId.getText()),
                            txtFieldNazev.getText(),
                            txtFieldGPS.getText()
                    );
                    
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Chyba validace");
                    alert.setHeaderText("ID musí být číslo");
                    alert.setContentText("");
                    alert.showAndWait();
                }
            }
            return null;
        });
    }

    private boolean validate(String id) {
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
