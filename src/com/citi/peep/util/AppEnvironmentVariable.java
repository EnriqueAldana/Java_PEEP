package com.citi.peep.util;

import java.io.File;

/**
 * 
 */

/**
 * @author JEAS
 *
 */
public final class AppEnvironmentVariable {
	
	
		public static  String VERSION="Version 1.0";
		public static  String AMBIENTE="Production";
		public static  String IpWebService="localhost";
		public static  String PortWebService="8111";
		public static  String LogWebServicePath;
		public static String videoTimeSec="60";
		public static String nameServer="localhost";
		static {
			String currentDirectory;
			File file = new File(".");
			currentDirectory = file.getAbsolutePath();
			System.out.println("Current working directory : "+currentDirectory);
			
			String LogWebServicePath=currentDirectory+"//Log";
		}
		

}
