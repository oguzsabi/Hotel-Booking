package BookingPack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMenuViewController implements AddableScene, RemovableScene {
    @FXML private Button backButton;
    static boolean fromAdminMenuView = false;

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Parent signInParent = FXMLLoader.load(getClass().getResource("SignInView.fxml"));
        Scene signInScene = new Scene(signInParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void addUserButtonClicked(MouseEvent mouseEvent) throws IOException{
        /*fromAdminMenuView = true;
        SignInController.fromSignInView = false;
        Parent signUpMenuParent = FXMLLoader.load(getClass().getResource("SignUpView.fxml"));
        Scene signUpScene = new Scene(signUpMenuParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signUpScene);
        window.show();*/
        addingScene(mouseEvent,"SignUpView.fxml");
    }

    public void removeUserButtonClicked(MouseEvent mouseEvent) throws IOException {
        /*Parent signUpMenuParent = FXMLLoader.load(getClass().getResource("RemoveUserView.fxml"));
        Scene signUpScene = new Scene(signUpMenuParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signUpScene);
        window.show();*/
        removingScene(mouseEvent,"RemoveUserView.fxml");
    }

    public void removeCityButtonClicked(MouseEvent mouseEvent) throws IOException {
        removingScene(mouseEvent,"RemoveCityView.fxml");
    }

    public void addCityButtonClicked(MouseEvent mouseEvent) throws IOException {
        removingScene(mouseEvent,"AddCityView.fxml");
    }

    public void removeHotelButtonClicked(MouseEvent mouseEvent) throws IOException {

    }

    public void addHotelButtonClicked(MouseEvent mouseEvent) throws IOException {

    }

    @Override
    public void addingScene(javafx.scene.input.MouseEvent mouseEvent, String source) throws IOException {
        fromAdminMenuView = true;
        SignInController.fromSignInView = false;
        Parent signUpMenuParent = FXMLLoader.load(getClass().getResource(source));
        Scene signUpScene = new Scene(signUpMenuParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signUpScene);
        window.show();
    }

    @Override
    public void removingScene(javafx.scene.input.MouseEvent mouseEvent, String source) throws IOException {
        Parent signUpMenuParent = FXMLLoader.load(getClass().getResource(source));
        Scene signUpScene = new Scene(signUpMenuParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signUpScene);
        window.show();
    }


}
