package com.common.file.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.common.file.constant.CommonFileReaderConstants;
import com.common.file.dto.FileReadOuputDTO;
import com.svn.properties.log.SVNManagerLogger;

public class TextFileReader implements ICommonFileReader {

	private File inputFile;
	private Logger logger = Logger.getLogger(TextFileReader.class);
	public TextFileReader(File inputFile){
		this.inputFile = inputFile;
	}
	
	public FileReadOuputDTO readFile() {
		// TODO Auto-generated method stub
		SVNManagerLogger.debug(logger, CommonFileReaderConstants.APPLICATION_NAME,CommonFileReaderConstants.Module_Name,"Entering readFile method of TextFileReader","File",inputFile.getAbsolutePath());
		List<String> readLines = new ArrayList<String>();
		FileReadOuputDTO fileReadOuputDTO = new FileReadOuputDTO();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
			String line=null;
			while((line = bufferedReader.readLine()) != null){
				if(!line.equals("")){
				readLines.add(line);
				}
			}
			
			fileReadOuputDTO.setReadLines(readLines);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SVNManagerLogger.debug(logger, CommonFileReaderConstants.APPLICATION_NAME,CommonFileReaderConstants.Module_Name,"Entering readFile method of TextFileReader","FileReadOuputDTO",fileReadOuputDTO);
		return fileReadOuputDTO;
	}

}
