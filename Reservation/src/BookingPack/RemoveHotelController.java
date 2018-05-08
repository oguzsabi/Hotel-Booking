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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.Scanner;

public class RemoveHotelController implements Initializable{
    @FXML private ListView<String> cityList = new ListView<>();
    @FXML private ListView<String> hotelList = new ListView<>();
    @FXML private CheckBox iAmSure;
    private Scanner readingFile;
    private CityAndHotelSelectionController cityControllerObject = new CityAndHotelSelectionController();
    private RemoveCityController removeCityController = new RemoveCityController();
    private ObservableList<String> hotelNamesObservable = FXCollections.observableArrayList();
    private File fileThree;
    private File fileFive;

    public void initialize(URL url, ResourceBundle resourceBundle){
        cityControllerObject.getCityObservableList().addAll(cityControllerObject.getCityObject().getDefaultCityNames());
        cityControllerObject.getCityObservableList().addAll(removeCityController.addingAddedCitiesToTheList());
        cityList.setItems(cityControllerObject.getCityObservableList());
        cityList.getSelectionModel().selectFirst();
        cityList.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue) -> displayHotelsOfTheCity(newValue));
    }

    public void removeButtonClicked(MouseEvent mouseEvent) throws IOException{
        if(iAmSure.isSelected()){
            String cityName = cityList.getSelectionModel().getSelectedItem().toString();
            String hotelName = hotelList.getSelectionModel().getSelectedItem().toString();
            File fileFive = new File(cityName + "HotelsFive.txt");
            File fileThree = new File(cityName + "HotelsThree.txt");
            try {
                readingFile = new Scanner(fileFive);
            }
            catch (FileNotFoundException e) {
                AlertBox.display("File Not Found Error!","You first need to create such a file to access it!");
            }
            if(fileFive.exists()){
                int hotelLength = hotelName.length();
                Save.removingHotelFiveStars(fileFive,'☭'+hotelName.substring(0,(hotelLength-7)));
            }
            else
                AlertBox.display("File Not Found Error!","You first need to create such a file to access it!");

            try {
                readingFile = new Scanner(fileThree);
            }
            catch (FileNotFoundException e) {
                AlertBox.display("File Not Found Error!","You first need to create such a file to access it!");
            }
            if(fileThree.exists()){
                int hotelLength = hotelName.length();
                Save.removingHotelThreeStars(fileThree,'☭'+hotelName.substring(0,(hotelLength-7)));
            }
            else
                AlertBox.display("File Not Found Error!","You first need to create such a file to access it!");

            hotelList.getItems().remove(hotelList.getSelectionModel().getSelectedItem());
        }
        else
            AlertBox.display("Are you sure?","You must check the box if you want to remove hotels.");
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Parent signInParent = FXMLLoader.load(getClass().getResource("AdminMenuView.fxml"));
        Scene signInScene = new Scene(signInParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void displayHotelsOfTheCity(String cityName){
        hotelNamesObservable.remove(0,hotelNamesObservable.size());
        String cityHotelsFiveTxtFile = cityName + "HotelsFive.txt";
        String cityHotelsThreeTxtFile = cityName + "HotelsThree.txt";
        try{
            fileFive = new File(cityHotelsFiveTxtFile);
            fileThree = new File(cityHotelsThreeTxtFile);
            if(fileFive.exists()){
                ArrayList<String> hotelNames = new ArrayList<>();
                int flag = -1;
                int afterHotelNameCounter;
                try {
                    readingFile = new Scanner(fileFive);
                }
                catch (FileNotFoundException e) {
                    AlertBox.display("File Not Found Error!","You first need to create such a file to access it!");
                }

                String hotelNameWithStars = "";
                while(readingFile.hasNext()) {
                    String line = readingFile.nextLine();
                    String hotelName;
                    if(line.length()>0) {
                        if (line.charAt(0) == '☭') {
                            flag++;
                        }
                    }
                    if(flag == 0 || flag%12==0){
                        hotelName = line.substring(1);
                        hotelNameWithStars = hotelNameWithStars + hotelName + " ";
                        afterHotelNameCounter = 0;
                        while(readingFile.hasNext()){
                            line = readingFile.nextLine();
                            if(line.charAt(0) == '☭'){
                                afterHotelNameCounter++;
                                flag++;
                                if(afterHotelNameCounter == 2){
                                    hotelNameWithStars = hotelNameWithStars + line.substring(3) + "\n";
                                    hotelNames.add(hotelNameWithStars);
                                    hotelNameWithStars = "";
                                    break;
                                }
                            }
                        }
                    }
                }
                hotelNamesObservable.addAll(hotelNames);
                hotelList.setItems(hotelNamesObservable);
            }
            if(fileThree.exists()){
                ArrayList<String> hotelNames = new ArrayList<>();
                int flag = -1;
                int afterHotelNameCounter;
                try {
                    readingFile = new Scanner(fileThree);
                }
                catch (FileNotFoundException e) {
                    AlertBox.display("File Not Found Error!","You first need to create such a file to access it!");
                }

                String hotelNameWithStars = "";
                while(readingFile.hasNext()) {
                    String line = readingFile.nextLine();
                    String hotelName;
                    if(line.length()>0) {
                        if (line.charAt(0) == '☭') {
                            flag++;
                        }
                    }
                    if(flag == 0 || flag%9==0){
                        hotelName = line.substring(1);
                        hotelNameWithStars = hotelNameWithStars + hotelName + " ";
                        afterHotelNameCounter = 0;
                        while(readingFile.hasNext()){
                            line = readingFile.nextLine();
                            if(line.charAt(0) == '☭'){
                                afterHotelNameCounter++;
                                flag++;
                                if(afterHotelNameCounter == 2){
                                    hotelNameWithStars = hotelNameWithStars + line.substring(3) + "\n";
                                    hotelNames.add(hotelNameWithStars);
                                    hotelNameWithStars = "";
                                    break;
                                }
                            }
                        }
                    }
                }
                hotelNamesObservable.addAll(hotelNames);
                hotelList.setItems(hotelNamesObservable);
            }
            if(!fileFive.exists() && !fileThree.exists())
                AlertBox.display("File Not Found Error!","You first need to create such a file to access it!");
        }
        catch(Exception e){
            AlertBox.display("Unknown Error!", "An unknown error has occured!");
        }
    }
}
