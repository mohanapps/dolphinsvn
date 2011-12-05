package com.common.file.reader;

import java.io.File;

import com.common.file.dto.FileReadOuputDTO;

public class XlsFileReader implements ICommonFileReader {

	private File inputFile;
	
	public XlsFileReader(File inputFile){
		this.inputFile = inputFile;
	}
	
	public FileReadOuputDTO readFile() {
		// TODO Auto-generated method stub
		return null;
	}

}
