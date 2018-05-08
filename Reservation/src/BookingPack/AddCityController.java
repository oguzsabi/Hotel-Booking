package BookingPack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCityController {
    @FXML private TextField cityName;
    @FXML private TextField cityPopulation;
    @FXML private TextField cityAnnualTouristVisit;
    @FXML private TextField cityAltitude;
    @FXML private TextArea cityDescription;

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Parent signInParent = FXMLLoader.load(getClass().getResource("AdminMenuView.fxml"));
        Scene signInScene = new Scene(signInParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void submitButtonClicked(MouseEvent mouseEvent) throws IOException {
        boolean cityNameChecker = true;
        boolean cityPopulationChecker = true;
        boolean cityAltitudeChecker = true;
        boolean cityAnnualVisitChecker = true;

        if(cityName.getText().length()>0 && cityDescription.getText().length()>0 && cityPopulation.getText().length()>0 && cityAltitude.getText().length()>0 && cityAnnualTouristVisit.getText().length()>0) {
            for (int i = 0; i < cityName.getText().length(); i++) {
                if (!Character.isLetter(cityName.getText().charAt(i))) {
                    cityNameChecker = false;
                    AlertBox.display("City Name Error", "Invalid city name entered!");
                    break;
                }
            }

            if (cityNameChecker && cityName.getText().length() > 0) {
                for (int i = 0; i < cityPopulation.getText().length(); i++) {
                    if (!Character.isDigit(cityPopulation.getText().charAt(i))) {
                        cityPopulationChecker = false;
                        AlertBox.display("City Population Error", "City population must be all digits!");
                        break;
                    }
                }
                if (cityPopulationChecker && Integer.parseInt(cityPopulation.getText()) > 0) {
                    for (int i = 0; i < cityAltitude.getText().length(); i++) {
                        if (!Character.isDigit(cityAltitude.getText().charAt(i)) || Double.parseDouble(cityAltitude.getText()) > 5131 || Integer.parseInt(cityAltitude.getText()) < -424) {
                            cityAltitudeChecker = false;
                            AlertBox.display("City Altitude Error", "City altitude must be all digits and between (-424,5131)");
                            break;
                        }
                    }
                    if (cityAltitudeChecker && Double.parseDouble(cityAltitude.getText()) < 5131 && Integer.parseInt(cityAltitude.getText()) > -424) {
                        for (int i = 0; i < cityAnnualTouristVisit.getText().length(); i++) {
                            if (!Character.isDigit(cityAnnualTouristVisit.getText().charAt(i))) {
                                cityAnnualVisitChecker = false;
                                AlertBox.display("City Annual Tourist Visit Error", "City annual tourist visit must be all digits!");
                                break;
                            }
                        }
                        if (cityAnnualVisitChecker && Integer.parseInt(cityAnnualTouristVisit.getText()) >= 0) {
                            AlertBox.display("Successful Activity!","The city has been added!");
                            CityPack.City cityObject = new CityPack.City(cityName.getText(), cityDescription.getText(), Double.parseDouble(cityAltitude.getText()), Integer.parseInt(cityPopulation.getText()), Integer.parseInt(cityAnnualTouristVisit.getText()));
                            Save.addingCity(cityObject.getCityName(),cityObject.getCityDescription(),cityObject.getCityPopulation(),cityObject.getCityAltitude(),cityObject.getAnnualTouristVisit());
                            Parent signInParent = FXMLLoader.load(getClass().getResource("AdminMenuView.fxml"));
                            Scene signInScene = new Scene(signInParent);

                            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                            window.setScene(signInScene);
                            window.show();
                        }
                    }
                }
            }
        }
        else
            AlertBox.display("City Creation Error", "All spaces must be filled!");
    }
}
