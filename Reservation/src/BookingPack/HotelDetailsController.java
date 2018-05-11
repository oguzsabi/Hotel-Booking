package BookingPack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Date;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

public class HotelDetailsController implements Initializable{
    @FXML RadioButton singleRoomRadioButton;
    @FXML RadioButton doubleRoomRadioButton;
    @FXML RadioButton tripleRoomRadioButton;
    @FXML RadioButton quadRoomRadioButton;
    @FXML RadioButton queenRoomRadioButton;
    @FXML RadioButton kingRoomRadioButton;
    @FXML Text hotelNameText;
    @FXML Text hotelDescriptionText;
    @FXML DatePicker checkInDate;
    @FXML DatePicker checkOutDate;
    private ToggleGroup toggleGroup = new ToggleGroup();
    private Date today = new Date();
    private int starCounter = 0;
    private int singlePrice, doublePrice, triplePrice, quadPrice, queenPrice, kingPrice;
    private SecureRandom randomPrice = new SecureRandom();
    private SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
    private String checkInDateString;
    private String checkOutDateString;
    private static long totalDays;
    private static String from;
    private static String to;
    private static long priceTag;

    public void initialize(URL url, ResourceBundle resourceBundle){
        singleRoomRadioButton.setToggleGroup(toggleGroup);
        doubleRoomRadioButton.setToggleGroup(toggleGroup);
        tripleRoomRadioButton.setToggleGroup(toggleGroup);
        quadRoomRadioButton.setToggleGroup(toggleGroup);
        queenRoomRadioButton.setToggleGroup(toggleGroup);
        kingRoomRadioButton.setToggleGroup(toggleGroup);
        hotelNameText.setText(CityAndHotelSelectionController.getHotelValue());
        hotelDescriptionText.setText(CityAndHotelSelectionController.getDescriptionOfTheHotel());
        hotelStarCounting();
        if(starCounter<=3){
            quadRoomRadioButton.setDisable(true);
            queenRoomRadioButton.setDisable(true);
            kingRoomRadioButton.setDisable(true);
            Save.readingThreeStarHotelRoomPrices(CityAndHotelSelectionController.getCityValue(),CityAndHotelSelectionController.getSelectedHotelName(),singleRoomRadioButton,doubleRoomRadioButton,tripleRoomRadioButton);
        }
        else{
            Save.readingFiveStarHotelRoomPrices(CityAndHotelSelectionController.getCityValue(),CityAndHotelSelectionController.getSelectedHotelName(),singleRoomRadioButton,doubleRoomRadioButton,tripleRoomRadioButton,quadRoomRadioButton,queenRoomRadioButton,kingRoomRadioButton);
        }
        if(singleRoomRadioButton.getText().equals("Single Room")){
            if(starCounter==1){
                singlePrice = randomPrice.nextInt(20)+80;
                doublePrice = randomPrice.nextInt(20)+100;
                triplePrice = randomPrice.nextInt(20)+120;
                singleRoomRadioButton.setText("Single Room ("+singlePrice+"₺)");
                doubleRoomRadioButton.setText("Double Room ("+doublePrice+"₺)");
                tripleRoomRadioButton.setText("Triple Room ("+triplePrice+"₺)");
            }
            else if(starCounter==2){
                singlePrice = randomPrice.nextInt(20)+95;
                doublePrice = randomPrice.nextInt(20)+115;
                triplePrice = randomPrice.nextInt(20)+130;
                singleRoomRadioButton.setText("Single Room ("+singlePrice+"₺)");
                doubleRoomRadioButton.setText("Double Room ("+doublePrice+"₺)");
                tripleRoomRadioButton.setText("Triple Room ("+triplePrice+"₺)");
            }
            else if(starCounter==3){
                singlePrice = randomPrice.nextInt(30)+100;
                doublePrice = randomPrice.nextInt(30)+130;
                triplePrice = randomPrice.nextInt(30)+160;
                singleRoomRadioButton.setText("Single Room ("+singlePrice+"₺)");
                doubleRoomRadioButton.setText("Double Room ("+doublePrice+"₺)");
                tripleRoomRadioButton.setText("Triple Room ("+triplePrice+"₺)");
            }
            else if(starCounter==4){
                singlePrice = randomPrice.nextInt(50)+160;
                doublePrice = randomPrice.nextInt(50)+190;
                triplePrice = randomPrice.nextInt(50)+240;
                quadPrice = randomPrice.nextInt(50)+280;
                queenPrice = randomPrice.nextInt(50)+330;
                kingPrice = randomPrice.nextInt(50)+360;
                singleRoomRadioButton.setText("Single Room ("+singlePrice+"₺)");
                doubleRoomRadioButton.setText("Double Room ("+doublePrice+"₺)");
                tripleRoomRadioButton.setText("Triple Room ("+triplePrice+"₺)");
                quadRoomRadioButton.setText("Quad Room ("+quadPrice+"₺)");
                queenRoomRadioButton.setText("Queen Room ("+queenPrice+"₺)");
                kingRoomRadioButton.setText("King Room ("+kingPrice+"₺)");
            }
            else{
                singlePrice = randomPrice.nextInt(50)+200;
                doublePrice = randomPrice.nextInt(50)+235;
                triplePrice = randomPrice.nextInt(50)+270;
                quadPrice = randomPrice.nextInt(50)+310;
                queenPrice = randomPrice.nextInt(50)+350;
                kingPrice = randomPrice.nextInt(50)+400;
                singleRoomRadioButton.setText("Single Room ("+singlePrice+"₺)");
                doubleRoomRadioButton.setText("Double Room ("+doublePrice+"₺)");
                tripleRoomRadioButton.setText("Triple Room ("+triplePrice+"₺)");
                quadRoomRadioButton.setText("Quad Room ("+quadPrice+"₺)");
                queenRoomRadioButton.setText("Queen Room ("+queenPrice+"₺)");
                kingRoomRadioButton.setText("King Room ("+kingPrice+"₺)");
            }
        }
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Parent signInParent = FXMLLoader.load(getClass().getResource("CityAndHotelSelectionView.fxml"));
        Scene signInScene = new Scene(signInParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void toThePaymentButtonClicked(MouseEvent mouseEvent) throws IOException{
        priceTag = roomTypeChecker();
        if(starCounter>3) {
            if (singleRoomRadioButton.isSelected() || doubleRoomRadioButton.isSelected() || tripleRoomRadioButton.isSelected() || quadRoomRadioButton.isSelected() || queenRoomRadioButton.isSelected() || kingRoomRadioButton.isSelected()) {
                if(checkInAndTodayChecker() && checkInAndCheckOutChecker()){
                    from = checkInDate.getEditor().getText();
                    to = checkOutDate.getEditor().getText();
                    totalDaysCalculator();
                    Parent signInParent = FXMLLoader.load(getClass().getResource("PaymentView.fxml"));
                    Scene signInScene = new Scene(signInParent);

                    Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                    window.setScene(signInScene);
                    window.show();
                }
            }
        }
        else{
            if (singleRoomRadioButton.isSelected() || doubleRoomRadioButton.isSelected() || tripleRoomRadioButton.isSelected()) {
                if(checkInAndTodayChecker() && checkInAndCheckOutChecker()){
                    from = checkInDate.getEditor().getText();
                    to = checkOutDate.getEditor().getText();
                    totalDaysCalculator();
                    Parent signInParent = FXMLLoader.load(getClass().getResource("PaymentView.fxml"));
                    Scene signInScene = new Scene(signInParent);

                    Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                    window.setScene(signInScene);
                    window.show();
                }
            }
        }
    }

    public boolean checkInAndTodayChecker(){
        String dateString = today.toInstant().toString();
        if(checkInDate.getEditor().getText().length()>0 && checkOutDate.getEditor().getText().length()>0) {
            if(Integer.parseInt(checkInDate.getEditor().getText().substring(6, 10)) == Integer.parseInt(dateString.substring(0,4))) {
                if(Integer.parseInt(checkInDate.getEditor().getText().substring(4, 5)) == Integer.parseInt(dateString.substring(5,7))) {
                    if(Integer.parseInt(checkInDate.getEditor().getText().substring(0, 2)) == Integer.parseInt(dateString.substring(8,10))) {
                        if(Integer.parseInt(dateString.substring(11,13))+3<12){
                            return true;
                        }
                        else
                            AlertBox.display("Reservation Time Error!","You cannot make a reservation for today after 12 p.m.!");
                    }
                    else if(Integer.parseInt(checkInDate.getEditor().getText().substring(0, 2)) > Integer.parseInt(dateString.substring(8,10))){
                        return true;
                    }
                    else
                        AlertBox.display("Check-In Date Error!","Your check-in date cannot be before today's date!");
                }
                else if(Integer.parseInt(checkInDate.getEditor().getText().substring(4, 5)) > Integer.parseInt(dateString.substring(5,7))){
                    return true;
                }
                else
                    AlertBox.display("Check-In Date Error!","Your check-in date cannot be before today's date!");
            }
            else if(Integer.parseInt(checkInDate.getEditor().getText().substring(6, 10)) > Integer.parseInt(dateString.substring(0,4))){
                return true;
            }
            else
                AlertBox.display("Check-In Date Error!","Your check-in date cannot be before today's date!");
        }
        else
            AlertBox.display("Date Pick Error!","You must pick your dates first!");
        return false;
    }

    public boolean checkInAndCheckOutChecker(){
        if (Integer.parseInt(checkInDate.getEditor().getText().substring(6, 10)) == Integer.parseInt(checkOutDate.getEditor().getText().substring(6, 10))) {
            if (Integer.parseInt(checkInDate.getEditor().getText().substring(4, 5)) == Integer.parseInt(checkOutDate.getEditor().getText().substring(4, 5))) {
                if (Integer.parseInt(checkInDate.getEditor().getText().substring(0, 2)) < Integer.parseInt(checkOutDate.getEditor().getText().substring(0, 2))) {
                    return true;
                } else
                    AlertBox.display("Date Pick Day Error!", "Check-out day cannot be in the same day with check-in!");
            } else if (Integer.parseInt(checkInDate.getEditor().getText().substring(4, 5)) < Integer.parseInt(checkOutDate.getEditor().getText().substring(4, 5))) {
                return true;
            } else
                AlertBox.display("Date Pick Month Error!", "Check-out month cannot be before check-in month!");

        } else if (Integer.parseInt(checkInDate.getEditor().getText().substring(6, 10)) < Integer.parseInt(checkOutDate.getEditor().getText().substring(6, 10))) {
            return true;
        } else
            AlertBox.display("Date Pick Year Error!", "Check-out year cannot be before check-in year!");
        return false;
    }

    public void hotelStarCounting(){
        String hotelNameWithStars = CityAndHotelSelectionController.getHotelValue();
        for(int i = 0; i<hotelNameWithStars.length(); i++){
            if(hotelNameWithStars.charAt(i) == '★'){
                starCounter++;
            }
        }
    }

    public void totalDaysCalculator(){
        checkInDateString = Integer.parseInt(checkInDate.getEditor().getText().substring(0, 2)) + " " + Integer.parseInt(checkInDate.getEditor().getText().substring(4, 5)) + " " + Integer.parseInt(checkInDate.getEditor().getText().substring(6, 10));
        checkOutDateString = Integer.parseInt(checkOutDate.getEditor().getText().substring(0, 2)) + " " + Integer.parseInt(checkOutDate.getEditor().getText().substring(4, 5)) + " " + Integer.parseInt(checkOutDate.getEditor().getText().substring(6, 10));
        try {
            Date date1 = myFormat.parse(checkInDateString);
            Date date2 = myFormat.parse(checkOutDateString);
            totalDays = date2.getTime() - date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int roomTypeChecker(){
        if(singleRoomRadioButton.isSelected()){
            return Integer.parseInt(singleRoomRadioButton.getText().substring(13,singleRoomRadioButton.getText().length()-2));
        }
        if(doubleRoomRadioButton.isSelected()){
            return Integer.parseInt(doubleRoomRadioButton.getText().substring(13,doubleRoomRadioButton.getText().length()-2));
        }
        if(tripleRoomRadioButton.isSelected()){
            return Integer.parseInt(tripleRoomRadioButton.getText().substring(13,tripleRoomRadioButton.getText().length()-2));
        }
        if(quadRoomRadioButton.isSelected()){
            return Integer.parseInt(quadRoomRadioButton.getText().substring(11,quadRoomRadioButton.getText().length()-2));
        }
        if(queenRoomRadioButton.isSelected()){
            return Integer.parseInt(queenRoomRadioButton.getText().substring(12,queenRoomRadioButton.getText().length()-2));
        }
        if(kingRoomRadioButton.isSelected()){
            return Integer.parseInt(kingRoomRadioButton.getText().substring(11,kingRoomRadioButton.getText().length()-2));
        }
        return 0;
    }

    public static long getPriceTag() {
        return priceTag;
    }

    public static long getTotalDays() {
        return totalDays;
    }

    public static String getFrom() {
        return from;
    }

    public static String getToo() {
        return to;
    }
}
