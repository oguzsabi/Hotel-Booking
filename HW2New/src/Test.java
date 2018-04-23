import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Vehicle[] vehicleArray = new Vehicle[8];
        int counter = 0;

        while(true){
            System.out.println("<<<<<  Please choose one of the options below  >>>>>");
            System.out.println("1. Add a car");
            System.out.println("2. Add a plane");
            System.out.println("3. Display information of all vehicles");
            System.out.println("4. Show certification of all vehicles");
            System.out.println("5. Exit the program");
            int choice = input.nextInt();
            input.nextLine();
            if(counter<vehicleArray.length) {
                if (choice == 1) {
                    while(true) {
                        System.out.println("You have chosen to add a car...");
                        System.out.print("Please enter the car's brand name: ");
                        String tempBrand = input.nextLine();
                        System.out.println();
                        System.out.print("Please enter how many wheel(s) the car has: ");
                        int tempWheel = input.nextInt();
                        System.out.println();
                        System.out.print("Please enter the car's horsepower: ");
                        int tempHorse = input.nextInt();
                        System.out.println();
                        System.out.print("Please enter how many seat(s) the car has: ");
                        int tempSeat = input.nextInt();
                        System.out.println();
                        System.out.print("Please enter the volume of the car's trunk(in m^3): ");
                        double tempTrunk = input.nextDouble();
                        input.nextLine();
                        System.out.println();

                        if(tempHorse>0 && tempWheel>0 && tempSeat>0 && tempTrunk>0 && tempBrand.length()>0 && Character.isLetter(tempBrand.charAt(0))) {
                            vehicleArray[counter] = new Car(tempWheel, tempHorse, tempSeat, tempBrand, tempTrunk);
                        }
                        else{
                            System.out.println("!!!!! Your values must be positive AND your brand name must start with a letter !!!!!");
                            System.out.println();
                            continue;
                        }
                        counter++;
                        break;
                    }
                }
                if (choice == 2) {
                    while(true) {
                        System.out.println("You have chosen to add a plane...");
                        System.out.print("Please enter the plane's brand name: ");
                        String tempBrand = input.nextLine();
                        System.out.println();
                        System.out.print("Please enter how many wheel(s) the plane has: ");
                        int tempWheel = input.nextInt();
                        System.out.println();
                        System.out.print("Please enter the plane's horsepower: ");
                        int tempHorse = input.nextInt();
                        System.out.println();
                        System.out.print("Please enter how many seat(s) the plane has: ");
                        int tempSeat = input.nextInt();
                        System.out.println();
                        System.out.print("Please enter how many wing(s) the plane has: ");
                        int tempWing = input.nextInt();
                        input.nextLine();
                        System.out.println();

                        if(tempHorse>0 && tempWheel>2 && tempSeat>0 && tempWing>0 && tempBrand.length()>0 && Character.isLetter(tempBrand.charAt(0))) {
                            vehicleArray[counter] = new Plane(tempWheel, tempHorse, tempSeat, tempBrand, tempWing);
                        }
                        else {
                            System.out.println("!!!!! Your values must be positive AND your brand name must start with a letter !!!!!");
                            System.out.println();
                            continue;
                        }
                        counter++;
                        break;
                    }
                }
            }
            else if(counter>=vehicleArray.length && (choice == 1 || choice == 2)) {
                System.out.println();
                System.out.println("YOU CANNOT ADD MORE PLANES OR CARS!!!");
                System.out.println();
            }

            if(choice==3){
                for(int i = 0; i<counter; i++){
                    System.out.println("-----> This is the vehicle at index " + i);
                    vehicleArray[i].display();
                }
            }
            if (choice==4){
                for(int i = 0; i<counter; i++){
                    System.out.println("-----> This is the vehicle at index " + i);
                    vehicleArray[i].certificationOfTheVehicle();
                }
            }
            if (choice==5){
                System.out.println("Now exiting the program.....");
                break;
            }
        }
    }
}
