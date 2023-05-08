package model;

/**
 * class to create a contact object
 */
public class Contacts {
    private int contactId;
    private String contactName;
    private String contactEmail;

    /**
     * constructs the contact class
     * @param contactId
     * Contact ID
     * @param contactName
     * Contact Name
     * @param contactEmail
     * Contact Email
     */
    public Contacts(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
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
     * set the contact id
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * @return
     * returns the contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName
     * set the contact namne
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return
     * returns the contact email
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * @param contactEmail
     * set the contact email
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
