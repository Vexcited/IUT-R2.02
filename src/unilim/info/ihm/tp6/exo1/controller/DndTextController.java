package unilim.info.ihm.tp6.exo1.controller;

import javafx.event.Event;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Text;

public class DndTextController {
    public static void manageSourceDragAndDrop(Text source) {
        source.setOnDragDetected(event -> {
            /* drag was detected, start a drag-and-drop gesture*/
            /* allow any transfer mode */
            Dragboard db = source.startDragAndDrop(TransferMode.ANY);

            /* Put a string on a dragboard */
            ClipboardContent content = new ClipboardContent();
            content.putString(source.getText());
            db.setContent(content);

            event.consume();
        });

        source.setOnDragDone(event -> {
            // if the data was successfully moved, clear it
            if (event.isAccepted()) {
                source.setText("");
            }
        });
    }

    public static void manageTargetDragAndDrop(Text source) {
        source.setOnDragOver(event -> {
            /* data is dragged over the target */
            /* accept it only if it is not dragged from the same node
             * and if it has a string data */
            if (event.getDragboard().hasString()) {
                /* allow for both copying and moving, whatever user chooses */
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }

            event.consume();
        });

        source.setOnDragEntered(Event::consume);
        source.setOnDragExited(Event::consume);

        source.setOnDragDropped(event -> {
            /* the drag-and-drop gesture entered the target */
            /* show to the user that it is an actual gesture target */
            if (event.getDragboard().hasString()) {
                source.setText(event.getDragboard().getString());
                event.setDropCompleted(true);
            }
            else {
                event.setDropCompleted(false);
            }

            event.consume();
        });
    }

}
