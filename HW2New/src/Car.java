public class Car extends Vehicle {
    private double trunkVolume;
    private int flag = 0;

    public Car(double trunkVolume) {
        this.trunkVolume = trunkVolume;
    }

    public Car(int numberOfWheels, int horsepower, int numberOfSeats, String brandName, double trunkVolume) {
        super(numberOfWheels, horsepower, numberOfSeats, brandName);
        this.trunkVolume = trunkVolume;
    }

    @Override
    public void display(){
        System.out.printf("This vehicle with the brand name %s has:\n%d Wheel(s)\n%.2f m^3 Trunk Volume\n%d Horsepower\n%d Seat(s)\n\n",super.getBrandName(),super.getNumberOfWheels(),trunkVolume,super.getHorsepower(),super.getNumberOfSeats());
    }

    @Override
    public void certificationOfTheVehicle(){
        if(flag==0) {
            System.out.println("Certification of the car is being created...");
            flag++;
        }
        else {
            System.out.println();
            System.out.println("Certification of the car is being called...");
        }

        String certification = super.getBrandName() + super.getHorsepower() + super.getNumberOfWheels() + (int)trunkVolume;
        System.out.println("The vehicle's certification is " + certification);
        System.out.println();
    }

    public double getTrunkVolume() {
        return trunkVolume;
    }

    public void setTrunkVolume(double trunkVolume) {
        if(trunkVolume>=0)
            this.trunkVolume = trunkVolume;
        else
            System.out.println("The car's trunk volume cannot be a negative value!");
    }
}
