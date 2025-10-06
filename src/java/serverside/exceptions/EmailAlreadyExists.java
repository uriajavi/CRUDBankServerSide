/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverside.exceptions;

/**
 * This exception is thrown when email already exists in database.
 * @author javi
 */
public class EmailAlreadyExists extends Exception {

    /**
     * Creates a new instance of <code>EmailAlreadyExists</code> without detail
     * message.
     */
    public EmailAlreadyExists() {
    }

    /**
     * Constructs an instance of <code>EmailAlreadyExists</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmailAlreadyExists(String msg) {
        super(msg);
    }
}
