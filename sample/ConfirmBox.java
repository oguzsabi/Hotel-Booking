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

import java.awt.*;

public class ConfirmBox {
    static boolean answer;

    public static boolean display(String title, String text){
        Stage window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
        window.setMinHeight(130);



        Label label = new Label(text);
        VBox layout = new VBox(8);
        Button yButton = new Button("Yes");
        Button nButton = new Button("No");
        layout.getChildren().addAll(label,yButton,nButton);
        layout.setAlignment(Pos.CENTER);

        yButton.setOnAction(event -> {
            answer = true;
            window.close();
        });
        nButton.setOnAction(event -> {
            answer = false;
            window.close();
        });

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
