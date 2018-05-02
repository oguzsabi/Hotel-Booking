package CityPack;

public class Gaziantep extends City {
    public Gaziantep() {
        super.setCityPopulation(2005000);
        super.setCityAltitude(840.0);
        super.setAnnualTouristVisit(1500000);
    }

    public Gaziantep(String cityName, String cityDescription, double cityAltitude, int cityPopulation, int annualTouristVisit) {
        super(cityName, cityDescription, cityAltitude, cityPopulation, annualTouristVisit);
    }

    @Override
    public String cityDescription() {
        String defaultCityDescription;
        return defaultCityDescription = "   There's one Turkish word you should learn before visiting Gaziantep: fıstık (pistachio). " +
                                        "This fast-paced and epicurean city has around 180 pastry shops producing the world's best pistachio baklava. " +
                                        "Other culinary treats are also on offer for adventurous foodie travellers.";
    }
}
