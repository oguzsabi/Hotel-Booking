import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GridAndCheck extends Application {
    Stage window;
    Scene logInScene,afterLogInScene;

    public static void main(String[]args){
        launch(args);
    }

    public void start(Stage primaryStage) {
        window = primaryStage;

        GridPane loginScreen = new GridPane();
        loginScreen.setPadding(new Insets(10,10,10,10));
        loginScreen.setHgap(6);
        loginScreen.setVgap(8);

        Label username = new Label("Username: ");
        GridPane.setConstraints(username,0,0);

        TextField nameInput = new TextField();
        nameInput.setPromptText("Example: jake");
        GridPane.setConstraints(nameInput,1,0);

        Label password = new Label("Password: ");
        GridPane.setConstraints(password,0,1);

        PasswordField passInput = new PasswordField();
        passInput.setPromptText("Example: 123!@sdf");
        GridPane.setConstraints(passInput,1,1);

        Button loginButton = new Button("Log In");
        GridPane.setConstraints(loginButton,1,2);

        CheckBox rememberMe = new CheckBox("Remember Me");
        GridPane.setConstraints(rememberMe,0,2);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setTooltip(new Tooltip("Select your country"));
        choiceBox.getItems().addAll("Country Not Selected","Germany","Romania","France","Brazil","Spain");
        choiceBox.setValue("Country Not Selected");
        GridPane.setConstraints(choiceBox,0,3);

        loginScreen.getChildren().addAll(username,nameInput,password,passInput,rememberMe,loginButton,choiceBox);

        loginButton.setOnAction((ActionEvent event) -> {
            if(rememberMe.isSelected()){
                System.out.printf("Welcome to the system %s!\n(Log In screen will remember you)\n",nameInput.getText());
                System.out.printf("Your country is %s.",choiceBox.getValue());
            }
            if(!rememberMe.isSelected()){
                System.out.printf("Welcome to the system %s!\n(Log In screen will NOT remember you)\n",nameInput.getText());
                System.out.printf("Your country is %s.",choiceBox.getValue());
            }
        });

        logInScene = new Scene(loginScreen);

        window.setTitle("LOG IN!");
        window.setScene(logInScene);
        window.show();
    }
}
