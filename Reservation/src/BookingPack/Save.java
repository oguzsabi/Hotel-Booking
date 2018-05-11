package BookingPack;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

import java.text.NumberFormat;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Save {
    static private FileWriter memberListFW;
    static private BufferedWriter memberListBW;
    static private PrintWriter memberListPW;
    static private Scanner readingFile;
    static private File adminList = new File("adminList.txt");
    static private File memberList = new File("memberList.txt");
    static private File rememberMe = new File("rememberMe.txt");
    static private File tempMemberList = new File("tempMemberList.txt");
    static private File cityInformation = new File("cityInformation.txt");
    static private File tempCityInformation = new File("tempCityInformation.txt");
    static private File tempHotels = new File("tempHotels.txt");
    static private File tempReservations = new File("tempReservations.txt");
    static private File hotelNames;
    static private File hotelNamesThree;
    static private File hotelNamesFive;
    static private File hotelInfoFive;
    static private File hotelInfoThree;
    static private File reservations;
    static private String username, password;
    static private NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
    static private String cityDescription = "City Information";

    public static boolean readingAdminList(String username, String password) {
        boolean unCheck = false,pwCheck = false;

        try {
            readingFile = new Scanner(adminList);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        while(readingFile.hasNext()) {
            if(username.equals(readingFile.next())) {
                unCheck = true;
            }
            if(password.equals(readingFile.next())) {
                pwCheck = true;
            }

            if(unCheck && pwCheck) {
                break;
            }
            else{
                unCheck = false;
                pwCheck = false;
            }
        }

        readingFile.close();

        if(unCheck && pwCheck) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean readingMemberList(String username, String password){
	    boolean unCheck = false, pwCheck = false;

        try {
            readingFile = new Scanner(memberList);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        while(readingFile.hasNext()) {
            if(username.equals(readingFile.next())) {
                unCheck = true;
            }
            if(password.equals(readingFile.next())) {
                pwCheck = true;
            }

            if(unCheck && pwCheck) {
                break;
            }
            else{
                unCheck = false;
                pwCheck = false;
            }
        }

        readingFile.close();

        if(unCheck && pwCheck) {
            return true;
        }
        else
            return false;
    }

    public static void removeMemberFromMemberList(String memberInformation) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(memberList));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempMemberList));


        String lineToRemove = memberInformation;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();

        PrintWriter writer1 = new PrintWriter(memberList);
        writer1.print("");
        try {
            readingFile = new Scanner(tempMemberList);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        while(readingFile.hasNext()) {
            String username = readingFile.next();
            String password = readingFile.next();
            addingToMemberList(username, password);
        }
    }

    public static ArrayList<String> getMemberList(){
        ArrayList<String> members = new ArrayList<>();
        try {
            readingFile = new Scanner(memberList);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        while(readingFile.hasNextLine()){
            members.add(readingFile.nextLine());
        }

        readingFile.close();

        return members;
    }

    public static boolean checkingMemberList(String username){
        int flag = 0;

        try {
            readingFile = new Scanner(memberList);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        while(readingFile.hasNext()) {
            if(username.equals(readingFile.next())) {
                flag++;
            }
            readingFile.next();

            if(flag==1) {
                break;
            }
        }

        readingFile.close();

        if(flag==1) {
            return true;
        }
        else
            return false;
    }

    public static boolean checkingAdminList(String username){
        int flag = 0;

        try {
            readingFile = new Scanner(adminList);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        while(readingFile.hasNext()) {
            if(username.equals(readingFile.next())) {
                flag++;
            }
            readingFile.next();

            if(flag==1) {
                break;
            }
        }

        readingFile.close();

        if(flag==1) {
            return true;
        }
        else
            return false;
    }

    public static void readingHotelInformation(String cityName, String hotelName, Label hotelNameText, Text hotelRoomAmount, Text hotelYearOfBuilt, Text hotelNightlyCost, Text hotelDescriptionText){
        int hotelNameLength = hotelName.length();
        String hotelNameOnly = hotelName.substring(0,(hotelNameLength-7));
        String hotelDescription = "";
        int descriptionCounter = 0;
        int starCounter = 0;
        int hammerCounter = 0;
        boolean hotelNameCheck = false;

        for(int i = 0; i<hotelName.length(); i++){
            if(hotelName.charAt(i) == '★'){
                starCounter++;
            }
        }
        if(starCounter>0 && starCounter<=3){
            hotelInfoThree = new File(cityName+"HotelsThree.txt");
            try{
                readingFile = new Scanner(hotelInfoThree);
            }
            catch(FileNotFoundException e){
                AlertBox.display("File Not Found Error!","You first need to create such a file to access it!");
            }
            while(readingFile.hasNext()){
                String line = readingFile.nextLine();
                if(line.equals('☭'+hotelNameOnly)){
                    hotelNameCheck = true;
                }

                if(line.charAt(0) == '☭' && hotelNameCheck){
                    hammerCounter++;
                }

                switch(hammerCounter){
                    case 1:
                        hotelNameText.setText(line.substring(1));
                        break;
                    case 2:
                        descriptionCounter++;
                        if(descriptionCounter==1){
                            hotelDescription = line.substring(1);
                        }
                        if(descriptionCounter>1){
                            hotelDescription = hotelDescription + line;
                        }
                        break;
                    case 3:
                        hotelDescriptionText.setText(hotelDescription);
                        break;
                    case 4:
                        hotelRoomAmount.setText(line.substring(1));
                        break;
                    case 5:
                        hotelYearOfBuilt.setText(line.substring(1));
                        break;
                    case 6:
                        hotelNightlyCost.setText(line.substring(1));
                        break;
                }
            }
        }
        if(starCounter>3 && starCounter<=5){
            hotelInfoFive = new File(cityName+"HotelsFive.txt");
            try{
                readingFile = new Scanner(hotelInfoFive);
            }
            catch(FileNotFoundException e){
                AlertBox.display("File Not Found Error!","You first need to create such a file to access it!");
            }
            while(readingFile.hasNext()){
                String line = readingFile.nextLine();
                if(line.equals('☭'+hotelNameOnly)){
                    hotelNameCheck = true;
                }

                if(line.charAt(0) == '☭' && hotelNameCheck){
                    hammerCounter++;
                }

                switch(hammerCounter){
                    case 1:
                        hotelNameText.setText(line.substring(1));
                        break;
                    case 2:
                        descriptionCounter++;
                        if(descriptionCounter==1){
                            hotelDescription = line.substring(1);
                        }
                        if(descriptionCounter>1){
                            hotelDescription = hotelDescription + " " + line + "\n";
                        }
                        break;
                    case 3:
                        hotelDescriptionText.setText(hotelDescription);
                        break;
                    case 4:
                        hotelRoomAmount.setText(line.substring(1));
                        break;
                    case 5:
                        hotelYearOfBuilt.setText(line.substring(1));
                        break;
                    case 6:
                        hotelNightlyCost.setText(line.substring(1));
                        break;
                }
            }
        }
    }

    public static boolean readingCityInformationAndDisplayingItsHotels(String cityName, Label cityNameLabel, Text cityDescriptionText, Text cityPopulation, Text cityAltitude, Text cityAnnuanlTourist, ObservableList<String> observableList, ListView<String> listView){
        boolean cityNameCheck = false;
        int hammerCounter = 0;
        int descriptionCounter = 0;
        String cityDescription = "";
        ArrayList<String> hotelNames = new ArrayList<>();
        hotelNamesFive = new File(cityName+"HotelsFive.txt");
        hotelNamesThree = new File(cityName+"HotelsThree.txt");

        try{
            readingFile = new Scanner(cityInformation);
        }
        catch(FileNotFoundException e){
            AlertBox.display("File Not Found Error!","You first need to create such a file to access it!");
        }

        while(readingFile.hasNext()){
            String line = readingFile.nextLine();
            if(line.equals('☭'+cityName))
                cityNameCheck = true;

            if(line.charAt(0) == '☭' && cityNameCheck){
                hammerCounter++;
            }

            switch(hammerCounter){
                case 1:
                    cityNameLabel.setText(cityName);
                    break;
                case 2:
                    descriptionCounter++;
                    if(descriptionCounter==1){
                        cityDescription = line.substring(1);
                    }
                    if(descriptionCounter>1){
                        cityDescription = cityDescription + " " + line + "\n";
                    }
                    break;
                case 3:
                    cityDescriptionText.setText(cityDescription);
                    cityPopulation.setText(numberFormat.format(Integer.parseInt(line.substring(1))));
                    break;
                case 4:
                    cityAltitude.setText(line.substring(1));
                    break;
                case 5:
                    cityAnnuanlTourist.setText(numberFormat.format(Integer.parseInt(line.substring(1))));

                    try{
                        if(hotelNamesFive.exists())
                            readingFile = new Scanner(hotelNamesFive);
                    }
                    catch(FileNotFoundException e){
                        AlertBox.display("File Not Found Error!","You first need to create such a file to access it!");
                    }

                    String hotelNameWithStars = "";
                    int flag = -1;
                    int afterHotelNameCounter;
                    while(readingFile.hasNext()) {
                        line = readingFile.nextLine();
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

                    try{
                        if(hotelNamesThree.exists())
                            readingFile = new Scanner(hotelNamesThree);
                    }
                    catch(FileNotFoundException e){
                        AlertBox.display("File Not Found Error!","You first need to create such a file to access it!");
                    }

                    flag = -1;
                    hotelNameWithStars = "";
                    while (readingFile.hasNext()) {
                        line = readingFile.nextLine();
                        String hotelName;
                        if (line.length() > 0) {
                            if (line.charAt(0) == '☭') {
                                flag++;
                            }
                        }
                        if (flag == 0 || flag % 9 == 0) {
                            hotelName = line.substring(1);
                            hotelNameWithStars = hotelNameWithStars + hotelName + " ";
                            afterHotelNameCounter = 0;
                            while (readingFile.hasNext()) {
                                line = readingFile.nextLine();
                                if (line.charAt(0) == '☭') {
                                    afterHotelNameCounter++;
                                    flag++;
                                    if (afterHotelNameCounter == 2) {
                                        hotelNameWithStars = hotelNameWithStars + line.substring(3) + "\n";
                                        hotelNames.add(hotelNameWithStars);
                                        hotelNameWithStars = "";
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    observableList.addAll(hotelNames);
                    listView.setItems(observableList);
                    return true;
            }
        }
        return false;
    }

    public static void readingFiveStarHotelRoomPrices(String cityName, String hotelName, RadioButton single, RadioButton doubleR, RadioButton triple, RadioButton quad, RadioButton queen, RadioButton king){
        int hammerCounter = 0;
        boolean hotelNameCheck = false;

        hotelInfoFive = new File(cityName+"HotelsFive.txt");
        try{
            readingFile = new Scanner(hotelInfoFive);
        }
        catch(FileNotFoundException e){
            AlertBox.display("File Not Found Error!","You first need to create such a file to access it!");
        }
        while(readingFile.hasNext()){
            String line = readingFile.nextLine();
            if(line.equals('☭'+hotelName)){
                hotelNameCheck = true;
            }

            if(line.charAt(0) == '☭' && hotelNameCheck){
                hammerCounter++;
            }

            switch(hammerCounter){
                case 7:
                    single.setText("Single Room ("+line.substring(1)+"₺)");
                    break;
                case 8:
                    doubleR.setText("Double Room ("+line.substring(1)+"₺)");
                    break;
                case 9:
                    triple.setText("Triple Room ("+line.substring(1)+"₺)");
                    break;
                case 10:
                    quad.setText("Quad Room ("+line.substring(1)+"₺)");
                    break;
                case 11:
                    queen.setText("Queen Room ("+line.substring(1)+"₺)");
                    break;
                case 12:
                    king.setText("King Room ("+line.substring(1)+"₺)");
                    break;
            }
        }
    }

    public static void readingThreeStarHotelRoomPrices(String cityName, String hotelName, RadioButton single, RadioButton doubleR, RadioButton triple){
        int hammerCounter = 0;
        boolean hotelNameCheck = false;
        hotelInfoThree = new File(cityName+"HotelsThree.txt");
        try{
            readingFile = new Scanner(hotelInfoThree);
        }
        catch(FileNotFoundException e){
            AlertBox.display("File Not Found Error!","You first need to create such a file to access it!");
        }
        while(readingFile.hasNext()){
            String line = readingFile.nextLine();
            if(line.equals('☭'+hotelName)){
                hotelNameCheck = true;
            }

            if(line.charAt(0) == '☭' && hotelNameCheck){
                hammerCounter++;
            }

            switch(hammerCounter){
                case 7:
                    single.setText("Single Room ("+line.substring(1)+"₺)");
                    break;
                case 8:
                    doubleR.setText("Double Room ("+line.substring(1)+"₺)");
                    break;
                case 9:
                    triple.setText("Triple Room ("+line.substring(1)+"₺)");
                    break;
            }
        }
    }

    public static void removingReservations(String fileName, String hotelName) throws IOException{
        reservations = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(reservations));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempReservations));

        try {
            readingFile = new Scanner(reservations);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        if(reservations.exists()) {
            String lineToRemove = "";
            int flag = 0;
            boolean checker = false;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();

                String line = readingFile.nextLine();
                if (line.equals('☭'+hotelName))
                    checker = true;

                if (line.length() > 0 && checker) {
                    if (line.charAt(0) == '☭') {
                        flag++;
                    }
                    lineToRemove = line;
                }

                if (flag < 5) {
                    if (trimmedLine.equals(lineToRemove) && checker) continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            writer.close();
            reader.close();

            BufferedReader tempReservationsReader = new BufferedReader(new FileReader(tempReservations));
            BufferedWriter backToReservationsWriter = new BufferedWriter(new FileWriter(reservations));

            while ((currentLine = tempReservationsReader.readLine()) != null) {
                backToReservationsWriter.write(currentLine + System.getProperty("line.separator"));
            }

            tempReservationsReader.close();
            backToReservationsWriter.close();
        }
    }

    public static void addingReservationInformation(File file, String hotelName,Text checkin, Text checkout, Text price){
        boolean hotelNameCheck = false;
        int hammerCounter = 0;

        if(file.exists()) {
            try {
                readingFile = new Scanner(file);
            } catch (FileNotFoundException e) {
                AlertBox.display("File Not Found Error!", "You first need to create such a file to access it!");
            }

            while (readingFile.hasNext()) {
                String line = readingFile.nextLine();
                if (line.equals('☭' + hotelName))
                    hotelNameCheck = true;

                if (line.charAt(0) == '☭' && hotelNameCheck) {
                    hammerCounter++;
                }

                switch (hammerCounter) {
                    case 2:
                        checkin.setText(line.substring(1));
                        break;
                    case 3:
                        checkout.setText(line.substring(1));
                        break;
                    case 4:
                        price.setText(line.substring(1));
                        break;
                }
            }
        }
    }

    public static void addingToMemberList(String username, String password){
        try {
            memberListFW = new FileWriter("memberList.txt",true);
            memberListBW = new BufferedWriter(memberListFW);
            memberListPW = new PrintWriter(memberListBW);
            memberListFW.write(username + "          " + password + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {

                if (memberListBW != null)
                    memberListBW.close();

                if (memberListFW != null)
                    memberListFW.close();

                if(memberListPW != null)
                    memberListPW.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
    }

    public static void addingTheAddedHotelsToTheCities(String cityName, ObservableList<String> observableList, ListView<String> listView){
        ArrayList<String> hotelNames = new ArrayList<>();
        observableList.add(1,cityDescription);
        try{
            File fileFive = new File(cityName + "HotelsFive.txt");
            File fileThree = new File(cityName + "HotelsThree.txt");
            if(fileFive.exists()){
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

                if(fileThree.exists()) {
                    flag = -1;
                    try {
                        readingFile = new Scanner(fileThree);
                    } catch (FileNotFoundException e) {
                        AlertBox.display("File Not Found Error!", "You first need to create such a file to access it!");
                    }

                    hotelNameWithStars = "";
                    while (readingFile.hasNext()) {
                        String line = readingFile.nextLine();
                        String hotelName;
                        if (line.length() > 0) {
                            if (line.charAt(0) == '☭') {
                                flag++;
                            }
                        }
                        if (flag == 0 || flag % 9 == 0) {
                            hotelName = line.substring(1);
                            hotelNameWithStars = hotelNameWithStars + hotelName + " ";
                            afterHotelNameCounter = 0;
                            while (readingFile.hasNext()) {
                                line = readingFile.nextLine();
                                if (line.charAt(0) == '☭') {
                                    afterHotelNameCounter++;
                                    flag++;
                                    if (afterHotelNameCounter == 2) {
                                        hotelNameWithStars = hotelNameWithStars + line.substring(3) + "\n";
                                        hotelNames.add(hotelNameWithStars);
                                        hotelNameWithStars = "";
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                observableList.addAll(hotelNames);
                listView.setItems(observableList);
            }
            else
                AlertBox.display("File Not Found Error!","You first need to create such a file to access it!");
        }
        catch(Exception e){
            AlertBox.display("Unknown Error!", "An unknown error has occured!");
        }
    }

    public static void addingCity(String cityName, String cityDescription, int cityPopulation, double cityAltitude, int cityAnnualVisit){
        try {
            memberListFW = new FileWriter("cityInformation.txt",true);
            memberListBW = new BufferedWriter(memberListFW);
            memberListPW = new PrintWriter(memberListBW);
            memberListFW.write((char)9773 + cityName + "\n" + (char)9773 + cityDescription + "\n" + (char)9773 + cityPopulation + "\n" + (char)9773 + cityAltitude + "\n" + (char)9773 + cityAnnualVisit + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {

                if (memberListBW != null)
                    memberListBW.close();

                if (memberListFW != null)
                    memberListFW.close();

                if(memberListPW != null)
                    memberListPW.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
    }

    public static void removingHotelFiveStars(File file, String hotelName) throws IOException{
        hotelNames = file;
        BufferedReader reader = new BufferedReader(new FileReader(hotelNames));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempHotels));

        try {
            readingFile = new Scanner(hotelNames);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        String lineToRemove = "";
        int flag = 0;
        boolean checker = false;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();

            String line = readingFile.nextLine();
            if (line.equals(hotelName))
                checker = true;

            if (line.length() > 0 && checker) {
                if (line.charAt(0) == '☭') {
                    flag++;
                }
                lineToRemove = line;
            }

            if(flag<13) {
                if (trimmedLine.equals(lineToRemove) && checker) continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }

        writer.close();
        reader.close();

        BufferedReader tempHotelsReader = new BufferedReader(new FileReader(tempHotels));
        BufferedWriter currentHotels = new BufferedWriter(new FileWriter(hotelNames));

        while((currentLine = tempHotelsReader.readLine()) != null) {
            currentHotels.write(currentLine + System.getProperty("line.separator"));
        }

        tempHotelsReader.close();
        currentHotels.close();
    }

    public static void removingHotelThreeStars(File file, String hotelName) throws IOException{
        hotelNames = file;
        BufferedReader reader = new BufferedReader(new FileReader(hotelNames));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempHotels));

        try {
            readingFile = new Scanner(hotelNames);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        String lineToRemove = "";
        int flag = 0;
        boolean checker = false;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();

            String line = readingFile.nextLine();
            if (line.equals(hotelName))
                checker = true;

            if (line.length() > 0 && checker) {
                if (line.charAt(0) == '☭') {
                    flag++;
                }
                lineToRemove = line;
            }

            if(flag<10) {
                if (trimmedLine.equals(lineToRemove) && checker) continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }

        writer.close();
        reader.close();

        BufferedReader tempHotelsReader = new BufferedReader(new FileReader(tempHotels));
        BufferedWriter currentHotels = new BufferedWriter(new FileWriter(hotelNames));

        while((currentLine = tempHotelsReader.readLine()) != null) {
            currentHotels.write(currentLine + System.getProperty("line.separator"));
        }

        tempHotelsReader.close();
        currentHotels.close();
    }

    public static void removingCity(String cityName) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(cityInformation));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempCityInformation));

        try {
            readingFile = new Scanner(cityInformation);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        String lineToRemove = "";
        int flag = 0;
        boolean checker = false;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();

            String line = readingFile.nextLine();
            if(line.equals(cityName))
                checker = true;

            if (line.length() > 0 && checker) {
                if (line.charAt(0) == '☭') {
                    flag++;
                }
                lineToRemove = line;
            }

            if(flag<6) {
                if (trimmedLine.equals(lineToRemove) && checker) continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }

        writer.close();
        reader.close();

        BufferedReader tempCityInformationReader = new BufferedReader(new FileReader(tempCityInformation));
        BufferedWriter backToCityInformationWriter = new BufferedWriter(new FileWriter(cityInformation));

        while((currentLine = tempCityInformationReader.readLine()) != null) {
            backToCityInformationWriter.write(currentLine + System.getProperty("line.separator"));
        }

        tempCityInformationReader.close();
        backToCityInformationWriter.close();
    }

    public static void addingReservations(File file, String hotelName, String checkIn, String checkOut, String price){
        try {
            memberListFW = new FileWriter(file.getName(),true);
            memberListBW = new BufferedWriter(memberListFW);
            memberListPW = new PrintWriter(memberListBW);
            memberListFW.write((char)9773 + hotelName + "\n" + (char)9773 + checkIn + "\n" + (char)9773 + checkOut + "\n" + (char)9773 + price + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {

                if (memberListBW != null)
                    memberListBW.close();

                if (memberListFW != null)
                    memberListFW.close();

                if(memberListPW != null)
                    memberListPW.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void addingHotelThreeStars(File file, String hotelName, String hotelDescription, int numberOfStars, int numberOfRooms, int yearOfBuilt, int nightlyCost, int singleRoomPrice, int doubleRoomPrice, int tripleRoomPrice){
        String starText = "";
        if(numberOfStars==1)
            starText = "★☆☆☆☆";
        if(numberOfStars==2)
            starText = "★★☆☆☆";
        if(numberOfStars==3)
            starText = "★★★☆☆";
        try {
            memberListFW = new FileWriter(file.getName(),true);
            memberListBW = new BufferedWriter(memberListFW);
            memberListPW = new PrintWriter(memberListBW);
            memberListFW.write((char)9773 + hotelName + "\n" + (char)9773 + hotelDescription + "\n" + (char)9773 + numberOfStars + " " + starText +  "\n" + (char)9773 + numberOfRooms + "\n" + (char)9773 + yearOfBuilt + "\n" + (char)9773 + nightlyCost + "\n" + (char)9773 + singleRoomPrice + "\n" + (char)9773 + doubleRoomPrice + "\n" + (char)9773 + tripleRoomPrice + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {

                if (memberListBW != null)
                    memberListBW.close();

                if (memberListFW != null)
                    memberListFW.close();

                if(memberListPW != null)
                    memberListPW.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void addingHotelFiveStars(File file, String hotelName, String hotelDescription, int numberOfStars, int numberOfRooms, int yearOfBuilt, int nightlyCost, int singleRoomPrice, int doubleRoomPrice, int tripleRoomPrice, int quadRoomPrice, int queenRoomPrice, int kingRoomPrice){
        String starText = "";
        if(numberOfStars==4)
            starText = "★★★★☆";
        if(numberOfStars==5)
            starText = "★★★★★";

        try {
            memberListFW = new FileWriter(file.getName(),true);
            memberListBW = new BufferedWriter(memberListFW);
            memberListPW = new PrintWriter(memberListBW);
            memberListFW.write((char)9773 + hotelName + "\n" + (char)9773 + hotelDescription + "\n" + (char)9773 + numberOfStars + " " + starText + "\n" + (char)9773 + numberOfRooms + "\n" + (char)9773 + yearOfBuilt + "\n" + (char)9773 + nightlyCost + "\n" + (char)9773 + singleRoomPrice + "\n" + (char)9773 + doubleRoomPrice + "\n" + (char)9773 + tripleRoomPrice + "\n" + (char)9773 + quadRoomPrice + "\n" + (char)9773 + queenRoomPrice + "\n" + (char)9773 + kingRoomPrice + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {

                if (memberListBW != null)
                    memberListBW.close();

                if (memberListFW != null)
                    memberListFW.close();

                if(memberListPW != null)
                    memberListPW.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static boolean checkingRememberMeFile(){
        int flag = 0;

        try {
            readingFile = new Scanner(rememberMe);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        while (readingFile.hasNextLine()) {
            username = readingFile.next();
            if (username.length() > 0)
                flag++;

            password = readingFile.next();
            if(password.length() > 0)
                flag++;
        }
        readingFile.close();

        if(flag==2) {
            return true;
        }
        else
            return false;
    }

    public static void formattingRememberMeFile(String username, String password){
        Formatter rememberMeFormatter = null;
        try {
            rememberMeFormatter = new Formatter(rememberMe);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        rememberMeFormatter.format("%s %s",username,password);
        rememberMeFormatter.close();
    }

    public static void cleaningRememberMeFile(){
        Formatter rememberMeFormatter = null;
        try {
            rememberMeFormatter = new Formatter(rememberMe);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        rememberMeFormatter.format("");
        rememberMeFormatter.close();
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
