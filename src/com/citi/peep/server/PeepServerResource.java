/**
 * CITI PEEP WS server application.
 * Web service solution for CITI Company.
 * TATA Consulting licensed for CITI Company. Copyright October 2015
 * Autor Jesus Enrique Aldana Sanchez jesus.enrique.aldanasanchez@citi.com
 */
package com.citi.peep.server;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.citi.peep.capture.CaptureScreenToFile;
import com.citi.peep.common.PeepServerResourceInterface;
import com.citi.peep.util.AppEnvironmentVariable;

/**
 * Implementation of the resource containing the PEEP WS server application..
 */
public class PeepServerResource extends ServerResource implements PeepServerResourceInterface {
	private static final Logger logApp = Logger.getLogger( "com.citi.peep.server");
	
    private String startUpId;
    private static boolean startRecorder=false;
    CaptureScreenToFile captureScreenToFile;
    CaptureScreenToFile videoEncoder;
    int segundosVideo=300;
    public void PeepServerResource(){
    	segundosVideo=Integer.parseInt(AppEnvironmentVariable.videoTimeSec);
    }
    
    private String runRecorder()throws RuntimeException {
    	String ret="";
    
    	if (startRecorder){
    		ret="Recorder is start up";
    	}else{
			 
    	startRecorder=true;
			  do{
				  try
				    {
					  
						  String outFile="";
						  SimpleDateFormat dt = new SimpleDateFormat("MM-dd-yyyy-hh-mm-ss");
						  Date date= new Date();
					      outFile = dt.format(date)+".mp4";
					      //outFile = "output.mp4";
					      
						  videoEncoder = new CaptureScreenToFile(outFile);
					      
					      int index = 0;
					      double val= videoEncoder.getFrameRate().getDouble();
					      int vecesXSeg=(int) (1000/val);
					      for (int i=1 ; i< segundosVideo;i++){
					    	  index = 0;
					    	  while (index <videoEncoder.getFps())
						      {
						        //System.out.println("encoded image" + String.valueOf(index));
						        videoEncoder.encodeImage(videoEncoder.takeSingleSnapshot());
						
						        try
						        {
						          // sleep for framerate milliseconds
						          Thread.sleep((long) (vecesXSeg));
						        }
						        catch (InterruptedException e)
						        {
						          e.printStackTrace(System.out);
						        }
						        index++;
						      }  
					      }
						      
					      videoEncoder.closeStreams();
					    
				    }catch (RuntimeException e)
					    {
				    	startRecorder=false;
				    	logApp.warning("we can't get permission to capture the screen");
				    	logApp.warning(e.getMessage());
				    	throw new RuntimeException(e.getMessage());
					    } 
			  }while(startRecorder);
			  ret="Recorder is shut down";
    	}
		return ret;
    	
    }
    
    
    /**
     * Retrieve the Start up or Shutdown Id identifier based on the URI path variable
     * "startUpId" declared in the URI template attached to the application
     * router.
     */
    @Override
    protected void doInit() throws ResourceException {
        this.startUpId = (String) getRequestAttributes().get(
                "startUpId");
      
        	
       // Just for trace and debug
       logApp.info("Get startUpId : " +this.startUpId);
    }
    

   @SuppressWarnings("finally")
private String getStartUpShutDown(String startUpId){
	   // Here startup or shutdown recorder
	   String ret="";
	   if ("On".equals(startUpId)){
		   try {
			   ret= runRecorder();
			     
		   		}catch (RuntimeException e){
		   				
					   ret="Has been a trouble when try start up recorder core. Check log file.";
		   		}
   
	   } else if ("Off".equals(startUpId)) {
		
		   try{
			   startRecorder=false;
			   ret="Recorder was shutdown";
		   }catch (RuntimeException e){
			   ret="Has been a trouble with try shutdown recorder core. Check log file.";
		   }
		   
	   } else{
		   ret="You should include On or Off parameter"; 
	   }
	   	
	   	
		   return ret;
	   
       
   }

   @Override
   protected Representation get() throws ResourceException {
       // Create a JSON object structure similar to a map
	 /*  JSONObject pointsAwaredObj = new JSONObject();

       try {
       	pointsAwaredObj.put("ID", ticketId);
       	pointsAwaredObj.put("ValorRetorno", getPointsAwared(ticketId));
           
       } catch (JSONException e) {
    	   logApp.log(Level.SEVERE, e.getMessage());
           throw new ResourceException(e);
       }

       return new JsonRepresentation(pointsAwaredObj);*/
	   return null;
   }

   @Override
   protected Representation put(Representation representation)
           throws ResourceException {
      

       return null;
   }


@Override
public Representation startUpShutDown() {
	
	
	String message=this.getStartUpShutDown(startUpId);
	StringBuffer str = new StringBuffer();
	str.append("<html>");
    str.append("<head><title> PEEP tool " +
            " Pick up Evidence End Point </title></head>");
    str.append("<body bgcolor=white>");
    
    str.append("<h2>Pick up the video on backgroung remotely tool " +
            " Pick up Evidence End Point </h2>");
	

    str.append("<br> </br>");
    str.append("<p>Start up / Down status</p>" );
    
    str.append("<p>"+message+"</p>" );
    str.append("<br> </br>");
    str.append( "<a href='http://"+AppEnvironmentVariable.nameServer+":"+AppEnvironmentVariable.PortWebService +"/"+ "'>"+"Back to Main Menu"+"</a>");
   
    str.append(("</body>"));
    str.append(("</html>"));

	

    return new StringRepresentation(str.toString(),MediaType.TEXT_HTML);
    
}




}
