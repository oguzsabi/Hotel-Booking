package BookingPack;

import CityPack.*;
import Hotelpack.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class CityAndHotelSelectionController implements Initializable {

    @FXML private ListView<String> cityList = new ListView<>();
    @FXML private ListView<String> hotelListForAllCities = new ListView<>();
    @FXML private Text cityOrHotelDescription;
    @FXML private Label cityOrHotelName;
    @FXML private AnchorPane descriptionAndImagePane;
    @FXML private Button nextButton;
    @FXML private Text cityPopulationText;
    @FXML private Text cityAltitudeText;
    @FXML private Text annualTouristVisitText;
    @FXML private Text hotelRoomAmountOrCityPopulationText;
    @FXML private Text yearOfBuiltOrCityAltitudeText;
    @FXML private Text nightlyCostOrAnnualTouristVisitText;
    @FXML private Label priceWarning;

    static private String selectedHotelName;
    static private String descriptionOfTheHotel;
    private NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
    private ArrayList<CityPack.City> cityNames = new ArrayList<>();
    private CityPack.City cityObject = new CityPack.City();
    private Hotel hotelObject = new Hotel();
    private String selectedCityName;
    static private String hotelValue;
    static private String cityValue;
    private RemoveCityController removeCityController;
    private String cityInformation = "City Information";
    private ObservableList<String> hotelObservableList = FXCollections.observableArrayList();
    private ObservableList<String> cityObservableList = FXCollections.observableArrayList();

    public ObservableList<String> getCityObservableList() {
        return cityObservableList;
    }

    public CityPack.City getCityObject() {
        return cityObject;
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Parent signInParent = FXMLLoader.load(getClass().getResource("SignInView.fxml"));
        Scene signInScene = new Scene(signInParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void nextButtonClicked(MouseEvent mouseEvent) throws IOException{
            if(!hotelListForAllCities.getSelectionModel().getSelectedItem().toString().equals("City Information") && hotelListForAllCities.getSelectionModel().getSelectedItem().toString().length()>0){
                selectedHotelName = cityOrHotelName.getText();
                descriptionOfTheHotel = cityOrHotelDescription.getText();
                Parent signInParent = FXMLLoader.load(getClass().getResource("HotelDetailsView.fxml"));
                Scene signInScene = new Scene(signInParent);

                Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                window.setScene(signInScene);
                window.show();
            }
            else
                AlertBox.display("Hotel Selection Error!","You must select a hotel to continue!");
    }

    public void myReservationsButtonClicked(MouseEvent mouseEvent) throws IOException{
        Parent signInParent = FXMLLoader.load(getClass().getResource("MyReservationsView.fxml"));
        Scene signInScene = new Scene(signInParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        cityNames.add(0, new Izmir()); cityNames.add(1, new Istanbul()); cityNames.add(2, new Ankara()); cityNames.add(3, new Antalya());
        cityNames.add(4, new Gaziantep()); cityNames.add(5, new Bursa()); cityNames.add(6, new Canakkale()); cityNames.add(7, new Edirne());

        removeCityController = new RemoveCityController();
        cityObservableList.addAll(cityObject.getDefaultCityNames());
        cityObservableList.addAll(removeCityController.addingAddedCitiesToTheList());
        cityList.setItems(cityObservableList);
        nextButton.setDisable(true);
        cityList.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newCityValue) -> citySelectionManager(newCityValue));
        cityList.getSelectionModel().selectFirst();
        priceWarning.setVisible(false);
        descriptionAndImagePane.setVisible(false);
        hotelListForAllCities.setVisible(false);
        hotelListForAllCities.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newHotelValue) -> hotelSelectionManager(newHotelValue));
    }

    public void citySelectionManager(String newCityValue){
        priceWarning.setVisible(false);
        nextButton.setDisable(false);
        cityValue = newCityValue;
        cityOrHotelName.setText(newCityValue);
        hotelObservableList.remove(1,hotelObservableList.size());
        hotelRoomAmountOrCityPopulationText.setText("City Population");
        yearOfBuiltOrCityAltitudeText.setText("City Altitude(m)");
        nightlyCostOrAnnualTouristVisitText.setText("Annual Tourist Visit");
        if(Save.readingCityInformationAndDisplayingItsHotels(cityValue, cityOrHotelName,cityOrHotelDescription,cityPopulationText, cityAltitudeText, annualTouristVisitText,hotelObservableList,hotelListForAllCities)){
            hotelObservableList.add(0,cityInformation);
            hotelListForAllCities.getSelectionModel().select(0);
            hotelObservableList.remove(1);
            descriptionAndImagePane.setVisible(true);
            hotelListForAllCities.setVisible(true);
        }

        if(cityValue.equals("Izmir")){
            selectedCityName = "Izmir";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(0).cityDescription()); //Run-time polymorphism is used here.
            cityPopulationText.setText(numberFormat.format(cityNames.get(0).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(0).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(0).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForIzmir());
            hotelListForAllCities.setItems(hotelObservableList);
            Save.addingTheAddedHotelsToTheCities(selectedCityName,hotelObservableList,hotelListForAllCities);
            hotelListForAllCities.getSelectionModel().select(1);
            hotelObservableList.remove(0);
            hotelListForAllCities.setVisible(true);
        }
        if(cityValue.equals("Istanbul")){
            selectedCityName = "Istanbul";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(1).cityDescription()); //Run-time polymorphism is used here.
            cityPopulationText.setText(numberFormat.format(cityNames.get(1).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(1).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(1).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForIstanbul());
            hotelListForAllCities.setItems(hotelObservableList);
            Save.addingTheAddedHotelsToTheCities(selectedCityName,hotelObservableList,hotelListForAllCities);
            hotelListForAllCities.getSelectionModel().select(1);
            hotelObservableList.remove(0);
            hotelListForAllCities.setVisible(true);
        }
        if(cityValue.equals("Ankara")){

            selectedCityName = "Ankara";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(2).cityDescription()); //Run-time polymorphism is used here.
            cityPopulationText.setText(numberFormat.format(cityNames.get(2).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(2).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(2).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForAnkara());
            hotelListForAllCities.setItems(hotelObservableList);
            Save.addingTheAddedHotelsToTheCities(selectedCityName,hotelObservableList,hotelListForAllCities);
            hotelListForAllCities.getSelectionModel().select(1);
            hotelObservableList.remove(0);
            hotelListForAllCities.setVisible(true);
        }
        if(cityValue.equals("Antalya")){
            selectedCityName = "Antalya";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(3).cityDescription()); //Run-time polymorphism is used here.
            cityPopulationText.setText(numberFormat.format(cityNames.get(3).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(3).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(3).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForAntalya());
            hotelListForAllCities.setItems(hotelObservableList);
            Save.addingTheAddedHotelsToTheCities(selectedCityName,hotelObservableList,hotelListForAllCities);
            hotelListForAllCities.getSelectionModel().select(1);
            hotelObservableList.remove(0);
            hotelListForAllCities.setVisible(true);
        }
        if(cityValue.equals("Gaziantep")){
            selectedCityName = "Gaziantep";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(4).cityDescription()); //Run-time polymorphism is used here.
            cityPopulationText.setText(numberFormat.format(cityNames.get(4).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(4).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(4).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForGaziantep());
            hotelListForAllCities.setItems(hotelObservableList);
            Save.addingTheAddedHotelsToTheCities(selectedCityName,hotelObservableList,hotelListForAllCities);
            hotelListForAllCities.getSelectionModel().select(1);
            hotelObservableList.remove(0);
            hotelListForAllCities.setVisible(true);
        }
        if(cityValue.equals("Bursa")){
            selectedCityName = "Bursa";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(5).cityDescription()); //Run-time polymorphism is used here.
            cityPopulationText.setText(numberFormat.format(cityNames.get(5).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(5).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(5).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForBursa());
            hotelListForAllCities.setItems(hotelObservableList);
            Save.addingTheAddedHotelsToTheCities(selectedCityName,hotelObservableList,hotelListForAllCities);
            hotelListForAllCities.getSelectionModel().select(1);
            hotelObservableList.remove(0);
            hotelListForAllCities.setVisible(true);
        }
        if(cityValue.equals("Canakkale")){
            selectedCityName = "Canakkale";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(6).cityDescription()); //Run-time polymorphism is used here.
            cityPopulationText.setText(numberFormat.format(cityNames.get(6).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(6).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(6).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForCanakkale());
            hotelListForAllCities.setItems(hotelObservableList);
            Save.addingTheAddedHotelsToTheCities(selectedCityName,hotelObservableList,hotelListForAllCities);
            hotelListForAllCities.getSelectionModel().select(1);
            hotelObservableList.remove(0);
            hotelListForAllCities.setVisible(true);
        }
        if(cityValue.equals("Edirne")){
            selectedCityName = "Edirne";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(7).cityDescription()); //Run-time polymorphism is used here.
            cityPopulationText.setText(numberFormat.format(cityNames.get(7).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(7).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(7).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForEdirne());
            hotelListForAllCities.setItems(hotelObservableList);
            Save.addingTheAddedHotelsToTheCities(selectedCityName,hotelObservableList,hotelListForAllCities);
            hotelListForAllCities.getSelectionModel().select(1);
            hotelObservableList.remove(0);
            hotelListForAllCities.setVisible(true);
        }
    }

    public static String getSelectedHotelName() {
        return selectedHotelName;
    }

    public static String getDescriptionOfTheHotel() {
        return descriptionOfTheHotel;
    }

    public static String getHotelValue() {
        return hotelValue;
    }

    public static String getCityValue() {
        return cityValue;
    }

    public void hotelSelectionManager(String newHotelValue){
        priceWarning.setVisible(true);
        hotelValue = newHotelValue;
        hotelRoomAmountOrCityPopulationText.setText("Number of Rooms");
        yearOfBuiltOrCityAltitudeText.setText("Year of Built");
        nightlyCostOrAnnualTouristVisitText.setText("Nightly Cost");
        Save.readingHotelInformation(cityValue,hotelValue,cityOrHotelName,cityPopulationText,cityAltitudeText,annualTouristVisitText,cityOrHotelDescription);

        //IZMIR HOTELS
        if(hotelValue.equals("Ramada Plaza ★★★★☆")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza Izmir; İzmir serves in Kahramanlar region. The facility is 26 km from Adnan Menderes Airport, 7 km from İzmir Bus Station and 2 km from the city center. The facility offers comfortable accommodation for its guests with its gentle staff and service understanding. The rooms that combine quality and comfort of the facility; safe, LCD TV, satellite TV, minibar and tea / coffee machine. Luggage storage and 24-hour reception are among the other facilities of the facility.");
            cityPopulationText.setText("207");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("195₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Wyndham Grand Özdilek ★★★★★")){

            selectedHotelName = "Wyndham Grand Özdilek";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Wyndham Grand İzmir Ozdilek is noted for its proximity to major attractions such as military museum ship, restaurants, marina, entertainment and shopping centers. Qualitasspa İzmir Agamemnon Thermal & Wellness Center has free use of the outdoor pool, indoor, jacuzzi, sauna, Turkish bath, aroma therapy, steam rooms and fitness center for its guests.");
            cityPopulationText.setText("219");
            cityAltitudeText.setText("2002");
            annualTouristVisitText.setText("301₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Ramada Encore ★★★★☆")){

            selectedHotelName = "Ramada Encore";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Situated close to the ancient city of Ephesus in Izmir's Balçova district, Ramada Encore Izmir offers comfortable accommodation for both leisure and business travelers. Guests can turn the pleasures of their seasons into seaview rooms.");
            cityPopulationText.setText("176");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("228₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Best Western Premier ★★★★☆")){

            selectedHotelName = "Best Western Premier";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Best Western Premier Karsiyaka; The historic and lively district of Izmir is located in Karsiyaka; sauna, swimming pool, fitness room and alternative massage facilities.");
            cityPopulationText.setText("118");
            cityAltitudeText.setText("2016");
            annualTouristVisitText.setText("273₺");
            descriptionAndImagePane.setVisible(true);

        }

        if(hotelValue.equals("Swissotel Grand Efes ★★★★★")){

            selectedHotelName = "Swissotel Grand Efes";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Swissotel Büyük Efes, with its 402 rooms that combine luxury and comfort, has been arranged to meet all the needs of business and leisure travelers. The legend offers an enjoyable stay with its outdoor pool");
            cityPopulationText.setText("402");
            cityAltitudeText.setText("2008");
            annualTouristVisitText.setText("502₺");
            descriptionAndImagePane.setVisible(true);

        }

        if(hotelValue.equals("Park Inn by Radisson ★★★★☆")){

            selectedHotelName = "Park Inn by Radisson";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Park Inn by Radisson Izmir is designed in every detail and meticulously modern style, in the heart of Izmir, within walking distance of the city's business, cultural and entertainment centers. The hotel has indoor parking and a gym for guests' use.");
            cityPopulationText.setText("137");
            cityAltitudeText.setText("2016");
            annualTouristVisitText.setText("236₺");
            descriptionAndImagePane.setVisible(true);

        }

        if(hotelValue.equals("Mövenpick ★★★★★")){

            selectedHotelName = "Mövenpick";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("2011 and by the World Travel Awards 2014, \"Turkey's Leading Business Hotel\" in selected and Green Globe certificate in the Mövenpick Hotel Izmir city center, within walking distance to business and shopping centers and fairgrounds, is a 5 star hotel.");
            cityPopulationText.setText("185");
            cityAltitudeText.setText("2008");
            annualTouristVisitText.setText("314₺");
            descriptionAndImagePane.setVisible(true);

        }

        if(hotelValue.equals("Buca Residence ★★★☆☆")){

            selectedHotelName = "Buca Residence";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Located in Izmir Buca, Buca Residence Hotel offers its guests modern and comfortable rooms, a blissful service and a pleasant holiday. The facility, which is close to many points of the city, is located 8 km from İzmir Bus Station.");
            cityPopulationText.setText("42");
            cityAltitudeText.setText("2015");
            annualTouristVisitText.setText("207₺");
            descriptionAndImagePane.setVisible(true);

        }

        if(hotelValue.equals("İbis Hotel ★★★☆☆")){

            selectedHotelName = "İbis Hotel ";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("İbis İzmir is located in Alsancak region. The hotel is 31 km from Adnan Menderes Airport, 2 km from Kordon and 7 km from Izmir Otogar. The facility only serves in the room concept. Room service is available. İbis İzmir Alsancak, located in Alsancak, the art, culture and trade center of the city; İzmir's meeting point is located 4 km from the Clock Tower and 3 km from Basmane Metro Station.");
            cityPopulationText.setText("140");
            cityAltitudeText.setText("2016");
            annualTouristVisitText.setText("177₺");
            descriptionAndImagePane.setVisible(true);

        }
        //ISTANBUL HOTELS//
        if(hotelValue.equals("Grand Naki ★★★★☆")){

            selectedHotelName = "Grand Naki";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Our hotel is only a few minutes walk from the historic center of Istanbul (Sultanahmet) where Topkapi Palace, Hagia Sophia Museum, Grand Bazaar, Yerebatan Palace, Hipodrum are located. Sound and heat-insulated windows, minibar, tea-coffee kettle with local foreign TV phone, wireless high-speed internet connection, electronic safe, telephone and hair dryer in bathroom.");
            cityPopulationText.setText("26");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("399₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Levent Hotel ★★★☆☆")){

            selectedHotelName = "Levent Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("51");
            cityAltitudeText.setText("2000");
            annualTouristVisitText.setText("276₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Lionel Hotel ★★★★★")){

            selectedHotelName = "Lionel Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("LIONEL HOTEL ISTANBUL BAYRAMPASA is a 5-star hotel located in the Bayrampaşa district of the city of Istanbul. Ataturk Airport is 10 km away, Forum istanbul Avm 2 km, Axsis Avm 2 km away. The warm, sincere service conception, which is thought to the finest detail of every detail, makes its guests feel comfortable with its modern and stylish decoration.");
            cityPopulationText.setText("230");
            cityAltitudeText.setText("2012");
            annualTouristVisitText.setText("385₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Park Dedeman Levent ★★★★☆")){

            selectedHotelName = "Park Dedeman Levent";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("The youngest and most dynamic addition to Dedeman Hotels & Resorts International, Park Dedeman Levent, is also the first hotel of the chain in Istanbul with the “Park” concept. " +
                    "Located in the heart of Turkey’s trade capital Istanbul’s central business and shopping district Levent, Park Dedeman Levent stands out as one of the city’s leading buildings with its LEED Silver certified environmentally friendly construction and contemporary architecture." +
                    "The hotel, situated right next to the Levent subway station, is also in close proximity to the city’s arts and culture, shopping and entertainment venues, and provides easy access to both international airports. ");
            cityPopulationText.setText("232");
            cityAltitudeText.setText("2015");
            annualTouristVisitText.setText("410₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Mercia Hotel ★★★★☆")){

            selectedHotelName = "Mercia Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Mercia Hotel & Resorts is a business hotel located in Kumburgaz, the residential area of \u200B\u200BIstanbul and has been renovated and opened in March 2012. We are pleased to serve you and your guests. Our hotel is located on the E-5 road side in Kumburgaz, 10 min. Ataturk airport, Büyükçekmece city center, business centers and shopping centers.");
            cityPopulationText.setText("89");
            cityAltitudeText.setText("2011");
            annualTouristVisitText.setText("173₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Sheraton Hotel ★★★★★")){

            selectedHotelName = "Sheraton Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Sheraton Ataköy is located in the Ataköy district of Istanbul. quality service concept, seaview and comfortable rooms, equipped meeting rooms, outdoor and indoor swimming pools are located 8 km from Ataturk Airport.");
            cityPopulationText.setText("285");
            cityAltitudeText.setText("2008");
            annualTouristVisitText.setText("738₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Grand Ant Hotel ★★★☆☆")){

            selectedHotelName = "Grand Ant Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("74");
            cityAltitudeText.setText("1990");
            annualTouristVisitText.setText("250₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Biz Cevahir Hotel ★★★★★")){

            selectedHotelName = "Biz Cevahir Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("We have a total of 104 rooms spread over 9 floors, with our convenient meeting rooms, indoor swimming pool, sauna, Turkish bath, massage rooms and sports center and our special SPA and Zeytinaylı Restaurant offering excellent examples of Mediterranean cuisine. Each of our Superior, Deluxe and Suites suites are thoughtfully designed for your comfort, with a tastefully furnished, perfectionist approach hidden in detail.");
            cityPopulationText.setText("104");
            cityAltitudeText.setText("2013");
            annualTouristVisitText.setText("473₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Jazz Hotel ★★★★☆")){

            selectedHotelName = "Jazz Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Jazz Hotel welcomes its guests in stylish and well-equipped rooms, inspired by world famous jazz artists, at Semti Nişantaşı, which provides easy access to centers that have an important place in Istanbul's business and cultural life.");
            cityPopulationText.setText("22");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("410₺");
            descriptionAndImagePane.setVisible(true);
        }
        //ANKARA HOTELS
        if(hotelValue.equals("Point Hotel Ankara ★★★★☆")){

            selectedHotelName = "Point Hotel Ankara";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("2 km from the Next Level and Armada shopping centers, 500 m from the Taurus shopping center. Ankara ATO International Convention Exhibition Center and many other cultural and arts centers. The hotel is 34 km away from the Esenboğa International Airport. The capital of Turkey in Ankara Business World in the heart of the first 5-star Art-Tech hotel in Ankara remarkable located and modern architecture of ours Point Hotel Ankara, It is in a superb location for business travelers who are close to the ministries, government buildings and headquarters of international companies.");
            cityPopulationText.setText("80");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("308₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Hotel Abro Necatibey ★★★☆☆")){

            selectedHotelName = "Hotel Abro Necatibey";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Our hotel is on the center of Ankara Kızılay Necatibey Street; close to many ministries, business centers, shops, restaurants, tourist agencies, concert halls, cinemas and theaters, exhibition halls, many historic sites, hospitals, embassies and metro.\n");
            cityPopulationText.setText("68");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("110₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Hotel İçkale ★★★★★")){

            selectedHotelName = "Hotel İçkale";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("122");
            cityAltitudeText.setText("2011");
            annualTouristVisitText.setText("188₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Bera Ankara ★★★★★")){

            selectedHotelName = "Bera Ankara ";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("209");
            cityAltitudeText.setText("2017");
            annualTouristVisitText.setText("227₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Anemon ★★★★☆")){

            selectedHotelName = "Anemon";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText(" Anemon Ankara offers services in Kavaklıdere with a comfortable environment for both business and tourism oriented accomodations. There are possibilities such as testimony, massage and meeting room located 1 km from Ankara city center and 4 km from Anıtkabir.");
            cityPopulationText.setText("83");
            cityAltitudeText.setText("2007");
            annualTouristVisitText.setText("209₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Gür Kent ★★★★☆")){

            selectedHotelName = "Gür Kent";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Offering comfortable accommodation for both business and leisure travelers, Gür Kent Hotel offers comfortable accommodation options close to places such as Kızılay Square and Youth Park.");
            cityPopulationText.setText("117");
            cityAltitudeText.setText("2012");
            annualTouristVisitText.setText("149₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Dafne ★★★☆☆")){

            selectedHotelName = "Dafne";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Located on Konya Road, Dafne Hotel is 3 km away from Aşti. 45 km to Esenboğa Airport. 7 km to the State Railways. Away. Anitkabir and Kızılay are 5 km on average. Away. Dafne Hotel is located in Balgat, one of Ankara's highlights. Dafne Hotel has a capacity of 155 rooms with 72 rooms including single.Double.Triple and suıt rooms which will be very pleased with you.");
            cityPopulationText.setText("72");
            cityAltitudeText.setText("2015");
            annualTouristVisitText.setText("175₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Swissotel ★★★★★")){

            selectedHotelName = "Swissotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Swissôtel Ankara, located in Çankaya, the elite district of the capital Ankara, serves its guests with its modern building and unique equipment. Swissotel Ankara redefines Ankara's splendor and comfort: Amrita Spa & Wellness enjoy a luxurious life. Swissotel Ankara is close to the Presidential Palace and many embassies. It is only 10 minutes to the city center and 45 minutes to Esenboğa Airport, making it easy to reach shopping centers.");
            cityPopulationText.setText("150");
            cityAltitudeText.setText("2010");
            annualTouristVisitText.setText("458₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Maltepe 2000 ★★★☆☆")){

            selectedHotelName = "Maltepe 2000";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("35");
            cityAltitudeText.setText("2013");
            annualTouristVisitText.setText("281₺");
            descriptionAndImagePane.setVisible(true);
        }
        //ANTALYA HOTELS

        if(hotelValue.equals("Golden Sun ★★★☆☆")){

            selectedHotelName = "Golden Sun";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("80");
            cityAltitudeText.setText("2010");
            annualTouristVisitText.setText("130₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Medusa ★★★☆☆")){

            selectedHotelName = "Medusa";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("85");
            cityAltitudeText.setText("2012");
            annualTouristVisitText.setText("141₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Ramada Plaza ★★★★★")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("230");
            cityAltitudeText.setText("2008");
            annualTouristVisitText.setText("200₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Throne Sea Gate ★★★★★")){

            selectedHotelName = "Throne Sea Gate";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("180");
            cityAltitudeText.setText("2015");
            annualTouristVisitText.setText("223₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Orange Country ★★★★★")){

            selectedHotelName = "Orange Country";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("193");
            cityAltitudeText.setText("2005");
            annualTouristVisitText.setText("178₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Angora Hotel ★★★☆☆")){

            selectedHotelName = "Angora Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("75");
            cityAltitudeText.setText("2011");
            annualTouristVisitText.setText("140₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Portakal Bahçesi Hotel ★★★★★")){

            selectedHotelName = "Portakal Bahçesi Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("160");
            cityAltitudeText.setText("2016");
            annualTouristVisitText.setText("185₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("DoubleTree by Hilton ★★★★★")){

            selectedHotelName = "DoubleTree by Hilton";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("250");
            cityAltitudeText.setText("2013");
            annualTouristVisitText.setText("210₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Olea Nova ★★★☆☆")){

            selectedHotelName = "Olea Nova";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("65");
            cityAltitudeText.setText("2009");
            annualTouristVisitText.setText("155₺");
            descriptionAndImagePane.setVisible(true);
        }

        //GAZIANTEP HOTELS
        if(hotelValue.equals("Royal Gaziantep ★★★★☆")){

            selectedHotelName = "Royal Gaziantep";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("110");
            cityAltitudeText.setText("2015");
            annualTouristVisitText.setText("132₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Best Western ★★★★☆")){

            selectedHotelName = "Best Western";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("128");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("154₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Utku Bey Hotel ★★☆☆☆")){

            selectedHotelName = "Utku Bey Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("33");
            cityAltitudeText.setText("2017");
            annualTouristVisitText.setText("102₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Dedeman Park ★★★★☆")){

            selectedHotelName = "Dedeman Park";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("121");
            cityAltitudeText.setText("2010");
            annualTouristVisitText.setText("139₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Büyük Velic ★★★★☆")){

            selectedHotelName = "Büyük Velic";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("105");
            cityAltitudeText.setText("2012");
            annualTouristVisitText.setText("127₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("İbis Hotel ★★★☆☆")){

            selectedHotelName = "İbis Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("90");
            cityAltitudeText.setText("2007");
            annualTouristVisitText.setText("112₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Yunus Hotel ★★☆☆☆")){

            selectedHotelName = "Yunus Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("25");
            cityAltitudeText.setText("2009");
            annualTouristVisitText.setText("100₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Grand Hotel ★★★★★")){

            selectedHotelName = "Grand Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("270");
            cityAltitudeText.setText("2002");
            annualTouristVisitText.setText("250₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Küçük Velic Hotel ★★★☆☆")){

            selectedHotelName = "Küçük Velic Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("70");
            cityAltitudeText.setText("2006");
            annualTouristVisitText.setText("118₺");
            descriptionAndImagePane.setVisible(true);
        }

        //BURSA HOTELS
        if(hotelValue.equals("Kervansaray Hotel ★★★☆☆")){

            selectedHotelName = "Kervansaray Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("59");
            cityAltitudeText.setText("2011");
            annualTouristVisitText.setText("106₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Ramada Plaza ★★★☆☆")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("40");
            cityAltitudeText.setText("2016");
            annualTouristVisitText.setText("147₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Heybeli Hotel ★★★★☆")){

            selectedHotelName = "Heybeli Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("115");
            cityAltitudeText.setText("2009");
            annualTouristVisitText.setText("203₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Tuğcu Hotel ★★★★☆")){

            selectedHotelName = "Tuğcu Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("100");
            cityAltitudeText.setText("2010");
            annualTouristVisitText.setText("212₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Green Pursa Hotel ★★★☆☆")){

            selectedHotelName = "Green Pursa Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("62");
            cityAltitudeText.setText("2004");
            annualTouristVisitText.setText("120₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Grand Turkuaz Hotel ★★★☆☆")){

            selectedHotelName = "Grand Turkuaz Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("48");
            cityAltitudeText.setText("2007");
            annualTouristVisitText.setText("133₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Mercure Hotel ★★★★★")){

            selectedHotelName = "Mercure Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("165");
            cityAltitudeText.setText("2013");
            annualTouristVisitText.setText("225₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Balam Residance ★★☆☆☆")){

            selectedHotelName = "Balam Residance";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("20");
            cityAltitudeText.setText("2017");
            annualTouristVisitText.setText("100₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Doğalya Hotel ★★★☆☆")){

            selectedHotelName = "Doğalya Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("55");
            cityAltitudeText.setText("2005");
            annualTouristVisitText.setText("111₺");
            descriptionAndImagePane.setVisible(true);
        }

        //CANAKKALE HOTELS
        if(hotelValue.equals("Esida Hotel ★★★☆☆")){

            selectedHotelName = "Esida Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("61");
            cityAltitudeText.setText("2013");
            annualTouristVisitText.setText("105₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Parion Hotel ★★★★★")){

            selectedHotelName = "Parion Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("216");
            cityAltitudeText.setText("2006");
            annualTouristVisitText.setText("243₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Figen Hotel ★★★☆☆")){

            selectedHotelName = "Figen Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("47");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("143₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Grand Ece Hotel ★★★☆☆")){

            selectedHotelName = "Grand Ece Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("51");
            cityAltitudeText.setText("2012");
            annualTouristVisitText.setText("114₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Flora Hotel ★★★☆☆")){

            selectedHotelName = "Flora Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("39");
            cityAltitudeText.setText("2015");
            annualTouristVisitText.setText("109₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Troia Tusan Hotel ★★★★☆")){

            selectedHotelName = "Troia Tusan Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("132");
            cityAltitudeText.setText("2007");
            annualTouristVisitText.setText("199₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Vitis Hotel ★★★☆☆")){

            selectedHotelName = "Vitis Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("67");
            cityAltitudeText.setText("2004");
            annualTouristVisitText.setText("116₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Maidos Hotel ★★★★☆")){

            selectedHotelName = "Maidos Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("134");
            cityAltitudeText.setText("2011");
            annualTouristVisitText.setText("202₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Iola Hotel ★★★☆☆")){

            selectedHotelName = "Iola Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("72");
            cityAltitudeText.setText("2010");
            annualTouristVisitText.setText("145₺");
            descriptionAndImagePane.setVisible(true);
        }

        //EDIRNE HOTELS
        if(hotelValue.equals("Efe Hotel ★★★☆☆")){

            selectedHotelName = "Efe Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("55");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("130₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Selimiye Hotel ★★★☆☆")){

            selectedHotelName = "Selimiye Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("62");
            cityAltitudeText.setText("2013");
            annualTouristVisitText.setText("123₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Edrin Hotel ★★★★☆")){

            selectedHotelName = "Edrin Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("86");
            cityAltitudeText.setText("2017");
            annualTouristVisitText.setText("197₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Kalevera Hotel ★★★☆☆")){

            selectedHotelName = "Kalevera Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("68");
            cityAltitudeText.setText("2005");
            annualTouristVisitText.setText("115₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Ramada Hotel ★★★☆☆")){

            selectedHotelName = "Ramada Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("52");
            cityAltitudeText.setText("2009");
            annualTouristVisitText.setText("110₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Şimsek Hotel ★★☆☆☆")){

            selectedHotelName = "Şimsek Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("28");
            cityAltitudeText.setText("2017");
            annualTouristVisitText.setText("101₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Balta Hotel ★★☆☆☆")){

            selectedHotelName = "Balta Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("36");
            cityAltitudeText.setText("2016");
            annualTouristVisitText.setText("109₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Margi Hotel ★★★★★")){

            selectedHotelName = "Margi Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("221");
            cityAltitudeText.setText("2017");
            annualTouristVisitText.setText("216₺");
            descriptionAndImagePane.setVisible(true);
        }
        if(hotelValue.equals("Saros Hotel ★★★★☆")){

            selectedHotelName = "Saros Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("All rooms have direct telephone, minibar, tv, central air conditioning, shower, bathtub, wc, hair dryer, toilet phone, fire alarm system, sound and heat proof windows. There is a 24 hour multilingual newspaper There are central air conditioning and heating, tv, wireless internet, cafe, ironing, dry cleaning, safe box, 24 hour room service, generator, services are available. The hotel also offers breakfast room, service and Turkish and International cuisine.");
            cityPopulationText.setText("120");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("208₺");
            descriptionAndImagePane.setVisible(true);
        }
    }
}