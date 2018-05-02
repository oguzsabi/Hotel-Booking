package CityPack;

public class Izmir extends City {
    public Izmir() {
        super.setCityPopulation(4274000);
        super.setCityAltitude(25.0);
        super.setAnnualTouristVisit(409991);
    }

    public Izmir(String cityName, String cityDescription, double cityAltitude, int cityPopulation, int annualTouristVisit) {
        super(cityName, cityDescription, cityAltitude, cityPopulation, annualTouristVisit);
    }

    @Override
    public String cityDescription() {
        String defaultCityDescription;
        return defaultCityDescription = "   “The beautiful city,” as it is known in Turkey, Izmir welcomes the visitor with a demonstration of warmth and friendliness worthy of the renowned Turkish hospitality. " +
                                        "A city of contrasts, Izmir offers the visitor the best of both the modern and ancient worlds steeped in history, while at the same time, a cosmopolitan and vibrant city. " +
                                        "With warm summers and mild winters, Izmir is a year-round destination.";
    }
}
