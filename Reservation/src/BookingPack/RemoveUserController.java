package BookingPack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RemoveUserController implements Initializable {
    @FXML ListView<String> memberList = new ListView<>();
    @FXML CheckBox iAmSure;
    private ObservableList<String> memberObservableList;

    public void initialize(URL url, ResourceBundle resourceBundle){
        memberObservableList = FXCollections.observableArrayList();
        memberObservableList.addAll(Save.getMemberList());
        memberList.setItems(memberObservableList);
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Parent signInParent = FXMLLoader.load(getClass().getResource("AdminMenuView.fxml"));
        Scene signInScene = new Scene(signInParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void removeButtonClicked(MouseEvent mouseEvent) throws IOException {
        if(!memberObservableList.isEmpty()){
            if(iAmSure.isSelected()) {
                String memberInformation = memberList.getSelectionModel().getSelectedItem().toString();
                Save.removeMemberFromMemberList(memberInformation);
                memberList.getItems().remove(memberList.getSelectionModel().getSelectedItem());
            }
            else
                AlertBox.display("Are You Sure?","You have to check the box to remove users!");
        }
    }
}
