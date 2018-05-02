package CityPack;

public class Ankara extends City  {
    public Ankara() {
        super.setCityPopulation(5445000);
        super.setCityAltitude(870.0);
        super.setAnnualTouristVisit(324476);
    }

    public Ankara(String cityName, String cityDescription, double cityAltitude, int cityPopulation, int annualTouristVisit) {
        super(cityName, cityDescription, cityAltitude, cityPopulation, annualTouristVisit);
    }

    @Override
    public String cityDescription() {
        String defaultCityDescription;
        return defaultCityDescription = "   The heart of the Turkish Republic, Ankara, is the second largest city of Turkey after Istanbul. The city genuinely lies on the border where east meets west. " +
                                        "This means that a traveller can sit outside at a cafe under the shade of a tree-lined boulevard eating Meze. " +
                                        "The European influences combined with the ones of the Middle East have coloured the architecture, food, wine, nightlife, fashion and arts.";
    }
}
