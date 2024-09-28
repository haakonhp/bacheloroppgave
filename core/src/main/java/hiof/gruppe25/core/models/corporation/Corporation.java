package hiof.gruppe25.core.models.corporation;

import java.util.UUID;

public class Corporation {
    private UUID id = UUID.randomUUID();
    private String companyName;
    private String companySector;
    private int contactNumber;
    private String contactPerson;
    private String contactEmail;
    private int numberOfEmployees;

    public Corporation() {
    }

    public Corporation(String companyName, String companySector, String contactPerson, String contactEmail, int contactNumber, int numberOfEmployees) {
        this.companyName = companyName;
        this.companySector = companySector;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.numberOfEmployees = numberOfEmployees;
    }

    public Corporation(UUID id, String companyName, String companySector, int contactNumber, String contactPerson, String contactEmail, int numberOfEmployees) {
        this.id = id;
        this.companyName = companyName;
        this.companySector = companySector;
        this.contactNumber = contactNumber;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.numberOfEmployees = numberOfEmployees;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanySector() {
        return companySector;
    }

    public void setCompanySector(String companySector) {
        this.companySector = companySector;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }
}
