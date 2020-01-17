/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverside.service;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;
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
 * Local interface for BankManager EJB.
 * @author jmarturi
 */
@Local
public interface BankManagerLocal {
    public void createAccount (Account  account) throws CreateException;
    public void createCustomer (Customer  customer) throws CreateException;
    public void createMovement (Movement  movement) throws CreateException;
    public void updateAccount(Account account) throws UpdateException;
    public void updateCustomer(Customer customer)throws UpdateException;
    public void updateMovement (Movement  movement) throws UpdateException;
    public void removeAccount(Account account) throws DeleteException;
    public void removeCustomer(Customer customer) throws DeleteException;
    public void removeMovement (Movement  movement) throws DeleteException;
    public Account findAccount(Long id) throws ReadException;
    public List<Account> findAllAccounts() throws ReadException;
    public Set<Account> findAccountsByCustomerId(Long idCustomer) throws ReadException;
    public Customer findCustomer(Long id) throws ReadException;
    public List<Customer> findAllCustomers() throws ReadException;
    public Movement findMovement(Long id) throws ReadException;
    public List<Movement> findMovementsByAccountId(Long idAccount) throws ReadException;
}
