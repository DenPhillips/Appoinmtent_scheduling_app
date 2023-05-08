package model;


/**
 * class to create an appointment object
 */
public class Appointments {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private String start;
    private String end;
    private String createdBy;
    private String lastUpdateBy;
    private int customerId;
    private int userId;
    private int contactId;


    /**
     * construct the Appointment class
     * @param appointmentId
     * Appointment ID
     * @param title
     * Appointment Title
     * @param description
     * Appointment Description
     * @param location
     * Appointment Location
     * @param type
     * Appointment Tyoe
     * @param start
     * Appointment Start
     * @param end
     * Appointment End
     * @param createdBy
     * Created By
     * @param lastUpdateBy
     * Last Updated By
     * @param customerId
     * Customer ID
     * @param userId
     * User ID
     * @param contactId
     * Contact ID
     */
    public Appointments(int appointmentId, String title, String description, String location, String type,
                       String start, String end, String createdBy,
                        String lastUpdateBy, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createdBy = createdBy;
        this.lastUpdateBy = lastUpdateBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * @return
     * returns the appointment id
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * @param appointmentId
     * sets the appoitment id
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return
     * returns the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     * sets the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return
     * returns the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     * sets the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return
     * returns the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     * sets the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return
     * returns the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     * sets the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return
     * returns the start
     */
    public String getStart() {
        return start;
    }

    /**
     * @param start
     * sets the start
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * @return
     * returns the end
     */
    public String getEnd() {
        return end;
    }

    /**
     * @param end
     * sets the end
     */
    public void setEnd(String end) {
        this.end = end;
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
     * sets creaded by
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
     * @param lastUpdateBy
     * sets last updated by
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
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
     * sets the customer id
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return
     * returns the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId
     * sets the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return
     * returns the contact id
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @param contactId
     * sets the contact id
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}
