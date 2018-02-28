package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void display(String title, String text) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Button button = new Button();
        button.setText("Close");
        button.setOnAction(event -> window.close());

        Label label = new Label();
        label.setText(text);

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(label,button);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
