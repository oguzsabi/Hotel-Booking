package BookingPack;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML private TextField userName, userEmail;
    @FXML private PasswordField userPassword, userPasswordAgain;
    @FXML private Button signUpButton, backButton;

    public void initialize(URL url, ResourceBundle resourceBundle){
        if(AdminMenuViewController.fromAdminMenuView){
            backButton.setOnMouseClicked(event -> {
                        try {
                            backButtonClickedSecondCase(event);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }

        if(SignInController.fromSignInView){
            backButton.setOnMouseClicked(event -> {
                        try {
                            backButtonClickedFirstCase(event);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
    }

    public void backButtonClickedFirstCase(MouseEvent mouseEvent) throws IOException{
        Parent signInParent = FXMLLoader.load(getClass().getResource("SignInView.fxml"));
        Scene signInScene = new Scene(signInParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void backButtonClickedSecondCase(MouseEvent mouseEvent) throws IOException{
        Parent adminMenuParent = FXMLLoader.load(getClass().getResource("AdminMenuView.fxml"));
        Scene adminMenuScene = new Scene(adminMenuParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(adminMenuScene);
        window.show();
    }

    public void signUpButtonClickedToSignUp(MouseEvent mouseEvent) throws IOException{
        try{
            if(userName.getText().length()>3) {
                if(Character.isLetter(userName.getText().charAt(0))) {
                    try {
                        boolean atBoolean = false, dotBoolean = false, lengthBoolean = false;
                        int atIndex = 0;
                        for (int i = 0; i < userEmail.getText().length(); i++) {
                            char checker = userEmail.getText().charAt(i);
                            if(checker == '@') {
                                atBoolean = true;
                                atIndex = i;
                            }
                            if(checker == '.' && atIndex!=0)
                                dotBoolean = true;
                            if(userEmail.getText().length()>5)
                                lengthBoolean = true;
                        }
                        if(atBoolean && dotBoolean && lengthBoolean) {
                            if (userPassword.getText().equals(userPasswordAgain.getText()) && userPassword.getText().length() > 5) {
                                if(!Save.checkingMemberList(userName.getText()) && !Save.checkingAdminList(userName.getText())) {
                                    Save.addingToMemberList(userName.getText(), userPassword.getText());

                                    Parent signInParent = FXMLLoader.load(getClass().getResource("SignInView.fxml"));
                                    Scene signInScene = new Scene(signInParent);

                                    Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                                    window.setScene(signInScene);
                                    window.show();
                                }
                                else{
                                    AlertBox.display("Sign Up Error!", "This username already exists!");
                                }
                            }
                            else {
                                AlertBox.display("Password Error!", "Your passwords must be the same and have at least 6 characters.");
                            }
                        }
                        else
                            throw new MustInputValidEmail("Your email must be valid.");
                    } catch (MustInputValidEmail e) {
                        AlertBox.display("Email Error!", e.getMessage());
                    }
                }
                else
                    throw new MustStartWithLetter("Your username must start with a letter.");
            }
            else
                throw new MustStartWithLetter("Your username must have at least 4 characters.");
        }
        catch(MustStartWithLetter e){
            AlertBox.display("Username Error!",e.getMessage());
        }
    }
}
