package BookingPack;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;
import java.io.IOException;


public class SignInController {
    @FXML private TextField userName;
    @FXML private PasswordField userPassword;
    @FXML private Button signInButton, signUpButton, adminSignInButton;
    @FXML private CheckBox rememberMe;
    @FXML private Label exitProgram;

    public void handleClose(MouseEvent mouseEvent){
        System.exit(0);
    }

    public void adminSignInButtonClicked(MouseEvent mouseEvent) throws IOException {

        if(Save.readingAdminList(userName.getText(),userPassword.getText())) {
            Parent adminMenuParent = FXMLLoader.load(getClass().getResource(""));
            Scene adminMenuScene = new Scene(adminMenuParent);

            Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(adminMenuScene);
            window.show();
        }
        else{
            AlertBox.display("Sign In Error!","Your username or password is wrong. Please try again.");
        }
    }

    public void signInButtonClicked(MouseEvent mouseEvent) throws IOException{
        if(Save.readingMemberList(userName.getText(),userPassword.getText())) {
            Parent adminMenuParent = FXMLLoader.load(getClass().getResource("CityAndHotelSelectionView.fxml"));
            Scene adminMenuScene = new Scene(adminMenuParent);

            Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(adminMenuScene);
            window.show();
        }
        else{
            AlertBox.display("Sign In Error!","Your username or password is wrong. Please try again.");
        }
    }

    public void signUpButtonClicked(MouseEvent mouseEvent) throws IOException{
        Parent signUpMenuParent = FXMLLoader.load(getClass().getResource("SignUpView.fxml"));
        Scene signUpScene = new Scene(signUpMenuParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signUpScene);
        window.show();
    }
}
