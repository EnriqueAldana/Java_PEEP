/**
 * CITI WS server application.
 * Web service solution for CITI Company.
 * TATA Consulting licensed for CITI Company. Copyright October 2015
 * Author Jesus Enrique Aldana Sanchez (JA47074) jesus.enrique.aldanasanchez@citi.com
 */
package com.citi.peep.common;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;



/**
 * PEEP Start up - Shutdown Resource.
 */
public interface PeepServerResourceInterface {

    /**
     * Represents the PEEP Resource as a simple string .
     * 
     * @return The Start up ot Shut down message .
     */
    @Get("txt")
    public Representation startUpShutDown();

}
