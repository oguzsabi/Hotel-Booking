package CityPack;

public class Denizli extends City {
    public Denizli() {
        super.setCityPopulation(1018000);
        super.setCityAltitude(450.0);
        super.setAnnualTouristVisit(6000000);
    }

    public Denizli(String cityName, String cityDescription, double cityAltitude, int cityPopulation, int annualTouristVisit) {
        super(cityName, cityDescription, cityAltitude, cityPopulation, annualTouristVisit);
    }

    @Override
    public String cityDescription() {
        String defaultCityDescription;
        return defaultCityDescription = "   Denizli is the commercial and transportation hub of inland region of southeastern Aegean Turkey. " +
                                        "It is nestled against the hillside at the southern side of the plains of Buyuk Menderes River. " +
                                        "It’s a busy place partly because of nearby Pamukkale and its travertine pools, but mainly because it’s the main commercial hub for the agricultural produce coming from the region.";
    }
}
