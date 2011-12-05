package com.common.file.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.common.file.enums.XMLAttributesEnum;

public class SVNNodeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -775213674747983342L;
	
	private String nodeName;
	private String localNodePath;
	private String remoteNodePath;
	
	public SVNNodeDTO(Map<String,String> inputMap){
		String nodeName = XMLAttributesEnum.ATT_NODE_NAME.getEnumVal();
		String localPath = XMLAttributesEnum.ATT_LOCAL_NODE_ROOT.getEnumVal();
		String remotePath = XMLAttributesEnum.ATT_REMOTE_NODE_ROOT.getEnumVal();
		StringBuilder keyBuilder = new StringBuilder(XMLAttributesEnum.NODE_MAPPING.getEnumVal());
		//System.out.println(inputMap);
		//System.out.println("key:"+keyBuilder.append(nodeName).toString());
		this.setNodeName(inputMap.get(keyBuilder.append(nodeName).toString()));
		keyBuilder = keyBuilder.delete(keyBuilder.indexOf(nodeName,0),keyBuilder.length());
		this.setLocalNodePath(inputMap.get(keyBuilder.append(localPath).toString()));
		keyBuilder = keyBuilder.delete(keyBuilder.indexOf(localPath,0),keyBuilder.length());
		this.setRemoteNodePath(inputMap.get(keyBuilder.append(remotePath).toString()));
	}
	
	public String getLocalNodePath() {
		return localNodePath;
	}
	public void setLocalNodePath(String localNodePath) {
		this.localNodePath = localNodePath;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getRemoteNodePath() {
		return remoteNodePath;
	}
	public void setRemoteNodePath(String remoteNodePath) {
		this.remoteNodePath = remoteNodePath;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		Map<String,String> stringMap = new HashMap<String, String>();
		stringMap.put("nodename", nodeName);
		stringMap.put("localnoderoot", localNodePath);
		stringMap.put("remotenoderoot", remoteNodePath);
		return stringMap.toString();
	}

}
