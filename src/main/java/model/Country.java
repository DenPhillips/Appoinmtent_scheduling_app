package model;


/**
 * class to create a country object
 */
public class Country {
    private int countryId;
    private String countryName;
    private String createdBy;
    private String lastUpdateBy;

    /**
     * constructs the country class
     * @param countryId
     * Country ID
     * @param countryName
     * Country Name
     * @param createdBy
     * Created By
     * @param lastUpdatedBy
     * Last Updated by
     */
    public Country(int countryId, String countryName,
                   String createdBy, String lastUpdatedBy) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.createdBy = createdBy;
        this.lastUpdateBy = lastUpdatedBy;
    }

    /**
     * @return
     * returns the country id
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * @param countryId
     * set the country id
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * @return
     * returns the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName
     * sets the country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return
     * returns created by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     * sets created by
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return
     * returns last updated by
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * @param lastUpdate
     * set lasts updated by
     */
    public void setLastUpdateBy(String lastUpdate) {
        this.lastUpdateBy = lastUpdateBy;
    }

}
