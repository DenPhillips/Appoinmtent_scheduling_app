package model;

/**
 * Class to create a customer object
 */
public class Customers {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private String createdBy;
    private String lastUpdatedBy;
    private int divisionId;

    /**
     * constructs the customer class
     * @param customerId
     * Customer ID
     * @param customerName
     * Customer Name
     * @param address
     * Customer Address
     * @param postalCode
     * Customer Postal Code
     * @param phone
     * Customer Phone Numebr
     * @param createdBy
     * Created By
     * @param lastUpdatedBy
     * Last Updated By
     * @param divisionId
     * Division ID
     */
    public Customers(int customerId, String customerName, String address, String postalCode, String phone, String createdBy, String lastUpdatedBy, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
    }


    /**
     * @return
     * returns the customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId
     * set the customer id
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return
     * returns the customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName
     * sets the customer name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return
     * returns the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     * set the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return
     * returns the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode
     * set the postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return
     * returns the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     * sets the phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * returns the last update by
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @param lastUpdatedBy
     * sets the last update by
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return
     * returns the division id
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * @param divisionId
     * set the division id
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
}
