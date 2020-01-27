/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverside.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import serverside.entity.Customer;
import serverside.exceptions.CreateException;
import serverside.exceptions.DeleteException;
import serverside.exceptions.ReadException;
import serverside.exceptions.UpdateException;

/**
 * RESTful service for Customers.
 * @author javi
 */
@Path("customer")
public class CustomerFacadeREST {

    @EJB
    private BankManagerLocal ejb;
    
    private Logger LOGGER=Logger.getLogger(CustomerFacadeREST.class.getName());


    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Customer entity) {
        try {
            LOGGER.log(Level.INFO,"Creating customer {0}",entity.getId());
            ejb.createCustomer(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(Customer entity) {
        try {
            LOGGER.log(Level.INFO,"Updating customer {0}",entity.getId());
            ejb.updateCustomer(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

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
