package BookingPack;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    @FXML private TextField userName;
    @FXML private PasswordField userPassword;
    @FXML private CheckBox rememberMe;
    static boolean fromSignInView = false;
    private static String usernameString;

    public void initialize(URL url, ResourceBundle resourceBundle){

        if(Save.checkingRememberMeFile()){
            userName.setText(Save.getUsername());
            userPassword.setText(Save.getPassword());
            rememberMe.setSelected(true);
        }

        if(userName.getText().length() == 0 || userPassword.getText().length() == 0)
            rememberMe.setDisable(true);
        else
            rememberMe.setDisable(false);
    }

    public void adminSignInButtonClicked(MouseEvent mouseEvent) throws IOException {
        if(rememberMe.isSelected()){
            Save.formattingRememberMeFile(userName.getText(),userPassword.getText());
        }
        else
            Save.cleaningRememberMeFile();

        if(Save.readingAdminList(userName.getText(),userPassword.getText())) {
            Parent adminMenuParent = FXMLLoader.load(getClass().getResource("AdminMenuView.fxml"));
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
        if(rememberMe.isSelected()){
            Save.formattingRememberMeFile(userName.getText(),userPassword.getText());
        }
        else
            Save.cleaningRememberMeFile();

        if(Save.readingMemberList(userName.getText(),userPassword.getText())) {
            usernameString = userName.getText();
            Parent userMenuParent = FXMLLoader.load(getClass().getResource("CityAndHotelSelectionView.fxml"));
            Scene userMenuScene = new Scene(userMenuParent);

            Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(userMenuScene);
            window.show();
            AlertBox.display("Hello!","You have successfully signed in " + userName.getText() + "!");
        }
        else{
            AlertBox.display("Sign In Error!","Your username or password is wrong. Please try again.");
        }
    }

    public void signUpButtonClicked(MouseEvent mouseEvent) throws IOException{
        fromSignInView = true;
        AdminMenuViewController.fromAdminMenuView = false;
        Parent signUpMenuParent = FXMLLoader.load(getClass().getResource("SignUpView.fxml"));
        Scene signUpScene = new Scene(signUpMenuParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signUpScene);
        window.show();
    }

    public void keyReleased(KeyEvent e) {
        if(userName.getText().length() == 0 || userPassword.getText().length() == 0)
            rememberMe.setDisable(true);
        else
            rememberMe.setDisable(false);
    }

    public static String getUsernameString() {
        return usernameString;
    }
}
