/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverside.service;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;
import serverside.entity.Account;
import serverside.entity.Customer;
import serverside.entity.Movement;
import serverside.exceptions.CreateException;
import serverside.exceptions.DeleteException;
import serverside.exceptions.ReadException;
import serverside.exceptions.UpdateException;

/**
 * Local interface for BankManager EJB.
 * @author Javier Martín Uría
 */
@Local
public interface BankManagerLocal {
    /**
     * This method creates a new account in the data store.
     * @param account The Account entity object containing new account data.
     * @throws CreateException Thrown when any error or exception occurs during 
     * creation.
     */
    public void createAccount (Account  account) throws CreateException;
    /**
     * This method creates a new customer in the data store.
     * @param customer The Customer entity object containing new customer data.
     * @throws CreateException Thrown when any error or exception occurs during 
     * creation.
     */
    public void createCustomer (Customer  customer) throws CreateException;
    /**
     * This method creates a new movement for an account in the data store.
     * @param movement The Movement entity object containing new movement data.
     * @throws CreateException Thrown when any error or exception occurs during 
     * creation.
     */
    public void createMovement (Movement  movement) throws CreateException;
    /**
     * This method updates an account data in the data store.
     * @param account The Account entity object containing modified account data.
     * @throws UpdateException Thrown when any error or exception occurs during 
     * update.
     */
    public void updateAccount(Account account) throws UpdateException;
    /**
     * This method updates a customer data in the data store.
     * @param customer The Customer entity object containing modified customer data.
     * @throws UpdateException Thrown when any error or exception occurs during 
     * update.
     */
    public void updateCustomer(Customer customer)throws UpdateException;
    /**
     * This method updates a movement data in the data store.
     * @param movement The Movement entity object containing modified movement data.
     * @throws UpdateException Thrown when any error or exception occurs during 
     * update.
     */
    public void updateMovement (Movement  movement) throws UpdateException;
    /**
     * This method removes an account from the data store.
     * @param account The Account entity object to be removed.
     * @throws DeleteException Thrown when any error or exception occurs during 
     * deletion.
     */
    public void removeAccount(Account account) throws DeleteException;
    /**
     * This method removes a customer from the data store.
     * @param customer The Customer entity object to be removed.
     * @throws DeleteException Thrown when any error or exception occurs during 
     * deletion.
     */
    public void removeCustomer(Customer customer) throws DeleteException;
    /**
     * This method removes a movement from the data store.
     * @param movement The Movement entity object to be removed.
     * @throws DeleteException Thrown when any error or exception occurs during 
     * deletion.
     */
    public void removeMovement (Movement  movement) throws DeleteException;
    /**
     * This method obtains an account from the data store using its id.
     * @param id The id for the account to be got.
     * @return An Account entity object containing account data.
     * @throws ReadException Thrown when any error or exception occurs during 
     * reading.
     */
    public Account findAccount(Long id) throws ReadException;
    /**
     * This method gets a list with all accounts in the data store. 
     * @return A List of Account entity objects.
     * @throws ReadException Thrown when any error or exception occurs during 
     * reading.
     */
    public List<Account> findAllAccounts() throws ReadException;
    /**
     * This method gets a list with all accounts for a customer in the data store. 
     * @param idCustomer The customer id 
     * @return A List of Account entity objects.
     * @throws ReadException Thrown when any error or exception occurs during 
     * reading.
     */
    public Set<Account> findAccountsByCustomerId(Long idCustomer) throws ReadException;
    /**
     * This method obtains a customer from the data store using its id.
     * @param id The id for the customer to be got.
     * @return A Customer entity object containing customer data.
     * @throws ReadException Thrown when any error or exception occurs during 
     * reading.
     */
    public Customer findCustomer(Long id) throws ReadException;
    /**
     * This method gets a list with all customers in the data store. 
     * @return A List of Customer entity objects.
     * @throws ReadException Thrown when any error or exception occurs during 
     * reading.
     */
    public List<Customer> findAllCustomers() throws ReadException;
    /**
     * This method obtains a movement from the data store using its id.
     * @param id The id for the movement to be got.
     * @return A Movement entity object containing customer data.
     * @throws ReadException Thrown when any error or exception occurs during 
     * reading.
     */
    public Movement findMovement(Long id) throws ReadException;
    /**
     * This method gets a list with all movements for an account in the data store. 
     * @param idAccount The id for the account 
     * @return A List of Movement entity objects.
     * @throws ReadException Thrown when any error or exception occurs during 
     * reading.
     */
    public List<Movement> findMovementsByAccountId(Long idAccount) throws ReadException;
}
