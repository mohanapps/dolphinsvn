package com.common.file.dto;

import java.io.Serializable;
import java.util.List;

public class SVNEnvironmentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1275043031845224287L;
	
	private String name;
	private List<SVNNodeDTO> nodeList;
	
	public SVNEnvironmentDTO(List<SVNNodeDTO> list,String name){
		this.nodeList = list;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SVNNodeDTO> getNodeList() {
		return nodeList;
	}
	public void setNodeList(List<SVNNodeDTO> nodeList) {
		this.nodeList = nodeList;
	}

}
