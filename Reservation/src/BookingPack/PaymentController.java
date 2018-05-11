package BookingPack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


public class PaymentController implements Initializable{
    @FXML Text checkInDate;
    @FXML Text checkOutDate;
    @FXML Text totalDays;
    @FXML Text totalPrice;
    @FXML RadioButton masterCardButton;
    @FXML RadioButton visaCardButton;
    @FXML RadioButton discoverCardButton;
    @FXML RadioButton installments3;
    @FXML RadioButton installments6;
    @FXML RadioButton installments9;
    @FXML TextField creditCardNumber;
    @FXML TextField expiryDateMonth;
    @FXML TextField expiryDateYear;
    @FXML TextField cvv;
    @FXML TextField firstName;
    @FXML TextField lastName;
    @FXML TextField phoneNumber;
    @FXML TextField email;

    private ToggleGroup cardTypeGroup = new ToggleGroup();
    private ToggleGroup installmentsGroup = new ToggleGroup();
    private File file;
    private Formatter newFile;

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Parent signInParent = FXMLLoader.load(getClass().getResource("HotelDetailsView.fxml"));
        Scene signInScene = new Scene(signInParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        checkInDate.setText("Check-In Date   : "+HotelDetailsController.getFrom());
        checkOutDate.setText("Check-Out Date  : "+HotelDetailsController.getToo());
        totalDays.setText("Number of Days: " + TimeUnit.DAYS.convert(HotelDetailsController.getTotalDays(), TimeUnit.MILLISECONDS));
        totalPrice.setText(Long.toString((HotelDetailsController.getTotalDays()/86400000)*HotelDetailsController.getPriceTag()) + "₺");

        masterCardButton.setToggleGroup(cardTypeGroup);
        visaCardButton.setToggleGroup(cardTypeGroup);
        discoverCardButton.setToggleGroup(cardTypeGroup);
        installments3.setToggleGroup(installmentsGroup);
        installments6.setToggleGroup(installmentsGroup);
        installments9.setToggleGroup(installmentsGroup);
    }

    public void payButtonClicked(MouseEvent mouseEvent){
        if(masterCardButton.isSelected() || visaCardButton.isSelected() || discoverCardButton.isSelected()){
            if(creditCardNumber.getText().length()==16){
                try {
                    if (Integer.parseInt(expiryDateMonth.getText()) > 0 && Integer.parseInt(expiryDateMonth.getText()) < 13 && Integer.parseInt(expiryDateYear.getText()) > 17 && Integer.parseInt(expiryDateYear.getText()) < 30){
                        try{
                            Integer.parseInt(cvv.getText());
                            if(cvv.getText().length()==3){
                                if(firstName.getText().length()>=2 && Character.isLetter(firstName.getText().charAt(0)) && lastName.getText().length()>=2 && Character.isLetter(lastName.getText().charAt(0))){
                                    try{
                                        Long.parseLong(phoneNumber.getText().substring(1));
                                        if(phoneNumber.getText().length()>8 && phoneNumber.getText().length()<17){
                                            boolean atBoolean = false, dotBoolean = false, lengthBoolean = false;
                                            int atIndex = 0;
                                            for (int i = 0; i < email.getText().length(); i++) {
                                                char checker = email.getText().charAt(i);
                                                if(checker == '@') {
                                                    atBoolean = true;
                                                    atIndex = i;
                                                }
                                                if(checker == '.' && atIndex!=0)
                                                    dotBoolean = true;
                                                if(email.getText().length()>5)
                                                    lengthBoolean = true;
                                            }
                                            if(dotBoolean && lengthBoolean && atBoolean){
                                                try {
                                                    file = new File(SignInController.getUsernameString()+"Reservations.txt");
                                                    if(file.createNewFile()){
                                                        newFile = new Formatter(SignInController.getUsernameString()+"Reservations.txt");
                                                    }
                                                } catch (Exception e) {
                                                    AlertBox.display("Unknown Error!", "An unknown error has occured!");
                                                }
                                                Save.addingReservations(file, CityAndHotelSelectionController.getHotelValue(), checkInDate.getText(), checkOutDate.getText(), totalPrice.getText());

                                                Thread.sleep(2000);

                                                Parent signInParent = FXMLLoader.load(getClass().getResource("ReservationCompletedView.fxml"));
                                                Scene signInScene = new Scene(signInParent);

                                                Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                                                window.setScene(signInScene);
                                                window.show();
                                            }
                                            else
                                                AlertBox.display("Email Error!","Your email must be a valid one!");
                                        }
                                        else
                                            AlertBox.display("Phone Number Error!","Your phone number must be a valid one!");
                                    }
                                    catch (Exception e){
                                        AlertBox.display("Phone Number Error!","Your phone number must be all digits! (excluding +)");
                                    }
                                }
                                else
                                    AlertBox.display("Name Error!","Your first name and last name must be correct!");
                            }
                            else
                                AlertBox.display("CVV Error!","Your cvv must be correct!");
                        }
                        catch(Exception e){
                            AlertBox.display("CVV Error!","Your cvv can only include digits!");
                        }
                    }
                    else
                        AlertBox.display("Expiry Date Error!","Your expiry date must be correct!");
                }
                catch(Exception e){
                    AlertBox.display("Expiry Date Error!","Your expiry date can only be digits!");
                }
            }
            else
                AlertBox.display("Credit Card Number Error!","Your credit card number must be correct!");
        }
        else
            AlertBox.display("Card Type Error!","You must select a card type!");
    }
}
