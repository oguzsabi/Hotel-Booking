package BookingPack;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
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
import java.util.ResourceBundle;
import java.util.Scanner;

public class RemoveCityController implements Initializable {
    @FXML private CheckBox iAmSureBox;
    @FXML private ListView<String> cityList = new ListView<>();
    private CityAndHotelSelectionController cityControllerObject = new CityAndHotelSelectionController();
    private Scanner readingFile;
    private File cityInformation = new File("cityInformation.txt");
    private ArrayList<String> cityNames = new ArrayList<>();

    public void initialize(URL url, ResourceBundle resourceBundle){
        cityControllerObject.getCityObservableList().addAll(cityControllerObject.getCityObject().getDefaultCityNames());
        addingAddedCitiesToTheList();
        cityList.setItems(cityControllerObject.getCityObservableList());
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Parent signInParent = FXMLLoader.load(getClass().getResource("AdminMenuView.fxml"));
        Scene signInScene = new Scene(signInParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void removeButtonClicked(MouseEvent mouseEvent) throws IOException{
        if(iAmSureBox.isSelected()){
            if(!cityControllerObject.getCityObservableList().isEmpty()){
                String cityName = cityList.getSelectionModel().getSelectedItem().toString();
                Save.removingCity('☭' + cityName);
                cityList.getItems().remove(cityList.getSelectionModel().getSelectedItem());
            }
        }
        else
            AlertBox.display("Are you sure?","You must check the box if you want to remove cities.");
    }

    public ArrayList<String> addingAddedCitiesToTheList(){
        int flag = -1;
        try {
            readingFile = new Scanner(cityInformation);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        while(readingFile.hasNext()) {
            String cityName = readingFile.nextLine();
            if(cityName.length()>0) {
                if (cityName.charAt(0) == '☭') {
                    flag++;
                }
            }
            if(flag == 0 || flag%5==0){
                cityNames.add(cityName.substring(1));
            }
        }
        cityControllerObject.getCityObservableList().addAll(cityNames);
        return cityNames;
    }
}
