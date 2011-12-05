package com.common.file.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.common.file.enums.SVNRepositoryTypeEnum;
import com.common.file.enums.XMLAttributesEnum;

public class SVNRepositoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1167047577880958645L;
	
	private String url;
	private String repositoryName;
	private String userName;
	private String password;
	private SVNRepositoryTypeEnum repositoryTypeEnum;
	private List<SVNEnvironmentDTO> environmentList;
	
	public SVNRepositoryDTO(List<SVNEnvironmentDTO> envList,Map<String,String> inputMap){
		
		String urlAtt = XMLAttributesEnum.ATT_URL.getEnumVal();
		String userNameAtt = XMLAttributesEnum.ATT_USER_NAME.getEnumVal();
		String passwordAtt = XMLAttributesEnum.ATT_PASSWORD.getEnumVal();
		String repoName = XMLAttributesEnum.ATT_REPOSITORY_NAME.getEnumVal();
		String repoType = XMLAttributesEnum.ATT_REPOSITORY_TYP.getEnumVal();
		StringBuilder builder = new StringBuilder(XMLAttributesEnum.SVNREPOSITORY.getEnumVal());
		this.url = inputMap.get(builder.append(urlAtt).toString());
		builder = builder.delete(builder.indexOf(urlAtt,0),builder.length());
		this.userName = inputMap.get(builder.append(userNameAtt).toString());
		builder = builder.delete(builder.indexOf(userNameAtt,0),builder.length());
		this.password = inputMap.get(builder.append(passwordAtt).toString());
		builder = builder.delete(builder.indexOf(passwordAtt,0),builder.length());
		this.repositoryName = inputMap.get(builder.append(repoName).toString());
		builder = builder.delete(builder.indexOf(repoName,0),builder.length());
		this.repositoryTypeEnum = SVNRepositoryTypeEnum.getEnum(inputMap.get(builder.append(repoType).toString()));
		this.environmentList = envList;
	}
	
	
	public List<SVNEnvironmentDTO> getEnvironmentList() {
		return environmentList;
	}
	public void setEnvironmentList(List<SVNEnvironmentDTO> environmentList) {
		this.environmentList = environmentList;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getRepositoryName() {
		return repositoryName;
	}


	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}


	public SVNRepositoryTypeEnum getRepositoryTypeEnum() {
		return repositoryTypeEnum;
	}


	public void setRepositoryTypeEnum(SVNRepositoryTypeEnum repositoryTypeEnum) {
		this.repositoryTypeEnum = repositoryTypeEnum;
	}
	
	

}
