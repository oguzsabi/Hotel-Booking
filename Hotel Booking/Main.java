import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args){
        System.out.printf("Welcome to the hotel booking system Maestro.\n(Please press 1 if you are an admin or 2 if you are a user.)\n");
        Scanner input = new Scanner(System.in);

        int logInOption = input.nextInt();
        System.out.println("---------------------------------------------------");

        if(logInOption==1){
            if(Admin.adminLogIn()) {
                Admin.adminMenu();
            }
        }

        else if(logInOption==2){
            if(Member.firstMemberMenu()) {
                Member.secondMemberMenu();
            }
        }
    }
}
