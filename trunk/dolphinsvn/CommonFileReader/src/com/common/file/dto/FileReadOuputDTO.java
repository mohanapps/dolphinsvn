package com.common.file.dto;

import java.util.List;

public class FileReadOuputDTO {
	
	private List<String> readLines;
	private List<SVNRepositoryDTO> repoList;
	
	public FileReadOuputDTO(){
		
	}
	
	public FileReadOuputDTO(List<String> readLines){
		this.readLines = readLines;
	}
	
	public List<String> getReadLines() {
		return readLines;
	}

	public void setReadLines(List<String> readLines) {
		this.readLines = readLines;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return readLines.toString();
	}

	public List<SVNRepositoryDTO> getRepoList() {
		return repoList;
	}

	public void setRepoList(List<SVNRepositoryDTO> repoList) {
		this.repoList = repoList;
	}
	

}
