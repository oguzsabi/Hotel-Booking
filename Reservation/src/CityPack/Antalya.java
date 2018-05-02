package CityPack;

public class Antalya extends City {
    public Antalya() {
        super.setCityPopulation(2288000);
        super.setCityAltitude(43.0);
        super.setAnnualTouristVisit(7500000);
    }

    public Antalya(String cityName, String cityDescription, double cityAltitude, int cityPopulation, int annualTouristVisit) {
        super(cityName, cityDescription, cityAltitude, cityPopulation, annualTouristVisit);
    }

    @Override
    public String cityDescription() {
        String defaultCityDescription;
        return defaultCityDescription = "   Antalya is the pearl of the Turkish Riviera - an exciting major city, and a paradise for swimmers. " +
                                        "The coast has innumerable delightful beaches that suit all tastes and along the coast, you will find lots of high-class hotels and restaurants. " +
                                        "Those who want to take a break and get away from beach life can make interesting excursions to antique ruins and beautiful landscapes.";
    }
}
