/**
 * Loyal Customer WS server application.
 * Web service solution for 7 Eleven Company.
 * Shauro Consulting licensed for 7 Eleven Company. Copyright September 2014
 * Autor Jesus Enrique Aldana Sanchez jealdana@gmail.com
 */
package com.citi.peep.server;

import org.restlet.Request;
import org.restlet.data.MediaType;
import org.restlet.data.Reference;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.citi.peep.common.RootResourceInterface;
import com.citi.peep.util.AppEnvironmentVariable;

/**
 * Root resource implementation.
 */
public class RootServerResource extends ServerResource implements RootResourceInterface {

	
	/**
     * Retrieve the context path identifier based on the URI 
     * 
     */
    @Override
    protected void doInit() throws ResourceException {
    	
    	Reference ref =  getRequest().getCurrent().getRootRef();
    	String identifyServerName=ref.getIdentifier();

    	try{
    		String[] split_identifyServerName = identifyServerName.split("\\:");
        	identifyServerName=split_identifyServerName[1].substring(2);
        	AppEnvironmentVariable.nameServer = identifyServerName ;
    	}catch (IndexOutOfBoundsException e){
    		
    	}
    	    

    }
    public Representation represent() {
    	
   
    	
    	StringBuffer str = new StringBuffer();
		str.append("<html>");
        str.append("<head><title>PEEP tool " +
                " Pick up Evidence End Point </title></head>");
        str.append("<body bgcolor=white>");
        str.append("<h2>Get video on background remotely " +
                " Pick up Evidence End Point </h2>");
		str.append("<ol>");
        
        str.append("<p>" +getApplication().getDescription()+"</p>");
        str.append("<p>" +getApplication().getOwner() +"</p>");
        str.append(	"<p>" +getApplication().getAuthor()+"</p>");
        str.append("</ol>");

        str.append("<br> </br>");
        str.append("<p>Main Menu</p>" );
        str.append("<ol>");
        str.append("<li>"+ "<a href='http://"+AppEnvironmentVariable.nameServer+":"+AppEnvironmentVariable.PortWebService +"/peep/On"+ "'>"+" Start up recorder in background. Check status with a second click"+"</a>" + "</li>");
        str.append("<li>"+ "<a href='http://"+AppEnvironmentVariable.nameServer+":"+AppEnvironmentVariable.PortWebService +"/peep/Off"+ "'>"+" Stop recorder in background"+"</a>" + "</li>");
        str.append("<li>"+ "<a href='http://"+AppEnvironmentVariable.nameServer+":"+AppEnvironmentVariable.PortWebService +"/peep/filesList/"+ "'>"+" File List"+"</a>" + "</li>");
        str.append("</ol>");
        str.append(("</body>"));
        str.append(("</html>"));

    	
 
        return new StringRepresentation(str.toString(),MediaType.TEXT_HTML);
    }

}
