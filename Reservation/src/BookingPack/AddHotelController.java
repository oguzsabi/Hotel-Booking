package BookingPack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Formatter;
import java.util.ResourceBundle;

public class AddHotelController implements Initializable {
    @FXML private TextField hotelNameText;
    @FXML private TextField hotelNumberOfStarsText;
    @FXML private TextField hotelNumberOfRoomsText;
    @FXML private TextField hotelNightlyCostText;
    @FXML private TextField hotelYearOfBuiltText;
    @FXML private TextArea hotelDescriptionText;
    @FXML private TextField hotelSingleRoomPriceText;
    @FXML private TextField hotelDoubleRoomPriceText;
    @FXML private TextField hotelTripleRoomPriceText;
    @FXML private TextField hotelQuadRoomPriceText;
    @FXML private TextField hotelQueenRoomPriceText;
    @FXML private TextField hotelKingRoomPriceText;
    @FXML private ListView<String> hotelList = new ListView<>();
    private CityAndHotelSelectionController cityControllerObject = new CityAndHotelSelectionController();
    private RemoveCityController removeCityController = new RemoveCityController();
    private File file;
    private Formatter newFile;

    public void initialize(URL url, ResourceBundle resourceBundle){
        cityControllerObject.getCityObservableList().addAll(cityControllerObject.getCityObject().getDefaultCityNames());
        cityControllerObject.getCityObservableList().addAll(removeCityController.addingAddedCitiesToTheList());
        hotelList.setItems(cityControllerObject.getCityObservableList());
        hotelList.getSelectionModel().selectFirst();
    }

