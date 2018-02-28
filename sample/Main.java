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
import javafx.stage.Stage;

public class Main extends Application {
    Button button1, button2;
    Stage window;
    Scene scene1;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setOnCloseRequest(event -> {
            event.consume();
            closeProgram();
        });

        button2 = new Button("Close program");
        button2.setOnAction(event -> closeProgram());

        button1 = new Button("Click ME!");
        button1.setOnAction(event -> {
            boolean result = ConfirmBox.display("Are you sure?","Do you really want to send this?");
            if(result==true){
                AlertBox.display("Notifier!","You have sent it.");
            }

            if(result==false){
                AlertBox.display("Notifier!","You have not sent it.");
            }
        });

        Label text = new Label("This is the first scene.");

        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(text,button1,button2);
        layout1.setAlignment(Pos.CENTER);

        scene1 = new Scene(layout1,200,200);

        window.setTitle("This is a title!");
        window.setScene(scene1);
        window.show();
    }

    private void closeProgram(){
        boolean result = ConfirmBox.display("Title","Do you really want to exit?");
        if(result)
            window.close();
    }
}
