package com.citi.peep.common;

import org.restlet.resource.Get;

public interface FileListResourceInterface {
	
	 /**
     * Represents the PEEP Resource as a simple string .
     * 
     * @return The File list .
     */
    @Get("html")
    public String fileList();

}
