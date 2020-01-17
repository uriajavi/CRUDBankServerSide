/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverside.service;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import serverside.entity.Account;
import serverside.exceptions.CreateException;
import javax.ws.rs.InternalServerErrorException;
import serverside.exceptions.DeleteException;
import serverside.exceptions.ReadException;
import serverside.exceptions.UpdateException;
/**
 * RESTful service for Accounts.
 * @author javi
 */
@Path("account")
public class AccountFacadeREST {

    @EJB
    private BankManagerLocal ejb;
    
    private Logger LOGGER=Logger.getLogger(AccountFacadeREST.class.getName());
    
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void createAccount(Account account) {
        try {
            LOGGER.log(Level.INFO,"Creating account {0}",account.getId());
            ejb.createAccount(account);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void updateAccount(Account account) {
        try {
            LOGGER.log(Level.INFO,"Updating account {0}",account.getId());
            ejb.updateAccount(account);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @DELETE
    @Path("{id}")
    public void removeAccount(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO,"Deleting account {0}",id);
            ejb.removeAccount(ejb.findAccount(id));
        } catch (ReadException|DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Account find(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO,"Reading data for account {0}",id);
            return ejb.findAccount(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Account> findAll() {
        try {
            LOGGER.log(Level.INFO,"Reading data for all accounts");
            return ejb.findAllAccounts();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
    @GET
    @Path("customer/{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Set<Account> findAccountsByCustomerId(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO,"Reading accounts data for customer {0}",id);
            return ejb.findAccountsByCustomerId(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

}
