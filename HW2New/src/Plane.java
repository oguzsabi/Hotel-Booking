public class Plane extends Vehicle{
    private int numberOfWings;
    private int flag = 0;

    public Plane(int numberOfWings) {
        this.numberOfWings = numberOfWings;
    }

    public Plane(int numberOfWheels, int horsepower, int numberOfSeats, String brandName, int numberOfWings) {
        super(numberOfWheels, horsepower, numberOfSeats, brandName);
        this.numberOfWings = numberOfWings;
    }

    @Override
    public void display(){
        System.out.printf("This vehicle with the brand name %s has:\n%d Wheel(s)\n%d Wing(s)\n%d Horsepower\n%d Seat(s)\n\n",super.getBrandName(),super.getNumberOfWheels(),numberOfWings,super.getHorsepower(),super.getNumberOfSeats());
    }

    @Override
    public void certificationOfTheVehicle(){
        if(flag==0) {
            System.out.println("Certification of the plane is being created...");
            flag++;
        }
        else {
            System.out.println();
            System.out.println("Certification of the plane is being called...");
        }

        String certification = super.getBrandName() + super.getHorsepower() + super.getNumberOfWheels() + numberOfWings;
        System.out.println("The vehicle's certification is " + certification);
        System.out.println();
    }

    public int getNumberOfWings() {
        return numberOfWings;
    }

    public void setNumberOfWings(int numberOfWings) {
        if(numberOfWings>0)
            this.numberOfWings = numberOfWings;
        else
            System.out.println("The plane must have at least 1 wing to be able to fly!");
    }
}
