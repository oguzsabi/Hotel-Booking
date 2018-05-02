package CityPack;

public class Canakkale extends City {
    public Canakkale() {
        super.setCityPopulation(530417);
        super.setCityAltitude(3.0);
        super.setAnnualTouristVisit(60000);
    }

    public Canakkale(String cityName, String cityDescription, double cityAltitude, int cityPopulation, int annualTouristVisit) {
        super(cityName, cityDescription, cityAltitude, cityPopulation, annualTouristVisit);
    }

    @Override
    public String cityDescription() {
        String defaultCityDescription;
        return defaultCityDescription = "   If you thought Çanakkale was worth visiting only as a launching point for Gallipoli's battlefields, think again. " +
                                        "The presence of the highly regarded Çanakkale Onsekiz Mart University endows this small city with a sizeable student population that loves nothing more than to eat, drink and party";
    }
}
