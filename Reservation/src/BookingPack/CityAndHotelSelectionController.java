package BookingPack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class CityAndHotelSelectionController implements Initializable {

    @FXML private ListView<String> cityList = new ListView<>();
    @FXML private ListView<String> hotelListIzmir = new ListView<>();
    private City cityObject = new City();
    private Hotel hotelObject = new Hotel();

    public void initialize(URL url, ResourceBundle resourceBundle){
        ObservableList<String> cityObservableList = FXCollections.observableArrayList();
        cityObservableList.addAll(cityObject.getDefaultCityNames());
        cityList.setItems(cityObservableList);

        ObservableList<String> hotelObservableList = FXCollections.observableArrayList();
        hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForIzmir());
        hotelListIzmir.setItems(hotelObservableList);
        hotelListIzmir.setVisible(false);

        cityList.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> selectionManager(newValue));
    }

    public void selectionManager(String newValue){
        if(newValue.equals("Izmir")){
            hotelListIzmir.setVisible(true);
        }
    }
}


