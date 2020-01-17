/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverside.exceptions;

/**
 * Exception thrown if any error hapens during data creation.
 * @author jmarturi
 */
public class CreateException extends Exception {

    /**
     * Creates a new instance of <code>CreateException</code> without detail
     * message.
     */
    public CreateException() {
    }

    /**
     * Constructs an instance of <code>CreateException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public CreateException(String msg) {
        super(msg);
    }
}
