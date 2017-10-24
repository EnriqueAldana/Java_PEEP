package com.citi.peep.server;

import java.io.File;
import java.util.logging.Logger;

import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.citi.peep.common.FileListResourceInterface;
import com.citi.peep.util.AppEnvironmentVariable;


public class FileListResource extends ServerResource implements FileListResourceInterface  {
	private static final Logger logApp = Logger.getLogger( "com.citi.peep.server");

	
	@Override
    protected void doInit() throws ResourceException {
       
       // Just for trace and debug
       logApp.info("File List doInit");
    }
    

	@Override
	   protected Representation get() throws ResourceException {
	     
		   return null;
	   }
	
	@Override
	   protected Representation put(Representation representation)
	           throws ResourceException {
	      

	       return null;
	   }

	@Override
	public String fileList() {
		StringBuffer str = new StringBuffer();
		str.append("<html>");
        str.append("<head><title> Pickup Evidence End Point tool " +
                " Files videos list </title></head>");
        str.append("<body bgcolor=white>");
       str.append("<h2>Pick up the video on backgroung remotely tool " +
                " Files videos list</h2>");
		str.append("<ol>");
	
		String exePath=System.getProperty("user.dir");
		File dir = new File(exePath);
		File[] filesList = dir.listFiles();
		for (File file : filesList) {
		    if (file.isFile()) {
		    	if (file.getName().contains(".mp4")){
		    		str.append( "<li>"+"<a href='http://"+ AppEnvironmentVariable.nameServer + ":" + AppEnvironmentVariable.PortWebService +"/peep/downLoadFile/"+file.getName()+ "'>"+file.getName()+"</a>"+"</li>");    		
		    	}   	
		    }
		}
		str.append("</ol>");
		str.append("<br> </br>");

        str.append( "<a href='http://"+AppEnvironmentVariable.nameServer+":"+AppEnvironmentVariable.PortWebService +"/"+ "'>"+"Back to Main Menu"+"</a>");

        str.append("</body>");
        str.append("</html>");
        //return stringBuilder.toString();
		return str.toString();
	}

	
	

}
