public abstract class Vehicle {
    private int numberOfWheels;
    private int horsepower;
    private int numberOfSeats;
    private String brandName;

    public Vehicle() {
    }

    public Vehicle(int numberOfWheels, int horsepower, int numberOfSeats, String brandName) {
        this.numberOfWheels = numberOfWheels;
        this.horsepower = horsepower;
        this.numberOfSeats = numberOfSeats;
        this.brandName = brandName;
    }

    public void display(){
        System.out.printf("This vehicle with the brand name %s has:\n%d Wheel(s)\n%d Horsepower\n%d Seat(s)\n",brandName,numberOfWheels,horsepower,numberOfSeats);
    }

    public abstract void certificationOfTheVehicle();

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        if(numberOfWheels>0)
            this.numberOfWheels = numberOfWheels;
        else
            System.out.println("The vehicle must have at least 1 wheel!");
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        if(horsepower>0)
            this.horsepower = horsepower;
        else
            System.out.println("The vehicle must posses the power of at least 1 horse!");
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        if(numberOfSeats>=0)
            this.numberOfSeats = numberOfSeats;
        else
            System.out.println("The vehicle cannot contain a negative number of seats!");
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        for(int i = 0; i<brandName.length(); i++){
            if(Character.isLetter(brandName.charAt(i))){
                this.brandName = brandName;
                break;
            }
            if(i==(brandName.length()-1))
                System.out.println("The brand name of the vehicle must contain at least 1 letter!");
        }

    }
}
