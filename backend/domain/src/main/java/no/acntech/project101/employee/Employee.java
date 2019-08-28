package no.acntech.project101.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "COMPANY_ID")
    private Long companyId;

    //TODO Create the enitity for Employee

    public Employee() {
        // Hibernate
    }

    public Employee(final String firstName, final String lastName, final LocalDate dateOfBirth, final Long companyId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.companyId = companyId;
    }


    public Long getId() {
        return id;
    }

    // Get & Set for firstName
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    // Get & Set for lastName
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    // Get & Set for dateOfBirth
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }
    public void setDateOfBirth(final LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // Get & Set for companyId
    public Long getCompanyId() {
        return this.companyId;
    }
    public void setCompanyId(final Long companyId) {
        this.companyId = companyId;
    }
}
