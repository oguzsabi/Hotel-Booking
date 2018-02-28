import java.util.*;
import java.io.*;
import java.lang.*;

public class Save {
    static FileWriter memberListFW;
    static BufferedWriter memberListBW;
    static PrintWriter memberListPW;
    static Scanner readingFile;
    static boolean answer = true;
    static File adminList = new File("C:\\Users\\oguzs\\IdeaProjects\\Reservation\\adminList.txt");
    static File memberList = new File("C:\\Users\\oguzs\\IdeaProjects\\Reservation\\memberList.txt");

    public static void readingAdminList() {
        int flag = 0;

        try {
            readingFile = new Scanner(adminList);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        while(readingFile.hasNext()) {
            if(Admin.adminName.equals(readingFile.next())) {
                    flag++;
            }
            if(Admin.adminPassword.equals(readingFile.next())) {
                flag++;
            }
        }

        if(flag==2) {
            System.out.println("Log in was successful...");
            System.out.println("---------------------------------------------------");
            answer = false;
        }
        else {
            System.out.println("Log in has failed. Please check your admin name or password!\n");
            answer = true;
        }
    }

    public static void readingMemberList(){
	    int flag = 0;

        try {
            readingFile = new Scanner(memberList);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        while(readingFile.hasNext()) {
            if(Member.memberName.equals(readingFile.next())) {
                flag++;
            }
            if(Member.memberPassword.equals(readingFile.next())) {
                flag++;
            }
        }

        if(flag==2) {
            System.out.println("Log in was successful...");
            System.out.println("---------------------------------------------------");
            answer = false;
        }
        else {
            System.out.println("Log in has failed. Please check your admin name or password!\n(Enter -3 to go to sign up screen)\n");
            answer = true;
        }
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
        System.out.println("---------------------------------------------------");
    }
}
