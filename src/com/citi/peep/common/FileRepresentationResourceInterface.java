/**
 * 
 */
package com.citi.peep.common;

import java.io.IOException;

import org.restlet.representation.FileRepresentation;

/**
 * @author ja47074
 *
 */
public interface FileRepresentationResourceInterface {
	
	
	public FileRepresentation getRepresent() throws IOException;

}
