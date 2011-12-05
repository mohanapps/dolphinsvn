package com.svn.client.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.svn.properties.constants.SVNManagerConstants;
import com.svn.properties.log.SVNManagerLogger;

public class FileUtility {
	
	public static Logger logger = Logger.getLogger(FileUtility.class);
	
	public static boolean copyFile(String source,String destination)
	{
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering copyFile method","source",source,"destination",destination);
		boolean retFlag = true;
		InputStream fileInputStream = null;
		OutputStream fileOutputStream = null;
		File sourceFile = new File(source);
		File destinationFile = new File(destination); 
		try {
			fileInputStream = new FileInputStream(sourceFile);
			fileOutputStream = new FileOutputStream(destinationFile);
			int writeValue;
			while( (writeValue = fileInputStream.read()) != -1){
				 fileOutputStream.write(writeValue);
			}
			
			if(destinationFile.length() != sourceFile.length()){
				retFlag = false;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			SVNManagerLogger.error(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering copyFile Exception","Exception is",e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			SVNManagerLogger.error(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering copyFile Exception","Exception is",e.getMessage());
			e.printStackTrace();
		}finally{
			
				try {
					if(fileInputStream != null){
					fileInputStream.close();
					}if(fileOutputStream != null){
						fileOutputStream.close();
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					SVNManagerLogger.error(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering copyFile finally block Exception","Exception is",e.getMessage());
					e.printStackTrace();
				}
		
			
		}
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Exiting copyFile method","Copy Flag",retFlag);
		return retFlag;
	}
	
	public static File[] createFilesList(List<String> list){
		File[] fileList = new File[list.size()];
		for(int i=0;i<list.size();i++){
			fileList[i] = new File(list.get(i));
			
		}
		
		return fileList;
	}

}
