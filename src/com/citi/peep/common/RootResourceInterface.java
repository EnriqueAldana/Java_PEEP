/**
 * Loyal Customer WS server application.
 * Web service solution for 7 Eleven Company.
 * Shauro Consulting licensed for 7 Eleven Company. Copyright September 2014
 * Autor Jesus Enrique Aldana Sanchez jealdana@gmail.com
 */
package com.citi.peep.common;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;


/**
 * Root resource.
 */
public interface RootResourceInterface {

    /**
     * Represents the application root with a welcome message.
     * 
     * @return The root representation.
     */
    @Get("txt")
    public Representation represent();

}
