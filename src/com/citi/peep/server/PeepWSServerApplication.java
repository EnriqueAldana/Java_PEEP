/**
 * CITI PEEP WS server application.
 * Web service solution for CITI Company.
 * Aternity Mexico Team. Copyright September 2014
 * Autor Jesus Enrique Aldana Sanchez jesus.enrique.aldanasanchez@citi.com
 */
package com.citi.peep.server;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.citi.peep.util.AppEnvironmentVariable;


/**
 * The reusable CITI WS server application.
 */
public class PeepWSServerApplication extends Application  {
	
	private static final Logger logApp = Logger.getLogger( "com.citi.peep.server");
    /**
     * Constructor.
     */
    public PeepWSServerApplication() {
    	FileHandler handlerFile;
		try {
			handlerFile = new FileHandler("CITI_PEEP_WSServer.%u.%g.log",
				    1024 * 1024, 10, true);
			SimpleFormatter sformatter = new SimpleFormatter();
			handlerFile.setFormatter(sformatter);
			logApp.addHandler(handlerFile);
		} catch (SecurityException e) {
			logApp.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}catch ( IOException ex){
			logApp.log(Level.SEVERE, ex.getMessage());
			ex.printStackTrace();
		}
		
    	 // Set basic properties
        setName("RESTful implementation to Pick up Evidence End Point Web Service Server component.");
        setDescription("Web service solution to start and stop the background recorder screen. ");
        setOwner(". Copyright 2015");
        setAuthor("Jesus Enrique Aldana Sanchez jealdana@gmail.com");
        System.out.println("Application component :" +AppEnvironmentVariable.VERSION);
        System.out.println("Environment :" +AppEnvironmentVariable.AMBIENTE);
		logApp.info("Application component :" +AppEnvironmentVariable.VERSION);
		logApp.info("Environment :" +AppEnvironmentVariable.AMBIENTE);;
        logApp.info("RESTful PEEP Web Service Server component.");
        logApp.info("Web service solution . ");
        logApp.info(" Copyright 2015");
        logApp.info("Jesus Enrique Aldana Sanchez jealdana@gmail.com");
    }

    /**
     * Creates a root Router to dispatch call to server resources.
     */
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("/", RootServerResource.class);
        logApp.info("Home : /");
        router.attach("/peep/{startUpId}", PeepServerResource.class);
        logApp.info("Work Context : /peep/{startUpId}");
        // Here add anothers URL for more features
        router.attach("/peep/filesList/", FileListResource.class);
        logApp.info("File List : /peep/filesList/");
        router.attach("/peep/downLoadFile/{fileName}", FileVideoResource.class);
       logApp.info("File Download : /peep/downLoadFile/{fileName}");
        return router;
    }

	

}
