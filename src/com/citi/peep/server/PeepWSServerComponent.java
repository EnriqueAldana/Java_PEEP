/**
 * Loyal Customer WS server application.
 * Web service solution for 7 Eleven Company.
 * Shauro Consulting licensed for 7 Eleven Company. Copyright September 2014
 * Autor Jesus Enrique Aldana Sanchez jealdana@gmail.com
 */
package com.citi.peep.server;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;




import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Protocol;

import com.citi.peep.util.AppEnvironmentVariable;
import com.citi.peep.util.Utils;
/**
 * RESTlet component containing the Citi Pick up Evidence end point WS server application.
 */
public class PeepWSServerComponent extends Component {
	private static final Logger logApp = Logger.getLogger(
	    Thread.currentThread().getStackTrace()[0].getClassName() );
	
	
    /**
     * Launches the CITI Aternity Pick up Evidence End Point WS server component.
     * 
     * @param args
     *            The arguments.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
    	try{
    		PeepWSServerComponent.initLogger();
    		logApp.info("Starting PEEP WS server component");
    		new PeepWSServerComponent().start();
    	}catch(Exception exp){
    			System.out.println("There aren a exeption on Application and will finishing");
    			System.out.println(exp.getMessage());
    			logApp.log(Level.SEVERE, exp.getMessage());
    			//logger.log( Level.SEVERE, exp.getMessage() );
    			exp.printStackTrace();
    	}
    }


	/**
     * Constructor.
     * 
     * @throws Exception
     */
    public PeepWSServerComponent() throws Exception {
    	loadAppEnvironmentVariable();
    	Server server = new Server(new Context(), Protocol.HTTP, Integer.parseInt(AppEnvironmentVariable.PortWebService));
        server.setAddress(AppEnvironmentVariable.IpWebService);
    	getServers().add(server);
        // Attach the application to the default virtual host
    	PeepWSServerApplication application=new PeepWSServerApplication();
        getDefaultHost().attachDefault(application);
  
    }
    
    private  void loadAppEnvironmentVariable()  {
    	Properties propiedades = new Properties();
	    InputStream entrada = null;
	    Utils util= new Utils();
	    String AppPath=util.getAppPath()+"CITI_PEEP_WSConfig.properties";
	    
	    try {
	        entrada = new FileInputStream(AppPath);
	        // Loading properties file
	        propiedades.load(entrada);
	        // get properties and set to AppEnrironment object
	        AppEnvironmentVariable.IpWebService=util.getPropertie("IpWebService", "localhost", propiedades);
	        AppEnvironmentVariable.PortWebService=util.getPropertie("PortWebService", "8111", propiedades);
	        AppEnvironmentVariable.videoTimeSec=util.getPropertie("videoTimeSec", "300", propiedades);
	        System.out.println("IP Web Services variable "+AppEnvironmentVariable.IpWebService);
	        System.out.println("PORT Web Services variable "+AppEnvironmentVariable.PortWebService);
	         // Close the properties file
	        if (entrada != null) {
	            try {
	                entrada.close();
	            } catch (IOException e) {
	            	logApp.log(Level.SEVERE, e.getMessage());
	                e.printStackTrace();
	            }
	        }
	    } catch (IOException ex) {
	    	System.out.println("There arent Config.properties file in " +AppPath);
	    	logApp.log(Level.SEVERE, "There arent Config.properties file in " +AppPath);
	    	System.out.println(ex.getMessage());
	        
	        System.out.println("Loading default values...");
	       // logger.log( Level.SEVERE, ex.getMessage() );
	        logApp.log(Level.SEVERE, ex.getMessage());
	        ex.printStackTrace();
	    } finally {
	        if (entrada != null) {
	            try {
	                entrada.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
    }
    
    private static void initLogger() throws SecurityException, IOException {
		FileHandler handlerFile = new FileHandler("CITI_PEEP_WSServer.%u.%g.log",
			    1024 * 1024, 10, true);
		SimpleFormatter sformatter = new SimpleFormatter();
		handlerFile.setFormatter(sformatter);
		logApp.getLogger("com.citi.peep.server");
		logApp.addHandler(handlerFile);
		
		
		/*Code 	Meaning
		/ 	The file name separator of the system. Typically either \ or / .
		%t 	The temp directory of the system.
		%h 	The user home directory of the system.
		%g 	The generation number that distinguishes the rotated log files from each other.
		%u 	A unique number to avoid naming conflicts.
		%% 	A single percent sign, in case you want to use that in your file name.*/
		
	}
    
   
}
