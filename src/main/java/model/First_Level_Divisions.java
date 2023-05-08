package model;


/**
 *  class to create a first level division object
 */
public class First_Level_Divisions {
    private int divisionId;
    private String division;
    private String createdBy;
    private String lastUpdateBy;
    private int countryId;


    /**
     * construct the first level division class
     * @param divisionId
     * Division ID
     * @param division
     * Division
     * @param createdBy
     * Created BY
     * @param lastupdateBy
     * Last Updated By
     * @param countryId
     * Country ID
     */
    public First_Level_Divisions(int divisionId, String division,String createdBy, String lastupdateBy, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.createdBy = createdBy;
        this.lastUpdateBy = lastupdateBy;
        this.countryId = countryId;

    }

    /**
     * @return
     * returns division id
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * @param divisionId
     * set division id
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * @return
     * returns division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @param division
     * set division
     */
    public void setDivision(String division) {
        this.division = division;
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
     * set created by
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return
     * returns last updated By
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * @param lastUpdateBy
     * sets last update by
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
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
}
