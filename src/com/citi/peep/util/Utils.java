package com.citi.peep.util;
import java.util.Properties;

/**
 * 
 */

/**
 * @author JEAS
 *
 */
public  final class Utils {
	
	
	public  String getAppPath(){
		String absolute = getClass().getProtectionDomain().getCodeSource().getLocation().toExternalForm();
		absolute = absolute.substring(0, absolute.length() - 1);
		absolute = absolute.substring(0, absolute.lastIndexOf("/") + 1);
		String configPath = absolute ;
				//+ "config/file.properties";
		String os = System.getProperty("os.name");
		if (os.indexOf("Windows") != -1) {
		    configPath = configPath.replace("/", "\\\\");
		    if (configPath.indexOf("file:\\\\") != -1) {
		        configPath = configPath.replace("file:\\\\", "");
		    }
		} else if (configPath.indexOf("file:") != -1) {
		    configPath = configPath.replace("file:", "");
		}
		return configPath;
	}
	
	public String getPropertie(String name, String DefaultValue,Properties propertiesFile){
		String retValue="";
		if (null==propertiesFile.getProperty(name)) {
			retValue=DefaultValue;
		}else{
			retValue=propertiesFile.getProperty(name);
		}
				
		return retValue;
	}

}
