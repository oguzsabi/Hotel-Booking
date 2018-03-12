package BookingPack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class SignUpController {
    @FXML private TextField userName, userEmail;
    @FXML private PasswordField userPassword, userPasswordAgain;
    @FXML private Button signUpButton, backButton;
    @FXML private Label exitProgram;

    public void handleClose(MouseEvent mouseEvent){
        System.exit(0);
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException{
        Parent signInParent = FXMLLoader.load(getClass().getResource("SignInView.fxml"));
        Scene signInScene = new Scene(signInParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void signUpButtonClickedToSignUp(MouseEvent mouseEvent) throws IOException{
        try{
            if(Character.isLetter(userName.getText().charAt(0))){
                try{
                    boolean emailCheck = false;
                    for(int i = 0; i<userEmail.getText().length(); i++){
                        char checker = userEmail.getText().charAt(i);
                        if(checker == '@')
                            emailCheck = true;
                    }
                    if(emailCheck){
                        if(userPassword.getText().equals(userPasswordAgain.getText()) && userPassword.getText().length()>5){
                            Save.addingToMemberList(userName.getText(),userPassword.getText());

                            Parent signInParent = FXMLLoader.load(getClass().getResource("SignInView.fxml"));
                            Scene signInScene = new Scene(signInParent);

                            Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                            window.setScene(signInScene);
                            window.show();
                        }
                        else{
                            AlertBox.display("Password Error!","Your passwords must be the same and have at least 6 characters.");
                        }
                    }
                    else
                        throw new MustInputValidEmail("Your email must be valid.");
                }
                catch(MustInputValidEmail e){
                    AlertBox.display("Email Error!",e.getMessage());
                }
            }
            else
                throw new MustStartWithLetter("Your username must start with a letter.");
        }
        catch(MustStartWithLetter e){
            AlertBox.display("Username Error!",e.getMessage());
        }
    }
}
