package com.common.file.reader;

import java.io.File;

import org.apache.log4j.Logger;

import com.common.file.constant.CommonFileReaderConstants;
import com.common.file.enums.FileExtensionEnum;
import com.svn.properties.log.SVNManagerLogger;

public class CommonFileReaderFactory {
	
	private static Logger logger = Logger.getLogger(CommonFileReaderFactory.class);
	
	public static ICommonFileReader createCommonFileReader(File inputFile){
		SVNManagerLogger.debug(logger, CommonFileReaderConstants.APPLICATION_NAME,CommonFileReaderConstants.Module_Name,"Entering createCommonFileReader method","File",inputFile.getAbsolutePath());
		ICommonFileReader commonFileReader = null;
		if(inputFile.exists()){
			String fileName = inputFile.getName();
			String fileExt = fileName.substring(fileName.indexOf('.')+1,fileName.length()).trim();
			
			FileExtensionEnum extensionEnum = FileExtensionEnum.getEnum(fileExt);
			switch(extensionEnum){
				case TXT:
					commonFileReader = new TextFileReader(inputFile);
					break;
				case XLS:
					commonFileReader = new XlsFileReader(inputFile);
					break;
				case XML:
					commonFileReader = new XMLFileReader(inputFile);
			}
		}
		SVNManagerLogger.debug(logger, CommonFileReaderConstants.APPLICATION_NAME,CommonFileReaderConstants.Module_Name,"Exiting createCommonFileReader method","ICommonFileReader",commonFileReader.getClass().toString());
		return commonFileReader;
	}

}
