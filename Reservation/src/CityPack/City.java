package CityPack;

public class City {
    private String[] defaultCityNames = {"Izmir","Istanbul","Ankara","Antalya","Gaziantep","Bursa","Canakkale","Edirne"};
    private String cityName, cityDescription;
    private double cityAltitude;
    private int cityPopulation, annualTouristVisit;

    public City(){
        this.cityName = "NO_CITY_NAME_ENTERED";
        this.cityDescription = "NO_CITY_DESCRIPTION_ENTERED";
        this.cityPopulation = 1;
        this.annualTouristVisit = 0;
        this.cityAltitude = 0.0;
    }

    public City(String cityName, String cityDescription, double cityAltitude, int cityPopulation, int annualTouristVisit) {
        this.cityName = cityName;
        this.cityDescription = cityDescription;
        this.cityAltitude = cityAltitude;
        this.cityPopulation = cityPopulation;
        this.annualTouristVisit = annualTouristVisit;
    }

    public String cityDescription(){
        String defaultCityDescription;
        return defaultCityDescription = "This city has some beautiful places to see. During your stay you will see incredible scenery.";
    }

    public String[] getDefaultCityNames() {
        return defaultCityNames;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityDescription() {
        return cityDescription;
    }

    public void setCityDescription(String cityDescription) {
        this.cityDescription = cityDescription;
    }

    public double getCityAltitude() {
        return cityAltitude;
    }

    public void setCityAltitude(double cityAltitude) {
        this.cityAltitude = cityAltitude;
    }

    public int getCityPopulation() {
        return cityPopulation;
    }

    public void setCityPopulation(int cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    public int getAnnualTouristVisit() {
        return annualTouristVisit;
    }

    public void setAnnualTouristVisit(int annualTouristVisit) {
        this.annualTouristVisit = annualTouristVisit;
    }
}
