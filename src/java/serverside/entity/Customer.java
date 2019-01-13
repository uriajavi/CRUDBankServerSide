/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverside.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity representing bank customers. Contains personal data, identification 
 * data and relational data for accessing customer accounts data. 
 * @author javi
 */
@Entity
@Table(name="customer",schema="bankdb")
@XmlRootElement
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Identification field for client.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * First name of the customer.
     */
    private String firstName;
    /**
     * Customer's last name.
     */
    private String lastName;
    /**
     * Customer's middle name initial.
     */
    private String middleInitial;
    /**
     * Customer's address street.
     */
    private String street;
    /**
     * Customer's address city.
     */
    private String city;
    /**
     * Customer's address state.
     */
    private String state;
        /**
     * Customer's address zip.
     */
    private Integer zip;
    /**
     * Customer's phone.
     */
    private Long phone;
    /**
     * Customer's email.
     */
    private String email;
    /**
     * Relational field for customer's accounts.
     */
    @ManyToMany
    @JoinTable(schema="bankdb")
    private List<Account> accounts;
    /**
     * 
     * @return the id of the customer.
     */
    public Long getId() {
        return id;
    }
    /**
     * 
     * @param id Customer's id.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @return the middleInitial
     */
    public String getMiddleInitial() {
        return middleInitial;
    }
    /**
     * @param middleInitial the middleInitial to set
     */
    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }
    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }
    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }
    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }
    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * @return the state
     */
    public String getState() {
        return state;
    }
    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }
    /**
     * @return the zip
     */
    public Integer getZip() {
        return zip;
    }
    /**
     * @param zip the zip to set
     */
    public void setZip(Integer zip) {
        this.zip = zip;
    }
    /**
     * @return the phone
     */
    public Long getPhone() {
        return phone;
    }
    /**
     * @param phone the phone to set
     */
    public void setPhone(Long phone) {
        this.phone = phone;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the accounts
     */
    public List<Account> getAccounts() {
        return accounts;
    }
    /**
     * @param accounts the accounts to set
     */
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    /**
     * Integer representation for Customer instance.
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    /**
     * Compares two Customer objects for equality. This method consider a Customer 
     * equal to another Customer if their id fields have the same value. 
     * @param object The other Customer object to compare to.
     * @return true if ids are equals.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    /**
     * Obtains a string representation of the Customer.
     * @return The String representing the Customer.
     */
    @Override
    public String toString() {
        return "Customer[ name="+ firstName+" "+lastName+" "+" id=" + id + " ]";
    }
}
