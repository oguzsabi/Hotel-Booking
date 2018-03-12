package BookingPack;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Save {
    static FileWriter memberListFW;
    static BufferedWriter memberListBW;
    static PrintWriter memberListPW;
    static Scanner readingFile;
    static File adminList = new File("adminList.txt");
    static File memberList = new File("memberList.txt");
    static SignInController signInControllerObject = new SignInController();

    public static boolean readingAdminList(String username, String password) {
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
            if(password.equals(readingFile.next())) {
                flag++;
            }

            if(flag==2) {
                break;
            }
        }

        if(flag==2) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean readingMemberList(String username, String password){
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
            if(password.equals(readingFile.next())) {
                flag++;
            }

            if(flag==2) {
                break;
            }
        }

        if(flag==2) {
            return true;
        }
        else
            return false;
    }

    public static void addingToMemberList(String username, String password){
        try {
            memberListFW = new FileWriter("C:\\Users\\oguzs\\IdeaProjects\\Reservation\\memberList.txt",true);
            memberListBW = new BufferedWriter(memberListFW);
            memberListPW = new PrintWriter(memberListBW);
            memberListFW.write(username + " " + password + "\n");
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
}
