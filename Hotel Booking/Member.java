import java.util.*;

public class Member {
    static Scanner input = new Scanner(System.in);
    static String memberName, memberPassword;

    public static boolean firstMemberMenu(){
        int choice = 0;
        System.out.printf("Welcome to MAESTRO.\n1. Log In\n2. Sign up\n(Enter 1 or 2)\n");
        choice = input.nextInt();
        System.out.println("---------------------------------------------------");
        if(choice==1){
            while(Save.answer) {
                System.out.printf("Member Name: ");
                memberName = input.next();
                if(memberName.equals("-3")){
                    System.out.printf("Let's get you signed up: ");
                    System.out.printf("\nMember Name: ");
                    memberName = input.next();
                    System.out.printf("\nMember Password: ");
                    memberPassword = input.next();
                    Save.addingToMemberList(memberName,memberPassword);
                    break;
                }
                System.out.printf("\nMember Password: ");
                memberPassword = input.next();

                Save.readingMemberList();
            }
        }

        else if(choice==2){
            System.out.printf("Let's get you signed up: ");
            System.out.printf("\nMember Name: ");
            memberName = input.next();
            System.out.printf("\nMember Password: ");
            memberPassword = input.next();
            Save.addingToMemberList(memberName,memberPassword);
        }
        return true;
    }

    public static void secondMemberMenu(){
        System.out.printf("Welcome to the menu %s!\n\nYour options are listed below:\n",memberName);
        System.out.printf("1. Make a reservation\n2. Return back to the log in screen\n3. Exit the system");
    }
}
