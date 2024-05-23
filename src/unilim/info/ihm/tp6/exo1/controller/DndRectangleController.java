package unilim.info.ihm.tp6.exo1.controller;

import javafx.event.Event;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class DndRectangleController {
    private static final DataFormat RECTANGLE_FORMAT = new DataFormat("rectangle");

    public static void manageSourceDragAndDrop(Rectangle source) {
        source.setOnDragDetected(event -> {
            Dragboard drag = source.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent clipboard = new ClipboardContent();
            clipboard.put(RECTANGLE_FORMAT, "switch");
            drag.setContent(clipboard);
            event.consume();
        });
    }

    public static void manageTargetDragAndDrop(Rectangle source) {
        source.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.MOVE);
            event.consume();
        });

        source.setOnDragDropped(event -> {
            Rectangle from = (Rectangle) event.getGestureSource();

            Paint sourceFill = source.getFill();
            Paint fromFill = from.getFill();

            source.setFill(fromFill);
            from.setFill(sourceFill);

            event.setDropCompleted(true);
            event.consume();
        });
    }
}
