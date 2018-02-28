import java.util.*;

public class Admin {
    static String adminName, adminPassword;
    static Scanner input = new Scanner(System.in);

    public static boolean adminLogIn() {
        while(Save.answer) {
            System.out.printf("Admin Name: ");
            adminName = input.next();
            System.out.printf("\nAdmin Password: ");
            adminPassword = input.next();

            Save.readingAdminList();
        }
        return true;
    }

    public static void adminMenu(){
        System.out.printf("Welcome to the menu %s!\n\nYour options are listed below:\n",adminName);
        System.out.printf("1. Add a new hotel to the list\n2. Remove an existing hotel from the list\n3. Make a reservation for a member\n4. Return back to the log in screen\n5. Exit the system");
    }
}
