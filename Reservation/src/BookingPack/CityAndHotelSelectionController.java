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
    @FXML private ImageView cityImage;
    @FXML private AnchorPane descriptionAndImagePane;
    @FXML private Button backButton;
    @FXML private Button nextButton;
    @FXML private Text cityPopulationText;
    @FXML private Text cityAltitudeText;
    @FXML private Text annualTouristVisitText;
    @FXML private Text hotelRoomAmountOrCityPopulationText;
    @FXML private Text yearOfBuiltOrCityAltitudeText;
    @FXML private Text nightlyCostOrAnnualTouristVisitText;

    private NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
    private ArrayList<City> cityNames = new ArrayList<>();
    private City cityObject = new City();
    private Hotel hotelObject = new Hotel();
    private String selectedCityName;
    private String selectedHotelName;
    private String hotelValue;
    private String cityValue;
    private ObservableList<String> hotelObservableList = FXCollections.observableArrayList();

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Parent signInParent = FXMLLoader.load(getClass().getResource("SignInView.fxml"));
        Scene signInScene = new Scene(signInParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        cityNames.add(0, new Izmir()); cityNames.add(1, new Istanbul()); cityNames.add(2, new Ankara()); cityNames.add(3, new Antalya());
        cityNames.add(4, new Gaziantep()); cityNames.add(5, new Bursa()); cityNames.add(6, new Canakkale()); cityNames.add(7, new Denizli());
        cityNames.add(8, new Edirne());

        ObservableList<String> cityObservableList = FXCollections.observableArrayList();
        cityObservableList.addAll(cityObject.getDefaultCityNames());
        cityList.setItems(cityObservableList);
        descriptionAndImagePane.setVisible(false);
        hotelListForAllCities.setVisible(false);
        cityList.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newCityValue) -> citySelectionManager(newCityValue));
        hotelListForAllCities.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newHotelValue) -> hotelSelectionManager(newHotelValue));
    }

    public void citySelectionManager(String newCityValue){
        cityValue = newCityValue;
        hotelObservableList.remove(0,hotelObservableList.size()-1);
        hotelRoomAmountOrCityPopulationText.setText("City Population");
        yearOfBuiltOrCityAltitudeText.setText("City Altitude(m)");
        nightlyCostOrAnnualTouristVisitText.setText("Annual Tourist Visit");

        if(cityValue.equals("Izmir")){
            selectedCityName = "Izmir";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(0).cityDescription());
            cityPopulationText.setText(numberFormat.format(cityNames.get(0).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(0).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(0).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForIzmir());
            hotelListForAllCities.setItems(hotelObservableList);
            hotelListForAllCities.setVisible(true);
        }
        if(cityValue.equals("Istanbul")){
            selectedCityName = "Istanbul";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(1).cityDescription());
            cityPopulationText.setText(numberFormat.format(cityNames.get(1).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(1).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(1).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForIstanbul());
            hotelListForAllCities.setItems(hotelObservableList);
            hotelListForAllCities.setVisible(true);
        }
        if(cityValue.equals("Ankara")){

            selectedCityName = "Ankara";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(2).cityDescription());
            cityPopulationText.setText(numberFormat.format(cityNames.get(2).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(2).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(2).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForAnkara());
            hotelListForAllCities.setItems(hotelObservableList);
            hotelListForAllCities.setVisible(true);
        }
        if(cityValue.equals("Antalya")){
            selectedCityName = "Antalya";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(3).cityDescription());
            cityPopulationText.setText(numberFormat.format(cityNames.get(3).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(3).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(3).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForAntalya());
            hotelListForAllCities.setItems(hotelObservableList);
            hotelListForAllCities.setVisible(true);
        }
        if(cityValue.equals("Gaziantep")){
            selectedCityName = "Gaziantep";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(4).cityDescription());
            cityPopulationText.setText(numberFormat.format(cityNames.get(4).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(4).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(4).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForGaziantep());
            hotelListForAllCities.setItems(hotelObservableList);
            hotelListForAllCities.setVisible(true);
        }
        if(cityValue.equals("Bursa")){
            selectedCityName = "Bursa";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(5).cityDescription());
            cityPopulationText.setText(numberFormat.format(cityNames.get(5).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(5).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(5).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForBursa());
            hotelListForAllCities.setItems(hotelObservableList);
            hotelListForAllCities.setVisible(true);
        }
        if(cityValue.equals("Canakkale")){
            selectedCityName = "Canakkale";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(6).cityDescription());
            cityPopulationText.setText(numberFormat.format(cityNames.get(6).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(6).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(6).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForCanakkale());
            hotelListForAllCities.setItems(hotelObservableList);
            hotelListForAllCities.setVisible(true);
        }
        if(cityValue.equals("Edirne")){
            selectedCityName = "Edirne";
            cityOrHotelName.setText(newCityValue);
            cityOrHotelDescription.setText(cityNames.get(8).cityDescription());
            cityPopulationText.setText(numberFormat.format(cityNames.get(8).getCityPopulation()));
            cityAltitudeText.setText(numberFormat.format(cityNames.get(8).getCityAltitude()));
            annualTouristVisitText.setText(numberFormat.format(cityNames.get(8).getAnnualTouristVisit()));
            descriptionAndImagePane.setVisible(true);
            hotelObservableList.addAll(hotelObject.getDefaultHotelNamesForEdirne());
            hotelListForAllCities.setItems(hotelObservableList);
            hotelListForAllCities.setVisible(true);
        }
    }

    public void imageClicked(MouseEvent mouseEvent){
        cityImage.getImage();
    }

    public String getSelectedCityName() {
        return selectedCityName;
    }

    public void hotelSelectionManager(String newHotelValue){
        hotelValue = newHotelValue;
        hotelRoomAmountOrCityPopulationText.setText("Number of Rooms");
        yearOfBuiltOrCityAltitudeText.setText("Year of Built");
        nightlyCostOrAnnualTouristVisitText.setText("Nightly Cost");

        //IZMIR HOTELS

        if(hotelValue.equals("Ramada Plaza ★★★★☆")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                                           "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                                           "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                                           "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("207");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("195₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Wyndham Grand Özdilek ★★★★★")){

            selectedHotelName = "Wyndham Grand Özdilek";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("219");
            cityAltitudeText.setText("2002");
            annualTouristVisitText.setText("301₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Ramada Encore ★★★★☆")){

            selectedHotelName = "Ramada Encore";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("İzmir’in Balçova ilçesinde, Efes Antik kentine yakın konumda bulunan Ramada Encore İzmir, hem tatil hem de iş seyahatleri için konforlu bir konaklama sunuyor." +
                    "\n Misafirler, dilerlerse deniz manzaralı odalarında tatillerini keyfe dönüştürebilir.");
            cityPopulationText.setText("176");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("228₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Best Western Premier ★★★★☆")){

            selectedHotelName = "Best Western Premier";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Best Western Premier Karşıyaka; İzmir'in tarihi ve hareketli bölgesi Karşıyaka'da; sauna, yüzme havuzu, fitness salonu ve alternatif masaj imkanlarıyla sağlıklı ve zinde bir konaklama imkanı sunuyor.");
            cityPopulationText.setText("118");
            cityAltitudeText.setText("2016");
            annualTouristVisitText.setText("273₺");
            descriptionAndImagePane.setVisible(true);

        }

        if(hotelValue.equals("Swissotel Grand Efes ★★★★★")){

            selectedHotelName = "Swissotel Grand Efes";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Swissotel Büyük Efes, İzmir lüks ve konforu buluşturan 402 odası ile gerek iş, gerekse eğlence amaçlı seyahat eden misafirlerin tüm ihtiyaçlarını karşılayacak şekilde düzenlenmiştir." +
                                           " Efsane açık havuzu ile misafirlerine keyifli konaklamalar sunuyor. ");
            cityPopulationText.setText("402");
            cityAltitudeText.setText("1964/2008");
            annualTouristVisitText.setText("502₺");
            descriptionAndImagePane.setVisible(true);

        }

        if(hotelValue.equals("Park Inn by Radisson ★★★★☆")){

            selectedHotelName = "Park Inn by Radisson";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("137");
            cityAltitudeText.setText("2016");
            annualTouristVisitText.setText("236₺");
            descriptionAndImagePane.setVisible(true);

        }

        if(hotelValue.equals("Mövenpick ★★★★★")){

            selectedHotelName = "Mövenpick";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("185");
            cityAltitudeText.setText("2008");
            annualTouristVisitText.setText("314₺");
            descriptionAndImagePane.setVisible(true);

        }

        if(hotelValue.equals("Buca Residence ★★★☆☆")){

            selectedHotelName = "Buca Residence";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("İzmir Buca’da konumlanan Buca Residence Hotel, modern ve konforlu odaları, güler yüzlü hizmetiyle misafirlerine keyifli bir tatil imkanı sunuyor." +
                                           " Şehrin pek çok noktasına yakın mesafede olan tesis, İzmir Otogarı’na 8 km mesafede yer alıyor.");
            cityPopulationText.setText("42");
            cityAltitudeText.setText("2015");
            annualTouristVisitText.setText("207₺");
            descriptionAndImagePane.setVisible(true);

        }

        if(hotelValue.equals("İbis Hotel ★★★☆☆")){

            selectedHotelName = "İbis Hotel ";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("İbis İzmir Alsancak bölgesinde bulunmaktadır. Otel Adnan Menderes Havalimanı'na 31 km, Kordon'a 2 km, İzmir Otogar'a 7 km uzaklıktadır. Tesis sadece oda konseptinde hizmet vermektedir. Oda servisi hizmeti bulunmaktadır. .\n" +
                    "\n" +
                    "Şehrin sanat, kültür ve ticaret merkezi Alsancak'ta yer alan İbis İzmir Alsancak; İzmir'in buluşma noktası tarihi Saat Kulesi'ne 4 km, Basmane Metro İstasyonu'na ise 3 km mesafede konumlanıyor.");
            cityPopulationText.setText("140");
            cityAltitudeText.setText("2016");
            annualTouristVisitText.setText("177₺");
            descriptionAndImagePane.setVisible(true);

        }
        //ISTANBUL HOTELS//
        if(hotelValue.equals("Grand Naki ★★★★☆")){

            selectedHotelName = "Grand Naki";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("26");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("399₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Levent Hotel ★★★☆☆")){

            selectedHotelName = "Levent Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("51");
            cityAltitudeText.setText("2000");
            annualTouristVisitText.setText("276₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Lionel Hotel ★★★★★")){

            selectedHotelName = "Lionel Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
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
            cityOrHotelDescription.setText("Mercia Hotel & Resorts, İstanbul’un sayfiye semti Kumburgaz’da bulunan ve yenileme çalışmaları tamamlanarak 2012 Mart ayında açılışı yapılan Business otelimiz sizlere ve misafirlerinize hizmet vermekten memnuniyet duyarız..\n" +
                    "\n" +
                    "Kumburgaz’da E-5 yol kenarında yer alan Otelimiz’den Tüyap Fuar merkezine 10 dk. mesafede, Atatürk hava alanı, Büyükçekmece şehir merkezi, iş merkezleri ve alışveriş merkezleri’ne kolay ulaşım imkanı.");
            cityPopulationText.setText("89");
            cityAltitudeText.setText("2011");
            annualTouristVisitText.setText("173₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Sheraton Hotel ★★★★★")){

            selectedHotelName = "Sheraton Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("İstanbul’un merkezi noktalarından Ataköy’de konumlanan Sheraton Ataköy; kaliteli hizmet anlayışı, deniz manzaralı ve konforlu odaları, donanımlı toplantı odaları, açık ve kapalı yüzme havuzları ile Atatürk Havaalanı’na 8 km mesafede konumlanıyor.");
            cityPopulationText.setText("285");
            cityAltitudeText.setText("2008");
            annualTouristVisitText.setText("738₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Grand Ant Hotel ★★★☆☆")){

            selectedHotelName = "Grand Ant Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("74");
            cityAltitudeText.setText("1990");
            annualTouristVisitText.setText("250₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Biz Cevahir Hotel ★★★★★")){

            selectedHotelName = "Biz Cevahir Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("104");
            cityAltitudeText.setText("2013");
            annualTouristVisitText.setText("473₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Jazz Hotel ★★★★☆")){

            selectedHotelName = "Jazz Hotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Jazz Hotel, İstanbul’un iş ve kültürel yaşamında önemli yer tutan merkezlere ulaşım kolaylığı sağlayan semti Nişantaşı'nda dünyaca ünlü caz sanatçılarından ilham alınarak dekore edilmiş, şık ve donanımlı odalarında misafirlerini ağırlıyor.");
            cityPopulationText.setText("22");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("410₺");
            descriptionAndImagePane.setVisible(true);
        }
        //ANKARA HOTELS
        if(hotelValue.equals("Point Hotel Ankara ★★★★☆")){

            selectedHotelName = "Point Hotel Ankara";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Next Level ve Armada alışveriş merkezlerine 2 Km., Taurus alışveriş merkezine 500 m. mesafede olup, Ankara ATO Uluslararası Kongre Sergi Sarayı’na ve daha birçok kültür sanat merkezine yakınlığıyla mükemmel bir konuma sahiptir. Otelin Ankara Esenboğa Havalimanına uzaklığı ise 34 Km.’dir..\n" +
                    "\n" +
                    "Türkiye'nin başkenti Ankara'da, İş Dünyası’nın kalbi Çukurambar’da yer alan ve modern mimarisiyle dikkat çeken Ankara’nın ilk 5 yıldızlı Art-Tech oteli Point Hotel Ankara, bakanlıklara, hükümet binalarına ve uluslararası şirketlerin genel merkezlerine yakınlığıyla iş amaçlı seyahat eden misafirler için muhteşem bir konumda bulunuyor. ");
            cityPopulationText.setText("80");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("308₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Hotel Abro Necatibey ★★★☆☆")){

            selectedHotelName = "Hotel Abro Necatibey";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Otelimiz Ankara'nın merkezi Kızılay Necatibey Caddesi üzerinde; bakanlıklara, iş merkezlerine, mağazalara, restaurantlara, turizm acentalarına, konser salonlarına, sinema ve tiyatrolara, sergi salonlarına, birçok tarihi mekâna, hastanelere, büyükelçiliklere ve metroya çok yakındır." +
                    "Ayrıca Anadolu Medeniyetleri Müzesi, Etnoğrafya Müzesi, Anıtkabir, Kızılay Meydanı, Ankara Kalesi, Kurtuluş Parkı Kocatepe Camii ve Amerika Büyükelçiliğine sadece yürüme mesafesindedir..\n");
            cityPopulationText.setText("68");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("110₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Hotel İçkale ★★★★★")){

            selectedHotelName = "Hotel İçkale";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("122");
            cityAltitudeText.setText("2011");
            annualTouristVisitText.setText("188₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Bera Ankara ★★★★★")){

            selectedHotelName = "Bera Ankara ";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("209");
            cityAltitudeText.setText("2017");
            annualTouristVisitText.setText("227₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Anemon ★★★★☆")){

            selectedHotelName = "Anemon";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Hem iş hem de turizm odaklı konaklamalar için konforlu bir ortam vadeden Anemon Ankara, Kavaklıdere’de hizmet veriyor. Ankara şehir merkezine 1 km ve Anıtkabir’e 4 km uzaklıkta yer alan tesiste, masaj ve toplantı odası gibi imkanlar mevcut.");
            cityPopulationText.setText("83");
            cityAltitudeText.setText("2007");
            annualTouristVisitText.setText("209₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Gür Kent ★★★★☆")){

            selectedHotelName = "Gür Kent";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Hem iş hem eğlence amaçlı seyahat eden misafirlere rahat bir konaklama imkanı veren Gür Kent Hotel, Kızılay Meydanı ve Gençlik Parkı gibi yerlere yakın konumda rahat bir konaklama alternatifi sunuyor. ");
            cityPopulationText.setText("117");
            cityAltitudeText.setText("1985/2012");
            annualTouristVisitText.setText("149₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Dafne ★★★☆☆")){

            selectedHotelName = "Dafne";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Konya Yolu üzerinde bulunan Dafne Otel Aşti'ye 3 km. Esenboğa Hava alanına 45 km. Devlet Demir Yollarına 7 km. mesafededir. Anitkabir ve Kızılay ortalama 5 km. uzaklıktadır. OTELİMİZ AİLE OTELİDİR EVLİLİK CÜZDANI SORULMAKTADIR.\n" +
                    "\n" +
                    "Dafne Hotel , Ankara’nın yükselen değeri olmaya devam eden semtlerinden Balgat’ta yer almaktadır. Sizleri ağırlamaktan büyük memnuniyet duyacak olan Dafne Hotel single.double.trıple ve suıt odalar olmak üzere 72 oda 155 yatak kapasitelidir.");
            cityPopulationText.setText("72");
            cityAltitudeText.setText("2015");
            annualTouristVisitText.setText("175₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Swissotel ★★★★★")){

            selectedHotelName = "Swissotel";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Başkent Ankara'nın elit semti Çankaya'da bulunan Swissôtel Ankara, modern binası ve eşsiz donanımıyla misafirlerine hizmet vermektedir. Swissotel Ankara, Ankara'nın ihtişamını ve konforunu duyularınıza yeniden tanıtır: Amrita Spa & Wellness ile lüks bir yaşamın keyfine varın. .\n" +
                    "\n" +
                    "Swissotel Ankara, Cumhurbaşkanlığı Köşkü ve bir çok elçiliklere yakın mesafededir. Şehir merkezine 10 dakika, Esenboğa havalimanına sadece 45 dakika uzaklıkta olup, alışveriş merkezlerine ulaşım kolaylıkla sağlanmaktadır.");
            cityPopulationText.setText("150");
            cityAltitudeText.setText("2010");
            annualTouristVisitText.setText("458₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Maltepe 2000 ★★★☆☆")){

            selectedHotelName = "Maltepe 2000";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("35");
            cityAltitudeText.setText("2013");
            annualTouristVisitText.setText("281₺");
            descriptionAndImagePane.setVisible(true);
        }
        //ANTALYA HOTELS

        if(hotelValue.equals("Ramada Plaza ★★★★☆")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("207");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("195₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Ramada Plaza ★★★★☆")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("207");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("195₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Ramada Plaza ★★★★☆")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("207");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("195₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Ramada Plaza ★★★★☆")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("207");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("195₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Ramada Plaza ★★★★☆")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("207");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("195₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Ramada Plaza ★★★★☆")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("207");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("195₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Ramada Plaza ★★★★☆")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("207");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("195₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Ramada Plaza ★★★★☆")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("207");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("195₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Ramada Plaza ★★★★☆")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("207");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("195₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Ramada Plaza ★★★★")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("207");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("195₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Ramada Plaza ★★★★")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("207");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("195₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Ramada Plaza ★★★★")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("207");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("195₺");
            descriptionAndImagePane.setVisible(true);
        }

        if(hotelValue.equals("Ramada Plaza ★★★★")){

            selectedHotelName = "Ramada Plaza";
            cityOrHotelName.setText(selectedHotelName);
            cityOrHotelDescription.setText("Ramada Plaza İzmir; İzmir, Kahramanlar bölgesinde hizmet vermektedir. Tesis Adnan Menderes Havalimanına 26 km, İzmir Otogarına 7 km, şehir merkezine 2 km uzaklıktadır.\n" +
                    "Tesis, güler yüzlü personeli ve hizmet anlayışı ile misafirlerine konforlu bir konaklama imkanı sunuyor. \n" +
                    "Tesisin kalite ve konforu bir araya getiren odalarında; kasa, LCD TV, uydu yayını, minibar ve çay-kahve makinesi bulunuyor.\n" +
                    "Bagaj muhafazası ve 24 saat açık resepsiyon tesisin diğer olanakları arasında yer alıyor.");
            cityPopulationText.setText("207");
            cityAltitudeText.setText("2014");
            annualTouristVisitText.setText("195₺");
            descriptionAndImagePane.setVisible(true);
        }
    }
}


