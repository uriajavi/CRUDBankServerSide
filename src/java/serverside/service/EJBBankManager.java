/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverside.service;

import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import serverside.entity.Account;
import serverside.entity.Customer;
import serverside.entity.Movement;
import serverside.exceptions.CreateException;
import serverside.exceptions.DeleteException;
import serverside.exceptions.ReadException;
import serverside.exceptions.UpdateException;

/**
 * This is the stateless EJB that implements the BankManagerLocal interface for Bank 
 * Management web service application. 
 * @author Javier Martín Uría
 */
@Stateless
public class EJBBankManager implements BankManagerLocal{
    /**
     * EntityManager for DataModelExamplePU persistence unit.
     */
    @PersistenceContext(unitName = "DataModelExamplePU")
    private EntityManager em;
    /**
     * This method creates a new account in the data store.
     * @param account The Account entity object containing new account data.
     * @throws CreateException Thrown when any error or exception occurs during 
     * creation.
     */
    @Override
    public void createAccount (Account  account) throws CreateException{
        try{
            em.persist(account);
        }catch(Exception e){
            throw new CreateException(e.getMessage());
        }
    }
    /**
     * This method creates a new customer in the data store.
     * @param customer The Customer entity object containing new customer data.
     * @throws CreateException Thrown when any error or exception occurs during 
     * creation.
     */
     @Override
    public void createCustomer (Customer  customer)throws CreateException {
        try{
            em.persist(customer);
        }catch(Exception e){
            throw new CreateException(e.getMessage());
        }
    }
    /**
     * This method creates a new movement for an account in the data store.
     * @param movement The Movement entity object containing new movement data.
     * @throws CreateException Thrown when any error or exception occurs during 
     * creation.
     */
    @Override
    public void createMovement(Movement movement) throws CreateException {
        try{
            //if persistence context does not contain account for movement
            //merge it to update account's balance after movement
            if(!em.contains(movement.getAccount()))
                    em.merge(movement.getAccount());
            em.persist(movement);
        }catch(Exception e){
            throw new CreateException(e.getMessage());
        }
    }
    /**
     * This method updates an account data in the data store.
     * @param account The Account entity object containing modified account data.
     * @throws UpdateException Thrown when any error or exception occurs during 
     * update.
     */
    @Override
    public void updateAccount(Account account) throws UpdateException {
        try{
            if(!em.contains(account))
                em.merge(account);
            em.flush();
        }catch(Exception e){
            throw new UpdateException(e.getMessage());
        }
    }
    /**
     * This method updates a customer data in the data store.
     * @param customer The Customer entity object containing modified customer data.
     * @throws UpdateException Thrown when any error or exception occurs during 
     * update.
     */
    @Override
    public void updateCustomer(Customer customer) throws UpdateException{
        try{
            if(!em.contains(customer))
                em.merge(customer);
            em.flush();
        }catch(Exception e){
            throw new UpdateException(e.getMessage());
        }
    }
    /**
     * This method updates a movement data in the data store.
     * @param movement The Movement entity object containing modified movement data.
     * @throws UpdateException Thrown when any error or exception occurs during 
     * update.
     */
    @Override
    public void updateMovement(Movement movement) throws UpdateException {
        try{
            if(!em.contains(movement))
                em.merge(movement);
            em.flush();
        }catch(Exception e){
            throw new UpdateException(e.getMessage());
        }
    }
    /**
     * This method removes an account from the data store.
     * @param account The Account entity object to be removed.
     * @throws DeleteException Thrown when any error or exception occurs during 
     * deletion.
     */
    @Override
    public void removeAccount(Account account) throws DeleteException {
        try{
            em.remove(em.merge(account));
        }catch(Exception e){
            throw new DeleteException(e.getMessage());
        }
    }
    /**
     * This method removes a customer from the data store.
     * @param customer The Customer entity object to be removed.
     * @throws DeleteException Thrown when any error or exception occurs during 
     * deletion.
     */
    @Override
    public void removeCustomer(Customer customer) throws DeleteException{
        try{
            em.remove(em.merge(customer));
        }catch(Exception e){
            throw new DeleteException(e.getMessage());
        }
    }
    /**
     * This method removes a movement from the data store.
     * @param movement The Movement entity object to be removed.
     * @throws DeleteException Thrown when any error or exception occurs during 
     * deletion.
     */
    @Override
    public void removeMovement(Movement movement) throws DeleteException {
        try{
            em.remove(em.merge(movement));
        }catch(Exception e){
            throw new DeleteException(e.getMessage());
        }
    }
    /**
     * This method obtains an account from the data store using its id.
     * @param id The id for the account to be got.
     * @return An Account entity object containing account data.
     * @throws ReadException Thrown when any error or exception occurs during 
     * reading.
     */
    @Override
    public Account findAccount(Long id) throws ReadException {
        Account account;
        try{
            account=em.find(Account.class, id);
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        }
        return account;
    }
    /**
     * This method obtains a customer from the data store using its id.
     * @param id The id for the customer to be got.
     * @return A Customer entity object containing customer data.
     * @throws ReadException Thrown when any error or exception occurs during 
     * reading.
     */
    @Override
    public Customer findCustomer(Long id) throws ReadException{
        Customer customer;
        try{
            customer=em.find(Customer.class, id);
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        }
        return customer;
    }
    /**
     * This method obtains a movement from the data store using its id.
     * @param id The id for the movement to be got.
     * @return A Movement entity object containing customer data.
     * @throws ReadException Thrown when any error or exception occurs during 
     * reading.
     */
    @Override
    public Movement findMovement(Long id) throws ReadException {
        Movement movement;
        try{
            movement=em.find(Movement.class, id);
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        }
        return movement;
    }
    /**
     * This method gets a list with all accounts in the data store. 
     * @return A List of Account entity objects.
     * @throws ReadException Thrown when any error or exception occurs during 
     * reading.
     */
    @Override
    public List<Account> findAllAccounts() throws ReadException{
        List<Account> accounts;
        try{
            accounts=em.createNamedQuery("findAllAccounts").getResultList();
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        }
        return accounts;
    }
    /**
     * This method gets a list with all customers in the data store. 
     * @return A List of Customer entity objects.
     * @throws ReadException Thrown when any error or exception occurs during 
     * reading.
     */
    @Override
    public List<Customer> findAllCustomers() throws ReadException{
        List<Customer> customers;
        try{
            customers=em.createNamedQuery("findAllCustomers").getResultList();
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        }
        return customers;
    }
    /**
     * This method gets a list with all accounts for a customer in the data store. 
     * @param idCustomer The customer id 
     * @return A List of Account entity objects.
     * @throws ReadException Thrown when any error or exception occurs during 
     * reading.
     */
    @Override
    public Set<Account> findAccountsByCustomerId(Long idCustomer) throws ReadException {
        Set<Account> accounts;
        try{
            accounts=em.find(Customer.class, idCustomer).getAccounts();
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        }
        return accounts;
    }
    /**
     * This method gets a list with all movements for an account in the data store. 
     * @param idAccount The id for the account 
     * @return A List of Movement entity objects.
     * @throws ReadException Thrown when any error or exception occurs during 
     * reading.
     */
    @Override
    public List<Movement> findMovementsByAccountId(Long idAccount) throws ReadException {
        List<Movement> movements;
        try{
            movements=em.createNamedQuery("findMovementsByAccount")
                        .setParameter("account", em.find(Account.class, idAccount))
                        .getResultList();
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        }
        return movements;
    }
}





