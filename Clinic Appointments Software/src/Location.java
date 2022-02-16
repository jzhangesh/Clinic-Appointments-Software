/**
 * enum class to define the 5 locations available for vaccination appointments
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public enum Location {
    /**
     * county names as the constant names and define the zip codes and city names as the properties
     */
    SOMERSET("08807", "Bridgewater"),
    MIDDLESEX("08854", "Piscataway"),
    MERCER("08542", "Princeton"),
    MORRIS("07960", "Morristown"),
    UNION("07083", "Union");

    private final String zips;
    private final String cities;

    /**
     * Correspond to zips and cities
     * @param zips County corresponding zip code
     * @param cities County corresponds to the city
     */
    Location(String zips, String cities) {
        this.zips = zips;
        this.cities = cities;
    }

    /**
     *get zips
     * @return zips
     */
    public String getZips() {
        return zips;
    }

    /**
     * get cities
     * @return cities
     */
    public String getCities() {
        return cities;
    }

}