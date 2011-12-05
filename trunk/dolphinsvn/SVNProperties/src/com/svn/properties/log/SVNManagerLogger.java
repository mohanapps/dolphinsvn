package com.svn.properties.log;

import org.apache.log4j.Logger;

import com.svn.properties.constants.SVNManagerConstants;

public class SVNManagerLogger {
	
	public static void debug(Logger logger,String applicationName,String moduleName,String message,Object... objects){
		logger.debug(buildLogMessage(applicationName, moduleName, message, objects));
	}
	
	public static void info(Logger logger,String applicationName,String moduleName,String message,Object... objects){
		logger.info(buildLogMessage(applicationName, moduleName, message, objects));
	}
	
	public static void warn(Logger logger,String applicationName,String moduleName,String message,Object... objects){
		logger.warn(buildLogMessage(applicationName, moduleName, message, objects));
	}
	
	public static void error(Logger logger,String applicationName,String moduleName,String message,Object... objects){
		logger.error(buildLogMessage(applicationName, moduleName, message, objects));
	}
	
	public static void fatal(Logger logger,String applicationName,String moduleName,String message,Object... objects){
		logger.fatal(buildLogMessage(applicationName, moduleName, message, objects));
	}
	
	private static Object buildLogMessage(String applicationName,String moduleName,String message,Object... objects){
		StringBuffer buffer = new StringBuffer(applicationName);
		buffer.append(SVNManagerConstants.LOGDELIMTER)
		.append(moduleName)
		.append(SVNManagerConstants.LOGDELIMTER)
		.append(message);
		for(Object object : objects){
			buffer.append(SVNManagerConstants.LOGDELIMTER);
			buffer.append(object);
		}
		return buffer.toString();
	}
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(SVNManagerLogger.class);
		debug(logger,"SVNManager","SVNClientInterface","Entering debug method","input1","input2","input3");
	}

}
