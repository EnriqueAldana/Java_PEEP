/**
 * 
 */
package com.citi.peep.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;





import java.util.logging.Logger;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.FileRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.citi.peep.common.FileRepresentationResourceInterface;
import com.citi.peep.util.FileEntity;

/**
 * @author ja47074
 *
 */



public class FileVideoResource extends ServerResource implements FileRepresentationResourceInterface  {
private static final Logger logApp = Logger.getLogger( "com.citi.peep.server");
	
    private String fileName;
    /**
     * Retrieve the file name  identifier based on the URI path variable
     * "fileName" declared in the URI template attached to the application
     * router.
     */
    @Override
    protected void doInit() throws ResourceException {
        this.fileName = (String) getRequestAttributes().get(
                "fileName");
       
       // Just for trace and debug
       logApp.info("Get file name : " +this.fileName);
    }

  @Get
  public FileRepresentation getRepresent() throws IOException{

	  FileRepresentation resp =null;
	 
    try{
    	String exePath=System.getProperty("user.dir");
    	File dir = new File(exePath);
		File[] filesList = dir.listFiles();
		for (File file : filesList) {
		    if (file.isFile()) {
		    	if(null!=this.fileName){
		    		if(this.fileName.equals(file.getName())){
		    			String absolutPath= file.getAbsolutePath();
		    			// file:///C:\Users\ja47074\workspace\CITI_PEEP\.\10-27-2015-11-16-39.mp410-27-2015-11-16-39.mp4.mp4
		    			absolutPath="file:///"+absolutPath;
		    			resp =new FileRepresentation(absolutPath,MediaType.VIDEO_MP4);
		    			logApp.info("File representation called : " +absolutPath +" to downloading");		
		    		}
		    		
		    	}
		    }
		}


      return resp;

      

    }catch (IllegalArgumentException ie ){
    	getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
    }catch	(SecurityException s) {
    	getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
    }

    return null;

  }




} 