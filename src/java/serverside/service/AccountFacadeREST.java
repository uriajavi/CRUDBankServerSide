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
 * @author Javier Martín Uría
 */
@Path("account")
public class AccountFacadeREST {
    /**
     * EJB object implementing business logic.
     */
    @EJB
    private BankManagerLocal ejb;
    /**
     * Logger for this class.
     */
    private Logger LOGGER=Logger.getLogger(AccountFacadeREST.class.getName());
    /**
     * POST method to create accounts: uses createAccount business logic method.
     * @param account The Account object containing data.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void createAccount(Account account) {
        try {
            LOGGER.log(Level.INFO,"Creating account {0}",account.getId());
            ejb.createAccount(account);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
    /**
     * PUT method to modify accounts: uses updateAccount business logic method.
     * @param account The Account object containing data.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void updateAccount(Account account) {
        try {
            LOGGER.log(Level.INFO,"Updating account {0}",account.getId());
            ejb.updateAccount(account);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
    /**
     * DELETE method to remove accounts: uses removeAccount business logic method.
     * @param id The id for the account to be deleted.
     */
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
    /**
     * GET method for getting an account by its id: it uses the business method
     * findAccount.
     * @param id The account id.
     * @return An Account object.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Account find(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO,"Reading data for account {0}",id);
            return ejb.findAccount(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
    /**
     * GET method to get all accounts data: it uses business method findAllAccounts.
     * @return A list of Account objects.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Account> findAll() {
        try {
            LOGGER.log(Level.INFO,"Reading data for all accounts");
            return ejb.findAllAccounts();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
    /**
     * GET method to get accounts for a given customer: it uses the business method 
     * findAccountsByCustomerId.
     * @param id The customer id.
     * @return A set of Account objects.
     */
    @GET
    @Path("customer/{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
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
