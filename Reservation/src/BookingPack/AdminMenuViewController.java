package BookingPack;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMenuViewController implements AddableScene, RemovableScene {
    static boolean fromAdminMenuView = false;

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Parent signInParent = FXMLLoader.load(getClass().getResource("SignInView.fxml"));
        Scene signInScene = new Scene(signInParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void adminManualButtonClicked(MouseEvent mouseEvent) throws IOException{
        addingScene(mouseEvent,"AdminManualView.fxml");
    }

    public void addUserButtonClicked(MouseEvent mouseEvent) throws IOException{
        addingScene(mouseEvent,"SignUpView.fxml");
    }

    public void removeUserButtonClicked(MouseEvent mouseEvent) throws IOException {
        removingScene(mouseEvent,"RemoveUserView.fxml");
    }

    public void removeCityButtonClicked(MouseEvent mouseEvent) throws IOException {
        removingScene(mouseEvent,"RemoveCityView.fxml");
    }

    public void addCityButtonClicked(MouseEvent mouseEvent) throws IOException {
        addingScene(mouseEvent,"AddCityView.fxml");
    }

    public void removeHotelButtonClicked(MouseEvent mouseEvent) throws IOException {
        removingScene(mouseEvent,"RemoveHotelView.fxml");
    }

    public void addHotelButtonClicked(MouseEvent mouseEvent) throws IOException {
        addingScene(mouseEvent,"AddHotelView.fxml");
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
