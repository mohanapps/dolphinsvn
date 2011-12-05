package com.svn.client.vo;

import java.util.List;

import com.svn.properties.enums.LocalWorkSpacePathEnum;

public class SVNCopyFilesVo extends SVNBaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3610379122088843189L;
	
	private LocalWorkSpacePathEnum source;
	private LocalWorkSpacePathEnum destination;
	private List<String> filesList;
	private List<String> srcFileList;
	private List<String> destFileList;
	
	public SVNCopyFilesVo(){
		
	}
	
	public SVNCopyFilesVo(List<String> filesList,LocalWorkSpacePathEnum source,LocalWorkSpacePathEnum destination){
		this.source = source;
		this.destination = destination;
		this.filesList = filesList;
	}
	
	public LocalWorkSpacePathEnum getDestination() {
		return destination;
	}
	public void setDestination(LocalWorkSpacePathEnum destination) {
		this.destination = destination;
	}
	
	public LocalWorkSpacePathEnum getSource() {
		return source;
	}
	public void setSource(LocalWorkSpacePathEnum source) {
		this.source = source;
	}

	public List<String> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<String> filesList) {
		this.filesList = filesList;
	}

	public List<String> getDestFileList() {
		return destFileList;
	}

	public void setDestFileList(List<String> destFileList) {
		this.destFileList = destFileList;
	}

	public List<String> getSrcFileList() {
		return srcFileList;
	}

	public void setSrcFileList(List<String> srcFileList) {
		this.srcFileList = srcFileList;
	}

	

}
