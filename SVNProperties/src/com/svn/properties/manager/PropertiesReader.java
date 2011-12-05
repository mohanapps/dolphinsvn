package com.svn.properties.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.svn.properties.constants.SVNManagerConstants;
import com.svn.properties.log.SVNManagerLogger;

public class PropertiesReader {
	
	
	
	private static Logger logger = Logger.getLogger(PropertiesReader.class);
	
	private static Map<String,String> hashMap;
	
	private static Properties getPropertyFromIS(String fileName){
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNPROPERTIES,"Entering initProperty method","fileName",fileName);
		InputStream inputStream = null;
	
		Properties prop = new Properties();
		try {
			inputStream = new  FileInputStream(new File(fileName));
			prop.load(inputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			SVNManagerLogger.error(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNPROPERTIES,"Exiting initProperty method Exception:",e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			SVNManagerLogger.error(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNPROPERTIES,"Exiting initProperty method Exception:",e.getMessage());
			e.printStackTrace();
		}finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					SVNManagerLogger.error(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNPROPERTIES,"Exiting initProperty method finally block Exception:",e.getMessage());
					e.printStackTrace();
				}
			}
		}
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNPROPERTIES,"Exiting initProperty method");
		return prop;
		
	}
	
	public static String getProperty(String fileName,String key)
	{
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNPROPERTIES,"Entering getProperty method","fileName",fileName,"Key",key);
		String value = "";
		if(hashMap == null){
			hashMap = new HashMap<String, String>();
		}
		if(!hashMap.containsKey(key)){
			Properties properties = getPropertyFromIS(fileName);
			value = properties.getProperty(key);
			hashMap.put(key,value);
		}else{
			value = hashMap.get(key);
		}
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNPROPERTIES,"Exiting getProperty method","value",value);
		return value;
	}
	
	
	

}
