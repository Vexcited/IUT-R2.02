package unilim.info.ihm.tp6.exo1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import unilim.info.ihm.tp6.exo1.controller.DndRectangleController;
import unilim.info.ihm.tp6.exo1.controller.DndTextController;

public class DndTextRectangle extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();

        Text source = new Text(50, 50, "DRAG ME");
        Text target = new Text(300, 50, "DROP HERE");

        DndTextController.manageSourceDragAndDrop(source);
        DndTextController.manageTargetDragAndDrop(target);

        Rectangle sourceRect = new Rectangle(50, 100, 150, 100);
        sourceRect.setFill(Color.DARKBLUE);
        Rectangle targetRect = new Rectangle(300, 200, 150, 100);
        targetRect.setFill(Color.DARKGREEN);

        DndRectangleController.manageSourceDragAndDrop(sourceRect);
        DndRectangleController.manageTargetDragAndDrop(targetRect);

        root.getChildren().addAll(source, target, sourceRect, targetRect);

        Scene scene = new Scene(root, 500, 350);
        scene.setFill(Color.LIGHTGREEN);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Drag and Drop Text Rectangle");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
