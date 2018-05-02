package CityPack;

public class Bursa extends City {
    public Bursa() {
        super.setCityPopulation(2936000);
        super.setCityAltitude(100.0);
        super.setAnnualTouristVisit(521000);
    }

    public Bursa(String cityName, String cityDescription, double cityAltitude, int cityPopulation, int annualTouristVisit) {
        super(cityName, cityDescription, cityAltitude, cityPopulation, annualTouristVisit);
    }

    @Override
    public String cityDescription() {
        String defaultCityDescription;
        return defaultCityDescription = "   Modern, industrial Bursa is built around the mosques, mausoleums and other sites from its incarnation as first Ottoman capital. " +
                                        "Despite being built-up and somewhat chaotic, its durable Ottoman core and abundant parks keep it remarkably placid in places.";
    }
}
