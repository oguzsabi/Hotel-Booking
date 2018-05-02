package CityPack;

public class Edirne extends City {
    public Edirne() {
        super.setCityPopulation(406855);
        super.setCityAltitude(48.0);
        super.setAnnualTouristVisit(3190000);
    }

    public Edirne(String cityName, String cityDescription, double cityAltitude, int cityPopulation, int annualTouristVisit) {
        super(cityName, cityDescription, cityAltitude, cityPopulation, annualTouristVisit);
    }

    @Override
    public String cityDescription() {
        String defaultCityDescription;
        return defaultCityDescription = "   Edirne is blessed with imperial building stock, a notable culinary heritage and a lingering and much-cherished sense of civic grandeur. " +
                                        "Close to the Greek and Bulgarian borders, the city has a European flavour that is best appreciated in summer";
    }
}
