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
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MyReservationsController implements Initializable {

    @FXML ListView<String> reservationList;
    @FXML Text checkInDate;
    @FXML Text checkOutDate;
    @FXML Text totalPrice;
    @FXML CheckBox iAmSure;

    private ObservableList<String> reservationObservable = FXCollections.observableArrayList();
    private File file;
    private Scanner readingFile;
    private String hotelName;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        reservationList.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)-> reservationSelection(newValue));
        addingToReservationList();
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Parent signInParent = FXMLLoader.load(getClass().getResource("CityAndHotelSelectionView.fxml"));
        Scene signInScene = new Scene(signInParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void cancelReservationButtonClicked(MouseEvent mouseEvent) throws IOException{
        file = new File(SignInController.getUsernameString()+"Reservations.txt");
        if(iAmSure.isSelected()){
            if(file.exists()){
                if(!reservationObservable.isEmpty()) {
                    Save.removingReservations(file.getName(),hotelName);
                    reservationList.getItems().remove(reservationList.getSelectionModel().getSelectedItem());
                }
            }
        }
        else
            AlertBox.display("Are You Sure?","You have to check the box before cancellation!");
    }

    public void reservationSelection(String newValue){
        hotelName = newValue;
        file = new File(SignInController.getUsernameString()+"Reservations.txt");
        if(file.exists()){
            Save.addingReservationInformation(file,hotelName,checkInDate,checkOutDate,totalPrice);
        }
    }

    public void addingToReservationList(){
        int flag = -1;
        file = new File(SignInController.getUsernameString()+"Reservations.txt");
        if(file.exists()){
            try{
                readingFile = new Scanner(file);
            }
            catch(FileNotFoundException e) {
                e.printStackTrace();
            }

            while(readingFile.hasNext()){
                String line = readingFile.nextLine();
                if(line.length()>0){
                    if(line.charAt(0)=='☭'){
                        flag++;
                    }
                }

                if(flag==0 || flag%4==0){
                    reservationObservable.add(line.substring(1));
                }
            }
            reservationList.setItems(reservationObservable);
        }
    }
}
