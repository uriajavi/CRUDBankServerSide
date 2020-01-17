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
 *
 * @author javi
 */
@Stateless
public class EJBBankManager implements BankManagerLocal{

    @PersistenceContext(unitName = "DataModelExamplePU")
    private EntityManager em;

    @Override
    public void createAccount (Account  account) throws CreateException{
        try{
            em.persist(account);
        }catch(Exception e){
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void createCustomer (Customer  customer)throws CreateException {
        try{
            em.persist(customer);
        }catch(Exception e){
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void createMovement(Movement movement) throws CreateException {
        try{
            em.persist(movement);
        }catch(Exception e){
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void updateAccount(Account account) throws UpdateException {
        try{
            em.merge(account);
            em.flush();
        }catch(Exception e){
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void updateCustomer(Customer customer) throws UpdateException{
        try{
            em.merge(customer);
            em.flush();
        }catch(Exception e){
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void updateMovement(Movement movement) throws UpdateException {
        try{
            em.merge(movement);
            em.flush();
        }catch(Exception e){
            throw new UpdateException(e.getMessage());
        }
    }
    
    @Override
    public void removeAccount(Account account) throws DeleteException {
        try{
            em.remove(em.merge(account));
        }catch(Exception e){
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public void removeCustomer(Customer customer) throws DeleteException{
        try{
            em.remove(em.merge(customer));
        }catch(Exception e){
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public void removeMovement(Movement movement) throws DeleteException {
        try{
            em.remove(em.merge(movement));
        }catch(Exception e){
            throw new DeleteException(e.getMessage());
        }
    }

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





