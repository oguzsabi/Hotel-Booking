package CityPack;

public class Istanbul extends City {
    public Istanbul() {
        super.setCityPopulation(15029000);
        super.setCityAltitude(30.0);
        super.setAnnualTouristVisit(9844000);
    }

    public Istanbul(String cityName, String cityDescription, double cityAltitude, int cityPopulation, int annualTouristVisit) {
        super(cityName, cityDescription, cityAltitude, cityPopulation, annualTouristVisit);
    }

    @Override
    public String cityDescription() {
        String defaultCityDescription;
        return defaultCityDescription = "   It’s easy to lose yourself In Istanbul’s grandiose history, but modern Istanbul is a simmering metropolis, well worth a visit in its own right. " +
                                        "The shopping is world class and the nightlife pulsates around Taksim Square. " +
                                        "Even though Istanbul is no longer the capital, it’s still the country’s business and cultural centre, still the city where new trends are created.";
    }
}
