/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverside.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import serverside.entity.Customer;
import serverside.exceptions.CreateException;
import serverside.exceptions.DeleteException;
import serverside.exceptions.EmailAlreadyExists;
import serverside.exceptions.LoginException;
import serverside.exceptions.ReadException;
import serverside.exceptions.UpdateException;

/**
 * RESTful service for Customers.
 * @author Javier Martín Uría
 */
@Path("customer")
public class CustomerFacadeREST {
    /**
     * EJB object implementing business logic.
     */
    @EJB
    private BankManagerLocal ejb;
    /**
     * Logger for this class.
     */
    private Logger LOGGER=Logger.getLogger(CustomerFacadeREST.class.getName());
    /**
     * POST method to create customers: uses createCustomer business logic method.
     * @param entity The Customer object containing data.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void create(Customer entity) {
        try {
            LOGGER.log(Level.INFO,"Creating customer for email {0}",entity.getEmail());
            ejb.createCustomer(entity);
        } catch (EmailAlreadyExists ex) {
            //If email already exists respond with a HTTP 403 error code
            LOGGER.severe(ex.getMessage());
            throw new ForbiddenException(ex.getMessage());        

        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
    /**
     * PUT method to modify customers: uses updateCustomer business logic method.
     * @param entity The Customer object containing data.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void edit(Customer entity) {
        try {
            LOGGER.log(Level.INFO,"Updating customer {0}",entity.getId());
            ejb.updateCustomer(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
    /**
     * DELETE method to remove customers: uses removeCustomer business logic method.
     * @param id The id for the customer to be deleted.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try{
            LOGGER.log(Level.INFO,"Deleting customer {0}",id);
            ejb.removeCustomer(ejb.findCustomer(id));
        } catch (ReadException|DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
                
    }
    /**
     * GET method for getting a customer by its id: it uses the business method
     * findCustomer.
     * @param id The customer id.
     * @return A Customer object.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Customer find(@PathParam("id") Long id) {
        try{
            LOGGER.log(Level.INFO,"Reading data for customer {0}",id);
            return ejb.findCustomer(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
        
    }
    /**
     * GET method for getting a customer by its email and password.
     * @param email The customer email. 
     * @param password The customer password.
     * @return A Customer object.
     */
    @GET
    @Path("/sigin/{email}/{password}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Customer findCustomerByEmailPassword(@PathParam("email") String email,
                                                @PathParam("password") String password) 
    {
        try{
            LOGGER.log(Level.INFO,"Signing in data customer {0}",email);
            return ejb.findCustomerByEmailPassword(email,password);
        } catch (LoginException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotAuthorizedException(ex.getMessage());        
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
        
    }
    /**
     * GET method to get all customers data: it uses business method findAllCustomers.
     * @return A list of Customer objects.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Customer> findAll() {
        try{
            LOGGER.log(Level.INFO,"Reading data for all customers.");
            return ejb.findAllCustomers();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
        
    }
}
