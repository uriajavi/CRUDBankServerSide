package serverside.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
import serverside.entity.Movement;
import serverside.exceptions.CreateException;
import serverside.exceptions.DeleteException;
import serverside.exceptions.ReadException;
import serverside.exceptions.UpdateException;

/**
 * RESTful service for Movements.
 * @author Javier Martín Uría
 */
@Path("movement")
public class MovementFacadeREST {
    /**
     * EJB object implementing business logic.
     */
    @EJB
    private BankManagerLocal ejb;
    /**
     * Logger for this class.
     */
    private Logger LOGGER=Logger.getLogger(MovementFacadeREST.class.getName());
    /**
     * POST method to create movements: uses createMovementt business logic method.
     * @param accountId The id of the account that movement belongs to.
     * @param movement The Movement object containing data.
     */
    @POST
    @Path("{accountId}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void create(@PathParam("accountId") Long accountId,Movement movement) {
        try {
            LOGGER.log(Level.INFO,"Creating movement {0}",movement.getId());
            //find account and set it as movement's.
            movement.setAccount(ejb.findAccount(accountId));
            ejb.createMovement(movement);
        } catch (CreateException | ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
    /**
     * PUT method to modify movements: uses updateMovement business logic method.
     * @param movement The Movement object containing data.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void edit(Movement movement) {
        try {
            LOGGER.log(Level.INFO,"Updating movement {0}",movement.getId());
            ejb.updateMovement(movement);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
    /**
     * DELETE method to remove movements: uses removeMovement business logic method.
     * @param id The id for the movement to be deleted.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO,"Deleting movement {0}",id);
            ejb.removeMovement(ejb.findMovement(id));
        } catch (ReadException|DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
    /**
     * GET method for getting an movement by its id: it uses the business method
     * findMovement.
     * @param id The movement id.
     * @return An Movement object.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Movement find(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO,"Reading data for movement {0}",id);
            return ejb.findMovement(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
    /**
     * GET method to get movements for a given account: it uses the business method 
     * findMovementsByAccountId.
     * @param id The account id.
     * @return A list of Movement objects.
     */
    @GET
    @Path("account/{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Movement> findMovementByAccount(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO,"Reading movements for account {0}",id);
            return ejb.findMovementsByAccountId(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    
}
