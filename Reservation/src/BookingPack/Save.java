package BookingPack;

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
    static private String username, password;

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
        /*try {
            readingFile = new Scanner(memberList);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        while (readingFile.hasNextLine()){
            String line = readingFile.nextLine();
            if(memberInformation.equals(line)){

            }
        }
        readingFile.close();*/

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
