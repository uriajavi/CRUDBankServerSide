/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverside.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import static javax.persistence.CascadeType.MERGE;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity representing movements for accounts. It contains the following
 * fields: id, account id, description, movement timestamp, account balance after the
 * movement and amount of the movement. 
 * @author Javier Martín Uría
 */
@Entity
@Table(name="movement",schema="bankdb")
@NamedQuery(name="findMovementsByAccount",
            query="SELECT m FROM Movement m WHERE m.account = :account"
)
@XmlRootElement
public class Movement implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Identification field for Movement. 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Account for the movement.
     */
    @ManyToOne
    private Account account;
    /**
     * Timestamp for the movement.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(as=Date.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date timestamp;
    /**
     * Amount of the movement.
     */
    private Double amount;
    /**
     * Account balance after the movement.
     */
    private Double balance;
    /**
     * Description of the movement.
     */
    private String description;
    /**
     * Integer representation for Movement instance.
     * @return 
     */    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }
    /**
     * Compares two Movement objects for equality. This method consider a Movement 
     * equal to another Movement if their id fields have the same value. 
     * @param object The other Movement object to compare to.
     * @return true if ids are equals.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movement)) {
            return false;
        }
        Movement other = (Movement) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    /**
     * Obtains a string representation of the Movement.
     * @return The String representing the Movement.
     */    
    @Override
    public String toString() {
        return "serverside.entity.Movement[ id=" + getId() + " ]";
    }

    /**
     * Identification field for Movement.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Identification field for Movement.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Account for the movement.
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Account for the movement.
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Timestamp for the movement.
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Timestamp for the movement.
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Amount of the movement.
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Amount of the movement.
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Account balance after the movement.
     * @return the balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * Account balance after the movement.
     * @param balance the balance to set
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * Description of the movement.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Description of the movement.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
