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
import serverside.entity.Movement;
import serverside.exceptions.CreateException;
import serverside.exceptions.DeleteException;
import serverside.exceptions.ReadException;
import serverside.exceptions.UpdateException;

/**
 *
 * @author javi
 */
@Path("movement")
public class MovementFacadeREST {

    @EJB
    private BankManagerLocal ejb;
    
    private Logger LOGGER=Logger.getLogger(MovementFacadeREST.class.getName());


    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Movement movement) {
        try {
            LOGGER.log(Level.INFO,"Creating movement {0}",movement.getId());
            ejb.createMovement(movement);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(Movement movement) {
        try {
            LOGGER.log(Level.INFO,"Updating movement {0}",movement.getId());
            ejb.updateMovement(movement);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

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

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Movement find(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO,"Reading data for movement {0}",id);
            return ejb.findMovement(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @GET
    @Path("account/{id}")
    @Produces({MediaType.APPLICATION_XML})
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