    public void submitButtonClicked(MouseEvent mouseEvent) throws IOException{
        String cityName = hotelList.getSelectionModel().getSelectedItem().toString();
        String cityHotelsFiveTxtFile = cityName + "HotelsFive.txt";
        String cityHotelsThreeTxtFile = cityName + "HotelsThree.txt";

        try{
            if(hotelNumberOfStarsText.getText().length()>0) {
                if (Integer.parseInt(hotelNumberOfStarsText.getText()) > 3 && Integer.parseInt(hotelNumberOfStarsText.getText()) < 6) {
                    if (hotelNumberOfStarsText.getText().length() > 0 && hotelDescriptionText.getText().length() > 0 && hotelNameText.getText().length() > 0 && hotelNightlyCostText.getText().length() > 0 && hotelNumberOfRoomsText.getText().length() > 0 && hotelSingleRoomPriceText.getText().length() > 0 && hotelDoubleRoomPriceText.getText().length() > 0 && hotelTripleRoomPriceText.getText().length() > 0 && hotelYearOfBuiltText.getText().length() > 0 && hotelQuadRoomPriceText.getText().length() > 0 && hotelQueenRoomPriceText.getText().length() > 0 && hotelKingRoomPriceText.getText().length() > 0) {
                        if(Character.isLetter(hotelNameText.getText().charAt(0))){
                            if(Integer.parseInt(hotelNumberOfRoomsText.getText())>9){
                                if(Integer.parseInt(hotelNightlyCostText.getText())>=0){
                                    if(Integer.parseInt(hotelYearOfBuiltText.getText())>=1900 && Integer.parseInt(hotelYearOfBuiltText.getText())<=2018){
                                        if(Integer.parseInt(hotelSingleRoomPriceText.getText()) == Integer.parseInt(hotelNightlyCostText.getText())){
                                            if(Integer.parseInt(hotelDoubleRoomPriceText.getText()) > Integer.parseInt(hotelSingleRoomPriceText.getText()) && Integer.parseInt(hotelTripleRoomPriceText.getText())>Integer.parseInt(hotelDoubleRoomPriceText.getText()) && Integer.parseInt(hotelQuadRoomPriceText.getText()) > Integer.parseInt(hotelTripleRoomPriceText.getText()) && Integer.parseInt(hotelQueenRoomPriceText.getText())>Integer.parseInt(hotelQuadRoomPriceText.getText()) && Integer.parseInt(hotelKingRoomPriceText.getText())>Integer.parseInt(hotelQueenRoomPriceText.getText())){
                                                try {
                                                    file = new File(cityHotelsFiveTxtFile);
                                                    if(file.createNewFile()){
                                                        newFile = new Formatter(cityHotelsFiveTxtFile);
                                                    }
                                                } catch (Exception e) {
                                                    AlertBox.display("Unknown Error!", "An unknown error has occured!");
                                                }
                                                Save.addingHotelFiveStars(file,hotelNameText.getText(),hotelDescriptionText.getText(), Integer.parseInt(hotelNumberOfStarsText.getText()), Integer.parseInt(hotelNumberOfRoomsText.getText()),Integer.parseInt(hotelYearOfBuiltText.getText()),Integer.parseInt(hotelNightlyCostText.getText()),Integer.parseInt(hotelSingleRoomPriceText.getText()),Integer.parseInt(hotelDoubleRoomPriceText.getText()),Integer.parseInt(hotelTripleRoomPriceText.getText()),Integer.parseInt(hotelQuadRoomPriceText.getText()),Integer.parseInt(hotelQueenRoomPriceText.getText()),Integer.parseInt(hotelKingRoomPriceText.getText()));
                                                AlertBox.display("Successful Activity!","The hotel has been added to the desired city!");
                                                Parent signInParent = FXMLLoader.load(getClass().getResource("AdminMenuView.fxml"));
                                                Scene signInScene = new Scene(signInParent);

                                                Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                                                window.setScene(signInScene);
                                                window.show();
                                            }
                                            else
                                                AlertBox.display("Room Price Error!","Bigger rooms must be more expensive than smaller ones!");
                                        }
                                        else
                                            AlertBox.display("Room Price Error!","Hotel's single room prices must be equal to cheapest nightly cost!");
                                    }
                                    else
                                        AlertBox.display("Hotel Year Of Built Error!","Hotel year of built must be between 1900-2018!");
                                }
                                else
                                    AlertBox.display("Hotel Nightly Cost Error!","Hotel's cheapest nightly cost must be at least 0!");
                            }
                            else
                                AlertBox.display("Hotel Number Of Rooms Error!","Hotel must have at least 10 rooms!");
                        }
                        else
                            AlertBox.display("Hotel Name Error!","Hotel names must start with a letter!");
                    }
                    else
                        AlertBox.display("Information Error!", "All spaces must be filled!");
                }

                else if (Integer.parseInt(hotelNumberOfStarsText.getText()) <= 3 && Integer.parseInt(hotelNumberOfStarsText.getText()) > 0) {
                    if (hotelNumberOfStarsText.getText().length() > 0 && hotelDescriptionText.getText().length() > 0 && hotelNameText.getText().length() > 0 && hotelNightlyCostText.getText().length() > 0 && hotelNumberOfRoomsText.getText().length() > 0 && hotelSingleRoomPriceText.getText().length() > 0 && hotelDoubleRoomPriceText.getText().length() > 0 && hotelTripleRoomPriceText.getText().length() > 0 && hotelYearOfBuiltText.getText().length() > 0) {
                        if(Character.isLetter(hotelNameText.getText().charAt(0))){
                            if(Integer.parseInt(hotelNumberOfRoomsText.getText())>9){
                                if(Integer.parseInt(hotelNightlyCostText.getText())>=0){
                                    if(Integer.parseInt(hotelYearOfBuiltText.getText())>=1900 && Integer.parseInt(hotelYearOfBuiltText.getText())<=2018){
                                        if(Integer.parseInt(hotelSingleRoomPriceText.getText()) == Integer.parseInt(hotelNightlyCostText.getText())){
                                            if(Integer.parseInt(hotelDoubleRoomPriceText.getText()) > Integer.parseInt(hotelSingleRoomPriceText.getText()) && Integer.parseInt(hotelTripleRoomPriceText.getText())>Integer.parseInt(hotelDoubleRoomPriceText.getText())){
                                                try {
                                                    file = new File(cityHotelsThreeTxtFile);
                                                    if(file.createNewFile()){
                                                        newFile = new Formatter(cityHotelsThreeTxtFile);
                                                    }
                                                } catch (Exception e) {
                                                    AlertBox.display("Unknown Error!", "An unknown error has occured!");
                                                }
                                                Save.addingHotelThreeStars(file,hotelNameText.getText(),hotelDescriptionText.getText(), Integer.parseInt(hotelNumberOfStarsText.getText()), Integer.parseInt(hotelNumberOfRoomsText.getText()),Integer.parseInt(hotelYearOfBuiltText.getText()),Integer.parseInt(hotelNightlyCostText.getText()),Integer.parseInt(hotelSingleRoomPriceText.getText()),Integer.parseInt(hotelDoubleRoomPriceText.getText()),Integer.parseInt(hotelTripleRoomPriceText.getText()));
                                                AlertBox.display("Successful Activity!","The hotel has been added to the desired city!");
                                                Parent signInParent = FXMLLoader.load(getClass().getResource("AdminMenuView.fxml"));
                                                Scene signInScene = new Scene(signInParent);

                                                Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                                                window.setScene(signInScene);
                                                window.show();
                                            }
                                            else
                                                AlertBox.display("Room Price Error!","Bigger rooms must be more expensive than smaller ones!");
                                        }
                                        else
                                            AlertBox.display("Room Price Error!","Hotel's single room prices must be equal to cheapest nightly cost!");
                                    }
                                    else
                                        AlertBox.display("Hotel Year Of Built Error!","Hotel year of built must be between 1900-2018!");
                                }
                                else
                                    AlertBox.display("Hotel Nightly Cost Error!","Hotel's cheapest nightly cost must be at least 0!");
                            }
                            else
                                AlertBox.display("Hotel Number Of Rooms Error!","Hotel must have at least 10 rooms!");
                        }
                        else
                            AlertBox.display("Hotel Name Error!","Hotel names must start with a letter!");
                    }
                    else
                        AlertBox.display("Information Error!", "All spaces must be filled!");
                }
                else {
                    AlertBox.display("Number of Stars Error!", "Number of stars must be between 1 and 5");
                }
            }
            else {
                AlertBox.display("Number of Stars Error!", "Number of stars must be between 1 and 5");
            }
        }
         catch(Exception e){
             AlertBox.display("Unknown Error!", "An unknown error has occured!");
         }
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Parent signInParent = FXMLLoader.load(getClass().getResource("AdminMenuView.fxml"));
        Scene signInScene = new Scene(signInParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void keyReleased(KeyEvent e) {
        boolean checker = false;
        for(int i = 0; i<hotelNumberOfStarsText.getText().length(); i++){
            if(Character.isDigit(hotelNumberOfStarsText.getText().charAt(i)))
                checker = true;
            else
                checker = false;
        }
        if(hotelNumberOfStarsText.getText().length()>0 && checker) {
            if (Integer.parseInt(hotelNumberOfStarsText.getText()) <= 3) {
                hotelKingRoomPriceText.setDisable(true);
                hotelQueenRoomPriceText.setDisable(true);
                hotelQuadRoomPriceText.setDisable(true);
            } else if (Integer.parseInt(hotelNumberOfStarsText.getText()) > 3) {
                hotelKingRoomPriceText.setDisable(false);
                hotelQueenRoomPriceText.setDisable(false);
                hotelQuadRoomPriceText.setDisable(false);
            }
        }
    }
}
